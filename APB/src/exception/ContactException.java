package exception;

@SuppressWarnings("serial")
public class ContactException extends Exception
{
	// Constructor for the exception of agenda that uses the superclass constructor Exception
	public ContactException() 
	{
		super();
	}

	/*
	 * Constructor for the exception of agenda that uses the superclass constructor 
	 * Exception passing as parameter a specific message
	 */
	public ContactException(String message)
	{
		super(message);
	}

}
