#!/usr/bin/env python3
"""Reproducible authenticated read workload against the local demo; no third-party dependencies."""
import argparse
import concurrent.futures
import datetime
import json
import platform
import statistics
import sys
import time
import uuid
from pathlib import Path
import smoke_test

def main():
    parser=argparse.ArgumentParser()
    parser.add_argument("--base",default="http://localhost:8081")
    parser.add_argument("--users",type=int,default=8)
    parser.add_argument("--requests",type=int,default=50,help="Requests per user")
    parser.add_argument("--duration",type=int,default=0,help="Sustained seconds instead of a fixed request count; max 60")
    parser.add_argument("--output",default="docs/performance/http.json")
    args=parser.parse_args()
    if not 0<=args.duration<=60:parser.error("Use duration 0-60 seconds")
    if not 1<=args.users<=30 or not 1<=args.requests<=1000:parser.error("Use 1-30 users and 1-1000 requests per user")
    smoke_test.BASE=args.base
    assert __import__("urllib.parse",fromlist=["urlparse"]).urlparse(args.base).hostname in ("localhost","127.0.0.1")
    assert 'data-demo-mode="true"' in smoke_test.Session().request("/")[2]
    sessions=[]
    for _ in range(args.users):
        session=smoke_test.Session();session.request("/signup.jsp")
        email="benchmark-"+uuid.uuid4().hex+"@hospital.test"
        assert session.request("/register",{"Fullname":"Synthetic benchmark user","email":email,"password":"Benchmark@123"})[0]==200
        assert smoke_test.login(session,"patient",email,"Benchmark@123")[1].endswith("view_appointment.jsp")
        for path in ("/api/slots?doctorId=1","/api/appointments?page=1"):
            assert session.request(path)[0]==200
        sessions.append(session)
    def worker(session):
        samples=[];errors=0
        i=0
        while (time.perf_counter()<deadline if args.duration else i<args.requests):
            path="/api/slots?doctorId=1" if i%2==0 else "/api/appointments?page=1"
            start=time.perf_counter()
            try:
                status=session.request(path)[0]
                if status!=200:errors+=1
            except Exception:errors+=1
            samples.append((time.perf_counter()-start)*1000)
            i+=1
        return samples,errors
    started=time.perf_counter()
    deadline=started+args.duration
    with concurrent.futures.ThreadPoolExecutor(max_workers=args.users) as pool:
        results=list(pool.map(worker,sessions))
    elapsed=time.perf_counter()-started
    samples=sorted(x for batch,_ in results for x in batch)
    errors=sum(n for _,n in results)
    def percentile(p):return samples[min(len(samples)-1,int((len(samples)-1)*p))]
    report={"recorded_at_utc":datetime.datetime.now(datetime.timezone.utc).isoformat(),"base":args.base,
        "python":platform.python_version(),"machine":platform.machine(),"users":args.users,"requests":len(samples),
        "endpoints":["/api/slots?doctorId=1","/api/appointments?page=1"],
        "setup_and_warmup_excluded":True,"dataset":"Four demo doctors, 14 days of slots; new users have empty appointment histories.",
        "requested_duration_seconds":args.duration,"elapsed_seconds":round(elapsed,3),"throughput_requests_per_second":round(len(samples)/elapsed,2),
        "latency_ms":{"mean":round(statistics.mean(samples),2),"p50":round(percentile(.50),2),"p95":round(percentile(.95),2),"p99":round(percentile(.99),2)},
        "errors":errors,"error_rate":errors/len(samples),
        "limits":"Closed-loop localhost read workload with Python threads and one server. Not a production capacity, write-throughput, or internet-latency claim."}
    target=Path(args.output);target.parent.mkdir(parents=True,exist_ok=True);target.write_text(json.dumps(report,indent=2)+"\n")
    print(json.dumps(report,indent=2))
    return 1 if errors else 0
if __name__=="__main__":sys.exit(main())
