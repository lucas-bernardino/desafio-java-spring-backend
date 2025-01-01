package com.bernardino.desafio.controller.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class CalculateUniqueDigitRequestDTOTest {
    
    @Test
    void itShouldReturnBigInteger() {
        var request = new CalculateUniqueDigitRequestDTO("9875", 4, Optional.empty());

        var result = request.getBigIntegerNumber();
        var integer = request.getK();

        assertTrue(result.equals(new BigInteger("9875")));
        assertEquals(integer, 4);
    }

    @Test
    void itShouldReturnErrorIfNumberIsNotParsebleToBigInteger() {
        var request = new CalculateUniqueDigitRequestDTO("xxx", 4, Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
        request.getBigIntegerNumber();
        });
    }

    @Test
    void itShouldReturnBigIntegerDoesntRespectTheRange() {
        var bigInteger = BigInteger.valueOf(-1);
        var request = new CalculateUniqueDigitRequestDTO(bigInteger.toString(), 4, Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
        request.getBigIntegerNumber();
        });
    }
}
