package control;

// Importando das bases
import java.sql.ResultSet;
import java.sql.SQLException;

// Importando classes da DAO e da MODEL
import dao.RelatorioDAO;
import model.Report;

/*
 *  Classe criada como controle do Relatorio
 *  Ele e responssavel por fazer o tratamento de excecao das variaveis:
 *  pesquisarPorData; pesquisarPorDataEBarbeiro
 *  pesquisarPorDataEServico; pesquisarPorBarbeiro
 *  pesquisarPorBarbeiroEServico; pesquisarPorServico;
 *  pesquisarPorDataBarbeiroEServico; 
 *  Variaveis que vao entrar com valores para as pesquisas
 */
public class RelatorioController 
{


private static RelatorioController instance;

	public RelatorioController () {}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por data
	public ResultSet pesquisarPorData ( Report relatorio ) throws SQLException 
	{
		return RelatorioDAO.getInstance().pesquisarPorData ( relatorio );
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por data e por barbeiro
	public ResultSet pesquisarPorDataEBarbeiro ( Report relatorio ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().pesquisarPorDataEBarbeiro ( relatorio );
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por data e servico
	public ResultSet pesquisarPorDataEServico(Report relatorio) throws SQLException 
	{	
		return RelatorioDAO.getInstance().pesquisarPorDataEServico ( relatorio );
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por barbeiro
	public ResultSet pesquisarPorBarbeiro ( Report relatorio ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().pesquisarPorBarbeiro ( relatorio );
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por barbeiro e servico
	public ResultSet pesquisarPorBarbeiroEServico(Report relatorio) throws SQLException 
	{	
		return RelatorioDAO.getInstance().pesquisarPorBarbeiroEServico(relatorio);
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por servico
	public ResultSet pesquisarPorServico ( Report relatorio ) throws SQLException 
	{	
		return RelatorioDAO.getInstance().pesquisarPorServico ( relatorio );
	}
	
	// Metodo que cede o acesso aos relatorios cadastrados e da a opcao de que sejam pesquisados por bagageiro e servico
	public ResultSet pesquisarPorDataBarbeiroEServico ( Report relatorio ) throws SQLException 
	{		
		return RelatorioDAO.getInstance().pesquisarPorDataBarbeiroEServico ( relatorio );
	}
	
	public static RelatorioController getInstance () 
	{
		
		if(instance == null)
			instance = new RelatorioController ();
		return instance;
		// Retorno do metodo RelatorioController getInstance() como a instance
	}

}// Fim da classe
