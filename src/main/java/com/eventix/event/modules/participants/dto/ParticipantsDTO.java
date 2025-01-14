package com.eventix.event.modules.participants.dto;

import java.time.LocalDate;

public record ParticipantsDTO(
         Integer id,

         Integer eventId,

         String name,

         LocalDate birth,

         String phone,

         String emergencyPhone,

         String reference
) {
}
