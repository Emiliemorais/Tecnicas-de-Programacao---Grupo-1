package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import exception.BarberException;
import exception.ServiceException;
import model.Contact;
import model.Barber;

import org.junit.Before;
import org.junit.Test;

public class BarberTest 
{

	Barber barber;
		 
	// Method used to get the attributes of a Barber correctly to the test
	@Before
	public void setUp()
	{
		try 
		{
			barber = new Barber();
			barber.setBarberName("Alessandro");
			barber.setBarberRg("418757896");
			barber.setBarberTelephone("3389-9085");
			barber.setBarberCpf("02919594150");
			barber.setBarberChair("10");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null name of a Barber for the test launch of the exception
	@Test (expected = NullPointerException.class)
	public void barberCantHaveNameNullBySetter()
	{
		try 
		{
			barber.setBarberName(null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null CPF a Barber for the test launch of the exception
	@Test (expected = NullPointerException.class)
	public void testCPFNull() 
	{
		try 
		{
			barber.setBarberCpf(null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null RG a Barber for the test launch of the exception
	@Test (expected = NullPointerException.class)
	public void testRGNull ()
	{
		try
		{
			barber.setBarberRg(null);
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null number of a Barber for the test launch of the exception
	@Test (expected = NullPointerException.class)
	public void barberCantHavePhoneNullBySetter() 
	{
		try
		{
			barber.setBarberTelephone(null);
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null chair of a Barber for the test launch of the exception	
	@Test (expected = NullPointerException.class)
	public void testChairNull()
	{
		try 
		{
			barber.setBarberChair(null);
		}
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null name in the constructor of a Barber for the test launch of the exception
	@Test (expected = IllegalArgumentException.class)
	public void builderWithNullName() 
	{
		try 
		{
			new Barber(null, "493.751.185-84", "2258256", "3389-9085", "10");
		} 
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}

	// Method used to receive a null in the constructor of a CPF Barber for the test launch of the exception	
	@Test (expected = IllegalArgumentException.class)
	public void builderWithNullCpf()
	{
		try 
		{
			new Barber("Alessandro", null, "2258256", "3389-9085", "10");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a null in the constructor of a RG Barber for the test launch of the exception
	@Test (expected = IllegalArgumentException.class)
	public void builderWithNullRG() 
	{
		try
		{
			new Barber("Alessandro", "493.751.185-84", null, "3389-9085", "10");
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to receive a null phone in the constructor of a Barber for the test launch of the exception	
	@Test(expected = IllegalArgumentException.class)
	public void builderWithNullFhone() 
	{	
		try 
		{
			new Barber("Alessandro", "493.751.185-84", "2258256", null, "10");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to receive a null in the constructor of a chair barber for the test launch of the exception
	@Test (expected = IllegalArgumentException.class)
	public void builderWithNullChair() 
	{
		try 
		{
			new Barber("Alessandro", "493.751.185-84", "2258256", "3389-9085", null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a CPF invalid a Barber for the test launch of the exception
	@Test (expected = AssertionError.class)
	public void testInvalidCPF() 
	{
		try 
		{
			barber.setBarberCpf("000000000");
			fail();
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive an RG containing letters for the test launch of the exception
	@Test (expected = AssertionError.class)
	public void testLettersCPF() 
	{
		try 
		{
			barber.setBarberRg("4654654ASD");
		} 
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}
	
	// Method used to receive a chair that contains letters for the test launch of the exception
	@Test (expected = AssertionError.class)
	public void numberChairNotNull()
	{
		try 
		{
			barber.setBarberChair("asd");
		} 
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}

	// Method used to receive a phone containing letters for the test launch of the exception
	@Test (expected = AssertionError.class)
	public void phoneNotConterLetter() 
	{
		try
		{
			barber.setBarberTelephone("65465a4");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method that tests receiving a name Barber get the access method
	@Test
	public void testGetterNameBarber() 
	{
		assertEquals("Alessandro", barber.getBarberName());
	}
	
	// Method that tests receiving a CPF Barber get the access method
	@Test
	public void testGetterCPFBarber()
	{
		assertEquals("02919594150", barber.getBarberCpf());
	}
	
	// Method that tests an incoming RG Barber get the access method
	@Test
	public void testGetterRGBarber() 
	{
		assertEquals("418757896", barber.getBarberRg());
	}
	
	// Method that tests an incoming phone Barber get the access method
	@Test
	public void testGetterPhoneBarber() 
	{
		assertEquals("3389-9085", barber.getBarberTelephone());
	}
	
	// Method that tests the receipt of a chair by Barber access method get
	@Test
	public void testGetterChairBarber()
	{
		assertEquals("10", barber.getBarberChair());
	}
	
	// Method that tests receiving a temporary name Barber get the access method 
	@Test
	public void testForGetterTheTemporaryNameTheBarber()
	{
		assertEquals(null, Barber.getTemporaryName());
	}
	
	// Method testing the passage of a name through the set access method
	@Test
	public void setBarberFunction()
	{
		try 
		{
			barber.setBarberName("Alessandro");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
		assertEquals("Alessandro", barber.getBarberName());
	}
	
	// Method that tests the passage of a name with the number through the set access method
	@Test(expected = BarberException.class)
	public void testNameWithNumbers() throws BarberException 
	{
		barber.setBarberName("J040");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a CPF blank Barber through the set access method
	@Test(expected =  BarberException.class)
	public void blankCPF() throws BarberException
	{
		barber.setBarberCpf("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a CPF Barber invalid through the set access method
	@Test(expected =  BarberException.class)
	public void invalidCPF() throws BarberException 
	{
		barber.setBarberCpf("123.654.456-75");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a rg with letters Barber through the set access method
	@Test(expected =  AssertionError.class)
	public void rgWithLetters() throws BarberException 
	{
		barber.setBarberRg("asasa");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a rg blank Barber through the set access method
	@Test(expected =  BarberException.class)
	public void rgBlank() throws BarberException
	{
		barber.setBarberRg("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a blank name Barber through the set access method
	@Test(expected =  BarberException.class)
	public void nameBlank() throws BarberException
	{
		barber.setBarberName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passing of a phone blank Barber through the set access method
	@Test(expected =  BarberException.class)
	public void phoneBlank() throws BarberException
	{
		barber.setBarberTelephone("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a chair on white Barber through the set access method
	@Test(expected =  BarberException.class)
	public void chairBlank() throws BarberException 
	{
		barber.setBarberChair("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a Barber chair with zero value through the set access method
	@Test(expected =  AssertionError.class)
	public void chairWithZero() throws BarberException 
	{
		barber.setBarberChair("0");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a chair with more than two digits through the set access method
	@Test(expected = BarberException.class)
	public void chairPassTwoDigits() throws BarberException
	{
		barber.setBarberChair("1000");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests receiving the temporary name through the set access method
	@Test(expected = AssertionError.class)
	public void testeReturnName() throws ServiceException
	{
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests passing a null temporary name
	@Test(expected = AssertionFailedError.class)
	public void setterNomeTemporaryNotNull () throws ServiceException 
	{
		Barber.setTemporaryName(null);
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a temporary blank name through the set access method
	@Test(expected = AssertionFailedError.class)
	public void setterNomeTemporaryNotBlank () 
	{
		Barber.setTemporaryName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a temporary name by Barber through the set access method
	@Test(expected = AssertionError.class)
	public void invalidNameTemporary () throws BarberException
	{
		Barber.setTemporaryName("João");
		assertEquals("João", Contact.getNameTemporary());
	}
}