package trabalho.ClienteJDBCDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import trabalho.Cliente.Cliente;
import trabalho.Cliente.Filme;
import trabalho.Cliente.Locacoes;
import trabalho.ClienteDAO.ClienteDAO;
import trabalho.ClienteDAO.ConnectionFactory;
import trabalho.DAOException.DAOException;

public class ClienteJDBC implements ClienteDAO{

	@Override
	public void inserir(Cliente cliente) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String tipo = ("Qual o tipo do Cliente? "
					+ "\n1 - Vip. "
					+ "\n2 - Normal");
			char _tipo = JOptionPane.showInputDialog(tipo).charAt(0);
			switch (_tipo) {
			case '1':
				String insert = "insert into cliente values (?, ?, ?, ?, ?)";
				PreparedStatement str = con.prepareStatement(insert);
				str.setInt(1, cliente.getId());
				str.setString(2, cliente.getCpf());
				str.setString(3, cliente.getNome());
				str.setDate(4, cliente.getDataNasc());
				str.setString(5, cliente.getEndereco());
				str.executeUpdate();
				insert_cliente_vip(cliente.getId());
				break;
				
			case '2':
				String _insert = "insert into cliente values (?, ?, ?, ?, ?)";
				PreparedStatement _str = con.prepareStatement(_insert);
				_str.setInt(1, cliente.getId());
				_str.setString(2, cliente.getCpf());
				_str.setString(3, cliente.getNome());
				_str.setDate(4, cliente.getDataNasc());
				_str.setString(5, cliente.getEndereco());
				_str.executeUpdate();
				insert_cliente_normal(cliente.getId());
                break;
                
			default:
				JOptionPane.showInputDialog("Tipo de Cliente não Suportado!!");
				break;
			}

		} catch (SQLException e) {
			throw new DAOException("Não foi permitido inserir esse Cliente!!", e);
		}finally{
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("A Conexão não pode ser Encerrada.", e);
			}
		}
		
	}

	@Override
	public void atualizar(Cliente cliente, int id) { // ageitar a parte de verificar se o cliente existe..
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String update = "update cliente set nome = ?, cpf = ?, endereco = ?, dataNasc = ? where id = ? ";
			PreparedStatement up = con.prepareStatement(update);
			up.setString(1, cliente.getNome());
			up.setString(2, cliente.getCpf());
			up.setString(3, cliente.getEndereco());
			up.setDate(4, cliente.getDataNasc());
			up.setInt(5, id);
			up.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Não foi possível Atualizar!!", e);
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Conexão não Encerrada!!", e);
			}
		}
		
	}
	
	public void insert_cliente_vip(int id){
		Connection con = null;
		int qtd_semanas = 2;
		try {
			con = ConnectionFactory.getConnection();
			String insert = "insert into cliente_vip values (?, ?)";
			PreparedStatement str = con.prepareStatement(insert);
			str.setInt(1, id);
			str.setInt(2, qtd_semanas);
			str.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Não foi possível inserir cliente!!");
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Impossível encerrar Conexão.", e);
			}
		}
	}
	
	public void insert_cliente_normal(int id){
		Connection con = null;
		int qtd_dias = 7;
		try {
			con = ConnectionFactory.getConnection();
			String insert = "insert into cliente_normal values (?, ?)";
			PreparedStatement str = con.prepareStatement(insert);
			str.setInt(1, id);
			str.setInt(2, qtd_dias);
			str.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException("Não foi possível inserir cliente!!");
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Impossível encerrar Conexão.", e);
			}
		}
	}

	@Override
	public void deletar(int id) {
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
				String deleta = "delete from cliente where id = ?";
				PreparedStatement str = con.prepareStatement(deleta);
				str.setInt(1, id);
				str.executeUpdate();
			} catch (SQLException e) {
			throw new DAOException("Não foi possível deletar!!", e);
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Conexão não encerrada!!", e);
			}
		}
		
	}
	
	
	@Override
	public List<Filme> filmes_disponiveis() {
		Connection conexao = null;
		List result = null;
		try {
			conexao = ConnectionFactory.getConnection();
			String disponiveis = "select distinct f.id, f.titulo, f.ano_publicacao "
					+ "from filme as f "
					+ "where (f.id) not in (select distinct id_filme from locacoes) "
					+ "order by id, titulo asc ";
			PreparedStatement str = conexao.prepareStatement(disponiveis);
			ResultSet pesq = str.executeQuery();
			result = new ArrayList<Filme>();
			while(pesq.next()){
				Filme f = imprime_filme(pesq);
				result.add(f);
			}
		} catch (SQLException e) {
			throw new DAOException("Não foi possível buscar filmes disponíveis!!",e);
		}finally {
			try {
				if(conexao != null )
					conexao.close();
			} catch (SQLException e) {
				throw new DAOException("Conexão não encerrada!!", e);				
			}
		}
		return result;
	}
	
	public List <Filme> filmes_mais_alugados() {   // Pode ser uma lista de filmes
		Connection conexao = null;
		List result = null;
		try {
			conexao = ConnectionFactory.getConnection(); 
			String filmes = "select id, titulo, ano_publicacao "
					+ "from filme join alugados on (titulo = nome and (numero_vezes) between 3 and (select count (id_filme) from locacoes)) order by id"
					+ "order by numero_vezes desc";
		    PreparedStatement str = conexao.prepareStatement(filmes);
		    ResultSet pes = str.executeQuery();
		    result = new ArrayList<Filme>();
            while(pes.next()){
            	Filme filme = imprime_filme(pes);
            	result.add(filme);
            }
		} catch (SQLException e) {
			throw new DAOException("Não Foi Possível Mostrar Filmes!!", e);
		}finally {
			try {
				if(conexao != null)
					conexao.close();
			} catch (SQLException e) {
				throw new DAOException("Conexão não Encerrada!!", e);
			}
		}
		return result;
	}
	
	
	public Filme imprime_filme(ResultSet rs) throws SQLException{
		Filme f = new Filme();
		f.setId(rs.getInt("id"));
		f.setTitulo(rs.getString("titulo"));
		f.setAno_publicacao(rs.getInt("ano_publicacao"));	
		return f;
	}


	@Override
	public List <Cliente> clientes_alocacoes() {
		Connection con = null;
		 List result = null;
		try {
			con = ConnectionFactory.getConnection();
			String busca = "select id, nome, cpf, endereco, dataNasc "
					+ "from cliente, locacoes "
					+ "where id = id_cliente group by id,nome having (count(id_filme)) >= 3 "
					+ "order by id asc";
			PreparedStatement str = con.prepareStatement(busca);
			ResultSet pesq = str.executeQuery();
			result = new ArrayList<Cliente>();
			while (pesq.next()){
				Cliente c = insert(pesq);
				result.add(c);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Cliente não encontrado!!", e);
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Impossível encerrar Conexão!!", e);
			}
		}
		
		return result;	
	}

	@Override
	public List<Filme> filme_por_categoria(String categoria) {
		Connection con = null;
		List result = null;
		
		try {
			con = ConnectionFactory.getConnection();
			String buscat = "select f.id, titulo, f.ano_publicacao "
					+ "from filme as f, categoria as c "
					+ "where f.id_categoria = c.id and (c.nome) ilike ? order by id";
			PreparedStatement str = con.prepareStatement(buscat);
			str.setString(1, categoria);
			ResultSet cons = str.executeQuery();
			result = new ArrayList<Filme>();
			while (cons.next()) {
				Filme f = imprime_filme(cons);
				result.add(f);
			}
		} catch (SQLException e) {
			throw new DAOException("Não foi possível mostra filmes pela categoria dada!!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível encerrar a conexão!!", e);
			}
		}
		
		return result;
	
	}

	@Override
	public List <Filme> filmes_por_ano(int ano) {
		Connection con = null;
		List result = null;
		try {
			con = ConnectionFactory.getConnection();
			String antigo = "select id, titulo, ano_publicacao "
					+ "from filme "
					+ "where ano_publicacao = ? "
					+ "order by (ano_publicacao) asc" ;
			PreparedStatement str = con.prepareStatement(antigo);
			str.setInt(1, ano);
			ResultSet pesq = str.executeQuery();
			result = new ArrayList<Filme>();
			while (pesq.next()) {
				Filme filme = imprime_filme(pesq);
				result.add(filme);
			}
		} catch (SQLException e) {
			throw new DAOException("Filme não encontrado!", e);
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
              throw new DAOException("Não foi Possível encerrar Conexão!", e);
			}
		}		
       	return result;
	}

	@Override
	public Locacoes inserir_locacao(Locacoes l) {
        Connection con = null;
        Locacoes alugou = new Locacoes();
        try {
			con = ConnectionFactory.getConnection();
			String insert = "insert into locacoes values (?, ?, ?, ?, ?)";
			PreparedStatement str = con.prepareStatement(insert);
			str.setInt(1, l.getId_cliente());
			str.setInt(2, l.getId_filme());
			str.setDate(3, l.getData_emprestimo());
			str.setDate(4, l.getData_devolucao());
			str.setDouble(5, l.getValor());
			str.executeUpdate();	
        } catch (SQLException e) {
			throw new DAOException("Impossível Inserir!!", e);
		}finally{
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("Não foi possível encerrar Conexão!!");
			}
		}
        
		return alugou;
	}

	@Override
	public List<Cliente> list_cliente() {
		Connection con = null;
		List result = null;
		try {
			con = ConnectionFactory.getConnection();
			String todos = "select id, nome, cpf, endereco, dataNasc from cliente order by id";
			PreparedStatement str = con.prepareStatement(todos);
		    ResultSet rs = str.executeQuery();
		    result = new ArrayList<Cliente>();
		    while (rs.next()) {
		    	Cliente cliente = insert(rs);  
				result.add(cliente);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Não foi possível Mostrar Clientes!!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("A conexão não pode ser Encerrada!!", e);
			}
		}
		return result;
	}
	
	public static Cliente insert(ResultSet rs) throws SQLException{
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setDataNasc(rs.getDate("dataNasc"));
		return cliente;
	}
	
	public List<Locacoes> list_locacao() {
		Connection con = null;
		List result = null;
		try {
			con = ConnectionFactory.getConnection();
			String todos = "select id_cliente, id_filme, data_emprestimo, data_devolucao, valor_pago from locacoes order by id_cliente";
			PreparedStatement str = con.prepareStatement(todos);
		    ResultSet rs = str.executeQuery();
		    result = new ArrayList<Locacoes>();
		    while (rs.next()) {
		    	Locacoes aluguel = _insert(rs);  
				result.add(aluguel);
			}
			
		} catch (SQLException e) {
			throw new DAOException("Não foi possível Mostrar Clientes!!", e);
		}finally {
			try {
				if(con != null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException("A conexão não pode ser Encerrada!!", e);
			}
		}
		return result;
	}
	
	public static Locacoes _insert(ResultSet rs) throws SQLException{
		Locacoes l = new Locacoes();
		l.setId_cliente(rs.getInt("id_cliente"));
		l.setId_filme(rs.getInt("id_filme"));
		l.setData_emprestimo(rs.getDate("data_emprestimo"));
		l.setData_devolucao(rs.getDate("data_devolucao"));
		l.setValor(rs.getDouble("valor_pago"));
		return l;
	}
	
}
