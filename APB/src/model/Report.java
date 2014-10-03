
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import exception.ReportException;

public class Report
{
	// Contains the initial date
	private String initialDate;
	
	// Contains the final date
	private String finalDate;
	
	// Contains the barber name responsible by the service
	private String barberName;
	
	// Contains the service type
	private String serviceType;
	
	// Constants that keeps an error message
	private final String BLANK_FINAL_DATE = "Data final em Branco";
	private final String BLANK_INITIAL_DATE = "Data inicial em Branco";
	private final String BLANK_BARBER_NAME = "Barbeiro em Branco";
	private final String BLANK_SERVICE_TYPE = "Tipo do Serviço em Branco";

	// Class constructor
	public Report(String initialDate, String finalDate, String barberName,
				  String serviceType) throws ReportException
    {
		// super();
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.barberName = barberName;
		this.serviceType = serviceType;

		if( this.initialDate == null )
        {
            throw new IllegalArgumentException(BLANK_INITIAL_DATE);
        }
		else
        {
            // Nothing to do
        }
		
		if( this.finalDate == null )
        {
            throw new IllegalArgumentException(BLANK_FINAL_DATE);
        }
		else
        {
            // Nothing to do
        }
		
		if( this.barberName == null )
        {
            throw new IllegalArgumentException(BLANK_BARBER_NAME);
        }
		else
        {
            // Nothing to do
        }
		
		if( this.serviceType == null )
        {
            throw new IllegalArgumentException(BLANK_SERVICE_TYPE);
        }
        else
        {
            // Nothing to do
        }
    }
	
	// General class constructor
	public Report()
	{
	}

	// Getter of 'initialDate'
	public String getInitialDate()
	{
		return initialDate;
	}

	// Setter of 'initialDate'
	public void setInitialDate(String initialDate) throws ReportException,
														  NullPointerException,
														  ParseException
    {
		if( initialDate == null )
        {
            throw new NullPointerException(BLANK_INITIAL_DATE);
        }
		else if( "".equals(initialDate) )
        {
            throw new AssertionError(BLANK_INITIAL_DATE);
        }
		else
        {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// Date on 'dd/MM/yyyy' format
			Date initialDateIso = simpleDateFormat.parse(initialDate);

			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			
			// Date on 'yyyy-MM-dd' format
			String initialDateISO = simpleDateFormat2.format(initialDateIso);

			this.initialDate = initialDateISO;
		}
	}


	// Getter of 'finalDate'
	public String getFinalDate()
	{
		return finalDate;
	}

	
	// Setter of 'finalDate'
 	public void setFinalDate(String finalDate) throws ReportException,
 													  NullPointerException,
													  ParseException
    {
		if( finalDate == null )
        {
            throw new NullPointerException(BLANK_FINAL_DATE);
        }
		else if( "".equals(finalDate) )
        {
            throw new AssertionError(BLANK_FINAL_DATE);
        }
		else
        {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			// Date on 'dd/MM/yyyy' format
			Date finalDateIso = simpleDateFormat.parse(finalDate);

			SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			
			// Date on 'yyyy-MM-dd' format
			String finalDateISO = simpleDateFormat2.format(finalDateIso);

			this.finalDate = finalDateISO;
		}
	}

 	// Getter of 'barberName'
	public String getBarberName()
	{
		return barberName;
	}


	// Setter of 'barberName'
	public void setBarberName(String barberName) throws ReportException
	{
		if( barberName == null )
        {
            throw new NullPointerException(BLANK_BARBER_NAME);
        }
		else if( "".equals(barberName) )
        {
            throw new AssertionError(BLANK_BARBER_NAME);
        }
		else
        {
            this.barberName = barberName;
        }
	}

	// Getter of 'serviceType'
	public String getServiceType()
	{
		return serviceType;
	}


	// Setter of 'serviceType'
	public void setServiceType(String serviceType) throws ReportException
	{
		if( serviceType == null )
        {
            throw new NullPointerException(BLANK_SERVICE_TYPE);
        }
		else if( "".equals(serviceType) )
        {
            throw new AssertionError(BLANK_SERVICE_TYPE);
        }
		else
        {
            this.serviceType = serviceType;
        }
	}


	/**
	 * Convert a date to ABNT format
	 * @param dateToConvert - Date to be converted to ABNT format
	 */
	public String convertDateToAbntFormat(String dateToConvert) throws ParseException
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// Date on 'yyyy-MM-dd' format
		Date dateISO = simpleDateFormat.parse(dateToConvert);

		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		
		// Converted date on the brasilian format 'dd/MM/yyyy'
		String convertedDate = simpleDateFormat2.format(dateISO);

		return convertedDate;
	}

}
