package com.eventix.event.infra.security.service;

import com.eventix.event.infra.security.dto.MessageReturnDTO;
import com.eventix.event.infra.security.dto.RegisterDTO;
import com.eventix.event.infra.security.dto.UserDTO;
import com.eventix.event.infra.security.model.UserModel;
import com.eventix.event.infra.security.repository.UserRepository;
import com.eventix.event.infra.security.strategy.UserValidation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    @Autowired
    private JwtService jwtService;

    private final UserRepository userRepository;

    private final List<UserValidation> validations;

    public UserService(UserRepository userRepository, List<UserValidation> validations) {
        this.userRepository = userRepository;
        this.validations = validations;
    }



    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<MessageReturnDTO> registerUser(HttpServletRequest request, RegisterDTO data) {
        var token = request.getHeader("Authorization");

        if (!jwtService.adminOnly(token)) return ResponseEntity.status(403).body(
                new MessageReturnDTO("Você não tem permissão para criar um usuário"));

        Boolean isValid = true;
        for(UserValidation validation : validations) {
            if (!validation.isValid(data)) {
                isValid = validation.isValid(data);
                if (!isValid) {
                    break;
                }

            }
        }
        if (!isValid){
            return ResponseEntity.badRequest().body(
                    new MessageReturnDTO("Este email ou username já está sendo utilizado por alguém"));
        }
        UserModel user = new UserModel();
        user.setEmail(data.email());
        user.setPassword(bCryptPasswordEncoder.encode(data.password()));
        user.setUsername(data.username());
        user.setRole("USER");
        user.setBranch(data.branch());
        userRepository.save(user);
        return ResponseEntity.ok(
                new MessageReturnDTO("Usuário criado com sucesso"));
    }

    public ResponseEntity<List<UserDTO>> getUsers(HttpServletRequest request) {

        var token = request.getHeader("Authorization");

        if (!jwtService.adminOnly(token)) return ResponseEntity.status(403).build();

        return ResponseEntity.ok(userRepository.findUsers());
    }

    public ResponseEntity<Boolean> verifyUsername(HttpServletRequest request, String username) {

        var token = request.getHeader("Authorization");

        if (!jwtService.adminOnly(token)) return ResponseEntity.status(403).build();

        return ResponseEntity.ok(!userRepository.existsByUsername(username));
    }
}
