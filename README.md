# EventEase - Event Management API

Hi there! 👋 This is **EventEase**, a simple but powerful REST API I built to help manage events. The idea was to create something that could handle the basics—creating events, looking them up, updating details, and cancelling them if needed.

I built this using **Spring Boot** because I wanted it to be robust and easy to scale. For the database, I went with **MySQL** so everything is stored reliably.

## What's Inside?

I've implemented full CRUD operations (Create, Read, Update, Delete). Here's what you can do:

- **Create an Event**: `POST /api/v1/events` - Add a new event to the system.
- **Get All Events**: `GET /api/v1/events` - See everything that's coming up.
- **Find an Event**: `GET /api/v1/events/{id}` - Look up specific details for an event.
- **Update Details**: `PUT /api/v1/events/{id}` - Change the date, location, or description.
- **Delete an Event**: `DELETE /api/v1/events/{id}` - Remove an event if it's cancelled.

## How to Run It

If you want to try it out on your machine, it's pretty straightforward:

1.  Make sure you have **Java 17** and **Maven** installed.
2.  You'll need a MySQL database running. I used `eventease` as the database name with `root/root` credentials. You can change this in `src/main/resources/application.properties` if your setup is different.
3.  Open a terminal in the project folder and run:
    ```bash
    mvn spring-boot:run
    ```
4.  Once it's running, you can test the endpoints using Postman or curl.

## Testing

I didn't want to leave things to chance, so I wrote some tests:
- **Unit Tests**: Checks the logic in my `EventService` to make sure it handles data correctly.
- **Integration Tests**: Tests the `EventController` to ensure the API endpoints actually respond the way they should.

You can run them yourself with:
```bash
mvn test
```

## Questions?

Feel free to poke around the code! I tried to add helpful comments (JavaDocs) to explain what the main classes do. If you spot anything or have questions, let me know.
