package com.eventix.event.infra.security.strategy;

import com.eventix.event.infra.security.dto.RegisterDTO;
import org.springframework.stereotype.Component;

@Component
public interface UserValidation {

    Boolean isValid(RegisterDTO user);
}
