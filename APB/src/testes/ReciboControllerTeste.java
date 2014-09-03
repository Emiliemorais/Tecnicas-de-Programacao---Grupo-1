// Pacote ao qual a classe pertence
package testes;

// Importações
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import model.Relatorio;
import org.junit.Before;
import org.junit.Test;
import control.ReciboController;
import exception.ReciboException;
import exception.RelatorioException;
// Fim das importações

// Início da classe
public class ReciboControllerTeste
{
	Relatorio relatorio = new Relatorio();

// Tratamento de exceções
	@Before
	public void setUp() throws ReciboException, ParseException
	{
        try
        {
            relatorio.setBarbeiro("Fulano");
            relatorio.setDataFinal("09/09/2013");
            relatorio.setDataInicial("01/01/2013");
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
		ResultSet rs = reciboController.pesquisarServicosDoBarbeiro(relatorio.getBarbeiro(), relatorio.getDataInicial(), relatorio.getDataFinal());

		while(rs.next());
	}
// Fim dos testes

}
// Fim da classe
