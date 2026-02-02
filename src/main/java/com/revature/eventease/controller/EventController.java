package com.revature.eventease.controller;

import com.revature.eventease.model.Event;
import com.revature.eventease.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
/**
 * REST Controller for Event resources.
 * Exposes endpoints for CRUD operations on events.
 */
public class EventController {

    private final EventService eventService;

    /**
     * Creates a new event.
     *
     * @param event The event payload.
     * @return The created event wrapped in ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    /**
     * Retrieves an event by ID.
     *
     * @param id The ID of the event.
     * @return The event if found, wrapped in ResponseEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    /**
     * Updates an existing event.
     *
     * @param id    The ID of the event to update.
     * @param event The updated event details.
     * @return The updated event wrapped in ResponseEntity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Valid @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updatedEvent);
    }

    /**
     * Deletes an event by ID.
     *
     * @param id The ID of the event to delete.
     * @return No Content response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
