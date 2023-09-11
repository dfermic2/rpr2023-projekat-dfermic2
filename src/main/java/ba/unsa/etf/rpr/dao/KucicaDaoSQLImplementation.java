package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Kucica;
import ba.unsa.etf.rpr.exceptions.DolinaSreceException;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

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
            int id = rs.getInt("id");
            String ime = rs.getString("ime");
            BigDecimal cijena = rs.getBigDecimal("cijena");
            boolean jacuzzi = rs.getBoolean("jacuzzi");
            byte[] slika = rs.getBlob("slika").getBinaryStream().readAllBytes();

            return new Kucica(id, ime, cijena, jacuzzi, slika);
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
