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

	private JPanel contentPane; // Instance of "Jpanel"
	private JTextField nameTextField; // Instance of "JTextField", which provides a text field to input the name
	private JTextField textFieldCpf; // Instance of "JTextField", which provides a text field to input the CPF (main brazilian document)
	private JTextField textFieldRG; // Instance of "JTextField", which provides a text field to input the RG (state brazilian document)
	private JTextField phoneTextField; // Instance of "JTextField", which provides a text field to input the phone number
	private JButton saveButton; // Button that gives the save option to the user
	private JButton clearFieldsButton; // Button that gives the clear form fields option to the user
	private JTextField chairTextField; // Instance of "JTextField", which provides text fields to the data input
	private JLabel chairLabel; // Instance of Jlabel, which provides a label named "Chair"
	private JButton returnButton; // Button that says "Return"

	
	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater ( new Runnable () 
		{
			public void run () 
			{
				try 
				{
					NewBarber frame = new NewBarber(); // frame - instance of "NewBarber" class
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
				} 
				catch ( Exception e ) 
				{
					e.printStackTrace ();
				}
			}
		});
	}

	// Method used to initialize componentes
	public NewBarber() throws ParseException 
	{
		initializeComponents ();
	}

	// Method that sets initial values to the screen components
	public void initializeComponents() throws ParseException 
	{
		setTitle ( "Cadastrar Barbeiro" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 474, 253);
		contentPane = new JPanel (); // contentPane - Instance of "Jpanel"
		contentPane.setBorder( new EmptyBorder ( 5, 5, 5, 5));
		setContentPane ( contentPane );
		getContentPane().setLayout ( null );
		contentPane.setLayout ( null );
		
		
		
		MaskFormatter phoneMaskFormatter = new MaskFormatter ( "(##)####-####" ); // phoneMaskFormatter - Receives the phone number
		MaskFormatter maskFormatterCPF = new MaskFormatter ( "###.###.###-##" ); 
		// maskFormatterCPF - Receives the CPF (main brazillian document)
	
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds ( 92, 11, 354, 20);
		getContentPane().add ( nameTextField );
		nameTextField.setColumns ( 10 );

		JLabel nameLabel = new JLabel ( "Nome:" ); // nameLabel - Instance of Jlabel, which provides a label named "Name"
		nameLabel.setBounds ( 21, 14, 46, 14 );
		getContentPane().add ( nameLabel );

		textFieldCpf = new JFormattedTextField ( maskFormatterCPF );
		textFieldCpf.setBounds ( 92, 42, 354, 20 );
		getContentPane().add ( textFieldCpf );
		textFieldCpf.setColumns(10);

		JLabel labelCPF = new JLabel ( "CPF:" ); // labelCPF - Instance of Jlabel, which provides a label named "CPF"
		labelCPF.setBounds ( 21, 45, 46, 14 );
		getContentPane().add ( labelCPF );

		textFieldRG = new JTextField ();
		textFieldRG.setBounds ( 92, 73, 354, 20 );
		getContentPane().add ( textFieldRG );
		textFieldRG.setColumns ( 10 );

		JLabel labelRG = new JLabel ( "RG:" ); // labelRG - Instance of Jlabel, which provides a label named "RG"
		labelRG.setBounds ( 21, 76, 46, 14 );
		getContentPane().add ( labelRG );

		phoneTextField = new JFormattedTextField ( phoneMaskFormatter );
		phoneTextField.setBounds ( 92, 104, 354, 20 );
		getContentPane().add ( phoneTextField );
		phoneTextField.setColumns ( 10 );

		JLabel phoneLabel = new JLabel ( "Telefone:" ); // phoneLabel - Instance of Jlabel, which provides a label named "Phone"
		phoneLabel.setBounds ( 21, 107, 61, 14 );
		getContentPane().add ( phoneLabel );

		chairLabel = new JLabel ( "Cadeira:" );
		chairLabel.setBounds ( 21, 136, 61, 14 );
		contentPane.add ( chairLabel );
		


		saveButton = new JButton ( "Salvar" );
		saveButton.addMouseListener(new MouseAdapter () 
		{
			@Override
			// Method that allows the use of the mouse for navigation
			public void mouseClicked ( MouseEvent k ) 
			{
				try 
				{
					Barber barber = new Barber (); 
					// barber - Receives name, CPF, RG, phone and chair of the barber
					barber.setBarberName ( nameTextField.getText () );
					barber.setBarberCpf ( textFieldCpf.getText () );
					barber.setBarberRg ( textFieldRG.getText () );
					barber.setBarberTelephone ( phoneTextField.getText ( ) );
					barber.setBarberChair ( chairTextField.getText () );

					BarberController barberController = BarberController.getInstance ();
					// barberController - Adds a barber
					barberController.includeBarber ( barber );

					JOptionPane.showMessageDialog (null, "Barbeiro "
							+ nameTextField.getText ()
							+ " foi cadastrado com sucesso" );

					dispose();
					RegisterBarber frame = new RegisterBarber ();
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
				} 
				catch ( BarberException e )
				{
					showErrorMessage ( e.getMessage () );
				} 
				catch ( SQLException e ) 
				{
					showErrorMessage ( e.getMessage () );
				}
			}

		});

		chairTextField = new JTextField ();
		chairTextField.setBounds ( 92, 133, 354, 20 );
		contentPane.add ( chairTextField );
		chairTextField.setColumns ( 10 );
		saveButton.setBounds ( 10, 177, 125, 23 );
		contentPane.add ( saveButton );

		clearFieldsButton = new JButton ( "Limpar Campos" );
		clearFieldsButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked ( MouseEvent e )
			{
				nameTextField.setText ( "" );
				textFieldCpf.setText ( "" );
				textFieldRG.setText ( "" );
				phoneTextField.setText ( "" );
				chairTextField.setText ( "" );
			}
		});
		clearFieldsButton.setBounds(308, 177, 138, 23);
		contentPane.add ( clearFieldsButton );

		returnButton = new JButton ( "Voltar" );
		returnButton.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				RegisterBarber frame = new RegisterBarber ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}
		});
		returnButton.setBounds ( 158, 177, 125, 23 );
		contentPane.add ( returnButton );

	}

	/*
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage (String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE );
	}

}