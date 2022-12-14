package com.example.demo.repository;

import com.example.demo.models.ColetaAgendada;
import com.example.demo.models.ColetaSolicitada;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ColetaAgendadaRepository extends MongoRepository<ColetaAgendada, String> {
    List<ColetaAgendada> findByUsernameScavenger(String username);
}
