package com.eventix.event.modules.participants.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParticipantsShortDTO(
        Integer id,

        String name,

        LocalDate birth,

        String reference
) {
}
