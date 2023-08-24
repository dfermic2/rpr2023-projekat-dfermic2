package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.ResultSet;

public class KucicaDaoSQLImplementation extends AbstractDao<Kucica> implements KucicaDao {
    @Override
    public Kucica row2object(ResultSet rs) throws DolinaSreceException {
        return null;
    }
}
