package entities;

public class Endereco {
    private String logradouro;
    private Long cep;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(String logradouro, Long cep, int numero,
                    String bairro, String cidade, String uf) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }



	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Endereco:\n");
	    sb.append("Logradouro: ").append(logradouro).append("\n");
	    sb.append("CEP: ").append(cep).append("\n");
	    sb.append("Número: ").append(numero).append("\n");
	    sb.append("Bairro: ").append(bairro).append("\n");
	    sb.append("Cidade: ").append(cidade).append("\n");
	    sb.append("UF: ").append(uf).append("\n");
	    
	    return sb.toString();
	}
}