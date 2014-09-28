
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.ServiceTypeDAO;
import model.ServiceType;

public class ServiceTypeController
{
	// Stores the current instance of the class
	private static ServiceTypeController instance;

	/** 
	 * Method that includes a new service type
	 * @param serviceTypeToInclude - Service type to be included
	 */
	public boolean includeServiceType (ServiceType serviceTypeToInclude) throws SQLException
	{
		if( serviceTypeToInclude == null )
        {
			return false;
		}
		else
        {
			ServiceTypeDAO.getInstance ().includeServiceType(serviceTypeToInclude);
			return true;
		}
	}


	/**
	 * Method that modify a service type on the system
	 * @param serviceTypeToNoChangeName - Contains the name of the service type to change
	 * @param newServiceType - New service type that will replace the old one on DB
	 */
	public boolean modifyServiceType ( String serviceTypeToChangeName,
									   ServiceType newServiceType ) throws SQLException
	{
		if( newServiceType == null )
        {
			return false;
		}
		else
        {
			// Auxiliar variable used to change the service type
			ServiceType changedServiceType = newServiceType;
			
			ServiceTypeDAO.getInstance().editServiceType (serviceTypeToChangeName, changedServiceType,
												  newServiceType);
			return true;
		}
	}
	

	/**
	 * Method that delete a service type on the system
	 * @param serviceTypeToDelete - Contains the service type to be deleted
	 */
	public boolean deleteServiceType (ServiceType serviceTypeToDelete) throws SQLException
	{
		if( serviceTypeToDelete == null )
        {
			return false;
		}
		else
        {
			ServiceTypeDAO.getInstance().deleteServiceType(serviceTypeToDelete);
			return true;
		}
	}

	// Class constructor
	private ServiceTypeController () 
	{
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
	 * @param service - Never usede ahead, Check need.
	 */
	public ResultSet showRegistredServiceTypes (ServiceType service) throws SQLException
	{
		return ServiceTypeDAO.getInstance().displayRegisteredTypesOfService(service);
	}


	/**
	 * Search for an specific service type name
	 * @param serviceTypeToSearch - Service type to search for
	 */
	public ResultSet searchServiceTypeByName (ServiceType serviceTypeToSearch) throws SQLException
	{
		return ServiceTypeDAO.getInstance().searchByName(serviceTypeToSearch);
	}

}
