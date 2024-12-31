package com.bernardino.desafio.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bernardino.desafio.domain.User;
import com.bernardino.desafio.repository.UserRepository;
import com.bernardino.desafio.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UUID createUser(String name, String email) {
        var user = User.builder().name(name).email(email).build();

        this.userRepository.save(user);
        
        return user.getUuid();
    }
    
}
