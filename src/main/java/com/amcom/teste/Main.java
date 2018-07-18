package com.amcom.teste;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
//@ComponentScan ({"com.amcom.teste.configuracao", "com.amcom.teste.repositorios"})
//@EnableMongoRepositories ("com.amcom.teste.repositorios")
public class Main {
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	
	@Bean
	public DataSource dataSource(){
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    dataSource.setUrl("jdbc:sqlserver://localhost:1433;database=teste;");
	    dataSource.setUsername("sa");
	    dataSource.setPassword("123spn");
	    return dataSource;
	}

}
