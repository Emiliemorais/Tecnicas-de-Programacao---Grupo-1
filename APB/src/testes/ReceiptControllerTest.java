package testes;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import model.Report;
import control.ReceiptController;
import exception.ReceiptException;
import exception.RelatorioException;

public class ReceiptControllerTest
{
	Report report = new Report();

	@Before
	// Initialize the attributes of 'report' (instance variable above)
	public void setUp() throws ReceiptException, ParseException
	{
        try
        {
            report.setBarberName("Fulano");
            report.setFinalDate("09/09/2013");
            report.setInitialDate("01/01/2013");
        }
        catch (RelatorioException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}


	@Test
	// Test if a instance previous declared is the current one
	public void getInstanceMethodTest()
	{
		// Used to test the method getInstance on a 'ReceiptController' instance
		ReceiptController receiptControllerToTest = ReceiptController.getInstance();
		
		assertEquals(ReceiptController.getInstance(), receiptControllerToTest);
	}

	@Test
	// Test if the method 'searchBarberServices' returns a receipt
	public void searchForBarberServicesMethodTest() throws SQLException
	{
		// Instantiated to get access to the method 'pesquisarServicosDoBarbeiro'
		ReceiptController receiptControllerToTest = new ReceiptController();
		
		ResultSet queryForReceiptResult = receiptControllerToTest
										  .barberServicesSearch(report.getBarberName(),
												  					   report.getInitialDate(),
												  					   report.getFinalDate());

		while( queryForReceiptResult.next() );
	}

}
