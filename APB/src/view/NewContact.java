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

import control.PhonebookController;
import exception.BarberException;
import model.Phonebook;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;
 
@SuppressWarnings ( "serial" )
public class NewContact extends JFrame 
{
	// Instance of "Jpanel"
	private JPanel contentPane; 
	
	// Will receive the contact name
	private JTextField nameTextField; 
	
	// Will receive the contact phone
	private JTextField phoneTextField; 
	
	// Will receive the contact description
	private JTextField descriptionTextField; 
	

	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater ( new Runnable ( ) 
		{
			public void run () 
			{
				try 
				{
					// frame - Instance of "NewContact" class
					NewContact frame = new NewContact (); 
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
		
		// maskFormatterPhone - Receives and formats the phone number
		MaskFormatter maskFormatterPhone = new MaskFormatter("(##)####-####");
		
		
		// saveButton - Button that gives the saving option to the user
		JButton saveButton = new JButton( "Salvar" ); 
		saveButton.addMouseListener(new MouseAdapter () 
		{
			// Method that allows the use of the mouse for navigation
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					// phonebookData - Gets the name, phone and description
					Phonebook phonebookData = new Phonebook ();
					phonebookData.setPhonebookName ( nameTextField.getText () );
					phonebookData.setPhonebook ( phoneTextField.getText () );
					phonebookData.setPhonebookDs ( descriptionTextField.getText () );
					
					// phonebookController - Instance of "PhonebookController" class
					PhonebookController phonebookController = PhonebookController.getInstance ();
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

		
		// returnButton - Button that offers the return option to the user
		JButton returnButton = new JButton ( "Voltar" ); 
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

		// clearFieldsButton - Button that offers the option to clear the fields to the user
		JButton clearFieldsButton = new JButton( "Limpar Campos" ); 
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

		// Label that says "Name"
		JLabel nameLabel = new JLabel ( "Nome:" ); 
		nameLabel.setBounds ( 22, 26, 46, 14 );
		contentPane.add ( nameLabel );

		// Label that says "Phone"
		JLabel phoneLabel = new JLabel ( "Telefone:" ); 
		phoneLabel.setBounds ( 22, 70, 64, 14 );
		contentPane.add ( phoneLabel );

		// Label that says "Description"
		JLabel descriptionLabel = new JLabel ( "Descri\u00E7\u00E3o:" ); 
		descriptionLabel.setBounds ( 22, 117, 64, 14 );
		contentPane.add ( descriptionLabel );
		
	}

	/**
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Atenção",
				JOptionPane.INFORMATION_MESSAGE );
	}

}

