package com.eventix.event.modules.events.controller;

import com.eventix.event.modules.events.dto.EventCreateDTO;
import com.eventix.event.modules.events.service.EventService;
import com.eventix.event.modules.events.dto.StringReponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/event")
    public ResponseEntity<StringReponse> createEvent(HttpServletRequest request, @RequestBody EventCreateDTO event) {
        return eventService.createEvent(request, event);
    }
}
