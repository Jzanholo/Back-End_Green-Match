package com.example.demo.repository;

import com.example.demo.models.UserCatador;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserCatadorRepository extends MongoRepository<UserCatador, String> {
    List<UserCatador> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
