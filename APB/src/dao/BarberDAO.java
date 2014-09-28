package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barber;

public class BarberDAO
{

	private static BarberDAO instance;

	private BarberDAO()
	{
		// Construtor padrão
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static BarberDAO getInstance ()
	{
		if(instance == null)
		{
			instance = new BarberDAO();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}
	
	
	/**
	 * Método que armazena no banco de dados um dado barbeiro.
	 * Checa se o argumento passado é nulo, retornando 'false' se sim.
	 * @param barber  - Barber class instance to access the class
	 */
	public boolean includeBarber (Barber barber) throws SQLException
	{
		if (barber == null)
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery("INSERT INTO "
				+ "barbeiro (nome, cpf, rg, telefone, cadeira) VALUES ("
				+ "\"" + barber.getBarberName() + "\", " + "\""
				+ barber.getBarberCpf() + "\", " + "\"" + barber.getBarberRg()
				+ "\", " + "\"" + barber.getBarberTelephone() + "\", " + "\""
				+ barber.getBarberChair() + "\"); ");

		return true;
	}
	
	
	/**
	 * Método que altera os dados de um barbeiro cadastrado no banco de dados.
	 * @param barberName Receives the barber name
	 * @param changedBarber Barber class instance of a changed barber to access the class
 	 * @param barber Barber class instance of a changed barber to access the 
	 */
	public boolean modifyBarber (String barberName, Barber changedBarber, Barber barber) throws SQLException
	{
		if (changedBarber == null || barber == null)
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery("UPDATE barbeiro SET nome = '"
				+ changedBarber.getBarberName() + "', " + "cpf = '"
				+ changedBarber.getBarberCpf() + "', " + "rg = '"
				+ changedBarber.getBarberRg() + "', " + "telefone = '"
				+ changedBarber.getBarberTelephone() + "', " + "cadeira = '"
				+ changedBarber.getBarberChair() + "' WHERE" + " cpf = '"
				+ barberName + "';");

		return true;
	}
	
	
	/**
	 * Método que exclui um dado barbeiro do banco de dados.
	 * Checa se o argumento passado é nulo, retornando 'false' se sim.
	 * @param barber Barber class instance of a changed barber to access the 
	 */
	public boolean deleteBarber (Barber barber) throws SQLException
	{
		if (barber == null)
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery("DELETE FROM barbeiro WHERE "
				+ "barbeiro.nome = \"" + barber.getBarberName() + "\";");
		
		return true;
	}
	

	// Realiza uma pesquisa no banco de dados.
	public ResultSet searchBarber() throws SQLException
	{
		// Connection interface's instance to connect with the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// PreparedStatement class's instance to query the database
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM barbeiro;");
		
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = preparedStatement.executeQuery();

		return queryForBarber;
	}
	
	/** 
	 * Realiza uma atualização do banco de dados.
	 * @param message receives a messagem to the update
	 */
	public void updateQuery(String message) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	// Método que mostra os barbeiros cadastrados no banco de dados.
	public ResultSet showRegisteredBarber(Barber barber) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = connection.createStatement().executeQuery(
				"Select nome, cpf, rg, telefone, cadeira from barbeiro;");
		
		return queryForBarber;
	}
	
	/* 
	 * Método que pesquisa por um dado barbeiro no banco de dados.
	 */
	public ResultSet searchByName(Barber barber) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// PreparedStatement class's instance to query the database
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM barbeiro WHERE nome = '" 
							+ barber.getBarberName() + "';");
		
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = preparedStatement.executeQuery();

		return queryForBarber;
	} 

}
