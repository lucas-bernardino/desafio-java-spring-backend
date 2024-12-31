package com.bernardino.desafio.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bernardino.desafio.domain.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    
}
