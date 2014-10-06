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

	
	// Name of the invalid service
	private final String invalidName = "Nome do Serviço Inválido"; 
	
	// Name of the blank service
	private final String blankName = "Nome do Serviço em Branco"; 
	
	// Shows that there is no barber�s name registered
	private final String invalidBarber = "Nome do Barbeiro em Branco"; 
	
	// Receives a barber�s name
	private final String blankBarber = "Insira um Barbeiro responsável pelo serviço"; 
	
	// Shows that the price is invalid
	private final String invalidPrice = "Preço Inválido"; 
	
	// Shows that there is no price registered
	private final String blankPrice = "Preço em Branco"; 
	
	// Shows that there is no date registered
	private final String blankDate = "Data em Branco"; 
	
	// Receives a new date
	private final String invalidDate = "Insira uma data válida"; 
	

	public DoneService () 
	{

	}

	// Method receiving and setting the parameters from the given service
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
	public void setServiceName(String serviceName) 
		throws ServiceException 
	{
		if (serviceName == null)
		{
			throw new NullPointerException(blankName);
		}
		else if ("".equals(serviceName))
		{
			throw new ServiceException(blankName);
		}
		else if (serviceName.matches("^[[ ]|\\p{L}*]+$"))
		{
			this.serviceName = serviceName;
		}
		else
		{
			throw new ServiceException(invalidName);
		}
	}

	// Barber name setter
	public void setBarberName(String barberName) 
		throws ServiceException 
	{
		if (barberName == null)
		{
			throw new NullPointerException(blankBarber);
		}
		else if ("".equals(barberName))
		{
			throw new ServiceException(blankBarber);
		}
		else if (barberName.matches("^[[ ]|\\p{L}*]+$"))
		{
			this.barberName = barberName;
		}
		else
		{
			throw new ServiceException(invalidBarber);
		}
	}

	// Price setter
	public void setPrice(String servicePrice) 
		throws ServiceException 
	{
		if (servicePrice == null)
		{
			throw new NullPointerException (blankPrice);
		}
		else if ("".equals(servicePrice))
		{
			throw new ServiceException(blankPrice);
		}
		else if (servicePrice.matches("[\\d]{1,3},[\\d]{1,2}"))
		{
			this.servicePrice = servicePrice;
		}
		else
		{
			throw new ServiceException(invalidPrice);
		}
	}

	// Date setter
	public void setDate(String serviceDate) 
		throws ServiceException , ParseException 
	{

		if (serviceDate == null)
		{
			throw new NullPointerException(blankDate);
		}
		else if ("".equals(serviceDate))
		{
			throw new ServiceException(blankDate);
		}
		else if (serviceDate.matches("[\\d]{1,4}-[\\d]{1,2}-[\\d]{1,2}")) 
		{
			this.serviceDate = serviceDate;
		}
		else if (serviceDate.matches("[\\d]{1,2}/[\\d]{1,2}/[\\d]{1,4}")) 
		{
			// simpleDateFormat = Date in the format: yyyy/mm/dd
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// dateISO - Receives intel from "serviceDate" variable
			Date dateISO = simpleDateFormat.parse(serviceDate);
			
			// secondSimpleDateFormat - Date in the format: dd/mm/yyyy
			SimpleDateFormat secondSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			// dateISO - Receives intel from "dateISO" variable
			String secondDateISO = secondSimpleDateFormat.format(dateISO);

			this.serviceDate = secondDateISO;
		}
		else
		{
			throw new ServiceException(invalidDate);
		}
	}

	// Method to convert service date to ABNT
	public String convertServiceDateToABNT ( String serviceDate )
		throws ParseException 
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
