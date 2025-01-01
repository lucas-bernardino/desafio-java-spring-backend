package com.bernardino.desafio.services;

import java.util.UUID;

import com.bernardino.desafio.services.dto.UserDTO;

public interface UserService {
    UUID createUser(String name, String email);
    UserDTO getUserById(UUID userId);
    void deleteUserById(UUID uuid);
    void updateUserById(UUID uuid, String name, String email);
}
