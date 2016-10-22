package com.hrp.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

	@Autowired
    private Environment environment;
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
		System.out.println(environment.getRequiredProperty("jdbc.driverClassName"));
		System.out.println(environment.getRequiredProperty("jdbc.username"));
		System.out.println(environment.getRequiredProperty("jdbc.password"));
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	    driverManagerDataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	    driverManagerDataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	    driverManagerDataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	    return driverManagerDataSource;
	}

}
