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
	
	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
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
	 */
	public boolean includeServiceType(ServiceType addServiceType) throws SQLException 
	{
		if (addServiceType == null)
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
	 */	
	public boolean editServiceType(String serviceTypeName, ServiceType editedServiceType, ServiceType editServiceType) throws SQLException 
	{
		if (editedServiceType == null || editServiceType == null) 
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
					
		String sqlCodeToUpdateServiceType = "UPDATE tiposervico SET nome = '"
				+ editedServiceType.getServiceTypeName () + "', " + "preco = '"
				+ editedServiceType.getServiceTypePrice ()  + "' WHERE"
				+ " nome = '" + serviceTypeName + "';";
		
		this.updateQuery(sqlCodeToUpdateServiceType);

		return true;
	}

	/**
	 * Method used to delete a service type
	 * @param deleteServiceType - Deletes the service type
	 */
	public boolean deleteServiceType(ServiceType deleteServiceType) throws SQLException 
	{
		if (deleteServiceType == null)
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToDeleteServiceType = "DELETE FROM tiposervico WHERE "
											+ "tipoServico.nome = \"" + deleteServiceType.getServiceTypeName() + "\";";
		
		this.updateQuery (sqlCodeToDeleteServiceType);
		
		return true;
	}

	/**
	 *  Method used to modify existing records
	 *  @param message - String about the exception
	 */
	public void updateQuery ( String message ) throws SQLException 
	{
		// connection - Receives values accordingly to the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// preparedStatement - Variable that receives the prepared statements
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
	
	/**
	 *  Method used to display the registered service types
	 *  @param service -  Contains the services
	 */
	public ResultSet displayRegisteredTypesOfService(ServiceType service) throws SQLException 
	{
		// connection - Receives values accordingly to the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// resultInstance - ResultSet instance
		ResultSet resultInstance = connection.createStatement().executeQuery("SELECT * FROM tiposervico;");
				
		return resultInstance;
	}
	
	/**
	 *  Method used to search by name
	 *  @param service - Contains the services 
	 */	
	public ResultSet searchByName(ServiceType service) throws SQLException 
	{
		// connection - Receives values accordingly to the database
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// preparedStatement - Receives the prepared statements
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tiposervico WHERE "
																					+ "nome = '" + service.getServiceTypeName() + "';");
		
		// resultInstance - ResultSet instance
		ResultSet resultInstance = preparedStatement.executeQuery();
		
		return resultInstance;
	}

}
