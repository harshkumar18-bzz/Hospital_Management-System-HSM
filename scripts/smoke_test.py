#!/usr/bin/env python3
"""Exercise the local demo with synthetic data. Requires only Python's standard library."""
import datetime
import http.cookiejar
import re
import sys
import urllib.error
import urllib.parse
import urllib.request
import uuid

BASE = sys.argv[1] if __name__ == "__main__" and len(sys.argv) > 1 else "http://localhost:8080"
assert urllib.parse.urlparse(BASE).hostname in ("localhost", "127.0.0.1"), "Run against the local demo only."


class Session:
    def __init__(self):
        self.client = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(http.cookiejar.CookieJar()))
        self.token = None

    def request(self, path, data=None, csrf=True):
        if data is not None:
            data = dict(data)
            if csrf:
                data["csrfToken"] = self.token or ""
            data = urllib.parse.urlencode(data).encode()
        try:
            response = self.client.open(BASE + path, data=data, timeout=15)
        except urllib.error.HTTPError as error:
            response = error
        text = response.read().decode()
        token = re.search(r'name="csrfToken" value="([^"]+)"', text)
        if token:
            self.token = token.group(1)
        return response.status, response.url, text


checks = 0


def check(condition, label):
    global checks
    assert condition, label
    checks += 1
    print("PASS", label)


def login(session, role, email, password):
    page, action = {"patient": ("user_Login.jsp", "userLogin"), "doctor": ("doctor.jsp", "loginDoctor"), "admin": ("admin.jsp", "adminLogin")}[role]
    session.request("/" + page)
    return session.request("/" + action, {"email": email, "password": password})


def register(name):
    session = Session()
    session.request("/signup.jsp")
    email = "test-" + uuid.uuid4().hex + "@hospital.test"
    status, url, text = session.request("/register", {"Fullname": name, "email": email, "password": "Patient@123"})
    check(status == 200 and url.endswith("user_Login.jsp") and "account is ready" in text, "patient registration")
    status, url, text = login(session, "patient", email, "Patient@123")
    check(status == 200 and url.endswith("view_appointment.jsp"), "patient login")
    return session, email



