package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barber;

public class BarberDAO
{

	// Receives the instance of BarberDAO's class
	private static BarberDAO instance;

	// Constructor general
	private BarberDAO()
	{
		
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static BarberDAO getInstance ()
	{
		if( instance == null )
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
	 * This method is used to save in the database a barber
	 * if the parameter is null the method returns 'false'
	 * @param barber  - Barber class instance to access the class
	 */
	public boolean includeBarber (Barber barber) throws SQLException
	{
		if( barber == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToIncludeBarber = "INSERT INTO "
										+ "barbeiro (nome, cpf, rg, telefone, cadeira) VALUES ("
										+ "\"" + barber.getBarberName() + "\", " + "\""
										+ barber.getBarberCpf() + "\", " + "\"" + barber.getBarberRg()
										+ "\", " + "\"" + barber.getBarberTelephone() + "\", " + "\""
										+ barber.getBarberChair() + "\"); " ;
		this.updateQuery(sqlCodeToIncludeBarber);

		return true;
	}
	
	
	/**
	 * This method is used to change a barber in the database 
	 * if the parameter is null the method returns 'false'
	 * @param barber Barber class instance of a changed barber to access the 
 	 * @param barberName Receives the barber name
 	 * @param changedBarber Barber class instance of a changed barber to access the class
	 */
	public boolean changeBarber (String barberName, Barber changedBarber, Barber barber) throws SQLException
	{
		if( changedBarber == null || barber == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToChangeBarber = "UPDATE barbeiro SET nome = '"
										+ changedBarber.getBarberName() + "', " + "cpf = '"
										+ changedBarber.getBarberCpf() + "', " + "rg = '"
										+ changedBarber.getBarberRg() + "', " + "telefone = '"
										+ changedBarber.getBarberTelephone() + "', " + "cadeira = '"
										+ changedBarber.getBarberChair() + "' WHERE" + " cpf = '"
										+ barberName + "';";
		this.updateQuery(sqlCodeToChangeBarber);

		return true;
	}
	
	
	/**
	 * This method is used to delete a barber in the database 
	 * if the parameter is null the method returns 'false'
	 * @param barber Barber class instance of a changed barber to access the 
	 */
	public boolean deleteBarber (Barber barber) throws SQLException
	{
		if( barber == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToDeleteBarber = "DELETE FROM barbeiro WHERE "
										+ "barbeiro.nome = \"" + barber.getBarberName() + "\";" ;
		
		this.updateQuery(sqlCodeToDeleteBarber);
		
		return true;
	}
	
	
	// This method is used to realize a search in the database
	public ResultSet searchBarber () throws SQLException
	{
		// Connection interface's instance to connect with the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// PreparedStatement class's instance to query the database
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM barbeiro;");
		
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = preparedStatement.executeQuery();

		return queryForBarber;
	}
	
	/* 
	 * This method is used to realize a update in the database
	 * @param message - Receives a message to the update
	 */
	public void updateQuery (String message) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/* 
	 * This method is used to show all barbers registered in the database
	 */
	public ResultSet showRegisteredBarber (Barber barber) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String sqlCodeToShowBarber = "Select nome, cpf, rg, telefone, cadeira from barbeiro;";
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = connection.createStatement().executeQuery(sqlCodeToShowBarber);
		
		return queryForBarber;
	}
	

	// This method is used to realize a search for a determined barber in the database 
	public ResultSet searchByName (Barber barber) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String sqlCodeToSearchBarberByName ="SELECT * FROM barbeiro WHERE nome = '"  + barber.getBarberName() + "';";
		// PreparedStatement class's instance to query the database
		PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSearchBarberByName);
		
		// ResultSet interface instance to query a barber's name
		ResultSet queryForBarber = preparedStatement.executeQuery();

		return queryForBarber;
	} 

}
