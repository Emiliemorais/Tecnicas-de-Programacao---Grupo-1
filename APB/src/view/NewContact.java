package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.AgendaController;
import exception.BarberException;
import model.Agenda;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;
 
@SuppressWarnings ( "serial" )
public class NewContact extends JFrame 
{

	private JPanel contentPane; // Instance of "Jpanel"
	private JTextField nameTextField; // Will receive the contact name
	private JTextField phoneTextField; // Will receive the contact phone
	private JTextField descriptionTextField; // Will receive the contact description

	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater ( new Runnable ( ) 
		{
			public void run () 
			{
				try 
				{
					NewContact frame = new NewContact (); // frame - Instance of "NewContact" class
					frame.setVisible ( true );
				} 
				catch ( Exception e ) 
				{
					e.printStackTrace ();
				}
			}
		});
	}

	// Method used to initialize componentes
	public NewContact () throws ParseException 
	{
		initializeComponents();
	}
	
	// Method that sets initial values to the screen components
	public void initializeComponents () throws ParseException 
	{
		setTitle ( "Novo Contato" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 450, 300 );
		contentPane = new JPanel ();
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5 ) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );
		MaskFormatter maskFormatterPhone = new MaskFormatter("(##)####-####");
		// maskFormatterPhone - Receives and formats the phone number

		JButton saveButton = new JButton( "Salvar" ); // saveButton - Button that gives the saving option to the user
		saveButton.addMouseListener(new MouseAdapter () 
		{
			@Override
			// Method that allows the use of the mouse for navigation
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					Agenda phonebookData = new Agenda ();
					// phonebookData - Gets the name, phone and description
					phonebookData.setNome ( nameTextField.getText () );
					phonebookData.setTelefone ( phoneTextField.getText () );
					phonebookData.setDescricao ( descriptionTextField.getText () );

					AgendaController phonebookController = AgendaController.getInstance ();
					// phonebookController - Instance of "PhonebookController" class
					phonebookController.incluir ( phonebookData );

					JOptionPane.showMessageDialog(null, "Contato "
							+ nameTextField.getText ()
							+ " foi adicionado com sucesso" );
					
					nameTextField.setText ( "" );
					phoneTextField.setText ( "" );
					descriptionTextField.setText ( "" );
					
					dispose();
					RegisterPhonebook frame =  new RegisterPhonebook () ;
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
					
				} 
				catch ( SQLException e1 ) 
				{
					showErrorMessage ( e1.getMessage () );
				}
				catch ( BarberException e1 )
				{
					showErrorMessage ( e1.getMessage () );
				}
			}
			
		});

		saveButton.setBounds ( 26, 218, 109, 33 );
		contentPane.add(saveButton);

		JButton returnButton = new JButton ( "Voltar" ); // returnButton - Button that offers the return option to the user
		returnButton.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				RegisterPhonebook frame = new RegisterPhonebook ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}
		});

		returnButton.setBounds(166, 218, 100, 33);
		contentPane.add(returnButton);

		JButton clearFieldsButton = new JButton( "Limpar Campos" ); 
		// clearFieldsButton - Button that offers the option to clear the fields to the user
		clearFieldsButton.addMouseListener( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				nameTextField.setText ( "" );
				phoneTextField.setText ( "" );
				descriptionTextField.setText ( "" );
			}
		});
		
		clearFieldsButton.setBounds ( 287, 218, 125, 33 );
		contentPane.add ( clearFieldsButton );

		nameTextField = new JTextField();
		nameTextField.setBounds(85, 23, 327, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		phoneTextField = new JFormattedTextField ( maskFormatterPhone );
		phoneTextField.setBounds(85, 67, 327, 20);
		contentPane.add ( phoneTextField );
		phoneTextField.setColumns(10);

		descriptionTextField = new JTextField ();
		descriptionTextField.setBounds(85, 117, 327, 44);
		contentPane.add ( descriptionTextField );
		descriptionTextField.setColumns(10);

		JLabel nameLabel = new JLabel ( "Nome:" ); // Label that says "Name"
		nameLabel.setBounds ( 22, 26, 46, 14 );
		contentPane.add ( nameLabel );

		JLabel phoneLabel = new JLabel ( "Telefone:" ); // Label that says "Phone"
		phoneLabel.setBounds ( 22, 70, 64, 14 );
		contentPane.add ( phoneLabel );

		JLabel descriptionLabel = new JLabel ( "Descri\u00E7\u00E3o:" ); // Label that says "Description"
		descriptionLabel.setBounds ( 22, 117, 64, 14 );
		contentPane.add ( descriptionLabel );
		
	}

	/*
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Atenção",
				JOptionPane.INFORMATION_MESSAGE );
	}

}

