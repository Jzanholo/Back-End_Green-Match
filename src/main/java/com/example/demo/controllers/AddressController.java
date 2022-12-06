package com.example.demo.controllers;

import com.example.demo.models.Address;
import com.example.demo.payload.requests.AddressRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AddressController {
    Address address;
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserClienteRepository userClienteRepository;

    @PostMapping("/newAddress")
    public ResponseEntity<?> registerAddress(@Valid @RequestBody AddressRequest addressRequest) {

        address = new Address(
                AuthController.username,
                addressRequest.getStreet(),
                addressRequest.getComplement(),
                addressRequest.getDistrict(),
                addressRequest.getCEP(),
                addressRequest.getCity(),
                addressRequest.getState()
                );
        addressRepository.save(address);
        return ResponseEntity.ok(new MessageResponse("Endereço cadastrado com sucesso!"));
    }
    /*
    @GetMapping("/getAddress")
    public List<Address> getAllAddress() {
        String id = AuthController.id;
        userClienteRepository.findAllById(Collections.singleton(id));
    }*/
}
