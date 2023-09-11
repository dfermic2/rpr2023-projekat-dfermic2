package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KorisnikDaoSQLImplementationTest {


    @Test
    public void testRow2object() throws SQLException, DolinaSreceException {

        Korisnik korisnik = new Korisnik("Mujo", "Mujic", "mujo@mujo.com", "Mu Town 99", "mujo123");
        korisnik.setId(1);

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("ime")).thenReturn("Mujo");
        when(resultSet.getString("prezime")).thenReturn("Mujic");
        when(resultSet.getString("email")).thenReturn("mujo@mujo.com");
        when(resultSet.getString("adresa")).thenReturn("Mu Town 99");
        when(resultSet.getString("password")).thenReturn("mujo123");

        KorisnikDaoSQLImplementation korisnikDaoSQLImplementation = (KorisnikDaoSQLImplementation) DaoFactory.korisnikDao();

        assertEquals(korisnikDaoSQLImplementation.row2object(resultSet), korisnik);
    }

    public void testObject2row() {


    }

    public void testPrepareItem() {
    }
}