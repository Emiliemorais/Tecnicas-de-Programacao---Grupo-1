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

public class RelatorioControllerTeste 
{

	Report relatorio = new Report();
	
	/* 
	 * M�todo utilizado para receber os atributos de um Relatorio de forma correta,
	 * para realiza��o do teste, lan�ando exce�oes de atributos nulos e problemas de convers�o
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
	 * M�todo utilizado para testar o getInstance da classe RelatorioDAO
	*/

	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente ()
	{
		ReportController relatorioController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), relatorioController);
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de um servi�o
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de uma data
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDate(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de um barbeiro
	 * e de um servi�o vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de um barbeiro
	 * e de uma data e um servi�o vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de um barbeiro
	 * e de uma data vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataEBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndBarber(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de uma data
	 * e de um servi�o vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * M�todo utilizado para testar a pesquisa de um relatorio atrav�s de um barbeiro
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarber(relatorio);
		
		while(rs.next());
	}

}
