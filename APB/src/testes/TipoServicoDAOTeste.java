package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoServico;

import org.junit.Test;

import dao.TipoServicoDAO;

public class TipoServicoDAOTeste 
{

	TipoServico tiposervico = new TipoServico();
	TipoServico tiposervico2 = new TipoServico();
	TipoServicoDAO servicoDAO = TipoServicoDAO.getInstance();
	
	// Test if a instance previous declared is the current one
	@Test
	public void getInstanceDeTipoServicoDAODeveRetonarInstanciaCorrente() 
	{
		assertEquals( TipoServicoDAO.getInstance(), servicoDAO );
	}
	
	// Test if a inclusion of a service type was made right
	@Test
	public void inserirDeTipoServicoDAODeveCadastrarUmTipoServico() 
	{
		try 
		{
			assertTrue( servicoDAO.incluir(tiposervico) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if a exclusion of a service type was made right
	@Test
	public void excluirDeTipoServicoDAODeveEnviarUmTipoServico() 
	{
		try 
		{
			assertTrue( servicoDAO.excluir(tiposervico) );
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	// Test if a modification of a service type was made right
	@Test
	public void alterarDeTipoServicoDAODeveEnviarUmTipoServico() 
	{
		try 
		{
			assertTrue( servicoDAO.alterar(tiposervico.getNomeTipoServico(),
										   tiposervico, tiposervico2) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'inserir', on 'TipoServicoDAO', don't accept null argument
	@Test
	public void inserirDeTipoServicoDAOPassandoUmServicoNulo() 
	{
		try 
		{
			assertFalse( servicoDAO.incluir(null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'excluir', on 'TipoServicoDAO', don't accept null argument
	@Test
	public void excluirDeTipoServicoDAOPassandoUmServicoNulo() 
	{
		try 
		{
			assertFalse( servicoDAO.excluir(null) );
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'alterar', on 'TipoServicoDAO', don't accept null argument
	@Test
	public void alterarDeTipoServicoDAOPassandoUmServicoNulo() 
	{
		try 
		{
			assertFalse( servicoDAO.alterar(tiposervico.getNomeTipoServico(),
											tiposervico, null));
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	// Test if the method 'alterar', on 'TipoServicoDAO', don't accept as null argument 'tipoServico'
	@Test
	public void alterarDeTipoServicoDAOPassandoUmServicoAlteradoNulo() 
	{
		try 
		{
			assertFalse( servicoDAO.alterar(tiposervico.getNomeTipoServico(),
											null, tiposervico));
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	 /* Test the method 'mostrarTipoServicoCadastrados' 
	  * (Test if the query result, on field name, is not null)
	  */ 
	@Test
	public void mostrarServicosDeTipoServicoDAODeveMostrarServico() 
	{
		try 
		{
			ResultSet rs = servicoDAO.mostrarTipoServicoCadastrados(tiposervico);
			
			while( rs.next() ) 
			{
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/* Test the method 'pesquisarPorNome'
	 * (Test if the query result, on field name, is not null) 
	 */
	@Test
	public void pesquisarPorNomeDeTipoServicoDAODeveMostrarServico() 
	{
		try 
		{
			ResultSet rs = servicoDAO.pesquisarPorNome(tiposervico);
			
			while ( rs.next() ) 
			{
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} 
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
