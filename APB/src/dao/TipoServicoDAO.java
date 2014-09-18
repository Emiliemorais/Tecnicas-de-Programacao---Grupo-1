package dao;

// Importando das bases
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando dados do modelo da classe Tipo de Servico
import model.ServiceType;

public class TipoServicoDAO 
{

	private static TipoServicoDAO instance;

	private TipoServicoDAO () 
	{
	}

	public static TipoServicoDAO getInstance () 
	{
		if (instance == null)
			instance = new TipoServicoDAO ();
		return instance;
	}

	public boolean incluir ( ServiceType tipoServico ) throws SQLException 
	{
		if ( tipoServico == null )
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "tiposervico (nome, preco) VALUES ("
				+ "\"" + tipoServico.getServiceTypeName() + "\", " + "\""
				+ tipoServico.getServiceTypePrice() + "\"); ");

		return true;
	}

	public boolean alterar ( String nome, ServiceType tipoServicoAlterado, ServiceType tipoServico ) throws SQLException 
	{
		if ( tipoServicoAlterado == null || tipoServico == null ) 
			return false;
		/*
		 * Retorna falso para o metodo caso a variavel tipoServicoAlterado
		 * ou a variavel tipoServico sejam nulas
		 */
		
		this.updateQuery ("UPDATE tiposervico SET nome = '"
				+ tipoServicoAlterado.getServiceTypeName () + "', " + "preco = '"
				+ tipoServicoAlterado.getServiceTypePrice ()  + "' WHERE"
				+ " nome = '" + nome + "';");

		return true;
		/*
		 * Retorna verdadeiro para o metodo caso a variavel tipoServicoAlterado
		 * ou a variavel tipoServico NAO sejam nulas  e ja tenha feito todo
		 * o procedimento do "this.updateQuery"
		 */
	}// Fim do metodo alterar

	// Metodo que retorna true caso o tipo de servico contenha alguma informacao
	public boolean excluir ( ServiceType tipoServico ) throws SQLException 
	{
		if ( tipoServico == null )
			return false;
		
		this.updateQuery ( "DELETE FROM tiposervico WHERE "
				+ "tipoServico.nome = \"" + tipoServico.getServiceTypeName() + "\";");
		return true;
	}// Fim do metodo excluir

	/* Metodo que permite executar as mudancas dos dados, sendo eles:
	 *Connection connection 
	 *PreparedStatement preparedStatement
	 */
	public void updateQuery ( String message ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}// Fim do metodo updateQuery
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam mostrados
	public ResultSet mostrarTipoServicoCadastrados ( ServiceType servico ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery ( 
				"SELECT * FROM tiposervico;" );
		
		return rs;
	}//Fim do metodo Result mostrarTipoServicoCadastrados
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por nome
	public ResultSet pesquisarPorNome ( ServiceType servico ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement ( "SELECT * FROM tiposervico WHERE "
				+ "nome = '" + servico.getServiceTypeName() + "';" );
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}// Fim do metodo  ResultSet pesquisarPorNome

}// Fim da Classe TipoServicoDAO
