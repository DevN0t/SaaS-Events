package com.eventix.event.modules.participants.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParticipantsCreateDTO(

        Integer eventId,

        String name,

        LocalDate birth,

        String phone,

        String emergencyPhone,

        String reference
) {
}
