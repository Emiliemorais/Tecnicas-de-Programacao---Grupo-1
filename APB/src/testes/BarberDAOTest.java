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
	 * This method initializes two barbers with different names and same data 
	 * if the data is null the method throws a exception
	 */
	public void setUp ()
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
			
			// BarberDAO class's instance to access the class
			BarberDAO barberDao = BarberDAO.getInstance();
			barberDao.includeBarber(barber);
		}
		catch( NullPointerException e )
		{
			e.printStackTrace();
		}
		catch( BarberException e ) 
		{
			e.printStackTrace();
		}
		catch( SQLException e )
		{
			e.printStackTrace();
		}	
	}

	BarberDAO barberDAO = BarberDAO.getInstance();
	
	@Test
	/* 
	 * This method is used to test the method getInstance 
	 */
	public void getInstanceMethodTest ()
	{	
		assertEquals( BarberDAO.getInstance(), barberDAO );
	}

	@Test
	/*
	 * This method tests the register of a barber, if the register fail an assert is ignited 
	 * the other assert checks if the name that was registered in the database is equal
	 * to the name that was required to register.
	 */
	public void includeBarberMethodTest ()
	{
		try
		{
			assertTrue( barberDAO.includeBarber(barber) );
			
			// Connection interface's instance to connect with the database
			Connection connection = FactoryConnection.getInstance().getConnection();
			
			String sqlCodeToQueryBarber = "SELECT nome FROM barbeiro WHERE "
			   							  + " nome = \"" + barber.getBarberName() + "\";" ;
			// ResultSet interface instance to query a barber's name
			ResultSet queryForBarberName = connection.createStatement().executeQuery(sqlCodeToQueryBarber);
			queryForBarberName.next();
					
			assertEquals( "Alessandro", queryForBarberName.getString("nome") );
			queryForBarberName.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	// Define an error type that is expected
	@Test (expected = AssertionError.class)
	/*
	 * This method is used to test the method deleteBarber
	 */
	public void deleteBarberMethodTeste ()
	{
		try 
		{
			assertTrue( barberDAO.deleteBarber(barber) );
			
			FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
			
			Connection connection = factoryConnectionInstance.getConnection();
			
			String sqlCodeToQueryBarber = "SELECT nome FROM barbeiro WHERE "
										  + " nome = \"" + barber.getBarberName() + "\";" ;
			
			ResultSet queryForBarberName = connection.createStatement().executeQuery(sqlCodeToQueryBarber);
			queryForBarberName.next();
			fail();
			queryForBarberName.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * This method is used to test the method changeBarber
	 */
	public void changeBarberMethodTest ()
	{
		try
		{
			assertTrue( barberDAO.changeBarber( barber.getBarberName(), barber, secondBarber ) );
			
			barberDAO.changeBarber( barber.getBarberCpf(),secondBarber, barber );
			
			FactoryConnection factoryConnectionInstance = FactoryConnection.getInstance();
			Connection connection = factoryConnectionInstance.getConnection();
			
			// java.sql.PreparedStatement instance to query in the database
			java.sql.PreparedStatement preparedStatement;
			
			String sqlCodeToQueryBarber = "SELECT nome FROM barbeiro WHERE "
										  + " nome = \"" + barber.getBarberName() + "\";";
			
			preparedStatement = connection.prepareStatement(sqlCodeToQueryBarber);
			
			ResultSet queryForBarberName = preparedStatement.executeQuery();
						
			while( queryForBarberName.next() )
			{
				assertEquals( "Alessandro", queryForBarberName.getString("nome") );
			}
			
			queryForBarberName.close();
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * This method tests the method includeBarber with a null barber
	 */
	public void includeBarberNullMethodTest () 
	{
		try
		{
			assertFalse( barberDAO.includeBarber(null) );
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * This method tests the method deleteBarber with a null barber 
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
	 * This method tests the method changeBarber with a null barber 
	 */
	public void changeBarberNullMethodTest()
	{
		try
		{
			assertFalse( barberDAO.changeBarber( barber.getBarberName(), null, null ) );
		} 
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	/*
	 * This method tests the method changeBarber with a null changedBarber 
	 */
	public void changeChangedBarberNullMethodTest() 
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
	 * This method tests the method searchBarberTest, an assert is ignited if the name is not null
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
	 * This method tests the method showRegisteredBarber, an assert is ignited if some name is null
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
	 *  This method tests the method  searchByName, an assert is ignited if the name is not null
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