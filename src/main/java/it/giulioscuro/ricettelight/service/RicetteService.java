package it.giulioscuro.ricettelight.service;

import java.util.List;

import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;

public interface RicetteService {

	
	public void addRicetta(Ricetta r );
	
	public void addFonte(String sigla,String url,String icona);
	
	public  List<Fonte> getFonti();
	
	
	public  List<Ingrediente> getAllIngredients();
	
	public List<Ricetta> search(String... nomiIngredienti);
	
}
