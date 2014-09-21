package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import exception.ReportException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings ( "serial" )
public class MainMenu extends JFrame 
{

	// Creating a panel
	private JPanel contentPane; // contentPane - Variable with the contents of the panel

	/**
	 * Launch the application.
	 */
	public static void main (String[] args ) 
	{
		EventQueue.invokeLater(new Runnable () 
		{
			public void run () 
			{
				try 
				{
					MainMenu frame = new MainMenu (); // frame - Instance of "MainMenu" class
					frame.setVisible ( true );
				} 
				catch ( Exception e ) 
				{
					e.printStackTrace ();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu ( ) 
	{
		setTitle( "APB" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 540, 200);
		contentPane = new JPanel (); // contentPane - Variable with the contents of the panel
		contentPane.setToolTipText ( "Menu Principal" );
		contentPane.setBorder( new EmptyBorder ( 5, 5, 5, 5) );
		setContentPane ( contentPane);
		contentPane.setLayout ( null );

		JPanel panel = new JPanel (); // panel - Instance of "Jpanel" class
		panel.setBorder ( new TitledBorder(UIManager
				.getBorder ( "TitledBorder.border" ), "Menu Principal",
				TitledBorder.CENTER, TitledBorder.TOP, null, null ) );
		panel.setBounds ( 10, 11, 505, 138 );
		contentPane.add ( panel );
		panel.setLayout ( null );

		JButton adminButton = new JButton ( "Administrativo" ); // adminButton - Button that says "Administrative"
		adminButton.addActionListener ( new ActionListener () 
		{
			public void actionPerformed ( ActionEvent e ) 
			{
				dispose();
				Administrative frame = new Administrative ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}
		});
		adminButton.setBounds ( 10, 60, 157, 37 );
		panel.add ( adminButton );

		JButton givenServicesButton = new JButton ( "Servi\u00E7os Prestados" ); 
		// givenServicesButton - Button that says "Given Services"
		givenServicesButton.addActionListener ( new ActionListener () 
		{
			public void actionPerformed ( ActionEvent e ) 
			{
				dispose ();
				RegisterDoneService frame = new RegisterDoneService ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}
		});
		givenServicesButton.setBounds ( 179, 60, 157, 37 );
		panel.add( givenServicesButton );

		JButton reportsButton = new JButton ( "Relat\u00F3rios" ); // reportsButton - Button that says "Reports"
		reportsButton.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					SearchReport.searchType = 0;
					VisualizarRelatorios frame = new VisualizarRelatorios ();
					frame.setVisible( true );
					frame.setLocationRelativeTo( null );
					dispose ();
				} 
				catch ( SQLException e1 ) 
				{
					showErrorMessage ( e1.getMessage () );
				} 
				catch ( ReportException e1 ) 
				{
					e1.printStackTrace();
				} 
				catch ( NullPointerException e1)
				{
					e1.printStackTrace ();
				} 
				catch ( ParseException e1 ) 
				{
					e1.printStackTrace ();
				}
			}
		});
		reportsButton.setBounds ( 346, 60, 149, 37 );
		panel.add ( reportsButton );
	}
	
	/*
	 * Method that shows a error message
	 * @param errorInformation - Shows a error message to the user
	 */
	private void showErrorMessage ( String errorInformation ) 
	{
		JOptionPane.showMessageDialog( null, errorInformation, "Atenção",
				JOptionPane.INFORMATION_MESSAGE );
	}

}

