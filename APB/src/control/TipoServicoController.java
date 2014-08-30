// Pacote ao qual pertence a classe
package control;

// Importando dados
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.TipoServicoDAO;
import model.TipoServico;
// Fim da importa��o dos dados

// In�cio da classe
public class TipoServicoController {

	private static TipoServicoController instance;

// M�todo de inserir o tipo de servi�o
	public boolean inserir(TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().incluir(tipoServico);
			return true;
		}
	}
// Fim do m�todo

// M�todo de alterar o tipo de servi�o
	public boolean alterar(String nome,TipoServico tipoServico) throws SQLException {
		if (tipoServico == null) {
			return false;
		} else {
			TipoServico tipoServico_alterado = tipoServico;
			TipoServicoDAO.getInstance().alterar(nome,tipoServico_alterado, tipoServico);
			return true;
		}
	}
// Fim do m�todo

// M�todo de excluir o tipo de servi�o
	public boolean excluir(TipoServico tipoServico) throws SQLException {

		if (tipoServico == null) {
			return false;
		} else {
			TipoServicoDAO.getInstance().excluir(tipoServico);
			return true;
		}
	}
// Fim do m�todo

	private TipoServicoController() {
	}

// Instanciamento
	public static TipoServicoController getInstance() {
		if (instance == null)
			instance = new TipoServicoController();
		return instance;
	}
// Fim do m�todo

// Interface que prove acesso aos dados dos tipos de servi�os cadastrados
	public ResultSet mostrarTipoServicoCadastrados(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().mostrarTipoServicoCadastrados(servico);
	}
// Fim do m�todo

// Interface que prove acesso a pesquisa por nome
	public ResultSet pesquisarPorNome(TipoServico servico) throws SQLException {
		return TipoServicoDAO.getInstance().pesquisarPorNome(servico);
	}
// Fim do m�todo

}

// Fim da classe
