package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceType;

public class ServiceTypeDAO 
{

	private static ServiceTypeDAO instance;

	private ServiceTypeDAO () 
	{
		
	}
	
	/**
 	 * @return The current instance, if exists, or instantiate a new one if does not and return it
	 */
	public static ServiceTypeDAO getInstance () 
	{
		if (instance == null)
		{
			instance = new ServiceTypeDAO ();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}
	
	/**
	 * Method used to include a service type
	 * @param addServiceType - Includes the type of the service
	 * @throws SQLException
	 */
	public boolean includeServiceType(ServiceType addServiceType) throws SQLException 
	{
		if( addServiceType == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToInsertServiceType = "INSERT INTO "
											+ "tiposervico (nome, preco) VALUES ("
											+ "\"" + addServiceType.getServiceTypeName() + "\", " + "\""
											+ addServiceType.getServiceTypePrice() + "\"); ";
		
		this.updateQuery(sqlCodeToInsertServiceType);

		return true;
	}

	/**
	 *  Method used to edit a service type
	 *  @param serviceTypeName -  Receives the name of the type of service
	 *  @param editedServiceType - Gets the edited service type
	 *  @param editServiceType - Edits the service type
	 *  @throws SQLException
	 */	
	public boolean editServiceType(String serviceTypeName,
								   ServiceType editedServiceType,
								   ServiceType editServiceType) throws SQLException 
	{
		if ( editedServiceType == null || editServiceType == null ) 
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
					
		String sqlCodeToUpdateServiceType = "UPDATE tiposervico SET nome = '"
											+ editedServiceType.getServiceTypeName ()
											+ "', " + "preco = '"
											+ editedServiceType.getServiceTypePrice ()  
											+ "' WHERE" + " nome = '" + serviceTypeName + "';";
		
		this.updateQuery(sqlCodeToUpdateServiceType);

		return true;
	}

	/**
	 * Method used to delete a service type
	 * @param deleteServiceType - Deletes the service type
	 * @throws SQLException
	 */
	public boolean deleteServiceType(ServiceType deleteServiceType) throws SQLException 
	{
		if( deleteServiceType == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToDeleteServiceType = "DELETE FROM tiposervico WHERE "
											+ "tipoServico.nome = \"" 
											+ deleteServiceType.getServiceTypeName() + "\";";
		
		this.updateQuery (sqlCodeToDeleteServiceType);
		
		return true;
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
	 *  Method used to execute some action on DB
	 *  @param message - SQL code of action to be executed
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
	
	/**
	 *  Method used to display the registered service types
	 *  @param service -  Contains the services
	 *  @throws SQLException
	 */
	public ResultSet displayRegisteredTypesOfService(ServiceType service) throws SQLException 
	{
		Connection connection = createConnectionWithDB();

		ResultSet resultInstance = connection.createStatement().executeQuery("SELECT * FROM tiposervico;");
				
		return resultInstance;
	}
	
	/**
	 *  Method used to search by name
	 *  @param service - Contains the services
	 *  @throws SQLException
	 */	
	public ResultSet searchByName(ServiceType service) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		java.sql.PreparedStatement preparedStatement;
		
		preparedStatement = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
														+ "nome = '" + service.getServiceTypeName() + "';");
		
		ResultSet resultInstance = preparedStatement.executeQuery();
		
		return resultInstance;
	}

}
