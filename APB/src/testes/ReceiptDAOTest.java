
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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

	
	@Before
	// Initialize the atributes of 'report'
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

	@Test
	/* 
	 * Test the method 'pesquisaSerivcosDoBarbeiro'
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
