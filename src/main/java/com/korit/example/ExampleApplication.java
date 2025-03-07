package com.korit.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.korit.example.dto.Lombok;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	void method(){
		Lombok lombok1 = new Lombok("a", 1 ,false, true);
		Lombok lombok2 = new Lombok("a", 1);

		//GETTER
		lombok1.getField1();
		lombok1.getField2();
		lombok1.isField3(); 
		lombok1.getField4();

		//SETTER
		lombok2.setField3(false);
		lombok2.setField4(null); // 참조형태라 null도 들어간다.
	}
}
