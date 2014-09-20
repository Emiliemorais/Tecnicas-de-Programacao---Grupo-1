package exception;

@SuppressWarnings("serial")
public class ReceiptException extends Exception 
{

	// Constructor that references the constructor of the superclass
	public ReceiptException()
	{
		super();
	}
	
	/*
	 *  Constructor that references the constructor of the superclass - Add a message of exception
	 *  @param errorMessage -  Receives an error message
	 */
	public ReceiptException (String errorMessage)
	{
		super(errorMessage);
	}

}
