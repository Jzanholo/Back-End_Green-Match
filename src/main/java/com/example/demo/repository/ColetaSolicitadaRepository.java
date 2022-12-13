package com.example.demo.repository;

import com.example.demo.models.ColetaSolicitada;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ColetaSolicitadaRepository extends MongoRepository<ColetaSolicitada, String> {
    List<ColetaSolicitada> findByUsername(String username);
    Optional<ColetaSolicitada> findById(String id);
}
