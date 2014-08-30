package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barbeiro;

public class BarbeiroDAO {

	private static BarbeiroDAO instance;

	private BarbeiroDAO() {
	}

	public static BarbeiroDAO getInstance() {
		if (instance == null)
			instance = new BarbeiroDAO();
		return instance;
	}
	
	
	/*
	 * Método que armazena no banco de dados um dado barbeiro.
	 * Checa se o argumento passado é nulo, retornando 'false' se sim.
	 * Dispara exceções SQL casso ocorram.
	 */
	public boolean incluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "barbeiro (nome, cpf, rg, telefone, cadeira) VALUES ("
				+ "\"" + barbeiro.getNome() + "\", " + "\""
				+ barbeiro.getCpf() + "\", " + "\"" + barbeiro.getRg()
				+ "\", " + "\"" + barbeiro.getTelefone() + "\", " + "\""
				+ barbeiro.getCadeira() + "\"); ");

		return true;
	}
	
	
	/*
	 * Método que altera os dados de um barbeiro cadastrado no banco de dados.
	 * Checa se os argumentos passados não são nulos.
	 * Dispara exceções SQL caso ocorram. 
	 */
	public boolean alterar(String nome, Barbeiro barbeiro_alterado, Barbeiro barbeiro) throws SQLException {
		if (barbeiro_alterado == null || barbeiro == null)
			return false;
		
		this.updateQuery("UPDATE barbeiro SET nome = '"
				+ barbeiro_alterado.getNome() + "', " + "cpf = '"
				+ barbeiro_alterado.getCpf() + "', " + "rg = '"
				+ barbeiro_alterado.getRg() + "', " + "telefone = '"
				+ barbeiro_alterado.getTelefone() + "', " + "cadeira = '"
				+ barbeiro_alterado.getCadeira() + "' WHERE" + " cpf = '"
				+ nome + "';");

		return true;
	}
	
	
	/*
	 * Método que exclui um dado barbeiro do banco de dados.
	 * Checa se o argumento passado é nulo, retornando 'false' se sim.
	 * Dispara exceções SQl caso ocorram.
	 */
	public boolean excluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;
		
		this.updateQuery("DELETE FROM barbeiro WHERE "
				+ "barbeiro.nome = \"" + barbeiro.getNome() + "\";");
		return true;
	}
	
	/* 
	 * Realiza uma pesquisa no banco de dados.
	 * Dispara exceções SQl caso ocorram.
	 */
	public ResultSet pesquisar() throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM barbeiro;");
		ResultSet rs = pst.executeQuery();

		return rs;
	}
	
	/* 
	 * Realiza uma atualização do banco de dados.
	 * Dispara exceções SQl caso ocorram.
	 */
	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/* 
	 * Método que mostra os barbeiros cadastrados no banco de dados.
	 * Dispara exceções SQl caso ocorram.
	 */
	public ResultSet mostrarBarbeirosCadastrados(Barbeiro barbeiro) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select nome, cpf, rg, telefone, cadeira from barbeiro;");
		
		return rs;
	}
	
	/* 
	 * Método que pesquisa por um dado barbeiro no banco de dados.
	 * Dispara exceções SQl caso ocorram.
	 */
	public ResultSet pesquisarPorNome(Barbeiro barbeiro) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM barbeiro WHERE nome = '" 
							+ barbeiro.getNome() + "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	} 

}
