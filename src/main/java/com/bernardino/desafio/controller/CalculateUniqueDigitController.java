package com.bernardino.desafio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardino.desafio.controller.dto.CalculateUniqueDigitRequestDTO;
import com.bernardino.desafio.services.CalculateUniqueDigitService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/calculate")
@RequiredArgsConstructor
public class CalculateUniqueDigitController {
    
    private final CalculateUniqueDigitService calculateUniqueDigitService;

    @PostMapping
    public ResponseEntity<Integer> calculateUniqueDigit(@RequestBody @Valid CalculateUniqueDigitRequestDTO calculateUniqueDigitRequestDTO) {
        
        var bigInteger = calculateUniqueDigitRequestDTO.getBigIntegerNumber();
        var k = calculateUniqueDigitRequestDTO.getK();
        var userId = calculateUniqueDigitRequestDTO.userId();

        var response = calculateUniqueDigitService.calculateUniqueDigit(bigInteger, k, userId);

        return ResponseEntity.ok(response);
    }
}
