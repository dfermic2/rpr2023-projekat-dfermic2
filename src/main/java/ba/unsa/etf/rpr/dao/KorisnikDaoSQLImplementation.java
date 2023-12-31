package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of KorisnikDao
 *
 * @author Dijana Fermic
 */
public class KorisnikDaoSQLImplementation extends AbstractDao<Korisnik> implements KorisnikDao {
    private static KorisnikDaoSQLImplementation instance = null;

    private KorisnikDaoSQLImplementation() {
        super("korisnici");
    }

    public static KorisnikDaoSQLImplementation getInstance() {
        if (instance == null)
            instance = new KorisnikDaoSQLImplementation();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Korisnik getByEmail(String email) throws DolinaSreceException {
        String sql = "SELECT * FROM korisnici WHERE email = '" + email + "'";
        try {
            Connection connection = KorisnikDaoSQLImplementation.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) return row2object(resultSet);
            else return null;
        } catch (Exception e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }

    @Override
    public Korisnik row2object(ResultSet rs) throws DolinaSreceException {
        try {
            Korisnik korisnik = new Korisnik();
            korisnik.setId(rs.getInt("id"));
            korisnik.setIme(rs.getString("ime"));
            korisnik.setPrezime(rs.getString("prezime"));
            korisnik.setEmail(rs.getString("email"));
            korisnik.setAdresa(rs.getString("adresa"));
            korisnik.setPassword(rs.getString("password"));
            return korisnik;
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

    @Override
    public Korisnik prepareItem(Korisnik item, int id) {
        item.setId(id);
        return item;
    }
}
