package com.bernardino.desafio.controller.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record GetCalculationsByUserIdResponseDTO(
    @Schema(description = "List of unique digits from a given user")
    List<UniqueDigitDTO> uniqueDigits
) {
    
}