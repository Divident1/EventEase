package com.revature.eventease.service;

import com.revature.eventease.model.Event;
import com.revature.eventease.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
/**
 * Unit tests for EventService.
 * Uses Mockito to mock the EventRepository dependencies.
 */
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    /**
     * Test case: Verify that createEvent saves the event and returns it with an ID.
     */
    @Test
    void createEvent_ShouldReturnSavedEvent() {
        Event event = new Event(null, "Test Event", LocalDate.now(), "Location", "Desc");
        Event savedEvent = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        Event result = eventService.createEvent(event);

        assertNotNull(result.getId());
        assertEquals("Test Event", result.getName());
    }

    /**
     * Test case: Verify that getEventById returns the correct event when it exists.
     */
    @Test
    void getEventById_ShouldReturnEvent_WhenExists() {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(1L);

        assertEquals(1L, result.getId());
    }

    /**
     * Test case: Verify that getEventById throws EntityNotFoundException when the
     * ID does not exist.
     */
    @Test
    void getEventById_ShouldThrowException_WhenNotFound() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> eventService.getEventById(1L));
    }

    /**
     * Test case: Verify that updateEvent updates the fields and returns the saved
     * event.
     */
    @Test
    void updateEvent_ShouldReturnUpdatedEvent() {
        Event existingEvent = new Event(1L, "Old Name", LocalDate.now(), "Old Loc", "Old Desc");
        Event updateDetails = new Event(null, "New Name", LocalDate.now(), "New Loc", "New Desc");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(any(Event.class))).thenReturn(existingEvent);

        Event result = eventService.updateEvent(1L, updateDetails);

        assertEquals("New Name", result.getName());
        assertEquals("New Loc", result.getLocation());
    }

    /**
     * Test case: Verify that getAllEvents returns a list of events.
     */
    @Test
    void getAllEvents_ShouldReturnListOfEvents() {
        Event event1 = new Event(1L, "Event 1", LocalDate.now(), "Loc 1", "Desc 1");
        Event event2 = new Event(2L, "Event 2", LocalDate.now(), "Loc 2", "Desc 2");

        when(eventRepository.findAll()).thenReturn(List.of(event1, event2));

        List<Event> result = eventService.getAllEvents();

        assertEquals(2, result.size());
    }
}
