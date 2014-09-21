package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;

import org.junit.Test;

import dao.PhonebookDAO;

public class PhonebookDAOTest 
{

	Agenda contact1 = new Agenda (); // contact1 - Instance for a contact from "Phonebook" class
	Agenda contact2 = new Agenda (); // contact2 - Second instance, for another contact from "Phonebook" class
	PhonebookDAO phonebookDAO = PhonebookDAO.getInstance (); // phonebookDAO - Gets the instance from "PhonebookDAO" class

	@Test
	// Method that tests if is returning a instance
	public void getInstanceOfPhonebookDAO ()
	{
		assertEquals ( PhonebookDAO.getInstance ( ), phonebookDAO );
	}

	@Test
	// Method that tests if adding a new contact
	public void addFromPhonebookDAO () 
	{
		try 
		{
			assertTrue( phonebookDAO.includeDataToPhonebook ( contact1 ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace () ;
		}
	}

	@Test
	// Method that tests if is sending a phonebook
	public void deleteFromPhonebookDAO () 
	{
		try 
		{
			assertTrue ( phonebookDAO.deletePhonebookData ( contact1 ) );
		} 
		catch ( SQLException e )
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method that tests if is sending a contact
	public void editFromPhonebookDAO () 
	{
		try 
		{
			assertTrue ( phonebookDAO.editPhonebookData ( contact1.getNome(), contact1, contact2) );
		} 
		catch ( SQLException e )
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method that adds with a null contact
	public void addFromPhonebookDAOWhenInvalidContact () 
	{
		try 
		{
			assertFalse ( phonebookDAO.includeDataToPhonebook (null) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}
	
	@Test
	// Method that deletes with a null contact
	public void deleteFromPhonebookDAOWhenInvalidContact () 
	{
		try 
		{
			assertFalse(phonebookDAO.deletePhonebookData ( null ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace () ;
		}
	}
	
	@Test
	// Method that edits with a null contact
	public void editFromPhonebookDAOWhenInvalidContact() 
	{
		try 
		{
			assertFalse( phonebookDAO.editPhonebookData ( contact1.getNome (), contact1, null ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method that edits passing a edited phonebook 
	public void editFromPhonebookDAOWhenEditedNullPhonebook () 
	{
		try 
		{
			assertFalse ( phonebookDAO.editPhonebookData ( contact1.getNome (), null, contact1 ) );
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method to test if the contacts are being displayed
	public void showPhonebookContactsDAO ()
	{
		try 
		{
			ResultSet resultInstance = phonebookDAO.showRegisteredContacts ( contact1 );
			// resultInstance - ResultSet Instance

			while ( resultInstance.next () ) 
			{
				String contactName = resultInstance.getString( "nome" );
				// contactName - Contains the contact´s name
				assertNotNull ( contactName );
			}
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace  ();
		}
	}

	@Test
	// Method to test if the contacts are being displayed
	public void searchByNameFromPhonebookDAO () 
	{
		try 
		{
			ResultSet resultInstance = phonebookDAO.searchByName ( contact1 );
			// resultInstance - ResultSet Instance

			while (resultInstance.next()) 
			{
				String contactName = resultInstance.getString ( "nome" );
				// contactName - Receives the contact name to the search
				assertNotNull ( contactName ) ;
			}
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	@Test
	// Method to test if barbers are being displayed when searched by their phone numbers
	public void searchByNumberFromBarberDAO () 
	{
		try 
		{
			ResultSet resultInstance = phonebookDAO.searchByPhone ( contact1 );
			// resultInstance - ResultSet Instance

			while ( resultInstance.next() ) 
			{
				String contactName = resultInstance.getString( "nome" );
				// contactName - Receives the contact name to the search
				assertNotNull( contactName );
			}
		} 
		catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

}

