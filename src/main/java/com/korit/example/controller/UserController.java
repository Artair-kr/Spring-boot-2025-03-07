package com.korit.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korit.example.dto.GetUserListResponseDto;
import com.korit.example.dto.GetUserResponseDto;
import com.korit.example.dto.PatchUserRequestDto;
import com.korit.example.dto.PostUserRequestDto;
import com.korit.example.dto.ResponseDto;
import com.korit.example.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("")
    public ResponseEntity<ResponseDto> postUser(
        @RequestBody @Valid PostUserRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = userService.postUser(requestBody);
        return response;
    }

    @GetMapping("")
    public ResponseEntity<? super GetUserListResponseDto> getUserList(){
        ResponseEntity<? super GetUserListResponseDto> response = userService.getUserList();
        return response;
    }

    @GetMapping("/{userId}")
    // 제너릭 와일드 카드 사용
    // <? extends ResponseDto> extends 뒤에 부모를 넣어준다.
    // <? super GetUserResponseDto> : GetUserResponseDto 또는 
    // GetUserResponseDto의 상위 타입을 의미하는 와일드카드
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @PathVariable("userId") String userId
    ) {
        ResponseEntity<? super GetUserResponseDto> response = userService.getUser(userId);
        return response;
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<ResponseDto> patchUser(
        @PathVariable("userId") String userId,
        @RequestBody @Valid PatchUserRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = userService.patchUser(userId, requestBody);
        return response;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseDto> deleteUser(
        @PathVariable("userId") String userId
    ){
        ResponseEntity<ResponseDto> response = userService.deleteUser(userId);
        return response;
    }
}
