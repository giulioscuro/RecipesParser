package it.giulioscuro.ricettelight.dao.jdbc;

import static org.junit.Assert.*;

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
public class JdbcTemplateRicettaDaoTest {
	

	@Autowired
	JdbcTemplateRicettaDao dao;
	
	

	@Test
	public void testfindRicettaByTitle() {
		Ricetta r = dao.findRicettaByIdFonte("GZF", "17354");
		assertEquals("Tiramisù", r.getTitolo());
	}
	
	@Test
	public void testDeleteRicetteTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testBulkSave() {
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
	public void testLoad() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindRicettaByTitle() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRicettaCount() {
		fail("Not yet implemented");
	}

}
