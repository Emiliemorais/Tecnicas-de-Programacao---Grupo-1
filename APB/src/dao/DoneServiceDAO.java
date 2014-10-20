package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DoneService;


public class DoneServiceDAO
{
	private static DoneServiceDAO instance;
	
	// General class constructor
	private DoneServiceDAO()
	{
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static DoneServiceDAO getInstance()
	{
		if (instance == null)
        {
            instance = new DoneServiceDAO();
        }
        else
        {
            // Nothing to do
        }
		return instance;
	}

	/** 
	 * Include a new service type to DB
	 * @param serviceToInclude - Service type that will be included on DB
	 */
	public boolean includeServiceType(DoneService serviceToInclude) throws SQLException
	{
		if( serviceToInclude != null )
        {
			
			String sqlCodeToInsertDoneService = "";
			
			sqlCodeToInsertDoneService = "INSERT INTO "
							 			 + "servicoprestado (nome, preco, barbeiro, data) VALUES ("
							 			 + "\"" + serviceToInclude.getServiceName() + "\", " + "\""
							 			 + serviceToInclude.getPrice() + "\", " + "\""
							 			 + serviceToInclude.getBarberName() + "\", " + "\""
							 			 + serviceToInclude.getDate() + "\"); ";
					
			this.updateQuery(sqlCodeToInsertDoneService);
			
			return true;
        }
        else
        {
            // Nothing to do
        }
		
		return false;
	}

	
	/** 
	 * Delete a service type on DB
	 * @param serviceToDelete - Service type that will be deleted from DB
	 */
	public boolean deleteServiceType(DoneService serviceToDelete) throws SQLException
	{
		if( serviceToDelete != null )
        {
			String sqlCodeToDeleteDoneService = "DELETE FROM servicoprestado WHERE "
					 					 		+ "servicoprestado.idservicoprestado = \"" 
					 					 		+ searchServiceType(serviceToDelete)+ "\";";
			
			this.updateQuery(sqlCodeToDeleteDoneService);
			
			return true;
        }
        else
        {
        	// Nothing to do
        }
		return false;
	}


	/** 
	 * Search a done service in DB
	 * @param serviceToSearchFor - Service type that will be searched in DB
	 */
	private String searchServiceType(DoneService serviceToSearchFor) throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		
		Connection connection = factoryConnectionInstance.getConnection();
		
		PreparedStatement preparedStatement;
		
		String sqlCodeToSearchForServiceType = "SELECT * FROM servicoprestado WHERE "
	  										   + "servicoprestado.nome = \""
	  										   + serviceToSearchFor.getServiceName()
	  										   + "\" AND servicoprestado.preco = \""
	  										   + serviceToSearchFor.getPrice()
	  										   + "\" AND servicoprestado.barbeiro = \""
	  										   + serviceToSearchFor.getBarberName()
	  										   + "\" AND servicoprestado.data = \""
	  										   + serviceToSearchFor.getDate() + "\";";
		
		preparedStatement = connection.prepareStatement(sqlCodeToSearchForServiceType);
		
		// Used to receive the result from a search on DB
		ResultSet queryResult = preparedStatement.executeQuery();
		queryResult.next();
		
		String idDoneService = queryResult.getString("idservicoprestado");
		
		return idDoneService;
	}


	/** 
	 * Execute an action on DB
	 * @param message - SQL message to do some action on DB
	 */
	private void updateQuery(String message) throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		
		Connection connection = factoryConnectionInstance.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}


	/**
	 *  Show registered done services
	 *  @param service - Never used. Should be deleted
	 *  Check the need of this parameter
	 */
	public ResultSet showRegistredDoneServices(DoneService service) throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		
		Connection connection = factoryConnectionInstance.getConnection();
		
		String sqlCodeToShowRegisteredDoneServices = "SELECT nome,"
				  									 + " preco,"
				  									 + " barbeiro,"
				  									 + " data FROM servicoprestado"
				  									 + " ORDER BY data;";
		
		// Used to receive the result from a search for registered done services on DB
		ResultSet queryForRegisteredDoneServicesResult = connection
														.createStatement()
														.executeQuery(sqlCodeToShowRegisteredDoneServices);
		return queryForRegisteredDoneServicesResult;
	}

}
