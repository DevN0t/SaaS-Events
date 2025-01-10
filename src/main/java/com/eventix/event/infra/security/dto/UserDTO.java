package com.eventix.event.infra.security.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public record UserDTO(
    @Id
    Integer id,
    String username
) {
}
