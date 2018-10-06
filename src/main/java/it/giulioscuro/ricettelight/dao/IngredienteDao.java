package it.giulioscuro.ricettelight.dao;

import java.util.List;

import it.giulioscuro.ricettelight.model.Ingrediente;

public interface IngredienteDao extends Dao<Ingrediente> {

    List<Ingrediente> findIngredienteByName(String name);

    Long getIngredientiCount();

  //  void updateAddress(long id, String newAddress);

    int[][] bulkSave(List<Ingrediente> newIngredienti);
    

	Ingrediente load(String nome);
}