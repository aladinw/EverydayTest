package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:dubbo/spring-dubbo.xml" })

public class EverydayTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EverydayTestApplication.class, args);
	}

}
