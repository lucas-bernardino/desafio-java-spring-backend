package com.bernardino.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bernardino.desafio.controller.dto.CreateUserRequestDTO;
import com.bernardino.desafio.controller.dto.UpdateUserRequestDTO;
import com.bernardino.desafio.services.UserService;
import com.bernardino.desafio.services.dto.UniqueDigitDTO;
import com.bernardino.desafio.services.dto.UserDTO;

@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {
    
    @Mock
    private UserService userService;

    private UserController userController;
    
    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.userController = new UserController(userService);
    }
    
    @BeforeEach
    void resetMocks() {
        reset(this.userService);
    }
    
    
    @Test
    void itShouldCreateUser() {

        var request = new CreateUserRequestDTO("Lucas Bernardino", "lucas.bernardino@gmail.com");
        var uuidGenerated = UUID.randomUUID();
        when(this.userService.createUser(request.name(), request.email())).thenReturn(uuidGenerated);


        var response = userController.createUser(request);
        var body = response.getBody();

        assertEquals(body.id(), uuidGenerated);
        verify(userService, times(1)).createUser(request.name(), request.email());
    }

    @Test
    void itShouldGetUserById() {
        var uuid = UUID.randomUUID();
        var userDTO = new UserDTO(uuid, "Lucas Bernardino", "lucas.bernardino@gmail.com");

        when(userService.getUserById(uuid)).thenReturn(userDTO);

        var response = userController.getUserById(uuid);
        var body = response.getBody();

        assertEquals(body.uuid(), userDTO.uuid());
        assertEquals(body.name(), userDTO.name());
        assertEquals(body.email(), userDTO.email());
    }

    @Test
    void itShouldDeleteUserById() {
        var uuid = UUID.randomUUID();

        userController.deleteUserById(uuid);

        verify(userService, times(1)).deleteUserById(uuid);
    }

    @Test
    void itShouldUpdateUserById() {
        var request = new UpdateUserRequestDTO("Lucas Bernardino", "lucas.bernardino@gmail.com");
        var uuidGenerated = UUID.randomUUID();
        
        userController.updateUserById(uuidGenerated, request);
        
        verify(userService, times(1)).updateUserById(uuidGenerated, request.name(), request.email());
    }

    @Test
    void itShouldReturnAnListOfUniqueDigitsDTO() {
        
        var uuid = UUID.randomUUID();
        var result = List.of(
            new UniqueDigitDTO(1, "2", 3),
            new UniqueDigitDTO(2, "3", 4)
        );

        when(userService.getCalculationsByUserId(uuid)).thenReturn(result);

        var response = userController.getCalculationsByUserId(uuid);
        var body = response.getBody();

        verify(userService, times(1)).getCalculationsByUserId(uuid);

        for (int i = 0; i < body.uniqueDigits().size(); i++) {
            assertEquals(result.get(i).result(), body.uniqueDigits().get(i).result());
            assertEquals(result.get(i).number(), body.uniqueDigits().get(i).number());
            assertEquals(result.get(i).k(), body.uniqueDigits().get(i).k());
        }

    }
}
