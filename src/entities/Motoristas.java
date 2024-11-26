package entities;

import java.util.List;

public class Motoristas extends Funcionario {
    private static final double SALARIO = 12000; // Salário fixo para motoristas



    public Motoristas(String codigoFuncionario, String nomeCompleto, Long rg, int numeroDependentes, String estadoCivil,
			CNH cnh, List<Dependentes> dependentes, Endereco endereco, String nacionalidade, String nomeMae,
			String nomePai, String cpf) {
		super(codigoFuncionario, nomeCompleto, rg, numeroDependentes, estadoCivil, null, dependentes, endereco, nacionalidade,
				nomeMae, nomePai, cpf);
		this.cnh = cnh;
		this.endereco = endereco;
	}
    
    

	public Motoristas() {
		// TODO Auto-generated constructor stub
	}



	public double getSalario() {
        return SALARIO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()); // Chama o toString da classe pai Funcionario
        sb.append("Tipo de Funcionário: Motorista\n");
        sb.append("Salário: ").append(SALARIO).append("\n");
        
        return sb.toString();
    }
}