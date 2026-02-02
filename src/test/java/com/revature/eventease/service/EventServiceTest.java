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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void createEvent_ShouldReturnSavedEvent() {
        Event event = new Event(null, "Test Event", LocalDate.now(), "Location", "Desc");
        Event savedEvent = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        Event result = eventService.createEvent(event);

        assertNotNull(result.getId());
        assertEquals("Test Event", result.getName());
    }

    @Test
    void getEventById_ShouldReturnEvent_WhenExists() {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getEventById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void getEventById_ShouldThrowException_WhenNotFound() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> eventService.getEventById(1L));
    }

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
}
