package com.bernardino.desafio.services.dto;

import java.util.UUID;

public record UserDTO(
    UUID uuid,
    String name,
    String email
) {
    
}
