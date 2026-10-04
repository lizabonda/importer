# Importer

A service for downloading and parsing data from XML files.

### Quick Start
1. **Start the database:**
   ```bash
   docker-compose up -d
   ```
2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

### How it works
On startup, the application:
1. Downloads a ZIP archive with XML data from an external source.
2. Extracts the XML to a `downloads` folder.
3. Parses data about municipalities and their parts.
4. Saves the results to PostgreSQL (Flyway migrations are applied automatically).

### Tech Stack
* Spring Boot, Spring Data JPA
* Flyway
* PostgreSQL

