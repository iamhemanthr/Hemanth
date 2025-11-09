# Setup

## Local (H2)
- Run backend: `./mvnw spring-boot:run` (Swagger UI at `/swagger-ui.html`)
- Login with seed admin: `admin / Admin@123`

## Docker (Postgres + app)
```bash
docker compose up --build
```

## UI
```bash
cd ui
npm install
npm run dev
```
Set `VITE_API_BASE` to point to backend (default `http://localhost:8080`).
