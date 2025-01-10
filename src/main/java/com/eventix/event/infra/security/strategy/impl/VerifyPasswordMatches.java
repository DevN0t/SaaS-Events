package com.eventix.event.infra.security.strategy.impl;

import com.eventix.event.infra.security.dto.RegisterDTO;
import com.eventix.event.infra.security.strategy.UserValidation;
import org.springframework.stereotype.Component;

@Component
public class VerifyPasswordMatches implements UserValidation {
    @Override
    public Boolean isValid(RegisterDTO user) {
        if(!user.password().equals(user.confirmPassword())){
            return false;
        }
        return true;
    }
}
