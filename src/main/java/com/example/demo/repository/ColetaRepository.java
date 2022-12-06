package com.example.demo.repository;

import com.example.demo.models.Coleta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ColetaRepository extends MongoRepository<Coleta, String> {

}
