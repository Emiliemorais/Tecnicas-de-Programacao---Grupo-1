
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import dao.PhonebookDAO;
import dao.ReceiptDAO;
import model.Report;
import exception.ReceiptException;
import exception.ReportException;

public class ReceiptDAOTest
{
	Report report = new Report();
	ReceiptDAO receiptDAO = new ReceiptDAO();
	
	@Before
	// Initialize the attributes of 'report'
	public void setUp() throws ReceiptException, ParseException
	{
		try
		{
			report.setBarberName("Fulano");
			report.setFinalDate("09/09/2013");
			report.setInitialDate("01/01/2013");
		}
		catch (ReportException e)
		{
			e.printStackTrace();
		}
	}


	@Test
	// Test if a instance previous declared is the current one
	public void getInstanceMethodTest()
	{
		// Used to test the method getInstance on a 'ReceiptDAO' instance
		ReceiptDAO receipt = ReceiptDAO.getInstance();
		
		ReceiptDAO receiptDAOInstance = ReceiptDAO.getInstance();
		
		assertEquals(receiptDAOInstance, receipt);
	}
	
	
	// Test if the connection is established is not null
	@Test
	public void createConnectionWithDBTestIfTheConnectionIsNotNull()
	{
		try
		{
			Connection connectionToTest = receiptDAO.createConnectionWithDB();
			
			assertNotNull("This connection should not be null", connectionToTest);
			
			connectionToTest.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if the connection is valid
	@Test
	public void createConnectionWithDBTestIfTheConnectionisValid()
	{
		try
		{
			Connection connectionToTest = receiptDAO.createConnectionWithDB();
			
			assertTrue("This connection should be active", connectionToTest.isValid(10));
			
			connectionToTest.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if the connection is closed
	@Test
	public void createConnectionWithDBTestIfTheConnectionisClosed()
	{
		try
		{
			Connection connectionToTest = receiptDAO.createConnectionWithDB();
			
			connectionToTest.close();
			
			assertTrue("This connection should be closed", connectionToTest.isClosed());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/* 
	 * Test the method 'barberServicesSearch'
	 *  (if returns the right services of a barber)
	 */
	public void searchBarberServicesMethodTest()
	{
		try
		{
			// Instantiated to get access to the method 'pesquisarServicosDoBarbeiro'
			ReceiptDAO receipt = ReceiptDAO.getInstance();
			
			// Used to receive the result from the search for barber services on DB
			ResultSet queryForBarberServicesResult = receipt
													 .barberServicesSearch(
													 report.getBarberName(),
													 report.getInitialDate(),
													 report.getFinalDate() );

			while ( queryForBarberServicesResult.next() )
            {
				// Receive the service name from query
				String serviceName = queryForBarberServicesResult.getString("nome");
				
				assertEquals("Corte", serviceName);
            }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
