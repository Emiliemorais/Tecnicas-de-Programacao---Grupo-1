package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptDAO 
{

	private static ReceiptDAO instance;

	private ReceiptDAO() 
	{
		
	}

	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
	 */
	public static ReceiptDAO getInstance() 
	{
		if (instance == null)
		{
			instance = new ReceiptDAO();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}

	/**
	 *  Method used to search barber services
	 *  @param barberName - Contains the barber name 
	 *  @param initialDate - Receives the initial date
	 *  @param finalDate - Receives the final date
	 */	
	public ResultSet barberServicesSearch(String barberName, String initialDate, String finalDate) throws SQLException 
	{
		// connection - Receives values accordingly to the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String sqlCodeToSelectFromGivenService = "SELECT * FROM servicoprestado WHERE data BETWEEN '"
													+ initialDate
													+ "' AND '"
													+ finalDate
													+ "' AND barbeiro = '" + barberName + "';";
		
		// preparedStatement - Instance of java.sql
		PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectFromGivenService);
		
		// resultInstance - ResultSet instance
		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}

	/**
	 *  Method used to modify existing records
	 *  @param message - String about the exception
	 */
	public void updateQuery(String message) throws SQLException 
	{
		// connection - Receives values accordingly to the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// preparedStatement - Instance of java.sql
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

}
