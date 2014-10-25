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

	Report report = new Report();
	
	// Method used to get the attributes of a report correctly to the test, throwing exceptions null attributes and conversion issues
	@Before
	public void setUp() throws ReportException, ParseException 
	{
		try 
		{
			report.setBarberName("Luciano");
			report.setFinalDate("09/09/2013");
			report.setInitialDate("01/01/2013");
			report.setServiceType("corte");
		}
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		}

	}
	
	// Method used to test the getInstance class RelatorioDAO
	@Test
	public void getInstanceReportDAORetornInstanceCurrent()
	{
		ReportController relatorioController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), relatorioController);
	}
	
	// Method used to test the research a report through a service seeing if the sample of the report there
	@Test
	public void returnOfSearchReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByService(report);
		
		while(rs.next());
	}
	
	// The method used to test the search for a report through the sample viewing date of the report exists
	@Test
	public void returnOfSearchForDateReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDate(report);
		
		while(rs.next());
	}
	
	// Method used to test the research of a report by a barber and a service seeing if the sample of the report there
	@Test
	public void retornSearchForBarberAndServiceReportController() throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarberAndService(report);
		
		while(rs.next());
	}
	
	// Method used to test the research of a report by a barber and a date and see if the service sample of the report there
	@Test
	public void retornSearchForDateBarberAndServiceReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateBarberAndService(report);
		
		while(rs.next());
	}
	
	// Method used to test the research of a report by a barber and a date seeing if there is a sample of the report
	@Test
	public void retornSearchForBarberAndDateReportController() throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndBarber(report);
		
		while(rs.next());
	}
	
	// Method used to test the research of a report by a date and a service seeing if the sample of the report there
	@Test
	public void retornSearchForDateAndServiceReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndService(report);
		
		while(rs.next());
	}
	
	// Method used to test the research of a report by a barber seeing if there is a sample of the report
	@Test
	public void retornSearchForBarberReportController() throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarber(report);
		
		while(rs.next());
	}

}
