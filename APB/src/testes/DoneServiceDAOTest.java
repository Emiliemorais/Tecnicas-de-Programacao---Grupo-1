package testes;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import model.GivenService;

import org.junit.Before;
import org.junit.Test;

import dao.DoneServiceDAO;
import exception.ServiceException;

public class DoneServiceDAOTest
{

	// Given Service class's instance to access the class
	GivenService doneService = new GivenService();
	GivenService doneService2 = new GivenService();
	
	/* 
	 * Método utilizado para receber os atributos de um Serviço Prestado de forma correta,
	 * para realização do teste, lançando exceçoes de atributos nulos e problemas de conversão
	*/
	@Before
	public void setUp () 
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
		catch (NullPointerException e)
		{
			e.printStackTrace();
		} 
		catch (ServiceException e)
		{
			e.printStackTrace();
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	DoneServiceDAO doneServiceDAO = DoneServiceDAO.getInstance();
	
	/* 
	 * Método utilizado para testar o getInstance da classe ServicoPrestadoDAO
	*/
	@Test
	public void getInstanceTest ()
	{
		assertEquals(DoneServiceDAO.getInstance(), doneServiceDAO);
	}

	/* 
	 * Método utilizado para testar a inserçao correta de um Servico Prestado
	*/
	@Test
	public void includeDoneServiceTest ()
	{
		try 
		{
			assertTrue(doneServiceDAO.includeServiceType(doneService));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para testar a exclusão correta de um Servico Prestado
	*/
	@Test
	public void deleteDoneServiceTest () 
	{
		try 
		{
			assertTrue(doneServiceDAO.deleteServiceType(doneService));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Método utilizado para testar a inserçao de um Servico nulo na classe Serviço Prestado
	*/
	@Test
	public void includeDoneServiceNullTest () 
	{
		try 
		{
			assertFalse(doneServiceDAO.includeServiceType(null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Método utilizado para testar a exclusão de um Servico nulo na classe Serviço Prestado
	*/
	@Test
	public void deleteDoneServiceNullTest () 
	{
		try
		{
			assertFalse(doneServiceDAO.deleteServiceType(null));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
