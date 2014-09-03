package exception;

@SuppressWarnings("serial")
public class BarbeiroException extends Exception 
{
	
	// Construtor padr�o que referencia o construtor padr�o da superclasse
	public BarbeiroException()
	{
		super();
	}
	
	// Construtor que referencia o construtor da superclasse - Adiciona uma mensagem de exce��o
	public BarbeiroException(String message)
	{
		super(message);
	}

}
