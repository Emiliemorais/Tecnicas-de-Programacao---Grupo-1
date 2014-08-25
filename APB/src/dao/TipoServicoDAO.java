package dao;

// Importando das bases
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando dados do modelo da classe Tipo de Servico
import model.TipoServico;

public class TipoServicoDAO {

	private static TipoServicoDAO instance;

	private TipoServicoDAO() {
	}

	public static TipoServicoDAO getInstance() {
		if (instance == null)
			instance = new TipoServicoDAO();
		return instance;
	}

	public boolean incluir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + tipoServico.getNomeTipoServico() + "\", " + "\""
				+ tipoServico.getPreco() + "\"); ");

		return true;
	}

	public boolean alterar(String nome,TipoServico tipoServico_alterado, TipoServico tipoServico) throws SQLException {
		if (tipoServico_alterado == null || tipoServico == null) 
			return false;
		/*
		 * Retorna falso para o metodo caso a variavel tipoServico_alterado
		 * ou a variavel tipoServico sejam nulas
		 */
		
		this.updateQuery("UPDATE tiposervico SET nome = '"
				+ tipoServico_alterado.getNomeTipoServico() + "', " + "preco = '"
				+ tipoServico_alterado.getPreco()  + "' WHERE"
				+ " nome = '" + nome + "';");

		return true;
		/*
		 * Retorna verdadeiro para o metodo caso a variavel tipoServico_alterado
		 * ou a variavel tipoServico NAO sejam nulas  e ja tenha feito todo
		 * o procedimento do "this.updateQuery"
		 */
	}// Fim do metodo alterar

	public boolean excluir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null)
			return false;
		
		this.updateQuery("DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + tipoServico.getNomeTipoServico() + "\";");
		return true;
	}// Fim do metodo excluir

	public void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}// Fim do metodo updateQuery
	
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"SELECT * FROM tiposervico;");
		
		return rs;
	}//Fim do metodo Result mostrarTipoServicoCadastrados
	
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
				+ "nome = '" + servico.getNomeTipoServico() + "';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}// Fim do metodo  ResultSet pesquisarPorNome

}// Fim da Classe TipoServicoDAO
