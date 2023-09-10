package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

            return new Rezervacija(id, idKorisnik, idKucica, pocetak, kraj, cijena);
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
        row.put("pocetak", object.getPocetak());
        row.put("kraj", object.getKraj());
        row.put("cijana", object.getCijena());
        return row;
    }

    @Override
    public Rezervacija prepareItem(Rezervacija item, int id) {
        item.setId(id);
        return item;
    }

    @Override
    public List<Rezervacija> getBetweenDates(Date date) {
        return null;
    }
}
