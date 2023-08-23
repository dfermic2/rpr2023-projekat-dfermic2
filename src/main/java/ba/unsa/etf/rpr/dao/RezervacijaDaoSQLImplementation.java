package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.ResultSet;

public class RezervacijaDaoSQLImplementation extends AbstractDao<Rezervacija> implements RezervacijaDao {
    @Override
    public Rezervacija row2object(ResultSet rs) throws DolinaSreceException {
        return null;
    }
}
