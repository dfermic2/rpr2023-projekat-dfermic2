package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RezervacijaDao extends Dao<Rezervacija> {

    Set<Integer> getBetweenDates(Date date);
}
