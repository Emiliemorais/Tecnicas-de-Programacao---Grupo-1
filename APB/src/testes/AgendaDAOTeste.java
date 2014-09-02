package testes;
// Importando bibliotecas
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Agenda;

import org.junit.Test;

import dao.AgendaDAO;
// Inicio da classe
public class AgendaDAOTeste 
{

	// Instancias da classe
	Agenda contato = new Agenda ();
	Agenda contato2 = new Agenda ();
	AgendaDAO agendaDAO = AgendaDAO.getInstance ();

	@Test
	// Metodo que retorna a instancia
	public void getInstanceDeAgendaDAODeveRetonarInstanciaCorrente ()
	{
		assertEquals ( AgendaDAO.getInstance ( ), agendaDAO );
	}// Fim do metodo

	@Test
	// Metodo para cadastrar o contato
	public void inserirDeAgendaDAODeveCadastrarUmContato () 
	{
		
		try {
			assertTrue(   agendaDAO.incluir ( contato ) );
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
			assertTrue ( agendaDAO.excluir ( contato ) );
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
			assertTrue ( agendaDAO.alterar ( contato.getNome(), contato, contato2) );
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
			assertFalse ( agendaDAO.incluir (null) );
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
			assertFalse(agendaDAO.excluir ( null ) );
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
			assertFalse( agendaDAO.alterar ( contato.getNome (), contato, null ) );
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
			assertFalse ( agendaDAO.alterar ( contato.getNome (), null, contato ) );
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
			ResultSet rs = agendaDAO.mostrarContatosCadastrados ( contato );

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
			ResultSet rs = agendaDAO.pesquisarPorNome ( contato );

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
			ResultSet rs = agendaDAO.pesquisarPorTelefone ( contato );

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
