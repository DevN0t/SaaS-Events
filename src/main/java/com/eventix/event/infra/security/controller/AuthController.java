package com.eventix.event.infra.security.controller;

import com.eventix.event.infra.security.dto.MessageReturnDTO;
import com.eventix.event.infra.security.dto.RegisterDTO;
import com.eventix.event.infra.security.dto.ResponseDTO;
import com.eventix.event.infra.security.dto.UserDTO;
import com.eventix.event.infra.security.service.AuthService;
import com.eventix.event.infra.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserDTO>> getAllUsers(HttpServletRequest request){
        return userService.getUsers(request);
    }

    @PostMapping("/auth")
    public ResponseEntity<ResponseDTO> auth(Authentication authentication){
        return authService.authenticate(authentication);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageReturnDTO> register(HttpServletRequest request,@RequestBody RegisterDTO data){
       return userService.registerUser(request, data);
    }

    @GetMapping("/verifyUsername")
    public ResponseEntity<Boolean> verifyUsername(HttpServletRequest request ,@RequestParam("username") String username){
        return userService.verifyUsername(request, username);
    }
}
