package com.example.demo.controllers;

import com.example.demo.models.Coleta;
import com.example.demo.models.UserCliente;
import com.example.demo.payload.requests.ColetaRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.ColetaRepository;
import com.example.demo.repository.UserCatadorRepository;
import com.example.demo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ColetaController {
    @Autowired
    UserCatadorRepository userCatadorRepository;
    @Autowired
    ColetaRepository coletaRepository;


    @PostMapping("/coleta")
    public ResponseEntity<?> NovaColeta(@RequestBody ColetaRequest coletaRequest){

        if(userCatadorRepository.existsByUsername(coletaRequest.getUsername_scavenger())) {
            Coleta newColeta = new Coleta(
                    AuthController.username,
                    coletaRequest.getUsername_scavenger(),
                    coletaRequest.getAdress(),
                    coletaRequest.getObs(),
                    coletaRequest.getWeight(),
                    coletaRequest.getMaterials(),
                    coletaRequest.getDayWeek(),
                    coletaRequest.getDayPeriod()
            );
            coletaRepository.save(newColeta);
            return ResponseEntity.ok(new MessageResponse("Pedido de coleta enviado com sucesso"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Pedido de coleta invalido"));
    }


}
