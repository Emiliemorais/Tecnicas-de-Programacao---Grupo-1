package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import model.Barber;
import control.BarberController;
import exception.BarberException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings ( "serial" )
public class NewBarber extends JFrame
{
	private JPanel contentPane; 
	
	// Instance of "JTextField", which provides a text field to input the barber name
	private JTextField nameTextField; 
	
	// Instance of "JTextField", which provides a text field to input the CPF (main brazilian document)
	private JTextField textFieldCpf; 
	
	// Instance of "JTextField", which provides a text field to input the RG (state brazilian document)
	private JTextField textFieldRG; 
	
	// Instance of "JTextField", which provides a text field to input the phone number
	private JTextField phoneTextField; 
	
	// Button that gives the save option to the user
	private JButton saveButton; 
	
	// Button that gives the clear form fields option to the user
	private JButton clearFieldsButton; 
	
	// Instance of "JTextField", which provides text fields to the data input
	private JTextField chairTextField; 
	
	// Instance of Jlabel, which provides a label named "Chair"
	private JLabel chairLabel; 
	
	// Button that says "Return"
	private JButton returnButton; 

	
	public static void main( String[] args ) 
	{
		EventQueue.invokeLater( new Runnable() 
		{
			public void run() 
			{
				try 
				{
					NewBarber frame = new NewBarber(); 
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

	// Class constructor
	public NewBarber() throws ParseException 
	{
		initializeFrame();
		initializePanel();
		createLabels();
		createMasks();
		initializeButtons();
	}

	// This method is used to initialize the frame to a new barber
	public void initializeFrame() throws ParseException 
	{
		setTitle( "Cadastrar Barbeiro" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 474, 253);
		
	}
	
	// This method is used to initialize the panel to insert the components
	public void initializePanel ()
	{
		
		contentPane = new JPanel (); 
		contentPane.setBorder( new EmptyBorder ( 5, 5, 5, 5));
		setContentPane ( contentPane );
		getContentPane().setLayout ( null );
		contentPane.setLayout ( null );
		
	}
	
	/**
	 * This method is used to create the labels on the frame
	 * @throws ParseException
	 */
	public void createLabels () throws ParseException
	{
		
		// nameLabel - Instance of Jlabel, which provides a label named "Name"
		JLabel nameLabel = new JLabel( "Nome:" ); 
		nameLabel.setBounds( 21, 14, 46, 14 );
		getContentPane().add ( nameLabel );
		
		// labelCPF - Instance of Jlabel, which provides a label named "CPF"
		JLabel labelCPF = new JLabel( "CPF:" ); 
		labelCPF.setBounds( 21, 45, 46, 14 );
		getContentPane().add( labelCPF );
		
		// labelRG - Instance of Jlabel, which provides a label named "RG"
		JLabel labelRG = new JLabel( "RG:" ); 
		labelRG.setBounds( 21, 76, 46, 14 );
		getContentPane().add( labelRG );
		
		// phoneLabel - Instance of Jlabel, which provides a label named "Phone"
		JLabel phoneLabel = new JLabel( "Telefone:" ); 
		phoneLabel.setBounds( 21, 107, 61, 14 );
		getContentPane().add( phoneLabel );
		
		chairLabel = new JLabel( "Cadeira:" );
		chairLabel.setBounds( 21, 136, 61, 14 );
		contentPane.add( chairLabel );
		
	}
	
	/**
	 * This method is used to create the masks format to phone and cpf
	 * @throws ParseException
	 */
	public void createMasks () throws ParseException
	{
		// phoneMaskFormatter - Receives the phone number
		MaskFormatter phoneMaskFormatter = new MaskFormatter( "(##)####-####" ); 
		
		// maskFormatterCPF - Receives the CPF (main brazillian document)
		MaskFormatter maskFormatterCPF = new MaskFormatter( "###.###.###-##" ); 
		
		createTextFields(phoneMaskFormatter, maskFormatterCPF);
	}
	
	/**
	 * This method is used to initialize the text fields on the frame
	 * @param phoneMaskFormatter - Receives the mask to the phone format
	 * @param maskFormatterCPF - Receives the mask to the cpf format
	 * @throws ParseException
	 */
	public void createTextFields (MaskFormatter phoneMaskFormatter, MaskFormatter maskFormatterCPF) 
								  throws ParseException
	{
		nameTextField = new JTextField();
		nameTextField.setBounds ( 92, 11, 354, 20);
		getContentPane().add ( nameTextField );
		nameTextField.setColumns ( 10 );

		textFieldCpf = new JFormattedTextField( maskFormatterCPF );
		textFieldCpf.setBounds( 92, 42, 354, 20 );
		getContentPane().add( textFieldCpf );
		textFieldCpf.setColumns(10);

		textFieldRG = new JTextField ();
		textFieldRG.setBounds( 92, 73, 354, 20 );
		getContentPane().add( textFieldRG );
		textFieldRG.setColumns( 10 );

		phoneTextField = new JFormattedTextField( phoneMaskFormatter );
		phoneTextField.setBounds( 92, 104, 354, 20 );
		getContentPane().add( phoneTextField );
		phoneTextField.setColumns( 10 );

		chairTextField = new JTextField ();
		chairTextField.setBounds ( 92, 133, 354, 20 );
		contentPane.add( chairTextField );
		chairTextField.setColumns ( 10 );

	}
	
	// This method is used to initialize the buttons on the frame
	public void initializeButtons ()
	{
		createButtonToSaveTheData();	
		createButtonToClearTheFields();
		createButtonToOpenRegisterBarberFrame();
		
	}

	/*
     * This method is used to create the button and the action 
	 * that save the data 
	 */
	public void createButtonToSaveTheData () 
	{
		
		saveButton = new JButton( "Salvar" );
		saveButton.setBounds ( 10, 177, 125, 23 );
		contentPane.add( saveButton );
		saveButton.addMouseListener(new MouseAdapter() 
		{
			// Method that allows the use of the mouse for navigation
			@Override
			public void mouseClicked( MouseEvent k ) 
			{

				try 
				{
					getDataFromUser();
					dispose();
					returnToRegisterBarberFrame();
				} 
				catch (BarberException e)
				{
					showErrorMessage(e.getMessage ());
				} 
				catch (SQLException e) 
				{
					showErrorMessage(e.getMessage ());
				}

			}
	
		});

	}
	
	/*
     * This method is used to create the button and the action 
	 * that clear the fields
	 */
	public void createButtonToClearTheFields () 
	{
		clearFieldsButton = new JButton( "Limpar Campos" );
		clearFieldsButton.addMouseListener( new MouseAdapter()
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				clearFields();
			}

		});
		clearFieldsButton.setBounds(308, 177, 138, 23);
		contentPane.add( clearFieldsButton );

	}
	
	/*
	 * This method is used to create the button and the action 
	 * that open the register barber frame
	 */
	public void createButtonToOpenRegisterBarberFrame() {
		returnButton = new JButton( "Voltar" );
		returnButton.addMouseListener( new MouseAdapter() 
		{
			@Override
			public void mouseClicked( MouseEvent e ) 
			{
				dispose();
				returnToRegisterBarberFrame();
			}
		});
		returnButton.setBounds( 158, 177, 125, 23 );
		contentPane.add( returnButton );
	}
	
	/**
	 * This method get the data from user, about the new barber
	 * @throws BarberException
	 * @throws SQLException
	 */
	public void getDataFromUser () throws BarberException, SQLException
	{
			// barber - Receives name, CPF, RG, phone and chair of the barber
			Barber barber = new Barber(); 
			barber.setBarberName( nameTextField.getText() );
			barber.setBarberCpf( textFieldCpf.getText() );
			barber.setBarberRg( textFieldRG.getText() );
			barber.setBarberTelephone( phoneTextField.getText() );
			barber.setBarberChair( chairTextField.getText() );
			
			includeBarberInTheDatabase(barber);
	}
	
	/**
	 * This method include the data about the new barber
	 * in the database
	 * @throws SQLException
	 */
	public void includeBarberInTheDatabase (Barber barber) throws SQLException
	{
	
	// barberController - Adds a barber
	BarberController barberController = BarberController.getInstance();
	barberController.includeBarber(barber);

	JOptionPane.showMessageDialog(null, "Barbeiro "
								  + nameTextField.getText()
								  + " foi cadastrado com sucesso" );
	}

	// This method is used to clear the fields in the frame
	public void clearFields () 
	{
		nameTextField.setText( "" );
		textFieldCpf.setText( "" );
		textFieldRG.setText( "" );
		phoneTextField.setText( "" );
		chairTextField.setText( "" );
		
	}
	
	// This method calls the register barber frame
	public void returnToRegisterBarberFrame ()
	{
		RegisterBarber frame = new RegisterBarber();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage (String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE );
	}

}
