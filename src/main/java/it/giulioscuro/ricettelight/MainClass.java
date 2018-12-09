package it.giulioscuro.ricettelight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.giulioscuro.ricettelight.dao.IngredienteDao;
import it.giulioscuro.ricettelight.dao.RicettaDao;
import it.giulioscuro.ricettelight.model.DettaglioIngrediente;
import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.parser.RicetteParser;
import it.giulioscuro.ricettelight.service.RicetteService;
import it.giulioscuro.ricettelight.service.RicetteServiceImpl;

@SpringBootApplication
public class MainClass implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(MainClass.class);


	@Autowired
	private RicetteServiceImpl ricettaService;

	@Autowired
	private RicetteParser getGZFParser;

	public static void main(String[] args) {
		// Application
		SpringApplication.run(MainClass.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// FatSecretService fservice = new FatSecretService();
		// fservice.searchFoodItems();
		ricettaService.clearDatabase();
		populateDB();

	}

	public void populateDB() {
		List<Ricetta> ricette = getGZFParser.parseList();
		System.out.println(" Numero ricette: " +ricette.size());
		for (Ricetta r : ricette) {
					ricettaService.addRicetta(r);
		
		}
	}


	


	
	

}
