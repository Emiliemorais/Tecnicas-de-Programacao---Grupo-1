
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.DoneService;
import control.DoneServiceController;
import exception.ServiceException;

public class DoneServiceControllerTest
{
	
	DoneService doneService = new DoneService();
	
	// Used in the test to get access to the methods of class 'ServicoPrestadoController.java'
	DoneServiceController doneServiceController = DoneServiceController.getInstance();


	@Before
	// Initialize the attributes of 'doneService'
	public void setUp() throws ServiceException, ParseException
	{
		doneService.setServiceName("Corte");
		doneService.setBarberName("Joao");
		doneService.setPrice("125,23");
		doneService.setDate("20/12/2013");
	}


	@Test
	// Test if a instance previous declared is the current one
	public void getInstanceMethodTest()
	{
		assertEquals(DoneServiceController.getInstance(), doneServiceController);
	}

	@Test
	/* 
	 * Test if a inclusion of a contact was made right
	 * Check the return of method 'insertProvidedService'
	 */
	public void includeDoneServiceMethodTest()
	{
		try
		{
			assertTrue( doneServiceController.insertProvidedService(doneService) );
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	/* 
	 * Test if a exclusion of a contact was made right
	 * Check the return of method 'deleteProvidedService'
	 */
	public void deleteDoneServiceMethodTest()
	{
		try
		{
			assertTrue( doneServiceController.deleteProvidedService(doneService) );
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'insertProvidedService' don't accept null argument
	public void includeDoneServiceTestForNullArgument()
	{
		try
		{
			assertFalse( doneServiceController.insertProvidedService(null) );
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'deleteProvidedService' don't accept null argument
	public void deleteDoneServiceTestForNullArgument()
	{
		try
		{
			assertFalse( doneServiceController.deleteProvidedService(null) );
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test the method 'displayRegisteredProvidedServices' is working
	public void showRegisteredDoneServicesMethodTest() throws SQLException
	{
		// Used to receive the result from the search for done services on DB
		ResultSet queryForDoneServicesResult = doneServiceController.displayRegisteredProvidedServices(doneService);
		
		while( queryForDoneServicesResult.next() );
	}
	
}
