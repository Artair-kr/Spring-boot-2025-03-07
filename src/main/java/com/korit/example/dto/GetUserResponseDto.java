package com.korit.example.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.korit.example.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetUserResponseDto extends ResponseDto{

    private String userId;
    private String userName;
    private String userAddress;

    // 상속받아 사용
    // UserEntity를 내보내면 안된다. 개인정보가 노출된다.
    // 아래처럼 private로 보호 후, 사용한다.
    private GetUserResponseDto(UserEntity userEntity){
        super("SU", "Success");
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.userAddress = userEntity.getUserAddress();
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity){
        GetUserResponseDto responseBody = new GetUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
