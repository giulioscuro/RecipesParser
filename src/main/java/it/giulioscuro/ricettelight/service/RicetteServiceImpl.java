package it.giulioscuro.ricettelight.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateIngredienteDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateRicettaDao;
import it.giulioscuro.ricettelight.dao.jdbc.JdbcTemplateRicettaIngredienteDao;
import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.model.RicettaIngrediente;

public class RicetteServiceImpl implements RicetteService {

	private static final Logger log = LoggerFactory.getLogger(RicetteServiceImpl.class);
	
	@Autowired
	private JdbcTemplateRicettaDao dao;
	
	@Autowired
	private  JdbcTemplateIngredienteDao daoIngrediente;
	
	@Autowired
	private  JdbcTemplateRicettaIngredienteDao daoRicettaIngrediente;
	
	public void clearDatabase() {
		daoRicettaIngrediente.deleteRicetteTable();
	}

	@Override
	public void addRicetta(Ricetta ricetta) {
		
		Set<RicettaIngrediente> lista = new HashSet<RicettaIngrediente>();
		// salva ricetta
		log.debug("Aggiungo ricetta : " + ricetta);
		Ricetta newr = dao.saveAndReturn(ricetta);
		//salva ingredienti- newl databese nessuna relazione con ricetta
		for(Ingrediente ingrediente : ricetta.getIngredienti()) {
			Ingrediente newIng=null;
			log.debug("Aggiungo ingrediente " +ingrediente.getNome() + " a ricetta con id : " + ricetta.getId());
		
			Ingrediente search = daoIngrediente.load(ingrediente.getNome());
			
			if(search == null) {
				 newIng = daoIngrediente.saveAndReturn(ingrediente);
			}else {
				newIng = search;
				log.debug("Ingrediente:" + ingrediente.getNome() + " già nel database");
			}
			
			//salva relazione tra ricetta e ingredienti
			RicettaIngrediente row = new RicettaIngrediente();
			
			row.setIdIngrediente(newIng.getId());
			row.setIdRicetta(newr.getId());
			
			row.setQuantitaEsetesa(ingrediente.getQuantita());
			row.setUnitaDiMisura(newr.getId()+""+newIng.getId());
			log.debug("RicettaIngrediente: " + row);
			lista.add(row);
				
		}
		
		if(!lista.isEmpty())
			daoRicettaIngrediente.bulkSave(lista);
		
	}

	@Override
	public void addFonte(String sigla, String url, String icona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fonte> getFonti() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Ingrediente> getAllIngredients() {
		return daoIngrediente.loadAll();
	}

	@Override
	public List<Ricetta> search(String... nomiIngredienti) {
		
		List<Ricetta> results = daoRicettaIngrediente.findByIngredients(nomiIngredienti);
		
		for (Ricetta r: results) {
			List<Ingrediente> ingredienti = daoRicettaIngrediente.getIngredients(r.getId());
			r.setIngredienti(ingredienti);
			
		}
		
		return results;
		
		
	}

}
