
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
		setTitle("Agenda de Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(table);

		try
		{	
			// Instantiated to get access to the method 'showRegisteredContacts()'
			PhonebookController phonebookController = PhonebookController.getInstance();
			
			// Used to pass as argument to the method 'showRegisteredContacts()' 
			Phonebook contact = new Phonebook();
			
			// Used to receive the result from the method 'mostrarContatosCadastrados'
			// Useless argument on this method. Check methods from AgendaController and AgendaDAO
			ResultSet queryForContactsResult = phonebookController.mostrarContatosCadastrados(contact);
			
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
		
		// Button that calls a window to register a new contact
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
		
		// Button that calls a window to look for contacts
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
		
		// Button that calls the class 'Administrative'
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


	/* 
	 * Method that shows the error message when a exception is triggered
	 * Parameter: excceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
