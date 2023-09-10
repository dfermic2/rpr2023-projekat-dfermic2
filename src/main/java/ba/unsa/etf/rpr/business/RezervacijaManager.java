package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

public class RezervacijaManager {
    public static Set<Integer> findBetweenDates(LocalDate date) {
        return DaoFactory.rezervacijaDao().getBetweenDates(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
}