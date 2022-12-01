package com.example.demo.controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.example.demo.models.ERole;
import com.example.demo.models.Role;
import com.example.demo.models.UserCatador;
import com.example.demo.models.UserCliente;
import com.example.demo.payload.requests.LoginRequest;
import com.example.demo.payload.requests.SignupCatadorRequest;
import com.example.demo.payload.requests.SignupClienteRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.payload.response.UserInfoResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserCatadorRepository;
import com.example.demo.repository.UserClienteRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
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
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @GetMapping("/info")
   @ResponseBody
    public ResponseEntity<?> currentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        return ResponseEntity.ok(new UserInfoResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getPhone(),
                userDetails.getBirthDate()
                ));
    }

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