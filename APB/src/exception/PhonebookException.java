package exception;

@SuppressWarnings("serial")
public class PhonebookException extends Exception
{
	// Constructor for the exception of agenda that uses the superclass constructor Exception
	public PhonebookException() 
	{
		super();
	}

	/*
	 * Constructor for the exception of agenda that uses the superclass constructor 
	 * Exception passing as parameter a specific message
	 */
	public PhonebookException(String message)
	{
		super(message);
	}

}
