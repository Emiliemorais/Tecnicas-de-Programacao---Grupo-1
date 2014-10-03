package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import exception.BarberException;
import exception.ServiceException;
import model.Phonebook;

public class PhonebookTest 
{
	
	Phonebook contactOfPhonebook = new Phonebook();
	
	/* 
	 *Method used to get the attributes correctly, an Agenda for the test

	*/
	@Before
	public void setUp ()
	{
		try 
		{
			contactOfPhonebook.setPhonebookName("Alessandro");
			contactOfPhonebook.setPhonebook("4568-9856");
		} 
		catch (BarberException e1) 
		{
			e1.printStackTrace();
		}
		contactOfPhonebook.setPhonebookDs("ASDAS");
	}
	
	 
	//Method used to test the operation of the builder of an Agenda
	@Test
	public void testeBuilderFunction() 
	{
		Phonebook contato = new Phonebook("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getPhonebookName());
		assertEquals("6589-5689", contato.getPhonebook());
		assertEquals("aaaa", contato.getPhonebookDs());
	}
	
	// Method that tests the receipt of a name of a contact by the access method get
	@Test
	public void getterNameFunction ()
	{
		assertEquals("Alessandro", contactOfPhonebook.getPhonebookName());
	}
	
	// Method that tests an incoming phone a contact by the access method get

	@Test
	public void getterPhoneFunction ()
	{
		assertEquals("4568-9856", contactOfPhonebook.getPhonebook());
	}
	
	// Method that tests receiving a description of a contact by the access method get

	@Test
	public void getterDescriptionFunction ()
	{
		assertEquals("ASDAS", contactOfPhonebook.getPhonebookDs());
	}
	
	/* 
	 * Method used to receive a blank name for the test launch of the exception

	*/ 
	@Test(expected = BarberException.class)
	public void nameBarberNotBlank () throws BarberException
	{
		contactOfPhonebook.setPhonebookName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * Method used to receive a phone blank for the test launch of the exception

	*/ 
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotNull () throws BarberException
	{
		contactOfPhonebook.setPhonebook("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * Method used to get a name out of the established format (special characters) for the test launch of the exception

	*/ 
	@Test(expected = BarberException.class)
	public void nameOfBarberNotPassOutFormat () throws BarberException
	{
		contactOfPhonebook.setPhonebookName("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * Method used to receive a telephone outside the established format (special characters, letters) for the test launch of the exception

	*/
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotPassOutFormat () throws BarberException
	{
		contactOfPhonebook.setPhonebook("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests receiving the temporary name for the access method get

	@Test (expected = AssertionError.class)
	public void getterReturnValueForNameTemporary () throws ServiceException 
	{
		assertEquals("Barba", Phonebook.getNameTemporary());
	}
	
	
	// Method that tests passing a null temporary name for access method in September
 
	@Test (expected = AssertionFailedError.class)
	public void setterTemporatyNameNotNull () throws ServiceException 
	{
		Phonebook.setNameTemporary(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Method that tests the passage of a temporary blank name for access method in September

	@Test (expected = AssertionFailedError.class)
	public void setterTemporaryNameNotNull () 
	{
		Phonebook.setNameTemporary("");
		Assert.fail("Deve lançar exceção");
	}
	
	// M�todo que testa a passagem de um nome tempor�rio pelo m�todo de acesso set
	@Test
	public void invalidNameTemporary () throws BarberException 
	{
		Phonebook.setNameTemporary("Paulo");
		assertEquals("Paulo", Phonebook.getNameTemporary());
	}

}
