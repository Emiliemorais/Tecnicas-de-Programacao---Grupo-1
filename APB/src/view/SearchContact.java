package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.PhonebookController;
import exception.BarberException;
import model.Phonebook;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings ( "serial" )
public class SearchContact extends JFrame 
{
	// Instance of Jpanel
	private JPanel contentPane; 
	
	// Instance of JTextField
	private JTextField textField; 

	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater(new Runnable () 
		{
			public void run () 
			{	
				try 
				{
					SearchContact frame = new SearchContact(); 
					frame.setVisible ( true );
				} 
				catch ( Exception e ) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	// Class constructor
	public SearchContact() 
	{
		initializeFrame();
		initializePanel();
		createTextFields();
		createLabels();
		createTable();
	}
	
	// Method that initializes the frame
	public void initializeFrame ()
	{	
		setTitle( "Pesquisar Contato" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 450, 300 );
	}
	
	// This method is used to initialize the panel on the frame
	public void initializePanel ()
	{
		// contentPane - Instance of Jpanel
		contentPane = new JPanel (); 
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );
	}
	
	// This method is used to create a table that contains the contacts
	public void createTable ()
	{
		// scrollPane - Creates the scrollbars
		JScrollPane scrollPane = new JScrollPane (); 
		scrollPane.setBounds ( 10, 11, 414, 115);
		contentPane.add ( scrollPane );

		// defaultTableModel - Default Table Model Instance
		final DefaultTableModel defaultTableModel = new DefaultTableModel ( null,
				new String[] { "Nome", "Telefone", "Descrição" });
		
		final JTable table = new JTable ( defaultTableModel ); 

		table.getColumnModel().getColumn(0).setResizable ( false );
		table.getColumnModel().getColumn(1).setResizable ( false );
		table.getColumnModel().getColumn(2).setResizable ( false );
		scrollPane.setViewportView ( table );
		
		initializeButtons(table, defaultTableModel);
	}
	
	// This method is used to create the textFields on the frame
	public void createTextFields ()
	{
		// textField - Instance of JTextField 
		textField = new JTextField (); 
		textField.setBounds ( 82, 137, 342, 20);
		contentPane.add ( textField );
		textField.setColumns ( 10 );
	}
	
	// This method is used to create the labels on the frame
	public void createLabels()
	{
		// searchLabel - Label that says "Search"
		JLabel searchLabel = new JLabel ( "Pesquisar:" ); 
		searchLabel.setBounds ( 20, 137, 66, 14);
		contentPane.add ( searchLabel);
	}
	
	/**
	 *  This method is used to initialize the buttons on the frame
	 * @param table - Receives the table that show the contacts
	 * @param defaultTableModel - Receives the header of the table
	 */
	public void initializeButtons (final JTable table, final  DefaultTableModel defaultTableModel)
	{
		createButtonToSearchByName(table,defaultTableModel);	
		createButtonToSearchByPhone(table, defaultTableModel);
		createButtonToModifyContact(table, defaultTableModel);
		createButtonToRemoveContact(table);
		createButtonToOpenRegisterPhonebookFrame();
	}
	
	/**
	 *  This method is used to create the button and the
	 *  action to search a contact by name
	 *  @param table - Receives the table that show the contacts
	 *  @param defaultTableModel - Receives the header of the table
	 */
	public void createButtonToSearchByName (final JTable table, final  DefaultTableModel defaultTableModel)
	{
		
		// nameSearchButton - Button that offers the "Search name" option
		JButton nameSearchButton = new JButton ( "Pesquisar Nome");
		nameSearchButton.addMouseListener ( new MouseAdapter ()
		{
			// Method that allows the use of the mouse for navigation
			@Override
			public void mouseClicked ( MouseEvent arg0)
			{
				try 
				{
					getSearchFromUser(table,defaultTableModel, "Search By Name");
					
				} 
				catch ( SQLException e ) 
				{
					showErrorMessage ( e.getMessage () );
				} 
				catch ( BarberException e ) 
				{
					showErrorMessage ( e.getMessage () );
				}
			}
			
		});
		nameSearchButton.setBounds( 82, 168, 160, 23 );
		contentPane.add ( nameSearchButton );
	}
	
	/**
	 *  This method is used to create the button and the
	 *  action to search a contact by phone
	 * @param table - Receives the table that show the contacts
	 * @param defaultTableModel - Receives the header of the table
	 */
	public void createButtonToSearchByPhone (final JTable table,final  DefaultTableModel defaultTableModel)
	{
		// phoneSearchButton - Button that says "Phone Search"
		JButton phoneSearchButton = new JButton ( "Pesquisar Telefone" );
		phoneSearchButton.addMouseListener ( new MouseAdapter () 
		{
			
			@Override
			public void mouseClicked ( MouseEvent arg0 ) 
			{

				try 
				{
					getSearchFromUser(table,defaultTableModel, "Search By Phone");
				} 
				catch ( SQLException e ) 
				{
					showErrorMessage ( e.getMessage () );
				} 
				catch ( BarberException e ) 
				{
					showErrorMessage( e.getMessage() );
				}
			}
			
		});
		phoneSearchButton.setBounds ( 264, 168, 160, 23);
		contentPane.add ( phoneSearchButton );
	}
	
	/**
	 *  This method is used to create the button and the
	 *  action to modify a contact
	 * @param table - Receives the table that show the contacts
	 * @param defaultTableModel - Receives the header of the table
	 */
	public void createButtonToModifyContact(final JTable table,final  DefaultTableModel defaultTableModel)
	{
		// editButton - Button that says "Edit"
		JButton editButton = new JButton ( "Alterar" ); 
		editButton.addMouseListener ( new MouseAdapter () 
		{
	
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					
					Phonebook.setNameTemporary ( defaultTableModel.getValueAt (
							table.getSelectedRow (), 0).toString () );
					dispose ();
					ModifyContact frame = new ModifyContact();
					frame.setVisible ( true) ;
					frame.setLocationRelativeTo ( null );

				} 
				catch ( ArrayIndexOutOfBoundsException e1 ) 
				{
					showErrorMessage ( "Selecione um contato para alterar" );
				}
			}
			
		});
		editButton.setBounds ( 98, 228, 89, 23 );
		contentPane.add ( editButton );
	}
	
