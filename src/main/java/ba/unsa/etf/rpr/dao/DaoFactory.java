package ba.unsa.etf.rpr.dao;

public class DaoFactory {
    private DaoFactory() {
    }

    private static final KorisnikDao korisnikDao = KorisnikDaoSQLImplementation.getInstance();

    public static KorisnikDao korisnikDao() {
        return korisnikDao;
    }

    private static final KucicaDao kucicaDao = KucicaDaoSQLImplementation.getInstance();

    public static KucicaDao kucicaDao() {
        return kucicaDao;
    }

    private static final RezervacijaDao rezervacijaDao = RezervacijaDaoSQLImplementation.getInstance();

    public static RezervacijaDao rezervacijaDao() {
        return rezervacijaDao;
    }
}
