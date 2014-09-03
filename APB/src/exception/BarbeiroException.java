package exception;

@SuppressWarnings("serial")
public class BarbeiroException extends Exception 
{
	
	// Construtor padrão que referencia o construtor padrão da superclasse
	public BarbeiroException()
	{
		super();
	}
	
	// Construtor que referencia o construtor da superclasse - Adiciona uma mensagem de exceção
	public BarbeiroException(String message)
	{
		super(message);
	}

}
