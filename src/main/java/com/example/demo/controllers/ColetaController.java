package com.example.demo.controllers;

import com.example.demo.models.ColetaSolicitada;
import com.example.demo.payload.requests.ColetaRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.ColetaRepository;
import com.example.demo.repository.UserCatadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ColetaController {
    @Autowired
    UserCatadorRepository userCatadorRepository;
    @Autowired
    ColetaRepository coletaRepository;


    @PostMapping("/newCollect")
    public ResponseEntity<?> NovaColeta(@RequestBody ColetaRequest coletaRequest){

            ColetaSolicitada newColeta = new ColetaSolicitada(
                    coletaRequest.getName_collect(),
                    AuthController.username,
                    coletaRequest.getUsername_scavenger(),
                    coletaRequest.getAdress(),
                    coletaRequest.getObs(),
                    coletaRequest.getWeight(),
                    coletaRequest.getMaterials(),
                    coletaRequest.getDayWeek(),
                    coletaRequest.getDayPeriod()
            );
        System.out.println(AuthController.username);
            coletaRepository.save(newColeta);
            return ResponseEntity.ok(new MessageResponse("Pedido de coleta enviado com sucesso"));
    }

    @GetMapping("/collectByUser")
    public List<ColetaSolicitada> coletaPorCliente(){
        List<ColetaSolicitada> info = coletaRepository.findByUsername(AuthController.username);
        return info;
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deletarColeta(@RequestBody ColetaRequest coletaRequest) {
        coletaRepository.deleteById(coletaRequest.getId());
        return ResponseEntity.ok("deletado");
    }
}
