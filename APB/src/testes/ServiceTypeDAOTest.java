package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceType;

import org.junit.Test;

import dao.TipoServicoDAO;

public class ServiceTypeDAOTest 
{

	ServiceType serviceType = new ServiceType();
	ServiceType serviceType2 = new ServiceType();
	
	// Used in test to get access to 'TipoServico'DAO Methods
	TipoServicoDAO serviceTypeDAO = TipoServicoDAO.getInstance();
	
	// Test if a instance previous declared is the current one
	@Test
	public void getInstanceMethodTest() 
	{
		assertEquals( TipoServicoDAO.getInstance(), serviceTypeDAO );
	}
	
	// Test if a inclusion of a service type was made right
	@Test
	public void includeServiceTypeMethodTest() 
	{
		try 
		{
			assertTrue( serviceTypeDAO.incluir(serviceType) );
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
			assertTrue( serviceTypeDAO.excluir(serviceType) );
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
			assertTrue( serviceTypeDAO.alterar(serviceType.getServiceTypeName(),
										   serviceType, serviceType2) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'inserir' don't accept null argument
	@Test
	public void includeServiceTypeMethodTestForNullArgument() 
	{
		try 
		{
			assertFalse( serviceTypeDAO.incluir(null) );
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
			assertFalse( serviceTypeDAO.excluir(null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'alterar'don't accept null argument
	@Test
	public void modifyServiceTypeMethodTestForNullArgument() 
	{
		try 
		{
			assertFalse( serviceTypeDAO.alterar(serviceType.getServiceTypeName(),
											serviceType, null));
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'alterar' don't accept as null argument 'tipoServico'
	@Test
	public void modifyServiceTypeMethodTestForNullArgumentChangedService() 
	{
		try 
		{
			assertFalse( serviceTypeDAO.alterar(serviceType.getServiceTypeName(),
											null, serviceType));
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	 /* 
	  * Test the method 'mostrarTipoServicoCadastrados' 
	  * (Test if the query result, on field name, is not null)
	  */ 
	@Test
	public void showRegisteredServicesTypesMethodTest() 
	{
		try 
		{
			// Used to receive the result from the search for service types on DB
			ResultSet queryForServiceTypesResult = serviceTypeDAO.mostrarTipoServicoCadastrados(serviceType);
			
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
	 * Test the method 'pesquisarPorNome'
	 * (Test if the query result, on field name, is not null) 
	 */
	@Test
	public void searchServiceTypeByName() 
	{
		try 
		{
			// Used to receive the result from the search for service types on DB 
			ResultSet queryForServiceTypesResult = serviceTypeDAO.pesquisarPorNome(serviceType);
			
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
