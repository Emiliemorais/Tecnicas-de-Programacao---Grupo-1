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

import control.BarberController;
import control.ContactController;
import exception.BarberException;
import model.Barber;
import model.Contact;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;
 
@SuppressWarnings ( "serial" )
public class NewContact extends JFrame 
{
	private JPanel contentPane; 
	
	// Will receive the contact name
	private JTextField nameTextField; 
	
	// Will receive the contact phone
	private JTextField phoneTextField; 
	
	// Will receive the contact description
	private JTextField descriptionTextField; 
	

	public static void main( String[] args ) 
	{
		EventQueue.invokeLater( new Runnable() 
		{
			public void run() 
			{
				try 
				{
					NewContact frame = new NewContact(); 
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	// Class constructor
	public NewContact() throws ParseException 
	{
		initializeFrame();
		initializePanel();
		createMasks();
		initializeButtons();
		createLabels();
	}
	
	/**
	 *  Method that initialize the frame
	 * @throws ParseException
	 */
	public void initializeFrame () throws ParseException 
	{
		setTitle( "Novo Contato" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 450, 300 );
	}
	
	// This method is used to initialize the buttons on the frame
	public void initializePanel () 
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout(null);
	}
	
	/**
	 * This method is used to create the masks format to phone
	 * @throws ParseException
	 */
	public void createMasks () throws ParseException 
	{
		// maskFormatterPhone - Receives and formats the phone number
		MaskFormatter maskFormatterPhone = new MaskFormatter("(##)####-####");
		
		createTextFields(maskFormatterPhone);
	}
		
	// This method is used to initialize the buttons on the frame
	public void initializeButtons () 
	{
		createButtonToSaveTheData();	
		createButtonToClearTheFields();
		createButtonToOpenRegisterPhonebookFrame();
	}
	
	/*
     * This method is used to create the button and the action 
	 * that save the data 
	 */
	public void createButtonToSaveTheData ()
	{
		// saveButton - Button that gives the saving option to the user
		JButton saveButton = new JButton( "Salvar" ); 
		saveButton.addMouseListener(new MouseAdapter() 
		{
			// Method that allows the use of the mouse for navigation
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					getDataFromUser();
					dispose();
					returnToRegisterBarberFrame();
				} 
				catch (SQLException e1) 
				{
					showErrorMessage( e1.getMessage() );
				}
				catch ( BarberException e1 )
				{
					showErrorMessage( e1.getMessage() );
				}
			}
			
		});
	
		saveButton.setBounds( 26, 218, 109, 33 );
		contentPane.add(saveButton);

	}
	
	/*
     * This method is used to create the button and the action 
	 * that clear the fields
	 */
	public void createButtonToClearTheFields () 
	{
		// clearFieldsButton - Button that offers the option to clear the fields to the user
		JButton clearFieldsButton = new JButton( "Limpar Campos" ); 
		clearFieldsButton.addMouseListener( new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				clearFields();
			}
		});
		
		clearFieldsButton.setBounds( 287, 218, 125, 33 );
		contentPane.add( clearFieldsButton );

	}
	
	/*
	 * This method is used to create the button and the action 
	 * that open the register barber frame
	 */
	public void createButtonToOpenRegisterPhonebookFrame () {
		// returnButton - Button that offers the return option to the user
		JButton returnButton = new JButton( "Voltar" ); 
		returnButton.addMouseListener( new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				dispose();
				returnToRegisterBarberFrame();
			}
		});

		returnButton.setBounds(166, 218, 100, 33);
		contentPane.add(returnButton);
	}
	
	/**
	 * This method is used to initialize the text fields on the frame
	 * @param maskFormatterPhone - Receives the mask to the phone format
	 */
	public void createTextFields (MaskFormatter maskFormatterPhone)
	{
		
		nameTextField = new JTextField();
		nameTextField.setBounds(85, 23, 327, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

		phoneTextField = new JFormattedTextField( maskFormatterPhone );
		phoneTextField.setBounds(85, 67, 327, 20);
		contentPane.add( phoneTextField );
		phoneTextField.setColumns(10);

		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(85, 117, 327, 44);
		contentPane.add( descriptionTextField );
		descriptionTextField.setColumns(10);
	}
	
	public void createLabels ()
	{
		// Label that says "Name"
		JLabel nameLabel = new JLabel( "Nome:" ); 
		nameLabel.setBounds( 22, 26, 46, 14 );
		contentPane.add( nameLabel );

		// Label that says "Phone"
		JLabel phoneLabel = new JLabel( "Telefone:" ); 
		phoneLabel.setBounds( 22, 70, 64, 14 );
		contentPane.add( phoneLabel );

		// Label that says "Description"
		JLabel descriptionLabel = new JLabel( "Descri\u00E7\u00E3o:" ); 
		descriptionLabel.setBounds( 22, 117, 64, 14 );
		contentPane.add( descriptionLabel );
		
	}
	/**
	 * This method get the data from user, about the new contact
	 * @throws BarberException
	 * @throws SQLException
	 */
	public void getDataFromUser () throws BarberException, SQLException
	{
		// phonebookData - Gets the name, phone and description
		Contact phonebookData = new Contact();
		phonebookData.setContactName ( nameTextField.getText() );
		phonebookData.setContactPhoneNumber ( phoneTextField.getText() );
		phonebookData.setContactDescription ( descriptionTextField.getText() );
		
		includeBarberInTheDatabase(phonebookData);
	}
	
	/**
	 * This method include the data about the new contact
	 * in the database
	 * @throws SQLException
	 */
	public void includeBarberInTheDatabase (Contact phonebookData) throws SQLException
	{
		// phonebookController - Instance of "PhonebookController" class
		ContactController phonebookController = ContactController.getInstance();
		phonebookController.includeContact ( phonebookData );

		JOptionPane.showMessageDialog(null, "Contato "
									  + nameTextField.getText ()
									  + " foi adicionado com sucesso" );
		
		clearFields();

	}

	// This method is used to clear the fields in the frame
	public void clearFields () 
	{
		nameTextField.setText( "" );
		phoneTextField.setText( "" );
		descriptionTextField.setText( "" );
	}
	
	// This method calls the register barber frame
	public void returnToRegisterBarberFrame ()
	{
		RegisterPhonebook frame =  new RegisterPhonebook() ;
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
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

