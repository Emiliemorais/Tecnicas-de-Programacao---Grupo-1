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

import model.Barber;
import dao.BarberDAO;
import dao.FactoryConnection;
import exception.BarbeiroException;

public class BarbeiroDAOTeste
{

	Barber barber = new Barber();
	Barber barbeiro2 = new Barber();
	
	@Before
	/*
	 * Inicializa dois barbeiros como nomes diferentes e dados iguais.
	 * Captura exceções da inclusão dos dados.
	 */
	public void setUp()
	{
		try
		{
			barber.setBarberName("Alessandro");
			barber.setBarberRg("418757896");
			barber.setBarberTelephone("3389-9085");
			barber.setBarberCpf("02919594150");
			barber.setBarberChair("5");
			barbeiro2.setBarberName("Luciano");
			barbeiro2.setBarberRg("418757896");
			barbeiro2.setBarberTelephone("3389-9085");
			barbeiro2.setBarberCpf("02919594150");
			barbeiro2.setBarberChair("5");
			
			BarberDAO barbeiroDao = BarberDAO.getInstance();
			barbeiroDao.includeBarber(barber);
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

	BarberDAO barberDAO = BarberDAO.getInstance();
	
	@Test
	/* 
	 * Define uma assertiva que é disparada se o retorno do método 'getInstance()' 
	 * não for igual à instância declarada logo acima (barbeiroDAO).
	 */
	public void getInstanceDeBarbeiroDAODeveRetonarInstanciaCorrente()
	{	
		assertEquals( BarberDAO.getInstance(), barberDAO );
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
			assertTrue( barberDAO.includeBarber(barber) );
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		+ " nome = \"" + barber.getBarberName() + "\";");
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
			assertTrue( barberDAO.deleteBarber(barber) );
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet rs = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		 + " nome = \"" + barber.getBarberName() + "\";");
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
			assertTrue( barberDAO.changeBarber( barber.getBarberName(), barber, barbeiro2 ) );
			
			barberDAO.changeBarber( barber.getBarberCpf(),barbeiro2, barber );
			Connection connection = FactoryConnection.getInstance().getConnection();
			
			java.sql.PreparedStatement pst1;

			pst1 = connection.prepareStatement("SELECT nome FROM barbeiro WHERE "
												+ " nome = \"" + barber.getBarberName() + "\";");
			
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
			assertFalse( barberDAO.includeBarber(null) );
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
			assertFalse( barberDAO.deleteBarber(null) );
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
			assertFalse( barberDAO.changeBarber( barber.getBarberName(), null, null ) );
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
			assertFalse( barberDAO.changeBarber( barber.getBarberName(), null, barber ) );
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
			ResultSet rs = barberDAO.searchBarber();
			
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
			ResultSet rs = barberDAO.showRegisteredBarber(barber);
			
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
			ResultSet rs = barberDAO.searchByName(barber);
			
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