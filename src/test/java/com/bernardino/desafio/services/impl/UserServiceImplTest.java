package com.bernardino.desafio.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bernardino.desafio.domain.User;
import com.bernardino.desafio.repository.UserRepository;
import com.bernardino.desafio.services.exceptions.EmailAlreadyExistsException;
import com.bernardino.desafio.services.exceptions.UserNotFoundException;

@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        userServiceImpl = new UserServiceImpl(this.userRepository);
    }

    @Test
    void itShouldCreateNewUserAndSaveOnRepository() {

        var name = "Lucas Bernardino"; var email = "lucas.bernardino@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        userServiceImpl.createUser(name,email);

        var captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());

        var user = captor.getValue();

        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());

    }

    @Test
    void itShouldThrowErrorIfEmailAlreadyExists() {
        var name = "Lucas Bernardino"; var email = "lucas.bernardino@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(User.builder().build()));

        assertThrows(EmailAlreadyExistsException.class, () -> {userServiceImpl.createUser(name, email);});

        verify(userRepository, times(0)).save(any());
    }


    @Test
    void itShouldReturnUserDTOIfUserExists() {
        var userId = UUID.randomUUID();
        var user = User.builder()
            .uuid(userId)
            .name("Lucas Bernardino")
            .email("lucas.bernardino@gmail.com")
            .build();

        when(this.userRepository.findById(userId)).thenReturn(Optional.of(user));

        var userDto = userServiceImpl.getUserById(userId);

        assertEquals(user.getUuid(), userDto.uuid());
        assertEquals(user.getName(), userDto.name());
        assertEquals(user.getEmail(), userDto.email());
    
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void itShouldThrowExceptionIfUserDoesntExist() {
        var userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,() -> userServiceImpl.getUserById(userId));

        verify(userRepository, times(1)).findById(userId);
    }
}
