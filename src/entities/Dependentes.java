package entities;

public class Dependentes {
    private Long cpfDependentes;
    private String nomeDependentes;
    private Long rgDependentes;

    public Dependentes(Long cpfDependentes, String nomeDependentes, Long rgDependentes) {
        this.cpfDependentes = cpfDependentes;
        this.nomeDependentes = nomeDependentes;
        this.rgDependentes = rgDependentes;
    }

	public Long getCpfDependentes() {
		return cpfDependentes;
	}

	public void setCpfDependentes(Long cpfDependentes) {
		this.cpfDependentes = cpfDependentes;
	}

	public String getNomeDependentes() {
		return nomeDependentes;
	}

	public void setNomeDependentes(String nomeDependentes) {
		this.nomeDependentes = nomeDependentes;
	}

	public Long getRgDependentes() {
		return rgDependentes;
	}

	public void setRgDependentes(Long rgDependentes) {
		this.rgDependentes = rgDependentes;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Dependentes:\n");
	    sb.append("CPF: ").append(cpfDependentes).append("\n");
	    sb.append("Nome: ").append(nomeDependentes).append("\n");
	    sb.append("RG: ").append(rgDependentes).append("\n");
	    
	    return sb.toString();
	}
}
