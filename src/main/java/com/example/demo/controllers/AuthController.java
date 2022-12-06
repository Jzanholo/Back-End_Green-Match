package com.example.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.models.*;
import com.example.demo.payload.requests.ColetaRequest;
import com.example.demo.payload.requests.LoginRequest;
import com.example.demo.payload.requests.SignupCatadorRequest;
import com.example.demo.payload.requests.SignupClienteRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.payload.response.UserInfoResponse;
import com.example.demo.repository.ColetaRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserCatadorRepository;
import com.example.demo.repository.UserClienteRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserClienteRepository userClienteRepository;
    @Autowired
    UserCatadorRepository userCatadorRepository;
    @Autowired
    RoleRepository roleRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private UserCliente userCliente;
    private UserCatador userCatador;
    public static String username = "";
    public static String id = "";
    @Autowired
    ColetaRepository coletaRepository;

    /*@PostMapping("/coleta")
    public ResponseEntity<?> NovaColeta(@Valid @RequestBody ColetaRequest coletaRequest) {

        Coleta newColeta = new Coleta(
                userCliente.getUsername(),
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
    }*/

    @PostMapping("/signinClient")
    public ResponseEntity<?> authenticateUserClient(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest.getUsername() + "----"+ loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        userCliente = new UserCliente(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),userDetails.getName(),userDetails.getPhone(),userDetails.getBirthDate());
        username = userCliente.getUsername();
        id = userCliente.getId();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userCliente);
    }

    @PostMapping("/signinScavenger")
    public ResponseEntity<?> authenticateUserScavenger(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        userCatador = new UserCatador(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),userDetails.getName(),userDetails.getPhone(),userDetails.getBirthDate());
        username = userCatador.getUsername();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(userCatador);
    }

    @GetMapping("/meusDados")
    public UserCliente retornInfo(){
        return userCliente;
    }

    /*@GetMapping("/infoTeste")
    @ResponseBody
    public ResponseEntity<Object> currentUserName(Authentication authentication) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok(new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getPhone(),
                userDetails.getBirthDate()
        ));
    }*/

    @PostMapping("/registerClient")
    public ResponseEntity<?> registerUserCliente(@Valid @RequestBody SignupClienteRequest signUpRequest) {
        if (userClienteRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userClienteRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserCliente user = new UserCliente(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getPhone(),
                signUpRequest.getBirthDate(),
                signUpRequest.getGender()
                );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();



            strRoles.forEach(role -> {
                switch (role) {
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_CLIENTE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found. entrou aqui 2"));
                        roles.add(userRole);

                        break;
                    case "catador":
                        Role catadorRole = roleRepository.findByName(ERole.ROLE_CATADOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found. entrou aqui 3"));
                        roles.add(catadorRole);

                        break;
                }
            });


        user.setRoles(roles);
        userClienteRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/registerScavenger")
    public ResponseEntity<?> registerUserCatador(@Valid @RequestBody SignupCatadorRequest signUpCatadorRequest) {
        if (userCatadorRepository.existsByUsername(signUpCatadorRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userCatadorRepository.existsByEmail(signUpCatadorRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserCatador user = new UserCatador(signUpCatadorRequest.getUsername(),
                signUpCatadorRequest.getEmail(),
                encoder.encode(signUpCatadorRequest.getPassword()),
                signUpCatadorRequest.getName(),
                signUpCatadorRequest.getPhone(),
                signUpCatadorRequest.getBirthDate(),
                signUpCatadorRequest.getGender(),
                signUpCatadorRequest.getMaterials(),
                signUpCatadorRequest.getDayWeek(),
                signUpCatadorRequest.getDayPeriod()
        );

        Set<String> strRoles = signUpCatadorRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "catador":
                    Role catadorRole = roleRepository.findByName(ERole.ROLE_CATADOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found. entrou aqui 3"));
                    roles.add(catadorRole);

                    break;
                default:

            }
        });

        user.setRoles(roles);
        userCatadorRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}