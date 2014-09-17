package testes;

// Importando bibliotecas
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoServico;

import org.junit.Before;
import org.junit.Test;

import control.ServiceTypeController;
import exception.ServicoException;

// Inicio da Classe TipoServicoControllerTeste
public class TipoServicoControllerTeste 
{

	// Instancias da classe
	TipoServico servico = new TipoServico();
	ServiceTypeController servicoController = ServiceTypeController
			.getInstance();

	@Before
	// Motodo setUp, com fun;'ao de atribuir os valores de teste da classe 
	public void setUp() {
		try {
			servico.setNomeTipoServico("Corte");
			servico.setPreco("15,00");
		} catch (ServicoException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	public void getInstanceDeTipoServicoControllerDeveRetornarInstanciaCorrente()
	{
		assertEquals(ServiceTypeController.getInstance(), servicoController);
	}//Fim  do metodo

	@Test
	// Metodo para restringir o tipo dde retorno como se deseja
	public void inserirDeTipoServicoControllerDeveEnviarUmTipoServico()
	{
		try {
			assertTrue(servicoController.includeServiceType(servico));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	// Metodo excluir para remover um tipo de servico
	public void excluirDeTipoServicoControllerDeveRemoverUmTipoServico() 
	{
		try {
			assertTrue(servicoController.deleteServiceType(servico));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	// Metodo para alterar um tipo de servico
	public void alterarDeTipoServicoControllerDeveAlterarUmTipoServico()
	{
		try {
			assertTrue(servicoController.modifyServiceType(servico.getNomeTipoServico(),
					servico));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	// Metodo para inserir um novo metodo de servico
	public void inserirTipoServicoNaoPodePassarTipoServicoNullo()
	{
		try {
			assertFalse(servicoController.includeServiceType(null));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	public void excluirTipoServicoNaoPodePassarTipoServicoNullo() 
	{
		try {
			assertFalse(servicoController.deleteServiceType(null));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo

	@Test
	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	public void alterarTipoServicoNaoPodePassarTipoServicoNullo() 
	{
		try {
			assertFalse(servicoController.modifyServiceType(null, null));
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}//Fim  do metodo
	
	
	@Test
	// Metodo teste para mostrar os barbeiros 
	public void mostrarBarbeirosDeBarbeiroControllerDeveMostrarUmBarbeiro()
			throws SQLException 
			{
		ResultSet rs = servicoController.showRegistredServiceTypes(servico);
		while (rs.next())
			;
	}//Fim  do metodo

	@Test
	// Metodo teste para verificar a pesquisa por nomes
	public void pesquisarPorNomeDeTipoServicoControllerDeveMostrarUmServico()
			throws SQLException 
			{
		ResultSet rs = servicoController.searchServiceTypeByName(servico);
		while (rs.next())
			;
	}//Fim  do metodo

}//Fim  da classe
