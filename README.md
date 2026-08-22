# Blog API

A blogging backend built with **Spring Boot**: JWT-secured authentication, posts, and comments, exposed as a clean REST API.

---

## Features

- **Auth** — registration and login issuing JWTs, enforced by a custom `JwtAuthFilter` on top of Spring Security
- **Posts** — full CRUD, scoped to the authenticated author
- **Comments** — comments attached to posts, with their own endpoints
- **DTO mapping** — dedicated mappers keep entities out of the API surface
- **Error handling** — centralised `ApiExceptionHandler` returning consistent JSON errors
- **Containerised** — Docker Compose for the database

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot, Spring Security, Spring Data JPA |
| Auth | JWT |
| Infra | Docker Compose |
| Build | Maven |

## Architecture

```
controller  ->  service  ->  repository  ->  entity
                  |
                  +-- dto + mapper
                  +-- config (SecurityConfig, JwtAuthFilter)
                  +-- exception (ApiException -> ApiExceptionHandler)
```

## Getting Started

**Prerequisites:** JDK 17+, Docker, Maven

```bash
git clone https://github.com/native-99/Blog-Powered-with-AI.git
cd Blog-Powered-with-AI/Blog

docker compose up -d      # start the database
./mvnw spring-boot:run
```

The API runs on `http://localhost:8080`.

## Roadmap

- [ ] Unit and integration tests
- [ ] OpenAPI/Swagger documentation
- [ ] Pagination and search on posts
