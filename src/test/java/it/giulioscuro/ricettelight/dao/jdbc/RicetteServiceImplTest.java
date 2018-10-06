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
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.service.RicetteService;
import it.giulioscuro.ricettelight.service.RicetteServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional()
public class RicetteServiceImplTest {
	
	@Autowired
	RicetteService ricetteService;

	@Test
	public void testAddRicetta() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFonte() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFonti() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllIngredients() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		List<Ricetta> ricette = ricetteService.search("Uova","Zucchero");
		assertEquals(26, ricette.size());
		
		Ricetta r = ricette.get(0);
		System.out.println(r);
		//assertEquals("Torta di mele", r.getTitolo());
		System.out.println(r.getDescrizione());
		System.out.println(r.getImageUrl());
		System.out.println(r.getIstruzioni());
		System.out.println(r.getLinkUrl());
		
	//	assertNotNull(r.getIngredienti());
	
		for(Ingrediente i : r.getIngredienti()) {
			System.out.println(i.getNome());
		}
	}

}
