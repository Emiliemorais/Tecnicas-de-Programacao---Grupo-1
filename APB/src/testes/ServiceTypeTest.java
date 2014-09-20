package testes;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;
import model.ServiceType;

import org.junit.Before;
import org.junit.Test;

import exception.ServicoException;


public class ServiceTypeTest 
{
	// Service Type class's instance to access the class
	ServiceType  serviceType =  new ServiceType();
	
	@Before
	/*
	 * Initialize a service type
	 */
	public void setUp()
	{
		try
		{
			serviceType.setServiceTypeName("Corte");
			serviceType.setServiceTypePrice("14,50");
		} 
		catch (ServicoException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/* 
	 * Define an assertive that compares the value of the service type ('Corte') included
	 *   with the service type registered 
	 */
	public void getterServiceTypeTest()
	{
		assertEquals( "Corte", serviceType.getServiceTypeName() );
	}
	
	@Test
	/*
	 * Define an assertive that compares the value of the price ('14,50') included
	 *   with the service type registered 
	 */
	public void getterPriceServiceTypeTest()
	{
		assertEquals( "14,50", serviceType.getServiceTypePrice() );
	}
	

	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define an assertive that throws a exception if the price of the service type is null
	 */
	public void setterPriceNullTest() throws ServicoException
	{
		serviceType.setServiceTypePrice(null);
		Assert.fail("Deve lançar exceção");
	}
	

	@Test (expected = NullPointerException.class)
	
	/* 
	 *	Define an assertive that throws a exception if the service type name is null
	 */
	public void setterServiceTypeNameTest() throws ServicoException 
	{
		serviceType.setServiceTypeName(null);
		Assert.fail("Deve lançar exceção");
	}
	

	@Test (expected = IllegalArgumentException.class)
	
	/*
	 * Define an assertive that throws a exception if the service type price is invalid
	 */
	public void setterPriceInvalidTest() throws ServicoException 
	{
		serviceType.setServiceTypePrice("14.50%");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test (expected =  ServicoException.class)
	
	/*
	 *	Define an assertive that throws a exception if the  service type price is blank
	 */
	public void setterPriceBlankTest() throws ServicoException
	{
		serviceType.setServiceTypePrice("");
		Assert.fail("Deve lançar exceção");
	}
	

	@Test (expected =  ServicoException.class)
	
	/*
	 * Define an assertive that throws a exception if the service type name is blank
	 */
	public void setterServiceTypeNameBlankTest() throws ServicoException
	{
		serviceType.setServiceTypeName("");
		Assert.fail("Deve lançar exceção");
	}
	

	@Test (expected = AssertionError.class)
	
	/* 
	 * Define an assertive that compares the value of the service type temporary name ('Corte') included
	 *   with the service type temporary name registered   
	 */
	public void getterTemporaryNameTest() throws ServicoException
	{
		assertEquals( "Corte", ServiceType.getTemporaryName() );
	}
	

	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define an assertive that throws a exception if the service type temporary name is null
	 */
	public void setterTemporaryNameNullTest() throws ServicoException 
	{
		ServiceType.setTemporaryName(null);
		Assert.fail("Deve lançar exceção");
	}
	

	@Test (expected = ServicoException.class)
	
	/* 
	 * Define an assertive that throws a exception if the service type temporary name is blank
	 */
	public void setterTemporaryNameBlankTest() throws ServicoException
	{
		ServiceType.setTemporaryName("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test
	
	/* 
	  * Define an assertive that throws a exception if the service type temporary name
	  *  is different of the registered
	 */
	public void validateTemporaryName() 
	{
		try
		{
			ServiceType.setTemporaryName("Barba");
		} 
		catch (ServicoException e)
		{
			e.printStackTrace();
			Assert.fail("Não Deve lançar exceção");
		}
		assertEquals( "Barba", ServiceType.getTemporaryName() );
	}

}
