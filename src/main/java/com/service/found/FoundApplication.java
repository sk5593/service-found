package com.service.found;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.service.found.mapper")
public class FoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoundApplication.class, args);
	}

}
