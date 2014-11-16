package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import control.ReportController;
import exception.ReportException;

public class ReportControllerTest 
{

	Report reportService = new Report();
	
	// Initialize the reportService object with arbitrary data to use on tests
	@Before
	public void setUp() throws ReportException, ParseException 
	{
		try 
		{
			reportService.setBarberName("Luciano");
			reportService.setFinalDate("09/09/2013");
			reportService.setInitialDate("01/01/2013");
			reportService.setServiceType("corte");
		}
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		}

	}
	
	// Test the getInstance method
	@Test
	public void getInstanceReportDAOReturnInstanceCurrent()
	{
		ReportController reportController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), reportController);
	}
	
	// Method used to test the research a report through a service seeing if the sample of the report there
	@Test
	public void returnOfSearchReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByService(reportService);
		
		while(instanceStatement.next());
	}
	
	// The method used to test the search for a report through the sample viewing date of the report exists
	@Test
	public void returnOfSearchForDateReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByDate(reportService);
		
		while(instanceStatement.next());
	}
	
	// Method used to test the research of a report by a barber and a service seeing if the sample of the report there
	@Test
	public void retornSearchForBarberAndServiceReportController() throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByBarberAndService(reportService);
		
		while(instanceStatement.next());
	}
	
	// Method used to test the research of a report by a barber and a date and see if the service sample of the report there
	@Test
	public void retornSearchForDateBarberAndServiceReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByDateBarberAndService(reportService);
		
		while(instanceStatement.next());
	}
	
	// Method used to test the research of a report by a barber and a date seeing if there is a sample of the report
	@Test
	public void retornSearchForBarberAndDateReportController() throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByDateAndBarber(reportService);
		
		while(instanceStatement.next());
	}
	
	// Method used to test the research of a report by a date and a service seeing if the sample of the report there
	@Test
	public void retornSearchForDateAndServiceReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByDateAndService(reportService);
		
		while(instanceStatement.next());
	}
	
	// Method used to test the research of a report by a barber seeing if there is a sample of the report
	@Test
	public void retornSearchForBarberReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet instanceStatement = relatorioController.searchByBarber(reportService);
		
		while(instanceStatement.next());
	}

}
