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

import control.AgendaController;
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

	private JPanel contentPane; // Instance of Jpanel
	private JTextField textField; // Instance of JTextField

	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater(new Runnable () 
		{
			public void run () 
			{
				
				try 
				{
					SearchContact frame = new SearchContact(); // frame - Instance of "SearchContact"
					frame.setVisible ( true );
				} 
				catch ( Exception e ) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	// Method used to initialize componentes
	public SearchContact () 
	{
		initializeComponents ();
	}
	
	// Method that sets initial values to the screen components
	public void initializeComponents ()
	{
		
		setTitle( "Pesquisar Contato" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 450, 300 );
		contentPane = new JPanel (); // contentPane - Instance of Jpanel
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );

		JScrollPane scrollPane = new JScrollPane (); // scrollPane - Creates the scrollbars
		scrollPane.setBounds ( 10, 11, 414, 115);
		contentPane.add ( scrollPane );

		final DefaultTableModel defaultTableModel = new DefaultTableModel ( null,
				new String[] { "Nome", "Telefone", "Descrição" });
		// defaultTableModel - Default Table Model Instance
		final JTable table = new JTable ( defaultTableModel ); 

		table.getColumnModel().getColumn(0).setResizable ( false );
		table.getColumnModel().getColumn(1).setResizable ( false );
		table.getColumnModel().getColumn(2).setResizable ( false );
		scrollPane.setViewportView ( table );

		textField = new JTextField (); // textField - Instance of JTextField 
		textField.setBounds ( 82, 137, 342, 20);
		contentPane.add ( textField );
		textField.setColumns ( 10 );

		JLabel searchLabel = new JLabel ( "Pesquisar:" ); // searchLabel - Label that says "Search"
		searchLabel.setBounds ( 20, 137, 66, 14);
		contentPane.add ( searchLabel);

		JButton nameSearchButton = new JButton ( "Pesquisar Nome");
		// nameSearchButton - Button that offers the "Search name" option
		nameSearchButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			// Method that allows the use of the mouse for navigation
			public void mouseClicked ( MouseEvent arg0)
			{
				try 
				{
					for (int i = 0; i < defaultTableModel.getRowCount (); i++) 
					{
						defaultTableModel.removeRow ( i );
					}

					Phonebook contact = new Phonebook (); // contact - // Instance of "Agenda"
					AgendaController agendaController = AgendaController
							.getInstance();
					contact.setPhonebookName ( textField.getText () );
					ResultSet resultInstance = agendaController.pesquisarPorNome ( contact );
					// resultInstance - ResultSetInstance

					while ( resultInstance.next ( ) ) 
					{
						String[] resultSetData = new String[3];
						// resultSetData - Receives the name, phone and description
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

		JButton phoneSearchButton = new JButton ( "Pesquisar Telefone" );
		// phoneSearchButton - Button that says "Phone Search"
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

					Phonebook contact = new Phonebook (); // contact - Instance of "AgendaController"
					AgendaController agendaController = AgendaController
							.getInstance();
					contact.setPhonebook ( textField.getText () );
					ResultSet resultInstance = agendaController
							.pesquisarPorTelefone ( contact );
					// resultInstance - ResultSetInstance

					while ( resultInstance.next() ) 
					{
						String[] resultSetData = new String[3];
						// resultSetDate - Receives the name, phone and description
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

		JButton editButton = new JButton ( "Alterar" ); // editButton - Button that says "Edit"
		editButton.addMouseListener ( new MouseAdapter () 
		{
	
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					
					Phonebook.setTempNome ( defaultTableModel.getValueAt (
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

		JButton removeButton = new JButton ( "Remover" ); // removeButton - Button that says "Remove"
		removeButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{

				try 
				{
					
					String contactName = ( String ) table.getValueAt (
							table.getSelectedRow (), 0 );
					// contactName - Variable that receives the name
					
					String contactPhone = ( String ) table.getValueAt (
							table.getSelectedRow( ), 1);
					// contactPhone - Variable that receives the phone
					
					Phonebook phonebook = new Phonebook ();
					// phonebook - Instance of Agenda class
					
					phonebook.setPhonebookName ( contactName );
					phonebook.setPhonebook ( contactPhone );

					int confirmation = JOptionPane.showConfirmDialog (null,
							"Remover " + contactName + " da lista?");
					// confirmation - Shows the confirmation dialog

					if ( confirmation == JOptionPane.YES_OPTION ) 
					{
						AgendaController phonebookController = AgendaController
								.getInstance ();
						// phonebookController - Instance of "AgendaController"
						phonebookController.excluir ( phonebook );

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

		JButton returnButton = new JButton ( "Voltar" );
		// returnButton - Button that says "Return"
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

	/*
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage ( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE );
	}
	
}
