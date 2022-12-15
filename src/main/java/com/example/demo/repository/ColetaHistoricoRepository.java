package com.example.demo.repository;

import com.example.demo.models.ColetaHistorico;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ColetaHistoricoRepository extends MongoRepository<ColetaHistorico,String> {
    List<ColetaHistorico> findByUsernameScavenger(String usernameScavenger);
    List<ColetaHistorico> findByUsername(String username);
}
