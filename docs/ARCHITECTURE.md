# Architecture

## Context
Talent Supply is a workflow-centric system tracking the lifecycle of a Talent: from availability → interviews → selection → deployment → rolloff.

## Modules
- **backend/** Spring Boot 3 (Java 17): REST API, JPA, JWT Security, Flyway, MapStruct.
- **ui/** React + Vite + Tailwind: login, search/list, inline status update.
- **Postman/** Collection for core flows.
- **CI** GitHub Actions: build, tests (incl. Testcontainers), UI build, artifacts.

## Data Model (ER)
- Talent (1) —— (n) Interview
- Talent (1) —— (1) Deployment
- Talent (1) —— (n) StatusHistory
- UserAccount (auth)

Key enums: TalentStatus, InterviewResult, DeploymentStatus

## Key Sequences
### Status Update
1. UI posts `/api/talents/{id}/status` with `{newStatus, reason, changedBy}`.
2. Service validates state transition; writes `StatusHistory`.
3. Talent.currentStatus is updated, `updatedAt` changes.

### Interview Result
1. PATCH `/api/interviews/{id}` with `{result, feedback, rating}`.
2. If `PASS` → Talent.SELECTED; `FAIL` → Talent.AVAILABLE.

## Decisions
- **MapStruct** to keep controllers clean and avoid exposing entities.
- **Flyway** for schema versioning + seed.
- **JWT** stateless auth. Allow `/auth/**` & Swagger.
- **JPA Specification** for filtering (status, skill like, location, minExp).
