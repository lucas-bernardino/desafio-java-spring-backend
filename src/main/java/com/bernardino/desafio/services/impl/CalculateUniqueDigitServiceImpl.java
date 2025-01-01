package com.bernardino.desafio.services.impl;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bernardino.desafio.domain.UniqueDigit;
import com.bernardino.desafio.repository.UserRepository;
import com.bernardino.desafio.services.CalculateUniqueDigitService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculateUniqueDigitServiceImpl implements CalculateUniqueDigitService{

    private final UserRepository userRepository;

    @Override
    public int calculateUniqueDigit(BigInteger bigInteger, int k, Optional<UUID> userId) {
        var uniqueDigit = new UniqueDigit(bigInteger, k);

        if (userId.isPresent()) {
            userRepository.findById(userId.get()).ifPresent(user -> {
                user.addUniqueDigit(uniqueDigit);
                userRepository.save(user);
            });
        }

        return uniqueDigit.getResult();
    }
    
}
