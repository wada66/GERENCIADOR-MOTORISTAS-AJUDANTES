package entities;

import java.util.ArrayList;
import java.util.List;

public class Funcionario {
    private String codigoFuncionario;
    private String nomeCompleto;
    private Long rg;
    private int numeroDependentes;
    private String estadoCivil;
    protected CNH cnh;  
    private List<Dependentes> dependentes; 
    protected Endereco endereco;
    private String nacionalidade;
    private String nomeMae;
    private String nomePai;
    private String cpf;

    public Funcionario(String codigoFuncionario, String nomeCompleto, Long rg,
                       int numeroDependentes, String estadoCivil, CNH cnh,
                       List<Dependentes> dependentes, Endereco endereco,
                       String nacionalidade, String nomeMae, String nomePai, String cpf) {
        this.codigoFuncionario = codigoFuncionario;
        this.nomeCompleto = nomeCompleto;
        this.rg = rg;
        this.numeroDependentes = numeroDependentes;
        this.estadoCivil = estadoCivil;
        this.cnh = cnh;
        this.endereco = endereco;
        this.nacionalidade = nacionalidade;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.cpf = cpf;
        this.dependentes = new ArrayList<>(); //alterado devido ao NullPointerException
        
        if (numeroDependentes > 0) {
            this.dependentes = dependentes != null ? dependentes : new ArrayList<>();
        } else {
            this.dependentes = null;
        }
    }

   

    public Funcionario() {
		// TODO Auto-generated constructor stub
	}



	public String getCodigoFuncionario() {
		return codigoFuncionario;
	}



	public void setCodigoFuncionario(String codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}



	public String getNomeCompleto() {
		return nomeCompleto;
	}



	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}



	public Long getRg() {
		return rg;
	}



	public void setRg(Long rg) {
		this.rg = rg;
	}



	public int getNumeroDependentes() {
		return numeroDependentes;
	}



	public void setNumeroDependentes(int numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}



	public String getEstadoCivil() {
		return estadoCivil;
	}



	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}



	public CNH getCnh() {
		return cnh;
	}



	public void setCnh(CNH cnh) {
		this.cnh = cnh;
	}



	public List<Dependentes> getDependentes() {
		return dependentes;
	}



	public void setDependentes(List<Dependentes> dependentes) {
		this.dependentes = dependentes;
	}



	public Endereco getEndereco() {
		return endereco;
	}



	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}



	public String getNacionalidade() {
		return nacionalidade;
	}



	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}



	public String getNomeMae() {
		return nomeMae;
	}



	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}



	public String getNomePai() {
		return nomePai;
	}


	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public void addDependente(Dependentes dependente) {
        if (dependentes == null) {
            dependentes = new ArrayList<>();
        }
        dependentes.add(dependente);
        numeroDependentes++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario:\n");
        sb.append("Código do Funcionário: ").append(codigoFuncionario).append("\n");
        sb.append("Nome Completo: ").append(nomeCompleto).append("\n");
        sb.append("RG: ").append(rg).append("\n");
        sb.append("Número de Dependentes: ").append(numeroDependentes).append("\n");
        sb.append("Estado Civil: ").append(estadoCivil).append("\n");
        sb.append("Nacionalidade: ").append(nacionalidade).append("\n");
        sb.append("Nome da Mãe: ").append(nomeMae).append("\n");
        sb.append("Nome do Pai: ").append(nomePai).append("\n");

        
        // Adiciona os dependentes
        if (dependentes != null && !dependentes.isEmpty()) {
            sb.append("Dependentes:\n");
            for (Dependentes d : dependentes) {
                sb.append(d.toString()).append("\n");
            }
        } else {
            sb.append("Nenhum dependente registrado.\n");
        }
        
        return sb.toString();
    }
}