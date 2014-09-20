package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import exception.BarberException;
import exception.ServicoException;
import model.Agenda;

public class AgendaTeste 
{
	
	Agenda contato = new Agenda();
	
	/* 
	 * M�todo utilizado para receber os atributos de forma correta,
	 * de uma Agenda para realiza��o do teste
	*/
	@Before
	public void setUp ()
	{
		try 
		{
			contato.setNome("Alessandro");
			contato.setTelefone("4568-9856");
		} 
		catch (BarberException e1) 
		{
			e1.printStackTrace();
		}
		contato.setDescricao("ASDAS");
	}
	
	 
	// M�todo utilizado para testar o funcionamento do construtor de uma Agenda
	@Test
	public void contrutorDeAgendaDeveFuncionar() 
	{
		Agenda contato = new Agenda("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getNome());
		assertEquals("6589-5689", contato.getTelefone());
		assertEquals("aaaa", contato.getDescricao());
	}
	
	// M�todo que testa o recebimento de um nome de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeNomeDeveFuncionar ()
	{
		assertEquals("Alessandro", contato.getNome());
	}
	
	// M�todo que testa o recebimento de um telefone de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeTelefoneDeveFuncionar ()
	{
		assertEquals("4568-9856", contato.getTelefone());
	}
	
	// M�todo que testa o recebimento de uma descri��o de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeDescricaoDeveFuncionar ()
	{
		assertEquals("ASDAS", contato.getDescricao());
	}
	
	/* 
	 * M�todo utilizado para receber um nome em branco para 
	 * realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setNome("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um telefone em branco para 
	 * realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setTelefone("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um nome fora do formato estabelecido (caracteres especiais)
	 * para realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setNome("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um telefone fora do formato estabelecido
	 * (caracteres especiais, letras)para realiza��o do teste do lan�amento da exce��o
	*/
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setTelefone("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// M�todo que testa o recebimento do nome temporario pelo m�todo de acesso get
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServicoException 
	{
		assertEquals("Barba", Agenda.getTempNome());
	}
	
	
	// M�todo que testa a passagem de um nome temporario nulo pelo m�todo de acesso set	 
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServicoException 
	{
		Agenda.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// M�todo que testa a passagem de um nome temporario em branco pelo m�todo de acesso set
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Agenda.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	// M�todo que testa a passagem de um nome tempor�rio pelo m�todo de acesso set
	@Test
	public void tempNomeValido () throws BarberException 
	{
		Agenda.setTempNome("Paulo");
		assertEquals("Paulo", Agenda.getTempNome());
	}

}
