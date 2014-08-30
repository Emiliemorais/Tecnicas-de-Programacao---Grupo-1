package exception;

@SuppressWarnings("serial")
public class ReciboException extends Exception {

	// Construtor padr�o que referencia o construtor padr�o da superclasse
	public ReciboException() {
		super();
	}
	
	// Construtor que referencia o construtor da superclasse - Adiciona uma mensagem de exce��o
	public ReciboException(String message) {
		super(message);
	}

	
}
