package model;

import exception.BarberException;

public class Contact 
{

	/**
	 * @param contactName Receives the name for the contact
	 * @param contactPhoneNumber Receives the phone number for the contact
	 * @param contactDescription Receives the description for the contact
	 * @param temporaryName Receives the temporaryName for the contact
	 */
	private String contactName;
	private String contactPhoneNumber;
	private String contactDescription;
	private static String temporaryName;

	
	private final String invalidName = "Nome Inv�lido";
	private final String nullName = "Nome em Branco";
	private final String invalidPhone = "Telefone Inv�lido";
	private final String nullPhone = "Telefone em Branco";
	
	// Class general constructor
	public Contact()
	{

	}

	// Class constructor
	public Contact(String contactName, String contactPhoneNumber, String contactDescription)
	{
		this.contactName = contactName;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactDescription = contactDescription;
	}
     
	public String getContactName () 
	{
		return contactName;
	}

	public String getContactPhoneNumber () 
	{
		return contactPhoneNumber;
	}

	public String getContactDescription () 
	{
		return contactDescription;
	}

	/*
	 *  Method of accessing the name, which throws an exception access 
	 *  if the name is not in the required format
	 */	
	public void setContactName (String nome) throws BarberException 
	{
		if ("".equals(nome))
		{
			throw new BarberException(nullName);
		}
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
		{
			this.contactName = nome;
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
	public void setContactPhoneNumber (String telefone) throws BarberException 
	{
		String phoneMatchesParamMethod = ("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$");
		
		if ("".equals(telefone))
		{
			throw new BarberException(nullPhone);
		}
		else if (telefone.matches(phoneMatchesParamMethod))
		{
			this.contactPhoneNumber = telefone;
		}
		else
		{
			// Throws an exception of class Barber as invalid telephone (except null phone)
			throw new BarberException(invalidPhone);
		}
	}

	// Access method for changing the description
	public void setContactDescription (String descricao)
	{
		this.contactDescription = descricao;
	}

	// Method of temporary access name for verification
	public static String getNameTemporary () 
	{
		
		return temporaryName;
	}

	// Method of temporary access name for verification
	public static void setNameTemporary(String tempNome)
	{
		Contact.temporaryName = tempNome;
	}

}
