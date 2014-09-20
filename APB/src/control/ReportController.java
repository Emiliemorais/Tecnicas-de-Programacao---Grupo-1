package control;

import java.sql.ResultSet;
import java.sql.SQLException;

// Importando classes da DAO e da MODEL
import dao.RelatorioDAO;
import model.Report;

public class ReportController 
{

private static ReportController instance;

	public ReportController () 
	{
		
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by date
	public ResultSet searchByDate ( Report relatorio ) throws SQLException 
	{
		return RelatorioDAO.getInstance().searchByDate ( relatorio );
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by date and barber
	public ResultSet searchByDateAndBarber ( Report report ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().searchByDateAndBarber ( report );
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by date and service
	public ResultSet searchByDateAndService(Report report) throws SQLException 
	{	
		return RelatorioDAO.getInstance().searchByDateAndService ( report );
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by barber
	public ResultSet searchByBarber ( Report report ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().searchByBarber ( report );
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by barber and service
	public ResultSet searchByBarberAndService(Report report) throws SQLException 
	{	
		return RelatorioDAO.getInstance().searchByBarberAndService(report);
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by service
	public ResultSet searchByService ( Report report ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().searchByService ( report );
	}
	
	// Method that gives access to the registered reports and also gives the option to search it by date, barber and service
	public ResultSet searchByDateBarberAndService ( Report report ) throws SQLException 
	{		
		return RelatorioDAO.getInstance().searchByDateBarberAndService ( report );
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
