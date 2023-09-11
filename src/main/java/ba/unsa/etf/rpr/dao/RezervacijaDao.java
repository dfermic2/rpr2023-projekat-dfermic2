package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface RezervacijaDao extends Dao<Rezervacija> {

    Set<Integer> getBetweenDates(LocalDate date);
}
