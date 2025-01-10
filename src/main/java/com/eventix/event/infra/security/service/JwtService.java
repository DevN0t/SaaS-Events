package com.eventix.event.infra.security.service;

import com.eventix.event.infra.security.dto.ResponseDTO;
import com.eventix.event.infra.security.model.UserModel;
import com.eventix.event.infra.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Value("${jwt.public.key}")
    private RSAPublicKey key;

    private final JwtEncoder encoder;

    private final UserRepository userRepository;

    public JwtService(JwtEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public ResponseDTO generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 36000L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("devnot")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        var user = userRepository.findByUsername(authentication.getName());

        return new ResponseDTO(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(), user.get().getId(), Date.from(now.plusSeconds(expiry)));
    }

    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(key).build();
    }


    public String getSubjectFromJwt(String jwtToken) {

        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        }

        Jwt jwt = jwtDecoder().decode(jwtToken);
        return jwt.getSubject();
    }

    public Integer getUserIdFromJwt(String jwtToken) {
        Integer userId = userRepository.findUserModelByUsername(getSubjectFromJwt(jwtToken)).getId();
        return userId;
    }

    public UserModel getUserFromJwt(String jwtToken) {
        var user = userRepository.findUserModelByUsername(getSubjectFromJwt(jwtToken));
        return user;
    }

    public Integer getUserBranchFromJwt(String jwtToken) {
        var branch = userRepository.findUserModelByUsername(getSubjectFromJwt(jwtToken)).getBranch();
        return branch;
    }
}
