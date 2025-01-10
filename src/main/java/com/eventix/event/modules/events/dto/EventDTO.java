package com.eventix.event.modules.events.dto;

import java.time.LocalDateTime;

public record EventDTO(
         Integer id,

         Integer branchId,

         String name,

         String description,

         String code,

         String value,

         String location,

         String contact,

         LocalDateTime date
) {
}
