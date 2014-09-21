package testes;

import static org.junit.Assert.*;

import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import exception.ReportException;

public class ReportTest 
{

	Report reportInstance; // reportInstance - Instance of the "Report" class 

	@Before
	// Method used to set up the parameters for the test
	public void setUp () throws ParseException 
	{
		try 
		{
			reportInstance = new Report (); 

			reportInstance.setBarberName ( "Chico" );
			reportInstance.setServiceType ( "barba" );
			reportInstance.setInitialDate ( "01/01/2013" );
			reportInstance.setFinalDate ( "09/09/2013");

		} 
		catch  ( NullPointerException e ) 
		{
			e.printStackTrace();
		} 
		catch  ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test ( expected = NullPointerException.class )
	// Method that tests if the initial date is null
	public void initialNotNullDate() throws NullPointerException,
		ParseException 
	{
		try 
		{
			reportInstance.setInitialDate(null);
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace();
		}
	}

	@Test( expected = AssertionError.class )
	// Method that tests if the initial date is blank
	public void initialNotBlankDate ()
		throws NullPointerException, ParseException 
	{
		try 
		{
			reportInstance.setInitialDate("");
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace();
		}
	}

	@Test(expected = NullPointerException.class)
	// Method that tests if the final date is null
	public void finalNotNullDate () throws NullPointerException,
		ParseException 
	{
		try 
		{
			reportInstance.setFinalDate(null);
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace();
		}
	}

	@Test( expected = AssertionError.class )
	// Method that tests if the final date is blank
	public void finalNotBlankDate () throws NullPointerException,
		ParseException 
	{
		try 
		{
			reportInstance.setFinalDate("");
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace();
		}
	}

	@Test( expected = NullPointerException.class)
	// Method that tests if the barber is null
	public void notNullBarber() 
	{
		try 
		{
			reportInstance.setBarberName ( null );
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test( expected = AssertionError.class )
	// Method that tests if the barber is blank
	public void notBlankBarber () 
	{
		try 
		{
			reportInstance.setBarberName("");
		} 
		catch ( ReportException e )
		{
			e.printStackTrace();
		}
	}

	@Test( expected = NullPointerException.class )
	// Method that tests if the service type is null
	public void notNullServiceType ()
	{
		try 
		{
			reportInstance.setServiceType( null );
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test( expected = AssertionError.class )
	// Method that tests if the service type is blank
	public void notBlankServiceType () 
	{
		try 
		{
			reportInstance.setServiceType("");
		} 
		catch ( ReportException e )
		{
			e.printStackTrace();
		}
	}

	@Test( expected = IllegalArgumentException.class )
	// Method that tests if the report constructor is null
	public void notNullBarberReportConstructor () 
	{
		try 
		{
			new Report ( "2013-01-01", "2013-01-01", null, "barba" );
		} 
		catch ( ReportException e ) {
			e.printStackTrace ();
		}
	}

	@Test( expected = IllegalArgumentException.class )
	// Method that tests if the report constructor final date is null
	public void notNullFinalDateReportConstructor() 
	{
		try 
		{
			new Report ( "2013-01-01", null, "Chico", "barba" );
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test ( expected = IllegalArgumentException.class )
	// Method that tests if the report constructor initial date is null
	public void notNullInitialDateReportConstructor ()
	{
		try
		{
			new Report(null, "2013-01-01", "Chico", "barba");
		} 
		catch (ReportException e) 
		{
			e.printStackTrace();
		}
	}

	@Test( expected = IllegalArgumentException.class )
	// Method that tests if the report constructor service type is null
	public void notNullServiceTypeReportConstructor () 
	{
		try 
		{
			new Report ( "2013-01-01", "2013-01-01", "Chico", null );
		} 
		catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method that tests if all data from constructor are correct
	public void allCorrectReportConstructor () 
	{
		try 
		{
			new Report ( "2013-01-01", "2013-12-31", "Chico", "barba" );
			assertEquals ( reportInstance, reportInstance );
		} 
		catch (ReportException e) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method that test the barber getter and setter
	public void barberGetterTester () 
	{
		assertEquals ( "Chico", reportInstance.getBarberName () );
	}

	@Test
	// Method that test the service type getter and setter
	public void serviceTypeGetterTester () 
	{
		assertEquals ( "barba", reportInstance.getServiceType () );
	}

	@Test
	// Method that test the initial date getter and setter
	public void initialDateGetterTester ()
	{
		assertEquals ( "2013-01-01", reportInstance.getInitialDate () );
	}

	@Test
	// Method that test the final date getter and setter
	public void finalDateGetterTester() 
	{
		assertEquals( "2013-09-09", reportInstance.getFinalDate () );
	}

	@Test
	// Method that tests the format of the date to be converted
	public void dateToConvertTest () 
	{
		try 
		{
			reportInstance.convertDateToAbntFormat( "2010-10-10" );
		} 
		catch ( ParseException e )
		{
			e.printStackTrace();
		}
	}

}

