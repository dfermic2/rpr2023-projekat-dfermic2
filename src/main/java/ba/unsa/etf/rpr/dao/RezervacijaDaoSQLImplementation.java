package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class RezervacijaDaoSQLImplementation extends AbstractDao<Rezervacija> implements RezervacijaDao {
    public static RezervacijaDaoSQLImplementation instance = null;

    public RezervacijaDaoSQLImplementation() {
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
            int id = rs.getInt("id");
            int idKorisnik = rs.getInt("id_korisnik");
            int idKucica = rs.getInt("id_kucica");
            Date pocetak = rs.getDate("pocetak");
            Date kraj = rs.getDate("kraj");
            BigDecimal cijena = rs.getBigDecimal("cijena");

            return new Rezervacija(id, idKorisnik, idKucica, pocetak.toLocalDate(), kraj.toLocalDate(), cijena);
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