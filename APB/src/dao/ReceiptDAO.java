package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptDAO 
{
	private static ReceiptDAO instance;

	public ReceiptDAO() 
	{
		
	}

	/**
	 * @return The current instance if exists, or instantiate a new one if does not and return it
	 */
	public static ReceiptDAO getInstance() 
	{
		if( instance == null )
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
	 * Create a connection with DB
	 * @return The connection established
	 * @throws SQLException
	 */
	public Connection createConnectionWithDB() throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		Connection connection = factoryConnectionInstance.getConnection();
		
		return connection;
	}

	/**
	 *  Method used to search barber services
	 *  @param barberName - Contains the barber name 
	 *  @param initialDate - Receives the initial date
	 *  @param finalDate - Receives the final date
	 *  @throws SQLException
	 */	
	public ResultSet barberServicesSearch(String barberName, String initialDate, String finalDate) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		String sqlCodeToSelectFromGivenService = "SELECT * FROM servicoprestado WHERE data BETWEEN '"
												 + initialDate + "' AND '" + finalDate
												 + "' AND barbeiro = '" + barberName + "';";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectFromGivenService);
		
		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}

	/**
	 *  Method used to modify existing records
	 *  @param message - String about the exception
	 *  @throws SQLException
	 */
	public void updateQuery(String message) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

}
