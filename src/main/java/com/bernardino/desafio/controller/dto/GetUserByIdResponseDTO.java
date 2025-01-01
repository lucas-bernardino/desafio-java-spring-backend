package com.bernardino.desafio.controller.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetUserByIdResponseDTO(
    @Schema(description = "User id", example = "123e4567-e89b-12d3-a456-426614174000")
    UUID uuid,

    @Schema(description = "User name", example = "Lucas Bernardino")
    String name,

    @Schema(description = "User email", example = "lucas.bernardino@gmail.com")
    String email
) {
    
}
