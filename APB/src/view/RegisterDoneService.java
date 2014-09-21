
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

import control.ProvidedServiceController;
import model.GivenService;
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
		
		setTitle("Servi\u00E7os Prestados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
			 * Intantiated to get access to the methods 
			 *   'mostrarServicosPrestadosCadastrados' e 'ConverterDataParaABNT' 
			 */
			ProvidedServiceController serviceController = ProvidedServiceController.getInstance();
			
			/* 
			 * Used to pass as argument to the method 'mostarServicosPrestadosCadastrados' 
			 * Useless argument on this method.
			 * Check methods from ServicoPrestadoController and ServicoPrestadoDAO.
			 */
			GivenService service = new GivenService();
			
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
				dataFromQuery[3] = service.convertServiceDateToABNT(queryForDoneServicesResult.getString("data") );
				tableModel.addRow(dataFromQuery);
			}
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage() );
		}
		catch (ParseException e)
		{
			showErrorMessage(e.getMessage() );
		}

		scrollPane.setViewportView(table);
		
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
		
		// Button that open a window to search for a done service
		JButton btnSearchForDoneServices = new JButton("Pesquisar");
		btnSearchForDoneServices.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				// Frame used to open a window to search for a done service
				PesquisarServicoPrestado searchForDoneServiceFrame = new PesquisarServicoPrestado();
				searchForDoneServiceFrame.setVisible(true);
				searchForDoneServiceFrame.setLocationRelativeTo(null);
				
				dispose();
			}
		}
		);
		btnSearchForDoneServices.setBounds(380, 58, 94, 23);
		contentPane.add(btnSearchForDoneServices);
		
		
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
					
					doneServiceToRemove = (String) table.getValueAt(table.getSelectedRow(), 0);
					
					/* 
					 * String that receive the value from the second column ('barbeiro')
					 *  of a selected row
					 * 
					 */
					String barberNameToRemove = "";
					
					barberNameToRemove = (String) table.getValueAt(table.getSelectedRow(), 1);
					
					/*
					 * String that receive the value from the third column ('valor')
					 *   of a selected row
					 */
					String doneServiceValueToRemove = "";
					
					doneServiceValueToRemove = (String) table
											   .getValueAt(table.getSelectedRow(), 2);
					
					/*
					 * String that receive the value from the fourth column ('data') 
					 *   of a selected row
					 */
					String doneServiceDateToRemove = "";
					
					doneServiceDateToRemove = (String) table
											  .getValueAt(table.getSelectedRow(), 3);
					
					//  Receives the data that will be deleted from DB
					GivenService serviceToBeDeleted = new GivenService();
					
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
						// Instantiated to get access to the method 'excluir' 
						ProvidedServiceController serviceController;
						
						serviceController = ProvidedServiceController.getInstance();
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
					showErrorMessage(e.getMessage() );
				}
				catch (SQLException e)
				{
					showErrorMessage(e.getMessage() );
				}
				catch (ParseException e)
				{
					showErrorMessage(e.getMessage() );
				}
			}
		}
		);
		btnDeleteDoneService.setBounds(380, 92, 94, 23);
		contentPane.add(btnDeleteDoneService);

		JButton btnBack = new JButton("Voltar");
		btnBack.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				
				// Frame used to "go back" to the main menu
				MenuPrincipal mainMenuFrame = new MenuPrincipal();
				mainMenuFrame.setVisible(true);
				mainMenuFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnBack.setBounds(380, 228, 94, 23);
		contentPane.add(btnBack);
	}

	/* 
	 * Method that shows the error message when a exception is triggered
	 * Parameter: exceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}
	
}
