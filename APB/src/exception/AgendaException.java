package exception;

@SuppressWarnings("serial")
public class AgendaException extends Exception
{
	// Construtor da exceção da agenda que utiliza o construtor da superclasse Exception
	public AgendaException() 
	{
		super();
	}

	/*
	 * Construtor da exceção da agenda que utiliza o construtor da superclasse Exception 
	 * passando como parametro uma mensagem específica
	 */
	public AgendaException(String message)
	{
		super(message);
	}

}
