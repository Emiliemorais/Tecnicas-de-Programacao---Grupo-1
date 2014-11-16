package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Contact;
import dao.FactoryConnection;

public class ContactDAO 
{

	private static ContactDAO instance;

	private ContactDAO () 
	{
		
	}

	/** 
	 * @return The current instance if exists, or instantiate a new one if does not and return it
	 */
	public static ContactDAO getInstance () 
	{
		if( instance == null ) 
		{
			instance = new ContactDAO();
		}
		else
		{
			// Nothing to do - because the condition "if"  is just used to check the initial value of the variable
		}
		
		return instance;
	}

	/**
	 *  Method used to include data in the phonebook
	 *  @param phonebook - Is going to receive the data
	 *  @throws SQLException
	 *  @return - Return the status of the insertion
	 */
	public boolean includeDataToPhonebook (Contact phonebook) throws SQLException 
	{
		boolean dataToPhonebookInserted;
		
		if( phonebook == null )
		{
			dataToPhonebookInserted = false;
		}
		else
		{
			String sqlCodeToInsertContact = "INSERT INTO "
											+ "agenda (nome, telefone, descricao) VALUES (" + "\""
											+ phonebook.getContactName() + "\", " + "\"" + phonebook.getContactPhoneNumber()
											+ "\", " + "\"" + phonebook.getContactDescription() + "\"); ";

			this.updateQuery(sqlCodeToInsertContact);
			dataToPhonebookInserted = true;
		}
		
		return dataToPhonebookInserted;
	}

	/**
	 *  Method used to edit data in the phonebook
	 *  @param name - Receives the name
	 *  @param phonebookEdited - Receives the editions
	 *  @param phonebook - Contains the phonebook data
	 *  @throws SQLException
	 *  @return - Return the status of the edition
	 */
	public boolean editPhonebookData (String name, Contact phonebookEdited, Contact phonebook) throws SQLException 
	{	
		boolean dataToPhonebookEdited;
		
		if( phonebook == null || phonebookEdited == null )
		{
			dataToPhonebookEdited = false;
		}
		else
		{
			String sqlCodeToUpdatePhonebook = "UPDATE agenda SET " +
											  "nome = \"" + phonebookEdited.getContactName() + "\", " +
											  "telefone = \"" + phonebookEdited.getContactPhoneNumber() + "\", "+
											  "descricao = \"" + phonebookEdited.getContactDescription() + "\""+
											  " WHERE " + " agenda.nome = \"" + name + "\";";

			this.updateQuery(sqlCodeToUpdatePhonebook);

			dataToPhonebookEdited = true;
		}

		return dataToPhonebookEdited;
	}

	/**
	 *  Method used to delete data from the phonebook
	 *  @param contact - Contact for delete
	 *  @throws SQLException
	 *  @return - Return the status of the exclusion
	 */
	public boolean deletePhonebookData (Contact contact) throws SQLException 
	{
		boolean dataToPhonebookDeleted;
		if (contact ==  null)
		{
			dataToPhonebookDeleted = false;
		}
		else
		{
			String sqlCodeToDeleteFromPhonebook = "DELETE FROM agenda WHERE " 
												  + "agenda.telefone = \""
												  + contact.getContactPhoneNumber () + "\";";

			this.updateQuery(sqlCodeToDeleteFromPhonebook);
			
			dataToPhonebookDeleted = true;
		}
		
		return dataToPhonebookDeleted;
	}
	
	/**
	 * Create a connection with DB
	 * @return The connection established
	 * @throws SQLException
	 * @return - Return the connection with the database
	 */
	public Connection createConnectionWithDB() throws SQLException
	{
		FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
		Connection connection = factoryConnectionInstance.getConnection();
		
		return connection;
	}
	
	/**
	 *  Method used to execute some action on DB
	 *  @param message - SQL code of action to be executed
	 *  @throws SQLException
	 */
	public void updateQuery (String message) throws SQLException 
	{
		Connection connection = createConnectionWithDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(message); 
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	/**
	 *  Method that gives access to the registered contacts
	 *  @throws SQLException
	 *  @return - Return the ResultSet of the selection of the data from phonebook
	 */
	public ResultSet showRegisteredContacts () throws SQLException 
	{
		Connection connection = createConnectionWithDB();

		ResultSet resultInstance = connection.createStatement().executeQuery("Select * from agenda;");
		
		return resultInstance;
	}
	
	/**
	 *  Method that gives access to the search by name
	 *  @param contact - Contains the contact to be displayed
	 *  @throws SQLException
	 *  @return - Return ResultSet of the search by name
	 */
	public ResultSet searchByName (Contact contact) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
	
		String sqlCodeToSelectThroughNameFromPhonebook = "SELECT * FROM agenda WHERE "
														+ "nome = '" + contact.getContactName()+ "';";
		
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectThroughNameFromPhonebook);

		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}
	
	/**
	 *  Method that gives access to the search by phone number
	 *  @param contact - Contains the contact to be displayed
	 *  @throws SQLException
	 *  @return - Return ResultSet of the search by phone
	 */
	public ResultSet searchByPhone (Contact contact) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		String sqlCodeToSelectThroughPhoneFromPhonebook = "SELECT * FROM agenda WHERE "
														  + "telefone = '" + contact.getContactPhoneNumber()+ "';";
		
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectThroughPhoneFromPhonebook);

		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}

}
