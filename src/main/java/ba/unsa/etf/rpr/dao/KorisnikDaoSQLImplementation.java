package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Korisnik;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class KorisnikDaoSQLImplementation extends AbstractDao<Korisnik> implements KorisnikDao {
    public static KorisnikDaoSQLImplementation instance = null;

    public KorisnikDaoSQLImplementation() {
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

    @Override
    public Korisnik prepareItem(Korisnik item, int id) {
        item.setId(id);
        return item;
    }
}
