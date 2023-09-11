package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.time.LocalDate;
import java.util.Set;

/**
 * Business Logic for management of Rezervacija
 *
 * @author Dijana Fermic
 */
public class RezervacijaManager {
    public static Set<Integer> findBetweenDates(LocalDate date) {
        return DaoFactory.rezervacijaDao().getBetweenDates(date);
    }

    public static Rezervacija add(Rezervacija rezervacija) throws DolinaSreceException {
        return DaoFactory.rezervacijaDao().add(rezervacija);
    }
}