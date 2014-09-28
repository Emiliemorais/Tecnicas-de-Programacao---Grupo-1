
package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Phonebook;
import control.PhonebookController;
import exception.BarberException;

public class PhonebookControllerTest
{
	Phonebook contact = new Phonebook();

	@Before
	// Initialize the attributes of 'contact'
	public void setUp()
	{
		try
		{
			contact.setPhonebookName("Corte");
			contact.setPhonebook("3895-5698");
			contact.setPhonebookDs("AAA");
		}
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}
	
	// Instantiated to get access to the methods of class 'PhonebookController'
	PhonebookController contactController = PhonebookController.getInstance();

	@Test
	//  Test if a instance previous declared is the current one
	public void getInstanceMethodTest()
	{
		assertEquals(PhonebookController.getInstance(), contactController);
	}

	@Test
	/* 
	 * Test if a inclusion of a contact was made right
	 * Check the return of method 'inserir'
	 */
	public void includeContactMethodTest()
	{
		try
		{
			assertTrue(contactController.incluir(contact));
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
	public void deleteContactMethodTest()
	{
		try
		{
			assertTrue(contactController.excluir(contact));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	/* 
	 * Test if a modification of a contact was made right
	 * Check the return of method 'alterar'
	 */
	public void modifyContactMethodTest()
	{
		try
		{
			assertTrue(contactController.alterar(contact.getPhonebookName(),contact));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'inserir' don't accept null argument
	public void includeContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse(contactController.incluir(null));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'excluir' don't accept null argument
	public void deleteContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse(contactController.excluir(null));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the method 'alterar' don't accept null argument
	public void modifyContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse(contactController.alterar(null,null));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test the method 'mostrarContatosCadastrados'
	public void showRegisteredContactsTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.mostrarContatosCadastrados(contact);
		
		while( queryForContactsResult.next() );
	}

	@Test
	// Test the method 'pesquisarPorNome' 
	public void searchContactsByNameTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.pesquisarPorNome(contact);
		
		while( queryForContactsResult.next() );
	}

	@Test
	// Test the method 'pesquisarPorTelefone'
	public void searchContactsByPhoneTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.pesquisarPorTelefone(contact);
		
		while( queryForContactsResult.next() );
	}

}
