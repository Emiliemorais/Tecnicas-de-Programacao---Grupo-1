package testes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Barbeiro;
import dao.BarbeiroDAO;
import dao.FactoryConnection;
import exception.BarbeiroException;

public class BarbeiroDAOTeste
{

	Barbeiro barbeiro = new Barbeiro();
	Barbeiro barbeiro2 = new Barbeiro();
	
	@Before
	/*
	 * Inicializa dois barbeiros como nomes diferentes e dados iguais.
	 * Captura exceções da inclusão dos dados.
	 */
	public void setUp()
	{
		try
		{
			barbeiro.setNome("Alessandro");
			barbeiro.setRg("418757896");
			barbeiro.setTelefone("3389-9085");
			barbeiro.setCpf("02919594150");
			barbeiro.setCadeira("5");
			barbeiro2.setNome("Luciano");
			barbeiro2.setRg("418757896");
			barbeiro2.setTelefone("3389-9085");
			barbeiro2.setCpf("02919594150");
			barbeiro2.setCadeira("5");
			
			BarbeiroDAO barbeiroDao = BarbeiroDAO.getInstance();
			barbeiroDao.incluir(barbeiro);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		catch (BarbeiroException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}	
	}

	BarbeiroDAO barbeiroDAO = BarbeiroDAO.getInstance();
	
	@Test
	/* 
	 * Define uma assertiva que é disparada se o retorno do método 'getInstance()' 
	 * não for igual à instância declarada logo acima (barbeiroDAO).
	 */
	public void getInstanceDeBarbeiroDAODeveRetonarInstanciaCorrente()
	{	
		assertEquals( BarbeiroDAO.getInstance(), barbeiroDAO );
	}

	@Test
	/*
	 * Define uma assertiva que é disparada se a inclusão não foi feita
	 *   com sucesso (retorno do método 'incluir()').
	 * Define outra assertiva que checa se o nome que foi cadastrado no banco de dados é compatível
	 *	 com o que foi realmente pedido para se cadastrar.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void inserirDeBarbeiroDAODeveCadastrarUmBarbeiro()
	{
		try
		{
			assertTrue( barbeiroDAO.incluir(barbeiro) );
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		+ " nome = \"" + barbeiro.getNome() + "\";");
			rs.next();
			assertEquals( "Alessandro", rs.getString("nome") );
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	// Define um erro que é esperado
	@Test (expected = AssertionError.class)
	/*
	 * Define uma assertiva que é disparada se a exclusão de um barbeiro não foi feita
	 * 	 com sucesso (retorno do método 'excluir()').
	 * Testa se o método 'fail()' funcionou (erro esperado).
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void excluirDeBarbeiroDAODeveEnviarUmBarbeiro()
	{
		try 
		{
			assertTrue( barbeiroDAO.excluir(barbeiro) );
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		 + " nome = \"" + barbeiro.getNome() + "\";");
			rs.next();
			fail();
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que é disparada se a alteração não foi feita
	 *   com sucesso (retorno do método 'alterar()').
	 * Define outra assertiva que testa se o nome foi realmente alterado, varrendo todas as linhas
	 * 	 da coluna 'nome' procurando pelo nome 'Alessandro'.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void alterarDeBarbeiroDaoDeveAlterarUmBarbeiro()
	{
		try
		{
			assertTrue( barbeiroDAO.alterar( barbeiro.getNome(), barbeiro, barbeiro2 ) );
			
			barbeiroDAO.alterar( barbeiro.getCpf(),barbeiro2, barbeiro );
			Connection connection = FactoryConnection.getInstance().getConnection();
			
			java.sql.PreparedStatement pst1;

			pst1 = connection.prepareStatement("SELECT nome FROM barbeiro WHERE "
												+ " nome = \"" + barbeiro.getNome() + "\";");
			
			ResultSet rs = pst1.executeQuery();
						
			while( rs.next() )
			{
				assertEquals( "Alessandro", rs.getString("nome") );
			}
			
			rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a inclusão de um barbeiro com passagem de argumento nulo.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void inserirDeBarbeiroDAOPassandoUmBarbeiroNulo() 
	{
		try
		{
			assertFalse( barbeiroDAO.incluir(null) );
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a exclusão de um barbeiro com passagem de argumento nulo.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void excluirDeBarbeiroDAOPassandoUmBarbeiroNulo()
	{
		try
		{
			assertFalse( barbeiroDAO.excluir(null) );
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a alteração de um barbeiro com passagem de argumentos nulos.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void alterarDeBarbeiroDaoPassandoUmBarbeiroNulo()
	{
		try
		{
			assertFalse( barbeiroDAO.alterar( barbeiro.getNome(), null, null ) );
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a inclusão de um barbeiro com passagem do argumento 'barbeiro_alterado' nulo.
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void alterarDeBarbeiroDaoPassandoUmBarbeiroAlteradoNulo() 
	{
		try
		{
			assertFalse( barbeiroDAO.alterar( barbeiro.getNome(), null, barbeiro ) );
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que é disparada se há algum nome nulo no banco de dados. 
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void pesquisarDeBarbeiroDAODeveMostrarUmBarbeiro() 
	{
		try 
		{
			ResultSet rs = barbeiroDAO.pesquisar();
			
			while ( rs.next() )
			{
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que é disparada se há algum nome nulo no banco de dados. 
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void mostrarBarbeirosCadastradosDeBarbeiroDAODeveMostrarBarbeiros() 
	{
		try
		{
			ResultSet rs = barbeiroDAO.mostrarBarbeirosCadastrados(barbeiro);
			
			while ( rs.next() )
			{
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que é disparada se o nome do barbeiro cadastrado no banco de dados for nulo. 
	 * Captura exceções SQL que possam ocorrer.
	 */
	public void pesquisarPorNomeDeBarbeiroDAODeveMostrarBarbeiros() 
	{
		try
		{
			ResultSet rs = barbeiroDAO.pesquisarPorNome(barbeiro);
			
			while ( rs.next() )
			{
				String nome = rs.getString("nome");
				assertNotNull(nome);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}