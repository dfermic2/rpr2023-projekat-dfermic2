package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * MySQL implementation of RezervacijaDao
 *
 * @author Dijana Fermic
 */
public class RezervacijaDaoSQLImplementation extends AbstractDao<Rezervacija> implements RezervacijaDao {
    private static RezervacijaDaoSQLImplementation instance = null;

    private RezervacijaDaoSQLImplementation() {
        super("rezervacije");
    }

    public static RezervacijaDaoSQLImplementation getInstance() {
        if (instance == null)
            instance = new RezervacijaDaoSQLImplementation();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Rezervacija row2object(ResultSet rs) throws DolinaSreceException {
        try {
            Rezervacija rezervacija = new Rezervacija();
            rezervacija.setId(rs.getInt("id"));
            rezervacija.setIdKorisnik(rs.getInt("id_korisnik"));
            rezervacija.setIdKucica(rs.getInt("id_kucica"));
            rezervacija.setPocetak(rs.getDate("pocetak").toLocalDate());
            rezervacija.setKraj(rs.getDate("kraj").toLocalDate());
            rezervacija.setCijena(rs.getBigDecimal("cijena"));

            return rezervacija;
        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Rezervacija object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("id_korisnik", object.getIdKorisnik());
        row.put("id_kucica", object.getIdKucica());
        row.put("pocetak", Date.valueOf(object.getPocetak()));
        row.put("kraj", Date.valueOf(object.getKraj()));
        row.put("cijena", object.getCijena());
        return row;
    }

    @Override
    public Rezervacija prepareItem(Rezervacija item, int id) {
        item.setId(id);
        return item;
    }

    @Override
    public Set<Integer> getBetweenDates(LocalDate date) {
        String sql = "SELECT id_kucica FROM rezervacije WHERE ? BETWEEN pocetak AND kraj";
        Set<Integer> kuciceId = new HashSet<>();

        try {
            Connection connection = RezervacijaDaoSQLImplementation.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) kuciceId.add(resultSet.getInt("id_kucica"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kuciceId;
    }
}