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

    //CADASTRADO DE NOVA COLETA
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

    //PEGAR COLETAS SOLICITADAS POR CLIENTE
    @GetMapping("/collectRequestedByUser")
    public List<ColetaSolicitada> coletaSolicitadaPorCliente(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findByUsername(AuthController.username);
        return info;
    }

    //PEGAR COLETAS AGENDADAS POR CLIENTE
    @GetMapping("/collectSchaduledByUser")
    public List<ColetaAgendada> coletaAgendadaPorCliente(){
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsername(AuthController.username);
        return info;
    }

    //PEGAR TODAS AS COLETAS SOLICITADAS
    @GetMapping("/AllCollect")
    public List<ColetaSolicitada> obterTodasColetas(){
        List<ColetaSolicitada> info = coletaSolicitadaRepository.findAll();
        return info;
    }

    //PEGAR TODAS AS COLETAS AGENDADAS POR CATADOR
    @GetMapping("/AllCollectSchaduledScavenger")
    public List<ColetaAgendada> obterTodasColetasAgendadas(){
        List<ColetaAgendada> info = coletaAgendadaRepository.findByUsernameScavenger(AuthController.username);
        return info;
    }

    //PEGAR TODAS AS COLETAS FINALIZADAS POR CATADOR
    @GetMapping("/AllCollectHistoricScavenger")
    public List<ColetaHistorico> obterHistoricoColetasCatador(){
        List<ColetaHistorico> info = coletaHistoricoRepository.findByUsernameScavenger(AuthController.username);
        return info;
    }

    //PEGAR TODAS AS COLETAS FINALIZADAS POR CLIENTE
    @GetMapping("/AllCollectHistoricCustumer")
    public List<ColetaHistorico> obterHistoricoColetasCliente(){
        List<ColetaHistorico> info = coletaHistoricoRepository.findByUsername(AuthController.username);
        return info;
    }

    //CANCELA COLETA SOLICITADA PELO CLIENTE (DELETA)
    @PostMapping("/deleteById")
    public ResponseEntity<?> deletarColeta(@RequestBody String id) {
        coletaSolicitadaRepository.deleteById(id);
        return ResponseEntity.ok("Coleta deletado");
    }

    //DELETA O RESPECTIVO ENDEREÇO DO USUARIO
    @PostMapping("/deleteAddressById")
    public ResponseEntity<?> deletarEndereço(@RequestBody String id) {
        addressRepository.deleteById(id);
        System.out.println(id);
        return ResponseEntity.ok("Endereço deletado");
    }

    //ACEITA SOLICITAÇÃO DE COLETA DO CLIENTE
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

    //COLETA FINALIZADA
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

    //AGENDAMENTO CANCELADO
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
