package com.example.demo.repository;

import com.example.demo.models.ColetaAgendada;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColetaAgendadaRepository extends MongoRepository<ColetaAgendada, String> {

}
