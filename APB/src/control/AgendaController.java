package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AgendaDAO;
import model.Agenda;


public class AgendaController {
	
	private static AgendaController instance;
	
	private AgendaController() {}
	
        // Método que recebe como parâmetro o objeto agenda e inclui no banco de dados
	public boolean incluir(Agenda agenda) throws SQLException {
		if(agenda == null )
			return false;
		
		AgendaDAO.getInstance().incluir(agenda);
		return true;
	}
	
        // Método que recebe como parâmetro o nome do contato a ser altera e o objeto agenda e altera no banco de dados
	public boolean alterar(String nome,Agenda agenda) throws SQLException {
		if(agenda == null )
			return false;
		
		Agenda agenda_alterado = agenda;
		AgendaDAO.getInstance().alterar(nome ,agenda_alterado, agenda);
		return true;		
	}
        
        // Método que recebe como parâmetro o objeto agenda e exclui do banco de dados
	public  boolean excluir(Agenda contato) throws SQLException {
		if (contato == null)
			return false;
		
		AgendaDAO.getInstance().excluir(contato);
		return true;
	}
	
	public static AgendaController getInstance() {
		if(instance == null)
			instance = new AgendaController();
		return instance;
	}
	
        // Interface que provê acesso aos contatos cadastrados e mostra todos os contatos
	public ResultSet mostrarContatosCadastrados(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().mostrarContatosCadastrados(contato);
	}
	
        // Interface que provê acesso aos contatos cadastrados e permite que sejam pesquisados pelo nome
	public ResultSet pesquisarPorNome(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().pesquisarPorNome(contato);
	}
	
        // Interface que provê acesso aos contatos cadastrados e permite que sejam pesquisados pelo telefone
	public ResultSet pesquisarPorTelefone(Agenda contato) throws SQLException {
		return AgendaDAO.getInstance().pesquisarPorTelefone(contato);
	}

}
