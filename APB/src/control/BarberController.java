
package control;

import java.sql.ResultSet;
import java.sql.SQLException;
import dao.BarberDAO;
import model.Barber;


public class BarberController
{
	// Stores the current instance of the class
	private static BarberController instance;
	
	// Class constructor
	private BarberController()
	{
	}

	// Return the current instance or instantiate a new one if 'instance' is null
	public static BarberController getInstance()
	{
		if(instance == null)
        {
            instance = new BarberController();
        }
        else
        {
            // Nothing to do
        }
		return instance;
	}

	/**
	 * Method that includes a new barber on DB
	 * @param barberToInclude -  Barber to be included on DB
	 */
	public boolean includeBarber(Barber barberToInclude) throws SQLException
	{
		if(barberToInclude == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		barberDAOInstance.includeBarber(barberToInclude);
		
		return true;
	}


	/** 
	 * Method that modify a barber registered on DB
	 * @param barberToChangeName - String that contains the barber name that will be replaced
	 * @param newBarber - Barber that will replace the old one (where 'barberToChangeName' on DB)
	 */
	public boolean modifyBarber(String barberToChangeName, Barber newBarber) throws SQLException
	{
		if(newBarber == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		// Check use - Probably can be deleted
		Barber changedBarber = newBarber;
		
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		barberDAOInstance.changeBarber(barberToChangeName, changedBarber, newBarber);
		
		return true;
	}


	/**
	 * Method that delete a barber registered on DB
	 * @param barberToDelete - Barber to be deleted
	 */
	public boolean deleteBarber(Barber barberToDelete) throws SQLException
	{
		if(barberToDelete == null)
        {
            return false;
        }
        else
        {
            // Nothing to do
        }
		
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		barberDAOInstance.deleteBarber(barberToDelete);
		
		return true;
	}


	// Method that return all barber table (on BarberDAO) - Check the need of this method
	public ResultSet searchBarbers() throws SQLException
	{
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		ResultSet searchBarberResult = barberDAOInstance.searchBarber();
		
		return searchBarberResult;
	}


	/** 
	 * Method that return all registered barbers on DB
	 * @param barber - Check the need of this parameter. Never used, should be deleted.
	 */
	public ResultSet showRegisteredBarbers(Barber barber) throws SQLException
	{
		
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		ResultSet showRegisteredBarberResult = barberDAOInstance.showRegisteredBarber(barber);	
		
		return showRegisteredBarberResult;
	}
	
	
	/** 
	 * Method that search for a specific barber on DB
	 * @param barberToSearchFor - Barber to search for on DB
	 */
	public ResultSet searchBarberByName(Barber barberToSearchFor) throws SQLException
	{
		
		BarberDAO barberDAOInstance = BarberDAO.getInstance();
		
		ResultSet searchByNameResult = barberDAOInstance.searchByName(barberToSearchFor);	
		
		return searchByNameResult;
	}

}
