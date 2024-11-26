package testeconexao;

import conexao.ConexaoBD;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TesteConexaoBD {
    @Test
    public void testConnection() {
        try (Connection connection = ConexaoBD.getConnection()) {
            assertNotNull(connection, "A conexão não deve ser nula");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private void assertNotNull(Connection connection, String string) {
		// TODO Auto-generated method stub
		
	}
}
