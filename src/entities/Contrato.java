package entities;

import java.util.Date;

public class Contrato {
    private int numeroContrato;
    private Date dataAdmissao;
    private Long inss;

    public Contrato(int numeroContrato, Date dataAdmissao, Long inss) {
        this.numeroContrato = numeroContrato;
        this.dataAdmissao = dataAdmissao;
        this.inss = inss;
    }

	public int getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(int numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Long getInss() {
		return inss;
	}

	public void setInss(Long inss) {
		this.inss = inss;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Contrato:\n");
	    sb.append("Número do Contrato: ").append(numeroContrato).append("\n");
	    sb.append("Data de Admissão: ").append(dataAdmissao).append("\n");
	    sb.append("INSS: ").append(inss).append("\n");
	    
	    return sb.toString();
	}
}