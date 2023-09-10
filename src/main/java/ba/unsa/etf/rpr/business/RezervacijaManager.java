package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.util.Date;
import java.util.Set;

public class RezervacijaManager {
    public static Set<Integer> findBetweenDates(Date date) {
        return DaoFactory.rezervacijaDao().getBetweenDates(date);
    }

    public static Rezervacija add(Rezervacija rezervacija) throws DolinaSreceException {
        return DaoFactory.rezervacijaDao().add(rezervacija);
    }
}