
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
	Phonebook contactOfPhonebook = new Phonebook();

	@Before
	// Initialize the attributes of 'contact'
	public void setUp()
	{
		try
		{
			contactOfPhonebook.setPhonebookName("Corte");
			contactOfPhonebook.setPhonebook("3895-5698");
			contactOfPhonebook.setPhonebookDs("AAA");
		}
		catch (BarberException exception)
		{
			exception.printStackTrace();
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
	 * Check the return of method 'incluir'
	 */
	public void includeContactMethodTest()
	{
		try
		{
			assertTrue( contactController.includeNewObject(contactOfPhonebook) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
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
			assertTrue( contactController.removeContact(contactOfPhonebook) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
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
			assertTrue( contactController.changeContact(contactOfPhonebook.getPhonebookName(),contactOfPhonebook) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}

	@Test
	// Test if the method 'incluir' don't accept null argument
	public void includeContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse( contactController.includeNewObject(null) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}

	@Test
	// Test if the method 'remove' don't accept null argument
	public void deleteContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse( contactController.removeContact(null) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}

	@Test
	// Test if the method 'alterar' don't accept null argument
	public void modifyContactMethodTestForNullArgument()
	{
		try
		{
			assertFalse( contactController.changeContact(null,null) );
		}
		catch (SQLException exception)
		{
			exception.printStackTrace();
		}
	}

	@Test
	// Test the method 'mostrarContatosCadastrados'
	public void showRegisteredContactsTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.registeredshowContacts(contactOfPhonebook);
		
		while( queryForContactsResult.next() );
	}

	@Test
	// Test the method 'pesquisarPorNome' 
	public void searchContactsByNameTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.searchContactForName(contactOfPhonebook);
		
		while( queryForContactsResult.next() );
	}

	@Test
	// Test the method 'pesquisarPorTelefone'
	public void searchContactsByPhoneTest() throws SQLException
	{
		// Used to receive the result from the search for contacts on DB
		ResultSet queryForContactsResult = contactController.searchForPhone(contactOfPhonebook);
		
		while( queryForContactsResult.next() );
	}

}
