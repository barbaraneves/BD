package trabalho.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import trabalho.ClienteDAO.ConnectionFactory;
import trabalho.DAOException.DAOException;

public class Filme {
	private int id;
    private String titulo;
    private int ano_publicacao;
	
    
    public Filme() {}


	public Filme(int id, String titulo, int ano_publicacao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.ano_publicacao = ano_publicacao;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getAno_publicacao() {
		return ano_publicacao;
	}


	public void setAno_publicacao(int ano_publicacao) {
		this.ano_publicacao = ano_publicacao;
	}


	@Override
	public String toString() {
		return "Filme [Id = " + id + ", Titulo = " + titulo + ", Ano_publicacao = "
				+ ano_publicacao + "]";
	}
    
    
    
}
