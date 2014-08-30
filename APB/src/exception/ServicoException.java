package exception;
//Iniciando Classe
@SuppressWarnings("serial")
public class ServicoException extends Exception
{
// Herancca da classe pai

	// Retorna Exception
	public ServicoException() 
	{
		super();
	}// Fim do metodo

	// Retorna a mensagem da excecao

	public ServicoException(String message) 
	{
		super(message);
	}// Fim do metodo
}// Fim da classe
