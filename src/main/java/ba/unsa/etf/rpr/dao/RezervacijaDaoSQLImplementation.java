package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Rezervacija;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RezervacijaDaoSQLImplementation extends AbstractDao<Rezervacija> implements RezervacijaDao {
    public RezervacijaDaoSQLImplementation(String tableName) {
        super(tableName);
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
}
