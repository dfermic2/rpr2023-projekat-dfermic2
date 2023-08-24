package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.util.List;

public interface Dao<T> {
    T getById(int id) throws DolinaSreceException;

    List<T> getAll() throws DolinaSreceException;

    T add(T item) throws DolinaSreceException;
}
