package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KorisnikDaoSQLImplementationTest {

    private static final int ID = 1;
    private static Korisnik korisnik;
    private static Korisnik korisnikNoId;
    private static KorisnikDaoSQLImplementation korisnikDaoSQLImplementation;

    private static ResultSet resultSet;

    @BeforeAll
    public static void initialize() throws SQLException {
        korisnik = new Korisnik("Mujo", "Mujic", "mujo@mujo.com", "Mu Town 99", "mujo123");
        korisnik.setId(ID);

        korisnikNoId = new Korisnik("Mujo", "Mujic", "mujo@mujo.com", "Mu Town 99", "mujo123");

        resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("ime")).thenReturn("Mujo");
        when(resultSet.getString("prezime")).thenReturn("Mujic");
        when(resultSet.getString("email")).thenReturn("mujo@mujo.com");
        when(resultSet.getString("adresa")).thenReturn("Mu Town 99");
        when(resultSet.getString("password")).thenReturn("mujo123");

        korisnikDaoSQLImplementation = (KorisnikDaoSQLImplementation) DaoFactory.korisnikDao();
    }

    @Test
    public void testRow2object() throws DolinaSreceException {
        Assertions.assertEquals(korisnikDaoSQLImplementation.row2object(resultSet), korisnik);
    }

    @Test
    public void testObject2row() {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", korisnik.getId());
        row.put("ime", korisnik.getIme());
        row.put("prezime", korisnik.getPrezime());
        row.put("email", korisnik.getEmail());
        row.put("adresa", korisnik.getAdresa());
        row.put("password", korisnik.getPassword());

        Assertions.assertEquals(korisnikDaoSQLImplementation.object2row(korisnik), row);
    }

    @Test
    public void testPrepareItem() {
        Assertions.assertEquals(korisnikDaoSQLImplementation.prepareItem(korisnikNoId, ID).getId(), ID);
    }
}