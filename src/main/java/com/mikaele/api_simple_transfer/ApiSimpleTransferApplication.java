package com.mikaele.api_simple_transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiSimpleTransferApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSimpleTransferApplication.class, args);
	}

}
