package exception;

@SuppressWarnings ( "serial" )
public class ServiceException extends Exception
{

	// Method that returns the exception
	public ServiceException () 
	{
		
		super ();
	}

	/*
	 *  Method that returns the exception message
	 *  @param errorMessage - Contains the error message
	 */
	public ServiceException ( String errorMessage ) 
	{
		
		super ( errorMessage );
	}
	
}
