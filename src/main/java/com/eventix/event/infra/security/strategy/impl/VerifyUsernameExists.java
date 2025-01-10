package com.eventix.event.infra.security.strategy.impl;

import com.eventix.event.infra.security.dto.RegisterDTO;
import com.eventix.event.infra.security.repository.UserRepository;
import com.eventix.event.infra.security.strategy.UserValidation;
import org.springframework.stereotype.Component;

@Component
public class VerifyUsernameExists implements UserValidation {

    private final UserRepository userRepository;

    public VerifyUsernameExists(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean isValid(RegisterDTO user) {
        if (userRepository.existsByUsername(user.username())){
            return false;
        }
        return true;
    }
}
