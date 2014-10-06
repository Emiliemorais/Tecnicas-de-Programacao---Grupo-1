package model;

import exception.BarberException;

public class Phonebook 
{

	/*
	 * @param Receives the name for phonebook
	 * @param Receives the description for phonebook
	 * @param Receives the number of phone for phonebook
	 * @param Receives the variable for change the method
	 */

	private String phonebookName;
	private String phonebook;
	private String phonebookDs;
	private static String tempNome;

	
	private final String invalidName = "Nome Inválido";
	private final String nullName = "Nome em Branco";
	private final String invalidPhone = "Telefone Inválido";
	private final String nullPhone = "Telefone em Branco";

	public Phonebook()
	{

	}

	//  Builder object PHONEBOOK that receives its attributes as a parameter
	public Phonebook(String nome, String telefone, String descricao)
	{
		this.phonebookName = nome;
		this.phonebook = telefone;
		this.phonebookDs = descricao;
	}
     
	// Method name to check access
	public String getPhonebookName () 
	{
		return phonebookName;
	}

	// Method of phone access for checking
	public String getPhonebook () 
	{
		return phonebook;
	}
	
	// Method for verifying access Description
	public String getPhonebookDs () 
	{
		return phonebookDs;
	}

	// Access method name that throws an exception if the name is not in the required format
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
	 *  Method of access the phone, which throws an exception access 
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
			//  Throws an exception of class Barber an invalid telephone (except null phone)
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
	public static void setNameTemporary (String tempNome)
	{
		Phonebook.tempNome = tempNome;
	}

}
