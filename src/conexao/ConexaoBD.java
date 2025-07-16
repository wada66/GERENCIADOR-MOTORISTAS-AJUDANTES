package conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	

	    private static final String URL = "jdbc:oracle:thin:@wwwwwwwww:0000:xe";
	    private static final String USER = "yyyy"; 
	    private static final String PASSWORD = "xxxx";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}


