package trabalho.ClienteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
    public static Connection getConnection () throws SQLException {
	
    	try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/trabalho_final", "postgres", "macilio");
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
    }
}
