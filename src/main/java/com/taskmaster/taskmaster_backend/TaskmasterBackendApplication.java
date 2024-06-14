package com.taskmaster.taskmaster_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.taskmaster")
@EnableJpaRepositories(basePackages = "com.taskmaster.repositories")
@EntityScan(basePackages = "com.taskmaster.models")
public class TaskmasterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskmasterBackendApplication.class, args);
	}

}
