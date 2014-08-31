// Pacote ao qual pertence a classe
package model;

// Importando
import exception.ServicoException;
// Fim da importa��o

// In�cio da classe
public class TipoServico {

// Declara��o de vari�veis
	private String nomeTipoServico;
	private String preco;
	private static String tempNome;

	private final static String NOME_BRANCO = "Nome do Servi�o em Branco";
	private final String PRECO_INVALIDO = "Pre�o Inv�lido";
	private final String PRECO_BRANCO = "Pre�o em Branco";
// Fim da declara��o de vari�veis

	public TipoServico(){
	}

// M�todo que recebe o nome do tipo de servi�o
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}
// Fim do m�todo

// M�todo que recebe o pre�o
	public String getPreco() {
		return preco;
	}
// Fim do m�todo

// M�todo que recebe o nome
	public static String getTempNome() {
		return tempNome;
	}
// Fim do m�todo

/*
 * M�todo que "seta" o nome do tipo de servi�o
 * Tratamento de exce��es
 */
	public void setNomeTipoServico(String nomeTipoServico) throws ServicoException {
		if (nomeTipoServico == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nomeTipoServico))
			throw new ServicoException(NOME_BRANCO);
		else
			this.nomeTipoServico = nomeTipoServico;
	}
// Fim do m�todo

/*
 * M�todo que "seta" o pre�o
 * Tratamento de exce��es
 */
	public void setPreco(String preco) throws ServicoException {
		if (preco == null)
			throw new NullPointerException(PRECO_INVALIDO);
		else if ("".equals(preco))
			throw new ServicoException(PRECO_BRANCO);
		else if (preco.matches("[\\d]{1,3},[\\d]{1,2}"))
			this.preco = preco;
		else
			throw new IllegalArgumentException("Pre�o deve ser no formato: **,** ");
	}
// Fim do m�todo

/*
 * M�todo que "seta" o nome
 * Tratamento de exce��es
 */
	public static void setTempNome(String tempNome) throws ServicoException {
		if (tempNome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(tempNome))
			throw new ServicoException(NOME_BRANCO);
		else
			TipoServico.tempNome = tempNome;
	}
// Fim do m�todo
}
// Fim da classe
