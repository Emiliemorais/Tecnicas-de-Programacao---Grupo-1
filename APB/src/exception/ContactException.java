package exception;

@SuppressWarnings("serial")
public class ContactException extends Exception
{
	// Class general constructor that references the super constructor
	public ContactException() 
	{
		super();
	}

	// Class constructor that references the super constructor
	public ContactException(String message)
	{
		super(message);
	}

}
