package com.example.restapi.controller;

import com.example.restapi.dto.AuthResponseDto;
import com.example.restapi.dto.LoginDto;
import com.example.restapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {


        String token = authService.login(loginDto);


        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);


        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }
}
