package com.gabriel.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.gabriel.os.services.DBService;

//@Configuration
//@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean
	public void intanciaDB() {
		
		//if(ddl.equals("create")) {
			this.dbService.instaciaDB();
		//}
		//return false;
	}
}
