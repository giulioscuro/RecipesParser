package it.giulioscuro.ricettelight.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.jolbox.bonecp.BoneCPDataSource;

import it.giulioscuro.ricettelight.dao.FonteDao;
import it.giulioscuro.ricettelight.dao.RicettaDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateFonteDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateIngredienteDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateRicettaDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateRicettaIngredienteDao;
import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.parser.GZFParser;
import it.giulioscuro.ricettelight.parser.RicetteParser;
import it.giulioscuro.ricettelight.service.RicetteService;
import it.giulioscuro.ricettelight.service.RicetteServiceImpl;

@Configuration
@ComponentScan(basePackages = "com.ricettelight")
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationConfig {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		BoneCPDataSource dataSource;
		dataSource = new BoneCPDataSource();
		dataSource.setDriverClass(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setJdbcUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;

	}

	@Bean
	public FonteDao fonteDao() {
		return new JdbcTemplateFonteDao();
	}

	@Bean
	public RicettaDao ricettaDao() {
		return new JdbcTemplateRicettaDao();
	}

	@Bean
	public JdbcTemplateIngredienteDao ingredienteDao() {
		return new JdbcTemplateIngredienteDao();
	}

	@Bean
	public JdbcTemplateRicettaIngredienteDao ricettaingredienteDao() {
		return new JdbcTemplateRicettaIngredienteDao();
	}

	// <property name="sigla" value="${fonte.sigla.giallozzafferano}"></property>
	// <property name="url" value="${fonte.url}"></property>
	// <property name="listUrl"
	// value="${fonte.url.list.giallozzafferano}"></property>
	@Bean
	public Fonte getGZfonte() {
		Fonte f = new Fonte();
		f.setListUrl(env.getProperty("fonte.url.list.giallozafferano"));
		f.setSigla(env.getProperty("fonte.sigla.giallozafferano"));
		f.setUrl(env.getProperty("fonte.url.giallozafferano"));
		f.setPageToScan(20);
		return f;
	}

	@Bean
	public RicetteParser getGZFParser() {

		return new GZFParser();
	}

	@Bean
	public RicetteServiceImpl getRicetteService() {

		return new RicetteServiceImpl();
	}

}