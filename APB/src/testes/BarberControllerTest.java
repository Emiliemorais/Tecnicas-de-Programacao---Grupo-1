package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barber;

import org.junit.Before;
import org.junit.Test;

import control.BarberController;
import exception.BarberException;

public class BarberControllerTest 
{

	Barber barberInstance = new Barber(); // barberInstance - Instance of the "Barber" class

	@Before
	// Method used to set up the parameters for the test
	public void setUp () 
	{
		try 
		{
			barberInstance.setBarberName ( "Alessandro" );
			barberInstance.setBarberRg ( "418757896" );
			barberInstance.setBarberTelephone ( "3389-9085" );
			barberInstance.setBarberCpf ( "02919594150" );
			barberInstance.setBarberChair ( "5" );
		} 
		catch ( NullPointerException e )
		{
			e.printStackTrace ();
		} 
		catch ( BarberException e ) 
		{
			e.printStackTrace ();
		}
	}

	BarberController barberController = BarberController.getInstance (); // barberController - Instance of the "BarberController" class

	@Test
	// Method used to check if the current instance is being returned
	public void getInstanceOfBarberController () 
	{
		assertEquals( BarberController.getInstance (), barberController );
	}

	@Test
	// Method used to test if a barber is being added
	public void addFromBarberController () 
	{
		try 
		{
			assertTrue ( barberController.includeBarber ( barberInstance ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method used to test if a barber is being deleted
	public void deleteFromBarberController () 
	{
		try 
		{
			assertTrue ( barberController.deleteBarber ( barberInstance ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}
	
	@Test
	// Method used to test if a barber is being edited
	public void editFromBarberController () 
	{
		try 
		{
			assertTrue ( barberController.alterar(barberInstance.getBarberName (), barberInstance ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method used to test if barber is null when adding
	public void addNotNullBarber ()
	{
		try 
		{
			assertFalse( barberController.includeBarber ( null ) );
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Test
	// Method used to test if barber is null when deleting
	public void deleteNotNullBarber () 
	{
		try 
		{
			assertFalse ( barberController.deleteBarber ( null ) );
		} 
		catch ( SQLException e )
		{
			e.printStackTrace () ;
		}
	}

	@Test
	// Method used to test if barber is null when editing
	public void editNotNullBarber()
	{
		try 
		{
			assertFalse( barberController.alterar( null, null ) ) ;
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}
	
	@Test
	// Method that test if a barber is being displayed
	public void searchByBarberController ()
		throws SQLException 
	{
		ResultSet resultInstance = barberController.searchBarbers ( );
		// resultInstance = ResultSet Instance
		while ( resultInstance.next () )
		{
			;
		}
	}

	@Test
	// Method that test if a barber is being displayed
	public void displayBarberFromController ()
		throws SQLException 
	{
		ResultSet resultInstance = barberController.showRegisteredBarbers ( barberInstance );
		// resultInstance = ResultSet Instance
		while ( resultInstance.next () )
		{
			;
		}
	}

	@Test
	// Method used to test if a barber is being displayed when searched by name
	public void searchByBarberNameController ()
		throws SQLException 
	{
		ResultSet resultInstance = barberController.searchBarberByName ( barberInstance );
		// resultInstance = ResultSet Instance
		while ( resultInstance.next () )
		{
			;
		}
	}
	
}

