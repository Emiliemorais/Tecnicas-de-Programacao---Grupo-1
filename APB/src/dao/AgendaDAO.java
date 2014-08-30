package dao;
// Importando os dados
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;
import dao.FactoryConnection;

// Inicio da classe AgendaDAO
public class AgendaDAO 
{

	private static AgendaDAO instance;

	// Inicio do metodo AgendaDAO
	private AgendaDAO() 
	{
	}// Fim do metodo AgendaDAO

	// Metodo da AgendaDAO para instaciar a variavel caso seja nula
	public static AgendaDAO getInstance() 
	{
		if (instance == null)
			instance = new AgendaDAO();
		return instance;
	}// Fim do metodo AgendaDAO getInstance

	// Metodo incluir para incluir um dado na agenda
	public boolean incluir(Agenda agenda) throws SQLException 
	{
		if (agenda == null)
			return false;
		
		this.updateQuery("INSERT INTO "
				+ "agenda (nome, telefone, descricao) VALUES (" + "\""
				+ agenda.getNome() + "\", " + "\"" + agenda.getTelefone()
				+ "\", " + "\"" + agenda.getDescricao() + "\"); ");
		return true;
	}// Fim do metodo incluir

	// Metodo para aferir as informacoes do banco
	public boolean alterar(String nome, Agenda agenda_alterado, Agenda agenda) throws SQLException 
	{	
		if(agenda == null || agenda_alterado == null)
			return false;
		
		this.updateQuery("UPDATE agenda SET " +
				"nome = \"" + agenda_alterado.getNome() + "\", " +
				"telefone = \"" + agenda_alterado.getTelefone() + "\", "+
				"descricao = \"" + agenda_alterado.getDescricao() + "\""+
				" WHERE " +
				" agenda.nome = \"" + nome + "\";");
			
		return true;
	}// Fim do metodo alterar

	public boolean excluir(Agenda contato) throws SQLException 
	{
		if(contato ==  null)
			return false;
		
		this.updateQuery("DELETE FROM agenda WHERE " + "agenda.telefone = \""
				+ contato.getTelefone() + "\";");
		return true;
	}// Fim do metodo excluir

	// Metodo para atribuir valores as variaveis de acordo com o banco
	public void updateQuery(String message) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}// Fim do metodo 
	
	// Metodo para atribuir valores as variaveis de acordo com o banco
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
				"Select * from agenda;");
		
		return rs;
	}// Fim do metodo 
	
	// Metodo para atribuir valores as variaveis de acordo com o banco
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "nome = '" + contato.getNome()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}// Fim do metodo 
	
	// Metodo para atribuir valores as variaveis de acordo com o banco
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		java.sql.PreparedStatement pst = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "telefone = '" + contato.getTelefone()+ "';");
		ResultSet rs = pst.executeQuery();

		return rs;
	}// Fim do metodo 

}
// Fim da classe