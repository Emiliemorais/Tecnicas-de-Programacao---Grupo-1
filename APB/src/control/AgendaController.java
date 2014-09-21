package control;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.PhonebookDAO;
import model.Phonebook;


public class AgendaController 
{
	
	private static AgendaController instance;
	
	private AgendaController() 
	{
		
	}
	
    // Método que recebe como parâmetro o objeto agenda e inclui no banco de dados
	public boolean incluir (Phonebook agenda) throws SQLException 
	{
		if(agenda == null)
		{
			return false;
		}
		
		// Se o objeto agenda não for nulo o método inclui no banco de dados
		else{
			PhonebookDAO.getInstance().includeDataToPhonebook(agenda);
			return true;
		}
	}
	
    // Método que recebe como parâmetro o nome do contato a ser altera e o objeto agenda e altera no banco de dados
	public boolean alterar (String nome, Phonebook agenda) throws SQLException 
	{
		if(agenda == null)
		{
			return false;
		}
		
		// Se o objeto agenda não for nulo o método altera no banco de dados
		else{
			Phonebook agenda_alterado = agenda;
			PhonebookDAO.getInstance().editPhonebookData(nome, agenda_alterado, agenda);
			return true;		
		}
	}
        
        // Método que recebe como parâmetro o objeto agenda e exclui do banco de dados
	public  boolean excluir (Phonebook contato) throws SQLException 
	{
		if(contato == null)
		{
			return false;
		}
		

		// Se o objeto agenda não for nulo o método exclui no banco de dados
		else
		{
			PhonebookDAO.getInstance().deletePhonebookData(contato);
			return true;
		}

	}
	
	// Método que pega a instância de uma Agenda
	public static AgendaController getInstance () 
	{
		if(instance == null)
		{
			instance = new AgendaController();
		}
		else
		{
			// Nothing to do
		}
		return instance;
	}
	
        // Interface que provê acesso aos contatos cadastrados e mostra todos os contatos
	public ResultSet mostrarContatosCadastrados(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().showRegisteredContacts(contato);
	}
	
        // Interface que provê acesso aos contatos cadastrados e permite que sejam pesquisados pelo nome
	public ResultSet pesquisarPorNome(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().searchByName(contato);
	}
	
        // Interface que provê acesso aos contatos cadastrados e permite que sejam pesquisados pelo telefone
	public ResultSet pesquisarPorTelefone(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().searchByPhone(contato);
	}

}
