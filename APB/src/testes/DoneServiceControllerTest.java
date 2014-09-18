
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.ServicoPrestado;
import control.ServicoPrestadoController;
import exception.ServicoException;

public class DoneServiceControllerTest
{
	
	ServicoPrestado doneService = new ServicoPrestado();
	
	// Used in the test to get access to the methods of class 'ServicoPrestadoController.java'
	ServicoPrestadoController doneServiceController = ServicoPrestadoController.getInstance();


	@Before
	// Initialize the attributes of 'doneService'
	public void setUp() throws ServicoException, ParseException
	{
		doneService.setNomeServico("Corte");
		doneService.setNomeBarbeiro("Joao");
		doneService.setPreco("125,23");
		doneService.setData("20/12/2013");
	}


	@Test
	// Test if a instance previous declared is the current one
	public void getInstanceMethodTest()
	{
		assertEquals(ServicoPrestadoController.getInstance(), doneServiceController);
	}

	@Test
	/* 
	 * Test if a inclusion of a contact was made right
	 * Check the return of method 'inserir'
	 */
	public void includeDoneServiceMethodTest()
	{
		try
		{
			assertTrue(doneServiceController.inserir(doneService));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	/* 
	 * Test if a exclusion of a contact was made right
	 * Check the return of method 'excluir'
	 */
	public void deleteDoneServiceMethodTest()
	{
		try
		{
			assertTrue(doneServiceController.excluir(doneService));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'inserir' don't accept null argument
	public void includeDoneServiceTestForNullArgument()
	{
		try
		{
			assertFalse(doneServiceController.inserir(null));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'excluir' don't accept null argument
	public void deleteDoneServiceTestForNullArgument()
	{
		try
		{
			assertFalse(doneServiceController.excluir(null));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test the method 'mostrarServicosPrestadosCadastrados' is working
	public void showRegisteredDoneServicesMethodTest() throws SQLException
	{
		// Used to receive the result from the search for done services on DB
		ResultSet queryForDoneServicesResult = doneServiceController.mostrarServicosPrestadosCadastrados(doneService);
		
		while( queryForDoneServicesResult.next() );
	}
	
}
