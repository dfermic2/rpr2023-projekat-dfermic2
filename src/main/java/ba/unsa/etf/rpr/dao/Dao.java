package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.util.List;

/**
 * Root interface for all DAO classes
 *
 * @author Dijana Fermic
 */
public interface Dao<T> {
    /**
     * get entity from database with given id
     * @param id primary key of entity
     * @return Entity from database
     * @throws DolinaSreceException - user defined exception
     */
    T getById(int id) throws DolinaSreceException;


    List<T> getAll() throws DolinaSreceException;

    T add(T item) throws DolinaSreceException;
}