	/**
	 *  This method is used to create the button and the
	 *  action to remove a contact
	 * @param table - Receives the table that show the contacts
	 */
	public void createButtonToRemoveContact (final JTable table)
	{
		// removeButton - Button that says "Remove"
		JButton removeButton = new JButton ( "Remover" ); 
		removeButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{

				try 
				{
					getTheSelectedContact(table);

				} 
				
				catch ( ArrayIndexOutOfBoundsException e1 ) 
				{
					showErrorMessage ( "Selecione um contato para remover" );
				} 
				catch ( BarberException e1 ) 
				{
					showErrorMessage( e1.getMessage () );
				} 
				catch ( SQLException e1 ) 
				{
					showErrorMessage ( e1.getMessage () );
				}
			}

			
		});
		removeButton.setBounds ( 216, 228, 89, 23);
		contentPane.add ( removeButton );
	}

	/*
	 *  This method is used to create the button and the
	 *  action to open the register phonebook frame
	 */
	public void createButtonToOpenRegisterPhonebookFrame()
	{
		// returnButton - Button that says "Return"
		JButton returnButton = new JButton ( "Voltar" );
		returnButton.addMouseListener ( new MouseAdapter ()
		{
			
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				RegisterPhonebook frame = new RegisterPhonebook();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}
			
		});
		returnButton.setBounds ( 335, 228, 89, 23);
		contentPane.add ( returnButton );
	}

	 /**
	  * This method get the data researched by user
	  * @param table - Receives the table that shows the contacts
	  * @param defaultTableModel - Receives the header of the table
	  * @param searchType - Receives the description of the search type
	  * @throws BarberException
	  * @throws SQLException
	  */
	public void getSearchFromUser (final JTable table ,final DefaultTableModel defaultTableModel, 
								   String searchType) throws BarberException, SQLException
	{
		for (int i = 0; i < defaultTableModel.getRowCount (); i++) 
		{
			defaultTableModel.removeRow ( i );
		}

		// contact - Instance of "Agenda"
		Phonebook contact = new Phonebook (); 
		PhonebookController agendaController = PhonebookController.getInstance();
			
		if( searchType.equals("Search By Name") )
		{
			contact.setPhonebookName ( textField.getText () );

			// resultInstance - ResultSetInstance
			ResultSet resultInstance = agendaController.searchContactForName ( contact);
			while ( resultInstance.next ( ) ) 
			{
				// resultSetData - Receives the name, phone and description
				String[] resultSetData = new String[3];
				resultSetData[0] = resultInstance.getString ( "nome" );
				resultSetData[1] = resultInstance.getString ( "telefone" );
				resultSetData[2] = resultInstance.getString ( "descricao" );
				defaultTableModel.addRow(resultSetData);
			}
		}
		
		else
		{
			contact.setPhonebook ( textField.getText () );
			
			// resultInstance - ResultSetInstance
			ResultSet resultInstance = agendaController.searchForPhone ( contact );
			while ( resultInstance.next ( ) ) 
			{
				// resultSetData - Receives the name, phone and description
				String[] resultSetData = new String[3];
				resultSetData[0] = resultInstance.getString ( "nome" );
				resultSetData[1] = resultInstance.getString ( "telefone" );
				resultSetData[2] = resultInstance.getString ( "descricao" );
				defaultTableModel.addRow(resultSetData);
			}
			table.updateUI ();
		}

	}
	
	/**
	 * This method is used to get the data in the rows selected
	 * @param table
	 * @throws BarberException
	 * @throws SQLException
	 */
	public void getTheSelectedContact (final JTable table) throws BarberException, SQLException
	{
		// contactName - Variable that receives the name
		String contactName = ( String ) table.getValueAt (
				table.getSelectedRow (), 0 );
		
		// contactPhone - Variable that receives the phone
		String contactPhone = ( String ) table.getValueAt (
				table.getSelectedRow( ), 1);
		
		deleteTheContact(contactName, contactPhone);
		
	}
	
	/**
	 *  This method is used to delete a contact
	 * @param contactName - Receives the name of the contact to delete
	 * @param contactPhone - Receives the phone of the contact to delete
	 * @throws BarberException
	 * @throws SQLException
	 */
	public void deleteTheContact (String contactName, String contactPhone) throws BarberException, SQLException
	{	
		// phonebook - Instance of Agenda class
		Phonebook phonebook = new Phonebook ();
		
		phonebook.setPhonebookName ( contactName );
		phonebook.setPhonebook ( contactPhone );

		// confirmation - Shows the confirmation dialog
		int confirmation = JOptionPane.showConfirmDialog (null,
				"Remover " + contactName + " da lista?");

		if ( confirmation == JOptionPane.YES_OPTION ) 
		{
			// phonebookController - Instance of "AgendaController"
			PhonebookController phonebookController = PhonebookController
					.getInstance ();
			phonebookController.removeContact ( phonebook );

			dispose ();
			SearchContact frame = new SearchContact ();
			frame.setVisible ( true );
			frame.setLocationRelativeTo ( null );
		}
	}

	/**
	 * Method that shows an error message
	 * @param errorInformation - Shows an error message to the user
	 */
	private void showErrorMessage( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE );
	}
	
}
