package com.bernardino.desafio.services.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bernardino.desafio.domain.UniqueDigit;
import com.bernardino.desafio.services.CalculateUniqueDigitService;

@Service
public class CalculateUniqueDigitServiceImpl implements CalculateUniqueDigitService{

    @Override
    public int calculateUniqueDigit(BigInteger bigInteger, int k, Optional<UUID> userId) {
        
        var uniqueDigit = new UniqueDigit(bigInteger, k);

        return uniqueDigit.getResult();
    }
    
}
