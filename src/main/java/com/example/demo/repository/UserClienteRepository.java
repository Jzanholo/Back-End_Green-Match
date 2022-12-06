package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.models.UserCliente;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserClienteRepository extends MongoRepository<UserCliente, String> {
    Optional<UserCliente> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
