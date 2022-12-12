package com.example.demo.repository;

import com.example.demo.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {
    List<Address> findByUsername(String username);
}
