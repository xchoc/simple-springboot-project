package com.example.simple.springboot.project;

import com.example.simple.springboot.project.config.JpaConfig;
import com.example.simple.springboot.project.config.AuthenticationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {Application.class, JpaConfig.class, AuthenticationConfig.class}, args);
	}
}
