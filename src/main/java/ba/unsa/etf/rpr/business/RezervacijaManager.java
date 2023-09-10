package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;

import java.util.Date;
import java.util.Set;

public class RezervacijaManager {
    public static Set<Integer> findBetweenDates(Date date) {
        return DaoFactory.rezervacijaDao().getBetweenDates(date);
    }
}