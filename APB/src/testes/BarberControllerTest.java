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
	
	// barberInstance - Instance of the "Barber" class
	Barber barberInstance = new Barber(); 

	// Method used to set up the parameters for the test
	@Before
	public void setUp() 
	{
		try 
		{
			barberInstance.setBarberName("Alessandro");
			barberInstance.setBarberRg("418757896");
			barberInstance.setBarberTelephone("3389-9085");
			barberInstance.setBarberCpf("02919594150");
			barberInstance.setBarberChair("5");
		} 
		catch (NullPointerException e)
		{
			e.printStackTrace();
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}

	// barberController - Instance of the "BarberController" class
	BarberController barberController = BarberController.getInstance (); 
	
	// Method used to check if the current instance is being returned
	@Test
	public void getInstanceOfBarberController() 
	{
		assertEquals(BarberController.getInstance(), barberController);
	}

	// Method used to test if a barber is being added
	@Test
	public void addFromBarberController() 
	{
		try 
		{
			assertTrue(barberController.includeBarber(barberInstance));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to test if a barber is being deleted
	@Test
	public void deleteFromBarberController () 
	{
		try 
		{
			assertTrue(barberController.deleteBarber(barberInstance));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method used to test if a barber is being edited
	@Test
	public void editFromBarberController() 
	{
		try 
		{
			assertTrue(barberController.modifyBarber(barberInstance.getBarberName(), barberInstance));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to test if barber is null when adding
	@Test
	public void addNotNullBarber()
	{
		try 
		{
			assertFalse(barberController.includeBarber(null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Method used to test if barber is null when deleting
	@Test
	public void deleteNotNullBarber() 
	{
		try 
		{
			assertFalse(barberController.deleteBarber(null));
		} 
		catch (SQLException e)
		{
			e.printStackTrace() ;
		}
	}

	@Test
	// Method used to test if barber is null when editing
	public void editNotNullBarber()
	{
		try 
		{
			assertFalse(barberController.modifyBarber(null, null)) ;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method that test if a barber is being displayed
	@Test
	public void searchByBarberController()
		throws SQLException 
	{
		// resultInstance = ResultSet Instance
		ResultSet resultInstance = barberController.searchBarbers();
		
		while (resultInstance.next())
		{
			;
		}
	}
	
	// Method that test if a barber is being displayed
	@Test
	public void displayBarberFromController()
		throws SQLException 
	{
		// resultInstance = ResultSet Instance
		ResultSet resultInstance = barberController.showRegisteredBarbers(barberInstance);
		
		while (resultInstance.next())
		{
			;
		}
	}

	// Method used to test if a barber is being displayed when searched by name
	@Test
	public void searchByBarberNameController()
		throws SQLException 
	{
		// resultInstance = ResultSet Instance
		ResultSet resultInstance = barberController.searchBarberByName(barberInstance);
		
		while (resultInstance.next())
		{
			;
		}
	}
	
}

