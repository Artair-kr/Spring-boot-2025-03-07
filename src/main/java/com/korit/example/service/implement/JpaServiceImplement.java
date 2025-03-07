package com.korit.example.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.korit.example.entity.SampleTable1Entity;
import com.korit.example.repository.SampleTable1Repository;
import com.korit.example.service.JpaService;

import lombok.RequiredArgsConstructor;

@Service
// Spring bean 서비스로 등록
@RequiredArgsConstructor
// 생성자를 이용하여 의존성 주입
public class JpaServiceImplement implements JpaService{

    // 생성자를 이용하여 의존성 주입
    private final SampleTable1Repository sampleTable1Repository;

    @Override
    public ResponseEntity<String> createSampleTable1() {
        
        SampleTable1Entity entity = new SampleTable1Entity
        ("a",1,false);

        sampleTable1Repository.save(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body("성공");
    }
    
}
