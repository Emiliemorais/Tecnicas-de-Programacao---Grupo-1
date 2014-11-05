package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ReportDAO;
import model.Report;

public class ReportController 
{

private static ReportController instance;

	public ReportController () 
	{
		
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by date
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by date
	 */
	public ResultSet searchByDate (Report report) throws SQLException 
	{
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByDateResult = reportDAOInstance.searchByDate(report);
		
		return searchByDateResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by date and barber
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by date and barber
	 */
	public ResultSet searchByDateAndBarber (Report report) throws SQLException 
	{	
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByDateAndBarberResult = reportDAOInstance.searchByDateAndBarber(report);
		
		return searchByDateAndBarberResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by date and service
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by date and service
	 */
	public ResultSet searchByDateAndService (Report report) throws SQLException 
	{	
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByDateAndServiceResult = reportDAOInstance.searchByDateAndService(report);
		
		return searchByDateAndServiceResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by barber
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by barber
	 */
	public ResultSet searchByBarber (Report report) throws SQLException 
	{	
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByBarberResult = reportDAOInstance.searchByBarber(report);
		
		return searchByBarberResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by barber and service
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by barber and service
	 */
	public ResultSet searchByBarberAndService (Report report) throws SQLException 
	{	
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByBarberAndServiceResult = reportDAOInstance.searchByBarberAndService(report);
		
		return searchByBarberAndServiceResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by service
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by service
	 */
	public ResultSet searchByService (Report report) throws SQLException 
	{	
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByServiceResult = reportDAOInstance.searchByService(report);
		
		return searchByServiceResult;
	}
	
	/**
	 *  Method that gives access to the registered reports and also gives the option to search it by date, barber and service
	 *  @param report - Contains the report object
	 *  @return - Return the ResultSet of the search by date, barber and service
	 */
	public ResultSet searchByDateBarberAndService (Report report) throws SQLException 
	{		
		ReportDAO reportDAOInstance = ReportDAO.getInstance();
		ResultSet searchByDateBarberAndServiceResult = reportDAOInstance.searchByDateBarberAndService(report);
		
		return searchByDateBarberAndServiceResult;
	}
	
	/**
	 * @return - Return the current instance if exists, or instantiate a new one if does not and return it
	 */
	public static ReportController getInstance () 
	{
		// "ReportController" class instance
		if( instance == null ) 
		{
			instance = new ReportController();
		}
		else
		{
			// Nothing to do - because the condition "if"  is just used to check the initial value of the variable
		}
		
		return instance;
	}

}
