package testes;

// Importando dados
import static org.junit.Assert.*;

import java.text.ParseException;

import model.Report;

import org.junit.Before;
import org.junit.Test;

import exception.ReportException;

// Inicio da classe
public class RelatorioTeste 
{

	Report relatorio;

	@Before
	/*
	 * Incio do metodo setUp() Ela tem a funcao de setar os valores na classe
	 * Alem de Trratar o erro caso nao como requeremos
	 */
	public void setUp () throws ParseException 
	{
		try {
			relatorio = new Report () ;

			relatorio.setBarberName ( "Chico" );
			relatorio.setServiceType ( "barba" );
			relatorio.setInitialDate ( "01/01/2013" );
			relatorio.setFinalDate ( "09/09/2013");

		} catch  ( NullPointerException e ) 
		{
			e.printStackTrace();
		} catch  ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo setUp()

	@Test ( expected = NullPointerException.class )
	/*
	 * Inicio da classe dataInicialNaoPodeSerSettadaNula() Verifica se a data
	 * tratada e nula no metodo Caso seja nula, uma excecao e detectada e
	 * tratada
	 */
	public void dataInicialNaoPodeSerSettadaNula() throws NullPointerException,
			ParseException {
		try {
			relatorio.setInitialDate(null);
		} catch ( ReportException e ) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo dataInicialNaoPodeSerSettadaNula()

	@Test( expected = AssertionError.class )
	/*
	 * Inicio da classe dataInicialNaoPodeSerSettadaEmBranco() Verifica se a
	 * data tratada esta em braco dentro do metodo Caso esteja em braco, uma
	 * excecao e detectada e tratada
	 */
	public void dataInicialNaoPodeSerSettadaEmBranco ()
			throws NullPointerException, ParseException 
			{

		// Comeco do try
		try {
			relatorio.setInitialDate("");
		} catch ( ReportException e ) 
		{
			e.printStackTrace();
		}// Fim do try
	}// Fim do metodo

	@Test(expected = NullPointerException.class)
	/*
	 * Inicio da classe dataFinalNaoPodeSerSettadaNula() Verifica se a data
	 * final tratada e nula no metodo Caso seja nula, uma excecao e detectada e
	 * tratada
	 */
	public void dataFinalNaoPodeSerSettadaNula () throws NullPointerException,
			ParseException {

		// Inicio do try
		try {
			relatorio.setFinalDate(null);
		} catch ( ReportException e ) 
		{
			e.printStackTrace();
		}// Fim do try
	}// Fim do metodo

	@Test( expected = AssertionError.class )
	/*
	 * Inicio da classe dataFinalNaoPodeSerSettaEmBranco() Verifica se a data
	 * tratada esta em braco dentro do metodo Caso esteja em braco, uma excecao
	 * e detectada e tratada
	 */
	public void dataFinalNaoPodeSerSettaEmBranco () throws NullPointerException,
			ParseException 
			{

		// Inicio do try
		try {
			relatorio.setFinalDate("");
		} catch ( ReportException e ) 
		{
			e.printStackTrace();
		}// Fim do try
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test( expected = NullPointerException.class)
	public void barbeiroNaoPodeSerSettadoNulo() 
	{
		try {
			relatorio.setBarberName ( null );
		} catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel tratada esta em braco dentro do
	 * metodo Caso esteja em braco, uma excecao e detectada e tratada
	 */
	@Test( expected = AssertionError.class )
	public void barbeiroNaoPodeSerSettoEmBranco () 
	{
		try {
			relatorio.setBarberName("");
		} catch ( ReportException e )
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test( expected = NullPointerException.class )
	public void tipoDeServicoNaoPodeSerSettadoNulo ()
	{
		try {
			relatorio.setServiceType( null );
		} catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel tratada esta em braco dentro do
	 * metodo Caso esteja em braco, uma excecao e detectada e tratada
	 */
	@Test( expected = AssertionError.class )
	public void tipoDeServicoNaoPodeSerSettoEmBranco () 
	{
		try {
			relatorio.setServiceType("");
		} catch ( ReportException e )
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test( expected = IllegalArgumentException.class )
	public void construtorDeRelatorioNaoPodePassarBarbeiroNulo () 
	{
		try {
			new Report ( "2013-01-01", "2013-01-01", null, "barba" );
		} catch ( ReportException e ) {
			e.printStackTrace ();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test( expected = IllegalArgumentException.class )
	public void construtorDeRelatorioNaoPodePassarDataFinalNula() 
	{
		try {
			new Report ( "2013-01-01", null, "Chico", "barba" );
		} catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test ( expected = IllegalArgumentException.class )
	public void construtorDeRelatorioNaoPodePassarDataInicialNula ()
	{
		try {
			new Report(null, "2013-01-01", "Chico", "barba");
		} catch (ReportException e) 
		{
			e.printStackTrace();
		}
	}// Fim do metodo

	/*
	 * Inicio da classe Verifica se a variavel de entrada e nula no metodo Caso
	 * seja nula, uma excecao e detectada e tratada
	 */
	@Test( expected = IllegalArgumentException.class )
	public void construtorDeRelatorioNaoPodePassarTipoServicoNulo () 
	{
		try {
			new Report ( "2013-01-01", "2013-01-01", "Chico", null );
		} catch ( ReportException e ) 
		{
			e.printStackTrace ();
		}
	}// Fim do metodo

	@Test
	// Verifica se todos os dados do construtor estao corretos
	public void construtorDeRelatorioPassandoTodosOsDadosCorretos () 
	{
		try {
			new Report ( "2013-01-01", "2013-12-31", "Chico", "barba" );
			assertEquals ( relatorio, relatorio );
		} catch (ReportException e) {
			e.printStackTrace ();
		}
	}// Fim do metodo
		// Chamada para metodo que testa Barbeiro

	@Test
	public void metodoParaTestarGetterDeBarbeiro () 
	{
		assertEquals ( "Chico", relatorio.getBarberName () );
	}// Fim do metodo
		// Chamada para metodo que testa TipoDeServico()

	@Test
	public void metodoParaTestarGetterDeTipoDeServico () 
	{
		assertEquals ( "barba", relatorio.getServiceType () );
	}// Fim do metodo
		// Chamada para metodo que testa DataInicial

	@Test
	public void metodoParaTestarGetterDeDataInicial ()
	{
		assertEquals ( "2013-01-01", relatorio.getInitialDate () );
	}// Fim do metodo
		// Chamada para metodo que testa DataFinal

	@Test
	public void metodoParaTestarGetterDeDataFinal() 
	{
		assertEquals( "2013-09-09", relatorio.getFinalDate () );
	}// Fim do metodo
		// Chamada para metodo que testa Converter

	@Test
	public void testeDataParaConverter () 
	{
		try {
			relatorio.convertDateToAbntFormat( "2010-10-10" );
		} catch ( ParseException e ) {
			e.printStackTrace();
		}
	}// Fim do metodo

}// Fim da classe Relatorio pessoa

