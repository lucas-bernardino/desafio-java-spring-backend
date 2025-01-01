package com.bernardino.desafio.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UniqueDigitDTO(
    @Schema(description = "The calculated unique digit")
    int result,

    @Schema(description = "The number to calculate the unique digit from")
    String number,

    @Schema(description = "How many times it should repeat the string")
    int k
) {
    
}
