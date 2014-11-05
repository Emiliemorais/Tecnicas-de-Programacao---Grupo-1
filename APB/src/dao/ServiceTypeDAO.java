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
		if( instance == null )
		{
			instance = new ServiceTypeDAO ();
		}
		else
		{
			// Nothing to do - because the condition "if"  is just used to check the initial value of the variable
		}
		
		return instance;
	}
	
	/**
	 * Method used to include a service type
	 * @param addServiceType - Includes the type of the service
	 * @throws SQLException
	 * @return - Return the status of the insertion
	 */
	public boolean includeServiceType (ServiceType addServiceType) throws SQLException 
	{
		boolean serviceTypeInserted;
		if( addServiceType == null )
		{
			serviceTypeInserted = false;
		}
		else
		{
			String sqlCodeToInsertServiceType = "INSERT INTO "
					+ "tiposervico (nome, preco) VALUES ("
					+ "\"" + addServiceType.getServiceTypeName() + "\", " + "\""
					+ addServiceType.getServiceTypePrice() + "\"); ";

			this.updateQuery(sqlCodeToInsertServiceType);
			
			serviceTypeInserted = true;
		}
		return serviceTypeInserted;
	}

	/**
	 *  Method used to edit a service type
	 *  @param serviceTypeName -  Receives the name of the type of service
	 *  @param editedServiceType - Gets the edited service type
	 *  @param editServiceType - Edits the service type
	 *  @throws SQLException
	 *  @return - Return the status of the edition
	 */	
	public boolean editServiceType (String serviceTypeName,
								   	ServiceType editedServiceType,
								   	ServiceType editServiceType) throws SQLException 
	{
		boolean serviceTypeEdited;
		if( editedServiceType == null || editServiceType == null ) 
		{
			serviceTypeEdited = false;
		}
		else
		{		
			String sqlCodeToUpdateServiceType = "UPDATE tiposervico SET nome = '"
												+ editedServiceType.getServiceTypeName ()
												+ "', " + "preco = '"
												+ editedServiceType.getServiceTypePrice ()  
												+ "' WHERE" + " nome = '" + serviceTypeName + "';";
			
			this.updateQuery(sqlCodeToUpdateServiceType);
			serviceTypeEdited = true;
		
		}

		return serviceTypeEdited;
	}

	/**
	 * Method used to delete a service type
	 * @param deleteServiceType - Deletes the service type
	 * @throws SQLException
	 * @return - Return the status of the exclusion
	 */
	public boolean deleteServiceType (ServiceType deleteServiceType) throws SQLException 
	{
		boolean serviceTypeDeleted;
		if( deleteServiceType == null )
		{
			serviceTypeDeleted = false;
		}
		else
		{
			String sqlCodeToDeleteServiceType = "DELETE FROM tiposervico WHERE "
					+ "tipoServico.nome = \"" 
					+ deleteServiceType.getServiceTypeName() + "\";";

			this.updateQuery (sqlCodeToDeleteServiceType);
			serviceTypeDeleted = true;
		}
		

		
		return serviceTypeDeleted;
	}
	
	/**
	 * Create a connection with DB
	 * @return The connection established
	 * @throws SQLException
	 */
	public Connection createConnectionWithDB () throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		Connection connection = factoryConnectionInstance.getConnection();
		
		return connection;
	}

	/**
	 *  Method used to execute some action on DB
	 *  @param message - SQL code of action to be executed
	 *  @throws SQLException
	 *  @return - Return the connection with the database
	 */
	public void updateQuery (String message) throws SQLException 
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
	 *  @return - Return the ResultSet of the selection of the all data from services type
	 */
	public ResultSet displayRegisteredTypesOfService (ServiceType service) throws SQLException 
	{
		Connection connection = createConnectionWithDB();

		ResultSet resultInstance = connection.createStatement().executeQuery("SELECT * FROM tiposervico;");
				
		return resultInstance;
	}
	
	/**
	 *  Method used to search by name
	 *  @param service - Contains the services
	 *  @throws SQLException
	 *  @return - Return ResultSet of the search by name
	 */	
	public ResultSet searchByName (ServiceType service) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		java.sql.PreparedStatement preparedStatement;
		
		preparedStatement = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
														+ "nome = '" + service.getServiceTypeName() + "';");
		
		ResultSet resultInstance = preparedStatement.executeQuery();
		
		return resultInstance;
	}

}
