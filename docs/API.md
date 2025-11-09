# API

Base path: `/api` (JWT-protected). See Swagger at `/swagger-ui.html`.

## Auth (no auth required)
- POST `/auth/register`
- POST `/auth/login` -> `{token}` (Bearer)

## Talents
- GET `/api/talents?status=AVAILABLE&skill=Java&location=BLR&minExp=3&page=0&size=20&sort=updatedAt,desc`
- GET `/api/talents/{id}`
- POST `/api/talents`
- PUT `/api/talents/{id}`
- DELETE `/api/talents/{id}`
- POST `/api/talents/{id}/status` (StatusUpdateRequest)

## Interviews
- GET `/api/talents/{id}/interviews`
- POST `/api/talents/{id}/interviews`
- PATCH `/api/interviews/{interviewId}` (update result/feedback/rating)

## Deployment
- GET `/api/talents/{id}/deployment`
- POST `/api/talents/{id}/deployment`
- PATCH `/api/deployments/{id}`
