package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PhonebookDAO;
import model.Phonebook;


public class PhonebookController 
{
	
	private static PhonebookController instance;
	
	private PhonebookController() 
	{
		
	}
	
    // Method that takes as parameter the object schedule and includes in the database
	public boolean includeNewObject (Phonebook phonebook) throws SQLException 
	{
		if(phonebook == null)
		{
			
			return false;
		}
		
		// If the object is not null the calendar method includes it to the database
		else
		{
			PhonebookDAO phonebookDAOInstance = PhonebookDAO.getInstance(); 
			phonebookDAOInstance.includeDataToPhonebook(phonebook);
			
			return true;
		}
	}
	
    /*
     * Method that takes as parameter the name of the contact you want to change and the schedule
     * object and changes it in the database
     */
	public boolean changeContact(String phonebookName, Phonebook phonebook) throws SQLException 
	{
		if (phonebook == null)
		{
			return false;
		}
		
		// If the object is not null the calendar method changes it in the database
		else
		{
			Phonebook phonebookChange = phonebook;
			PhonebookDAO editPhonebookDataInstance = PhonebookDAO.getInstance();
			editPhonebookDataInstance.editPhonebookData(phonebookName, phonebookChange, phonebook);
			
			return true;		
		}
	}
        
    // Method that takes as parameter the object schedule and deletes it in the database
	public  boolean removeContact(Phonebook phonebookContact) throws SQLException 
	{
		if (phonebookContact == null)
		{
			
			return false;
		}
		
		// If the object is not null the calendar method deletes it in the database
		else
		{
			PhonebookDAO deletePhonebookDataInstance = PhonebookDAO.getInstance();
			deletePhonebookDataInstance.deletePhonebookData(phonebookContact);
			
			return true;
		}

	}
	
	// Method that takes an instance of Agenda
	public static PhonebookController getInstance() 
	{
		if(instance == null)
		{
			instance = new PhonebookController();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}
	
    // Interface that provides access to registered contacts and shows them all
	public ResultSet registeredshowContacts(Phonebook contact) throws SQLException 
	{
		PhonebookDAO showRegisteredContactsInstance = PhonebookDAO.getInstance();
		ResultSet showRegisteredContactsResult = showRegisteredContactsInstance.showRegisteredContacts(contact);
		
		return showRegisteredContactsResult;
	}
	
    // Interface that provides access to registered contacts and allows them to be searched by name
	public ResultSet searchContactForName(Phonebook contact) throws SQLException 
	{
		PhonebookDAO searchByNameInstance =PhonebookDAO.getInstance();
		ResultSet searchByNameResult = searchByNameInstance.searchByName(contact);
		
		return searchByNameResult;
	}
	
    // Interface that provides access to registered contacts and allows them to be surveyed by phone
	public ResultSet searchForPhone(Phonebook contact) throws SQLException 
	{
		PhonebookDAO searchByPhoneInstance = PhonebookDAO.getInstance();
		ResultSet searchPhoneResult  = searchByPhoneInstance.searchByPhone(contact);
		
		return searchPhoneResult;
	}

}
