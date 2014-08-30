package testes;

//Importando dados
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barbeiro;

import org.junit.Before;
import org.junit.Test;

import control.BarbeiroController;
import exception.BarbeiroException;

//Inicio da classe
public class BarbeiroControllerTeste 
{

	// Instancia de Barbeiro
	Barbeiro barbeiro = new Barbeiro();

	@Before
	/*
	 * Incio do metodo setUp() Ela tem a funcao de setar os valores na classe
	 * Alem de Trratar o erro caso nao como requeremos
	 */
	public void setUp() 
	{
		try {
			barbeiro.setNome("Alessandro");
			barbeiro.setRg("418757896");
			barbeiro.setTelefone("3389-9085");
			barbeiro.setCpf("02919594150");
			barbeiro.setCadeira("5");
		} catch (NullPointerException e)
		{
			e.printStackTrace();
		} catch (BarbeiroException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo setUp()

	BarbeiroController barbeiroController = BarbeiroController.getInstance();

	@Test
	// Metodo para passar os dados para a classe por meio do encapsulamento
	public void getInstanceDeBarbeiroControlerDeveRetonarInstanciaCorrente() 
	{
		assertEquals(BarbeiroController.getInstance(), barbeiroController);
	}// Fim do metodo

	@Test
	public void inserirDeBarbeiroControllerDeveEnviarUmBarbeiro() 
	{
		try {
			assertTrue(barbeiroController.inserir(barbeiro));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	@Test
	// Metodo para passar a altoriazacao de exclusao dos dados para a classe por meio do encapsulamento
	public void excluirDeBarbeiroControllerDeveEnviarUmBarbeiro() 
	{
		try {
			assertTrue(barbeiroController.excluir(barbeiro));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	@Test
	// Metodo para passar a altoriazacao de alterar os dados da a classe por meio do encapsulamento
	public void alterarDeBarbeiroControllerDeveEnviarUmBarbeiro() 
	{
		try {
			assertTrue(barbeiroController.alterar(barbeiro.getNome(), barbeiro));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	// Fim do metodo
	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void inserirBarbeiroNaoPodePassarBarbeiroNullo()
{
		try {
			assertFalse(barbeiroController.inserir(null));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */

	@Test
	public void excluirBarbeiroNaoPodePassarBarbeiroNullo() 
	{
		try {
			assertFalse(barbeiroController.excluir(null));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */

	@Test
	public void alterarBarbeiroNaoPodePassarBarbeiroNullo()
	{
		try {
			assertFalse(barbeiroController.alterar(null, null));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	@Test
	public void procurarPorBarbeiroControllerDeveMostrarUmBarbeiro()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.pesquisar();
		while (rs.next())
			;
	}// Fim do metodo

	@Test
	public void mostrarBarbeirosDeBarbeiroControllerDeveMostrarUmBarbeiro()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.mostrarBarbeirosCadastrados(barbeiro);
		while (rs.next())
			;
	}// Fim do metodo

	@Test
	public void pesquisarPorNomeDeBarbeiroControllerDeveMostrarUmBarbeiro()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.pesquisarPorNome(barbeiro);
		while (rs.next())
			;
	}// Fim do metodo
}// Fim da Classe
