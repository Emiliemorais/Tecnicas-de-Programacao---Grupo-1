package testes;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;

import model.ServicoPrestado;

import org.junit.Before;
import org.junit.Test;

import dao.ServicoPrestadoDAO;
import exception.ServicoException;

public class ServicoPrestadoDAOTeste {

	ServicoPrestado servico = new ServicoPrestado();
	ServicoPrestado servico2 = new ServicoPrestado();
	
	/* 
	 * M�todo utilizado para receber os atributos de um Servi�o Prestado de forma correta,
	 * para realiza��o do teste, lan�ando exce�oes de atributos nulos e problemas de convers�o
	*/
	@Before
	public void setUp() {
		try {
			servico.setNomeServico("Corte");
			servico.setNomeBarbeiro("Alessandro");
			servico.setData("10/10/2010");
			servico.setPreco("10,00");
			servico2.setNomeServico("Barba");
			servico2.setNomeBarbeiro("Luciano");
			servico2.setData("01/01/2010");
			servico2.setPreco("9,90");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ServicoException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	ServicoPrestadoDAO servicoDAO = ServicoPrestadoDAO.getInstance();
	
	/* 
	 * M�todo utilizado para testar o getInstance da classe ServicoPrestadoDAO
	*/
	@Test
	public void getInstanceDeServicoPrestadoDAODeveRetonarInstanciaCorrente() {
		assertEquals(ServicoPrestadoDAO.getInstance(), servicoDAO);
	}

	/* 
	 * M�todo utilizado para testar a inser�ao correta de um Servico Prestado
	*/
	@Test
	public void inserirDeServicoPrestadoDAODeveCadastrarUmServicoPrestado() {
		try {
			assertTrue(servicoDAO.incluir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/* 
	 * M�todo utilizado para testar a exclus�o correta de um Servico Prestado
	*/
	@Test
	public void excluirDeServicoPrestadoDAODeveEnviarUmServicoPrestado() {
		try {
			assertTrue(servicoDAO.excluir(servico));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * M�todo utilizado para testar a inser�ao de um Servico nulo na classe Servi�o Prestado
	*/
	@Test
	public void inserirDeServicoPrestadoDAOPassandoUmServicoNulo() {
		try {
			assertFalse(servicoDAO.incluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* 
	 * M�todo utilizado para testar a exclus�o de um Servico nulo na classe Servi�o Prestado
	*/
	@Test
	public void excluirDeServicoPrestadoDAOPassandoUmServicoNulo() {
		try {
			assertFalse(servicoDAO.excluir(null));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
