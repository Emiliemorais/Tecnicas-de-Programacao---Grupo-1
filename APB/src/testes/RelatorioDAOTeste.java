package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import model.Relatorio;

import org.junit.Before;
import org.junit.Test;

import dao.RelatorioDAO;

import exception.RelatorioException;

public class RelatorioDAOTeste {

	Relatorio relatorio = new Relatorio();

	/* 
	 * M�todo utilizado para receber os atributos de um relatorio de forma correta,
	 * para realiza��o do teste, lan�ando exce�oes de atributos nulos e problemas de convers�o
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
 
	// M�todo utilizado para testar o getInstance da classe RelatorioDAO
	@Test
	public void getInstanceDeRelatorioDAODeveRetonarInstanciaCorrente() {
		RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
		assertEquals(RelatorioDAO.getInstance(), relatorioDAO);
	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado por data
	@Test
	public void procurarPorDataDeRelatorioDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorData(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* 
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado
	 *  por data e servi�o	
	*/
	@Test
	public void pesquisarPorDataEServicoDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataEServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado por barbeiro	
	@Test
	public void pesquisarPorBArbeiroDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorBarbeiro(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado
	 *  por data e servi�o	
	 */
	@Test
	public void pesquisarPorBArbeiroEServicoDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorBarbeiroEServico(relatorio);
			
			while(rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// M�todo utilizado para testar a vizualiza��o de um relatorio, quanto procurado servi�o	
	@Test
	public void pesquisarPorServicoDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado 
	 * por data e barbeiro	
	 */
	@Test
	public void pesquisarPorDataEBArbeiroDAODeveMostrarUmRelatorio() {
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataEBarbeiro(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * M�todo utilizado para testar a vizualiza��o de um relatorio, quando procurado 
	 * por data, servi�o e barbeiro	
	 */
	@Test
	public void pesquisarPorDataBarbeiroEServicoDAODeveMostrarUmRelatorio(){
		try {
			RelatorioDAO relatorioDAO = RelatorioDAO.getInstance();
			ResultSet rs = relatorioDAO.pesquisarPorDataBarbeiroEServico(relatorio);
			
			while (rs.next()) {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
