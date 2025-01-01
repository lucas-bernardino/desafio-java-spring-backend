package com.bernardino.desafio.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bernardino.desafio.controller.dto.CalculateUniqueDigitRequestDTO;
import com.bernardino.desafio.services.CalculateUniqueDigitService;

@TestInstance(Lifecycle.PER_CLASS)
public class CalculateUniqueDigitControllerTest {
    
    private CalculateUniqueDigitController calculateUniqueDigitController;
    
    @Mock
    private CalculateUniqueDigitService calculateUniqueDigitService;

    @BeforeAll
    void setup() {
        MockitoAnnotations.openMocks(this);
        calculateUniqueDigitController = new CalculateUniqueDigitController(calculateUniqueDigitService);
    }

    @BeforeEach
    void resetMocks() {
        reset(calculateUniqueDigitService);
    }
    
    @Test
    void itShouldReturnUniqueDigitCalculated() {
        var request = new CalculateUniqueDigitRequestDTO("9875", 4, null);

        when(calculateUniqueDigitService.calculateUniqueDigit(any(BigInteger.class), anyInt(), any())).thenReturn(8);
        
        var response = calculateUniqueDigitController.calculateUniqueDigit(request);
        var body = response.getBody();

        assertEquals(8, body);
        verify(calculateUniqueDigitService, times(1)).calculateUniqueDigit(any(BigInteger.class), anyInt(), any());
    }

}
