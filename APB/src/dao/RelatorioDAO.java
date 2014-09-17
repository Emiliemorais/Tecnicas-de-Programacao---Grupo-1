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

	// M�todo que pega a inst�ncia de um relat�rio
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

    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por data
	public ResultSet pesquisarPorData (Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				                                            +relatorio.getInitialDate()+"' AND '"
				                                            +relatorio.getFinalDate()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por data e por barbeiro 
	public ResultSet pesquisarPorDataEBarbeiro(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por data e servi�o
	public ResultSet pesquisarPorDataEServico(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro
	public ResultSet pesquisarPorBarbeiro(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro e servico
	public ResultSet pesquisarPorBarbeiroEServico(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"' AND nome = '"	
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por servico
	public ResultSet pesquisarPorServico(Report relatorio)throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que prov� acesso aos relatorios cadastrados e permite que sejam pesquisados por data, barbeiro e servi�o
	public ResultSet pesquisarPorDataBarbeiroEServico(Report relatorio)throws SQLException
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
