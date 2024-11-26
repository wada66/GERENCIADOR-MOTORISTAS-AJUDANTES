package entities;

import java.util.List;

public class Ajudantes extends Funcionario {
    private static final double SALARIO = 9000; // Salário fixo para ajudantes



    public Ajudantes(String codigoFuncionario, String nomeCompleto, Long rg, int numeroDependentes, String estadoCivil,
			CNH cnh, List<Dependentes> dependentes, Endereco endereco, String nacionalidade, String nomeMae,
			String nomePai, String cpf) {
		super(codigoFuncionario, nomeCompleto, rg, numeroDependentes, estadoCivil, cnh, dependentes, endereco, nacionalidade,
				nomeMae, nomePai, cpf);
		this.cnh = cnh;
		this.endereco = endereco;
	}

	public double getSalario() {
        return SALARIO;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()); 
        sb.append("Tipo de Funcionário: Ajudante\n");
        sb.append("Salário: ").append(SALARIO).append("\n");
        
        return sb.toString();
    }
}