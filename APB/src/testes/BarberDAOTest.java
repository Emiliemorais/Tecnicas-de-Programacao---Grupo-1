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
import exception.BarberException;

public class BarberDAOTest
{

	// Barber class's instance to access the class
	Barber barber = new Barber();
	Barber secondBarber = new Barber();
	
	@Before
	/*
	 * Inicializa dois barbeiros como nomes diferentes e dados iguais.
	 * Captura exce��es da inclus�o dos dados.
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
			secondBarber.setBarberName("Luciano");
			secondBarber.setBarberRg("418757896");
			secondBarber.setBarberTelephone("3389-9085");
			secondBarber.setBarberCpf("02919594150");
			secondBarber.setBarberChair("5");
			
			// BarberDAO class's instanceto access the class
			BarberDAO barberDao = BarberDAO.getInstance();
			barberDao.includeBarber(barber);
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		catch (BarberException e)
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
	 * Define uma assertiva que � disparada se o retorno do m�todo 'getInstance()' 
	 * n�o for igual � inst�ncia declarada logo acima (barbeiroDAO).
	 */
	public void getInstanceMethodTest()
	{	
		assertEquals( BarberDAO.getInstance(), barberDAO );
	}

	@Test
	/*
	 * Define uma assertiva que � disparada se a inclus�o n�o foi feita
	 *   com sucesso (retorno do m�todo 'incluir()').
	 * Define outra assertiva que checa se o nome que foi cadastrado no banco de dados � compat�vel
	 *	 com o que foi realmente pedido para se cadastrar.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void includeBarberMethodTest()
	{
		try
		{
			assertTrue( barberDAO.includeBarber(barber) );
			
			// Connection interface's instance to connect with the database
			Connection connection = FactoryConnection.getInstance().getConnection();
			
			// ResultSet interface instance to query a barber's name
			ResultSet queryForBarberName = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		+ " nome = \"" + barber.getBarberName() + "\";");
			queryForBarberName.next();
			assertEquals( "Alessandro", queryForBarberName.getString("nome") );
			queryForBarberName.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	// Define um erro que � esperado
	@Test (expected = AssertionError.class)
	/*
	 * Define uma assertiva que � disparada se a exclus�o de um barbeiro n�o foi feita
	 * 	 com sucesso (retorno do m�todo 'excluir()').
	 * Testa se o m�todo 'fail()' funcionou (erro esperado).
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void deleteBarberMethodTeste()
	{
		try 
		{
			assertTrue( barberDAO.deleteBarber(barber) );
			
			Connection connection = FactoryConnection.getInstance().getConnection();
			ResultSet queryForBarberName = connection.createStatement()
						   .executeQuery("SELECT nome FROM barbeiro WHERE "
								   		 + " nome = \"" + barber.getBarberName() + "\";");
			queryForBarberName.next();
			fail();
			queryForBarberName.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que � disparada se a altera��o n�o foi feita
	 *   com sucesso (retorno do m�todo 'alterar()').
	 * Define outra assertiva que testa se o nome foi realmente alterado, varrendo todas as linhas
	 * 	 da coluna 'nome' procurando pelo nome 'Alessandro'.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void changeBarberMethodTest()
	{
		try
		{
			assertTrue( barberDAO.modifyBarber( barber.getBarberName(), barber, secondBarber ) );
			
			barberDAO.modifyBarber( barber.getBarberCpf(),secondBarber, barber );
			Connection connection = FactoryConnection.getInstance().getConnection();
			
			// java.sql.PreparedStatement instance to query in the database
			java.sql.PreparedStatement preparedStatement;

			preparedStatement = connection.prepareStatement("SELECT nome FROM barbeiro WHERE "
												+ " nome = \"" + barber.getBarberName() + "\";");
			
			ResultSet queryForBarberName = preparedStatement.executeQuery();
						
			while( queryForBarberName.next() )
			{
				assertEquals( "Alessandro", queryForBarberName.getString("nome") );
			}
			
			queryForBarberName.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a inclus�o de um barbeiro com passagem de argumento nulo.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void includeBarberNullMethodTest() 
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
	 * Testa se ocorre a exclus�o de um barbeiro com passagem de argumento nulo.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void deleteBarberNullMethodTest()
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
	 * Testa se ocorre a altera��o de um barbeiro com passagem de argumentos nulos.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void changeBarberNullMethodTest()
	{
		try
		{
			assertFalse( barberDAO.modifyBarber( barber.getBarberName(), null, null ) );
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Testa se ocorre a inclus�o de um barbeiro com passagem do argumento 'barbeiro_alterado' nulo.
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void changeChangedBarberNullMethodTest() 
	{
		try
		{
			assertFalse( barberDAO.modifyBarber( barber.getBarberName(), null, barber ) );
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que � disparada se h� algum nome nulo no banco de dados. 
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void searchBarberTest() 
	{
		try 
		{
			ResultSet queryForBarber = barberDAO.searchBarber();
			
			while ( queryForBarber.next() )
			{
				// Receives a name of a barber
				String barberName = queryForBarber.getString("nome");
				assertNotNull(barberName);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que � disparada se h� algum nome nulo no banco de dados. 
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void showRegisteredBarberMethodTest() 
	{
		try
		{
			ResultSet queryForBarber = barberDAO.showRegisteredBarber(barber);
			
			while ( queryForBarber.next() )
			{			
				// Receives a name of a barber
				String barberName = queryForBarber.getString("nome");
				assertNotNull(barberName);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * Define uma assertiva que � disparada se o nome do barbeiro cadastrado no banco de dados for nulo. 
	 * Captura exce��es SQL que possam ocorrer.
	 */
	public void searchByNameMethodTest() 
	{
		try
		{
			ResultSet queryForBarber = barberDAO.searchByName(barber);
			
			while ( queryForBarber.next() )
			{
				// Receives a name of a barber
				String barberName = queryForBarber.getString("nome");
				assertNotNull(barberName);
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}