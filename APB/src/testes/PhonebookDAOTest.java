package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

import model.Phonebook;

import org.junit.Test;

import dao.FactoryConnection;
import dao.PhonebookDAO;

public class PhonebookDAOTest 
{
	// contact1 - Instance for a contact from "Phonebook" class
	Phonebook contact1 = new Phonebook(); 
	
	// contact2 - Second instance, for another contact from "Phonebook" class
	Phonebook contact2 = new Phonebook(); 
	
	// phonebookDAO - Gets the instance from "PhonebookDAO" class
	PhonebookDAO phonebookDAO = PhonebookDAO.getInstance(); 

	
	// Method that tests if is returning a instance
	@Test
	public void getInstanceOfPhonebookDAO()
	{
		assertEquals(PhonebookDAO.getInstance(), phonebookDAO);
	}

	// Method that tests if adding a new contact
	@Test
	public void addFromPhonebookDAO () 
	{
		try 
		{
			assertTrue(phonebookDAO.includeDataToPhonebook(contact1));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace() ;
		}
	}
	
	// Method that tests if is sending a phonebook
	@Test
	public void deleteFromPhonebookDAO() 
	{
		try 
		{
			assertTrue(phonebookDAO.deletePhonebookData(contact1));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// Method that tests if is sending a contact
	@Test
	public void editFromPhonebookDAO() 
	{
		try 
		{
			assertTrue(phonebookDAO.editPhonebookData(contact1.getPhonebookName(), contact1, contact2));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Method that adds with a null contact
	@Test
	public void addFromPhonebookDAOWhenInvalidContact() 
	{
		try 
		{
			assertFalse(phonebookDAO.includeDataToPhonebook (null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method that deletes with a null contact
	@Test
	public void deleteFromPhonebookDAOWhenInvalidContact() 
	{
		try 
		{
			assertFalse(phonebookDAO.deletePhonebookData (null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace() ;
		}
	}
	
	// Method that edits with a null contact
	@Test
	public void editFromPhonebookDAOWhenInvalidContact() 
	{
		try 
		{
			assertFalse(phonebookDAO.editPhonebookData(contact1.getPhonebookName(), contact1, null));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method that edits passing a edited phonebook 
	@Test
	public void editFromPhonebookDAOWhenEditedNullPhonebook() 
	{
		try 
		{
			assertFalse(phonebookDAO.editPhonebookData(contact1.getPhonebookName (), null, contact1));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the connection is established is not null
	@Test
	public void createConnectionWithDBTestIfTheConnectionIsNotNull()
	{
		try
		{
			Connection connectionToTest = phonebookDAO.createConnectionWithDB();
			
			assertNotNull("This connection should not be null", connectionToTest);
			
			connectionToTest.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if the connection is valid
	@Test
	public void createConnectionWithDBTestIfTheConnectionisValid()
	{
		try
		{
			Connection connectionToTest = phonebookDAO.createConnectionWithDB();
			
			assertTrue("This connection should be active", connectionToTest.isValid(10));
			
			connectionToTest.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if the connection is closed
	@Test
	public void createConnectionWithDBTestIfTheConnectionisClosed()
	{
		try
		{
			Connection connectionToTest = phonebookDAO.createConnectionWithDB();
			
			connectionToTest.close();
			
			assertTrue("This connection should be closed", connectionToTest.isClosed());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Method to test if the contacts are being displayed
	@Test
	public void showRegisteredContactsTest()
	{
		try 
		{			
			// resultInstance - ResultSet Instance
			ResultSet resultInstance = phonebookDAO.showRegisteredContacts(contact1);

			while (resultInstance.next()) 
			{
				// contactName - Contains the contact´s name
				String contactName = resultInstance.getString("nome");
				
				assertNotNull(contactName);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method to test if the contacts are being displayed
	@Test
	public void searchByNameFromPhonebookDAO() 
	{
		try 
		{
			// resultInstance - ResultSet Instance
			ResultSet resultInstance = phonebookDAO.searchByName(contact1);

			while (resultInstance.next()) 
			{
				// contactName - Receives the contact name to the search
				String contactName = resultInstance.getString("nome");
				assertNotNull(contactName) ;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Method to test if barbers are being displayed when searched by their phone numbers
	@Test
	public void searchByNumberFromBarberDAO() 
	{
		try 
		{			
			// resultInstance - ResultSet Instance
			ResultSet resultInstance = phonebookDAO.searchByPhone(contact1);

			while (resultInstance.next()) 
			{
				// contactName - Receives the contact name to the search
				String contactName = resultInstance.getString("nome");
				assertNotNull(contactName);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}

