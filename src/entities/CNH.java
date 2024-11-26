package entities;

import java.util.Date;

public class CNH {
    private String categoria;
    private String tipoSanguineo;
    private String fatorRH;
    private Date primeraHabilitacao;
    private Date validade;
    private Long registro;

    // Construtor
    public CNH(String categoria, String tipoSanguineo, String fatorRH,
               Date primeraHabilitacao, Date validade, Long registro) {
        this.categoria = categoria;
        this.tipoSanguineo = tipoSanguineo;
        this.fatorRH = fatorRH;
        this.primeraHabilitacao = primeraHabilitacao;
        this.validade = validade;
        this.registro = registro;
    }

   

    public CNH() {
		// TODO Auto-generated constructor stub
	}







	public String getCategoria() {
		return categoria;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public String getTipoSanguineo() {
		return tipoSanguineo;
	}



	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}



	public String getFatorRH() {
		return fatorRH;
	}



	public void setFatorRH(String fatorRH) {
		this.fatorRH = fatorRH;
	}



	public Date getPrimeraHabilitacao() {
		return primeraHabilitacao;
	}



	public void setPrimeraHabilitacao(Date primeraHabilitacao) {
		this.primeraHabilitacao = primeraHabilitacao;
	}



	public Date getValidade() {
		return validade;
	}



	public void setValidade(Date validade) {
		this.validade = validade;
	}



	public Long getRegistro() {
		return registro;
	}



	public void setRegistro(Long registro) {
		this.registro = registro;
	}



	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Categoria: ").append(categoria).append("\n");
        sb.append("Tipo Sanguíneo: ").append(tipoSanguineo).append("\n");
        sb.append("Fator RH: ").append(fatorRH).append("\n");
        sb.append("Primeira Habilitação: ").append(primeraHabilitacao).append("\n");
        sb.append("Validade: ").append(validade).append("\n");
        sb.append("Registro: ").append(registro).append("\n");
        
        return sb.toString();
    }
}