package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.ColetaAgendada;
import com.example.demo.models.ColetaHistorico;
import com.example.demo.models.ColetaSolicitada;
import com.example.demo.payload.requests.ColetaRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.*;
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
    @Autowired
    ColetaHistoricoRepository coletaHistoricoRepository;
    @Autowired
    AddressRepository addressRepository;
    @PostMapping("/newCollect")
    public ResponseEntity<?> NovaColeta(@RequestBody ColetaRequest coletaRequest){

            ColetaSolicitada newColeta = new ColetaSolicitada(
                    coletaRequest.getName_collect(),
                    AuthController.username,
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

    @GetMapping("/collectRequestedByUser")
    public List<ColetaSolicitada> coletaSolicitadaPorCliente(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findByUsername(AuthController.username);
        return info;
    }

    @GetMapping("/collectSchaduledByUser")
    public List<ColetaAgendada> coletaAgendadaPorCliente(){
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsername(AuthController.username);
        return info;
    }

    @GetMapping("/AllCollect")
    public List<ColetaSolicitada> obterTodasColetas(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findAll();
        return info;
    }
    @GetMapping("/AllCollectSchaduledScavenger")
    public List<ColetaAgendada> obterTodasColetasAgendadas(){
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsernameScavenger(AuthController.username);
        return info;
    }

    /*@GetMapping("/findAdressByCollect")
    public List<ColetaAgendada> procuraEndereço(){
        List<Address> info_address = addressRepository.findByName
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsernameScavenger(AuthController.username);

        return info;
    }*/
    @GetMapping("/AllCollectHistoricScavenger")
    public List<ColetaHistorico> obterHistoricoColetasCatador(){
        List<ColetaHistorico> info = coletaHistoricoRepository.findByUsernameScavenger(AuthController.username);
        return info;
    }

    @GetMapping("/AllCollectHistoricCustumer")
    public List<ColetaHistorico> obterHistoricoColetasCliente(){
        List<ColetaHistorico> info = coletaHistoricoRepository.findByUsername(AuthController.username);
        return info;
    }

    @PostMapping("/deleteById")
    public ResponseEntity<?> deletarColeta(@RequestBody String id) {
        coletaSolicitadaRepository.deleteById(id);
        return ResponseEntity.ok("Coleta deletado");
    }

    @PostMapping("/deleteAddressById")
    public ResponseEntity<?> deletarEndereço(@RequestBody String id) {
        addressRepository.deleteById(id);
        System.out.println(id);
        return ResponseEntity.ok("Endereço deletado");
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

    @PostMapping("/completeSchaduled")
    public ResponseEntity<?> finalizarColeta(@RequestBody String id){
        Optional<ColetaAgendada> coleta = coletaAgendadaRepository.findById(id);

        ColetaHistorico newColeta = new ColetaHistorico(
                coleta.get().getId(),
                coleta.get().getName_collect(),
                coleta.get().getUsername(),
                coleta.get().getUsernameScavenger(),
                coleta.get().getAddress(),
                coleta.get().getObs(),
                coleta.get().getWeight(),
                coleta.get().getMaterials(),
                coleta.get().getDayWeek(),
                coleta.get().getDayPeriod()
        );

        coletaHistoricoRepository.save(newColeta);
        coletaAgendadaRepository.deleteById(id);

        return ResponseEntity.ok("Coleta finalizada com sucesso");
    }

    @PostMapping("/cancelSchaduled")
    public ResponseEntity<?> cencelarColetaAgendada(@RequestBody String id){
        Optional<ColetaAgendada> coleta = coletaAgendadaRepository.findById(id);

        ColetaSolicitada newColeta = new ColetaSolicitada(
                coleta.get().getName_collect(),
                coleta.get().getUsername(),
                coleta.get().getAddress(),
                coleta.get().getObs(),
                coleta.get().getWeight(),
                coleta.get().getMaterials(),
                coleta.get().getDayWeek(),
                coleta.get().getDayPeriod()
        );

        coletaSolicitadaRepository.save(newColeta);
        coletaAgendadaRepository.deleteById(id);

        return ResponseEntity.ok("Coleta finalizada com sucesso");
    }


}
