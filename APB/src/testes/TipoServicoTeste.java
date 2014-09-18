package testes;

import static org.junit.Assert.assertEquals;
import junit.framework.Assert;
import model.ServiceType;

import org.junit.Before;
import org.junit.Test;

import exception.ServicoException;


public class TipoServicoTeste 
{
	
	ServiceType  servico =  new ServiceType();
	
	@Before
	/*
	 * Inicializa um tipo de serviço.
	 * Captura exceções do tipo ServicoException.
	 */
	public void setUp()
	{
		try
		{
			servico.setServiceTypeName("Corte");
			servico.setServiceTypePrice("14,50");
		} 
		catch (ServicoException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/* 
	 * Define uma assertiva que é disparada se o valor 'nomeTipoServico' ('Corte') inserido
	 *   anteriormente não for igual ao que está realmente cadastrado. 
	 */
	public void getterDeNomeDeveRetornarValorPassado()
	{
		assertEquals( "Corte", servico.getServiceTypeName() );
	}
	
	@Test
	/*
	 * Define uma assertiva que é disparada se o valor 'preco' ('14,50') inserido
	 *   anteriormente não for igual ao que está realmente cadastrado.
	 */
	public void getterDePrecoDeveRetornarValorPassado()
	{
		assertEquals( "14,50", servico.getServiceTypePrice() );
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que é disparada se o valor do 'preco' a se cadastrar for nulo.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void setterDePrecoNaoPodeSerNulo() throws ServicoException
	{
		servico.setServiceTypePrice(null);
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que é disparada se o valor do 'nomeTipoServico' a se cadastrar for nulo.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void setterDeNomeNaoPodeSerNulo() throws ServicoException 
	{
		servico.setServiceTypeName(null);
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado
	@Test (expected = IllegalArgumentException.class)
	
	/*
	 * Define uma assertiva que é disparada se o valor do 'preco' a se cadastrar for inválido.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void setterDePrecoNaoPodeSerInvalido() throws ServicoException 
	{
		servico.setServiceTypePrice("14.50%");
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado
	@Test (expected =  ServicoException.class)
	
	/*
	 * Define uma assertiva que é disparada se o valor do 'preco' a se cadastrar estiver em branco.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void setterDePrecoServicoNaoPodeSerEmBranco() throws ServicoException
	{
		servico.setServiceTypePrice("");
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado 
	@Test (expected =  ServicoException.class)
	
	/*
	 * Define uma assertiva que é disparada se o valor do 'nomeTipoServico' a se cadastrar estiver em branco.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void setterDeNomeServicoNaoPodeSerEmBranco() throws ServicoException
	{
		servico.setServiceTypeName("");
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado
	@Test (expected = AssertionError.class)
	
	/* 
	 * Define uma assertiva que é disparada se o valor 'tempNome' ('Corte') inserido
	 *   anteriormente não for igual ao que está realmente cadastrado.
	 * Dispara exceções do tipo ServicoException.  
	 */
	public void getterDeTempNomeDeveRetornarValorPassado() throws ServicoException
	{
		assertEquals( "Corte", ServiceType.getTemporaryName() );
	}
	
	// Define o erro esperado
	@Test (expected = NullPointerException.class)
	
	/* 
	 * Define uma assertiva que é disparada se o valor do 'tempNome' a se cadastrar for nulo.
	 * Dispara exceções do tipo ServicoException. 
	 */
	public void setterDeTempNomeNaoPodeSerNulo() throws ServicoException 
	{
		ServiceType.setTemporaryName(null);
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Define o erro esperado
	@Test (expected = ServicoException.class)
	
	/* 
	 * Define uma assertiva que é disparada se o valor do 'tempNome' a se cadastrar estiver em branco
	 * Dispara exceções do tipo ServicoException.
	 */
	public void setterDeTempNomeNaoPodeSerEmBranco() throws ServicoException
	{
		ServiceType.setTemporaryName("");
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	@Test
	
	/* 
	 * Define uma assertiva que é disparada se o valor inserido em 'tempNome'
	 *   não for igual ao que está cadastrado. 
	 */
	public void tempNomeValido() 
	{
		try
		{
			ServiceType.setTemporaryName("Barba");
		} 
		catch (ServicoException e)
		{
			e.printStackTrace();
			Assert.fail("NÃ£o Deve lanÃ§ar exceÃ§Ã£o");
		}
		assertEquals( "Barba", ServiceType.getTemporaryName() );
	}

}
