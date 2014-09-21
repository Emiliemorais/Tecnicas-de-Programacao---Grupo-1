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

	private PhonebookDAO () 
	{
		
	}

	/* 
	 * Method used to instance the variable 
	 * Used only in the case of being NULL
	 */
	public static PhonebookDAO getInstance () 
	{
		if (instance == null) // "PhonebookDAO" Class Instance
		{
			instance = new PhonebookDAO ();
		}
		else
		{
			
		}
		
		return instance;
	}

	/*
	 *  Method used to include data in the phonebook
	 *  @param phonebook - Is going to receive the data
	 */
	public boolean includeDataToPhonebook ( Phonebook phonebook ) throws SQLException 
	{
		if ( phonebook == null )
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery("INSERT INTO "
				+ "agenda (nome, telefone, descricao) VALUES (" + "\""
				+ phonebook.getPhonebookName() + "\", " + "\"" + phonebook.getPhonebook()
				+ "\", " + "\"" + phonebook.getPhonebookDs() + "\"); ");
		
		return true;
	}

	/*
	 *  Method used to edit data in the phonebook
	 *  @param name - Receives the name
	 *  @param phonebookEdited - Receives the editions
	 *  @param phonebook - Contains the phonebook data
	 */
	public boolean editPhonebookData ( String name, Phonebook phonebookEdited, Phonebook phonebook) throws SQLException 
	{	
		if ( phonebook == null || phonebookEdited == null )
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery("UPDATE agenda SET " +
				"nome = \"" + phonebookEdited.getPhonebookName() + "\", " +
				"telefone = \"" + phonebookEdited.getPhonebook() + "\", "+
				"descricao = \"" + phonebookEdited.getPhonebookDs() + "\""+
				" WHERE " +
				" agenda.nome = \"" + name + "\";");
			
		return true;
	}

	/*
	 *  Method used to delete data from the phonebook
	 *  @param contact - Receives the contact for delete
	 */
	public boolean deletePhonebookData ( Phonebook contact ) throws SQLException 
	{
		if ( contact ==  null )
		{
			
			return false;
		}
		else
		{
			// Nothing to do
		}
		
		this.updateQuery ( "DELETE FROM agenda WHERE " + "agenda.telefone = \""
				+ contact.getPhonebook () + "\";");
		
		return true;
	}

	/*
	 *  Method used to modify existing records
	 *  @param message - String about the exception
	 */
	public void updateQuery ( String message ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection(); 
		// connection - Receives values accordingly to the database
		PreparedStatement preparedStatement = connection.prepareStatement(message); 
		// preparedStatement - Receives the prepared statements
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}

	/*
	 *  Method that gives access to the registered contacts
	 *  @param contact - Contains the contact to be displayed
	 */
	public ResultSet showRegisteredContacts ( Phonebook contact ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance ().getConnection ();
		// connection - Receives values accordingly to the database
		ResultSet resultInstance = connection.createStatement().executeQuery("Select * from agenda;");
		// resultInstance - ResultSetInstance
		
		return resultInstance;
	}
	
	/*
	 *  Method that gives access to the search by name
	 *  @param contact - Contains the contact to be displayed
	 */
	public ResultSet searchByName ( Phonebook contact ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		// connection - Receives values accordingly to the database
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "nome = '" + contact.getPhonebookName()+ "';");
		// preparedStatement - Instance of java.sql
		ResultSet resultInstance = preparedStatement.executeQuery();
		// resultInstance - ResultSetInstance

		return resultInstance;
	}
	
	/*
	 *  Method that gives access to the search by phone number
	 *  @param contact - Contains the contact to be displayed
	 */
	public ResultSet searchByPhone ( Phonebook contact ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		// connection - Receives values accordingly to the database
		java.sql.PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM agenda WHERE "
				+ "telefone = '" + contact.getPhonebook()+ "';");
		// preparedStatement - Instance of java.sql
		ResultSet resultInstance = preparedStatement.executeQuery();
		// resultInstance - ResultSetInstance

		return resultInstance;
	}

}
