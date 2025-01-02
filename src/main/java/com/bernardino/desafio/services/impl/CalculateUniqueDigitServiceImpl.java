package com.bernardino.desafio.services.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bernardino.desafio.domain.UniqueDigit;
import com.bernardino.desafio.repository.UserRepository;
import com.bernardino.desafio.services.CacheService;
import com.bernardino.desafio.services.CalculateUniqueDigitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateUniqueDigitServiceImpl implements CalculateUniqueDigitService{

    private final UserRepository userRepository;
    private final CacheService<UniqueDigit> cacheService;

    @Override
    public int calculateUniqueDigit(BigInteger bigInteger, int k, Optional<UUID> userId) {
        
        var cacheKey = this.cacheKey(bigInteger, k);
        var uniqueDigit = cacheService.get(cacheKey).orElseGet(() -> createNewUniqueDigit(bigInteger, k));

        if (userId.isPresent()) {
            userRepository.findById(userId.get()).ifPresent(user -> {
                user.addUniqueDigit(uniqueDigit);
                userRepository.save(user);
            });
        }

        return uniqueDigit.getResult();
    }

    private String cacheKey(BigInteger number, int k) {
        return new StringBuilder()
            .append("uniquedigit::")
            .append("number::")
            .append(String.valueOf(number))
            .append("::k::")
            .append(String.valueOf(k))
            .toString();
    }

    private UniqueDigit createNewUniqueDigit(BigInteger bigInteger, int k) {
        var uniqueDigit = new UniqueDigit(bigInteger, k);

        var cacheKey = cacheKey(bigInteger, k);

        cacheService.put(cacheKey, uniqueDigit);

        return uniqueDigit;
    }
    
}
