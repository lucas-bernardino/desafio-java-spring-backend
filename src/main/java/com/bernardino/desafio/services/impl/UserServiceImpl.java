package com.bernardino.desafio.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bernardino.desafio.domain.User;
import com.bernardino.desafio.repository.UserRepository;
import com.bernardino.desafio.services.UserService;
import com.bernardino.desafio.services.dto.UniqueDigitDTO;
import com.bernardino.desafio.services.dto.UserDTO;
import com.bernardino.desafio.services.exceptions.EmailAlreadyExistsException;
import com.bernardino.desafio.services.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UUID createUser(String name, String email) {

        var emailExists = userRepository.findByEmail(email);

        if (emailExists.isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        var user = User.builder().name(name).email(email).build();

        this.userRepository.save(user);
        
        return user.getUuid();
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());

        return new UserDTO(user.getUuid(), user.getName(), user.getEmail());
    }

    @Override
    public void deleteUserById(UUID uuid) {
        userRepository.deleteById(uuid);
    }

    @Override
    public void updateUserById(UUID uuid, String name, String email) {
        var user = userRepository.findById(uuid).orElseThrow(() -> new UserNotFoundException());

        user.setName(name);
        user.setEmail(email);

        userRepository.save(user);
        
    }

    @Override
    public List<UniqueDigitDTO> getCalculationsByUserId(UUID uuid) {
        var user = userRepository.findById(uuid);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        var uniqueDigits = user.get().getUniqueDigits().stream().map(uniqueDig -> {
            return new UniqueDigitDTO(uniqueDig.getResult(), uniqueDig.getNumber(), uniqueDig.getK());
        }).collect(Collectors.toList());

        return uniqueDigits;
    }
    
    
    
}
