package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceType;

import org.junit.Before;
import org.junit.Test;

import control.ServiceTypeController;
import exception.ServiceException;

public class ServiceTypeControllerTest 
{

	// serviceInstance - Instance of "ServiceType" class
	ServiceType serviceInstance = new ServiceType(); 
	
	// serviceControllerInstance - Instance of  "ServiceTypeController" class
	ServiceTypeController serviceControllerInstance = ServiceTypeController.getInstance();

	// Method used to set up the parameters for the test
	@Before
	public void setUp() 
	{
		try 
		{
			serviceInstance.setServiceTypeName("Corte");
			serviceInstance.setServiceTypePrice("15,00");
		}
		catch (ServiceException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to check if the current instance is being returned
	@Test
	public void getInstanceFromServiceTypeController()
	{
		assertEquals(ServiceTypeController.getInstance(), serviceControllerInstance);
	}

	// Method used to test if a service type is being added
	@Test
	public void addServiceTypeController()
	{
		try 
		{
			assertTrue(serviceControllerInstance.includeServiceType(serviceInstance));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to test if a service type is being deleted
	@Test
	public void deleteServiceTypeController() 
	{
		try 
		{
			assertTrue(serviceControllerInstance.deleteServiceType(serviceInstance));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to test if a service type is being edited
	@Test
	public void editServiceTypeController()
	{
		try 
		{
			assertTrue(serviceControllerInstance.modifyServiceType(serviceInstance.getServiceTypeName(),
					serviceInstance));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method that tests if the service type is null when adding
	@Test
	public void addNotNullServiceType()
	{
		try 
		{
			assertFalse(serviceControllerInstance.includeServiceType(null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method that tests if the service type is null when deleting
	@Test
	public void deleteNotNullServiceType() 
	{
		try 
		{
			assertFalse(serviceControllerInstance.deleteServiceType(null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method that tests if the service type is null when editing
	@Test
	public void editNotNullServiceType() 
	{
		try 
		{
			assertFalse(serviceControllerInstance.modifyServiceType(null, null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Methot that test if a barber is being displayed
	@Test
	public void displayBarberFromController()
		throws SQLException 
	{
		
		// resultInstance = ResultSet Instance
		ResultSet resultInstance = serviceControllerInstance.showRegistredServiceTypes(serviceInstance);
		
		while (resultInstance.next())
		{
			;
		}
	}

	// Method that test if a service is being displayed when searched by its name
	@Test
	public void searchByServiceTypeNameController()
		throws SQLException 
	{
		
		// resultInstance = ResultSet Instance
		ResultSet resultInstance = serviceControllerInstance.searchServiceTypeByName(serviceInstance);
	
		while (resultInstance.next())
		{
			;
		}
	}

}

