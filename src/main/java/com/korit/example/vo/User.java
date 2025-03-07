package com.korit.example.vo;

import java.util.ArrayList;
import java.util.List;

import com.korit.example.entity.UserEntity;

import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String userName;
    private String userAddress;

    private User(UserEntity userEntity){
        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.userAddress = userEntity.getUserAddress();
    }

    public static List<User> getList(List<UserEntity> userEntities){
        List<User> users = new ArrayList<>();

        // 반복문으로 userEntity 내용물을 꺼내서 user에 입력
        for(UserEntity userEntity: userEntities){
            User user = new User(userEntity);
            users.add(user);
        }
        // 반복이 끝났으면 users로 반환
        return users;
    }
}
