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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//Integration Testing
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    void createEvent_ShouldReturnCreated() throws Exception {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventService.createEvent(any(Event.class))).thenReturn(event);

        mockMvc.perform(post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Event\", \"date\":\"2024-12-01\", \"location\":\"Location\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Event"));
    }

    @Test
    void getEventById_ShouldReturnEvent() throws Exception {
        Event event = new Event(1L, "Test Event", LocalDate.now(), "Location", "Desc");
        when(eventService.getEventById(1L)).thenReturn(event);

        mockMvc.perform(get("/events/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Event"));
    }

    @Test
    void deleteEvent_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/events/1"))
                .andExpect(status().isNoContent());
    }
}
