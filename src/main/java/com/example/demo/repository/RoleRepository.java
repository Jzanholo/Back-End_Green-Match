package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.models.ERole;
import com.example.demo.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}