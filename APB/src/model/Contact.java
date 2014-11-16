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
    
	// contactName getter
	public String getContactName() 
	{
		return contactName;
	}
	
	// contactPhoneNumber getter
	public String getContactPhoneNumber() 
	{
		return contactPhoneNumber;
	}
	
	// contactDescription getter
	public String getContactDescription() 
	{
		return contactDescription;
	}
	
	// temporaryName getter
	public static String getNameTemporary () 
	{
		return temporaryName;
	}

	/**
	 * contactName setter. Check if the name matches a format
	 * @param name - Name to set
	 * @throws BarberException
	 */
	public void setContactName (String name) throws BarberException 
	{
		String nameFormat = "^[[ ]|\\p{L}*]+$"; 
		
		if ("".equals(name))
		{
			throw new BarberException(nullName);
		}
		else if (name.matches(nameFormat))
		{
			this.contactName = name;
		}
		else
		{
			throw new BarberException(invalidName);
		}
	}

	/**
	 * phoneNumber setter. Check if it matches a format
	 * @param phoneNumber - phoneNumber to set
	 * @throws BarberException
	 */
	public void setContactPhoneNumber (String phoneNumber) throws BarberException 
	{
		String phoneNumberFormat = ("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$");
		
		if ("".equals(phoneNumber))
		{
			throw new BarberException(nullPhone);
		}
		else if (phoneNumber.matches(phoneNumberFormat))
		{
			this.contactPhoneNumber = phoneNumber;
		}
		else
		{
			throw new BarberException(invalidPhone);
		}
	}

	/**
	 * contactDescription setter
	 * @param description - Description do set
	 */
	public void setContactDescription (String description)
	{
		this.contactDescription = description;
	}


	/**
	 * temporaryName setter
	 * @param temporaryNome - temporaryName to set
	 */
	public static void setNameTemporary(String temporaryNome)
	{
		Contact.temporaryName = temporaryNome;
	}

}
