package com.korit.example.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PathVariable;




// Controller 레이어:
// - 클라이언트와 서버간의 접점
// - 클라이언트의 요청을 받고 해당 요청에 대한 응답을 처리
// - 각 요청의 HTTP method와 url에 해당하는 메서드를 장성하는 공간

// @RestController: JSON 형태의 ResponseBody를 반환하는 Controller임을 명시
// @Controller + @ResponseBody
// @Controller: 해당 클래스가 HTML 형태의 ResponseBody를 반환하는 Controller임을 명시
// @ResponseBody: 컨트롤러의 응답형태를 직접 조작할 수 있도록 한다.
// 쉽게 생각하면 입구를 만드는 것이다.
@RestController

// @RequestMapping: HTTP 요청에 클래스 혹은 메서드를 URL 및 HTTP Method 
// 등으로 매핑하기 위한 어너테이션
// HTTP GET 127.0.0.1:8080/basic/**
// @RequestMapping(value = "/basic", method = RequestMethod.GET)

// HTTP GET 127.0.0.1:8080/example 까지 들어온후 Controller 실행
@RequestMapping("/basic")
public class ExampleController {
    
    // HTTP GET 127.0.0.1:8080/example/first
    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String firstMethod(){
        return "Spring Boot 첫번째 메소드";
    }

    // @GetMapping : RequestMapping을 GET method에 한정시킨 어노테이션
    // GET method - 클라이언트가 데이터를 받기위한 메소드
    // Request Body X / Response Body O / HTML form O
    @GetMapping("")
    public String getMethod() {
        return "GET Method";
    }
    
    // @PostMapping: RequestMapping을 POST에 한정시킨 어노테이션
    // POST method - 클라이언트가 서버에 데이터를 전송하기위한 메소드
    //             - 클라이언트가 서버에 데이터를 작성하기 위한 메소드(create에 한정)
    // Request Body O / Response Body O / HTML form O
    @PostMapping("")
    public String postMethod() {
        return "POST method";
    }

    // @PutMapping: RequestMapping을 PUT에 한정시킨 어노테이션
    // PUT method - 클라이언트가 서버에 데이터를 생성 혹은 수정하기위한 메소드
    //            - 만약 전송한 데이터가 동일한 데이터가 있다면 수정
    //            - 만약 전송한 데이터가 존재하지 않는 데이터면 생성
    //            - 동일 데이터를 여러번 전송하더라도 한번만 적용
    // Request Body X / Response Body X / HTML form X

    // Response Body 는 존재하면 안되지만 테스트용으로 작성
    @PutMapping("")
    public String putMethod(){
        return "PUT Method";
    }

    // @PatchMapping: RequestMapping을 PATCH에 한정시킨 어노테이션
    // PATCH method - 클라이언트가 서버의 일부 데이터를 수정하기위한 메소드
    // Request Body O / Response Body O / HTML form X
    @PatchMapping("")
    public String patchMethod(){
        return "PATCH method";
    }

    // @DeleteMapping: RequestMapping을 DELETE에 한정시킨 어노테이션
    // DELETE metod - 클라이언트가 서버에 데이터를 삭제하기 위한 메소드
    // Request Body X(OR O) / Response Body X(OR O) / HTML form X
    // 있을수도 있고 없을수도 있다.
    @DeleteMapping("")
    public String deleteMethod(){
        return "DELETE method";
    }

    // ! 주의 HTTP Method + URL 패턴이 중복되면 런타임 error가 발생
    // @GetMapping("/duplicate")
    // public void duplicate1() {}

    // @GetMapping("/duplicate")
    // public void duplicate2() {}
    
}    
