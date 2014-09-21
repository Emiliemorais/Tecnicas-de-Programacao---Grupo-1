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

	Report relatorio = new Report();
	
	/* 
	 * Method used to get the attributes of a report correctly to the test, throwing exceptions null attributes and conversion issues
	*/
	@Before
	public void setUp () throws ReportException, ParseException 
	{
		try 
		{
			relatorio.setBarberName("Luciano");
			relatorio.setFinalDate("09/09/2013");
			relatorio.setInitialDate("01/01/2013");
			relatorio.setServiceType("corte");
		}
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Method used to test the getInstance class RelatorioDAO
	*/

	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente ()
	{
		ReportController relatorioController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), relatorioController);
	}
	
	/* 
	 * Method used to test the research a report through a service seeing if the sample of the report there
	*/
	@Test
	public void procurarPorServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * The method used to test the search for a report through the sample viewing date of the report exists
	*/
	@Test
	public void procurarPorDataDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDate(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Method used to test the research of a report by a barber and a service seeing if the sample of the report there

	*/
	@Test
	public void procurarPorBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Method used to test the research of a report by a barber and a date and see if the service sample of the report there

	*/
	@Test
	public void procurarPorDataBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 *Method used to test the research of a report by a barber and a date seeing if there is a sample of the report
	*/
	@Test
	public void procurarPorDataEBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndBarber(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Method used to test the research of a report by a date and a service seeing if the sample of the report there
	*/
	@Test
	public void procurarPorDataEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Method used to test the research of a report by a barber seeing if there is a sample of the report

	*/
	@Test
	public void procurarPorBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarber(relatorio);
		
		while(rs.next());
	}

}
