package com.eventix.event.infra.security.dto;

public record RegisterDTO(
        String username,
        String email,
        String password,
        String confirmPassword,
        Integer branch
) {
}
