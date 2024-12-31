package com.bernardino.desafio.services;

import java.util.UUID;

public interface UserService {
    UUID createUser(String name, String email);
}
