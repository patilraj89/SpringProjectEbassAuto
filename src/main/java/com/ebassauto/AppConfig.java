package com.ebassauto;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**

@Author Pradip Patil

*
*/
@Configuration
@ComponentScan
//@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {
	
	//@Autowired
	//Environment environment;

	/*
	 * private final String URL = "url"; private final String USER = "dbuser";
	 * private final String DRIVER = "driver"; private final String PASSWORD =
	 * "dbpass";
	 */

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:mysql://localhost/emmi");//environment.getProperty(URL)
		driverManagerDataSource.setUsername("root");//environment.getProperty(USER)
		driverManagerDataSource.setPassword("root");//environment.getProperty(PASSWORD)
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");//environment.getProperty(DRIVER)
		return driverManagerDataSource;
	}

}

