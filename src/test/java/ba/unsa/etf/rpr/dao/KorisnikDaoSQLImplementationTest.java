package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

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

    @Test
    public void testObject2row() {
        KorisnikDaoSQLImplementation korisnikDaoSQLImplementation = (KorisnikDaoSQLImplementation) DaoFactory.korisnikDao();
        Korisnik korisnik = new Korisnik("Mujo", "Mujic", "mujo@mujo.com", "Mu Town 99", "mujo123");

        Map<String, Object> row = new TreeMap<>();
        row.put("id", korisnik.getId());
        row.put("ime", korisnik.getIme());
        row.put("prezime", korisnik.getPrezime());
        row.put("email", korisnik.getEmail());
        row.put("adresa", korisnik.getAdresa());
        row.put("password", korisnik.getPassword());
        korisnik.setId(1);

        assertEquals(korisnikDaoSQLImplementation.object2row(korisnik), row);

    }

    public void testPrepareItem() {
    }
}