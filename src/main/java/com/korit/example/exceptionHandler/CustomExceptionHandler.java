package com.korit.example.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.korit.example.dto.ResponseDto;

//import com.korit.example.dto.Validation;

// @RestControllerAdvice : 해당 클래스를 RestController에서 발생하는
// 특정 상황들에 대해 처리를 담당하는 클래스로 지정한다.

@RestControllerAdvice
public class CustomExceptionHandler {
    
    // @ExceptionHandler: 지정한 예외에 대하여 직접 조작할 수 있도록 하는어노테이션
    // @ExceptionHandler(value = {예외클래스, ...}) 배열이라 여러개 등록가능 { 예외1클래스, 예외2클래스}
    @ExceptionHandler(value = {
        MethodArgumentNotValidException.class,
        HttpMessageNotReadableException.class
    })
    public ResponseEntity<ResponseDto> customException(
        Exception exception
    ){
        exception.printStackTrace();
        ResponseDto responseBody = new ResponseDto("VF","Validation Failed.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
