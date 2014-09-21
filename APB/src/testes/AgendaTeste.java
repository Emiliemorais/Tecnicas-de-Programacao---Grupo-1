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
	 * M�todo utilizado para receber os atributos de forma correta,
	 * de uma Agenda para realiza��o do teste
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
	
	 
	// M�todo utilizado para testar o funcionamento do construtor de uma Agenda
	@Test
	public void contrutorDeAgendaDeveFuncionar() 
	{
		Phonebook contato = new Phonebook("Alessandro", "6589-5689", "aaaa");
		assertEquals("Alessandro", contato.getPhonebookName());
		assertEquals("6589-5689", contato.getPhonebook());
		assertEquals("aaaa", contato.getPhonebookDs());
	}
	
	// M�todo que testa o recebimento de um nome de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeNomeDeveFuncionar ()
	{
		assertEquals("Alessandro", contato.getPhonebookName());
	}
	
	// M�todo que testa o recebimento de um telefone de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeTelefoneDeveFuncionar ()
	{
		assertEquals("4568-9856", contato.getPhonebook());
	}
	
	// M�todo que testa o recebimento de uma descri��o de um contato pelo m�todo de acesso get 
	@Test
	public void getterDeDescricaoDeveFuncionar ()
	{
		assertEquals("ASDAS", contato.getPhonebookDs());
	}
	
	/* 
	 * M�todo utilizado para receber um nome em branco para 
	 * realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setPhonebookName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um telefone em branco para 
	 * realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoEmBranco () throws BarberException
	{
		contato.setPhonebook("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um nome fora do formato estabelecido (caracteres especiais)
	 * para realiza��o do teste do lan�amento da exce��o
	*/ 
	@Test(expected = BarberException.class)
	public void nomeDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setPhonebookName("ASDAS!!");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/* 
	 * M�todo utilizado para receber um telefone fora do formato estabelecido
	 * (caracteres especiais, letras)para realiza��o do teste do lan�amento da exce��o
	*/
	@Test(expected = BarberException.class)
	public void telefoneDoBarbeiroNaoPodePassarQuandoForaDeFormato () throws BarberException
	{
		contato.setPhonebook("45645aa-a54654");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// M�todo que testa o recebimento do nome temporario pelo m�todo de acesso get
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServiceException 
	{
		assertEquals("Barba", Phonebook.getTempNome());
	}
	
	
	// M�todo que testa a passagem de um nome temporario nulo pelo m�todo de acesso set	 
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServiceException 
	{
		Phonebook.setTempNome(null);
		Assert.fail("Deve lançar exceção");
	}
	
	// M�todo que testa a passagem de um nome temporario em branco pelo m�todo de acesso set
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Phonebook.setTempNome("");
		Assert.fail("Deve lançar exceção");
	}
	
	// M�todo que testa a passagem de um nome tempor�rio pelo m�todo de acesso set
	@Test
	public void tempNomeValido () throws BarberException 
	{
		Phonebook.setTempNome("Paulo");
		assertEquals("Paulo", Phonebook.getTempNome());
	}

}
