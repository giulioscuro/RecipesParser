package it.giulioscuro.ricettelight.dao;

import java.util.List;

import it.giulioscuro.ricettelight.model.Ricetta;

public interface RicettaDao extends Dao<Ricetta> {

    List<Ricetta> findRicettaByTitle(String title);
    
    Ricetta findRicettaByIdFonte(String fonte,String idFonte);

    Long getRicettaCount();

  //  void updateAddress(long id, String newAddress);

    int[][] bulkSave(List<Ricetta> newRicette);
    
    void deleteRicetteTable();
}