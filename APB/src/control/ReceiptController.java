package control;

import java.sql.ResultSet;
import java.sql.SQLException;

//Importando classes da DAO
import dao.ReciboDAO;

public class ReceiptController 
{

	private static ReceiptController instance;

	public ReceiptController () 
	{
		
	}

	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
	 */
	public static ReceiptController getInstance () 
	{
		if (instance == null) // "ReceiptController" Class Instance
		{
			instance = new ReceiptController ();
		}
		else
		{
			// Nothing to do
		}

		return instance;
	}
	/*
	 * @param barberName - Contains the barber name  
	 * @param initialDate - Contains the initial date
	 * @param finalDate - Contains the final date
	 */
	public ResultSet barberServicesSearch ( String barberName , String initialDate, String finalDate ) throws SQLException 
	{
		
		return ReciboDAO.getInstance().barberServicesSearch ( barberName , initialDate, finalDate );
	}
	
}
