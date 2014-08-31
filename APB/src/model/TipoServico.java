// Pacote ao qual pertence a classe
package model;

// Importando
import exception.ServicoException;
// Fim da importação

// Início da classe
public class TipoServico {

// Declaração de variáveis
	private String nomeTipoServico;
	private String preco;
	private static String tempNome;

	private final static String NOME_BRANCO = "Nome do Serviço em Branco";
	private final String PRECO_INVALIDO = "Preço Inválido";
	private final String PRECO_BRANCO = "Preço em Branco";
// Fim da declaração de variáveis

	public TipoServico(){
	}

// Método que recebe o nome do tipo de serviço
	public String getNomeTipoServico() {
		return nomeTipoServico;
	}
// Fim do método

// Método que recebe o preço
	public String getPreco() {
		return preco;
	}
// Fim do método

// Método que recebe o nome
	public static String getTempNome() {
		return tempNome;
	}
// Fim do método

/*
 * Método que "seta" o nome do tipo de serviço
 * Tratamento de exceções
 */
	public void setNomeTipoServico(String nomeTipoServico) throws ServicoException {
		if (nomeTipoServico == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(nomeTipoServico))
			throw new ServicoException(NOME_BRANCO);
		else
			this.nomeTipoServico = nomeTipoServico;
	}
// Fim do método

/*
 * Método que "seta" o preço
 * Tratamento de exceções
 */
	public void setPreco(String preco) throws ServicoException {
		if (preco == null)
			throw new NullPointerException(PRECO_INVALIDO);
		else if ("".equals(preco))
			throw new ServicoException(PRECO_BRANCO);
		else if (preco.matches("[\\d]{1,3},[\\d]{1,2}"))
			this.preco = preco;
		else
			throw new IllegalArgumentException("Preço deve ser no formato: **,** ");
	}
// Fim do método

/*
 * Método que "seta" o nome
 * Tratamento de exceções
 */
	public static void setTempNome(String tempNome) throws ServicoException {
		if (tempNome == null)
			throw new NullPointerException(NOME_BRANCO);
		else if ("".equals(tempNome))
			throw new ServicoException(NOME_BRANCO);
		else
			TipoServico.tempNome = tempNome;
	}
// Fim do método
}
// Fim da classe
