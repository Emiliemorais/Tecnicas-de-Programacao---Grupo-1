package exception;

@SuppressWarnings ("serial")
public class ServiceException extends Exception
{

	// Class constructor
	public ServiceException() 
	{
		super();
	}

	/**
	 *  Method that returns the exception message
	 *  @param errorMessage - Contains the error message
	 */
	public ServiceException(String errorMessage) 
	{
		super(errorMessage);
	}
	
}
