package exception;

@SuppressWarnings("serial")
public class BarberException extends Exception 
{
	
	// Constructor that references the constructor of the superclass
	public BarberException()
	{
		super();
	}
	
	/**
	 *  Constructor that references the constructor of the superclass - Add a message of exception
	 *  @param errorMessage - Receives an error message
	 */
	public BarberException(String errorMessage)
	{
		super(errorMessage);
	}

}
