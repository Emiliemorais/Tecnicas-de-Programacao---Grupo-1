package testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import exception.BarberException;
import exception.ServiceException;
import model.Phonebook;
import model.Barber;

import org.junit.Before;
import org.junit.Test;


public class BarberTest 
{

	Barber barber;
	
	/* 
	 * Method used to get the attributes of a Barber correctly to the test
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
	 * Method used to receive a null name of a Barber for the test launch of the exception
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
	 *Method used to receive a null CPF a Barber for the test launch of the exception
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
	 * Method used to receive a null RG a Barber for the test launch of the exception
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
	 * Method used to receive a null number of a Barber for the test launch of the exception
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
	 * Method used to receive a null chair of a Barber for the test launch of the exception
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
	 * Method used to receive a null name in the constructor of a Barber for the test launch of the exception
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
	 * Method used to receive a null in the constructor of a CPF Barber for the test launch of the exception
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
	 *Method used to receive a null in the constructor of a RG Barber for the test launch of the exception
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
	 * Method used to receive a null phone in the constructor of a Barber for the test launch of the exception
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
	 * Method used to receive a null in the constructor of a chair barber for the test launch of the exception
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
	 * Method used to receive a CPF invalid a Barber for the test launch of the exception
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
	 * Method used to receive an RG containing letters for the test launch of the exception
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
	 * Method used to receive a chair that contains letters for the test launch of the exception
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
	 *Method used to receive a phone containing letters for the test launch of the exception
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
	
	
	// Method that tests receiving a name Barber get the access method
	@Test
	public void testeParaGetterDeNomeDeBarbeiro () 
	{
		assertEquals("Alessandro", barber.getBarberName());
	}
	
	// Method that tests receiving a CPF Barber get the access method
	@Test
	public void testeParaGetterDeCPFDeBarbeiro ()
	{
		assertEquals("02919594150", barber.getBarberCpf());
	}
	
	// Method that tests an incoming RG Barber get the access method

	@Test
	public void testeParaGetterDeRGDeBarbeiro () 
	{
		assertEquals("418757896", barber.getBarberRg());
	}
	
	// Method that tests an incoming phone Barber get the access method
 
	@Test
	public void testeParaGetterDeTelefoneDeBarbeiro () 
	{
		assertEquals("3389-9085", barber.getBarberTelephone());
	}
	
	// Method that tests the receipt of a chair by Barber access method get
	@Test
	public void testeParaGetterDeCadeiraDeBarbeiro ()
	{
		assertEquals("10", barber.getBarberChair());
	}
	
	// M�todo que testa o recebimento de um nome temporario do Barbeiro pelo m�todo de acesso get 
	@Test
	public void testeParaGetterDeTempNomeDeBarbeiro ()
	{
		assertEquals(null, Barber.getTemporaryName());
	}
	
	// Method testing the passage of a name of the access method Barber September
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
	
	// Method that tests the passage of a name with the number of the access method Barber September
	@Test (expected = BarberException.class)
	public void nomeComNumero () throws BarberException 
	{
		barber.setBarberName("J040");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a CPF blank Barber set by the access method
	@Test (expected =  BarberException.class)
	public void cpfPassadoEmBranco () throws BarberException
	{
		barber.setBarberCpf("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a CPF Barber invalid by the access method in September
	@Test (expected =  BarberException.class)
	public void cpfInvalido () throws BarberException 
	{
		barber.setBarberCpf("123.654.456-75");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a rg with letters Barber by the access method in September
	@Test (expected =  AssertionError.class)
	public void rgPassadoComLetras () throws BarberException 
	{
		barber.setBarberRg("asasa");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a rg blank Barber by the access method in September
	@Test (expected =  BarberException.class)
	public void rgPassadoEmBrancro () throws BarberException
	{
		barber.setBarberRg("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a blank name Barber by the access method in September

	@Test (expected =  BarberException.class)
	public void nomePassadoEmBrancro () throws BarberException
	{
		barber.setBarberName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passing of a phone blank Barber by the access method in September

	@Test (expected =  BarberException.class)
	public void telefonePassadoEmBrancro () throws BarberException
	{
		barber.setBarberTelephone("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a chair on white Barber by the access method in September

	@Test (expected =  BarberException.class)
	public void cadeiraPassadoEmBrancro () throws BarberException 
	{
		barber.setBarberChair("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/*
	 * Method that tests the passage of a Barber chair with zero value set by the access method

	 */
	@Test (expected =  AssertionError.class)
	public void cadeiraPassadoComoZero () throws BarberException 
	{
		barber.setBarberChair("0");
		Assert.fail("Deve lançar uma exceção");
	}
	
	/*
	 * Method that tests the passage of a chair with more than two digits of the access method Barber September
	 */
	@Test (expected = BarberException.class)
	public void cadeiraPassadoComMaisDeDoisDigitos () throws BarberException
	{
		barber.setBarberChair("1000");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests receiving the temporary name for the access method get

	@Test (expected = AssertionError.class)
	public void getterDeTempNomeDeveRetornarValorPassado () throws ServiceException
	{
		Assert.fail("Deve lançar uma exceção");
	}
	
	 // Method that tests passing a null temporary name

	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerNulo () throws ServiceException 
	{
		Barber.setTemporaryName(null);
		Assert.fail("Deve lançar uma exceção");
	}
	
	
	// Method that tests the passage of a temporary blank name for access method in September	
	@Test (expected = AssertionFailedError.class)
	public void setterDeTempNomeNaoPodeSerEmBranco () 
	{
		Barber.setTemporaryName("");
		Assert.fail("Deve lançar uma exceção");
	}
	
	// Method that tests the passage of a temporary name by Barber access method in September

	@Test (expected = AssertionError.class)
	public void tempNomeValido () throws BarberException
	{
		Barber.setTemporaryName("João");
		assertEquals("João", Phonebook.getTempNome());
	}
}