package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.*;
import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {

//        KorisnikDao korisnikDao = new KorisnikDaoSQLImplementation();
//        Connection connection = KorisnikDaoSQLImplementation.getConnection();
//
//        if (connection != null) System.out.println("Connection created");
//        else System.out.println("Failed to connect to DB");
//
//        try {
//            System.out.println(korisnikDao.getByEmail("'YKWDuuude@yahoo.com'"));
//            System.out.println(korisnikDao.getAll());
//            System.out.println(korisnikDao.add(new Korisnik(21, "Dan", "Soder", "notSpecial@hbo.com", "Brooklyn 9213 NYC", "ricFlair")));
//        } catch (DolinaSreceException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//        RezervacijaDao rezervacijaDao = new RezervacijaDaoSQLImplementation();
//        Connection connection = RezervacijaDaoSQLImplementation.getConnection();
//
//        if (connection != null) System.out.println("Connection created");
//        else System.out.println("Failed to connect to DB");
//
//        try {
//            System.out.println(rezervacijaDao.getById(1));
//            System.out.println(rezervacijaDao.getAll());
//        } catch (DolinaSreceException e) {
//            System.out.println(e.getMessage());
//        }

//        KucicaDao kucicaDao = new KucicaDaoSQLImplementation();
//        Connection connection = KucicaDaoSQLImplementation.getConnection();
//
//        System.out.println("Binary: " +kucicaDao.getById(1).getSlika().getBinaryStream());

    }
}
