package view;

import java.awt.Checkbox;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import exception.ReportException;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class SearchReport extends JFrame
{
	// Receives the search type
	public static int searchType = 0;
	
	// Receives a text to service
	public static String service = "servico";
	
	// Receives a text to barber
	public static String barber = "barbeiro";
	
	// Receives the initial date
	public static String initialDate = "dataInicial";
	
	// Receives the final date
	public static String finalDate = "dataFinal";
	
	// Jpanel class's instance to create the panel of search report
	private JPanel contentPane;
	
	// Receives the initial date from the jTextField
	private JTextField textFieldInitialDate;

	// Receives the final date from the jTextField
	private JTextField textFieldFinalDate;
	
	// Receives a text to service from the jtextfield
	private JTextField textFieldBarber;
	
	// Receives a text to barber from the jtextfield
	private JTextField textFieldService;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{
			
				// Method that initialize the SearchReport frame
				public void run() 
				{
					try
					{
						// Used to create a frame of search report
						SearchReport searchReportFrame = new SearchReport();
						searchReportFrame.setVisible(true);
					} 
					catch (Exception e) 
					{
						showErrorMessage( e.getMessage() );
					}
				}
			}
		);
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	// Constructor
	public SearchReport() throws ParseException
	{
		setTitle("Tipo de Pesquisa do Relat\u00F3rio");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 300);
		contentPane = new JPanel();
		contentPane.setBorder( new LineBorder( new Color(0, 0, 0) ) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final MaskFormatter maskFormatDate = new MaskFormatter("##/##/####");

		// Used to create a panel of dates
		JPanel datePanel = new JPanel();
		datePanel.setBorder(new TitledBorder(UIManager
							.getBorder("TitledBorder.border"), "Por Data",
							TitledBorder.LEADING, TitledBorder.TOP, null, null));
		datePanel.setBounds(10, 11, 221, 97);
		contentPane.add(datePanel);
		datePanel.setLayout(null);

		textFieldInitialDate = new JFormattedTextField(maskFormatDate);
		textFieldInitialDate.setEnabled(false);
		textFieldInitialDate.setBounds(10, 66, 94, 20);
		datePanel.add(textFieldInitialDate);
		textFieldInitialDate.setColumns(10);

		textFieldFinalDate = new JFormattedTextField(maskFormatDate);
		textFieldFinalDate.setEnabled(false);
		textFieldFinalDate.setBounds(114, 66, 94, 20);
		datePanel.add(textFieldFinalDate);
		textFieldFinalDate.setColumns(10);

		// Creates a label to Initial Date
		JLabel labelInitialDate = new JLabel("Data Inicial");
		labelInitialDate.setBounds(10, 53, 86, 14);
		datePanel.add(labelInitialDate);

		// Used to create a checkbox of date
		final Checkbox checkboxDate = new Checkbox("Ativar");
		checkboxDate.addItemListener(new ItemListener() 
		{
						
			// Method that active the search by date
			public void itemStateChanged(ItemEvent e)
			{
				if (checkboxDate.getState() == false)
				{
					textFieldInitialDate.setEnabled(false);
					textFieldFinalDate.setEnabled(false);
				} 
				else 
				{
					textFieldInitialDate.setEnabled(true);
					textFieldFinalDate.setEnabled(true);
				}
			}
		});
		checkboxDate.setBounds(6, 23, 71, 23);
		datePanel.add(checkboxDate);

		// Creates a label to final Date
		JLabel labelFinalDate = new JLabel("Data Final");
		labelFinalDate.setBounds(114, 53, 71, 14);
		datePanel.add(labelFinalDate);

		// Used to create a panel of barbers
		JPanel barberPanel = new JPanel();
		barberPanel.setLayout(null);
		barberPanel.setBorder(new TitledBorder(UIManager
							  .getBorder("TitledBorder.border"), "Por Barbeiro",
							  TitledBorder.LEADING, TitledBorder.TOP, null, null));
		barberPanel.setBounds(10, 119, 221, 62);
		contentPane.add(barberPanel);

		textFieldBarber = new JTextField();
		textFieldBarber.setText("Nome do barbeiro");
		textFieldBarber.setEnabled(false);
		textFieldBarber.setColumns(10);
		textFieldBarber.setBounds(71, 23, 140, 20);
		barberPanel.add(textFieldBarber);

		// Used to create a checkbox of barber
		final Checkbox checkboxBarber = new Checkbox("Ativar");
		checkboxBarber.addItemListener(new ItemListener()
		{
			
			// Method that active the search by barber name
			public void itemStateChanged (ItemEvent e)
			{
				if (checkboxBarber.getState() == false)
				{
					textFieldBarber.setEnabled(false);
					textFieldBarber.setText("Nome do barbeiro");
				} 
				else 
				{
					textFieldBarber.setEnabled(true);
					textFieldBarber.setText("");
				}
			}
		});

		checkboxBarber.setBounds(6, 23, 59, 23);
		barberPanel.add(checkboxBarber);

		// Used to create a panel of services
		JPanel servicePanel = new JPanel();
		servicePanel.setLayout(null);
		servicePanel.setBorder(new TitledBorder(UIManager
							   .getBorder("TitledBorder.border"), "Por Servi\u00E7o",
							   TitledBorder.LEADING, TitledBorder.TOP, null, null));
		servicePanel.setBounds(10, 189, 221, 62);
		contentPane.add(servicePanel);

		textFieldService = new JTextField();
		textFieldService.setText("Tipo de Servi\u00E7o");
		textFieldService.setEnabled(false);
		textFieldService.setColumns(10);
		textFieldService.setBounds(71, 23, 140, 20);
		servicePanel.add(textFieldService);

		// Used to create a checkbox of services
		final Checkbox checkboxService = new Checkbox("Ativar");
		checkboxService.addItemListener(new ItemListener()
		{
			
			// Method that active the search by service type
			public void itemStateChanged(ItemEvent e)
			{
				if (checkboxService.getState() == false) 
				{
					textFieldService.setEnabled(false);
					textFieldService.setText("Tipo de Servi\u00E7o");
				} 
				else
				{
					textFieldService.setEnabled(true);
					textFieldService.setText("");
				}
			}
		});
		checkboxService.setBounds(6, 23, 59, 23);
		servicePanel.add(checkboxService);

		// Creates a button to complete the search
		JButton completeButton = new JButton("Concluir");
		completeButton.addMouseListener(new MouseAdapter()
		{
			
			// Method that receives the data digitized by user to the search of report
			@Override
			public void mouseClicked (MouseEvent arg0)
			{

				if (textFieldBarber.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Digite o nome do barbeiro.");
				} 
				else if (textFieldService.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Digite um tipo de servi�o.");
				} 
				else if (textFieldFinalDate.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Digite uma data final");
				} 
				else if (textFieldInitialDate.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Digite uma data inicial");
				} 
				else 
				{
					if (checkboxBarber.getState() == true
						&& checkboxDate.getState() == false
						&& checkboxService.getState() == false)
					{
						barber = textFieldBarber.getText();
						searchType = 1;
					}
					else
					{
						// Nothing to do
					}

					if (checkboxBarber.getState() == true 
						&& checkboxDate.getState() == false
						&& checkboxService.getState() == true) 
					{
						barber = textFieldBarber.getText();
						service = textFieldService.getText();
						searchType = 2;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkboxBarber.getState() == true
						&& checkboxDate.getState() == true
						&& checkboxService.getState() == false) 
					{
						barber = textFieldBarber.getText();
						initialDate = textFieldInitialDate.getText();
						finalDate = textFieldFinalDate.getText();
						searchType = 3;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkboxBarber.getState() == true
							&& checkboxDate.getState() == true
							&& checkboxService.getState() == true) 
					{
						barber = textFieldBarber.getText();
						initialDate = textFieldInitialDate.getText();
						finalDate = textFieldFinalDate.getText();
						service = textFieldService.getText();
						searchType = 4;
					}
					else
					{
						//Nothing to do
					}
					
					if (checkboxBarber.getState() == false
						&& checkboxDate.getState() == false
						&& checkboxService.getState() == true)
					{
						service = textFieldService.getText();
						searchType = 5;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkboxBarber.getState() == false
						&& checkboxDate.getState() == true
						&& checkboxService.getState() == true)
					{
						initialDate = textFieldInitialDate.getText();
						finalDate = textFieldFinalDate.getText();
						service = textFieldService.getText();
						searchType = 6;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkboxBarber.getState() == false
						&& checkboxDate.getState() == true
						&& checkboxService.getState() == false)
					{
						initialDate = textFieldInitialDate.getText();
						finalDate = textFieldFinalDate.getText();
						searchType = 7;
					}
					else
					{
						// Nothing to do
					}
					
				}

				if (checkboxBarber.getState() == false
					&& checkboxDate.getState() == false
					&& checkboxService.getState() == false)
				{
					JOptionPane.showMessageDialog(null, "Selecione uma op��o de busca");
				} 
				else
				{
					// Nothing to do
				}
				
				if (searchType != 0)
				{
					try 
					{
						// Used to instantiate a frame of view reports
						ViewReport viewReportFrame = new ViewReport();
						viewReportFrame.setVisible(true);
						viewReportFrame.setLocationRelativeTo(null);
						dispose();
					}
					catch (SQLException e)
					{
						showErrorMessage(e.getMessage());
					}
					catch (ReportException e) 
					{
						showErrorMessage(e.getMessage());
					}
					catch (NullPointerException e)
					{
						showErrorMessage(e.getMessage());
					}
					catch (ParseException e) 
					{
						showErrorMessage(e.getMessage());
					}
				}
				else
				{
					// Nothing to do
				}

			}
		});
		completeButton.setBounds(241, 11, 105, 62);
		contentPane.add(completeButton);

		// Creates a button to return  to view reports
		JButton returnButton = new JButton("Voltar");
		returnButton.addMouseListener(new MouseAdapter() 
		{
			
			// Method that shows the ViewReport frame
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					ViewReport viewReportFrame = new ViewReport();
					viewReportFrame.setVisible(true);
					viewReportFrame.setLocationRelativeTo(null);
					dispose();
				} 
				catch (SQLException e1)
				{
					showErrorMessage(e1.getMessage());
				} 
				catch (ReportException e1)
				{
					showErrorMessage(e1.getMessage());
				} 
				catch (NullPointerException e1)
				{
					showErrorMessage(e1.getMessage());
				} 
				catch (ParseException e1) 
				{
					showErrorMessage(e1.getMessage());
				}
			}
		});
		returnButton.setBounds(241, 228, 105, 23);
		contentPane.add(returnButton);
	}
	
	public boolean action(Event evento, Object arg)
	{
		
		return false;
	}
	
	/**
	 *  Method used to show an error message for exception treatment
	 *	@param errorMessage - Receives an error message
	 */
	private static void showErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(null, errorMessage, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
