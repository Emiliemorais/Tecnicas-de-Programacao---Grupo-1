package exception;

@SuppressWarnings("serial")
public class ReciboException extends Exception {

	// Construtor padrão que referencia o construtor padrão da superclasse
	public ReciboException() {
		super();
	}
	
	// Construtor que referencia o construtor da superclasse - Adiciona uma mensagem de exceção
	public ReciboException(String message) {
		super(message);
	}

	
}
