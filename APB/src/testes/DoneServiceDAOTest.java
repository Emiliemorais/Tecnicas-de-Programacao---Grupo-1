package testes;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import model.ServicoPrestado;

import org.junit.Before;
import org.junit.Test;

import dao.DoneServiceDAO;
import exception.ServiceException;

public class DoneServiceDAOTest
{

	// Given Service class's instance to access the class
	ServicoPrestado doneService = new ServicoPrestado();
	ServicoPrestado doneService2 = new ServicoPrestado();
	
	/* 
	 * M�todo utilizado para receber os atributos de um Servi�o Prestado de forma correta,
	 * para realiza��o do teste, lan�ando exce�oes de atributos nulos e problemas de convers�o
	*/
	@Before
	public void setUp () 
	{
		try
		{
			doneService.setNomeServico("Corte");
			doneService.setNomeBarbeiro("Alessandro");
			doneService.setData("10/10/2010");
			doneService.setPreco("10,00");
			doneService2.setNomeServico("Barba");
			doneService2.setNomeBarbeiro("Luciano");
			doneService2.setData("01/01/2010");
			doneService2.setPreco("9,90");
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
	 * M�todo utilizado para testar o getInstance da classe ServicoPrestadoDAO
	*/
	@Test
	public void getInstanceTest ()
	{
		assertEquals(DoneServiceDAO.getInstance(), doneServiceDAO);
	}

	/* 
	 * M�todo utilizado para testar a inser�ao correta de um Servico Prestado
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
	 * M�todo utilizado para testar a exclus�o correta de um Servico Prestado
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
	 * M�todo utilizado para testar a inser�ao de um Servico nulo na classe Servi�o Prestado
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
	 * M�todo utilizado para testar a exclus�o de um Servico nulo na classe Servi�o Prestado
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
