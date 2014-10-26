
package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import control.DoneServiceController;
import control.PhonebookController;
import model.DoneService;
import model.Phonebook;
import exception.ServiceException;

@SuppressWarnings("serial")
public class RegisterDoneService extends JFrame
{
	private JPanel contentPane;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {
			public void run()
            {
				try
				{
					RegisterDoneService registerDoneServiceFrame = new RegisterDoneService();
					registerDoneServiceFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	public RegisterDoneService()
	{
		// These methods are used to initialize the components  
		initializeFrame();
		initializePanel();
		
	}
	// This method is used to initialize the main frame of the application
	private void initializeFrame() 
	{
		setTitle("Servi\u00E7os Prestados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
	}
	
	// This method is used to initialize the panel to insert the components
	private void initializePanel() 
	{

		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		createTableInFrame(contentPane);
		
	}
	

	/**
	 *  This method is used to create a table that contains the contacts
	 * @param contentPane - Panel that contains the components
	 */
	private void createTableInFrame(JPanel contentPane) 
	{
		// Add a scroll pane to the frame
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 360, 240);
		contentPane.add(scrollPane);
		
		// Define a default table model with 4 columns
		final DefaultTableModel tableModel;
		
		tableModel = new DefaultTableModel(null,
										   new String[] { "Serviço",
														 "Realizado por",
														 "Valor",
														 "Data" });
		
		// Add a table with 4 fields ('Servico, realizado por, valor e data') to the frame
		final JTable table = new JTable(tableModel);

		try
		{
			/* 
			 * Instantiated to get access to the methods 
			 *   'displayRegisteredProvidedServices' e 'ConverterDataParaABNT' 
			 */
			DoneServiceController serviceController = DoneServiceController.getInstance();
			
			/* 
			 * Used to pass as argument to the method 'mostarServicosPrestadosCadastrados' 
			 * Useless argument on this method.
			 * Check methods from ServicoPrestadoController and ServicoPrestadoDAO.
			 */
			DoneService service = new DoneService();
			
			// Used to receive the result from the method 'mostrarServicosPrestadosCadastrados'
			ResultSet queryForDoneServicesResult = serviceController
											      .displayRegisteredProvidedServices(service);
			
			while ( queryForDoneServicesResult.next() )
            {
				/* 
				 * Array used to receive the data from every column of queryDoneServicesResult
				 *   and then add on the rows.
				 */
				String[] dataFromQuery = new String[4];
				
				dataFromQuery[0] = queryForDoneServicesResult.getString("nome");
				dataFromQuery[1] = queryForDoneServicesResult.getString("barbeiro");
				dataFromQuery[2] = queryForDoneServicesResult.getString("preco");
				
				String dateFromQuery = queryForDoneServicesResult.getString("data");
				
				dataFromQuery[3] = service.convertServiceDateToABNT(dateFromQuery);
				tableModel.addRow(dataFromQuery);
			}
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage());
		}
		catch (ParseException e)
		{
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);
		initializeButtons(contentPane, table);
	}

	/**
	 *  This method is used to initialize the buttons
	 * @param contentPane - Panel that contains the components
	 */
	private void initializeButtons(JPanel contentPane, JTable table) 
	{
		
		createButtonToRegisterContact(contentPane);
		createButtonToSearchContact(contentPane);				
		createButtonToOpenTheMainFrame(contentPane);
		createButtonToDeleteService(contentPane, table);
		
	}
	
	/**
	 * This method is used to create the button and the action that open the main frame
	 * @param contentPane - Panel that contains the components
	 */
	private void createButtonToOpenTheMainFrame(JPanel contentPane) 
	{		
		JButton btnBack = new JButton("Voltar");
		btnBack.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				
				// Frame used to "go back" to the main menu
				MainMenu mainMenuFrame = new MainMenu();
				mainMenuFrame.setVisible(true);
				mainMenuFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnBack.setBounds(380, 228, 94, 23);
		contentPane.add(btnBack);
		
	}
	
	/**
	 *  This method is used to create the button and the action that
	 *  open the frame that register a new done service
	 *  @param contentPane - Panel that contains the components
	 */
	private void createButtonToRegisterContact(JPanel contentPane) 
	{
		// Button that open a window to register a new done service
		JButton btnNewDoneService = new JButton("Novo");
		btnNewDoneService.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				
				// Frame used to open a window to add a new done service
				NewDoneService newDoneServiceFrame = new NewDoneService();
				newDoneServiceFrame.setVisible(true);
				newDoneServiceFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnNewDoneService.setBounds(380, 24, 94, 23);
		contentPane.add(btnNewDoneService);

	}
	
	/**  
	 * This method is used to create the button and the action that 
	 *  open the frame that search a done service
	 * @param contentPane - Panel that contains the components
	 */
	private void createButtonToSearchContact(JPanel contentPane) 
	{
		// Button that open a window to search for a done service
		JButton btnSearchForDoneServices = new JButton("Pesquisar");
		btnSearchForDoneServices.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				// Frame used to open a window to search for a done service
				SearchDoneService searchForDoneServiceFrame = new SearchDoneService();
				searchForDoneServiceFrame.setVisible(true);
				searchForDoneServiceFrame.setLocationRelativeTo(null);
				
				dispose();
			}
		}
		);
		btnSearchForDoneServices.setBounds(380, 58, 94, 23);
		contentPane.add(btnSearchForDoneServices);

	}
	
	/**  
	 * This method is used to create the button and the action that 
	 *  open the frame that delete a done service
	 * @param contentPane - Panel that contains the components
	 */
	private void createButtonToDeleteService(JPanel contentPane, final JTable table) 
	{
		JButton btnDeleteDoneService = new JButton("Remover");
		btnDeleteDoneService.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent arg0)
	        {
				try
				{
					/* 
					 * String that receive the value from the first column ('nome')
					 *   of a selected row
					 */
					String doneServiceToRemove = "";
					
					int selectedRowToColumn0 = table.getSelectedRow();
					
					doneServiceToRemove = (String) table.getValueAt(selectedRowToColumn0, 0);
					
					/* 
					 * String that receive the value from the second column ('barbeiro')
					 *  of a selected row
					 */
					String barberNameToRemove = "";
					
					int selectedRowToColumn1 = table.getSelectedRow();
					
					barberNameToRemove = (String) table.getValueAt(selectedRowToColumn1, 1);
					
					/*
					 * String that receive the value from the third column ('valor')
					 *   of a selected row
					 */
					String doneServiceValueToRemove = "";
					
					int selectedRowToColumn2 = table.getSelectedRow();
					
					doneServiceValueToRemove = (String) table
											   .getValueAt(selectedRowToColumn2, 2);
					
					/*
					 * String that receive the value from the fourth column ('data') 
					 *   of a selected row
					 */
					String doneServiceDateToRemove = "";
					
					int selectedRowToColumn3 = table.getSelectedRow();
					
					doneServiceDateToRemove = (String) table
											  .getValueAt(selectedRowToColumn3, 3);
					
					//  Receives the data that will be deleted from DB
					DoneService serviceToBeDeleted = new DoneService();
					
					// Passing the data to be deleted
					serviceToBeDeleted.setServiceName(doneServiceToRemove);
					serviceToBeDeleted.setBarberName(barberNameToRemove);
					serviceToBeDeleted.setPrice(doneServiceValueToRemove);
					serviceToBeDeleted.setDate(doneServiceDateToRemove);
					
					// Receive the confirmation from user to delete the selected row
					int deleteConfirmation = JOptionPane
											 .showConfirmDialog(null,
													 			"Remover " 
													 			+ doneServiceToRemove
													 			+ " da lista?");
	
					if( deleteConfirmation == JOptionPane.YES_OPTION )
	                {
						// Instantiated to get access to the method 'deleteProvidedService' 
						DoneServiceController serviceController;
						
						serviceController = DoneServiceController.getInstance();
						serviceController.deleteProvidedService(serviceToBeDeleted);
	
						dispose();
						
						// Frame used to "go back" to the done services options
						RegisterDoneService registerDoneServiceFrame = new RegisterDoneService();
						registerDoneServiceFrame.setVisible(true);
						registerDoneServiceFrame.setLocationRelativeTo(null);
					}
					else
	                {
	                    // Nothing to do
	                }
				}
				catch (ArrayIndexOutOfBoundsException e)
				{
					showErrorMessage("Selecione um Serviço para remover");
				}
				catch (ServiceException e)
				{
					showErrorMessage(e.getMessage());
				}
				catch (SQLException e)
				{
					showErrorMessage(e.getMessage());
				}
				catch (ParseException e)
				{
					showErrorMessage(e.getMessage());
				}
			}
		}
		);
		btnDeleteDoneService.setBounds(380, 92, 94, 23);
		contentPane.add(btnDeleteDoneService);
	}
	/** 
	 * Method that shows the error message when a exception is triggered
	 * @param exceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}
	
}
