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

	ServiceType serviceInstance = new ServiceType(); // serviceInstance - Instance of "ServiceType" class
	ServiceTypeController serviceControllerInstance = ServiceTypeController // serviceControllerInstance - Instance of  "ServiceTypeController" class
			.getInstance();

	@Before
	// Method used to set up the parameters for the test
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

	@Test
	// Method used to check if the current instance is being returned
	public void getInstanceFromServiceTypeController()
	{
		assertEquals(ServiceTypeController.getInstance(), serviceControllerInstance);
	}

	@Test
	// Method used to test if a service type is being added
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
	
	@Test
	// Method used to test if a service type is being deleted
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

	@Test
	// Method used to test if a service type is being edited
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

	@Test
	// Method that tests if the service type is null when adding
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

	@Test
	// Method that tests if the service type is null when deleting
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

	@Test
	// Method that tests if the service type is null when editing
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
	
	@Test
	// Methot that test if a barber is being displayed
	public void displayBarberFromController()
		throws SQLException 
	{
		ResultSet resultInstance = serviceControllerInstance.showRegistredServiceTypes(serviceInstance);
		// resultInstance = ResultSet Instance
		while (resultInstance.next())
		{
			;
		}
	}

	@Test
	// Method that test if a service is being displayed when searched by its name
	public void searchByServiceTypeNameController()
		throws SQLException 
	{
		ResultSet resultInstance = serviceControllerInstance.searchServiceTypeByName(serviceInstance);
		// resultInstance = ResultSet Instance
		while (resultInstance.next())
		{
			;
		}
	}

}

