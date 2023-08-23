package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KorisnikDaoSQLImplementation extends AbstractDao<Korisnik> implements KorisnikDao {
    public KorisnikDaoSQLImplementation() {
        super("korisnici");
    }

    @Override
    public Korisnik row2object(ResultSet rs) throws DolinaSreceException {
        try {
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String adresa = rs.getString("email");
            String password = rs.getString("password");

            return new Korisnik(id, ime, prezime, email, adresa, password);
        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }
}
