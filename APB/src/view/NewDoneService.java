package view;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DoneService;
import control.DoneServiceController;
import dao.FactoryConnection;
import exception.ServiceException;

@SuppressWarnings("serial")
public class NewDoneService extends JFrame
{

	// Jpanel class's instance to create the panel of new given service
	private JPanel contentPane;
	
	// Creates a text field to the global use
	private JTextField textGlobal;

	public static void main(String[] args)
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{
				public void run()
				{
					try
					{
						// Used to create a frame of new given service
						NewDoneService doneServiceFrame = new NewDoneService();
						doneServiceFrame.setVisible(true);
					} 
					catch( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
		);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// Constructor
	public NewDoneService()
	{

		// Define the description of the panel
		setTitle("Criar nova presta\u00E7\u00E3o de servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 214);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Creates a label to service
		JLabel labelService = new JLabel("Servi\u00E7o:");
		labelService.setBounds(27, 25, 46, 14);
		contentPane.add(labelService);
		
		// Creates a label to barber's name
		JLabel labelBarber = new JLabel("Realizado por:");
		labelBarber.setBounds(27, 56, 92, 14);
		contentPane.add(labelBarber);
		
		// Creates a label to service price
		JLabel labelPrice = new JLabel("Pre\u00E7o (R$):");
		labelPrice.setBounds(27, 87, 71, 14);
		contentPane.add(labelPrice);
		
		// Add a text field to the panel
		textGlobal = new JTextField();
		textGlobal.setColumns(10);
		textGlobal.setBounds(129, 84, 114, 20);
		contentPane.add(textGlobal);
		
		// Creates the combo box with the barbers
		final JComboBox comboBoxBarbers = new JComboBox();
		comboBoxBarbers.setModel(new DefaultComboBoxModel(
								 new String[] { "Selecione um barbeiro" }));
		comboBoxBarbers.setBounds(129, 53, 289, 20);
		contentPane.add(comboBoxBarbers);
		
		// Creates the combo box with the services type
		final JComboBox comboBoxService = new JComboBox();
		comboBoxService.addItemListener(new ItemListener()
		{
			
			// Read from the database the informations from ComboBox
			public void itemStateChanged(ItemEvent arg0)
			{
				// Connection class's instance to connect with the database
				Connection connection;
				if( comboBoxService.getSelectedIndex() != 0 )
				{
					try
					{
						// Receives the service type to search on the database
						String[] serviceType = comboBoxService.getSelectedItem().toString().split(" - ");
						
						FactoryConnection factoryConnectionInstance =  FactoryConnection.getInstance();
						connection = factoryConnectionInstance.getConnection();
						
						// java.sql.PreparedStatement instance to query in the database
						java.sql.PreparedStatement preparedStatement;
						String sqlCodeToQueryServiceType = "SELECT preco FROM tipoServico WHERE nome = \""
								  						   + serviceType[1] + "\";";
						preparedStatement = connection.prepareStatement(sqlCodeToQueryServiceType);
						
						// ResultSet interface instance to query a price
						ResultSet queryForPrice = preparedStatement.executeQuery();
						queryForPrice.next();

						textGlobal.setText( queryForPrice.getString("preco") );
					} 
					catch( SQLException e )
					{
						showErrorMessage( e.getMessage() );
					}
				}
				else
				{
					// Nothing to do
				}
			}

		});
		comboBoxService.setModel(new DefaultComboBoxModel(
							     new String[] { "Selecione um tipo de servi\u00E7o" }));
		comboBoxService.setMaximumRowCount(4);
		comboBoxService.setBounds(129, 22, 289, 20);
		contentPane.add(comboBoxService);
		
		try 
		{
			// Receives the quantity of  service type
			int serviceTypeQuantity = 0;
			
			FactoryConnection factoryConnectionInstance =  FactoryConnection.getInstance();
			Connection connection = factoryConnectionInstance.getConnection();
			String sqlCodeToQueryBarberChair = "SELECT nome, cadeira FROM barbeiro ORDER BY cadeira;";
			java.sql.PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement(sqlCodeToQueryBarberChair);
			
			java.sql.PreparedStatement preparedStatement2; 
			preparedStatement2 = connection.prepareStatement("SELECT nome FROM tiposervico;");
			ResultSet queryForChair = preparedStatement.executeQuery();
			ResultSet queryForServiceType = preparedStatement2.executeQuery();
			
			// Lists the names of the barbers by chair
			while( queryForChair.next() ) 
			{
				// Receives the barber's name
				String barberName = queryForChair.getString("nome");
				
				// Receives the barber's chair
				String barberChair = queryForChair.getString("cadeira");
				comboBoxBarbers.addItem(barberChair + " - " + barberName);
			}
			
			// Lists the services type
			while( queryForServiceType.next() )
			{
				serviceTypeQuantity++;
				
				// Receives the service type
				String serviceType = queryForServiceType.getString("nome");
				comboBoxService.addItem(serviceTypeQuantity + " - " + serviceType);
			}

		} 
		catch(SQLException e)
		{
			showErrorMessage( e.getMessage() );
		}
		
		// Creates a button that saves the given service
		JButton saveButton = new JButton("Salvar");
		saveButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				
				try
				{
					
					if( comboBoxService.getSelectedIndex() == 0 )
					{
						JOptionPane.showMessageDialog(null, "Você deve selecionar um tipo de serviço.");
					}
					else if( comboBoxBarbers.getSelectedIndex() == 0 )
					{
						JOptionPane.showMessageDialog(null, "Você deve selecionar um barbeiro.");
					}
					else 
					{
						// Receives the date from the given service in the simple format
						String serviceDate;
						
						// Receives the date of the given service
						Date date = new Date();
						
						// SimpleDateFormat class's instance to convert the date
						SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
						serviceDate = simpleFormat.format(date);

						// Receives the service type
						String[] serviceType = comboBoxService.getSelectedItem().toString().split(" - ");
						
						// Receives the  barbers' name
						String[] barberName = comboBoxBarbers.getSelectedItem().toString().split(" - ");

						// DoneService class's instance to access the class
						DoneService doneService = new DoneService();

						doneService.setBarberName(barberName[1]);
						doneService.setServiceName(serviceType[1]);
						doneService.setPrice(textGlobal.getText());
						doneService.setDate(serviceDate);
						
						// Service Controller class's instance to control the data received
						DoneServiceController serviceController = DoneServiceController.getInstance();
						serviceController.insertProvidedService(doneService);

						JOptionPane.showMessageDialog(null, "Serviço criado com sucesso");

						comboBoxBarbers.setSelectedIndex(0);
						comboBoxService.setSelectedIndex(0);

						textGlobal.setText("");
					}
				} 
				catch (ServiceException e)
				{
					showErrorMessage( e.getMessage() );
				} 
				catch (SQLException e)
				{
					showErrorMessage( e.getMessage() );
				}
				catch (ParseException e)
				{
					showErrorMessage( e.getMessage() );
				}

			}
		});
		saveButton.setBounds(27, 129, 89, 23);
		contentPane.add(saveButton);
		
		// Creates a button that clears the fields
		JButton clearFieldButton = new JButton("Limpar Campos");
		clearFieldButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				
				textGlobal.setText("");
				comboBoxBarbers.setSelectedIndex(0);
				comboBoxService.setSelectedIndex(0);
			}
		});
		clearFieldButton.setBounds(152, 129, 148, 23);
		contentPane.add(clearFieldButton);
		
		// Creates a button that returns to the frame Register Given Service
		JButton returnButton = new JButton("Voltar");
		returnButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				dispose();
	
				// Used to show the frame of a register barber
				RegisterDoneService registerBarberFrame = new RegisterDoneService();
	
				registerBarberFrame.setVisible(true);
				registerBarberFrame.setLocationRelativeTo(null);
			}
		});
		returnButton.setBounds(329, 129, 89, 23);
		contentPane.add(returnButton);
	}

	
	/**
	 *  Method used to show an error message for exception treatment
	 *	@param errorMessage - Receives an error message
	 */
	private void showErrorMessage (String errorMessage)
	{
		JOptionPane.showMessageDialog(null, errorMessage, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
}
