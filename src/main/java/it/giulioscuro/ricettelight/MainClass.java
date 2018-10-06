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
	private RicettaDao dao;

	@Autowired
	private IngredienteDao daoIngrediente;

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
		//ricettaService.clearDatabase();
		populateDB();
		//testHibernate();
		
	}

	public void populateDB() {
		List<Ricetta> ricette = getGZFParser.parseList();
		System.out.println(" Numero ricette: " +ricette.size());
		for (Ricetta r : ricette) {
					ricettaService.addRicetta(r);
		
		}
	}

	public void testHibernate() {

		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Ingrediente.class)
				.addAnnotatedClass(DettaglioIngrediente.class).addAnnotatedClass(Ricetta.class)
				.addAnnotatedClass(Fonte.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			/*
			 * Ingrediente ingr= new Ingrediente(); ingr.setNome("Pomodorino2");
			 * DettaglioIngrediente di = new DettaglioIngrediente(); di.setCalorie(12);
			 * ingr.setDettaglioIngrediente(di); ; session.save(ingr);
			 */

			Ingrediente ing = session.get(Ingrediente.class, 1343l);

			Fonte f = session.get(Fonte.class, "gzf");
		

			if (ing != null) {

				System.out.println("Deleting -->" + ing.getNome());
				session.delete(ing);
			}

			if (f != null) {
				System.out.println("Numemero ricette "+f.getSigla() + " " + f.getRicette().size());
			}

			session.getTransaction().commit();
		} finally {
			// TODO: handle finally clause
			sessionFactory.close();
		}
	}

	


	
	

}
