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
	 * Método utilizado para receber os atributos de um Relatorio de forma correta,
	 * para realização do teste, lançando exceçoes de atributos nulos e problemas de conversão
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
	 * Método utilizado para testar o getInstance da classe RelatorioDAO
	*/

	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente ()
	{
		ReportController relatorioController = ReportController.getInstance();
		assertEquals(ReportController.getInstance(), relatorioController);
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um serviço
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
	 * Método utilizado para testar a pesquisa de um relatorio através de uma data
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
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * e de um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException 
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * e de uma data e um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateBarberAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
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
	 * Método utilizado para testar a pesquisa de um relatorio através de uma data
	 * e de um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataEServicoDeRelatorioControllerDeveMostrarUmRelatorio () throws SQLException
	{
		ReportController relatorioController = new ReportController();
		ResultSet rs = relatorioController.searchByDateAndService(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
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
