package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Dao interface for Rezervacija model
 *
 * @author Dijana Fermic
 */
public interface RezervacijaDao extends Dao<Rezervacija> {
    /**
     * get entity from database with given id
     * @param date we check is between fields pocetak and kraj of Rezervacija
     * @return Set of Integers representing idKucica that are already reserved
     * @throws DolinaSreceException - user defined exception
     */
    Set<Integer> getBetweenDates(LocalDate date);
}
