
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DoneService;


public class DoneServiceDAO
{
	private static DoneServiceDAO instance;
	
	// General constructor
	private DoneServiceDAO()
	{
        // Blank
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

	/* 
	 * Include a new service type to DB
	 * Parameter: serviceToInclude - Service type that will be included on DB
	 */
	public boolean includeServiceType(DoneService serviceToInclude) throws SQLException
	{
		if( serviceToInclude != null )
        {
			this.updateQuery("INSERT INTO "
							 + "servicoprestado (nome, preco, barbeiro, data) VALUES ("
							 + "\"" + serviceToInclude.getServiceName() + "\", " + "\""
							 + serviceToInclude.getPrice() + "\", " + "\""
							 + serviceToInclude.getBarberName() + "\", " + "\""
							 + serviceToInclude.getDate() + "\"); ");
			return true;
        }
        else
        {
            // Nothing to do
        }
		
		return false;
	}

	
	/* 
	 * Delete a service type on DB
	 * Parameter: serviceToDelete - Service type that will be deleted from DB
	 */
	public boolean deleteServiceType(DoneService serviceToDelete) throws SQLException
	{
		if( serviceToDelete != null )
        {
			this.updateQuery("DELETE FROM servicoprestado WHERE "
							 + "servicoprestado.idservicoprestado = \"" 
							 + searchServiceType(serviceToDelete)+ "\";");
			return true;
        }
        else
        {
        	// Nothing to do
        }
		return false;
	}


	/* 
	 * Search a service type in DB
	 * Parameter: serviceToSearchFor - Service type that will be searched in DB
	 */
	private String searchServiceType(DoneService serviceToSearchFor) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		PreparedStatement preparedStatement;
		
		preparedStatement = connection.prepareStatement("SELECT * FROM servicoprestado WHERE "
											  			+ "servicoprestado.nome = \""
											  			+ serviceToSearchFor.getServiceName()
											  			+ "\" AND servicoprestado.preco = \""
											  			+ serviceToSearchFor.getPrice()
											  			+ "\" AND servicoprestado.barbeiro = \""
											  			+ serviceToSearchFor.getBarberName()
											  			+ "\" AND servicoprestado.data = \""
											  			+ serviceToSearchFor.getDate() + "\";");
		
		// Used to receive the result from a search on DB
		ResultSet queryResult = preparedStatement.executeQuery();
		queryResult.next();
		
		return queryResult.getString("idservicoprestado");
	}


	// Update the data included on DB
	private void updateQuery(String message) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}


	/*
	 *  Show registered done services
	 *  Parameter: service - Never used. Should be deleted
	 *  Check the need of this parameter
	 */
	public ResultSet showRegistredDoneServices(DoneService service) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		// Used to receive the result from a search for registered done services on DB
		ResultSet queryForRegisteredDoneServicesResult = connection
														.createStatement()
														.executeQuery("SELECT nome,"
																	  + " preco,"
																	  + " barbeiro,"
																	  + " data FROM servicoprestado"
																	  + " ORDER BY data;");
		return queryForRegisteredDoneServicesResult;
	}

}
