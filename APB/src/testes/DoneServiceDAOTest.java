package testes;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import model.DoneService;

import org.junit.Before;
import org.junit.Test;

import dao.DoneServiceDAO;
import exception.ServiceException;

public class DoneServiceDAOTest
{

	// Given Service class's instance to access the class
	DoneService doneService = new DoneService();
	DoneService doneService2 = new DoneService();
	
	/* 
	 * This method is used to receives the attributes of a Done Service in the correct form
	 * to realize the test, throws exceptions of null attributes e conversions problems
	 */
	@Before
	public void setUp() 
	{
		try
		{
			doneService.setServiceName("Corte");
			doneService.setBarberName("Alessandro");
			doneService.setDate("10/10/2010");
			doneService.setPrice("10,00");
			doneService2.setServiceName("Barba");
			doneService2.setBarberName("Luciano");
			doneService2.setDate("01/01/2010");
			doneService2.setPrice("9,90");
		} 
		catch (NullPointerException exception)
		{
			exception.printStackTrace();
		} 
		catch (ServiceException exception)
		{
			exception.printStackTrace();
		}
		catch (ParseException exception) 
		{
			exception.printStackTrace();
		}
	}

	DoneServiceDAO doneServiceDAO = DoneServiceDAO.getInstance();
		
	// This method is used to test the method getInstance 
	@Test
	public void getInstanceTest()
	{
		assertEquals(DoneServiceDAO.getInstance(), doneServiceDAO);
	}
	 
	// This method tests the register of a Done Service
	@Test
	public void includeDoneServiceTest()
	{
		try 
		{
			assertTrue(doneServiceDAO.includeServiceType(doneService));
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
	}
	
	// This method tests the delete of a Done Service	
	@Test
	public void deleteDoneServiceTest() 
	{
		try 
		{
			assertTrue(doneServiceDAO.deleteServiceType(doneService));
		} 
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}
	 
	// This method tests the register of a Done Service	with a null done service
	@Test
	public void includeDoneServiceNullTest() 
	{
		try 
		{
			assertFalse(doneServiceDAO.includeServiceType(null));
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
		}
	}
	 
	// This method tests the delete of a Done Service with a null done service
	@Test
	public void deleteDoneServiceNullTest() 
	{
		try
		{
			assertFalse(doneServiceDAO.deleteServiceType(null));
		} 
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}

}
