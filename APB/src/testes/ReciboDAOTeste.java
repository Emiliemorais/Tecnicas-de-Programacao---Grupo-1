// Pacote ao qual a classe pertence
package testes;

// Importações
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import model.Report;
import org.junit.Before;
import org.junit.Test;
import dao.AgendaDAO;
import dao.ReciboDAO;
import exception.ReciboException;
import exception.RelatorioException;
// Fim das importações

// Início da classe
public class ReciboDAOTeste
{
	Report relatorio = new Report();

// Tratamento de exceções
	@Before
	public void setUp() throws ReciboException, ParseException
	{
		try
		{
			relatorio.setBarberName("Fulano");
			relatorio.setFinalDate("09/09/2013");
			relatorio.setInitialDate("01/01/2013");
		}
		catch (RelatorioException e)
		{
			e.printStackTrace();
		}
	}

// Testes
	@Test
	public void getInstanceDeReciboDAODeveRetonarInstanciaCorrente()
	{
		ReciboDAO reciboDAO = ReciboDAO.getInstance();
		assertEquals(ReciboDAO.getInstance(), reciboDAO);
	}

	@Test
	public void pesquisarPorDataEBArbeiroDAODeveMostrarUmRecibo()
	{
		try
		{
			ReciboDAO reciboDAO = ReciboDAO.getInstance();
			ResultSet rs = reciboDAO.pesquisarServicosDoBarbeiro(
					relatorio.getBarberName(), relatorio.getInitialDate(),
					relatorio.getFinalDate());

			while (rs.next())
                {
				String nome = rs.getString("nome");
				assertEquals("Corte", nome);
                }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
// Fim dos testes

}
// Fim da classe
