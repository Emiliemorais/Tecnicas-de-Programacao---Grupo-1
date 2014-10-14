package exception;

@SuppressWarnings ("serial")
public class ReportException extends Exception 
{
	
	// Class constructor
	public ReportException() 
	{
		super();
	}

	/**
	 *  Method that returns the exception message
	 *  @param errorMessage - Contains the error message
	 */
	public ReportException(String errorMessage) 
	{
		super(errorMessage);
	}

}