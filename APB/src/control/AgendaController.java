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
	
    // M�todo que recebe como par�metro o objeto agenda e inclui no banco de dados
	public boolean incluir (Phonebook agenda) throws SQLException 
	{
		if(agenda == null)
		{
			return false;
		}
		
		// Se o objeto agenda n�o for nulo o m�todo inclui no banco de dados
		else{
			PhonebookDAO.getInstance().includeDataToPhonebook(agenda);
			return true;
		}
	}
	
    // M�todo que recebe como par�metro o nome do contato a ser altera e o objeto agenda e altera no banco de dados
	public boolean alterar (String nome, Phonebook agenda) throws SQLException 
	{
		if(agenda == null)
		{
			return false;
		}
		
		// Se o objeto agenda n�o for nulo o m�todo altera no banco de dados
		else{
			Phonebook agenda_alterado = agenda;
			PhonebookDAO.getInstance().editPhonebookData(nome, agenda_alterado, agenda);
			return true;		
		}
	}
        
        // M�todo que recebe como par�metro o objeto agenda e exclui do banco de dados
	public  boolean excluir (Phonebook contato) throws SQLException 
	{
		if(contato == null)
		{
			return false;
		}
		

		// Se o objeto agenda n�o for nulo o m�todo exclui no banco de dados
		else
		{
			PhonebookDAO.getInstance().deletePhonebookData(contato);
			return true;
		}

	}
	
	// M�todo que pega a inst�ncia de uma Agenda
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
	
        // Interface que prov� acesso aos contatos cadastrados e mostra todos os contatos
	public ResultSet mostrarContatosCadastrados(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().showRegisteredContacts(contato);
	}
	
        // Interface que prov� acesso aos contatos cadastrados e permite que sejam pesquisados pelo nome
	public ResultSet pesquisarPorNome(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().searchByName(contato);
	}
	
        // Interface que prov� acesso aos contatos cadastrados e permite que sejam pesquisados pelo telefone
	public ResultSet pesquisarPorTelefone(Phonebook contato) throws SQLException 
	{
		return PhonebookDAO.getInstance().searchByPhone(contato);
	}

}
