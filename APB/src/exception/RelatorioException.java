package exception;
// Iniciando Classe
@SuppressWarnings("serial")
public class RelatorioException extends Exception 
{
// Herancca da classe pai
	
	// Retorna Exception
	public RelatorioException() 
	{
		super();
	}// Fim do metodo

	// Retorna a mensagem da excecao
	public RelatorioException(String message) 
	{
		super(message);
	}// Fim do metodo

}// Fim da classe