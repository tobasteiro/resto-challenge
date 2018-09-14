package com.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableRetry
public class RestoProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestoProducerApplication.class, args);
	}
}
