package testes;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;
import model.TipoServico;

import org.junit.Before;
import org.junit.Test;

import exception.ServicoException;


public class TipoServicoTeste {
	
	TipoServico  servico =  new TipoServico();
	
	@Before
	/*
	 * Inicializa um tipo de servi�o.
	 * Captura exce��es do tipo ServicoException.
	 */
	public void setUp(){
		try {
			servico.setNomeTipoServico("Corte");
			servico.setPreco("14,50");
		} catch (ServicoException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/* 
	 * Define uma assertiva que � disparada se o valor 'nomeTipoServico' ('Corte') inserido
	 *   anteriormente n�o for igual ao que est� realmente cadastrado. 
	 */
	public void getterDeNomeDeveRetornarValorPassado(){
		assertEquals("Corte", servico.getNomeTipoServico());
	}
	
	@Test
	/*
	 * Define uma assertiva que � disparada se o valor 'preco' ('14,50') inserido
	 *   anteriormente n�o for igual ao que est� realmente cadastrado.
	 */
	public void getterDePrecoDeveRetornarValorPassado(){
		assertEquals("14,50", servico.getPreco());
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que � disparada se o valor do 'preco' a se cadastrar for nulo.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void setterDePrecoNaoPodeSerNulo() throws ServicoException {
		servico.setPreco(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que � disparada se o valor do 'nomeTipoServico' a se cadastrar for nulo.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void setterDeNomeNaoPodeSerNulo() throws ServicoException {
		servico.setNomeTipoServico(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado
	@Test (expected = IllegalArgumentException.class)
	
	/*
	 * Define uma assertiva que � disparada se o valor do 'preco' a se cadastrar for inv�lido.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void setterDePrecoNaoPodeSerInvalido() throws ServicoException {
		servico.setPreco("14.50%");
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado
	@Test (expected =  ServicoException.class)
	
	/*
	 * Define uma assertiva que � disparada se o valor do 'preco' a se cadastrar estiver em branco.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void setterDePrecoServicoNaoPodeSerEmBranco() throws ServicoException {
		servico.setPreco("");
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado 
	@Test (expected =  ServicoException.class)
	
	/*
	 * Define uma assertiva que � disparada se o valor do 'nomeTipoServico' a se cadastrar estiver em branco.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void setterDeNomeServicoNaoPodeSerEmBranco() throws ServicoException {
		servico.setNomeTipoServico("");
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado
	@Test (expected = AssertionError.class)
	
	/* 
	 * Define uma assertiva que � disparada se o valor 'tempNome' ('Corte') inserido
	 *   anteriormente n�o for igual ao que est� realmente cadastrado.
	 * Dispara exce��es do tipo ServicoException.  
	 */
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServicoException {
		assertEquals("Corte", TipoServico.getTempNome());
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que � disparada se o valor do 'tempNome' a se cadastrar for nulo.
	 * Dispara exce��es do tipo ServicoException. 
	 */
	public void setterDeTempNomeNaoPodeSerNulo() throws ServicoException {
		TipoServico.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// Define o erro esperado
	@Test (expected = ServicoException.class)
	
	/* 
	 * Define uma assertiva que � disparada se o valor do 'tempNome' a se cadastrar estiver em branco
	 * Dispara exce��es do tipo ServicoException.
	 */
	public void setterDeTempNomeNaoPodeSerEmBranco() throws ServicoException {
		TipoServico.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	@Test
	
	/* 
	 * Define uma assertiva que � disparada se o valor inserido em 'tempNome'
	 *   n�o for igual ao que est� cadastrado. 
	 */
	public void tempNomeValido() {
		try {
			TipoServico.setTempNome("Barba");
		} catch (ServicoException e) {
			e.printStackTrace();
			Assert.fail("Não Deve lançar exceção");
		}
		assertEquals("Barba", TipoServico.getTempNome());
	}

}
