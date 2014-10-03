
package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import java.text.ParseException;

import model.DoneService;

import exception.ServiceException;

public class DoneServiceTest
{
	DoneService service = new DoneService();
	
	@Test
	// Test the class constructor
	public void constructorTest()
	{
		// Used to test the constructor
		DoneService service1 = new DoneService("Corte", "15,00","Claudio");
		
		assertEquals( "Corte", service1.getServiceName() );
		assertEquals( "15,00", service1.getPrice() );
		assertEquals( "Claudio", service1.getBarberName() );
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServiceName' don't accept null argument
	public void setterDoneServiceNameTestForNullArgument() throws ServiceException
	{
		service.setServiceName(null);
		Assert.fail("Deve lan�ar exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServiceName' don't accept blank names
	public void setterDoneServiceNameTestForBlankArgument() throws ServiceException
	{
		service.setServiceName("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServiceName' don't accept nonstandard names
	public void setterDoneServiceNameTestForInvalidArgument() throws ServiceException
	{
		service.setServiceName("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test
	// Test if the setter of 'doneServiceName' accept legal names and test the setter result
	public void setterDoneServiceNomeTestValidArgument()
	{
		try
		{
			service.setServiceName("Corte");
		}
		catch (ServiceException e)
		{
			e.printStackTrace();
			Assert.fail("N�o deve lan�ar exce��o");
		}
		
		assertEquals( "Corte", service.getServiceName() );
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServicePrice' don't accept ilegal prices
	public void setterDoneServicePriceTestForInvalidArgument() throws ServiceException
	{
		service.setPrice("as");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServicePrice' don't accept null argument
	public void setterDoneServicePriceTestForNullArgument() throws ServiceException
	{
		service.setPrice(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServicePrice' don't accept blank prices
	public void setterDoneServicePriceTestForBlankArgument() throws ServiceException
	{
		service.setPrice("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test
	// Test if the setter of 'doneServicePrice' accept legal prices and test the setter result
	public void setterDoneServicePriceTestForValidArgument()
	{
		try
		{
			service.setPrice("123,45");
		}
		catch (ServiceException e)
		{
			Assert.fail("N�o deve lan�ar exce��o");
		}
		
		assertEquals( "123,45", service.getPrice() );
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'barberName' don't accept null argument
	public void setterBarberNameTestForNullArgument() throws ServiceException
	{
		service.setBarberName(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'barberName' don't accept blank barber names
	public void setterBarberNameTestForBlankArgument() throws ServiceException
	{
		service.setBarberName("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'barberName' don't accept ilegal names
	public void setterBarberNameTestForInvalidArgument() throws ServiceException
	{
		service.setBarberName("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = AssertionFailedError.class)
	//  Test if the setter of 'barberName' accept legal names and test the setter result
	public void setterBarberNameTestForValidArgument()
	{
		try
		{
			service.setBarberName("Jo�o");
		}
		catch (ServiceException e)
		{
			Assert.fail("Não deve lançar uma exceção");
		}
		
		assertEquals( "Jo�o", service.getBarberName() );
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServiceDate' don't accept null arguments
	public void setterDoneServiceDateTestForNullArgument() throws ServiceException
	{
		try
		{
			service.setDate(null);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		catch (AssertionFailedError e)
		{
			e.printStackTrace();
		}
		
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServiceDate' don't accept blank date
	public void setterDoneServiceDateTestForBlankArgument() throws ServiceException
	{
		try
		{
			service.setDate("");
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		catch (AssertionFailedError e)
		{
			e.printStackTrace();
		}
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServiceException.class)
	// Test if the setter of 'doneServiceDate' don't accept ilegal types of date
	public void setterDoneServiceDateTestForInvalidArgument() throws ServiceException
	{
		try
		{
			service.setDate("abc");
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		catch (AssertionFailedError e)
		{
			e.printStackTrace();
		}
		
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test
	// Test if the date conversion to ABNT format is correct
	public void dateConversionToAbntTest()
	{
		try
		{
			service.convertServiceDateToABNT("2010-10-10");
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}

	@Test
	// Test if the setter of 'doneServiceDate' accept legal types of date
	public void setterDoneServiceDateTestForValidArgument()
	{
		try
		{
			service.setDate("10/10/2012");
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

	@Test (expected = AssertionError.class)
	// Test if the getter of 'doneServiceDate' returns the true inserted date
	public void getterDoneServiceDateTest()
	{
		assertEquals( "10/10/2012", service.getDate() );
	}

}
