package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	

	    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	    private static final String USER = "c##wada"; 
	    private static final String PASSWORD = "2110";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}


