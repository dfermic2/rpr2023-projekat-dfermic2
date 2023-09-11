package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of KucicaDao
 *
 * @author Dijana Fermic
 */
public class KucicaDaoSQLImplementation extends AbstractDao<Kucica> implements KucicaDao {

    private static KucicaDaoSQLImplementation instance = null;

    private KucicaDaoSQLImplementation() {
        super("kucice");
    }

    public static KucicaDaoSQLImplementation getInstance() {
        if (instance == null)
            instance = new KucicaDaoSQLImplementation();
        return instance;
    }

    public static void removeInstance() {
        if (instance != null)
            instance = null;
    }

    @Override
    public Kucica row2object(ResultSet rs) throws DolinaSreceException {
        try {
            Kucica kucica = new Kucica();
            kucica.setId(rs.getInt("id"));
            kucica.setIme(rs.getString("ime"));
            kucica.setCijena(rs.getBigDecimal("cijena"));
            kucica.setJacuzzi(rs.getBoolean("jacuzzi"));
            kucica.setSlika(rs.getBlob("slika").getBinaryStream().readAllBytes());
            return kucica;
        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Object> object2row(Kucica object) {
        Map<String, Object> row = new TreeMap<>();
        row.put("id", object.getId());
        row.put("ime", object.getIme());
        row.put("cijena", object.getCijena());
        row.put("jacuzzi", object.isJacuzzi());
        row.put("slika", object.getSlika());
        return row;
    }

    @Override
    public Kucica prepareItem(Kucica item, int id) {
        item.setId(id);
        return item;
    }


}
