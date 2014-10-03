package testes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Report;
import dao.ReportDAO;
import exception.ReportException;

public class ReportDAOTest
{
	
	// Report class's instance to access the class
	Report report = new Report();

	/* 
	 * This method is used to receives the attributes of a Report DAO in the correct form
	 * to realize the test, throws exceptions of null attributes 
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
 
	// This method is used to test the method getInstance
	@Test
	public void getInstanceTest ()
	{
		// DAO Report class's instance to access the class
		ReportDAO reportDAO = ReportDAO.getInstance();
		assertEquals( ReportDAO.getInstance(), reportDAO );
	}

	// This method is used to test the method that search a registered report by date
	@Test
	public void searchByDateTest ()
	{
		try 
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
			
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

	// This method is used to test the method that search a registered report by date and service
	@Test
	public void searchByDateAndServiceTest ()
	{
		try 
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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

	// This method is used to test the method that search a registered report by barber	
	@Test
	public void searchBarberTest ()
	{
		try
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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

	//This method is used to test the method that search a registered report by barber and date
	@Test
	public void searchByBarberAndServiceTest()
	{
		try
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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

	// This method is used to test the method that search a registered report by service
	@Test
	public void searchByServiceTest()
	{
		try
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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

	// This method is used to test the method that search a registered report by date and barber
	@Test
	public void searchByDateAndBarberTest()
	{
		try
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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
	
	// This method is used to test the method that search a registered report by date, barber and service
	@Test
	public void searchByDateAndBarberAndServiceTest()
	{
		try 
		{
			ReportDAO reportDAO = ReportDAO.getInstance();
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
