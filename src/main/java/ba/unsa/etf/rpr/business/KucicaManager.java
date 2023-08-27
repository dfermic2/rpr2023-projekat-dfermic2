package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

public class KucicaManager {
    public static Kucica findById(int id) throws DolinaSreceException {
        return DaoFactory.kucicaDao().getById(id);
    }
}
