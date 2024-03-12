package com.example.demo.annotations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setWebApplicationType(WebApplicationType.SERVLET); // O REACTIVE si es una aplicación reactiva
		app.run(args);
		//SpringApplication.run(DemoApplication.class, args);
	}

}
