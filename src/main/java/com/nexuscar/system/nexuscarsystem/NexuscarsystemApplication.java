package com.nexuscar.system.nexuscarsystem;

import com.nexuscar.system.nexuscarsystem.domain.DTO.ConsultarFIPEPorMarcaDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class NexuscarsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexuscarsystemApplication.class, args);
	}
}



