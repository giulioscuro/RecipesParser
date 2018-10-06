package it.giulioscuro.ricettelight.parser;

import static org.junit.Assert.*;

import java.util.List;

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
public class GZFParserTest {

	@Autowired
	GZFParser gfzParser;
	
	
	@Test
	public void testParseRicette() {
		
		List<Ricetta> ricette = gfzParser.parseList();
		System.out.println(ricette);
		
		assertEquals(15*gfzParser.getFonte().getPageToScan(), ricette.size());
		
		
		for(Ricetta r : ricette) {
			System.out.println(r.getTitolo());
		}
	}

}
