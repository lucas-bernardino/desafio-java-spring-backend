package com.bernardino.desafio.controller;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardino.desafio.controller.dto.CreateUserRequestDTO;
import com.bernardino.desafio.controller.dto.CreateUserResponseDTO;
import com.bernardino.desafio.controller.dto.GetCalculationsByUserIdResponseDTO;
import com.bernardino.desafio.controller.dto.GetUserByIdResponseDTO;
import com.bernardino.desafio.controller.dto.UniqueDigitDTO;
import com.bernardino.desafio.controller.dto.UpdateUserRequestDTO;
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


    @GetMapping("/{userId}")
    public ResponseEntity<GetUserByIdResponseDTO> getUserById(@PathVariable UUID userId) {
        var userDTO = userService.getUserById(userId);
        var response = new GetUserByIdResponseDTO(userDTO.uuid(), userDTO.name(), userDTO.email());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUserById(@PathVariable UUID uuid, @RequestBody @Valid UpdateUserRequestDTO updateUserRequestDTO) {
        
        userService.updateUserById(uuid, updateUserRequestDTO.name(), updateUserRequestDTO.email());
        
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/calculations")
    public ResponseEntity<GetCalculationsByUserIdResponseDTO> getCalculationsByUserId(@PathVariable UUID userId) {
        var listOfUniqueDigits = userService.getCalculationsByUserId(userId);
        
        var listAsDTO = listOfUniqueDigits.stream().map(uniqueDig -> {
            return new UniqueDigitDTO(uniqueDig.result(), uniqueDig.number(), uniqueDig.k());
        }).collect(Collectors.toList());
        
        var response = new GetCalculationsByUserIdResponseDTO(listAsDTO);

        return ResponseEntity.ok(response);
    }


}
