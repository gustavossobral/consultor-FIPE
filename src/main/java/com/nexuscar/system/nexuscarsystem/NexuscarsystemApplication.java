package com.nexuscar.system.nexuscarsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NexuscarsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexuscarsystemApplication.class, args);
	}
}



