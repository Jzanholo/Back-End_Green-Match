package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.models.ColetaSolicitada;
import com.example.demo.payload.requests.AddressRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    //CADASTRANDO NOVO ENDEREÇO
    @PostMapping("/newAddress")
    public ResponseEntity<?> registerAddress(@Valid @RequestBody AddressRequest addressRequest) {
        Address address = new Address(
                addressRequest.getAddressName(),
                AuthController.username,
                addressRequest.getStreet(),
                addressRequest.getNumber(),
                addressRequest.getComplement(),
                addressRequest.getDistrict(),
                addressRequest.getCep(),
                addressRequest.getCity(),
                addressRequest.getState()
                );
        addressRepository.save(address);
        return ResponseEntity.ok(new MessageResponse("Endereço cadastrado com sucesso!"));
    }
    //BUSCAR TODOS OS ENDEREÇOS POR CLIENTE
    @GetMapping("/AddressByUsername")
    public List<Address> obterEndereçoPorCliente(){
        List<Address> info = addressRepository.findByUsername(AuthController.username);
        return info;
    }

}
