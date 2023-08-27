package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.util.List;

public class KucicaManager {
    public static Kucica findById(int id) throws DolinaSreceException {
        return DaoFactory.kucicaDao().getById(id);
    }

    public static List<Kucica> getAll() throws DolinaSreceException {
        return DaoFactory.kucicaDao().getAll();
    }
}
