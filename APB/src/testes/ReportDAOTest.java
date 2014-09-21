package testes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Report;
import dao.ResultBarber;
import exception.ReportException;

public class ReportDAOTest
{
	
	// Report class's instance to access the class
	Report report = new Report();

	/* 
	 * M�todo utilizado para receber os atributos de um relatorio de forma correta,
	 * para realiza��o do teste, lan�ando exce�oes de atributos nulos e problemas de convers�o
	 */
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
 
	// M�todo utilizado para testar o getInstance da classe RelatorioDAO
	@Test
	public void getInstanceTest()
	{
		// DAO Report class's instance to access the class
		ResultBarber reportDAO = ResultBarber.getInstance();
		assertEquals( ResultBarber.getInstance(), reportDAO );
	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado por data
	@Test
	public void searchByDateTest()
	{
		try 
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			
			// ResultSet interface instance to query a report
			ResultSet queryForReport = reportDAO.searchByDate(report);
			
			while( queryForReport.next() )
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/* 
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado
	 *  por data e servi�o	
	 */
	@Test
	public void searchByDateAndServiceTest()
	{
		try 
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByDateAndService(report);
			
			while ( queryForReport.next() )
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado por barbeiro	
	@Test
	public void searchBarberTest()
	{
		try
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByBarber(report);
			
			while( queryForReport.next() ) 
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}

	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado
	 *  por data e servi�o	
	 */
	@Test
	public void searchByBarberAndServiceTest()
	{
		try
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByBarberAndService(report);
			
			while( queryForReport.next() ) 
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quanto procurado servi�o	
	@Test
	public void searchByServiceTest()
	{
		try
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByService(report);
			
			while ( queryForReport.next() )
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

	}

	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado 
	 * por data e barbeiro	
	 */
	@Test
	public void searchByDateAndBarberTest()
	{
		try
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByDateAndBarber(report);
			
			while ( queryForReport.next() ) 
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado 
	 * por data, servi�o e barbeiro	
	 */
	@Test
	public void searchByDateAndBarberAndServiceTest()
	{
		try 
		{
			ResultBarber reportDAO = ResultBarber.getInstance();
			ResultSet queryForReport = reportDAO.searchByDateBarberAndService(report);
			
			while ( queryForReport.next() ) 
			{
				// Receives the service type 
				String serviceType = queryForReport.getString("nome");
				assertEquals("Corte", serviceType);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
