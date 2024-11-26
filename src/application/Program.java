package application;

import entities.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    private static Metodos metodos = new Metodos(); // Usar a lógica de CRUD que interage com o banco de dados
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            try {
                switch (opcao) {
                    case 1:
                        adicionarFuncionario();
                        break;
                    case 2:
                        listarFuncionarios();
                        break;
                    case 3:
                        atualizarFuncionario();
                        break;
                    case 4:
                        deletarFuncionario();
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
            } catch (ParseException e) {
                System.out.println("Erro ao processar data: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Um erro inesperado ocorreu: " + e.getMessage());
            }
        } while (opcao != 5);
    }

    private static void mostrarMenu() {
        System.out.println("Menu:");
        System.out.println("1. Adicionar Funcionário");
        System.out.println("2. Listar Funcionários");
        System.out.println("3. Atualizar Funcionário");
        System.out.println("4. Deletar Funcionário");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarFuncionario() throws SQLException, ParseException {
        // Coleta de informações da CNH
        System.out.print("Categoria da CNH: ");
        String categoriaCNH = scanner.nextLine();

        System.out.print("Tipo Sanguíneo: ");
        String tipoSanguineo = scanner.nextLine();

        System.out.print("Fator RH: ");
        String fatorRH = scanner.nextLine();

        System.out.print("Primeira Habilitação (dd/MM/yyyy): ");
        Date primeraHabilitacao = sdf.parse(scanner.nextLine());

        System.out.print("Validade (dd/MM/yyyy): ");
        Date validade = sdf.parse(scanner.nextLine());

        System.out.print("Registro: ");
        Long registroCNH = scanner.nextLong();
        scanner.nextLine(); // Consumir a nova linha

        // Criar a CNH usando o construtor com todos os argumentos
        CNH cnh = new CNH(categoriaCNH, tipoSanguineo, fatorRH, primeraHabilitacao, validade, registroCNH);

        // Coleta de informações do endereço
        System.out.print("Logradouro: ");
        String logradouro = scanner.nextLine();

        System.out.print("CEP: ");
        Long cep = scanner.nextLong();
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("UF: ");
        String uf = scanner.nextLine();

        // Criar o Endereço usando o construtor com todos os argumentos
        Endereco endereco = new Endereco(logradouro, cep, numero, bairro, cidade, uf);

        // Coleta de informações básicas do funcionário
        System.out.print("Código do Funcionário: ");
        String codigoFuncionario = scanner.nextLine();

        System.out.print("Nome Completo: ");
        String nomeCompleto = scanner.nextLine();

        System.out.print("RG: ");
        Long rg = scanner.nextLong();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Número de Dependentes: ");
        int numeroDependentes = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Estado Civil: ");
        String estadoCivil = scanner.nextLine();

        System.out.print("Nome da Mãe: ");
        String nomeMae = scanner.nextLine();

        System.out.print("Nome do Pai: ");
        String nomePai = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine(); // Pode ser tratado como String se precisar de formatação

        // Escolhe o tipo de funcionário
        System.out.print("Tipo de Funcionário (1 para Motorista, 2 para Ajudante): ");
        int tipoFuncionario = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Funcionario funcionario;
        
        // Criar o funcionário usando o construtor com todos os argumentos
        if (tipoFuncionario == 1) {
            funcionario = new Motoristas(codigoFuncionario, nomeCompleto, rg, numeroDependentes, estadoCivil, cnh, null, endereco, "Brasileiro(a)", nomeMae, nomePai, cpf);
        } else {
            funcionario = new Ajudantes(codigoFuncionario, nomeCompleto, rg, numeroDependentes, estadoCivil, cnh, null, endereco, "Brasileiro(a)", nomeMae, nomePai, cpf);
        }

        // Chama o método Create para adicionar ao banco de dados
        metodos.Create(funcionario);
        System.out.println("Funcionário adicionado com sucesso!");
    }



    private static void listarFuncionarios() throws SQLException {
        System.out.println("Listando Funcionários:");
        for (Funcionario f : metodos.ReadAll()) {
            System.out.println(f.toString());
        }
    }

    private static void atualizarFuncionario() throws SQLException, ParseException {
        System.out.print("Código do Funcionário a ser atualizado: ");
        String codigo = scanner.nextLine();
        Funcionario funcionario = metodos.Read(codigo); // Lê o funcionário do banco de dados
        if (funcionario != null) {
            atualizarDadosFuncionario(funcionario);
            metodos.Update(funcionario);
            System.out.println("Dados atualizados com sucesso.");
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    private static void atualizarDadosFuncionario(Funcionario funcionario) throws ParseException {
        System.out.println("Qual tabela deseja atualizar? (FUNCIONARIOS, ENDERECOS, CNH): ");
        String tabela = scanner.nextLine();

        switch (tabela.toUpperCase()) {
            case "FUNCIONARIOS":
                atualizarTabelaFuncionario(funcionario);
                break;

            case "ENDERECOS":
                if (funcionario.getEndereco() != null) {
                    atualizarTabelaEndereco(funcionario.getEndereco(), funcionario.getCodigoFuncionario());
                } else {
                    System.out.println("Endereço não cadastrado.");
                }
                break;

            case "CNH":
                if (funcionario.getCnh() != null) {
                    atualizarTabelaCnh(funcionario.getCnh(), funcionario.getCodigoFuncionario());
                } else {
                    System.out.println("CNH não cadastrada.");
                }
                break;

            default:
                System.out.println("Tabela de atualização não implementada.");
        }
    }

    // Atualiza todos os dados de `Funcionarios`
    private static void atualizarTabelaFuncionario(Funcionario funcionario) {
        System.out.println("Digite o nome completo atualizado: ");
        funcionario.setNomeCompleto(scanner.nextLine());

        System.out.println("Digite o novo RG: ");
        funcionario.setRg(scanner.nextLong());
        scanner.nextLine(); // Consumir o newline deixado pelo nextLong()

        System.out.println("Digite o número de dependentes: ");
        funcionario.setNumeroDependentes(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Digite o estado civil: ");
        funcionario.setEstadoCivil(scanner.nextLine());

        System.out.println("Digite a nacionalidade: ");
        funcionario.setNacionalidade(scanner.nextLine());

        System.out.println("Digite o nome do pai: ");
        funcionario.setNomePai(scanner.nextLine());

        System.out.println("Digite o nome da mãe: ");
        funcionario.setNomeMae(scanner.nextLine());

        System.out.println("Digite o CPF: ");
        funcionario.setCpf(scanner.nextLine());

        
        // Chama o método update para `Funcionarios`
        metodos.Update(funcionario);
    }

    // Atualiza todos os dados de `Enderecos`
    private static void atualizarTabelaEndereco(Endereco endereco, String codigoFuncionario) {
        System.out.println("Digite o CEP atualizado: ");
        endereco.setCep(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Digite o logradouro atualizado: ");
        endereco.setLogradouro(scanner.nextLine());

        System.out.println("Digite o número atualizado: ");
        endereco.setNumero(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Digite a cidade atualizada: ");
        endereco.setCidade(scanner.nextLine());

        System.out.println("Digite a UF atualizada: ");
        endereco.setUf(scanner.nextLine());

        System.out.println("Digite o bairro atualizado: ");
        endereco.setBairro(scanner.nextLine());

        // Chama o método update para `Enderecos`
       metodos.UpdateEndereco(endereco, codigoFuncionario);
        
    }

    // Atualiza todos os dados de `CNH`
    private static void atualizarTabelaCnh(CNH cnh, String codigoFuncionario) {
        System.out.println("Digite a categoria atualizada: ");
        cnh.setCategoria(scanner.nextLine());

        System.out.println("Digite o tipo sanguíneo atualizado: ");
        cnh.setTipoSanguineo(scanner.nextLine());

        System.out.println("Digite o fator RH atualizado: ");
        cnh.setFatorRH(scanner.nextLine());

        System.out.println("Digite a data da primeira habilitação (dd/MM/yyyy): ");
       
      	Date primeiraHabilitacao;
		try {
			primeiraHabilitacao = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        
        cnh.setPrimeraHabilitacao(primeiraHabilitacao);
  
    
        System.out.println("Digite a data de validade (dd/MM/yyyy): ");
        Date validade = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
        cnh.setValidade(validade);

       metodos.UpdateCnh(cnh, codigoFuncionario);
        
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }


    private static void deletarFuncionario() throws SQLException {
        System.out.print("Código do Funcionário a ser deletado: ");
        String codigo = scanner.nextLine();

        Funcionario funcionario = metodos.Read(codigo); // Lê o funcionário do banco de dados
        if (funcionario != null) {
            System.out.print("Tem certeza que deseja deletar o funcionário? (S/N): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("S")) {
                metodos.Delete(codigo); // Chama o método Delete no banco de dados
                System.out.println("Funcionário deletado com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }
    private static CNH coletarDadosCnh() throws ParseException {
        System.out.print("Categoria da CNH: ");
        String categoriaCNH = scanner.nextLine();
        System.out.print("Tipo Sanguíneo: ");
        String tipoSanguineo = scanner.nextLine();
        System.out.print("Fator RH: ");
        String fatorRH = scanner.nextLine();
        System.out.print("Primeira Habilitação (dd/MM/yyyy): ");
        Date primeraHabilitacao = sdf.parse(scanner.nextLine());
        System.out.print("Validade (dd/MM/yyyy): ");
        Date validade = sdf.parse(scanner.nextLine());
        System.out.print("Registro: ");
        Long registroCNH = scanner.nextLong();
        scanner.nextLine(); // Consumir a nova linha

        return new CNH(categoriaCNH, tipoSanguineo, fatorRH, primeraHabilitacao, validade, registroCNH);
    }

}
