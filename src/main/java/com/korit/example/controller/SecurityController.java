package com.korit.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.korit.example.dto.SignInRequestDto;
import com.korit.example.dto.SignUpRequestDto;
import com.korit.example.provider.JwtProvider;
import com.korit.example.service.SecurityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {

  private final SecurityService securityService;
  private final JwtProvider jwtProvider;
    private SignUpRequestDto requestBody;
  
    @GetMapping("/jwt")
    public String getJwt(
      @RequestParam("name") String name
    ) {
      String jwt = jwtProvider.create(name);
      return jwt;
    }
  
    @PostMapping("/jwt")
    public String validateJwt(
      @RequestBody String jwt
    ) {
      String subject = jwtProvider.validate(jwt);
      return subject;
    }
  
    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(
      @RequestBody @Valid SignUpRequestDto requestBody
    ){
      ResponseEntity<String> response = securityService.signUp(requestBody);
    return response;
  }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(
      @RequestBody @Valid SignInRequestDto requestBody
    ){
     ResponseEntity<String> response = securityService.signIn(requestBody);
     return response;
  }

}