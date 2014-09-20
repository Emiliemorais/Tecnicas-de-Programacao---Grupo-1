package testes;

//Importando dados
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Barber;

import org.junit.Before;
import org.junit.Test;

import control.BarberController;
import exception.BarbeiroException;

//Inicio da classe
public class BarbeiroControllerTeste 
{

	// Instancia de Barbeiro
	Barber barber = new Barber();

	@Before
	/*
	 * Incio do metodo setUp() Ela tem a funcao de setar os valores na classe
	 * Alem de Trratar o erro caso nao como requeremos
	 */
	public void setUp () 
	{
		try {
			barber.setBarberName ( "Alessandro" );
			barber.setBarberRg ( "418757896" );
			barber.setBarberTelephone ( "3389-9085" );
			barber.setBarberCpf ( "02919594150" );
			barber.setBarberChair ( "5" );
		} catch ( NullPointerException e )
		{
			e.printStackTrace ();
		} catch ( BarbeiroException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo setUp()

	BarberController barbeiroController = BarberController.getInstance ();

	@Test
	// Metodo para passar os dados para a classe por meio do encapsulamento
	public void getInstanceDeBarbeiroControlerDeveRetonarInstanciaCorrente () 
	{
		assertEquals( BarberController.getInstance (), barbeiroController );
	}// Fim do metodo

	@Test
	public void inserirDeBarbeiroControllerDeveEnviarUmBarbeiro () 
	{
		try {
			assertTrue ( barbeiroController.includeBarber ( barber ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	@Test
	// Metodo para passar a altoriazacao de exclusao dos dados para a classe por meio do encapsulamento
	public void excluirDeBarbeiroControllerDeveEnviarUmBarbeiro () 
	{
		try {
			assertTrue ( barbeiroController.deleteBarber ( barber ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	@Test
	// Metodo para passar a altoriazacao de alterar os dados da a classe por meio do encapsulamento
	public void alterarDeBarbeiroControllerDeveEnviarUmBarbeiro () 
	{
		try {
			assertTrue ( barbeiroController.alterar(barber.getBarberName (), barber ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}

	// Fim do metodo
	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void inserirBarbeiroNaoPodePassarBarbeiroNullo ()
{
		try {
			assertFalse( barbeiroController.includeBarber ( null ) );
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
	public void excluirBarbeiroNaoPodePassarBarbeiroNullo () 
	{
		try {
			assertFalse ( barbeiroController.deleteBarber ( null ) );
		} catch ( SQLException e )
		{
			e.printStackTrace () ;
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
			assertFalse( barbeiroController.alterar( null, null ) ) ;
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	@Test
	public void procurarPorBarbeiroControllerDeveMostrarUmBarbeiro ()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.searchBarbers ( );
		while ( rs.next () )
			;
	}// Fim do metodo

	@Test
	public void mostrarBarbeirosDeBarbeiroControllerDeveMostrarUmBarbeiro ()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.showRegisteredBarbers ( barber );
		while ( rs.next () )
			;
	}// Fim do metodo

	@Test
	public void pesquisarPorNomeDeBarbeiroControllerDeveMostrarUmBarbeiro ()
			throws SQLException 
			{
		ResultSet rs = barbeiroController.searchBarberByName ( barber );
		while ( rs.next () )
			;
	}// Fim do metodo
}// Fim da Classe
