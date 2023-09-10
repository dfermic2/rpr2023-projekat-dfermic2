package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;

import java.util.Date;
import java.util.List;

public interface RezervacijaDao extends Dao<Rezervacija> {

    List<Rezervacija> getBetweenDates(Date date);
}
