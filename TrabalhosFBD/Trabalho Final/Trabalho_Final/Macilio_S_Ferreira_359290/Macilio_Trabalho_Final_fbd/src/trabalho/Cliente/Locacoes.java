package trabalho.Cliente;

import java.sql.Date;
import java.text.DecimalFormat;

public class Locacoes {
      private int id_cliente;
      private int id_filme;
      private Date data_emprestimo;
      private Date data_devolucao;
      private double valor;
      
      
      
      public Locacoes() { }
      
	  public Locacoes(int id_cliente, int id_filme, Date data_emprestimo,	Date data_devolucao, double valor) {
		this.id_cliente = id_cliente;
		this.id_filme = id_filme;
		this.data_emprestimo = data_emprestimo;
		this.data_devolucao = data_devolucao;
		this.valor = valor;
      }

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_filme() {
		return id_filme;
	}

	public void setId_filme(int id_filme) {
		this.id_filme = id_filme;
	}

	public Date getData_emprestimo() {
		return data_emprestimo;
	}

	public void setData_emprestimo(Date data_emprestimo) {
		this.data_emprestimo = data_emprestimo;
	}

	public Date getData_devolucao() {
		return data_devolucao;
	}

	public void setData_devolucao(Date data_devolucao) {
		this.data_devolucao = data_devolucao;
	}


	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	
	@Override
	public String toString() {
		return "Locacao [Cliente = " + id_cliente + ", Filme = " + id_filme
				+ ", Data de Empréstimo = " + data_emprestimo + ", Data de Devolucao = "
				+ data_devolucao + " Valor = "+ valor + "]";
	}
      
      
      
      
}
