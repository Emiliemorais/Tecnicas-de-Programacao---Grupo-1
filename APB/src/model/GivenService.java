package model;

import java.util.Date;

import exception.ServiceException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GivenService
{

	private String serviceName; // Receives the service name
	private String barberName; // Receives the barber�s name
	private String servicePrice; // Receives the service price
	private String serviceDate; // Receives the date of the service


	private final String invalidName = "Nome do Serviço Inválido"; // Name of the invalid service
	private final String blankName = "Nome do Serviço em Branco"; // Name of the blank service
	private final String invalidBarber = "Nome do Barbeiro em Branco"; // Shows that there is no barber�s name registered
	private final String blankBarber = "Insira um Barbeiro responsável pelo serviço"; // Receives a barber�s name
	private final String invalidPrice = "Preço Inválido"; // Shows that the price is invalid
	private final String blankPrice = "Preço em Branco"; // Shows that there is no price registered
	private final String blankDate = "Data em Branco"; // Shows that there is no date registered
	private final String invalidDate = "Insira uma data válida"; // Receives a new date

	public GivenService () 
	{

	}

	// Method receiving and setting the parameters from the given service
	public GivenService ( String serviceName, String servicePrice, String barberName ) 
	{
		this.serviceName = serviceName;
		this.servicePrice = servicePrice;
		this.barberName = barberName;
	}

	// Service name getter
	public String getServiceName  () 
	{
		
		return serviceName;
	}

	// Barber name getter
	public String getBarberName () 
	{
		
		return barberName;
	}
	
	// Price getter
	public String getPrice  () 
	{
		
		return servicePrice;
	}
	// Date getter
	public String getDate () 
	{
		
		return serviceDate;
	}

	// Service name setter
	public void setServiceName (  String serviceName ) throws ServiceException 
	{
		if ( serviceName == null )
		{
			throw new NullPointerException ( blankName );
		}
		else if ("".equals ( serviceName ) )
		{
			throw new ServiceException ( blankName );
		}
		else if ( serviceName.matches("^[[ ]|\\p{L}*]+$" ) )
		{
			this.serviceName = serviceName;
		}
		else
		{
			throw new ServiceException ( invalidName );
		}
	}

	// Barber name setter
	public void setBarberName ( String barberName ) throws ServiceException 
	{
		if ( barberName == null )
		{
			throw new NullPointerException ( blankBarber );
		}
		else if ( "".equals(barberName ) )
		{
			throw new ServiceException ( blankBarber );
		}
		else if ( barberName.matches ("^[[ ]|\\p{L}*]+$" ) )
		{
			this.barberName = barberName;
		}
		else
		{
			throw new ServiceException ( invalidBarber );
		}
	}

	// Price setter
	public void setPrice ( String servicePrice ) throws ServiceException 
	{
		if ( servicePrice == null )
		{
			throw new NullPointerException ( blankPrice);
		}
		else if ( "".equals ( servicePrice ) )
		{
			throw new ServiceException ( blankPrice );
		}
		else if (servicePrice.matches ( "[\\d]{1,3},[\\d]{1,2}" ) )
		{
			this.servicePrice = servicePrice;
		}
		else
		{
			throw new ServiceException ( invalidPrice );
		}
	}

	// Date setter
	public void setDate ( String serviceDate ) throws ServiceException, ParseException 
	{

		if ( serviceDate == null )
		{
			throw new NullPointerException ( blankDate );
		}
		else if ("".equals(serviceDate))
		{
			throw new ServiceException ( blankDate );
		}
		else if (serviceDate.matches ( "[\\d]{1,4}-[\\d]{1,2}-[\\d]{1,2}" ) ) 
		{
			this.serviceDate = serviceDate;
		}
		else if (serviceDate.matches ( "[\\d]{1,2}/[\\d]{1,2}/[\\d]{1,4}" ) ) 
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
			// simpleDateFormat = Date in the format: yyyy/mm/dd
			Date dateISO = simpleDateFormat.parse ( serviceDate );
			// dateISO - Receives intel from "serviceDate" variable
			SimpleDateFormat secondSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
			// secondSimpleDateFormat - Date in the format: dd/mm/yyyy
			String secondDateISO = secondSimpleDateFormat.format ( dateISO );
			// dateISO - Receives intel from "dateISO" variable

			this.serviceDate = secondDateISO;
		}
		else
		{
			throw new ServiceException ( invalidDate );
		}
	}

	// Method to convert service date to ABNT
	public String convertServiceDateToABNT ( String serviceDate ) throws ParseException 
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd" );
		// simpleDateFormat = Date in the format: yyyy/mm/dd
		Date dateISO = simpleDateFormat.parse(serviceDate);
		// dateISO - Receives intel from "serviceDate" variable
		
		SimpleDateFormat secondSimpleDateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
		// secondSimpleDateFormat - Date in the format: dd/mm/yyyy
		String dateABNT = secondSimpleDateFormat.format ( dateISO );
		// dateABNT - Date in the brazillian format pattern
		
		return dateABNT;
	}
	
}
