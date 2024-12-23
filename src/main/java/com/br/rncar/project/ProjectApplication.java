package com.br.rncar.project;

import com.br.rncar.project.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ProjectApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}



}
