package it.giulioscuro.ricettelight.controller.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.giulioscuro.ricettelight.controller.WebController;
import it.giulioscuro.ricettelight.dao.IngredienteDao;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.service.RicetteService;

@RestController
public class RestControllerApi {
	
	private static final Logger log = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	IngredienteDao ingredienteDao;

	@Autowired
	RicetteService service;
	

	  @RequestMapping("/ingredienti")
	    public List<Ingrediente> testController() {
		    List<Ingrediente> ingredienti = ingredienteDao.loadAll();
	       
	        return ingredienti;
	    }
	  
	  @RequestMapping("/ricette")
	    public List<Ricetta> searchRicette(@RequestParam(name= "searchString[]") String  searchString) {
		  log.debug("search string: " + searchString);
			 String[] arraySearch =  searchString.split(",");
			   List<Ricetta> ricette = service.search(arraySearch);
	      
	        return ricette;
	    }
	
}
