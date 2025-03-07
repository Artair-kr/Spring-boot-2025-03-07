package com.korit.example.entity;

import com.korit.example.dto.PatchUserRequestDto;
import com.korit.example.dto.PostUserRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// entitiy 클래스, entitiy 명은 user
// practic_sql 데이터베이스의 user 테이블과 매핑
@Entity(name="user")
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class UserEntity {

    @Id
    private String userId;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userTelNumber;

    public UserEntity(PostUserRequestDto dto){
        this.userId = dto.getUserId();
        this.userPassword = dto.getUserPassword();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userTelNumber = dto.getUserTelNumber();
    }

    public void patch(PatchUserRequestDto dto) {
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
    }
}
