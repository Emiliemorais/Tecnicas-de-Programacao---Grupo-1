package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import control.BarberController;
import control.ReceiptController;

import com.javadocx.CreateDocx;

@SuppressWarnings("serial")
public class GenerateReceipt extends JFrame
{

	// Constants to write on the receipt informations about the barber shop
	private static String COMPANY_NAME = "BARBEARIA DO ONOFRE LTDA - ME";
	private static String RECEIPT_PAYMENT = "RECIBO PAGAMENTO ALUGUEL BENS MÓVEIS";
	private static String LINE = "____________________________________________________________";
	private static String DATE_AND_LOCATION = "                    Brasília - DF  ____/____/________";

	// Jpanel class's instance to create the panel of generate receipt
	private JPanel contentPane;
	
	// Constant that writes on the receipt the Initial Date
	private JTextField textFieldInitialDate;
	
	// Constant that writes on the receipt the end Date
	private JTextField textFieldFinalDate;
	
	// Constant that writes on the receipt the total price
	private double totalAmount = 0;

	// Constant that help to write on the receipt
	private String price;

	/**
	 * Launch the application.
	 */

	/**
	 *  Method used to convert the date to ABNT format
	 *	@param date - Receives the date to convert
	 */
	public String convertDateForABNT (String date) throws ParseException
	{
		
		// SimpleDateFormat class's instance to convert the date
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		// Receives the date in the simple format
		Date isoDate = simpleFormat.parse(date);

		SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		
		// Receives the ISO date in the simple format
		String brazilDate = simpleFormat2.format(isoDate);

		return brazilDate;
	}

	/**
	 *  Method used to convert the date to ABNT format without the slash
	 *	@param date - Receives the date to convert
	 */
	public String convertDateForABNTWithoutSlash(String date) throws ParseException 
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date isoDate = simpleFormat.parse(date);

		SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		String brazilDate = simpleFormat2.format(isoDate);

