package trabalho.Cliente;

import java.sql.Date;

public class Cliente {
   
	  private int id;
	  private String cpf;
	  private String nome;
	  private String endereco;
	  private Date dataNasc;
	  
	public Cliente() { }

	public Cliente(int id, String cpf, String nome, String endereco, Date dataNasc) {
		this.id = id;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return "Cliente [id = " + id + ", cpf = " + cpf + ", nome = " + nome
				+ ", endereco = " + endereco + ", dataNasc = " + dataNasc + "]";
	}
		
	
}
