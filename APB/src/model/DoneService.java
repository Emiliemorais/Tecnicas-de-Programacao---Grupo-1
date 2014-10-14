package model;

import java.util.Date;

import exception.ServiceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DoneService
{
	// Receives the service name
	private String serviceName; 
	
	// Receives the barber�s name
	private String barberName; 
	
	// Receives the service price
	private String servicePrice; 
	
	// Receives the date of the service
	private String serviceDate; 
	
	// Error constants
	private final String invalidName = "Nome do Serviço Inválido"; 
	private final String blankName = "Nome do Serviço em Branco"; 
	private final String invalidBarber = "Nome do Barbeiro em Branco"; 
	private final String blankBarber = "Insira um Barbeiro responsável pelo serviço"; 
	private final String invalidPrice = "Preço Inválido"; 
	private final String blankPrice = "Preço em Branco"; 
	private final String blankDate = "Data em Branco"; 
	private final String invalidDate = "Insira uma data válida"; 
	
	public DoneService () 
	{

	}

	// Class constructor
	public DoneService(String serviceName, String servicePrice, String barberName) 
	{
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.barberName = barberName;
	}

	// Service name getter
	public String getServiceName() 
	{
		return serviceName;
	}

	// Barber name getter
	public String getBarberName() 
	{
		return barberName;
	}
	
	// Price getter
	public String getPrice() 
	{
		return servicePrice;
	}
	
	// Date getter
	public String getDate() 
	{
		return serviceDate;
	}

	// Service name setter
	public void setServiceName(String serviceName) throws ServiceException 
	{
		if( serviceName == null )
		{
			throw new NullPointerException(blankName);
		}
		else if( "".equals(serviceName) )
		{
			throw new ServiceException(blankName);
		}
		else if( serviceName.matches("^[[ ]|\\p{L}*]+$") )
		{
			this.serviceName = serviceName;
		}
		else
		{
			throw new ServiceException(invalidName);
		}
	}

	// Barber name setter
	public void setBarberName(String barberName) throws ServiceException 
	{
		if( barberName == null )
		{
			throw new NullPointerException(blankBarber);
		}
		else if( "".equals(barberName) )
		{
			throw new ServiceException(blankBarber);
		}
		else if( barberName.matches("^[[ ]|\\p{L}*]+$") )
		{
			this.barberName = barberName;
		}
		else
		{
			throw new ServiceException(invalidBarber);
		}
	}

	// Price setter
	public void setPrice(String servicePrice) throws ServiceException 
	{
		if( servicePrice == null )
		{
			throw new NullPointerException (blankPrice);
		}
		else if( "".equals(servicePrice) )
		{
			throw new ServiceException(blankPrice);
		}
		else if( servicePrice.matches("[\\d]{1,3},[\\d]{1,2}") )
		{
			this.servicePrice = servicePrice;
		}
		else
		{
			throw new ServiceException(invalidPrice);
		}
	}

	// Date setter
	public void setDate(String serviceDate) throws ServiceException , ParseException 
	{
		if( serviceDate == null )
		{
			throw new NullPointerException(blankDate);
		}
		else if( "".equals(serviceDate) )
		{
			throw new ServiceException(blankDate);
		}
		else if(serviceDate.matches("[\\d]{1,4}-[\\d]{1,2}-[\\d]{1,2}") ) 
		{
			this.serviceDate = serviceDate;
		}
		else if( serviceDate.matches("[\\d]{1,2}/[\\d]{1,2}/[\\d]{1,4}") ) 
		{
			this.serviceDate = convertServiceDate(serviceDate);
		}
		else
		{
			throw new ServiceException(invalidDate);
		}
	}
	
	/**
	 * Convert the date to yyyy-MM-dd format
	 * @param serviceDate - Date to be converted
	 */
	public String convertServiceDate(String serviceDate) throws ParseException 
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateISO = simpleDateFormat.parse(serviceDate);

		SimpleDateFormat secondSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String secondDateISO = secondSimpleDateFormat.format(dateISO);
		
		return secondDateISO;
	}

	/**
	 * Method to convert service date to ABNT
	 * @param serviceDate - Date to be converted
	 */
	public String convertServiceDateToABNT ( String serviceDate ) throws ParseException 
	{
		
		// simpleDateFormat = Date in the format: yyyy/mm/dd
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// dateISO - Receives intel from "serviceDate" variable
		Date dateISO = simpleDateFormat.parse(serviceDate);
		
		// secondSimpleDateFormat - Date in the format: dd/mm/yyyy
		SimpleDateFormat secondSimpleDateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
		
		// dateABNT - Date in the brazillian format pattern
		String dateABNT = secondSimpleDateFormat.format(dateISO);
		
		return dateABNT;
	}
	
}
