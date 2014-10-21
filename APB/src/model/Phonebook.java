package model;

import exception.BarberException;

public class Phonebook 
{

	/**
	 * @param phonebookNameReceives the name for phonebook
	 * @param phonebookReceives the description for phonebook
	 * @param phonebookDs Receives the number of phone for phonebook
	 * @param tempNome Receives the variable for change the method
	 */
	private String phonebookName;
	private String phonebook;
	private String phonebookDs;
	private static String tempNome;

	
	private final String invalidName = "Nome Inv�lido";
	private final String nullName = "Nome em Branco";
	private final String invalidPhone = "Telefone Inv�lido";
	private final String nullPhone = "Telefone em Branco";

	public Phonebook()
	{

	}

	// Constructor object PHONEBOOK that receives its attributes as parameter
	public Phonebook(String nome, String telefone, String descricao)
	{
		this.phonebookName = nome;
		this.phonebook = telefone;
		this.phonebookDs = descricao;
	}
     
	// phonebookName getter
	public String getPhonebookName () 
	{
		
		return phonebookName;
	}

	// phonebook getter
	public String getPhonebook () 
	{
		
		return phonebook;
	}
	
	// phonebookDs getter
	public String getPhonebookDs () 
	{
		
		return phonebookDs;
	}

	/*
	 *  Method of accessing the name, which throws an exception access 
	 *  if the name is not in the required format
	 */	
	public void setPhonebookName (String nome) throws BarberException 
	{
		if ("".equals(nome))
		{
			throw new BarberException(nullName);
		}
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
		{
			this.phonebookName = nome;
		}
		else
		{
			throw new BarberException(invalidName);
		}
	}

	/*
	 *  Method of accessing the phone, which throws an exception access 
	 *  if the phone is not in the required format
	 */
	public void setPhonebook (String telefone) throws BarberException 
	{
		String phoneMatchesParamMethod = ("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$");
		
		if ("".equals(telefone))
		{
			throw new BarberException(nullPhone);
		}
		else if (telefone.matches(phoneMatchesParamMethod))
		{
			this.phonebook = telefone;
		}
		else
		{
			// Throws an exception of class Barber as invalid telephone (except null phone)
			throw new BarberException(invalidPhone);
		}
	}

	// Access method for changing the description
	public void setPhonebookDs (String descricao)
	{
		this.phonebookDs = descricao;
	}

	// Method of temporary access name for verification
	public static String getNameTemporary () 
	{
		
		return tempNome;
	}

	// Method of temporary access name for verification
	public static void setNameTemporary(String tempNome)
	{
		Phonebook.tempNome = tempNome;
	}

}
