package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.UserCliente;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserClienteRepository extends MongoRepository<UserCliente, String> {
    //Optional<UserCliente> findByUsername(String username);

    List<UserCliente> findByUsername(String id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);


}
