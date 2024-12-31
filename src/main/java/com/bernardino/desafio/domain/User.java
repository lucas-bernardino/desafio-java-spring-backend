package com.bernardino.desafio.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters and constructors
@Builder // So that we can build on the service, like User.builder().name(name).email(email).build()
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Builder.Default // With this annotation, every user will have a random UUID as default
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
    private UUID uuid = UUID.randomUUID();
    
    @Column(name = "name", nullable = false, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "email", nullable = false, columnDefinition = "varchar(255)")
    private String email;

    // Eager because every time we pull an user, we'll get all their uniqueDigits 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default // Every time user is instantiated, a new empty list is created. 
    private List<UniqueDigit> uniqueDigits = new ArrayList<>();



}
