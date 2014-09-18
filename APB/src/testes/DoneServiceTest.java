
package testes;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import java.text.ParseException;

import model.ServicoPrestado;

import exception.ServicoException;

public class DoneServiceTest
{
	ServicoPrestado service = new ServicoPrestado();
	
	@Test
	// Test the class constructor
	public void constructorTest()
	{
		// Used to test the constructor
		ServicoPrestado service1 = new ServicoPrestado("Corte", "15,00","Claudio");
		
		assertEquals("Corte", service1.getNomeServico());
		assertEquals("15,00", service1.getPreco());
		assertEquals("Claudio", service1.getNomeBarbeiro());
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServiceName' don't accept null argument
	public void setterDoneServiceNameTestForNullArgument() throws ServicoException
	{
		service.setNomeServico(null);
		Assert.fail("Deve lan�ar exce��o");
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServiceName' don't accept blank names
	public void setterDoneServiceNameTestForBlankArgument() throws ServicoException
	{
		service.setNomeServico("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServiceName' don't accept nonstandard names
	public void setterDoneServiceNameTestForInvalidArgument() throws ServicoException
	{
		service.setNomeServico("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test
	// Test if the setter of 'doneServiceName' accept legal names and test the setter result
	public void setterDoneServiceNomeTestValidArgument()
	{
		try
		{
			service.setNomeServico("Corte");
		}
		catch (ServicoException e)
		{
			e.printStackTrace();
			Assert.fail("N�o deve lan�ar exce��o");
		}
		assertEquals("Corte", service.getNomeServico());
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServicePrice' don't accept ilegal prices
	public void setterDoneServicePriceTestForInvalidArgument() throws ServicoException
	{
		service.setPreco("as");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServicePrice' don't accept null argument
	public void setterDoneServicePriceTestForNullArgument() throws ServicoException
	{
		service.setPreco(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServicePrice' don't accept blank prices
	public void setterDoneServicePriceTestForBlankArgument() throws ServicoException
	{
		service.setPreco("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test
	// Test if the setter of 'doneServicePrice' accept legal prices and test the setter result
	public void setterDoneServicePriceTestForValidArgument()
	{
		try
		{
			service.setPreco("123,45");
		}
		catch (ServicoException e)
		{
			Assert.fail("N�o deve lan�ar exce��o");
		}
		assertEquals("123,45", service.getPreco());
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'barberName' don't accept null argument
	public void setterBarberNameTestForNullArgument() throws ServicoException
	{
		service.setNomeBarbeiro(null);
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'barberName' don't accept blank barber names
	public void setterBarberNameTestForBlankArgument() throws ServicoException
	{
		service.setNomeBarbeiro("");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = ServicoException.class)
	// Test if the setter of 'barberName' don't accept ilegal names
	public void setterBarberNameTestForInvalidArgument() throws ServicoException
	{
		service.setNomeBarbeiro("123");
		Assert.fail("Deve lan�ar uma exce��o");
	}

	@Test (expected = AssertionFailedError.class)
	//  Test if the setter of 'barberName' accept legal names and test the setter result
	public void setterBarberNameTestForValidArgument()
	{
		try
		{
			service.setNomeBarbeiro("Jo�o");
		}
		catch (ServicoException e)
		{
			Assert.fail("Não deve lançar uma exceção");
		}
		assertEquals("Jo�o", service.getNomeBarbeiro());
	}

	@Test (expected = NullPointerException.class)
	// Test if the setter of 'doneServiceDate' don't accept null arguments
	public void setterDoneServiceDateTestForNullArgument() throws ServicoException
	{
		try
		{
			service.setData(null);
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

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServiceDate' don't accept blank date
	public void setterDoneServiceDateTestForBlankArgument() throws ServicoException
	{
		try
		{
			service.setData("");
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

	@Test (expected = ServicoException.class)
	// Test if the setter of 'doneServiceDate' don't accept ilegal types of date
	public void setterDoneServiceDateTestForInvalidArgument() throws ServicoException
	{
		try
		{
			service.setData("abc");
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
			service.ConverterDataParaABNT("2010-10-10");
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
			service.setData("10/10/2012");
		}
		catch (ServicoException e)
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
		assertEquals("10/10/2012", service.getData());
	}

}
