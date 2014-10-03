package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Report;

public class ReportDAO 
{

	private static ReportDAO instance;
	
	private ReportDAO()
	{
		
	}

	// Method that takes an instance of a report
	public static ReportDAO getInstance ()
	{
		if(instance == null)
		{
			instance = new ReportDAO();
		}
		else
		{
			//Noting to do
		}
		return instance;
	}

    // Interface that provides access to registered reports and allows them to be searched by date
	public ResultSet searchByDate (Report relatorio) throws SQLException
	{
		String prepareStatementToMethod = "SELECT * FROM servicoprestado WHERE data BETWEEN '"
                						+relatorio.getInitialDate()+"' AND '"
                						+relatorio.getFinalDate()+"';";
		
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement(prepareStatementToMethod);
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date and by barber
	public ResultSet searchByDateAndBarber(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod = "SELECT * FROM servicoprestado WHERE data BETWEEN '"
												+relatorio.getInitialDate()+"' AND '"
												+relatorio.getFinalDate()+"' AND barbeiro = '"
												+relatorio.getBarberName()+"';";
		
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date and service
	public ResultSet searchByDateAndService(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod ="SELECT * FROM servicoprestado WHERE data BETWEEN '"
										+relatorio.getInitialDate()+"' AND '"
										+relatorio.getFinalDate()+"' AND nome = '"
										+relatorio.getServiceType()+"';";
		
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by Barber
	public ResultSet searchByBarber(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod ="SELECT * FROM servicoprestado WHERE barbeiro = '"
										+relatorio.getBarberName()+"';";
				
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by Barber and service
	public ResultSet searchByBarberAndService(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod ="SELECT * FROM servicoprestado WHERE barbeiro = '"
										+relatorio.getBarberName()+"' AND nome = '"	
										+relatorio.getServiceType()+"';";
				
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by service
	public ResultSet searchByService(Report relatorio)throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod ="SELECT * FROM servicoprestado WHERE nome = '"
										+relatorio.getServiceType()+"';";
				
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date, barber and service
	public ResultSet searchByDateBarberAndService(Report relatorio)throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		
		String prepareStatementMethod = "SELECT * FROM servicoprestado WHERE data BETWEEN '"
										+relatorio.getInitialDate()+"' AND '"
										+relatorio.getFinalDate()+"' AND barbeiro = '"
										+relatorio.getBarberName()+"' AND nome = '"
										+relatorio.getServiceType()+"';";
		
		PreparedStatement pst = connection.prepareStatement(prepareStatementMethod);
		
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}

}
