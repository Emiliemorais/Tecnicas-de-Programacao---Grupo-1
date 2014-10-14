package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Phonebook;
import dao.FactoryConnection;

public class PhonebookDAO 
{

	private static PhonebookDAO instance;

	private PhonebookDAO() 
	{
		
	}

	/** 
	 * @return The current instance if exists, or instantiate a new one if does not and return it
	 */
	public static PhonebookDAO getInstance() 
	{
		if (instance == null) 
		{
			instance = new PhonebookDAO();
		}
		else
		{
			// Nothing to do
		}
		
		return instance;
	}

	/**
	 *  Method used to include data in the phonebook
	 *  @param phonebook - Is going to receive the data
	 *  @throws SQLException
	 */
	public boolean includeDataToPhonebook(Phonebook phonebook) throws SQLException 
	{
		if( phonebook == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToInsertContact = "INSERT INTO "
										+ "agenda (nome, telefone, descricao) VALUES (" + "\""
										+ phonebook.getPhonebookName() + "\", " + "\"" + phonebook.getPhonebook()
										+ "\", " + "\"" + phonebook.getPhonebookDs() + "\"); ";
		
		this.updateQuery(sqlCodeToInsertContact);
		
		return true;
	}

	/**
	 *  Method used to edit data in the phonebook
	 *  @param name - Receives the name
	 *  @param phonebookEdited - Receives the editions
	 *  @param phonebook - Contains the phonebook data
	 *  @throws SQLException
	 */
	public boolean editPhonebookData(String name, Phonebook phonebookEdited, Phonebook phonebook) throws SQLException 
	{	
		if( phonebook == null || phonebookEdited == null )
		{
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToUpdatePhonebook = "UPDATE agenda SET " +
										  "nome = \"" + phonebookEdited.getPhonebookName() + "\", " +
										  "telefone = \"" + phonebookEdited.getPhonebook() + "\", "+
										  "descricao = \"" + phonebookEdited.getPhonebookDs() + "\""+
										  " WHERE " + " agenda.nome = \"" + name + "\";";
		
		this.updateQuery(sqlCodeToUpdatePhonebook);
			
		return true;
	}

	/**
	 *  Method used to delete data from the phonebook
	 *  @param contact - Contact for delete
	 *  @throws SQLException
	 */
	public boolean deletePhonebookData(Phonebook contact) throws SQLException 
	{
		if (contact ==  null)
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		String sqlCodeToDeleteFromPhonebook = "DELETE FROM agenda WHERE " 
											  + "agenda.telefone = \""
											  + contact.getPhonebook () + "\";";
				
		this.updateQuery(sqlCodeToDeleteFromPhonebook);
		
		return true;
	}
	
	/**
	 * Create a connection with DB
	 * @return The connection established
	 * @throws SQLException
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
	public void updateQuery(String message) throws SQLException 
	{
		Connection connection = createConnectionWithDB(); 
		
		PreparedStatement preparedStatement = connection.prepareStatement(message); 
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	/**
	 *  Method that gives access to the registered contacts
	 *  @param contact - Contains the contact to be displayed
	 *  @throws SQLException
	 */
	public ResultSet showRegisteredContacts(Phonebook contact) throws SQLException 
	{
		Connection connection = createConnectionWithDB();

		ResultSet resultInstance = connection.createStatement().executeQuery("Select * from agenda;");
		
		return resultInstance;
	}
	
	/**
	 *  Method that gives access to the search by name
	 *  @param contact - Contains the contact to be displayed
	 *  @throws SQLException
	 */
	public ResultSet searchByName(Phonebook contact) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
	
		String sqlCodeToSelectThroughNameFromPhonebook = "SELECT * FROM agenda WHERE "
														+ "nome = '" + contact.getPhonebookName()+ "';";
		
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectThroughNameFromPhonebook);

		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}
	
	/**
	 *  Method that gives access to the search by phone number
	 *  @param contact - Contains the contact to be displayed
	 *  @throws SQLException
	 */
	public ResultSet searchByPhone(Phonebook contact) throws SQLException 
	{
		Connection connection = createConnectionWithDB();
		
		String sqlCodeToSelectThroughPhoneFromPhonebook = "SELECT * FROM agenda WHERE "
														  + "telefone = '" + contact.getPhonebook()+ "';";
		
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement(sqlCodeToSelectThroughPhoneFromPhonebook);

		ResultSet resultInstance = preparedStatement.executeQuery();

		return resultInstance;
	}

}
