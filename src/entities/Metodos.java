package entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Metodos implements CRUD {
    private Connection connection;

    public Metodos() {
        this.connection = connect(); 
    }

    private Connection connect() {
        try {
            
            String url = "jdbc:oracle:thin:@localhost:1521:xe"; // URL do banco de dados
            String user = "c##wada"; // Usuário do banco de dados
            String password = "2110"; // Senha do banco de dados
            
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
    public void Create(Funcionario funcionario) {
   
    	 
        Endereco endereco = funcionario.getEndereco(); 
        CNH cnh = funcionario.getCnh(); 

        
        Create(funcionario, endereco, cnh);
    }
    public void Create(Funcionario funcionario, Endereco endereco, CNH cnh) {
        try {
            
            String sqlFuncionario = "INSERT INTO Funcionarios (codigo_funcionario, nome, rg, numero_dependentes, estado_civil, nacionalidade, nome_pai, nome_mae, cpf) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmtFuncionario = connection.prepareStatement(sqlFuncionario);
            stmtFuncionario.setString(1, funcionario.getCodigoFuncionario());
            stmtFuncionario.setString(2, funcionario.getNomeCompleto());
            stmtFuncionario.setLong(3, funcionario.getRg());
            stmtFuncionario.setInt(4, funcionario.getNumeroDependentes());
            stmtFuncionario.setString(5, funcionario.getEstadoCivil());
            stmtFuncionario.setString(6, "brasileiro"); // Nacionalidade padrão
            stmtFuncionario.setString(7, funcionario.getNomePai());
            stmtFuncionario.setString(8, funcionario.getNomeMae());
            stmtFuncionario.setString(9, funcionario.getCpf());
            stmtFuncionario.executeUpdate();

           
            if (endereco != null) {
                String sqlEndereco = "INSERT INTO Enderecos (cep, logradouro, numero, cidade, uf, bairro, codigo_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtEndereco = connection.prepareStatement(sqlEndereco);
                stmtEndereco.setLong(1, endereco.getCep());
                stmtEndereco.setString(2, endereco.getLogradouro());
                stmtEndereco.setInt(3, endereco.getNumero());
                stmtEndereco.setString(4, endereco.getCidade());
                stmtEndereco.setString(5, endereco.getUf());
                stmtEndereco.setString(6, endereco.getBairro());
                stmtEndereco.setString(7, funcionario.getCodigoFuncionario());
                stmtEndereco.executeUpdate();
            }

           
            if (cnh != null) {
                String sqlCnh = "INSERT INTO Cnh (categoria, tipo_sanguineo, fator_rh, primeira_habilitacao, validade, registro, codigo_funcionario) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtCnh = connection.prepareStatement(sqlCnh);
                stmtCnh.setString(1, cnh.getCategoria());
                stmtCnh.setString(2, cnh.getTipoSanguineo());
                stmtCnh.setString(3, cnh.getFatorRH());
                stmtCnh.setDate(4, new java.sql.Date(cnh.getPrimeraHabilitacao().getTime()));
                stmtCnh.setDate(5, new java.sql.Date(cnh.getValidade().getTime()));
                stmtCnh.setLong(6, cnh.getRegistro());
                stmtCnh.setString(7, funcionario.getCodigoFuncionario());
                stmtCnh.executeUpdate();
            }

            
            String sqlUpdateFuncionario = "UPDATE Funcionarios SET cep = ?, registro = ? WHERE codigo_funcionario = ?";
            PreparedStatement stmtUpdateFuncionario = connection.prepareStatement(sqlUpdateFuncionario);
            stmtUpdateFuncionario.setLong(1, endereco.getCep());  
            stmtUpdateFuncionario.setLong(2, cnh.getRegistro());  
            stmtUpdateFuncionario.setString(3, funcionario.getCodigoFuncionario()); 
            stmtUpdateFuncionario.executeUpdate();

            
            if (funcionario instanceof Motoristas) {
                Motoristas motorista = (Motoristas) funcionario; 
                String sqlMotorista = "INSERT INTO Motoristas (salario, cpf, nome, registro, cep, codigo_funcionario) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtMotorista = connection.prepareStatement(sqlMotorista);
                stmtMotorista.setDouble(1, motorista.getSalario());
                stmtMotorista.setString(2, funcionario.getCpf());
                stmtMotorista.setString(3, funcionario.getNomeCompleto());
                stmtMotorista.setLong(4, cnh.getRegistro());
                stmtMotorista.setLong(5, endereco.getCep());
                stmtMotorista.setString(6, funcionario.getCodigoFuncionario());
                stmtMotorista.executeUpdate();
            }
            
            else if (funcionario instanceof Ajudantes) {
                Ajudantes ajudante = (Ajudantes) funcionario; 
                String sqlAjudante = "INSERT INTO Ajudantes (salario, cpf, nome, registro, cep, codigo_funcionario) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmtAjudante = connection.prepareStatement(sqlAjudante);
                stmtAjudante.setDouble(1, ajudante.getSalario());
                stmtAjudante.setString(2, funcionario.getCpf());
                stmtAjudante.setString(3, funcionario.getNomeCompleto());
                stmtAjudante.setLong(4, cnh.getRegistro());
                stmtAjudante.setLong(5, endereco.getCep());
                stmtAjudante.setString(6, funcionario.getCodigoFuncionario());
                stmtAjudante.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Erro ao adicionar funcionário: " + "Caracter inválido.");
        } finally {
            closeConnection(); 
        }
    }







    public Funcionario Read(String codigoFuncionario) {
        String sqlFuncionario = "SELECT * FROM Funcionarios WHERE codigo_funcionario = ?";
        Funcionario funcionario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sqlFuncionario)) {
            stmt.setString(1, codigoFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                boolean isMotorista = verificarSeMotorista(codigoFuncionario); 
                
                if (isMotorista) {
                    
                    funcionario = new Motoristas(
                        rs.getString("codigo_funcionario"),
                        rs.getString("nome"),
                        rs.getLong("rg"),
                        rs.getInt("numero_dependentes"),
                        rs.getString("estado_civil"),
                        null, 
                        null, 
                        null, 
                        rs.getString("nacionalidade"),
                        rs.getString("nome_mae"),
                        rs.getString("nome_pai"),
                        rs.getString("cpf")
                    );

                    
                    CNH cnh = ReadCnh(codigoFuncionario);
                    if (cnh != null) {
                        ((Motoristas) funcionario).setCnh(cnh); // Cast para Motoristas e preenche CNH
                    }

                } else {
                    // Se não for motorista, cria um objeto Funcionario normal
                    funcionario = new Funcionario(
                        rs.getString("codigo_funcionario"),
                        rs.getString("nome"),
                        rs.getLong("rg"),
                        rs.getInt("numero_dependentes"),
                        rs.getString("estado_civil"),
                        null, // CNH
                        null, // Dependentes
                        null, // Endereço
                        rs.getString("nacionalidade"),
                        rs.getString("nome_mae"),
                        rs.getString("nome_pai"),
                        rs.getString("cpf")
                    );
                }

                // Preenche o endereço
                Endereco endereco = ReadEndereco(codigoFuncionario);
                funcionario.setEndereco(endereco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }

        return funcionario;
    }
   
    public List<Funcionario> ReadAll() {
        String sqlFuncionario = "SELECT * FROM Funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sqlFuncionario)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario funcionario;

                // Verifica se o funcionário é um motorista
                boolean isMotorista = verificarSeMotorista(rs.getString("codigo_funcionario"));

                if (isMotorista) {
                    // Se for motorista, cria um objeto Motoristas
                    funcionario = new Motoristas(
                        rs.getString("codigo_funcionario"),
                        rs.getString("nome"),
                        rs.getLong("rg"),
                        rs.getInt("numero_dependentes"),
                        rs.getString("estado_civil"),
                        null, 
                        null, 
                        null, 
                        rs.getString("nacionalidade"),
                        rs.getString("nome_mae"),
                        rs.getString("nome_pai"),
                        rs.getString("cpf")
                    );

                    // Lê a CNH do motorista
                    CNH cnh = ReadCnh(rs.getString("codigo_funcionario"));
                    ((Motoristas) funcionario).setCnh(cnh); // Atribui CNH ao motorista

                } else {
                    // Se não for motorista, cria um objeto Ajudante
                    funcionario = new Ajudantes(
                        rs.getString("codigo_funcionario"),
                        rs.getString("nome"),
                        rs.getLong("rg"),
                        rs.getInt("numero_dependentes"),
                        rs.getString("estado_civil"),
                        null, 
                        null, 
                        null, 
                        rs.getString("nacionalidade"),
                        rs.getString("nome_mae"), 
                        rs.getString("nome_pai"),
                        rs.getString("cpf"));
                }

                
                Endereco endereco = ReadEndereco(rs.getString("codigo_funcionario"));
                funcionario.setEndereco(endereco); 

                
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    public void Update(Funcionario funcionario) {
        String sql = "UPDATE Funcionarios SET nome = ?, rg = ?, numero_dependentes = ?, estado_civil = ?, "
                + "nacionalidade = ?, nome_pai = ?, nome_mae = ?, cpf = ? WHERE codigo_funcionario = ?";
     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
         stmt.setString(1, funcionario.getNomeCompleto());
         stmt.setLong(2, funcionario.getRg());
         stmt.setInt(3, funcionario.getNumeroDependentes());
         stmt.setString(4, funcionario.getEstadoCivil());
         stmt.setString(5, funcionario.getNacionalidade());
         stmt.setString(6, funcionario.getNomePai());
         stmt.setString(7, funcionario.getNomeMae());
         stmt.setString(8, funcionario.getCpf());
         stmt.setString(9, funcionario.getCodigoFuncionario());

         int rowsUpdated = stmt.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Dados do funcionário atualizados com sucesso.");
         }
     } catch (SQLException e) {
         System.out.println("Erro ao atualizar dados do funcionário: " + e.getMessage());
     }
 }

 public void UpdateEndereco(Endereco endereco, String codigoFuncionario) {
     String sql = "UPDATE Enderecos SET cep = ?, logradouro = ?, numero = ?, cidade = ?, uf = ?, bairro = ? "
                + "WHERE codigo_funcionario = ?";
     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
         stmt.setLong(1, endereco.getCep());
         stmt.setString(2, endereco.getLogradouro());
         stmt.setInt(3, endereco.getNumero());
         stmt.setString(4, endereco.getCidade());
         stmt.setString(5, endereco.getUf());
         stmt.setString(6, endereco.getBairro());
         stmt.setString(7, codigoFuncionario);

         int rowsUpdated = stmt.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Endereço atualizado com sucesso.");
         }
     } catch (SQLException e) {
         System.out.println("Erro ao atualizar o endereço: " + e.getMessage());
     }
 }

 public void UpdateCnh(CNH cnh, String codigoFuncionario) {
     String sql = "UPDATE Cnh SET categoria = ?, tipo_sanguineo = ?, fator_rh = ?, "
                + "primeira_habilitacao = ?, validade = ? WHERE codigo_funcionario = ?";
     try (PreparedStatement stmt = connection.prepareStatement(sql)) {
         stmt.setString(1, cnh.getCategoria());
         stmt.setString(2, cnh.getTipoSanguineo());
         stmt.setString(3, cnh.getFatorRH());
         stmt.setDate(4, new java.sql.Date(cnh.getPrimeraHabilitacao().getTime()));
         stmt.setDate(5, new java.sql.Date(cnh.getValidade().getTime()));
         stmt.setString(6, codigoFuncionario);

         int rowsUpdated = stmt.executeUpdate();
         if (rowsUpdated > 0) {
             System.out.println("Dados da CNH atualizados com sucesso.");
         }
     } catch (SQLException e) {
         System.out.println("Erro ao atualizar dados da CNH: " + e.getMessage());
     }
 }


    public void Delete(String codigo) {
        String sql = "DELETE FROM Funcionarios WHERE codigo_funcionario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionário: " + e.getMessage());
        }
    }
    
    public Motoristas ReadMotorista(String codigoFuncionario) {
        String sql = "SELECT * FROM Motoristas WHERE codigo_funcionario = ?";
        Motoristas motorista = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motorista = new Motoristas(
                    rs.getString("codigo_funcionario"),
                    null,  
                    null,  
                    0,    
                    null, 
                    null,  
                    null, 
                    null,  
                    null,  
                    null,  
                    null, 
                    null  
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler motorista: " + e.getMessage());
        }
        return motorista;
    }
    public Endereco ReadEndereco(String codigoFuncionario) {
        String sql = "SELECT * FROM Enderecos WHERE codigo_funcionario = ?";
        Endereco endereco = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                endereco = new Endereco(
                    rs.getString("logradouro"),
                    rs.getLong("cep"),
                    rs.getInt("numero"),
                    rs.getString("bairro"),
                    rs.getString("cidade"),
                    rs.getString("uf")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao ler endereço: " + e.getMessage());
        }
        return endereco;
    }
    public CNH ReadCnh(String codigoFuncionario) {
        String sql = "SELECT * FROM Cnh WHERE codigo_funcionario = ?";
        CNH cnh = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cnh = new CNH(
                    rs.getString("categoria"),             
                    rs.getString("tipo_sanguineo"),        
                    rs.getString("fator_rh"),              
                    rs.getDate("primeira_habilitacao"),    
                    rs.getDate("validade"),                
                    rs.getLong("registro")                 
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar CNH: " + e.getMessage());
        }

        return cnh;
    }
    public boolean verificarSeMotorista(String codigoFuncionario) {
        String sql = "SELECT COUNT(*) FROM Motoristas WHERE codigo_funcionario = ?";
        boolean isMotorista = false;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, codigoFuncionario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                isMotorista = count > 0; // Se o número de registros for maior que 0, o funcionário é um motorista
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar se o funcionário é motorista: " + e.getMessage());
        }

        return isMotorista;
    }

}
