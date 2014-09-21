package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import exception.BarberException;
import exception.ServiceException;
import model.Agenda;
import model.Barber;

import org.junit.Before;
import org.junit.Test;


public class BarbeiroTeste 
{

	Barber barber;
	
	/* 
	 * Método utilizado para receber os atributos de um Barbeiro de forma correta,
	 * para realização do teste
	*/
	@Before
	public void setUp()
	{
		try 
		{
			barber =  new Barber();
			barber.setBarberName("Alessandro");
			barber.setBarberRg("418757896");
			barber.setBarberTelephone("3389-9085");
			barber.setBarberCpf("02919594150");
			barber.setBarberChair("10");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	/* 
	 * Método utilizado para receber um nome nulo de um Barbeiro para realização do teste
	 * do lançamento da exceção
	*/
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirNomeNuloPassandoPeloSetter ()
	{
		try 
		{
			barber.setBarberName(null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber um CPF nulo de um Barbeiro para realização do teste
	 * do lançamento da exceção
	*/
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirCPFNuloPassandoPeloSetter () 
	{
		try 
		{
			barber.setBarberCpf(null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber um RG nulo de um Barbeiro para realização do teste
	 * do lançamento da exceção
	*/
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirRGNuloPassandoPeloSetter ()
	{
		try
		{
			barber.setBarberRg(null);
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber um telefone nulo de um Barbeiro para realização do teste
	 * do lançamento da exceção
	*/
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirTelefoneNuloPassandoPeloSetter () 
	{
		try
		{
			barber.setBarberTelephone(null);
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber uma cadeira nula de um Barbeiro para realização do teste
	 * do lançamento da exceção
	*/	
	@Test (expected = NullPointerException.class)
	public void barbeiroNaoPodePossuirCadeiraNuloPassandoPeloSetter ()
	{
		try 
		{
			barber.setBarberChair(null);
		}
		catch (BarberException e)
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber um nome nulo no construtor de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComNomeNulo () 
	{
		try 
		{
			new Barber(null, "493.751.185-84", "2258256", "3389-9085", "10");
		} catch (BarberException e)
		{
			e.printStackTrace();
		}

	}

	/* 
	 * Método utilizado para receber um CPF nulo no construtor de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/	
	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComCpfNulo ()
	{
		try 
		{
			new Barber("Alessandro", null, "2258256", "3389-9085", "10");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para receber um RG nulo no construtor de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComRgNulo () 
	{
		try
		{
			new Barber("Alessandro", "493.751.185-84", null, "3389-9085", "10");
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}

	}

	/* 
	 * Método utilizado para receber um telefone nulo no construtor de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/	
	@Test(expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComTelefoneNulo() 
	{
		try 
		{
			new Barber("Alessandro", "493.751.185-84", "2258256", null, "10");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}

	/* 
	 * Método utilizado para receber uma cadeira nula no construtor de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = IllegalArgumentException.class)
	public void contrutorDeBarbeiroNaoPodePassarComCadeiraNulo () 
	{
		try 
		{
			new Barber("Alessandro", "493.751.185-84", "2258256", "3389-9085", null);
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Método utilizado para receber um CPF invalido de um Barbeiro para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = AssertionError.class)
	public void cpfNaoPodePassarQuandoInvalido () 
	{
		try 
		{
			barber.setBarberCpf("000000000");
			fail();
		}
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Método utilizado para receber um RG que contem letras para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = AssertionError.class)
	public void rgNaoPodeConterLetras () 
	{
		try 
		{
			barber.setBarberRg("4654654ASD");
		} 
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}
	
	/* 
	 * Método utilizado para receber uma cadeira que contem letras para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = AssertionError.class)
	public void numeroDaCadeiraNaoPodeSerUmaLetra ()
	{
		try 
		{
			barber.setBarberChair("asd");
		} 
		catch (BarberException e)
		{
			e.printStackTrace();
		}
	}

	/* 
	 * Método utilizado para receber um telefone que contem letras para 
	 * realização do teste do lançamento da exceção
	*/
	@Test (expected = AssertionError.class)
	public void numeroDoTelefoneNaoPodeConterLetras () 
	{
		try
		{
			barber.setBarberTelephone("65465a4");
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	// Método que testa o recebimento de um nome do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeNomeDeBarbeiro () 
	{
		assertEquals("Alessandro", barber.getBarberName());
	}
	
	// Método que testa o recebimento de um CPF do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeCPFDeBarbeiro ()
	{
		assertEquals("02919594150", barber.getBarberCpf());
	}
	
	// Método que testa o recebimento de um RG do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeRGDeBarbeiro () 
	{
		assertEquals("418757896", barber.getBarberRg());
	}
	
	// Método que testa o recebimento de um telefone do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeTelefoneDeBarbeiro () 
	{
		assertEquals("3389-9085", barber.getBarberTelephone());
	}
	
	// Método que testa o recebimento de uma cadeira do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeCadeiraDeBarbeiro ()
	{
		assertEquals("10", barber.getBarberChair());
	}
	
	// Método que testa o recebimento de um nome temporario do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeTempNomeDeBarbeiro ()
	{
		assertEquals(null, Barber.getTemporaryName());
	}
	
	// Método que testa a passagem de um nome do Barbeiro pelo método de acesso set 
	@Test
	public void setDeBarbeiroDeveFuncionar ()
	{
		try 
		{
			barber.setBarberName("Alessandro");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarberException e) 
		{
			e.printStackTrace();
		}
		assertEquals("Alessandro", barber.getBarberName());
	}
	
	// Método que testa a passagem de um nome com número do Barbeiro pelo método de acesso set
	@Test (expected = BarberException.class)
	public void nomeComNumero () throws BarberException 
	{
		barber.setBarberName("J040");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um CPF em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void cpfPassadoEmBranco () throws BarberException
	{
		barber.setBarberCpf("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um CPF invalido do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void cpfInvalido () throws BarberException 
	{
		barber.setBarberCpf("123.654.456-75");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um rg com letras do Barbeiro pelo método de acesso set
	@Test (expected =  AssertionError.class)
	public void rgPassadoComLetras () throws BarberException 
	{
		barber.setBarberRg("asasa");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um rg em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void rgPassadoEmBrancro () throws BarberException
	{
		barber.setBarberRg("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void nomePassadoEmBrancro () throws BarberException
	{
		barber.setBarberName("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um telefone em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void telefonePassadoEmBrancro () throws BarberException
	{
		barber.setBarberTelephone("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de uma cadeira em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarberException.class)
	public void cadeiraPassadoEmBrancro () throws BarberException 
	{
		barber.setBarberChair("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/*
	 *  Método que testa a passagem de uma cadeira com valor zero do Barbeiro 
	 *  pelo método de acesso set
	 */
	@Test (expected =  AssertionError.class)
	public void cadeiraPassadoComoZero () throws BarberException 
	{
		barber.setBarberChair("0");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/*
	 *  Método que testa a passagem de uma cadeira com mais de dois dígitos
	 *   do Barbeiro pelo método de acesso set
	 */
	@Test (expected = BarberException.class)
	public void cadeiraPassadoComMaisDeDoisDigitos () throws BarberException
	{
		barber.setBarberChair("1000");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa o recebimento do nome temporario pelo método de acesso get
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServiceException
	{
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	 // Método que testa a passagem de um nome temporario nulo 
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServiceException 
	{
		Barber.setTemporaryName(null);
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	
	// Método que testa a passagem de um nome temporario em branco pelo método de acesso set
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Barber.setTemporaryName("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome temporário do Barbeiro pelo método de acesso set
	@Test (expected = AssertionError.class)
	public void tempNomeValido () throws BarberException
	{
		Barber.setTemporaryName("JoÃ£o");
		assertEquals("JoÃ£o", Agenda.getTempNome());
	}
}