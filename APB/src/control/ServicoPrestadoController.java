//Pacote ao qual a classe pertence
package control;

// Importações
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DoneServiceDAO;
import model.ServicoPrestado;
// Fim das importações

// Inicio da classe
public class ServicoPrestadoController
{

	private ServicoPrestadoController()
	{
	    // Blank
	}

	// Instanciamento
	public static ServicoPrestadoController getInstance()
	{
		if (instance == null)
        {
            instance = new ServicoPrestadoController();
        }
        else
        {
            // Nothing to do
        }

        return instance;
	}

	private static ServicoPrestadoController instance;

    // Metodo que insere um servico
	public boolean inserir(ServicoPrestado servico) throws SQLException
	{
		if (servico != null)
        {
			DoneServiceDAO.getInstance().includeServiceType(servico);
			return true;
		}
		else
        {
            // Nothing to do
        }

		return false;
	}
    // Fim do metodo

    // Metodo que exclui um servico
	public boolean excluir(ServicoPrestado servico) throws SQLException
	{
		if (servico !=  null)
        {
			DoneServiceDAO.getInstance().deleteServiceType(servico);
			return true;
		}
		else
        {
            // Nothing to do
        }
		return false;
	}
	// Fim do metodo

	// Metodo que prove informações sobre os servico prestados que estao cadastrados
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException
	{
		return DoneServiceDAO.getInstance().showRegistredDoneServices(servico);
	}
    // Fim do metodo

}
// Fim da classe
