package it.giulioscuro.ricettelight.dao;

import java.util.List;

import it.giulioscuro.ricettelight.model.Fonte;


public interface FonteDao extends Dao<Fonte> {

	 List<Fonte> findIngredienteBySigla(String sigla);

	 Long getRowCount();
 
	 Fonte load(String sigla);
	 
	 


}
