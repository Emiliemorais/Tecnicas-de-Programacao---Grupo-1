package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DoneServiceDAO;
import model.ServicoPrestado;

public class ProvidedServiceController
{

	private ProvidedServiceController()
	{

	}

	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
	 */
	public static ProvidedServiceController getInstance()
	{
		if (instance == null) // "ProvidedServiceController" class instance
        {
            instance = new ProvidedServiceController();
        }
        else
        {
            // Nothing to do
        }

        return instance;
	}

	private static ProvidedServiceController instance;

    /*
     *  Method used to insert a service
     *  @param providedService - Contains the provided service
     */
	public boolean insertProvidedService(ServicoPrestado providedService) throws SQLException
	{
		if (providedService != null)
        {
			DoneServiceDAO.getInstance().includeServiceType(providedService);
			return true;
		}
		else
        {
            // Nothing to do
        }

		return false;
	}

	/*
     *  Method used to delete a service
     *  @param providedService - Contains the provided service
     */
	public boolean deleteProvidedService(ServicoPrestado providedService) throws SQLException
	{
		if (providedService !=  null)
        {
			DoneServiceDAO.getInstance().deleteServiceType(providedService);
			return true;
		}
		else
        {
            // Nothing to do
        }
		
		return false;
	}

	/*
     *  Method that gives access to the registered services
     *  @param providedService - Contains the provided service
     */
	public ResultSet displayRegisteredProvidedServices(ServicoPrestado providedService) throws SQLException
	{
		
		return DoneServiceDAO.getInstance().showRegistredDoneServices(providedService);
	}

}

