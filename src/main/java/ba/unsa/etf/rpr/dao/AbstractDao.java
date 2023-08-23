package ba.unsa.etf.rpr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {
    private static Connection connection = null;

    public AbstractDao() {
        createConnection();
    }

    private static void createConnection() {
        if (AbstractDao.connection == null) {
            try {
                AbstractDao.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rpr_projekat?sessionVariables=WAIT_TIMEOUT=28800", "root", "root");
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

    private static Connection getConnection() {
        return AbstractDao.connection;
    }
}
