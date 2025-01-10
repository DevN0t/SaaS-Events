package com.eventix.event.modules.events.service;

import com.eventix.event.infra.security.service.JwtService;
import com.eventix.event.modules.events.dto.EventCreateDTO;
import com.eventix.event.modules.events.dto.EventDTO;
import com.eventix.event.modules.events.dto.EventShortDTO;
import com.eventix.event.modules.events.dto.StringReponse;
import com.eventix.event.modules.events.infra.model.Event;
import com.eventix.event.modules.events.infra.repository.EventRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JwtService jwtService;


    public ResponseEntity<StringReponse> createEvent(HttpServletRequest request, EventCreateDTO eventDTO) {
        try{
            var token = request.getHeader("Authorization");
            var branchId = jwtService.getUserBranchFromJwt(token);

            var event = new Event();
            event.setName(eventDTO.name());
            event.setDescription(eventDTO.description());
            event.setCode(UUID.randomUUID().toString());
            event.setContact(eventDTO.contact());
            event.setLocation(eventDTO.location());
            event.setDate(eventDTO.date());
            event.setBranchId(branchId);
            event.setValue(eventDTO.value());
            eventRepository.save(event);

            return ResponseEntity.ok(new StringReponse("Evento criado com sucesso!"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new StringReponse("Erro ao cadastrar evento"));
        }
    }

    public ResponseEntity<StringReponse> updateEvent(HttpServletRequest request, EventCreateDTO eventDTO, Integer id) {
        try{
            var token = request.getHeader("Authorization");
            var branchId = jwtService.getUserBranchFromJwt(token);

            var event = eventRepository.findByIdAndBranchId(id, branchId);
            event.setName(eventDTO.name());
            event.setDescription(eventDTO.description());
            event.setCode(UUID.randomUUID().toString());
            event.setContact(eventDTO.contact());
            event.setLocation(eventDTO.location());
            event.setDate(eventDTO.date());
            event.setBranchId(branchId);
            event.setValue(eventDTO.value());
            eventRepository.save(event);

            return ResponseEntity.ok(new StringReponse("Evento criado com sucesso!"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new StringReponse("Erro ao cadastrar evento"));
        }
    }

    public ResponseEntity<List<EventShortDTO>> getAllEvents(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        var branchId = jwtService.getUserBranchFromJwt(token);
        return ResponseEntity.ok(eventRepository.findAllByBranchId(branchId));
    }

    public ResponseEntity<List<EventDTO>> getEventById(HttpServletRequest request, Integer id) {
        var token = request.getHeader("Authorization");
        var branchId = jwtService.getUserBranchFromJwt(token);
        return ResponseEntity.ok(eventRepository.findByBranchIdAndId(branchId, id));
    }
}
