package com.revature.eventease.service;

import com.revature.eventease.model.Event;
import com.revature.eventease.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
/**
 * Service class for managing Event operations.
 * Handles business logic for creating, retrieving, updating, and deleting
 * events.
 */
public class EventService {

    private final EventRepository eventRepository;

    /**
     * Creates a new event.
     *
     * @param event The event object to be created.
     * @return The created event with generated ID.
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Retrieves an event by its ID.
     *
     * @param id The ID of the event to retrieve.
     * @return The found event.
     * @throws EntityNotFoundException if no event is found with the given ID.
     */
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
    }

    /**
     * Retrieves all events.
     *
     * @return A list of all events.
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Updates an existing event.
     *
     * @param id           The ID of the event to update.
     * @param eventDetails The updated event details.
     * @return The updated event object.
     * @throws EntityNotFoundException if no event is found with the given ID.
     */
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getEventById(id);
        event.setName(eventDetails.getName());
        event.setDate(eventDetails.getDate());
        event.setLocation(eventDetails.getLocation());
        event.setDescription(eventDetails.getDescription());
        return eventRepository.save(event);
    }

    /**
     * Deletes an event by its ID.
     *
     * @param id The ID of the event to delete.
     * @throws EntityNotFoundException if no event is found with the given ID.
     */
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }
}
