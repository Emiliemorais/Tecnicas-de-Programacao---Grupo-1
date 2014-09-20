package model;

import exception.BarberException;

public class Agenda 
{

	private String nome;
	private String telefone;
	private String descricao;
	private static String tempNome;

	private final String NOME_INVALIDO = "Nome Inválido";
	private final String NOME_BRANCO = "Nome em Branco";
	private final String TELEFONE_INVALIDO = "Telefone Inválido";
	private final String TELEFONE_BRANCO = "Telefone em Branco";

	public Agenda()
	{

	}

	// Construtor do objeto Agenda que recebe seus atributos como parâmetro
	public Agenda(String nome, String telefone, String descricao)
	{
		this.nome = nome;
		this.telefone = telefone;
		this.descricao = descricao;
	}
     
	// Método de acesso do nome para verificação
	public String getNome () 
	{
		return nome;
	}

	// Método de acesso do telefone para verificação
	public String getTelefone () 
	{
		return telefone;
	}
	
	// Método de acesso da descrição para verificação
	public String getDescricao () 
	{
		return descricao;
	}

	// Método de acesso do nome, que lança uma exceção se o nome não estiver no formato requerido
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
	 *  Método de acesso do telefone, que lança uma exceção 
	 *  se o telefone não estiver no formato requerido
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
			// Lança uma exceção da classe Barbeiro de um telefone inválido(exceto telefone em branco)
			throw new BarberException(TELEFONE_INVALIDO);
		}
	}

	// Método de acesso da descrição para alteração
	public void setDescricao (String descricao)
	{
		this.descricao = descricao;
	}

	// Método de acesso do nome temporário para verificação
	public static String getTempNome () 
	{
		return tempNome;
	}

	// Método de acesso do nome temporário para alteração
	public static void setTempNome (String tempNome)
	{
		Agenda.tempNome = tempNome;
	}

}
