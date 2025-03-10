package com.korit.example.service;

import org.springframework.http.ResponseEntity;

import com.korit.example.dto.SignUpRequestDto;

public interface SecurityService {
    ResponseEntity<String> signUp(SignUpRequestDto dto);
}
