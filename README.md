
aaaaa
F
asd2

lmao
xd
reeee
f
a
f
a
F
xd
a
a
Change QA
why

## CI/CD Setup (Nexus)

1. **GitHub Secrets (repo → Settings → Secrets → Actions)**:
   - `SONAR_HOST_URL`, `SONAR_TOKEN`
   - `SMTP_SERVER`, `SMTP_PORT`, `SMTP_USERNAME`, `SMTP_PASSWORD`
   - `MAIL_TO_LEAD`, `MAIL_TO_PO`

2. **Branch protection** for `dev`, `uat`, `master`:
   - Require PR, Require review by Lead Dev
   - Required status checks: **PR Gate (Nexus - Sonar + Tests)**

3. **Jenkins**: create a Multibranch Pipeline pointing to this repo.
   - Jenkins will deploy to **dev/uat/prod** automatically after merge.