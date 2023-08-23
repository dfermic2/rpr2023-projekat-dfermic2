package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.DolinaSreceException;

import java.sql.*;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDao<T> implements Dao<T> {
    private static Connection connection = null;
    private String tableName;

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
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public abstract T row2object(ResultSet rs) throws DolinaSreceException;
}
