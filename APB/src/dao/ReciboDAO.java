package dao;

//Importando das bases
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Inicio da classe ReciboDAO
public class ReciboDAO 
{

	private static ReciboDAO instance;

	private ReciboDAO() 
	{
	}

	// Metodo que instacia e retorna a instancia com o valor ReciboDAO() caso a instancia seja = (NULL)  
	public static ReciboDAO getInstance() 
	{
		if (instance == null)
			instance = new ReciboDAO();
		return instance;
		// Retorna uma instancia para o metodo ReciboDAO getInstance()
	}

	// Metodo que cede direito para pesquisar por tipos de servico do barbeiro
	public ResultSet pesquisarServicosDoBarbeiro ( String barbeiro,
			String dataInicial, String dataFinal ) throws SQLException 
			{

		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection
				.prepareStatement ( "SELECT * FROM servicoprestado WHERE data BETWEEN '"
						+ dataInicial
						+ "' AND '"
						+ dataFinal
						+ "' AND barbeiro = '" + barbeiro + "';" );
		ResultSet rs = pst.executeQuery();

		return rs;
		//Retorna o valor da variavel rs para o metodo
	}

	//Metodo que modifica os valores armazenados
	public void updateQuery ( String message ) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(message);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
	}// Fim do metodo updateQuery

}// Fim da classe ReciboDAO 
