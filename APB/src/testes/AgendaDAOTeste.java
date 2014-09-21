package testes;
// Importando bibliotecas
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;

import org.junit.Test;

import dao.PhonebookDAO;
// Inicio da classe
public class AgendaDAOTeste 
{

	// Instancias da classe
	Agenda contato = new Agenda ();
	Agenda contato2 = new Agenda ();
	PhonebookDAO agendaDAO = PhonebookDAO.getInstance ();

	@Test
	// Metodo que retorna a instancia
	public void getInstanceDeAgendaDAODeveRetonarInstanciaCorrente ()
	{
		assertEquals ( PhonebookDAO.getInstance ( ), agendaDAO );
	}// Fim do metodo

	@Test
	// Metodo para cadastrar o contato
	public void inserirDeAgendaDAODeveCadastrarUmContato () 
	{
		
		try {
			assertTrue(   agendaDAO.includeDataToPhonebook ( contato ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace () ;
		}
	}// Fim do metodo

	@Test
	// Metodo teste para escolher um contato para ser excluido 	
	public void excluirDeAgendaDAODeveEnviarUmAgenda () 
	{
		try {
			assertTrue ( agendaDAO.deletePhonebookData ( contato ) );
		} catch ( SQLException e )
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	// Metodo teste para escolher um contato para ser alterado
	@Test
	public void alterarDeAgendaDAODeveEnviarUmContato () 
	{
		try {
			assertTrue ( agendaDAO.editPhonebookData ( contato.getNome(), contato, contato2) );
		} catch ( SQLException e )
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void inserirDeAgendaDAOPassandoUmContatoNulo () 
	{
		try {
			assertFalse ( agendaDAO.includeDataToPhonebook (null) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo
	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void excluirDeAgendaDAOPassandoUmContatoNulo () 
	{
		try {
			assertFalse(agendaDAO.deletePhonebookData ( null ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace () ;
		}
	}// Fim do metodo
	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void alterarDeAgendaDAOPassandoUmContatoNulo() 
	{
		try {
			assertFalse( agendaDAO.editPhonebookData ( contato.getNome (), contato, null ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo
	/*
	 * Inicio da classe 
	 * Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test
	public void alterarDeAgendaDAOPassandoUmAgendaAleradoNulo ( ) 
	
	{
		try {
			assertFalse ( agendaDAO.editPhonebookData ( contato.getNome (), null, contato ) );
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}
	// Fim do metodo
	@Test
	// Metodo teste para mostrar contatos
	public void mostrarContatosAgendaDAODeveMostrarContato ()
	{
		try {
			ResultSet rs = agendaDAO.showRegisteredContacts ( contato );

			while ( rs.next () ) {
				String nome = rs.getString( "nome" );
				assertNotNull ( nome );
			}
		} catch ( SQLException e ) {
			e.printStackTrace  ();
		}
	}// Fim do metodo

	@Test
	public void pesquisaPorNomeDeAgendaDAODeveMostrarContato () 
	{
		try {
			ResultSet rs = agendaDAO.searchByName ( contato );

			while (rs.next()) {
				String nome = rs.getString ( "nome" );
				assertNotNull ( nome ) ;
			}
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	@Test
	// Metodo teste para escolher um contato de pesquisa e assim mostrar os barbeiros
	public void pesquisarPorTelefoneDeBarbeiroDAODeveMostrarBarbeiros () 
	{
		try 
		{
			ResultSet rs = agendaDAO.searchByPhone ( contato );

			while ( rs.next() ) 
			{
				String nome = rs.getString( "nome" );
				assertNotNull( nome );
			}
		} catch ( SQLException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

}
// Fim da classe
