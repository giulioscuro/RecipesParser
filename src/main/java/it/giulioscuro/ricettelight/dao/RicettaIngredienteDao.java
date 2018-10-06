package it.giulioscuro.ricettelight.dao;

import java.util.List;

import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.model.RicettaIngrediente;

public interface RicettaIngredienteDao extends Dao<RicettaIngrediente> {

   

    Long getRicettaCount();

  //  void updateAddress(long id, String newAddress);

    int[][] bulkSave(List<RicettaIngrediente> newRicette);
    
    void deleteRicetteTable();
}