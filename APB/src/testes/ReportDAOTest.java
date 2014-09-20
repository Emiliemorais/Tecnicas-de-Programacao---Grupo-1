package testes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Report;
import dao.RelatorioDAO;
import exception.RelatorioException;

public class ReportDAOTest
{
	
	// Report class's instance to access the class
	Report report = new Report();

	/* 
	 * Método utilizado para receber os atributos de um relatorio de forma correta,
	 * para realização do teste, lançando exceçoes de atributos nulos e problemas de conversão
	 */
	@Before
	public void setUp() throws RelatorioException, ParseException
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
 
	// Método utilizado para testar o getInstance da classe RelatorioDAO
	@Test
	public void getInstanceTest()
	{
		// DAO Report class's instance to access the class
		RelatorioDAO reportDAO = RelatorioDAO.getInstance();
		assertEquals( RelatorioDAO.getInstance(), reportDAO );
	}

	// Método utilizado para testar a vizualização de um relatorio, quando procurado por data
	@Test
	public void searchByDateTest()
	{
		try 
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
			
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
	 * Método utilizado para testar a vizualização de um relatorio, quando procurado
	 *  por data e serviço	
	 */
	@Test
	public void searchByDateAndServiceTest()
	{
		try 
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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

	// Método utilizado para testar a vizualização de um relatorio, quando procurado por barbeiro	
	@Test
	public void searchBarberTest()
	{
		try
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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
	 * Método utilizado para testar a vizualização de um relatorio, quando procurado
	 *  por data e serviço	
	 */
	@Test
	public void searchByBarberAndServiceTest()
	{
		try
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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

	// Método utilizado para testar a vizualização de um relatorio, quanto procurado serviço	
	@Test
	public void searchByServiceTest()
	{
		try
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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
	 * Método utilizado para testar a vizualização de um relatorio, quando procurado 
	 * por data e barbeiro	
	 */
	@Test
	public void searchByDateAndBarberTest()
	{
		try
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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
	 * Método utilizado para testar a vizualização de um relatorio, quando procurado 
	 * por data, serviço e barbeiro	
	 */
	@Test
	public void searchByDateAndBarberAndServiceTest()
	{
		try 
		{
			RelatorioDAO reportDAO = RelatorioDAO.getInstance();
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
