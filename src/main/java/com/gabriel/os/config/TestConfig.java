package com.gabriel.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gabriel.os.services.DBService;

@Configuration
//@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public void intanciaDB() {
		this.dbService.instaciaDB();
	}
}
