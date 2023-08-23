package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.KorisnikDao;
import ba.unsa.etf.rpr.dao.KorisnikDaoSQLImplementation;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {

        KorisnikDao korisnikDao = new KorisnikDaoSQLImplementation();
        Connection connection = KorisnikDaoSQLImplementation.getConnection();

        if (connection != null) System.out.println("Connection created");
        else System.out.println("Failed to connect to DB");

        try {
            System.out.println(korisnikDao.getById(1));
        } catch (DolinaSreceException e) {
            System.out.println(e.getMessage());
        }

    }
}
