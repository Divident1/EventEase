package com.revature.eventease.controller;

import com.revature.eventease.model.Event;
import com.revature.eventease.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Integration Testing
@WebMvcTest(EventController.class)
/**
 * Integration tests for EventController.
 * Uses MockMvc to simulate HTTP requests and verify responses.
 */
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    /**
     * Test case: Verify POST /events creates a new event and returns 201 Created.
     */
    @Test
    void createEvent_ShouldReturnCreated() throws Exception {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/api/v1/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Event\", \"date\":\"2024-12-01\", \"location\":\"Location\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Event"));
    }

    /**
     * Test case: Verify GET /events/{id} returns the event JSON and 200 OK.
     */
    @Test
    void getEventById_ShouldReturnEvent() throws Exception {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventService.getEventById(1L)).thenReturn(event);

        mockMvc.perform(get("/api/v1/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Event"));
    }

    /**
     * Test case: Verify DELETE /events/{id} calls delete service and returns 204 No
     * Content.
     */
    @Test
    void deleteEvent_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/events/1"))
                .andExpect(status().isNoContent());
    }

    /**
     * Test case: Verify GET /api/v1/events returns a list of events and 200 OK.
     */
    @Test
    void getAllEvents_ShouldReturnList() throws Exception {
        Event event1 = new Event(1L, "Event 1", LocalDate.now(), "Loc 1", "Desc 1");
        Event event2 = new Event(2L, "Event 2", LocalDate.now(), "Loc 2", "Desc 2");

        when(eventService.getAllEvents()).thenReturn(List.of(event1, event2));

        mockMvc.perform(get("/api/v1/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Event 1"));
    }
}
