package com.moody.moody_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.moody")
public class MoodyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoodyApiApplication.class, args);
	}

}
