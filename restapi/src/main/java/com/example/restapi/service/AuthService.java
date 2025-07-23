package com.example.restapi.service;

import com.example.restapi.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
