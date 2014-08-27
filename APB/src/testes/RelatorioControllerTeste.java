package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Relatorio;

import org.junit.Before;
import org.junit.Test;

import control.RelatorioController;
import exception.RelatorioException;

public class RelatorioControllerTeste {

	Relatorio relatorio = new Relatorio();
	
	/* 
	 * Método utilizado para receber os atributos de um Relatorio de forma correta,
	 * para realização do teste, lançando exceçoes de atributos nulos e problemas de conversão
	*/
	@Before
	public void setUp() throws RelatorioException, ParseException {
		try {
			relatorio.setBarbeiro("Luciano");
			relatorio.setDataFinal("09/09/2013");
			relatorio.setDataInicial("01/01/2013");
			relatorio.setTipoServico("corte");
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
	
	/* 
	 * Método utilizado para testar o getInstance da classe RelatorioDAO
	*/

	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente() {
		RelatorioController relatorioController = RelatorioController.getInstance();
		assertEquals(RelatorioController.getInstance(), relatorioController);
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um serviço
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorServico(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de uma data
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorData(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * e de um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorBarbeiroEServico(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * e de uma data e um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataBarbeiroEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorDataBarbeiroEServico(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * e de uma data vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataEBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorDataEBarbeiro(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de uma data
	 * e de um serviço vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorDataEServicoDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorDataEServico(relatorio);
		
		while(rs.next());
	}
	
	/* 
	 * Método utilizado para testar a pesquisa de um relatorio através de um barbeiro
	 * vendo se existe a amostra do relatorio
	*/
	@Test
	public void procurarPorBarbeiroDeRelatorioControllerDeveMostrarUmRelatorio() throws SQLException {
		RelatorioController relatorioController = new RelatorioController();
		ResultSet rs = relatorioController.pesquisarPorBarbeiro(relatorio);
		
		while(rs.next());
	}

}
