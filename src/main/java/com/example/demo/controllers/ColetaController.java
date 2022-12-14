package com.example.demo.controllers;

import com.example.demo.models.ColetaAgendada;
import com.example.demo.models.ColetaSolicitada;
import com.example.demo.payload.requests.ColetaRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.ColetaSolicitadaRepository;
import com.example.demo.repository.ColetaAgendadaRepository;
import com.example.demo.repository.UserCatadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class ColetaController {
    @Autowired
    UserCatadorRepository userCatadorRepository;
    @Autowired
    ColetaSolicitadaRepository coletaSolicitadaRepository;
    @Autowired
    ColetaAgendadaRepository coletaAgendadaRepository;

    @PostMapping("/newCollect")
    public ResponseEntity<?> NovaColeta(@RequestBody ColetaRequest coletaRequest){

            ColetaSolicitada newColeta = new ColetaSolicitada(
                    coletaRequest.getName_collect(),
                    AuthController.username,
                    coletaRequest.getUsernameScavenger(),
                    coletaRequest.getAddress(),
                    coletaRequest.getObs(),
                    coletaRequest.getWeight(),
                    coletaRequest.getMaterials(),
                    coletaRequest.getDayWeek(),
                    coletaRequest.getDayPeriod()
            );
        System.out.println(AuthController.username);
        coletaSolicitadaRepository.save(newColeta);
            return ResponseEntity.ok(new MessageResponse("Pedido de coleta enviado com sucesso"));
    }

    @GetMapping("/collectByUser")
    public List<ColetaSolicitada> coletaPorCliente(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findByUsername(AuthController.username);
        return info;
    }

    @GetMapping("/AllCollect")
    public List<ColetaSolicitada> obterTodasColetas(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findAll();
        return info;
    }

    @GetMapping("/AllCollectScheduled")
    public List<ColetaAgendada> obterTodasColetasAgendadas(){
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsernameScavenger(AuthController.username);
        return info;
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deletarColeta(@RequestBody String id) {
        coletaSolicitadaRepository.deleteById(id);
        System.out.println(id);
        return ResponseEntity.ok("deletado");
    }

    @PostMapping("/acceptRequest")
    public ResponseEntity<?> aceitarColeta(@RequestBody String id){
        Optional<ColetaSolicitada> coleta = coletaSolicitadaRepository.findById(id);

        ColetaAgendada newColeta = new ColetaAgendada(
                coleta.get().getId(),
                coleta.get().getName_collect(),
                coleta.get().getUsername(),
                AuthController.username,
                coleta.get().getAddress(),
                coleta.get().getObs(),
                coleta.get().getWeight(),
                coleta.get().getMaterials(),
                coleta.get().getDayWeek(),
                coleta.get().getDayPeriod()
        );

        coletaAgendadaRepository.save(newColeta);
        coletaSolicitadaRepository.deleteById(id);

        return ResponseEntity.ok("Coleta aceita com sucesso");
    }
}
