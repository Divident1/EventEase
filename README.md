# EventEase - Event Management API

Hi there! 👋 This is **EventEase**, a simple but powerful REST API I built to help manage events. The idea was to create something that could handle the basics—creating events, looking them up, updating details, and cancelling them if needed.

I built this using **Spring Boot** because I wanted it to be robust and easy to scale. For the database, I went with **MySQL** so everything is stored reliably.

## 🚀 Key Features

- **Full CRUD Support**: Complete lifecycle management for events.
- **RESTful API Design**: Clean, standard-compliant endpoints.
- **Data Validation**: Ensures all events have a name, date, and location.
- **Global Exception Handling**: Returns friendly error messages when things go wrong (like 404 or 400).
- **API Versioning**: Future-proofed with `/api/v1/` prefix.

## 🛠 Tech Stack

- **Backend**: Java 17, Spring Boot 3.2.1
- **Database**: MySQL 8.0
- **Documentation**: SpringDoc OpenAPI (Swagger UI)
- **Testing**: JUnit 5, Mockito, MockMvc
- **Build Tool**: Maven

## 📦 Project Structure

```text
src/main/java/com/revature/eventease/
├── controller/  # REST Endpoints (API Layer)
├── service/     # Business Logic (Service Layer)
├── repository/  # Database Access (Data Layer)
├── model/       # Data Models (Entities)
└── exception/   # Global Error Handling
```

## 🔌 API Endpoints

I've implemented full CRUD operations. Here's what you can do:

- **Create an Event**: `POST /api/v1/events` - Add a new event.
- **Get All Events**: `GET /api/v1/events` - See everything coming up.
- **Find an Event**: `GET /api/v1/events/{id}` - Look up specific details.
- **Update Details**: `PUT /api/v1/events/{id}` - Change date, location, or description.
- **Delete an Event**: `DELETE /api/v1/events/{id}` - Remove an event.

## 🏁 How to Run It

1.  Make sure you have **Java 17** and **Maven** installed.
2.  You'll need a MySQL database running.
    - Default database name: `eventease`
    - Credentials: `root/root`
    - *(Adjust these in `src/main/resources/application.properties` if needed)*.
3.  Open a terminal in the project folder and run:
    ```bash
    mvn spring-boot:run
    ```
4.  Once it's running, visit the **Swagger UI** for interactive testing:
    [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

## 🧪 Testing

I wrote both Unit and Integration tests to ensure high code quality:
- **Unit Tests**: Verifies `EventService` business logic.
- **Integration Tests**: Verifies `EventController` endpoints.

Run them with:
```bash
mvn test
```

## 📝 Questions?

Feel free to poke around the code! I've added JavaDocs to explain how it all works under the hood. If you spot anything or have questions, let me know.
