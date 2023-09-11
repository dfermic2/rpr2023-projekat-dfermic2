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

    /**
     * get all entities from database with given id
     * @return List of entities from database
     * @throws DolinaSreceException - user defined exception
     */
    List<T> getAll() throws DolinaSreceException;

    /**
     * add object to database
     * @param item object to add to database
     * @return Entity from database
     * @throws DolinaSreceException - user defined exception
     */
    T add(T item) throws DolinaSreceException;
}
