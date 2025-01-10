package com.eventix.event.infra.security.service;

import com.eventix.event.infra.security.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;

    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public ResponseEntity<ResponseDTO> authenticate(Authentication authentication){
        return ResponseEntity.ok(jwtService.generateToken(authentication));
    }

}
