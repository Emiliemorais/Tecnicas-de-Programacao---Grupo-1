package exception;

@SuppressWarnings("serial")
public class AgendaException extends Exception
{
	// Construtor da exce��o da agenda que utiliza o construtor da superclasse Exception
	public AgendaException() 
	{
		super();
	}

	/*
	 * Construtor da exce��o da agenda que utiliza o construtor da superclasse Exception 
	 * passando como parametro uma mensagem espec�fica
	 */
	public AgendaException(String message)
	{
		super(message);
	}

}
