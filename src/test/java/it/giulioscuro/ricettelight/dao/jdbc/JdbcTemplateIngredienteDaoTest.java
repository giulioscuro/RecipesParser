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
import it.giulioscuro.ricettelight.dao.IngredienteDao;
import it.giulioscuro.ricettelight.model.Ingrediente;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional()
public class JdbcTemplateIngredienteDaoTest {

	
	@Autowired
	IngredienteDao dao;
	
	@Test
	public void testDeleteIngredientiTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAndReturn() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadString() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadAll() {
		List<Ingrediente> all = dao.loadAll();
		assertNotNull(all);
		System.out.println(all);
	}

	@Test
	public void testFindIngredienteByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIngredientiCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulkSave() {
		fail("Not yet implemented");
	}

}
