package control;

import java.sql.ResultSet;
import java.sql.SQLException;

// Importando classes da DAO e da MODEL
import dao.ReportDAO;
import model.Report;

public class ReportController 
{

private static ReportController instance;

	public ReportController () 
	{
		
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by date
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByDate ( Report report ) throws SQLException 
	{
		return ReportDAO.getInstance().searchByDate ( report );
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by date and barber
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByDateAndBarber ( Report report ) throws SQLException 
	{	
		return ReportDAO.getInstance().searchByDateAndBarber ( report );
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by date and service
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByDateAndService(Report report) throws SQLException 
	{	
		return ReportDAO.getInstance().searchByDateAndService ( report );
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by barber
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByBarber ( Report report ) throws SQLException 
	{	
		return ReportDAO.getInstance().searchByBarber ( report );
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by barber and service
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByBarberAndService(Report report) throws SQLException 
	{	
		return ReportDAO.getInstance().searchByBarberAndService(report);
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by service
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByService ( Report report ) throws SQLException 
	{	
		return ReportDAO.getInstance().searchByService ( report );
	}
	
	/*
	 *  Method that gives access to the registered reports and also gives the option to search it by date, barber and service
	 *  @param report - Contains the report object
	 */
	public ResultSet searchByDateBarberAndService ( Report report ) throws SQLException 
	{		
		return ReportDAO.getInstance().searchByDateBarberAndService ( report );
	}
	
	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
	 */
	public static ReportController getInstance () 
	{
		if(instance == null) // "ReportController" class instance
		{
			instance = new ReportController ();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}

}
