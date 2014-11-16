package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import exception.BarberException;
import exception.ServiceException;
import model.Contact;

public class ContactTest 
{
	
	Contact contactOfPhonebook = new Contact();
	
	// Method used to get the attributes correctly, an Agenda for the test
	@Before
	public void setUp()
	{
		try 
		{
			contactOfPhonebook.setContactName("Alessandro");
			contactOfPhonebook.setContactPhoneNumber("4568-9856");
		} 
		catch (BarberException secondException) 
		{
			secondException.printStackTrace();
		}
		contactOfPhonebook.setContactDescription("ASDAS");
	}
		 
	// Method used to test the operation of the builder of an Agenda
	@Test
	public void testeBuilderFunction() 
	{
		Contact contato = new Contact("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getContactName());
		assertEquals("6589-5689", contato.getContactPhoneNumber());
		assertEquals("aaaa", contato.getContactDescription());
	}
	
	// Method that tests the receipt of a name of a contact by the access method get
	@Test
	public void getterNameFunction()
	{
		assertEquals("Alessandro", contactOfPhonebook.getContactName());
	}
	
	// Method that tests an incoming phone a contact by the access method get
	@Test
	public void getterPhoneFunction()
	{
		assertEquals("4568-9856", contactOfPhonebook.getContactPhoneNumber());
	}
	
	// Method that tests receiving a description of a contact by the access method get
	@Test
	public void getterDescriptionFunction()
	{
		assertEquals("ASDAS", contactOfPhonebook.getContactDescription());
	}
	
	// Method used to receive a blank name for the test launch of the exception 
	@Test(expected = BarberException.class)
	public void nameBarberNotBlank () throws BarberException
	{
		contactOfPhonebook.setContactName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method used to receive a phone blank for the test launch of the exception 
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotNull() throws BarberException
	{
		contactOfPhonebook.setContactPhoneNumber("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method used to get a name out of the established format (special characters) for the test launch of the exception 
	@Test(expected = BarberException.class)
	public void nameOfBarberNotPassOutFormat() throws BarberException
	{
		contactOfPhonebook.setContactName("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method used to receive a telephone outside the established format (special characters, letters) for the test launch of the exception
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotPassOutFormat () throws BarberException
	{
		contactOfPhonebook.setContactPhoneNumber("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests receiving the temporary name through the set access method
	@Test (expected = AssertionError.class)
	public void getterReturnValueForNameTemporary() throws ServiceException 
	{
		assertEquals("Barba", Contact.getNameTemporary());
	}
		
	// Method that tests passing a null temporary name through the set access method
	@Test (expected = AssertionFailedError.class)
	public void setterTemporatyNameNotNull () throws ServiceException 
	{
		Contact.setNameTemporary(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Method that tests the passage of a temporary blank name through the set access method
	@Test (expected = AssertionFailedError.class)
	public void setterTemporaryNameNotNull () 
	{
		Contact.setNameTemporary("");
		Assert.fail("Deve lançar exceção");
	}
	
	// Method that tests the passege of a temporary name through the set access method
	@Test
	public void invalidNameTemporary () throws BarberException 
	{
		Contact.setNameTemporary("Paulo");
		assertEquals("Paulo", Contact.getNameTemporary());
	}

}
