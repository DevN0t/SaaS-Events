package com.eventix.event.modules.events.dto;

import java.time.LocalDateTime;

public record EventCreateDTO(
        String name,
        String description,
        String value,
        String location,
        String contact,
        LocalDateTime date
) {
}
