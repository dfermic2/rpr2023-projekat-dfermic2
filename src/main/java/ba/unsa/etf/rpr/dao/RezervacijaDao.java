package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;

import java.time.LocalDate;
import java.util.Set;

/**
 * Dao interface for Rezervacija model
 *
 * @author Dijana Fermic
 */
public interface RezervacijaDao extends Dao<Rezervacija> {

    Set<Integer> getBetweenDates(LocalDate date);
}
