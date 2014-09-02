package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import exception.BarbeiroException;
import exception.ServicoException;
import model.Agenda;
import model.Barbeiro;

import org.junit.Before;
import org.junit.Test;


public class BarbeiroTeste 
{

	Barbeiro barbeiro;
	
	/* 
	 * Método utilizado para receber os atributos de um Barbeiro de forma correta,
	 * para realização do teste
	*/
	@Before
	public void setUp()
	{
		try 
		{
			barbeiro =  new Barbeiro();
			barbeiro.setNome("Alessandro");
			barbeiro.setRg("418757896");
			barbeiro.setTelefone("3389-9085");
			barbeiro.setCpf("02919594150");
			barbeiro.setCadeira("10");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarbeiroException e) 
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
			barbeiro.setNome(null);
		} 
		catch (BarbeiroException e) 
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
			barbeiro.setCpf(null);
		} 
		catch (BarbeiroException e) 
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
			barbeiro.setRg(null);
		}
		catch (BarbeiroException e) 
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
			barbeiro.setTelefone(null);
		}
		catch (BarbeiroException e) 
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
			barbeiro.setCadeira(null);
		}
		catch (BarbeiroException e)
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
			new Barbeiro(null, "493.751.185-84", "2258256", "3389-9085", "10");
		} catch (BarbeiroException e)
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
			new Barbeiro("Alessandro", null, "2258256", "3389-9085", "10");
		} 
		catch (BarbeiroException e) 
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
			new Barbeiro("Alessandro", "493.751.185-84", null, "3389-9085", "10");
		}
		catch (BarbeiroException e) 
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
			new Barbeiro("Alessandro", "493.751.185-84", "2258256", null, "10");
		} 
		catch (BarbeiroException e) 
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
			new Barbeiro("Alessandro", "493.751.185-84", "2258256", "3389-9085", null);
		} 
		catch (BarbeiroException e) 
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
			barbeiro.setCpf("000000000");
			fail();
		}
		catch (BarbeiroException e) 
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
			barbeiro.setRg("4654654ASD");
		} 
		catch (BarbeiroException e)
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
			barbeiro.setCadeira("asd");
		} 
		catch (BarbeiroException e)
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
			barbeiro.setTelefone("65465a4");
		} 
		catch (BarbeiroException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	// Método que testa o recebimento de um nome do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeNomeDeBarbeiro () 
	{
		assertEquals("Alessandro", barbeiro.getNome());
	}
	
	// Método que testa o recebimento de um CPF do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeCPFDeBarbeiro ()
	{
		assertEquals("02919594150", barbeiro.getCpf());
	}
	
	// Método que testa o recebimento de um RG do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeRGDeBarbeiro () 
	{
		assertEquals("418757896", barbeiro.getRg());
	}
	
	// Método que testa o recebimento de um telefone do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeTelefoneDeBarbeiro () 
	{
		assertEquals("3389-9085", barbeiro.getTelefone());
	}
	
	// Método que testa o recebimento de uma cadeira do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeCadeiraDeBarbeiro ()
	{
		assertEquals("10", barbeiro.getCadeira());
	}
	
	// Método que testa o recebimento de um nome temporario do Barbeiro pelo método de acesso get 
	@Test
	public void testeParaGetterDeTempNomeDeBarbeiro ()
	{
		assertEquals(null, Barbeiro.getTempNome());
	}
	
	// Método que testa a passagem de um nome do Barbeiro pelo método de acesso set 
	@Test
	public void setDeBarbeiroDeveFuncionar ()
	{
		try 
		{
			barbeiro.setNome("Alessandro");
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
		} 
		catch (BarbeiroException e) 
		{
			e.printStackTrace();
		}
		assertEquals("Alessandro", barbeiro.getNome());
	}
	
	// Método que testa a passagem de um nome com número do Barbeiro pelo método de acesso set
	@Test (expected = BarbeiroException.class)
	public void nomeComNumero () throws BarbeiroException 
	{
		barbeiro.setNome("J040");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um CPF em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void cpfPassadoEmBranco () throws BarbeiroException
	{
		barbeiro.setCpf("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um CPF invalido do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void cpfInvalido () throws BarbeiroException 
	{
		barbeiro.setCpf("123.654.456-75");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um rg com letras do Barbeiro pelo método de acesso set
	@Test (expected =  AssertionError.class)
	public void rgPassadoComLetras () throws BarbeiroException 
	{
		barbeiro.setRg("asasa");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um rg em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void rgPassadoEmBrancro () throws BarbeiroException
	{
		barbeiro.setRg("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void nomePassadoEmBrancro () throws BarbeiroException
	{
		barbeiro.setNome("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um telefone em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void telefonePassadoEmBrancro () throws BarbeiroException
	{
		barbeiro.setTelefone("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de uma cadeira em branco do Barbeiro pelo método de acesso set
	@Test (expected =  BarbeiroException.class)
	public void cadeiraPassadoEmBrancro () throws BarbeiroException 
	{
		barbeiro.setCadeira("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/*
	 *  Método que testa a passagem de uma cadeira com valor zero do Barbeiro 
	 *  pelo método de acesso set
	 */
	@Test (expected =  AssertionError.class)
	public void cadeiraPassadoComoZero () throws BarbeiroException 
	{
		barbeiro.setCadeira("0");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	/*
	 *  Método que testa a passagem de uma cadeira com mais de dois dígitos
	 *   do Barbeiro pelo método de acesso set
	 */
	@Test (expected = BarbeiroException.class)
	public void cadeiraPassadoComMaisDeDoisDigitos () throws BarbeiroException
	{
		barbeiro.setCadeira("1000");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa o recebimento do nome temporario pelo método de acesso get
	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServicoException
	{
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	 // Método que testa a passagem de um nome temporario nulo 
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServicoException 
	{
		Barbeiro.setTempNome(null);
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	
	// Método que testa a passagem de um nome temporario em branco pelo método de acesso set
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Barbeiro.setTempNome("");
		Assert.fail("Deve lanÃ§ar uma exceÃ§Ã£o");
	}
	
	// Método que testa a passagem de um nome temporário do Barbeiro pelo método de acesso set
	@Test (expected = AssertionError.class)
	public void tempNomeValido () throws BarbeiroException
	{
		Barbeiro.setTempNome("JoÃ£o");
		assertEquals("JoÃ£o", Agenda.getTempNome());
	}
}