
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// h. JDBC Configuration 
class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/db_djp";
    private static final String USER = "root";
    private static final String PASS = ""; 

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}