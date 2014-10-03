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
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
				                                            +relatorio.getInitialDate()+"' AND '"
				                                            +relatorio.getFinalDate()+"';");
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date and by barber
	public ResultSet searchByDateAndBarber(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date and service
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
        
    // Interface that provides access to registered reports and allows them to be searched by Barber
	public ResultSet searchByBarber(Report relatorio) throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by Barber and service
	public ResultSet searchByBarberAndService(Report relatorio) throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE barbeiro = '"
															+relatorio.getBarberName()+"' AND nome = '"	
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by service
	public ResultSet searchByService(Report relatorio)throws SQLException 
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
        
    // Interface that provides access to registered reports and allows them to be searched by date, barber and service
	public ResultSet searchByDateBarberAndService(Report relatorio)throws SQLException
	{
		Connection connection = FactoryConnection.getInstance().getConnection();
		PreparedStatement pst = connection.prepareStatement("SELECT * FROM servicoprestado WHERE data BETWEEN '"
															+relatorio.getInitialDate()+"' AND '"
															+relatorio.getFinalDate()+"' AND barbeiro = '"
															+relatorio.getBarberName()+"' AND nome = '"
															+relatorio.getServiceType()+"';");
		ResultSet instanceStatement = pst.executeQuery();
		
		return instanceStatement;
	}

}
