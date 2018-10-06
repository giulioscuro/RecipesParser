package it.giulioscuro.ricettelight.dao;


import java.util.List;

import it.giulioscuro.ricettelight.model.Ingrediente;

public interface Dao<T> {

    long save(T t);

    T load(long id);

    void delete(long id);

    void update(T t);

    List<T> loadAll();

}