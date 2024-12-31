package com.bernardino.desafio.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class UniqueDigitTest {
    @Test
    void itShouldReturnTheRightValue() {
        var number = 9875; var k = 4;

        var uniqueDigit1 = new UniqueDigit(BigInteger.valueOf(number), k).getResult();
        var uniqueDigit2 = new UniqueDigit(BigInteger.valueOf(number), 1).getResult();

        assertEquals(uniqueDigit1, 8);
        assertEquals(uniqueDigit2, 2);

    }
}
