
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ServiceTypeDAO;
import model.ServiceType;

public class ServiceTypeController
{
	// Stores the current instance of the class
	private static ServiceTypeController instance;
	
	// General class constructor
	private ServiceTypeController() 
	{
	}
	
	/** 
	 * Method that includes a new service type
	 * @param serviceTypeToInclude - Service type to be included
	 * @return if exists Service Type To Include
	 */
	public boolean includeServiceType (ServiceType serviceTypeToInclude) throws SQLException
	{
		boolean existsServiceTypeToInclude;
		if( serviceTypeToInclude == null )
        {
			existsServiceTypeToInclude = false;
		}
		else
        {
			ServiceTypeDAO serviceTypeDAOInstance =  ServiceTypeDAO.getInstance();

			serviceTypeDAOInstance.includeServiceType(serviceTypeToInclude);
			existsServiceTypeToInclude = true;
		}
		return existsServiceTypeToInclude;
	}

	/**
	 * Method that modify a service type on the system
	 * @param serviceTypeToNoChangeName - Contains the name of the service type to change
	 * @param newServiceType - New service type that will replace the old one on DB
	 * @return modifyServiceType - if exists modify Service Type
	 */
	public boolean modifyServiceType ( String serviceTypeToChangeName,
									   ServiceType newServiceType ) throws SQLException
	{
		boolean existsNewServiceType;
		if( newServiceType == null )
        {
			existsNewServiceType = false;
		}
		else
        {
			// Auxiliary variable used to change the service type
			ServiceType changedServiceType = newServiceType;
			
			ServiceTypeDAO serviceTypeDAOInstance = ServiceTypeDAO.getInstance();
			
			serviceTypeDAOInstance.editServiceType(serviceTypeToChangeName,
												   changedServiceType,
												   newServiceType);
			existsNewServiceType = true;
		}
		return existsNewServiceType;
	}
	

	/**
	 * Method that delete a service type on the system
	 * @param serviceTypeToDelete - Contains the service type to be deleted
	 * @return deleteServiceType - if exists service type to delete  
	 */
	public boolean deleteServiceType (ServiceType serviceTypeToDelete) throws SQLException
	{
		boolean existsServiceTypeToDelete;
		if( serviceTypeToDelete == null )
        {
			existsServiceTypeToDelete = false;
		}
		else
        {
			ServiceTypeDAO serviceTypeDAOInstance =  ServiceTypeDAO.getInstance();
			
			serviceTypeDAOInstance.deleteServiceType(serviceTypeToDelete);
			existsServiceTypeToDelete = true;
		}
		return existsServiceTypeToDelete;
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static ServiceTypeController getInstance ()
	{
		if( instance == null )
        {
            instance = new ServiceTypeController();
        }
        else
        {
            // Nothing to do
        }
		
		return instance;
	}


	/**
	 * Return a ResultSet interface object with the service types registered on the system
	 * @param service - Never used ahead, Check need.
	 * @return showRegistredServiceTypes - display Registered Types Of Service Result
	 */
	public ResultSet showRegistredServiceTypes (ServiceType service) throws SQLException
	{
		ServiceTypeDAO serviceTypeDAOInstance = ServiceTypeDAO.getInstance();
		
		ResultSet displayRegisteredTypesOfServiceResult = serviceTypeDAOInstance
														  .displayRegisteredTypesOfService(service);
		
		return displayRegisteredTypesOfServiceResult;
	}


	/**
	 * Search for an specific service type name
	 * @param serviceTypeToSearch - Service type to search for
	 */
	public ResultSet searchServiceTypeByName (ServiceType serviceTypeToSearch) throws SQLException
	{
		ServiceTypeDAO serviceTypeDAOInstance = ServiceTypeDAO.getInstance();
		
		ResultSet searchByNameResult = serviceTypeDAOInstance
									   .searchByName(serviceTypeToSearch);
		
		return searchByNameResult;
	}
}
