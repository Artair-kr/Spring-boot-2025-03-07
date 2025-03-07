package com.korit.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Lombok 라이브러리
// - getter, setter, constructor 등 자주 사용되지만 
// 반복적으로 작성해야하는 코드를 자동으로 생성해주는 라이브러리
// - 프로젝트가 lombok 라이브러리 의존성을 가지고 있어야한다.
// - 대부분의 IDE는 기복적으로 Lombok 어노테이션에 대해 
// 이해를 하지 못하기 때문에 Lombok 추가 확장이 필요하다.

// @Getter: getter메서드 생성 / 객체의 속성값을 읽어오는 메서드
// @Setter: setter메서드 생성 / 객체의 속성값을 설정하거나 변경하는 메서드
// @ToString: toString 메서드 생성 (필드명과 값을 문자열로 표현)
// @NoArgsConstructor: 기본 생성자 생성 (빈 생성자)
// @AllArgsConstructor: 모든 멤버 변수를 매개변수로 받는 생성자 생성
// @RequiredArgsConstructor: 필수 멤버 변수를 매개변수로 받는 생성자 생성

// @NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Lombok {
  private final String field1;
  private final int field2;
  private boolean field3; // 기본형
  private Boolean field4; // 참조형
}