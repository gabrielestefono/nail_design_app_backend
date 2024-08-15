package com.nail.design.users.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nail.design.users.dto.UserLoginDto;
import com.nail.design.users.dto.UserRegisterDto;
import com.nail.design.users.model.UserEntity;
import com.nail.design.users.repository.UserRepository;
import com.nail.design.users.services.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public UserController(
            UserRepository userRepository,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Valid @RequestBody UserRegisterDto userRegisterDto,
            Errors errors
    ) {
        // TODO: Retornar no máximo em 3 lugares diferentes
        if (errors.hasErrors()) {
            var errorList = errors.getAllErrors();
            var errorsMap = new HashMap<String, String>();

            for (int i = 0; i < errorList.size(); i++) {
                var error = (FieldError) errorList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(errorsMap);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userRegisterDto.getFirstName());
        userEntity.setLastName(userRegisterDto.getLastName());
        userEntity.setEmail(userRegisterDto.getEmail());
        userEntity.setPhone(userRegisterDto.getPhone());
        userEntity.setAddress(userRegisterDto.getAddress());
        userEntity.setRole("Client");
        userEntity.setCreatedAt(new Date());

        var bCryptEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(bCryptEncoder.encode(userRegisterDto.getPassword()));

        try {
            var otherUser = userRepository.findByEmail(userRegisterDto.getEmail());
            if (otherUser != null) {
                return ResponseEntity.badRequest().body("O usuário já existe!");
            }

            userRepository.save(userEntity);

            String jwtToken = jwtService.createJwtToken(userEntity);

            var response = new HashMap<String, String>();
            response.put("token", jwtToken);
            response.put("user", userEntity.toString());

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            // TODO: Criar uma nova Exception específica
            // TODO: Remover o printStackTrace
            System.out.println("Ocorreu um erro!");
            ex.printStackTrace();
        }

        return ResponseEntity.badRequest()
                .body("Error");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody UserLoginDto userLoginDto, Errors errors
    ) {
        if (errors.hasErrors()) {
            var errorList = errors.getAllErrors();
            var errorsMap = new HashMap<String, String>();

            for (int i = 0; i < errorList.size(); i++) {
                var error = (FieldError) errorList.get(i);
                errorsMap.put(error.getField(), error.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(errorsMap);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.getEmail(), userLoginDto.getPassword()
                    )
            );

            UserEntity userEntity = userRepository.findByEmail(userLoginDto.getEmail());

            String jwtToken = jwtService.createJwtToken(userEntity);

            var response = new HashMap<String, String>();
            response.put("token", jwtToken);
            response.put("user", userEntity.toString());

            return ResponseEntity.ok(response);

        } catch (Exception ex) {
            // TODO: Criar uma nova Exception específica
            // TODO: Remover o printStackTrace
            System.out.println("Ocorreu um erro!");
            ex.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Usuário ou senha incorretos!");
    }

}
