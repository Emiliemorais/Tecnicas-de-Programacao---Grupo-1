package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;

import exception.BarberException;
import exception.ServiceException;
import model.Phonebook;

public class AgendaTeste 
{
	
	Phonebook contato = new Phonebook();
	
	/* 
	 * Método utilizado para receber os atributos de forma correta,
	 * de uma Agenda para realização do teste
	*/
	@Before
	public void setUp ()
	{
		try 
		{
			contato.setPhonebookName("Alessandro");
			contato.setPhonebook("4568-9856");
		} 
		catch (BarberException e1) 
		{
			e1.printStackTrace();
		}
		contato.setPhonebookDs("ASDAS");
	}
	
	 
	// Método utilizado para testar o funcionamento do construtor de uma Agenda
	@Test
	public void contrutorDeAgendaDeveFuncionar() 
	{
		Phonebook contato = new Phonebook("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getPhonebookName());
		assertEquals("6589-5689", contato.getPhonebook());
		assertEquals("aaaa", contato.getPhonebookDs());
	}
	
	// Método que testa o recebimento de um nome de um contato pelo método de acesso get 
	@Test
	public void getterDeNomeDeveFuncionar ()
	{
		assertEquals("Alessandro", contato.getPhonebookName());
	}
	
	// Método que testa o recebimento de um telefone de um contato pelo método de acesso get 
	@Test
	public void getterDeTelefoneDeveFuncionar ()
	{
		assertEquals("4568-9856", contato.getPhonebook());
	}
	
	// Método que testa o recebimento de uma descrição de um contato pelo método de acesso get 
	@Test
	public void getterDeDescricaoDeveFuncionar ()
	{
		assertEquals("ASDAS", contato.getPhonebookDs());
	}
	
	/* 
	 * Método utilizado para receber um nome em branco para 
	 * realização do teste do lançamento da exceção
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setPhonebookName("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/* 
	 * Método utilizado para receber um telefone em branco para 
	 * realização do teste do lançamento da exceção
	*/ 
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setPhonebook("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/* 
	 * Método utilizado para receber um nome fora do formato estabelecido (caracteres especiais)
	 * para realização do teste do lançamento da exceção
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setPhonebookName("ASDAS!!");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/* 
	 * Método utilizado para receber um telefone fora do formato estabelecido
	 * (caracteres especiais, letras)para realização do teste do lançamento da exceção
	*/
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setPhonebook("45645aa-a54654");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa o recebimento do nome temporario pelo método de acesso get
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServiceException 
	{
		assertEquals("Barba", Phonebook.getTempNome());
	}
	
	
	// Método que testa a passagem de um nome temporario nulo pelo método de acesso set	 
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServiceException 
	{
		Phonebook.setTempNome(null);
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome temporario em branco pelo método de acesso set
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Phonebook.setTempNome("");
		Assert.fail("Deve lanÃ§ar exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome temporário pelo método de acesso set
	@Test
	public void tempNomeValido () throws BarberException 
	{
		Phonebook.setTempNome("Paulo");
		assertEquals("Paulo", Phonebook.getTempNome());
	}

}
