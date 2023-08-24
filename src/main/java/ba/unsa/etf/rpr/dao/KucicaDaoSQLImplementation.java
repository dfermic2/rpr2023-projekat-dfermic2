package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KucicaDaoSQLImplementation extends AbstractDao<Kucica> implements KucicaDao {

    public KucicaDaoSQLImplementation() {
        super("kucice");
    }

    @Override
    public Kucica row2object(ResultSet rs) throws DolinaSreceException {
        try {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            BigDecimal cijena = rs.getBigDecimal("cijena");
            boolean jacuzzi = rs.getBoolean("jacuzzi");
            Blob slika = rs.getBlob("slika");

            return new Kucica(id, ime, cijena, jacuzzi, slika);
        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }
}
