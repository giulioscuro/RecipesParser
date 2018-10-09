package it.giulioscuro.ricettelight.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.giulioscuro.ricettelight.dao.IngredienteDao;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.service.RicetteService;

@Controller
public class WebController {

	
	private static final Logger log = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	IngredienteDao ingredienteDao;
	
	@Autowired RicetteService service;
	
	  @RequestMapping("/web/ingredienti")
	    public String testController(String name, Model model) {
		   List<Ingrediente> ingredienti = ingredienteDao.loadAll();
	        model.addAttribute("ingredienti", ingredienti);
	        return "ingredienti";
	    }
	  
	  @RequestMapping("/web/search")
	    public String searchContoller(String name, Model model) {
		   List<Ingrediente> ingredienti = ingredienteDao.loadAll();
	        model.addAttribute("ingredienti", ingredienti);
	        return "search2";
	    }
	  
	  
	  @RequestMapping("/web/ricette")
	    public String ricetteController(@RequestParam String searchString, Model model) {
		  log.debug("search string: " + searchString);
		 String[] arraySearch =  searchString.split(",");
		   List<Ricetta> ricette = service.search(arraySearch);
	        model.addAttribute("ricette", ricette);
	        return "ricete";
	    }
	  
	  @RequestMapping("/web/ricette2")
	    public String ricette2Controller(@RequestParam(name= "searchString[]") String  searchString, Model model) {
		  log.debug("search string: " + searchString);
		 String[] arraySearch =  searchString.split(",");
		   List<Ricetta> ricette = service.search(arraySearch);
	        model.addAttribute("ricette", ricette);
	        return "search2";
	    }
	
	
	
}
