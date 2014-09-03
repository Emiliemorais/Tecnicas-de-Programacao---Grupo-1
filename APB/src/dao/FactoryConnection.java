// Pacote ao qual pertence a classe
package dao;

// Importando
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// Fim da importa��o

// Inicio da classe
public class FactoryConnection
{
	static String statusConnection = "";

	private String local = "jdbc:mysql://localhost/apb";
	private String user = "root";
	private String password = "root";

	private static FactoryConnection instance;

	private FactoryConnection()
	{
        // Blank
	}

// Instanciamento
	public static FactoryConnection getInstance()
	{
		if(instance == null)
		{
            instance = new FactoryConnection();
		}
		else
        {
            // Nothing to do
        }
		return instance;
	}
// Fim do m�todo

// M�todo que est� recebendo os dados
	public Connection getConnection() throws SQLException
	{
		Connection connection = null;
		connection = DriverManager.getConnection(local, user, password);
		return connection;
	}
// Fim do m�todo

}
// Fim da classe
