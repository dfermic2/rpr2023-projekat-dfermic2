package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 *
 * @author Dijana Fermic
 */
public abstract class AbstractDao<T> implements Dao<T> {
    private static Connection connection = null;
    private final String tableName;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection() {
        if (AbstractDao.connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(ClassLoader.getSystemResource("application.properties").openStream());
                String url = properties.getProperty("db.connection_string");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                AbstractDao.connection = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public static Connection getConnection() {
        return AbstractDao.connection;
    }

    public T getById(int id) throws DolinaSreceException {
        String sql = "SELECT * FROM " + tableName + " WHERE  id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) return row2object(resultSet);
            else return null;

        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws DolinaSreceException {
        String sql = "SELECT * FROM " + tableName;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<T> results = new ArrayList<>();

            while (resultSet.next()) {
                results.add(row2object(resultSet));
            }

            return results;
        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }

    @Override
    public T add(T item) throws DolinaSreceException {
        Map<String, Object> row = object2row(item);
        String sql = "INSERT INTO " + tableName + prepareColumnNames(row) + " VALUES " + prepareValues(row);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                if (entry.getKey().equals("id")) continue;
                preparedStatement.setObject(counter, entry.getValue());
                counter++;
            }

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return prepareItem(item, resultSet.getInt(1));

        } catch (SQLException e) {
            throw new DolinaSreceException(e.getMessage(), e);
        }
    }

    public abstract T row2object(ResultSet rs) throws DolinaSreceException;

    public abstract Map<String, Object> object2row(T object);

    public abstract T prepareItem(T item, int id);

    private static String prepareColumnNames(Map<String, Object> row) {
        String columnNames = row.keySet().stream().filter(o -> !Objects.equals(o, "id")).collect(Collectors.joining(","));
        return "(" + columnNames + ")";
    }

    private static String prepareValues(Map<String, Object> row) {
        String values = row.entrySet().stream().filter(o -> !Objects.equals(o.getKey(), "id")).map(o -> "?").collect(Collectors.joining(","));
        return "(" + values + ")";
    }
}
