package com.korit.example.service;

import org.springframework.http.ResponseEntity;

import com.korit.example.dto.GetUserListResponseDto;
import com.korit.example.dto.GetUserResponseDto;
import com.korit.example.dto.PatchUserRequestDto;
import com.korit.example.dto.PostUserRequestDto;
import com.korit.example.dto.ResponseDto;

public interface UserService {
    
    ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);
    ResponseEntity<? super GetUserListResponseDto> getUserList();
    ResponseEntity<? super GetUserResponseDto> getUser(String userId);
    ResponseEntity<ResponseDto> patchUser(String userId, PatchUserRequestDto dto);
    ResponseEntity<ResponseDto> deleteUser(String userId);
}