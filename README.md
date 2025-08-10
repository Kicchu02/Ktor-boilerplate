# Ktor Sample Project

A Kotlin-based web application built with Ktor framework, featuring user authentication, PostgreSQL database integration, and JOOQ for database operations.

## Prerequisites

### For Linux

- Docker and Docker Compose must be installed
- Java 17 or higher
- Gradle (included via wrapper)

### For Windows

- Docker Desktop must be installed and running
- Java 17 or higher
- Gradle (included via wrapper)

## Project Setup

### 1. Database Setup

The project uses PostgreSQL database running in Docker. Before starting the application, you need to start the database:

#### On Linux

**Start the database:**

```bash
./scripts/start_dev_docker.sh
```

**Stop the database:**

```bash
./scripts/stop_dev_docker.sh
```

**Restart the database:**

```bash
./scripts/restart_db.sh
```

#### On Windows

**Start the database:**

```cmd
scripts\start_dev_docker.bat
```

**Stop the database:**

```cmd
scripts\stop_dev_docker.bat
```

**Restart the database:**

```cmd
scripts\restart_db.bat
```

**Important**: Ensure Docker is running in the background before executing these scripts.

## Running the Application

### 1. Build the Project

```bash
# Build the project
./gradlew build
```

### 2. Run the Application

```bash
# Run in development mode
./gradlew run
```

## Database Migrations

The project uses Flyway for database migrations. Database migration scripts are located in `src/main/resources/db/migration/` folder. These migrations will be automatically applied when running the PostgreSQL database using the scripts provided above.

**Note**: The migrations are applied to the database container when it starts up, ensuring your database schema is always up-to-date.

## Development

### Code Formatting

```bash
./gradlew spotlessApply
```

### JOOQ Code Generation

JOOQ code is automatically generated in the scripts when building the project. The generated code provides type-safe database access using the database schema.

```bash
./gradlew generateJooq
```
