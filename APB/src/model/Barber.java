package model;

import exception.BarberException;

public class Barber
{
	 // Constant that receives a invalid name of a barber
	private final String INVALID_NAME = "Nome Inválido";
	
	// Constant that receives a blank name of a barber
	private final String BLANK_NAME = "Nome em Branco";
	
	// Constant that receives a invalid Cpf (cadastro de pessoa f�sica, in portuguese) of a barber
	private final String INVALID_CPF = "CPF Inválido";
	
	// Constant that receives a blank Cpf of a barber
	private final String BLANK_CPF = "CPF em Branco";

	// Constant that receives a blank barberRg (registro geral, in portuguese) of a barber
	private final String BLANK_RG = "RG em Branco";

	// Constant that receives a invalid barberRg  of a barber
	private final String INVALID_RG = "RG Inválido";

	// Constant that receives a invalid telephone of a barber
	private final String INVALID_TELEPHONE = "Telefone Inválido";

	// Constant that receives a blank telephone of a barber
	private final String BLANK_TELEPHONE = "Telefone em Branco";

	// Constant that receives a invalid chair of a barber
	private final String INVALID_CHAIR = "Cadeira Inválida";

	// Constant that receives a blank chair of a barber
	private final String BLANK_CHAIR = "Campo Cadeira em Branco";
	
	// Receives the temporary name of a barber
	private static String temporaryName;
	
	// Receives the  name of a barber
	private String barberName;
	
	// Receives the Cpf of a barber
	private String barberCpf;
	
	// Receives the Rg of a barber
	private String barberRg;
	
	// Receives the telephone of a barber
	private String barberTelephone;
	
	// Receives the chair of a barber
	private String barberChair;
		
	// General Constructor 
	public Barber()
	{
		
	}
	
	// Constructor - Test if the parameters are not null
	public Barber(String barberName, String barberCpf, String barberRg, String barberTelephone,
				  String barberChair) throws BarberException
	{
		this.barberName = barberName;
		this.barberCpf = barberCpf;
		this.barberRg = barberRg;
		this.barberTelephone = barberTelephone;
		this.barberChair = barberChair;

		if (this.barberName == null)
		{
			throw new IllegalArgumentException(BLANK_NAME);
		}
		else
		{
			// Nothing to do
		}

		if (this.barberCpf == null)
		{
			throw new IllegalArgumentException(BLANK_CPF);
		}
		else
		{
			// Nothing to do
		}

		if (this.barberRg == null)
		{
			throw new IllegalArgumentException(BLANK_RG);
		}
		else
		{
			// Nothing to do
		}

		if (this.barberTelephone == null)
		{
			throw new IllegalArgumentException(BLANK_TELEPHONE);
		}
		else
		{
			// Nothing to do
		}

		if (this.barberChair == null)
		{
			throw new IllegalArgumentException(BLANK_CHAIR);
		}
		else
		{
			// Nothing to do
		}
	}
	
	
	public String getBarberName()
	{
		return barberName;
	}

	public String getBarberCpf()
	{
		return barberCpf;
	}

	public String getBarberRg()
	{
		return barberRg;
	}

	public String getBarberTelephone()
	{
		return barberTelephone;
	}

	public String getBarberChair()
	{
		return barberChair;
	}
	
	public static String getTemporaryName()
	{
		return temporaryName;
	}


	
	/**
	 *  This method is used to access the attribute and it checks tests if the barber's name is valid
	 *   and otherwise throws exceptions
	 *	@param barberName - receives the name of a barber to validate
	 */
	public void setBarberName (String barberName) throws BarberException 
	{
		if (barberName == null)
		{
			throw new NullPointerException(BLANK_NAME);
		}
		else if ( "".equals(barberName) )
		{
			throw new BarberException(BLANK_NAME);
		}
		else if ( barberName.matches("^[[ ]|\\p{L}*]+$") )
		{
			this.barberName= barberName;
		}
		else
		{
			throw new BarberException(INVALID_NAME);
		}
	}
	
	/**
	 * 	This method is used to access the attribute and it checks tests if the barber's cpf is valid
	 *  and otherwise throws exceptions
	 *	@param barberCpf - receives the cpf of a barber to validate
	 */
	public void setBarberCpf (String barberCpf) throws BarberException
	{
		// valid CPF example: 493.751.185-84
		try 
		{
			if( barberCpf == null )
			{
				throw new NullPointerException(BLANK_CPF);
			}
			else if( "".equals(barberCpf) )
			{
				throw new AssertionError(BLANK_CPF);
			}
			else if( barberCpf.matches("[\\d]{3,3}.[\\d]{3,3}.[\\d]{3,3}-[\\d]{2,2}$") )
			{
				barberCpf = barberCpf.split("[\\. | -]")[0] + barberCpf.split("[\\. | -]")[1]
					       +barberCpf.split("[\\. | -]")[2] + barberCpf.split("[\\. | -]")[3];
			}
			if( this.validateCpf(barberCpf) )
			{
				this.barberCpf = barberCpf;
			}
			else
			{
				throw new BarberException(INVALID_CPF);
			}
		}
		catch (AssertionError e) 
		{
			throw new BarberException(INVALID_CPF);
		}
	}
	
