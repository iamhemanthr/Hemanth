# Talent Supply

Production-ready monorepo for managing talent supply (CRUD + interviews + deployment + status history + JWT auth).

## Quick Start

### Backend (H2 dev)
```bash
cd backend
./mvnw spring-boot:run
# Swagger UI -> http://localhost:8080/swagger-ui.html
```

### Docker (Postgres + app)
```bash
docker compose up --build
```

### UI (Vite)
```bash
cd ui
npm install
npm run dev
# UI -> http://localhost:5173
```

### Tests
```bash
cd backend
./mvnw -q test
```

## Repo Layout
```
backend/      # Spring Boot 3 (Java 17, Maven)
ui/           # React + Vite + Tailwind
docs/         # ARCHITECTURE, API, SETUP
Postman/      # Postman collection
.github/      # GitHub Actions CI
```

> See docs/SETUP.md for details.
