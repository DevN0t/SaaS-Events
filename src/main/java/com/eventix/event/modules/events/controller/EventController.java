package com.eventix.event.modules.events.controller;

import com.eventix.event.modules.events.dto.EventCreateDTO;
import com.eventix.event.modules.events.dto.EventDTO;
import com.eventix.event.modules.events.dto.EventShortDTO;
import com.eventix.event.modules.events.service.EventService;
import com.eventix.event.modules.events.dto.StringReponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event/")
    public ResponseEntity<StringReponse> createEvent(HttpServletRequest request, @RequestBody EventCreateDTO event) {
        return eventService.createEvent(request, event);
    }

    @PutMapping("/event/")
    public ResponseEntity<StringReponse> updateEvent(HttpServletRequest request, @RequestBody EventCreateDTO event, @RequestParam Integer id) {
        return eventService.updateEvent(request, event, id);
    }

    @GetMapping("/event/")
    public ResponseEntity<EventDTO> getEvent(HttpServletRequest request, @RequestParam Integer id) {
        return eventService.getEventById(request, id);
    }

    @GetMapping("/events/")
    public ResponseEntity<List<EventShortDTO>> getEvents(HttpServletRequest request) {
        return eventService.getAllEvents(request);
    }

    @DeleteMapping("/event/")
    public ResponseEntity<StringReponse> deleteEvent(HttpServletRequest request, @RequestParam Integer id) {
        return eventService.deleteEvent(request, id);
    }
}
