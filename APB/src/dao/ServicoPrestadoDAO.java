// Pacote ao qual pertence a classe
package dao;

// Importando
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServicoPrestado;
// Fim da importação

// Início da classe
public class ServicoPrestadoDAO {

	private static ServicoPrestadoDAO instance;

	private ServicoPrestadoDAO() {

	}

// Instanciamento
	public static ServicoPrestadoDAO getInstance() {
		if (instance == null)
			instance = new ServicoPrestadoDAO();
		return instance;
	}
// Fim do método

// Método que inclui um serviço prestado
	public boolean incluir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			this.updateQuery("INSERT INTO "
					+ "servicoprestado (nome, preco, barbeiro, data) VALUES ("
					+ "\"" + servico.getNomeServico() + "\", " + "\""
					+ servico.getPreco() + "\", " + "\""
					+ servico.getNomeBarbeiro() + "\", " + "\""
					+ servico.getData() + "\"); ");
			return true;
		}

		return false;
	}
// Fim do método

// Método que exclui um serviço prestado
	public boolean excluir(ServicoPrestado servico) throws SQLException {
		if (servico != null) {
			this.updateQuery("DELETE FROM servicoprestado WHERE "
				+ "servicoprestado.idservicoprestado = \"" + pesquisar(servico)+ "\";");
			return true;
		}

		return false;
	}
// Fim do método

// Método que pesquisa por um serviço prestado
	private String pesquisar(ServicoPrestado servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("SELECT * FROM servicoprestado WHERE "
						+ "servicoprestado.nome = \""
						+ servico.getNomeServico()
						+ "\" AND servicoprestado.preco = \""
						+ servico.getPreco()
						+ "\" AND servicoprestado.barbeiro = \""
						+ servico.getNomeBarbeiro()
						+ "\" AND servicoprestado.data = \""
						+ servico.getData() + "\";");
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getString("idservicoprestado");
	}
// Fim do método

// Método que atualiza os dados incluídos
	private void updateQuery(String message) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}
// Fim do método

// Interface que provê acesso aos serviços prestados que constam no banco
	public ResultSet mostrarServicosPrestadosCadastrados(ServicoPrestado servico) throws SQLException {
		Connection connection = FactoryConnection.getInstance().getConnection();
		ResultSet rs = connection.createStatement().executeQuery(
						"SELECT nome, preco, barbeiro, data FROM servicoprestado ORDER BY data;");

		return rs;
	}
// Fim do método (interface)

}
// Fim da classe
