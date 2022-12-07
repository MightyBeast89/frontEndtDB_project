package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnection {

	private static final Logger logger = Logger.getLogger(dbConnection.class.getName());
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DB_USER = "COMP214_PROJECT";
    private static final String DB_PASSWORD = "1234";
    
    private dbConnection() {
            
    }
    
    public static Connection getDBConnection() throws SQLException {
            Connection connection = null;

            try {
                    Class.forName(DB_DRIVER);
            } catch (ClassNotFoundException exception) {
                    logger.log(Level.SEVERE, exception.getMessage());
            }

            try {
                    connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                    return connection;
            } catch (SQLException exception) {
                    logger.log(Level.SEVERE, exception.getMessage());
            }

            return connection;
    }
}
