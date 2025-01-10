package com.eventix.event.infra.security.service;

import com.eventix.event.infra.security.dto.MessageReturnDTO;
import com.eventix.event.infra.security.dto.RegisterDTO;
import com.eventix.event.infra.security.dto.UserDTO;
import com.eventix.event.infra.security.model.UserModel;
import com.eventix.event.infra.security.repository.UserRepository;
import com.eventix.event.infra.security.strategy.UserValidation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final List<UserValidation> validations;

    public UserService(UserRepository userRepository, List<UserValidation> validations) {
        this.userRepository = userRepository;
        this.validations = validations;
    }



    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<MessageReturnDTO> registerUser(RegisterDTO data) {

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
        userRepository.save(user);
        return ResponseEntity.ok(
                new MessageReturnDTO("Usuário criado com sucesso"));
    }

    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userRepository.findUsers());
    }

    public ResponseEntity<Boolean> verifyUsername(String username) {
        return ResponseEntity.ok(!userRepository.existsByUsername(username));
    }
}
