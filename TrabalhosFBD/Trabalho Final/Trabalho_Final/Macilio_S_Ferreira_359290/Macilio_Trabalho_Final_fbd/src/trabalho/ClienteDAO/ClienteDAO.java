package trabalho.ClienteDAO;

import java.sql.Date;
import java.util.List;

import trabalho.Cliente.Cliente;
import trabalho.Cliente.Filme;
import trabalho.Cliente.Locacoes;


public interface ClienteDAO {
   
	public void inserir (Cliente cliente); // Inseri um novo Cliente
	
	public void atualizar (Cliente cliente, int id); // Atualiza um Cliente
	
	public void deletar (int id);  // Deleta um Cliente
	
	public List <Filme> filmes_disponiveis();  // Mostrar filmes disponivéis.
	
	public List <Cliente> clientes_alocacoes();      // Mostra cliente que mais alugou filme.
	
	public List <Cliente> list_cliente();     // Mostra todos os clientes do banco
	
	public List <Filme> filmes_mais_alugados();         // Mostra filme mais alugados.	
	
	public List <Filme> filme_por_categoria (String categoria);    // Mostra filmes por uma determinada categoria.
	
	public List<Locacoes> list_locacao(); // Mostra todas as Locacoes
	
	public List <Filme> filmes_por_ano(int ano);     // Filmes mais antigos
	
	public Locacoes inserir_locacao (Locacoes l);   // Inseri uma nova Locação	
		
}
