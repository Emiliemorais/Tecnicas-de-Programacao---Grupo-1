//Pacote ao qual pertence a classe
package control;

// Importações
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.BarbeiroDAO;
import model.Barbeiro;
// Fim das importações

// Início da classe
public class BarbeiroController {

	private static BarbeiroController instance;

	private BarbeiroController() {}

// Instanciamento
	public static BarbeiroController getInstance() {
		if (instance == null)
			instance = new BarbeiroController();
		return instance;
	}

// Método para inserir um barbeiro
	public boolean inserir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		BarbeiroDAO.getInstance().incluir(barbeiro);
		return true;
	}
// Fim do método

// Método para alterar um barbeiro
	public boolean alterar(String nome, Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		Barbeiro barbeiro_alterado = barbeiro;
		BarbeiroDAO.getInstance().alterar(nome, barbeiro_alterado, barbeiro);
		return true;
	}
// Fim do método

// Método para excluir um barbeiro
	public boolean excluir(Barbeiro barbeiro) throws SQLException {
		if (barbeiro == null)
			return false;

		BarbeiroDAO.getInstance().excluir(barbeiro);
		return true;
	}
// Fim do método

// Interface que prove acesso a pesquisa
	public ResultSet pesquisar() throws SQLException {
		return BarbeiroDAO.getInstance().pesquisar();
	}
// Fim da interface que prove acesso a pesquisa	dos barbeiros

// Interface que prove acesso aos barbeiros cadastrados
	public ResultSet mostrarBarbeirosCadastrados(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().mostrarBarbeirosCadastrados(barbeiro);
	}
// Fim da interface que prove acesso aos barbeiros cadastrados

// Interface que prove acesso a pesquisa dos barbeiros por nome
	public ResultSet pesquisarPorNome(Barbeiro barbeiro) throws SQLException {
		return BarbeiroDAO.getInstance().pesquisarPorNome(barbeiro);
	}
// Fim da interface que prove acesso a pesquisa dos barbeiros por nome

}
// Fim da classe
