
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
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
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;

import model.Phonebook;
import control.PhonebookController;

@SuppressWarnings("serial")
public class RegisterPhonebook extends JFrame
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
					RegisterPhonebook registerPhonebookFrame = new RegisterPhonebook();
					registerPhonebookFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	public RegisterPhonebook()
	{
		// These methods are used to initialize the components  
		initializeFrame();
		initializePanel();

	}
	
	// This method is used to initialize the main frame of the application
	private void initializeFrame() 
	{
		setTitle("Agenda de Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		
	}
	
	// This method is used to initialize the panel to insert the components
	private void initializePanel() 
	{

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		createTableInFrame(contentPane);
		initializeButtons(contentPane);
		
	}
	

	/**
	 *  This method is used to create a table that contains the contacts
	 * @param contentPane - Panel that contains the components
	 */
	private void createTableInFrame(JPanel contentPane) 
	{

		// Add a scroll pane to the frame
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 435, 401);
		contentPane.add(scrollPane);

		final DefaultTableModel tableModel;
		
		tableModel = new DefaultTableModel(null,
										   new String[] { "Nome", "Telefone",
														 "Descri\u00E7\u00E3o" })
        {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column)
			{
				return columnEditables[column];
			}
		};
		
		// Add a table with 3 fields ('Nome, telefone e descricao') to the frame
		final JTable table = new JTable(tableModel);
		
		TableColumn firstColumn = table.getColumnModel().getColumn(0);
		TableColumn secondColumn = table.getColumnModel().getColumn(1);
		TableColumn thirdColumn = table.getColumnModel().getColumn(2);
		
		firstColumn.setResizable(false);
		secondColumn.setResizable(false);
		thirdColumn.setResizable(false);
		scrollPane.setViewportView(table);
						
		try
		{	
			// Instantiated to get access to the method 'showRegisteredContacts()'
			PhonebookController phonebookController = PhonebookController.getInstance();
			
			// Used to pass as argument to the method 'showRegisteredContacts()' 
			Phonebook contact = new Phonebook();
			
			// Used to receive the result from the method 'mostrarContatosCadastrados'
			// Useless argument on this method. Check methods from AgendaController and AgendaDAO
			ResultSet queryForContactsResult = phonebookController.registeredshowContacts(contact);
			
			while ( queryForContactsResult.next() )
            {
				/* 
				 * Array used to receive the data from every column (from queryForContactsResult)
				 *   and then add on the rows.
				 */
				String[] dataFromQuery = new String[3];
				dataFromQuery[0] = queryForContactsResult.getString("nome");
				dataFromQuery[1] = queryForContactsResult.getString("telefone");
				dataFromQuery[2] = queryForContactsResult.getString("descricao");
				tableModel.addRow(dataFromQuery);
			}
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage() );
		}

		scrollPane.setViewportView(table);
		
	}

	/**
	 *  This method is used to initialize the buttons
	 * @param contentPane - Panel that contains the components
	 */
	private void initializeButtons(JPanel contentPane) 
	{
		
		createButtonToRegisterContact(contentPane);
		createButtonToSearchContact(contentPane);				
		createButtonToOpenAdministrativeFrame(contentPane);
		
	}
	
	/**
	 * This method is used to create the button and the action that open the administrative frame
	 * @param contentPane - Panel that contains the components
	 */
	private void createButtonToOpenAdministrativeFrame(JPanel contentPane) 
	{		
		JButton btnBack = new JButton("Voltar");
		btnBack.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				
				// Frame used to "go back" to the administrative menu
				Administrative administrativeFrame = new Administrative();
				administrativeFrame.setVisible(true);
				administrativeFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnBack.setBounds(455, 399, 94, 23);
		contentPane.add(btnBack);
		
	}
	
	/**
	 *  This method is used to create the button and the action that
	 *  open the frame that register a new contact
	 *  @param contentPane - Panel that contains the components
	 */
	private void createButtonToRegisterContact(JPanel contentPane) 
	{

		JButton btnNewContact = new JButton("Novo");
		btnNewContact.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				
				// Frame used to open a window to add a new contact 
				NewContact newContactFrame;
				
				try
				{
					newContactFrame = new NewContact();
					newContactFrame.setVisible(true);
					newContactFrame.setLocationRelativeTo(null);
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
			}
		}
		);
		btnNewContact.setBounds(455, 24, 94, 23);
		contentPane.add(btnNewContact);
		
	}
	
	/**  
	 * This method is used to create the button and the action that 
	 *  open the frame that search a contact
	 * @param contentPane - Panel that contains the components
	 */
	private void createButtonToSearchContact(JPanel contentPane) 
	{

		JButton btnSearchContact = new JButton("Pesquisar");
		btnSearchContact.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				dispose();
				SearchContact searchContactFrame = new SearchContact();
				searchContactFrame.setVisible(true);
				searchContactFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnSearchContact.setBounds(455, 58, 94, 23);
		contentPane.add(btnSearchContact);
		
	}

	/** 
	 * Method that shows the error message when a exception is triggered
	 * @param excceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
