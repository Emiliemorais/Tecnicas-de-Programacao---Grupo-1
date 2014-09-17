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
import control.ReciboController;
import exception.ReciboException;
import exception.RelatorioException;
// Fim das importações

// Início da classe
public class ReciboControllerTeste
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

// Testes
	@Test
	public void getInstanceDeReciboDAODeveRetonarInstanciaCorrente()
	{
		ReciboController reciboController = ReciboController.getInstance();
		assertEquals(ReciboController.getInstance(), reciboController);
	}

	@Test
	public void procurarPorDataEBarbeiroDeReciboControllerDeveMostrarUmRecibo() throws SQLException
	{
		ReciboController reciboController = new ReciboController();
		ResultSet rs = reciboController.pesquisarServicosDoBarbeiro(relatorio.getBarberName(), relatorio.getInitialDate(), relatorio.getFinalDate());

		while(rs.next());
	}
// Fim dos testes

}
// Fim da classe
