# Swagger Playground

Eine Spring Boot REST API mit JWT-Authentifizierung und interaktiver Swagger UI Dokumentation.

## Tech Stack

- **Java 21**
- **Spring Boot 4.0.1**
- **Spring Security** mit JWT-Authentifizierung
- **Spring Data JPA** mit MySQL
- **SpringDoc OpenAPI** (Swagger UI)
- **Lombok**
- **Docker Compose** (MySQL)

## Features

- Benutzerregistrierung und Login mit JWT Tokens
- Todo-Verwaltung (CRUD) pro Benutzer
- Automatische Admin-Erstellung beim Start
- Swagger UI mit Bearer Token Support
- Globales Exception Handling

## Projektstruktur

```
src/main/java/com/steinledevops/swaggerplayground/
├── config/          # Security, JWT Filter, Swagger Konfiguration
├── controller/      # REST Endpoints
├── entity/          # JPA Entities (User, Todo, Authority)
├── exceptions/      # Custom Exceptions
├── repository/      # Spring Data Repositories
├── request/         # Request DTOs
├── response/        # Response DTOs
├── service/         # Business Logic
└── util/            # Utility Classes
```

## API Endpoints

### Authentication (`/api/auth`)
| Methode | Endpoint    | Beschreibung                  |
|---------|-------------|-------------------------------|
| POST    | `/register` | Neuen Benutzer registrieren   |
| POST    | `/login`    | Login und JWT Token erhalten  |

### Todos (`/api/todos`)
| Methode | Endpoint         | Beschreibung              |
|---------|------------------|---------------------------|
| POST    | `/`              | Todo erstellen            |
| GET     | `/`              | Alle Todos abrufen        |
| GET     | `/{id}`          | Todo nach ID abrufen      |
| PUT     | `/{id}`          | Todo aktualisieren        |
| DELETE  | `/{id}`          | Todo löschen              |
| PATCH   | `/{id}/complete` | Todo als erledigt markieren |

### User (`/api/user`)
| Methode | Endpoint | Beschreibung                      |
|---------|----------|-----------------------------------|
| GET     | `/info`  | Benutzerinformationen abrufen     |

## Installation & Start

### 1. MySQL starten

```bash
docker compose up -d
```

### 2. Anwendung starten

```bash
./mvnw spring-boot:run
```

### 3. Swagger UI öffnen

```
http://localhost:8080/docs
```

## Authentifizierung in Swagger UI

1. POST `/api/auth/register` - Benutzer registrieren
2. POST `/api/auth/login` - Token erhalten
3. "Authorize" Button klicken und Token eingeben
4. Geschützte Endpoints sind nun nutzbar

## Konfiguration

| Property                | Beschreibung          | Default                |
|-------------------------|-----------------------|------------------------|
| `spring.jwt.secret`     | JWT Secret Key        | (in application.properties) |
| `spring.jwt.expiration` | Token Gültigkeit (ms) | 900000 (15 min)        |
| `springdoc.swagger-ui.path` | Swagger UI Pfad   | `/docs`                |

## Lizenz

Portfolio-Projekt von Sergej Steinle
