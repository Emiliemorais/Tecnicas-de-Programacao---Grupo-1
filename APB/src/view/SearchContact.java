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
		initializeComponents();
	}
	
	// Method that sets initial values to the screen components
	public void initializeComponents()
	{
		
		setTitle( "Pesquisar Contato" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 450, 300 );
		
		// contentPane - Instance of Jpanel
		contentPane = new JPanel (); 
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );

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

		// textField - Instance of JTextField 
		textField = new JTextField (); 
		textField.setBounds ( 82, 137, 342, 20);
		contentPane.add ( textField );
		textField.setColumns ( 10 );

		
		// searchLabel - Label that says "Search"
		JLabel searchLabel = new JLabel ( "Pesquisar:" ); 
		searchLabel.setBounds ( 20, 137, 66, 14);
		contentPane.add ( searchLabel);

		
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
					for (int i = 0; i < defaultTableModel.getRowCount (); i++) 
					{
						defaultTableModel.removeRow ( i );
					}

					// contact - Instance of "Agenda"
					Phonebook contact = new Phonebook (); 
					PhonebookController agendaController = PhonebookController
							.getInstance();
					contact.setPhonebookName ( textField.getText () );

					// resultInstance - ResultSetInstance
					ResultSet resultInstance = agendaController.pesquisarPorNome ( contact );

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

		// phoneSearchButton - Button that says "Phone Search"
		JButton phoneSearchButton = new JButton ( "Pesquisar Telefone" );
		phoneSearchButton.addMouseListener ( new MouseAdapter () 
		{
			
			@Override
			public void mouseClicked ( MouseEvent arg0 ) 
			{

				try 
				{
					for ( int i = 0 ; i < defaultTableModel.getRowCount() ; i++)
					{
						defaultTableModel.removeRow ( i );
					}

					// contact - Instance of "AgendaController"
					Phonebook contact = new Phonebook (); 
					PhonebookController agendaController = PhonebookController
							.getInstance();
					contact.setPhonebook ( textField.getText () );
					
					// resultInstance - ResultSetInstance
					ResultSet resultInstance = agendaController
							.pesquisarPorTelefone ( contact );

					while ( resultInstance.next() ) 
					{
						// resultSetDate - Receives the name, phone and description
						String[] resultSetData = new String[3];
						resultSetData[0] = resultInstance.getString ( "nome" );
						resultSetData[1] = resultInstance.getString ( "telefone" );
						resultSetData[2] = resultInstance.getString ( "descricao" );

						defaultTableModel.addRow(resultSetData);

						table.updateUI ();
					}
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

		// removeButton - Button that says "Remove"
		JButton removeButton = new JButton ( "Remover" ); 
		removeButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{

				try 
				{
					// contactName - Variable that receives the name
					String contactName = ( String ) table.getValueAt (
							table.getSelectedRow (), 0 );
					
					// contactPhone - Variable that receives the phone
					String contactPhone = ( String ) table.getValueAt (
							table.getSelectedRow( ), 1);
					
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
						phonebookController.remove ( phonebook );

						dispose ();
						SearchContact frame = new SearchContact ();
						frame.setVisible ( true );
						frame.setLocationRelativeTo ( null );
					}
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
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE );
	}
	
}
