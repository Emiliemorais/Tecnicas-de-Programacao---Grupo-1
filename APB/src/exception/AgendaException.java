package exception;

@SuppressWarnings("serial")
public class AgendaException extends Exception
{
	// Constructor for the exception of agenda that uses the superclass constructor Exception
	public AgendaException() 
	{
		super();
	}

	/*
	 * Constructor for the exception of agenda that uses the superclass constructor 
	 * Exception passing as parameter a specific message
	 */
	public AgendaException(String message)
	{
		super(message);
	}

}
