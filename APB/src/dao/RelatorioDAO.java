package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Report;

public class RelatorioDAO 
{

	private static RelatorioDAO instance;
	
	private RelatorioDAO()
	{
		
	}

	// Método que pega a instância de um relatório
	public static RelatorioDAO getInstance ()
	{
		if (instance == null)
		{
			instance = new RelatorioDAO();
		}
		else
		{
			
		}
		return instance;
	}

    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data
	public ResultSet searchByDate (Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				                                            +relatorio.getInitialDate()+"' AND '"
				                                            +relatorio.getFinalDate()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data e por barbeiro 
	public ResultSet searchByDateAndBarber(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data e serviço
	public ResultSet searchByDateAndService(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro
	public ResultSet searchByBarber(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro e servico
	public ResultSet searchByBarberAndService(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"' AND nome = '"	
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por servico
	public ResultSet searchByService(Report relatorio)throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data, barbeiro e serviço
	public ResultSet searchByDateBarberAndService(Report relatorio)throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND barbeiro = '"
															+relatorio.getBarberName()+"' AND nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
