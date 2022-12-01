package com.example.demo.repository;

import com.example.demo.models.UserCatador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCatadorRepository extends MongoRepository<UserCatador, String> {
    Optional<UserCatador> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
