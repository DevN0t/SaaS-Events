package com.eventix.event.modules.participants.controller;

import com.eventix.event.modules.events.dto.StringReponse;
import com.eventix.event.modules.participants.dto.ParticipantsCreateDTO;
import com.eventix.event.modules.participants.dto.ParticipantsDTO;
import com.eventix.event.modules.participants.dto.ParticipantsShortDTO;
import com.eventix.event.modules.participants.dto.ParticipantsUpdateDTO;
import com.eventix.event.modules.participants.service.ParticipantsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParticipantsController {

    @Autowired
    private ParticipantsService participantsService;

    @PostMapping("/participant/")
    public ResponseEntity<StringReponse> createParticipants(HttpServletRequest request, @RequestBody ParticipantsCreateDTO data) {
        return participantsService.createParticipant(request, data);
    }

    @PutMapping("/participant/")
    public ResponseEntity<StringReponse> updateParticipants(HttpServletRequest request, @RequestBody ParticipantsUpdateDTO data, @RequestParam Integer id) {
        return participantsService.updateParticipant(request, data, id);
    }

    @GetMapping("/participant/")
    public ResponseEntity<ParticipantsDTO> getParticipant(HttpServletRequest request, @RequestParam Integer id) {
        return participantsService.getParticipantById(request, id);
    }

    @GetMapping("/participants/")
    public ResponseEntity<List<ParticipantsShortDTO>> getParticipants(HttpServletRequest request, @RequestParam Integer eventId) {
        return participantsService.getAllParticipants(request, eventId);
    }

    @DeleteMapping("/participant/")
    public ResponseEntity<StringReponse> deleteParticipants(HttpServletRequest request, @RequestParam Integer id) {
        return participantsService.deleteParticipant(request, id);
    }
}
