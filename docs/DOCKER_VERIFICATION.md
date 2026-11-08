# Docker verification

Verified locally on November 7, 2026.

## Issue and fix

The Docker CLI selected `desktop-linux`, but Docker Desktop's user service was stopped. Its socket at `~/.docker/desktop/docker.sock` did not exist, so the CLI could not reach the engine.

```bash
systemctl --user start docker-desktop
systemctl --user enable docker-desktop
docker info
```

The service is now active and enabled for graphical login. The running server reports Docker Desktop with Engine 29.7.2; the host CLI is 29.9.2. No engine reinstall or application source change was required.

## Verified deployment

From the repository root:

```bash
docker compose -p hms-verify --env-file .local/docker-demo.env up --build --wait --wait-timeout 240
docker compose -p hms-verify --env-file .local/docker-demo.env ps
docker compose -p hms-verify --env-file .local/docker-demo.env exec -T app id
curl --fail http://localhost:8083/health/ready
HMS_TEST_ADMIN_PASSWORD=AdminDemo@123 python3 scripts/smoke_test.py http://localhost:8083
```

- Multi-stage image build succeeded, including all 13 Java tests.
- Both the application and MySQL containers reached healthy status.
- Readiness returned HTTP 200 with `ok`.
- Application process identity: UID/GID 10001 (`hospital`).
- Runtime: Tomcat 10.1.61, Temurin Java 17.0.21, MySQL 8.4.13.
- All 85 HTTP integration checks passed, including authentication, CSRF, ownership, concurrent booking, idempotency, cancellation, notes, password changes, and archiving.

The demo is available at http://localhost:8083. Admin login: `admin@hospital.test` / `AdminDemo@123`. This local demo contains fictional records, including records created by the integration checks.

`.local/docker-demo.env` is a private, ignored file with mode 600. It contains independently generated database passwords, the admin password hash, `DEMO_MODE=true`, and `APP_PORT=8083`. It is local workstation configuration; on another checkout, create your own configuration from `.env.example` using the README instructions.

## Start and stop this demo

```bash
# Start again using the existing database volume.
docker compose -p hms-verify --env-file .local/docker-demo.env up -d --wait

# Stop the containers while keeping the database volume.
docker compose -p hms-verify --env-file .local/docker-demo.env stop
```

The MySQL port is not published to the host. The app is bound to localhost port 8083. The named volume `hms-verify_hospital-db` retains the demo data; changing environment passwords alone does not change credentials already initialized in that volume.

This verifies the local Docker deployment. The separately recorded performance benchmark used the external Tomcat deployment, and hosted CI/public deployment remain unverified.
