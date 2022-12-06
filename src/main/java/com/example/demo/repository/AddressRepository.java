package com.example.demo.repository;

import com.example.demo.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
