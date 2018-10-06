package it.giulioscuro.ricettelight.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.giulioscuro.ricettelight.config.TestApplicationConfig;
import it.giulioscuro.ricettelight.model.Ricetta;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional()
public class JdbcTemplateRicettaIngredienteDaoTest {

	
	@Autowired
	JdbcTemplateRicettaIngredienteDao dao;
	
	
	@Test
	public void testFindByIngredients() {
		List<Ricetta> ricette = dao.findByIngredients(new long[] {1923,1924});
		System.out.println(ricette);
	}
	
	@Test
	public void testFindByIngredientNames() {
		List<Ricetta> ricette = dao.findByIngredients("Uova","Zucchero","Mele Gala");
		Ricetta r = ricette.get(0);
		assertEquals("Torta di mele", r.getTitolo());
		
	
	}
	

}
