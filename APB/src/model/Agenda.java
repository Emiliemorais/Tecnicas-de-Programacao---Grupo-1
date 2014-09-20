package model;

import exception.BarberException;

public class Agenda 
{

	private String nome;
	private String telefone;
	private String descricao;
	private static String tempNome;

	private final String NOME_INVALIDO = "Nome Inv�lido";
	private final String NOME_BRANCO = "Nome em Branco";
	private final String TELEFONE_INVALIDO = "Telefone Inv�lido";
	private final String TELEFONE_BRANCO = "Telefone em Branco";

	public Agenda()
	{

	}

	// Construtor do objeto Agenda que recebe seus atributos como par�metro
	public Agenda(String nome, String telefone, String descricao)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.descricao = descricao;
	}
     
	// M�todo de acesso do nome para verifica��o
	public String getNome () 
	{
		return nome;
	}

	// M�todo de acesso do telefone para verifica��o
	public String getTelefone () 
	{
		return telefone;
	}
	
	// M�todo de acesso da descri��o para verifica��o
	public String getDescricao () 
	{
		return descricao;
	}

	// M�todo de acesso do nome, que lan�a uma exce��o se o nome n�o estiver no formato requerido
	public void setNome (String nome) throws BarberException 
	{
		if ("".equals(nome))
		{
			throw new BarberException(NOME_BRANCO);
		}
		else if (nome.matches("^[[ ]|\\p{L}*]+$"))
		{
			this.nome = nome;
		}
		else
		{
			throw new BarberException(NOME_INVALIDO);
		}
	}

	/*
	 *  M�todo de acesso do telefone, que lan�a uma exce��o 
	 *  se o telefone n�o estiver no formato requerido
	 */
	public void setTelefone (String telefone) throws BarberException 
	{
		if ("".equals(telefone))
		{
			throw new BarberException(TELEFONE_BRANCO);
		}
		else if (telefone.matches("(\\([\\d]{2,3}\\))?[ ]*[\\d]{4,4}[ ]*-[ ]*[\\d]{4,4}[ ]*$"))
		{
			this.telefone = telefone;
		}
		else
		{
			// Lan�a uma exce��o da classe Barbeiro de um telefone inv�lido(exceto telefone em branco)
			throw new BarberException(TELEFONE_INVALIDO);
		}
	}

	// M�todo de acesso da descri��o para altera��o
	public void setDescricao (String descricao)
	{
		this.descricao = descricao;
	}

	// M�todo de acesso do nome tempor�rio para verifica��o
	public static String getTempNome () 
	{
		return tempNome;
	}

	// M�todo de acesso do nome tempor�rio para altera��o
	public static void setTempNome (String tempNome)
	{
		Agenda.tempNome = tempNome;
	}

}
