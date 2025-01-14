package com.eventix.event.modules.participants.service;

import com.eventix.event.infra.security.service.JwtService;
import com.eventix.event.modules.events.dto.StringReponse;
import com.eventix.event.modules.participants.dto.ParticipantsCreateDTO;
import com.eventix.event.modules.participants.dto.ParticipantsDTO;
import com.eventix.event.modules.participants.dto.ParticipantsShortDTO;
import com.eventix.event.modules.participants.dto.ParticipantsUpdateDTO;
import com.eventix.event.modules.participants.infra.model.Participants;
import com.eventix.event.modules.participants.infra.repository.ParticipantsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantsService {

    @Autowired
    private ParticipantsRepository participantsRepository;

    @Autowired
    private JwtService jwtService;

    ObjectMapper objectMapper = new ObjectMapper();



    public ResponseEntity<StringReponse> createParticipant(HttpServletRequest request, ParticipantsCreateDTO data) {
        try{
            var token = request.getHeader("Authorization");
            var branchId = jwtService.getUserBranchFromJwt(token);

            var participants = new Participants();

            participants.setName(data.name());
            participants.setBirth(data.birth());
            participants.setPhone(data.phone());
            participants.setEmergencyPhone(data.emergencyPhone());
            participants.setReference(data.reference());
            participants.setCreatedAt(java.time.LocalDateTime.now());
            participants.setStatus(true);
            participants.setEventId(data.eventId());
            participants.setBranchId(branchId);

            participantsRepository.save(participants);

            return ResponseEntity.ok(new StringReponse("Participante criado com sucesso!"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new StringReponse("Erro ao cadastrar Participante"));
        }
    }

    public ResponseEntity<StringReponse> updateParticipant(HttpServletRequest request, ParticipantsUpdateDTO data, Integer id) {
        try{
            var token = request.getHeader("Authorization");
            var branchId = jwtService.getUserBranchFromJwt(token);

            var participants = participantsRepository.findByIdAndBranchId(id, branchId);

            participants.setName(data.name());
            participants.setBirth(data.birth());
            participants.setPhone(data.phone());
            participants.setEmergencyPhone(data.emergencyPhone());
            participants.setReference(data.reference());
            participants.setUpdatedAt(java.time.LocalDateTime.now());
            participants.setStatus(true);
            participants.setBranchId(branchId);

            participantsRepository.save(participants);

            return ResponseEntity.ok(new StringReponse("Participante criado com sucesso!"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new StringReponse("Erro ao cadastrar Participante"));
        }
    }

    public ResponseEntity<List<ParticipantsShortDTO>> getAllParticipants(HttpServletRequest request, Integer eventId) {
        return ResponseEntity.ok(participantsRepository.findAllByEventId(eventId));
    }

    public ResponseEntity<ParticipantsDTO> getParticipantById(HttpServletRequest request, Integer id) {
        var token = request.getHeader("Authorization");
        var branchId = jwtService.getUserBranchFromJwt(token);

        return ResponseEntity.ok(participantsRepository.findByBranchIdAndId(branchId, id));
    }

    public ResponseEntity<StringReponse> deleteParticipant(HttpServletRequest request, Integer id) {
        try{
            var token = request.getHeader("Authorization");
            var branchId = jwtService.getUserBranchFromJwt(token);

            var participants = participantsRepository.findByIdAndBranchId(id, branchId);
            participants.setStatus(false);
            participantsRepository.save(participants);

            return ResponseEntity.ok(new StringReponse("Participante deletado com sucesso!"));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new StringReponse("Erro ao deletar Participante"));
        }
    }

}
