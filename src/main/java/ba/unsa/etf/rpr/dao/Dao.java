package ba.unsa.etf.rpr.dao;

import java.util.List;

public interface Dao<T> {
    T getById(int id);

    List<T> getAll();
}
