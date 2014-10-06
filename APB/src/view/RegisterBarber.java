package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Barber;
import control.BarberController;
import exception.BarberException;

@SuppressWarnings ( "serial" )
public class RegisterBarber extends JFrame 
{

	// Creating a panel
	private JPanel contentPane;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					// frame - Instance of "CadastrarBarbeiro" class
					RegisterBarber frame = new RegisterBarber(); 
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	// Method used to initialize components
	public RegisterBarber() 
	{
		initializeComponents();
	}
	
	// Method that sets initial values to the screen components
	public void initializeComponents() 
	{
		setTitle("Barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 490);
		
		// contentPane - Receives the components values
		contentPane = new JPanel(); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null); 

		// scrollPane - Creates the scrollbars
		JScrollPane scrollPane = new JScrollPane(); 
		scrollPane.setBounds(10, 11, 474, 429);
		contentPane.add(scrollPane);

		// defaultTableModel - Default Table Model Instance
		final DefaultTableModel defaultTableModel = new DefaultTableModel(null,
				new String[] { "Nome", "CPF", "RG", "Telefone", "Cadeira" });
		
		// table - A instance from Jtable
		final JTable table = new JTable ( defaultTableModel ); 

		try 
		{
			// barberController - Instance of "BarberController" class
			BarberController barberController = BarberController.getInstance(); 
			
			// barber - Instance of "Barber" class
			Barber barber = new Barber(); 
			
			// resultInstance - ResultSetInstance
			ResultSet resultInstance = barberController.showRegisteredBarbers(barber); 
			
			while ( resultInstance.next () )
			{
				// data - Gets name, cpf, rg, phone number and chair
				String[] data = new String[5];
				
				data[0] = resultInstance.getString("nome");
				data[1] = resultInstance.getString("cpf");
				data[2] = resultInstance.getString("rg");
				data[3] = resultInstance.getString("telefone");
				data[4] = resultInstance.getString("cadeira");
				defaultTableModel.addRow(data);
			}
		} 
		catch ( SQLException e ) 
		{
			showErrorMessage ( e.getMessage () );
		}

		scrollPane.setViewportView ( table );

		// newButton - Creates a button that says "new"
		JButton newButton = new JButton ( "Novo" ); 
		newButton.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				NewBarber frame;
				try 
				{
					
					frame = new NewBarber ();
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
				} 
				catch ( ParseException e1 ) 
				{
					e1.printStackTrace ();
				}
	
			}
		});
		newButton.setBounds ( 494, 11, 158, 28 );
		contentPane.add ( newButton );

		// editButton - Creates a button that says "edit"
		JButton editButton = new JButton ( "Alterar" ); 
		editButton.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					Barber.setTemporaryName( defaultTableModel.getValueAt ( table.getSelectedRow (), 0).toString () );
					ModifyBarber frame = new ModifyBarber ();
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
					dispose();
				} 
				catch ( ArrayIndexOutOfBoundsException e1 ) 
				{
					showErrorMessage ( "Selecione um Barbeiro para Alterar" );
				}
			}
		});
		editButton.setBounds(494, 50, 158, 28);
		contentPane.add(editButton);

		// deleteButton - Creates a button that says "delete"
		JButton deleteButton = new JButton ( "Remover" ); 
		deleteButton.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent arg0 ) 
			{
				try 
				{
					String nome = (String) table.getValueAt ( table.getSelectedRow (), 0 );
					Barber barber = new Barber ();
					barber.setBarberName ( nome );
					
					// confirmation - Show confirm dialog
					int confirmation = JOptionPane.showConfirmDialog ( null,
							"Remover " + nome + " da lista?" );
					
					if ( confirmation == JOptionPane.YES_OPTION ) 
					{
						BarberController barbeiroController = BarberController.getInstance();
						barbeiroController.deleteBarber ( barber );

						dispose();
						RegisterBarber frame = new RegisterBarber ();
						frame.setVisible( true );
						frame.setLocationRelativeTo ( null );
					}
				} 
				catch ( ArrayIndexOutOfBoundsException e ) 
				{
					showErrorMessage("Selecione um Barbeiro para remover");
				} 
				catch ( BarberException e ) 
				{
					showErrorMessage( e.getMessage () );
				} 
				catch ( SQLException e ) 
				{
					showErrorMessage ( e.getMessage () );
				}
			}
		});
		deleteButton.setBounds ( 494, 89, 158, 28);
		contentPane.add ( deleteButton );
		
		// returnButton - Creates a button that says "return"
		JButton returnButton = new JButton("Voltar"); 
		returnButton.addMouseListener( new MouseAdapter() 
		{
			@Override
			public void mouseClicked ( MouseEvent arg0 ) 
			{
				Administrative frame = new Administrative();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		returnButton.setBounds(494, 412, 158, 28);
		contentPane.add(returnButton);
		
	}
	
	/**
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage ( String errorInformation ) 
	{
		JOptionPane.showMessageDialog(null, errorInformation, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
}