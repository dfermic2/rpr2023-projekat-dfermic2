package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

public class KorisnikManager {
    public static Korisnik findByEmail(String email) throws DolinaSreceException {
        return DaoFactory.korisnikDao().getByEmail(email);
    }

    public static Korisnik add(Korisnik korisnik) throws DolinaSreceException {
        return DaoFactory.korisnikDao().add(korisnik);
    }
}
