package com.example.demo.repository;

import com.example.demo.models.ColetaSolicitada;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ColetaRepository extends MongoRepository<ColetaSolicitada, String> {
    List<ColetaSolicitada> findByUsername(String username);
}
