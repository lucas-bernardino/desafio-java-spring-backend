package com.bernardino.desafio.services;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

public interface CalculateUniqueDigitService {
    
    int calculateUniqueDigit(BigInteger bigInteger, int k, Optional<UUID> userId);

}