		return brazilDate;
	}

	/**
	 *  Method used to convert the date to ISO
	 *	@param date - Receives the date to convert
	 */
	private String convertDateForISO (String date) throws ParseException
	{
		SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date abntDate = simpleFormat.parse(date);

		SimpleDateFormat simpleFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		String isoDate = simpleFormat2.format(abntDate);

		return isoDate;
	}

	public static void main(String[] args)
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{
			
				// Method used to initialize the frame Generate Receipt
				public void run()
				{
					try 
					{
						// Used to create the frame generate receipt
						GenerateReceipt receiptFrame = new GenerateReceipt();
						receiptFrame.setVisible(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public GenerateReceipt() throws ParseException
	{
		setTitle("Gerar Recibo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 264);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Creates the combo box with the barbers
		final JComboBox comboBoxBarbers = new JComboBox();
				comboBoxBarbers.setModel(new DefaultComboBoxModel(
								 		 new String[] { "Selecione um barbeiro" }));
		comboBoxBarbers.setBounds(10, 32, 304, 26);
		contentPane.add(comboBoxBarbers);

		try
		{
			BarberController barberControllerInstance = BarberController.getInstance();
			
			// ResultSet interface instance to query a barber
			ResultSet queryForBarber = barberControllerInstance.searchBarbers();
			
			while ( queryForBarber.next() ) 
			{
				comboBoxBarbers.addItem(queryForBarber.getString("cadeira") + " - "
										+ queryForBarber.getString("nome"));
			}
		}
		catch (SQLException e)
		{
			showErrorMessage( e.getMessage() );
		}

		// MaskFormatter class's instance to format the string that contains the date
		final MaskFormatter maskFormatDate = new MaskFormatter("##/##/####");

		textFieldInitialDate = new JFormattedTextField(maskFormatDate);
		textFieldInitialDate.setBounds(10, 110, 124, 20);
		contentPane.add(textFieldInitialDate);
		textFieldInitialDate.setColumns(10);

		// Creates a label to Initial Date
		JLabel labelInitialDate = new JLabel("Data Inicial");
		labelInitialDate.setBounds(36, 89, 86, 14);
		contentPane.add(labelInitialDate);

		textFieldFinalDate = new JFormattedTextField(maskFormatDate);
		textFieldFinalDate.setBounds(190, 110, 124, 20);
		contentPane.add(textFieldFinalDate);
		textFieldFinalDate.setColumns(10);

		// Creates a label to Final Date
		JLabel labelFinalDate = new JLabel("Data Final");
		labelFinalDate.setBounds(215, 89, 86, 14);
		contentPane.add(labelFinalDate);

		// Creates a button for generate receipt
		JButton generateReceiptbutton = new JButton("Gerar Recibo");
		generateReceiptbutton.addMouseListener(new MouseAdapter() 
		{
			
			// Method used to generate a receipt in the format "docx"
			@Override
			public void mouseClicked(MouseEvent e)
			{
				ReceiptController reciboController = ReceiptController.getInstance();
				
				try
				{
					if( comboBoxBarbers.getSelectedIndex() != 0 ) 
					{
						// CreateDocx class's instance to create the receipt in the format "docx"
						CreateDocx docx = new CreateDocx("docx");

						// Receives the parameters to write in the receipt
						HashMap paramsHead = new HashMap();
						HashMap paramsTitle = new HashMap();
						HashMap paramsValue = new HashMap();
						HashMap paramsText = new HashMap();
						HashMap paramsSignatureLine = new HashMap();
						HashMap paramsText4 = new HashMap();
						HashMap paramsLineBreak = new HashMap();
						HashMap paramsBarberSignature = new HashMap();

						paramsHead.put("b", "single");
						paramsHead.put("jc", "center");
						paramsHead.put("font", "Arial");

						paramsTitle.put("b", "single");
						paramsTitle.put("jc", "center");
						paramsTitle.put("font", "Arial");

						paramsValue.put("b", "single");
						paramsValue.put("jc", "center");
						paramsValue.put("font", "Arial");

						paramsText.put("font", "Arial");
						paramsText.put("align", "justify");

						paramsSignatureLine.put("jc", "center");

						paramsText4.put("jc", "center");
						paramsText4.put("b", "single");

						paramsBarberSignature.put("jc", "center");
						paramsBarberSignature.put("b", "single");

						String initialDateToConverte = textFieldInitialDate.getText();
						// Constant that writes on the receipt the Initial Date
						final String isoInitialDate = convertDateForISO(initialDateToConverte);
						
						String finalDateToConverte = textFieldFinalDate.getText();
						// Constant that writes on the receipt the end Date
						final String isoFinalDate = convertDateForISO(finalDateToConverte);

						// Gets its barber's name from the combo box 
						String[] barberName = comboBoxBarbers.getSelectedItem().toString().split(" - ");

						
						
						// ResultSet interface instance to query a barber service
						ResultSet queryForBarberService = reciboController.barberServicesSearch(barberName[1],isoInitialDate, isoFinalDate);
						
						while( queryForBarberService.next() )
						{
							String priceBrazilFormat =  queryForBarberService.getString("preco");
							price = priceBrazilFormat.replace(",", ".");
													
							// Receives the price of the service
							double value = Double.parseDouble(price);
							totalAmount = totalAmount + (value / 2);
						}

						//DecimalFormat class's instance to change the format of the price
						DecimalFormat decimalFormat = new DecimalFormat("##0.00");

						// Receives the initial date from the jTextField
						String initialDate = textFieldInitialDate.getText();

						// Receives the end date from the jTextField
						String finalDate = textFieldFinalDate.getText();

						// Receives the total price
						String value = ( "VALOR R$ " + decimalFormat.format(totalAmount) );

						// Receives the text of the receipt
						String textReceipt = "                    Recebi do Sr. "
											+ barberName[1] + " a importância supra de R$ "
											+ (decimalFormat.format(totalAmount)) + ", "
											+ "referente ao Aluguel do período de "
											+ initialDate + " até " + finalDate
											+ ", conforme CONTRATO de locação "
											+ "de bens móveis, firmado entre as partes.";
						
						String textSignatureReceipt = "                    Por ser verdade assino o presente RECIBO para"
											          + " os fins de direitos, de acordo com a lei.";

						docx.addText(COMPANY_NAME, paramsHead);
						docx.addText(RECEIPT_PAYMENT, paramsTitle);
						docx.addText(value, paramsValue);
						docx.addBreak("line");
						docx.addText(textReceipt, paramsText);
						docx.addText(textSignatureReceipt, paramsText);
						docx.addText(DATE_AND_LOCATION, paramsText);
						docx.addBreak("line");
						docx.addText(LINE, paramsSignatureLine);
						docx.addText(COMPANY_NAME, paramsText4);
						docx.addBreak("line");
						docx.addText(LINE, paramsSignatureLine);
						docx.addText(barberName[1], paramsBarberSignature);

						docx.createDocx("Recibo " + barberName[1] + " "
										+ convertDateForABNTWithoutSlash(isoInitialDate)
										+ " - "
										+ convertDateForABNTWithoutSlash(isoFinalDate));

						GenerateReceipt receiptFrame = new GenerateReceipt();
						receiptFrame.setVisible(true);
						receiptFrame.setLocationRelativeTo(null);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Selecione o um barbeiro");
					}
				} 
				catch (SQLException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		generateReceiptbutton.setBounds(202, 175, 112, 35);
		contentPane.add(generateReceiptbutton);

		// Creates a button that returns to the Administrative
		JButton returnButton = new JButton("Voltar");
		returnButton.addMouseListener(new MouseAdapter()
		{
			
			// Method used to return the Administrative frame
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				dispose();
				
				// Used to create the frame generate receipt
				Administrative administrativeFrame = new Administrative();
				
				administrativeFrame.setVisible(true);
				administrativeFrame.setLocationRelativeTo(null);
			}
		});
		returnButton.setBounds(10, 175, 112, 35);
		contentPane.add(returnButton);
	}

	/**
	 *  Method used to show an error message for exception treatment
	 *	@param errorMessage - Receives an error message
	 */
	private void showErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(null, errorMessage, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
