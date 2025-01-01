package com.bernardino.desafio.controller.dto;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CalculateUniqueDigitRequestDTO(

    @Schema(description = "Number to calculate the unique digit", example = "9875")
    @NotBlank
    String number,
    
    @Schema(description = "Number of times the unique digit should be calculated", example = "4", nullable = true)
    Integer k,
    
    @Schema(description = "User id", example = "123e4567-e89b-12d3-a456-426614174000")
    Optional<UUID> userId
    
) {
    public BigInteger getBigIntegerNumber() {
        BigInteger bigInteger;

        try {
            bigInteger = new BigInteger(number());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Number must be a valid big integer");
        }

        if (bigInteger.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Number must be positive");
        }

        if (bigInteger.compareTo(BigInteger.TEN.pow(1000000)) > 0) {
            throw new IllegalArgumentException("Number must be less than 1 million");
        }
        return bigInteger;
    }

    public int getK() {
        if (k() == null) {
            return 1;
        }

        if (k() < 1) {
            throw new IllegalArgumentException("K must be positive");
        }
        
        if (k() > 100000) {
            throw new IllegalArgumentException("K must be less than 100000");
        }

        return k();
    }
}
