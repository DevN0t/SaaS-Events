package com.eventix.event.infra.security.dto;

import java.util.Date;

public record ResponseDTO(
        String token,
        Integer userId,
        Date expires
) {
}
