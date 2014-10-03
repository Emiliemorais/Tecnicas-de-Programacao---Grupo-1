
package model;

import exception.ServiceException;

public class ServiceType
{
	// Contains the service type name
	private String serviceTypeName;
	
	// Contains the service type price
	private String serviceTypePrice;
	
	//  Contains a temporary name - ??
	private static String temporaryName;
	
	// Constants that keeps an error message
	private final static String BLANK_SERVICE_TYPE_NAME = "Nome do Servi�o em Branco";
	private final String INVALID_SERVICE_TYPE_PRICE = "Pre�o Inv�lido";
	private final String BLANK_SERVICE_TYPE_PRICE = "Pre�o em Branco";
	
	// General constructor
	public ServiceType()
	{
	}
	

	// Getter of 'serviceTypeName'
	public String getServiceTypeName()
	{
		return serviceTypeName;
	}

	
	// Getter of 'serviceTypePrice'
	public String getServiceTypePrice()
	{
		return serviceTypePrice;
	}


	// Getter of 'temporaryName'
	public static String getTemporaryName()
	{
		return temporaryName;
	}

	// Setter of 'serviceTypeName'
	public void setServiceTypeName(String serviceTypeName) throws ServiceException
	{
		if( serviceTypeName == null )
        {
            throw new NullPointerException(BLANK_SERVICE_TYPE_NAME);
        }
		else if( "".equals(serviceTypeName) )
        {
            throw new ServiceException(BLANK_SERVICE_TYPE_NAME);
        }
		else
        {
            this.serviceTypeName = serviceTypeName;
        }
	}


	// Setter of 'serviceTypePrice'
	public void setServiceTypePrice(String serviceTypePrice) throws ServiceException
	{
		if( serviceTypePrice == null )
        {
            throw new NullPointerException(INVALID_SERVICE_TYPE_PRICE);
        }
		else if( "".equals(serviceTypePrice) )
        {
            throw new ServiceException(BLANK_SERVICE_TYPE_PRICE);
        }
		else if( serviceTypePrice.matches("[\\d]{1,3},[\\d]{1,2}") )
        {
            this.serviceTypePrice = serviceTypePrice;
        }
		else
        {
            throw new IllegalArgumentException("Pre�o deve ser no formato: **,** ");
        }
	}

	
	// Setter of 'temporaryName'
	public static void setTemporaryName(String temporaryName) throws ServiceException
	{
		if( temporaryName == null )
        {
            throw new NullPointerException(BLANK_SERVICE_TYPE_NAME);
        }
		else if( "".equals(temporaryName) )
        {
            throw new ServiceException(BLANK_SERVICE_TYPE_NAME);
        }
		else
        {
            ServiceType.temporaryName = temporaryName;
        }
	}

}
