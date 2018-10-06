package it.giulioscuro.ricettelight.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import it.giulioscuro.ricettelight.config.TestApplicationConfig;
import it.giulioscuro.ricettelight.dao.FonteDao;
import it.giulioscuro.ricettelight.model.Fonte;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class, loader = AnnotationConfigContextLoader.class)
@Transactional()

public class JdbcTemplateFonteDaoTest {

	@Autowired
	FonteDao fonteDao;

	@Test
	@Transactional
	@Rollback(true)
	public void testInsert() {
		Fonte fonteSito = new Fonte();
		fonteSito.setSigla("TEST");
		fonteSito.setIcon("icon.jpg");
		fonteSito.setUrl("url.com");
		long rowAffected = fonteDao.save(fonteSito);
		assertEquals(1, rowAffected);
		
		Fonte f = fonteDao.load("TEST");
		assertNotNull(f);
		
		Long numRows = fonteDao.getRowCount();
		assertEquals(2, numRows.longValue());
		
		List<Fonte> all = fonteDao.loadAll();
		
	    assertEquals(2,all.size());
		
	}
	
	
	

}
