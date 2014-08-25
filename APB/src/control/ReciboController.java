package control;

//Importando das bases
import java.sql.ResultSet;
import java.sql.SQLException;


//Importando classes da DAO
import dao.ReciboDAO;

// Classe publica ReciboController iniciada
public class ReciboController {

	private static ReciboController instance;
	
	public ReciboController(){}
	
	public static ReciboController getInstance(){
		if(instance == null){
			instance = new ReciboController();
		}
		//retorno do metodo ReciboController getInstance()
		return instance;
	}//fim do metodo
	
	public ResultSet pesquisarServicosDoBarbeiro(String barbeiro, String dataInicial, String dataFinal) throws SQLException{
		
		return ReciboDAO.getInstance().pesquisarServicosDoBarbeiro(barbeiro, dataInicial, dataFinal);
		// Retorno do metodo public ResultSet pesquisarServicosDoBarbeiro
		
	}//fim do metodo
	
}
//fim da classe
