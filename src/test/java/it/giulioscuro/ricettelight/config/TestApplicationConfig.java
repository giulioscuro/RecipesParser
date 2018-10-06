package it.giulioscuro.ricettelight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.giulioscuro.ricettelight.config.ApplicationConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.ricettelight")
@PropertySource(value = { "classpath:application.properties" })
@Import(ApplicationConfig.class)
public class TestApplicationConfig extends ApplicationConfig{


	   @Bean
	   public PlatformTransactionManager transactionManager(){

		   DataSourceTransactionManager transactionManager
	        = new DataSourceTransactionManager();
	      transactionManager.setDataSource(dataSource());
	      return transactionManager;
	   }
	
}
