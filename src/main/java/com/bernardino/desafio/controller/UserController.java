package com.bernardino.desafio.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardino.desafio.controller.dto.CreateUserRequestDTO;
import com.bernardino.desafio.controller.dto.CreateUserResponseDTO;
import com.bernardino.desafio.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(
        @RequestBody @Valid CreateUserRequestDTO createUserRequestDTO) {
            var createdUserId = this.userService.createUser(createUserRequestDTO.name(), createUserRequestDTO.email());
            var response = new CreateUserResponseDTO(createdUserId);
            return ResponseEntity.ok(response);
    }


}
