package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ContactDAO;
import model.Contact;


public class ContactController 
{
	
	private static ContactController instance;
	
	// Class constructor
	private ContactController() 
	{
		
	}
	
    /**
     * Include a new contact on the database
     * @param contact - Contact to be included
     * @return true if the inclusion was made right or false if it does not.
     * @throws SQLException
     */
	public boolean includeContact (Contact contact) throws SQLException 
	{
		if(contact == null)
		{
			return false;
		}
		else
		{
			ContactDAO phonebookDAOInstance = ContactDAO.getInstance(); 
			phonebookDAOInstance.includeDataToPhonebook(contact);
			
			return true;
		}
	}
	
	/**
	 * Change a contact on the database
	 * @param contactToChangeName - Name of the contact that will be replaced
	 * @param newContact - New contact to replace the old one
	 * @return true if the changes was made right or false if it does not.
	 * @throws SQLException
	 */
	public boolean changeContact(String contactToChangeName, Contact newContact) throws SQLException 
	{
		if (newContact == null)
		{
			return false;
		}
		else
		{
			Contact phonebookChange = newContact;
			ContactDAO editPhonebookDataInstance = ContactDAO.getInstance();
			editPhonebookDataInstance.editPhonebookData(contactToChangeName, phonebookChange, newContact);
			
			return true;		
		}
	}
        
    /**
     * Remove a contact from database
     * @param contactToBeRemoved - The contact to be deleted
     * @return true if the exclusion was made right or false if it does not.
     * @throws SQLException
     */
	public boolean removeContact(Contact contactToBeRemoved) throws SQLException 
	{
		if (contactToBeRemoved == null)
		{
			return false;
		}
		else
		{
			ContactDAO deletePhonebookDataInstance = ContactDAO.getInstance();
			deletePhonebookDataInstance.deletePhonebookData(contactToBeRemoved);
			
			return true;
		}

	}
	
	/**
	 * @return The current instance if it exists or instantiate a new one if does not 
	 */
	public static ContactController getInstance() 
	{
		if(instance == null)
		{
			instance = new ContactController();
		}
		else
		{
			// Nothing to do because if 'instance' is not null, there is something inside it 
		}
		
		return instance;
	}
	
    /**
     * Provides access to the registered contacts on the database
     * @return a ResultSet object with all contacts registered on the database
     * @throws SQLException
     */
	public ResultSet showRegisteredContacts() throws SQLException 
	{
		ContactDAO contactDAOInstance = ContactDAO.getInstance();
		ResultSet showRegisteredContactsResult = contactDAOInstance.showRegisteredContacts();
		
		return showRegisteredContactsResult;
	}
	
    /**
     * Search for a contact by it's name
     * @param contact - Contact to be searched
     * @return a ResultSet object with all contacts with the given contact name
     * @throws SQLException
     */
	public ResultSet searchContactForName(Contact contact) throws SQLException 
	{
		ContactDAO contactDAOInstance =ContactDAO.getInstance();
		ResultSet searchByNameResult = contactDAOInstance.searchByName(contact);
		
		return searchByNameResult;
	}
	
    /**
     * Search for a contact by it's phone number 
     * @param contact - Contact to be searched
     * @return a ResultSet object with all contacts with the given contact phone number
     * @throws SQLException
     */
	public ResultSet searchForPhone(Contact contact) throws SQLException 
	{
		ContactDAO contactDAOInstance = ContactDAO.getInstance();
		ResultSet searchPhoneResult  = contactDAOInstance.searchByPhone(contact);
		
		return searchPhoneResult;
	}

}