	/**
	 * This method tests if the barber's rg is valid and otherwise throws exceptions
	 * @param barberRg - receives the rg of a barber to validate
	 */

	public void setBarberRg (String barberRg) throws BarberException
	{
		if( barberRg == null )
		{
			throw new NullPointerException(BLANK_RG);
		}
		else if( "".equals(barberRg) )
		{
			throw new BarberException(BLANK_RG);
		}
		else if( barberRg.matches("^[[ ]|\\p{L}*]+$") )
		{
			throw new AssertionError(INVALID_RG);
		}
		else if( barberRg.matches("^[0-9]*$") )
		{
			this.barberRg = barberRg;
		}
		else
		{
			throw new AssertionError(INVALID_RG);
		}
	}

	/**
	 *  This method is used to access the attribute and it checks tests if the barber's telephone is valid
	 *  and otherwise throws exceptions
	 * 	@param barberTelephone - receives the telephone of a barber to validate
	 */
	public void setBarberTelephone (String barberTelephone) throws BarberException
	{
		if( barberTelephone == null )
		{
			throw new NullPointerException(BLANK_TELEPHONE);
		}
		else if( "".equals(barberTelephone) )
		{
			throw new BarberException(BLANK_TELEPHONE);
		}
		else if( barberTelephone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$") )
		{
			this.barberTelephone = barberTelephone;
		}
		else
		{
			throw new AssertionError(INVALID_TELEPHONE);
		}
	}
	
	/**
	 *  This method is used to access the attribute and it checks tests if the barber's chair is valid
	 *  and otherwise throws exceptions
	 * 	@param barberChair- receives the chair of a barber to validate
	 */
	public void setBarberChair (String barberChair) throws BarberException
	{
		if( barberChair == null )
		{
			throw new NullPointerException(BLANK_CHAIR);
		}
		else if ("".equals(barberChair))
		{
			throw new BarberException(BLANK_CHAIR);
		}
		else if ( "0".equals(barberChair) || barberChair.matches("^[[ ]|\\p{L}*]+$") )
		{
			throw new AssertionError(INVALID_CHAIR);
		}
		else if ( barberChair.matches("^[0-9]{0,2}$") )
		{
			this.barberChair = barberChair;
		}
		else
		{
			throw new BarberException(INVALID_CHAIR);
		}
	}
	
	/**
	 * This method is used to access the attribute
	 * @param barberTemporaryName
	 */
	public static void setTemporaryName (String barberTemporaryName)
	{
		Barber.temporaryName = barberTemporaryName;
	}
	

	
		
	/**
	 * This method is used to validate the barber's cpf
	 * @param barberCpf - receives the cpf of a barber
	 */
	
	private boolean validateCpf (String barberCpf) 
	{
		// Auxiliary variables to check Cpf
		int auxiliary1 = 0;
		int auxiliary2 = 0;
		
		// Variables used to represent the last two digits of a Cpf
		int penultimateDigit = 0;
		int lastDigit = 0;
		
		// Auxiliary variable to check Cpf (cadastro de pessoa f�sica, in portuguese)
		int rest = 0;
		
		// Receives the current digit of the Cpf
		int cpfDigit;
		
		// Receives the last two digits of a Cpf
		String result;


		// (currentDigit) Variable used to walk through the digits of cpf 
		int i = 1;
		
		// auxiliary1 = auxiliary2 = penultimateDigit = lastDigit = rest = 0;
		
		for (i = 1; i < barberCpf.length() - 1; i++)
		{
			// Receives the digit of the Cpf for pass for integer 
			String cpfSubstring;
			cpfSubstring = barberCpf.substring(i - 1, i);
			
			cpfDigit = Integer.valueOf(cpfSubstring).intValue();

			auxiliary1 = auxiliary1 + (11 - i) * cpfDigit;
			auxiliary2 = auxiliary2 + (12 - i) * cpfDigit;
		};

		rest = auxiliary1 % 11;

		if (rest < 2)
		{
			penultimateDigit = 0;
		}
		else
		{
			penultimateDigit = 11 - rest;
		}

		auxiliary2 += 2*penultimateDigit;
		rest = (auxiliary2 % 11);

		if (rest < 2)
		{
			lastDigit = 0;
		}
		else
		{
			lastDigit = 11 - rest;
		}
		
		// Receives the last two digits of a Cpf
		String verific = barberCpf.substring( barberCpf.length() - 2, barberCpf.length() );
		result = String.valueOf(penultimateDigit) + String.valueOf(lastDigit);

		return verific.equals(result);
	}
}
