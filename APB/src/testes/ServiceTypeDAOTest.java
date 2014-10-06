package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceType;

import org.junit.Test;

import dao.ServiceTypeDAO;

public class ServiceTypeDAOTest 
{

	ServiceType serviceType = new ServiceType();
	ServiceType serviceType2 = new ServiceType();
	
	// Used in test to get access to 'TipoServico'DAO methods
	ServiceTypeDAO serviceTypeDAO = ServiceTypeDAO.getInstance();
	
	// Test if a instance previous declared is the current one
	@Test
	public void getInstanceMethodTest() 
	{
		ServiceTypeDAO serviceTypeDAOInstance =  ServiceTypeDAO.getInstance();
		
		assertEquals( serviceTypeDAOInstance, serviceTypeDAO );
	}
	
	// Test if a inclusion of a service type was made right
	@Test
	public void includeServiceTypeMethodTest() 
	{
		try 
		{
			assertTrue( serviceTypeDAO.includeServiceType(serviceType) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if a exclusion of a service type was made right
	@Test
	public void deleteServiceTypeMethodTest() 
	{
		try 
		{
			assertTrue( serviceTypeDAO.deleteServiceType(serviceType) );
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if a modification of a service type was made right
	@Test
	public void modifyServiceTypeMethodTest() 
	{
		try 
		{
			String serviceTypeName = serviceType.getServiceTypeName();
			
			assertTrue( serviceTypeDAO.editServiceType( serviceTypeName, serviceType, serviceType2) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'includeServiceType' don't accept null argument
	@Test
	public void includeServiceTypeMethodTestForNullArgument() 
	{
		try 
		{
			assertFalse( serviceTypeDAO.includeServiceType(null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'excluir' don't accept null argument
	@Test
	public void deleteServiceTypeMethodTestForNullArgument() 
	{
		try 
		{
			assertFalse( serviceTypeDAO.deleteServiceType(null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'editServiceType' don't accept null argument
	@Test
	public void modifyServiceTypeMethodTestForNullArgument() 
	{
		try 
		{
			String serviceTypeName = serviceType.getServiceTypeName();
			
			assertFalse( serviceTypeDAO.editServiceType( serviceTypeName, serviceType, null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'editServiceType' don't accept as null argument 'tipoServico'
	@Test
	public void modifyServiceTypeMethodTestForNullArgumentChangedService() 
	{
		try 
		{
			String serviceTypeName = serviceType.getServiceTypeName();
			
			assertFalse( serviceTypeDAO.editServiceType( serviceTypeName, null, serviceType) );
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	 /* 
	  * Test the method 'displayRegisteredTypesOfService' 
	  * (Test if the query result, on field name, is not null)
	  */ 
	@Test
	public void showRegisteredServicesTypesMethodTest() 
	{
		try 
		{
			// Used to receive the result from the search for service types on DB
			ResultSet queryForServiceTypesResult = serviceTypeDAO.displayRegisteredTypesOfService(serviceType);
			
			while( queryForServiceTypesResult.next() ) 
			{
				// Receive the service type name from a query result
				String serviceTypeName = queryForServiceTypesResult.getString("nome");
				
				assertNotNull(serviceTypeName);
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Test the method 'searchByName'
	 * (Test if the query result, on field name, is not null) 
	 */
	@Test
	public void searchServiceTypeByName() 
	{
		try 
		{
			// Used to receive the result from the search for service types on DB 
			ResultSet queryForServiceTypesResult = serviceTypeDAO.searchByName(serviceType);
			
			while ( queryForServiceTypesResult.next() ) 
			{
				// Receive the service type name from a query result
				String serviceTypeName = queryForServiceTypesResult.getString("nome");
				assertNotNull(serviceTypeName);
			}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
