package control;

import java.sql.ResultSet;
import java.sql.SQLException;

//Importando classes da DAO
import dao.ReceiptDAO;

public class ReceiptController 
{

	private static ReceiptController instance;

	public ReceiptController () 
	{
		
	}

	/**
	 * @return - Return the current instance if exists, or instantiate a new one if does not and return it
	 */
	public static ReceiptController getInstance () 
	{
		// "ReceiptController" Class Instance
		if( instance == null ) 
		{
			instance = new ReceiptController ();
		}
		else
		{
			// Nothing to do - because the condition "if"  is just used to check the initial value of the variable
		}

		return instance;
	}
	
	/**
     * Method that gives access to the barber services search
	 * @param barberName - Contains the barber name  
	 * @param initialDate - Contains the initial date
	 * @param finalDate - Contains the final date
	 * @return - Return the ResultSet of the search
	 */
	public ResultSet barberServicesSearch (String barberName , String initialDate, String finalDate) throws SQLException 
	{
		ReceiptDAO receiptDAOInstance = ReceiptDAO.getInstance();
		ResultSet barberServicesSearchResult = receiptDAOInstance.barberServicesSearch(barberName , initialDate, finalDate);
		
		return barberServicesSearchResult;
	}
	
}
