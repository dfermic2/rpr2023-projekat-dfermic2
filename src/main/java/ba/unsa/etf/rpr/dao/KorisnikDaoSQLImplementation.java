package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

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

    @Override
    public Map<String, Object> object2row(Korisnik object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("ime", object.getIme());
        row.put("prezime", object.getPrezime());
        row.put("email", object.getEmail());
        row.put("adresa", object.getAdresa());
        row.put("password", object.getPassword());
        return row;
    }
}
