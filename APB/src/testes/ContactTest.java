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
	
	// Initialize contactOfPhonebook object with arbitrary data to use on tests
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
		 
	// Test the class constructor 
	@Test
	public void classConstructorTest() 
	{
		Contact contato = new Contact("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getContactName());
		assertEquals("6589-5689", contato.getContactPhoneNumber());
		assertEquals("aaaa", contato.getContactDescription());
	}
	
	// Test contactName getter
	@Test
	public void getterNameFunction()
	{
		assertEquals("Alessandro", contactOfPhonebook.getContactName());
	}
	
	// Test contactPhoneNumber getter
	@Test
	public void getterPhoneFunction()
	{
		assertEquals("4568-9856", contactOfPhonebook.getContactPhoneNumber());
	}
	
	// Test contactDescription getter
	@Test
	public void getterDescriptionFunction()
	{
		assertEquals("ASDAS", contactOfPhonebook.getContactDescription());
	}
	
	// Test if the contactName setter does not accept an empty argument
	@Test(expected = BarberException.class)
	public void nameBarberNotBlank () throws BarberException
	{
		contactOfPhonebook.setContactName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Test if the contactPhoneNumber does not accept an empty argument 
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotNull() throws BarberException
	{
		contactOfPhonebook.setContactPhoneNumber("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Test if the contactName setter does not accept a name out of format
	@Test(expected = BarberException.class)
	public void nameOfBarberNotPassOutFormat() throws BarberException
	{
		contactOfPhonebook.setContactName("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Test if the contactPhoneNumber setter does not accept a number out of format
	@Test(expected = BarberException.class)
	public void phoneOfBarberNotPassOutFormat() throws BarberException
	{
		contactOfPhonebook.setContactPhoneNumber("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Test the temporaryName getter
	@Test (expected = AssertionError.class)
	public void getterReturnValueForNameTemporary() throws ServiceException 
	{
		assertEquals("Barba", Contact.getNameTemporary());
	}
		
	// Test if the temporaryName setter does not accept a null argument
	@Test (expected = AssertionFailedError.class)
	public void setterTemporaryNameNotNull() throws ServiceException 
	{
		Contact.setNameTemporary(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Test if the temporaryName setter does not accept an empty argument
	@Test (expected = AssertionFailedError.class)
	public void setterTemporaryNameNotBlank() 
	{
		Contact.setNameTemporary("");
		Assert.fail("Deve lançar exceção");
	}
	
	// Test the temporaryName setter and getter with a valid argument
	@Test
	public void invalidTemporaryName() throws BarberException 
	{
		Contact.setNameTemporary("Paulo");
		assertEquals("Paulo", Contact.getNameTemporary());
	}

}
