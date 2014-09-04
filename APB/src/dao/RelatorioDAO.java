package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Relatorio;

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
	public ResultSet pesquisarPorData (Relatorio relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				                                            +relatorio.getDataInicial()+"' AND '"
				                                            +relatorio.getDataFinal()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data e por barbeiro 
	public ResultSet pesquisarPorDataEBarbeiro(Relatorio relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getDataInicial()+"' AND '"
															+relatorio.getDataFinal()+"' AND barbeiro = '"
															+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data e serviço
	public ResultSet pesquisarPorDataEServico(Relatorio relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getDataInicial()+"' AND '"
															+relatorio.getDataFinal()+"' AND nome = '"
															+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro
	public ResultSet pesquisarPorBarbeiro(Relatorio relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarbeiro()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por barbeiro e servico
	public ResultSet pesquisarPorBarbeiroEServico(Relatorio relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarbeiro()+"' AND nome = '"	
															+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por servico
	public ResultSet pesquisarPorServico(Relatorio relatorio)throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
															+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface que provê acesso aos relatorios cadastrados e permite que sejam pesquisados por data, barbeiro e serviço
	public ResultSet pesquisarPorDataBarbeiroEServico(Relatorio relatorio)throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getDataInicial()+"' AND '"
															+relatorio.getDataFinal()+"' AND barbeiro = '"
															+relatorio.getBarbeiro()+"' AND nome = '"
															+relatorio.getTipoServico()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}

}
