package com.eventix.event.modules.events.dto;

import java.time.LocalDateTime;

public record EventShortDTO(
        Integer id,
        String name,
        String description,
        LocalDateTime date
) {
}