def main():
    # Run only against a local demo. These synthetic records remain for inspection.
    guest = Session()
    check('data-demo-mode="true"' in guest.request("/")[2], "target is the local sample-data demo")
    for path in ("/", "/signup.jsp", "/user_Login.jsp", "/doctor.jsp", "/admin.jsp", "/component/app.css", "/health/live", "/health/ready"):
        check(guest.request(path)[0] == 200, "public page " + path)
    for path, destination in (("/user_appointment.jsp","user_Login.jsp"),("/admin/patient.jsp","admin.jsp"),("/doctor/patient.jsp","doctor.jsp")):
        check(guest.request(path)[1].endswith(destination), "protect "+path)
    check(guest.request("/api/slots")[0] == 401, "API requires authentication")
    check(guest.request("/unknown")[0] == 404, "unlisted routes are denied")
    check(guest.request("/register",{"email":"test@hospital.test"},csrf=False)[0] == 403, "reject missing CSRF")
    patient,email = register('<script>alert("test")</script>')
    check("&lt;script&gt;" in patient.request("/view_appointment.jsp")[2], "escape account names")
    check(patient.request("/admin/index.jsp")[1].endswith("admin.jsp"), "patient cannot access admin")
    text = patient.request("/user_appointment.jsp")[2]
    slot_markup = text.split('<select id="slot"',1)[1].split('</select>',1)[0]
    match = re.search(r'<option value="(\d+)">([^<]*Aditi[^<]*)</option>', slot_markup)
    check(match is not None, "booking page contains real published slots")
    slot = match.group(1)
    key = re.search(r'name="requestKey" value="([^"]+)"',text).group(1)
    reason = "Synthetic visit "+uuid.uuid4().hex[:10]
    booking = {"slotId":slot,"requestKey":key,"userid":"999999","fullname":"Synthetic Patient","gender":"other","age":"24","email":email,"phno":"9000000000","diseases":reason,"address":"Synthetic address"}
    bad=dict(booking,age="999")
    check(patient.request("/appAppointment",bad)[0] == 400, "server rejects invalid age")
    status,url,text = patient.request("/appAppointment",booking)
    check(status == 200 and reason in text and "PENDING" in text, "booking reserves a slot for session account")
    appointment_id = urllib.parse.parse_qs(urllib.parse.urlparse(url).query)["id"][0]
    check(patient.request("/appAppointment",booking)[1].endswith("id="+appointment_id), "repeated request key returns same appointment")
    check(patient.request("/appAppointment",dict(booking,fullname="Changed payload"))[0] == 409, "request key cannot identify different payload")
    other,_ = register("Another Synthetic Patient")
    check(other.request("/appointment.jsp?id="+appointment_id)[0] == 404, "patient cannot read another appointment")
    check(other.request("/api/appointments/"+appointment_id)[0] == 404, "API applies same object ownership")
    check(other.request("/appAppointment",dict(booking,requestKey=uuid.uuid4().hex))[0] == 409, "occupied slot returns conflict")
    check(patient.request("/appointmentState",{"id":appointment_id,"status":"CONFIRMED"})[0] == 403, "patient cannot confirm own appointment")
    doctor=Session()
    check(login(doctor,"doctor","aditi@hospital.test","Doctor@123")[1].endswith("doctor/index.jsp"), "doctor login")
    check(doctor.request("/doctor/comment.jsp?id="+appointment_id)[0] == 200, "assigned doctor can read consultation")
    unassigned=Session()
    login(unassigned,"doctor","arjun@hospital.test","Doctor@123")
    check(unassigned.request("/doctor/comment.jsp?id="+appointment_id)[0] == 404, "other doctor cannot read consultation")
    check(unassigned.request("/updateStatus",{"id":appointment_id,"did":"1","comm":"Unauthorized note"})[0] == 404, "forged doctor ID cannot write note")
    check(doctor.request("/appointmentState",{"id":appointment_id,"status":"CONFIRMED"})[0] == 200, "assigned doctor confirms appointment")
    check(doctor.request("/appointmentState",{"id":appointment_id,"status":"COMPLETED"})[0] == 409, "future appointment cannot complete early")
    doctor.request("/updateStatus",{"id":appointment_id,"comm":"First synthetic note <script>test</script>"})
    doctor.request("/updateStatus",{"id":appointment_id,"comm":"Second synthetic note"})
    text=patient.request("/appointment.jsp?id="+appointment_id)[2]
    check("First synthetic note &lt;script&gt;test&lt;/script&gt;" in text and "Second synthetic note" in text, "append-only escaped consultation history")
    check("CONFIRMED" in text, "notes do not overwrite status")
    check("NOTE_ADDED" in text and "STATUS_CHANGED" in text, "auditable activity history")
    check(patient.request("/appointmentState",{"id":appointment_id,"status":"CANCELLED"})[0] == 200, "patient can cancel future appointment")
    check(doctor.request("/updateStatus",{"id":appointment_id,"comm":"Late note"})[0] == 409, "cancelled record rejects new notes")
    status,url,text=other.request("/appAppointment",dict(booking,requestKey=uuid.uuid4().hex))
    check(status == 200 and "PENDING" in text, "cancellation releases slot for rebooking")
    other_id=urllib.parse.parse_qs(urllib.parse.urlparse(url).query)["id"][0]
    check(patient.request("/appointment.jsp?id="+appointment_id)[0] == 200, "cancelled history remains accessible")

    # Complete HTTP concurrency test: separate sessions, the same slot, synchronized submissions.
    from concurrent.futures import ThreadPoolExecutor
    from threading import Barrier
    a,ae=register("Race A")
    b,be=register("Race B")
    slots=__import__("json").loads(a.request("/api/slots")[2])["slots"]
    race_slot=str(slots[0]["id"])
    barrier=Barrier(2)
    def race(session,email):
        form=dict(booking,slotId=race_slot,requestKey=uuid.uuid4().hex,email=email)
        barrier.wait()
        return session.request("/appAppointment",form)[0]
    with ThreadPoolExecutor(max_workers=2) as pool:
        futures=[pool.submit(race,a,ae),pool.submit(race,b,be)]
        check(sorted(f.result() for f in futures)==[200,409], "simultaneous HTTP bookings have exactly one winner")

    admin=Session()
    import os
    check(login(admin,"admin","admin@hospital.test",os.environ.get("HMS_TEST_ADMIN_PASSWORD","Admin@123"))[1].endswith("admin/index.jsp"), "admin login")
    for path in ("/admin/doctor.jsp","/admin/view_doctor.jsp","/admin/edit_doctor.jsp?id=1","/admin/patient.jsp","/admin/slots.jsp","/doctor/edit_profile.jsp","/doctor/slots.jsp"):
        session=doctor if path.startswith("/doctor") else admin
        check(session.request(path)[0]==200, "render "+path)
    check(admin.request("/admin/metrics")[0]==200, "protected operational metrics")
    check("hms_http_requests_total" in admin.request("/admin/metrics")[2], "metrics include request counts")
    check(patient.request("/publishSlot",{"doctorId":"1","startsAt":"2030-01-01T10:00"})[0]==403, "patients cannot publish slots")
    check(admin.request("/delete_doctor",{"id":"1"})[0]==409, "doctor with open appointments cannot be archived")
    check(admin.request("/delete_doctor?id=1")[0]==405, "GET cannot archive doctors")

    # Staff availability, soft archiving, and doctor credential/session checks.
    name="SyntheticDoctor"+uuid.uuid4().hex[:8]
    doctor_email=uuid.uuid4().hex+"@doctor.test"
    profile={"fullname":name,"dob":"1980-01-01","qualification":"MD","specialist":"General Medicine","email":doctor_email,"mobno":"9000000000","password":"Temporary@123"}
    status,url,text=admin.request("/addDoctor",profile)
    check(status==200 and name in text,"admin creates a validated doctor")
    temp_id=re.search(r'<tr><td>'+name+r'</td>.*?edit_doctor.jsp\?id=(\d+)',text,re.S).group(1)
    temp=Session()
    check(login(temp,"doctor",doctor_email,"Temporary@123")[1].endswith("doctor/index.jsp"),"new doctor can authenticate")
    starts=(datetime.date.today()+datetime.timedelta(days=2)).isoformat()+"T10:30"
    check(temp.request("/publishSlot",{"doctorId":"1","startsAt":starts})[0]==200,"doctor publishes own half-hour slot despite forged doctor field")
    available=__import__("json").loads(temp.request("/api/slots?doctorId="+temp_id)[2])["slots"]
    temp_slot=str(next(x["id"] for x in available if x["startsAt"].startswith(starts)))
    check(unassigned.request("/withdrawSlot",{"slotId":temp_slot})[0]==404,"doctor cannot withdraw another doctor's slot")
    check(temp.request("/withdrawSlot",{"slotId":temp_slot})[0]==200,"doctor withdraws an unreserved slot")
    check(temp.request("/publishSlot",{"startsAt":starts[:-2]+"15"})[0]==400,"slot start must align to half-hour boundary")
    check(temp.request("/docChangePassword",{"uid":"1","oldPassword":"Temporary@123","newPassword":"TemporaryNew@123"})[1].endswith("doctor.jsp"),"doctor password change signs out current session")
    check(login(temp,"doctor",doctor_email,"TemporaryNew@123")[1].endswith("doctor/index.jsp"),"new doctor password works")
    status,url,text=admin.request("/delete_doctor",{"id":temp_id})
    check(status==200 and name in text and "Archived" in text,"archiving retains the doctor record")
    check(temp.request("/doctor/index.jsp")[0]==401,"archiving revokes an active doctor session")
    check(login(Session(),"doctor",doctor_email,"TemporaryNew@123")[0]==401,"archived doctor cannot authenticate")

    # Changing a password revokes a second pre-existing session as well.
    second=Session()
    login(second,"patient",email,"Patient@123")
    check(patient.request("/userChangePassword",{"uid":"999999","oldPassword":"wrong","newPassword":"Changed@123"})[0]==400, "wrong current password rejected")
    check(patient.request("/userChangePassword",{"uid":"999999","oldPassword":"Patient@123","newPassword":"Changed@123"})[1].endswith("user_Login.jsp"), "password change signs out current session")
    check(second.request("/view_appointment.jsp")[0]==401, "password change revokes other sessions")
    check(login(Session(),"patient",email,"Changed@123")[1].endswith("view_appointment.jsp"), "new password works for session-owned account")
    check(other.request("/userLogout")[0]==405, "GET cannot log out an authenticated patient")
    check(other.request("/userLogout",{})[1].endswith("user_Login.jsp"), "POST logout works")
    check(other.request("/view_appointment.jsp")[1].endswith("user_Login.jsp"), "logout removes access")
    throttled=Session()
    throttled.request("/user_Login.jsp")
    fake=uuid.uuid4().hex+"@hospital.test"
    for _ in range(5):
        check(throttled.request("/userLogin",{"email":fake,"password":"wrong"})[0]==401, "invalid login rejected")
    check(throttled.request("/userLogin",{"email":fake,"password":"wrong"})[0]==429, "failed login throttle")
    print(f"\n{checks} HTTP integration checks passed. Synthetic records remain in the local demo.")

if __name__ == "__main__":
    main()
