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
	
    // Method that takes as a parameter the object schedule and includes the database
	public boolean incluir (Phonebook phonebook) throws SQLException 
	{
		if(phonebook == null)
		{
			return false;
		}
		
		// If the object is not null the calendar method includes the database
		else
		{
			PhonebookDAO phonebookDAOInstance =PhonebookDAO.getInstance(); 
			phonebookDAOInstance.includeDataToPhonebook(phonebook);
			
			return true;
		}
	}
	
    // Method that takes as a parameter the name of the contact you want to change and the schedule object and changes in the database
	public boolean alterar (String phonebookName, Phonebook phonebook) throws SQLException 
	{
		if(phonebook == null)
		{
			return false;
		}
		
		// If the object is not null the calendar method changes in the database
		else
		{
			Phonebook agenda_alterado = phonebook;
			PhonebookDAO editPhonebookDataInstance = PhonebookDAO.getInstance();
			editPhonebookDataInstance.editPhonebookData(phonebookName, agenda_alterado, phonebook);
			return true;		
		}
	}
        
        // Method that takes as a parameter the object schedule and deletes the database
	public  boolean remove (Phonebook phonebookContact) throws SQLException 
	{
		if(phonebookContact == null)
		{
			return false;
		}
		

		// If the object is not null the calendar method deletes the database
		else
		{
			PhonebookDAO deletePhonebookDataInstance = PhonebookDAO.getInstance();
			deletePhonebookDataInstance.deletePhonebookData(phonebookContact);
			return true;
		}

	}
	
	// Method that takes an instance of an Agenda
	public static PhonebookController getInstance () 
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
	
        // Interface that provides access to registered contacts and shows all contacts
	public ResultSet mostrarContatosCadastrados(Phonebook contact) throws SQLException 
	{
		PhonebookDAO showRegisteredContactsInstance = PhonebookDAO.getInstance();
		ResultSet showRegisteredContactsResult = showRegisteredContactsInstance.showRegisteredContacts(contact);
		
		return showRegisteredContactsResult;
	}
	
        // Interface that provides access to registered contacts and allows them to be searched by name
	public ResultSet pesquisarPorNome(Phonebook contact) throws SQLException 
	{
		PhonebookDAO searchByNameInstance =PhonebookDAO.getInstance();
		ResultSet searchByNameResult = searchByNameInstance.searchByName(contact);
		
		return searchByNameResult;
	}
	
        // Interface that provides access to registered contacts and allows them to be surveyed by phone
	public ResultSet pesquisarPorTelefone(Phonebook contact) throws SQLException 
	{
		PhonebookDAO searchByPhoneInstance = PhonebookDAO.getInstance();
		ResultSet searchPhoneResult  = searchByPhoneInstance.searchByPhone(contact);
		
		return searchPhoneResult;
	}

}
