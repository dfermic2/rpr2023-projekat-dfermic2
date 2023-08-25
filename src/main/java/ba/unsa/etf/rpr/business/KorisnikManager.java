package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.KorisnikDao;
import ba.unsa.etf.rpr.dao.KorisnikDaoSQLImplementation;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

public class KorisnikManager {
    public static Korisnik findByEmail(String email) throws DolinaSreceException {
        KorisnikDao korisnikDao = new KorisnikDaoSQLImplementation();
        return korisnikDao.getByEmail(email);
    }
}
