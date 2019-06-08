package trabalho.Principal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import trabalho.Cliente.Cliente;
import trabalho.Cliente.Filme;
import trabalho.Cliente.Locacoes;
import trabalho.ClienteDAO.ClienteDAO;
import trabalho.ClienteJDBCDAO.ClienteJDBC;

public class Principal {
	
	public static void main(String[] args) {
		char op;
		int sair = 0;
		Filme filme = new Filme();
	    ClienteDAO cliente = new ClienteJDBC();
		
		String menu =  "1-> Inserir Cliente. "
				+ " \n2 -> Atualizar Cliente por ID."
				+ " \n3 -> Deletar Cliente."
				+ " \n4 -> Consultar Filmes mais Alugados. "
				+ " \n5 -> Clientes que mais Alugaram Filmes."
				+ " \n6 -> Filmes Disponíveis. "
				+ " \n7 -> Buscar filmes por Categoria."
				+ " \n8 -> Buscar filmes por Ano."
				+ " \n9 -> Inserir Alocação."
				+ " \nL -> Visualizar todas as Alocações. Digite 'l'."
				+ " \nC -> Para ver os Clientes da Locadora. Digite 'c'."
				+ " \nS -> Para Sair. Digite 's'.";
		
		do {
		op = JOptionPane.showInputDialog(menu).charAt(0);	
		switch (op) {
		case '1':
			Cliente c = new Cliente();
			int _id_up = Integer.valueOf(JOptionPane.showInputDialog("Id do Cliente a se Inserir"));
			String _nome = JOptionPane.showInputDialog("Insira o Nome.", c.getNome());
			String _cpf = String.valueOf(JOptionPane.showInputDialog("Insira o  Cpf.", c.getCpf()));
			String _endereco = JOptionPane.showInputDialog("Insira o Endereco.", c.getEndereco());
			Date data_Nasc = Date.valueOf(JOptionPane.showInputDialog("Insira a Data de Nascimento.", c.getDataNasc()));
			
			c.setId(_id_up);
			c.setNome(_nome);
			c.setCpf(_cpf);
			c.setEndereco(_endereco);
			c.setDataNasc(data_Nasc);
			cliente.inserir(c);
			listaCliente(cliente.list_cliente());
			break;
		
		case '2':
			Cliente novo = new Cliente();
			int id_up = Integer.valueOf(JOptionPane.showInputDialog("Qual o id do Cliente que deseja Atualizar?"));
			String nome = JOptionPane.showInputDialog("Insira o Nome.", novo.getNome());
			String cpf = String.valueOf(JOptionPane.showInputDialog("Insira o  Cpf.", novo.getCpf()));
			String endereco = JOptionPane.showInputDialog("Insira o Endereco.", novo.getEndereco());
			Date dataNasc = Date.valueOf(JOptionPane.showInputDialog("Insira a Data de Nascimento.", novo.getDataNasc()));
			
			novo.setId(id_up);
			novo.setNome(nome);
			novo.setCpf(cpf);
			novo.setEndereco(endereco);
			novo.setDataNasc(dataNasc);
			cliente.atualizar(novo, id_up);
			listaCliente(cliente.list_cliente());
			break;
		
		case '3':
			int id = Integer.valueOf(JOptionPane.showInputDialog("Id do cliente que deseja Deletar."));
			cliente.deletar(id);
			listaCliente(cliente.list_cliente());
			break;
			
		case '4':
			listaFilme(cliente.filmes_mais_alugados());
			break;
		
		case '5':
			listaCliente(cliente.clientes_alocacoes());			
			break;
			
		case '6':
			listaFilme(cliente.filmes_disponiveis());
			break;
	
		case '7':
			String categoria = JOptionPane.showInputDialog("Insira a Categoria!");
			listaFilme(cliente.filme_por_categoria(categoria));
			break;
		
		case '8': 
			
			int ano = Integer.valueOf(JOptionPane.showInputDialog("Insira por qual ano quer pesquisar!!"));
			 listaFilme(cliente.filmes_por_ano(ano));
			break;
				
		case '9':			
			Locacoes l = new Locacoes();
			int id_loc1 = Integer.valueOf(JOptionPane.showInputDialog("Insira o Id do Cliente!"));
			int id_loc2 = Integer.valueOf(JOptionPane.showInputDialog("Insira o Id do Filme!"));
			Date emprestimo = Date.valueOf(JOptionPane.showInputDialog("Data de Emprestimo!"));
			Date devolucao = Date.valueOf(JOptionPane.showInputDialog("Data de Devoluçao!"));
			double valor = Double.valueOf(JOptionPane.showInputDialog("Valor Da Alocação!"));
			
			l.setId_cliente(id_loc1);
			l.setId_filme(id_loc2);
			l.setData_emprestimo(emprestimo);
			l.setData_devolucao(devolucao);
			l.setValor(valor);
			cliente.inserir_locacao(l);
			list_locacoes(cliente.list_locacao());
			break;

		case 'l':
			list_locacoes(cliente.list_locacao());
			break;	
			
		case 'c':
			listaCliente(cliente.list_cliente());
			break;
		
		case 's':
			sair = 1;
			break;
		
		default:
			JOptionPane.showInputDialog("Opção não suportada!!");
			break;
		}	
		} while (sair != 1);
	}
	
	public static void list_locacoes(List<Locacoes> l) {
        StringBuilder list = new StringBuilder();
        for(Locacoes _l : l) {
        list.append(_l).append("\n");
    }
        JOptionPane.showMessageDialog(null, list.length() == 0 ? "Não foi possível encontrar o Filme!!" : list);
    }
	
	public static void listaFilme(List<Filme> filme) {
        StringBuilder list = new StringBuilder();
        for(Filme f : filme) {
        list.append(f).append("\n");
    }
        JOptionPane.showMessageDialog(null, list.length() == 0 ? "Não foi possível encontrar o Filme!!" : list);
    }
	
	public static void listaCliente(List<Cliente> cliente) {
        StringBuilder list = new StringBuilder();
        for(Cliente c : cliente) {
        list.append(c).append("\n");
    }
        JOptionPane.showMessageDialog(null, list.length() == 0 ? "Não foi possível encontrar o Filme!!" : list);
    }
	
	public static void lista_cliente(Cliente f) {
    	JOptionPane.showMessageDialog(null, f == null ? "Não foi possível encontrar o Filme!!" : f);
    }
}
