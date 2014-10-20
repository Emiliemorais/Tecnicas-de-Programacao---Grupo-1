
package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.text.ParseException;

@SuppressWarnings("serial")
public class Administrative extends JFrame
{
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {

			public void run()
            {
				try
				{
					Administrative administrativeFrame = new Administrative();
					administrativeFrame.setVisible(true);
					administrativeFrame.setLocationRelativeTo(null);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	/**
	 * Create the frame.
	 */
	public Administrative()
	{
		// These methods are used to initialize the components  
		initializeFrame();
		initializePanel();
		
	}
	// This method is used to initialize the main frame of the application
	private void initializeFrame() 
	{
		setTitle("APB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 238);			
	}
	
	// This method is used to initialize the panel to insert the components
	private void initializePanel() 
	{
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		// Panel that contains the content of Administrative panel
		JPanel administrativePanel = new JPanel();
		administrativePanel.setBorder(new TitledBorder(UIManager
									 .getBorder("TitledBorder.border"), "Administrativo",
									 TitledBorder.CENTER, TitledBorder.TOP, null, null) );
		administrativePanel.setBounds(10, 11, 379, 183);
		contentPane.add(administrativePanel);
		administrativePanel.setLayout(null);
		
		// This method initialize the buttons
		initializeButtons(administrativePanel);
		
	}

	// This method is used to initialize the buttons
	private void initializeButtons(JPanel administrativePanel) 
	{
				
		createButtonToBackTheMainFrame(administrativePanel);				
		createButtonToOpenBarberFrame(administrativePanel);
		createButtonToOpenPhonebookFrame(administrativePanel);
		createButtonToOpenReceiptFrame(administrativePanel);
		createButtonToOpenServiceTypeFrame(administrativePanel);

	}
	
	// This method is used to create the button and the action that back to the main frame
		private void createButtonToBackTheMainFrame(JPanel administrativePanel) 
		{
			
			/* 
			 * Button that calls the class 'MainMenu' 
			 * (Create a frame that links to the main menu)
			 */
			JButton btnBack = new JButton("Voltar");
			btnBack.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent arg0)
	            {
					dispose();
					
					// Frame used to "go back" to the main menu
					MainMenu mainMenuFrame = new MainMenu();
					mainMenuFrame.setVisible(true);
					mainMenuFrame.setLocationRelativeTo(null);
				}
			}
			);
			btnBack.setBounds(151, 141, 89, 23);
			administrativePanel.add(btnBack);
		}
		
	
	// This method is used to create the button and the action that open the barber frame
	private void createButtonToOpenBarberFrame(JPanel administrativePanel) 
	{
		// Button that calls the class 'RegisterBarber' (Create a frame to register a barber)
		JButton btnBarber = new JButton("Barbeiro");
		btnBarber.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				dispose();
				
				// Frame used to register a barber
				RegisterBarber registerBarberFrame = new RegisterBarber();
				registerBarberFrame.setVisible(true);
				registerBarberFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnBarber.setBounds(10, 45, 157, 37);
		administrativePanel.add(btnBarber);	
	}
	
	// This method is used to create the button and the action that open the Phonebook frame
	private void createButtonToOpenPhonebookFrame(JPanel administrativePanel) 
	{
		/* 
		 * Button that calls the class 'RegisterPhonebook'
		 * (Create a frame to register a phonebook)
		 */
		JButton btnPhonebook = new JButton("Agenda");
		btnPhonebook.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				dispose();
				
				// Frame used to register a phonebook
				RegisterPhonebook registerPhonebookframe = new RegisterPhonebook();
				registerPhonebookframe.setVisible(true);
				registerPhonebookframe.setLocationRelativeTo(null);
			}
		}
		);
		btnPhonebook.setBounds(10, 93, 157, 37);
		administrativePanel.add(btnPhonebook);
	
	}
	
	// This method is used to create the button and the action that open the receipt frame
	private void createButtonToOpenReceiptFrame(JPanel administrativePanel) 
	{
		/*
		 * Button that calls the class 'GenerateReceipt' 
		 * (Create a frame that allows to create a receipt)
		 */
		JButton btnReceipt = new JButton("Recibo");
		btnReceipt.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				dispose();
				GenerateReceipt generateReceiptFrame = null;
				try
				{
					// Frame used to generate a receipt
					generateReceiptFrame = new GenerateReceipt();
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				generateReceiptFrame.setVisible(true);
				generateReceiptFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnReceipt.setBounds(215, 93, 149, 37);
		administrativePanel.add(btnReceipt);	
	}

	// This method is used to create the button and the action that open the Service Type frame
		private void createButtonToOpenServiceTypeFrame(JPanel administrativePanel) 
		{
			
			/* 
			 * Button that calls the class 'RegisterServiceType'
			 *  (Create a frame to register a service type)
			 */
			JButton btnServiceType = new JButton("Tipo de Servi\u00E7o");
			btnServiceType.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent e)
	            {
					dispose();
					
					// Frame used to register a serviceType
					RegisterServiceType registerServiceTypeFrame = new RegisterServiceType();
					registerServiceTypeFrame.setVisible(true);
					registerServiceTypeFrame.setLocationRelativeTo(null);
				}
			});
			btnServiceType.setBounds(215, 45, 149, 37);
			administrativePanel.add(btnServiceType);
			
		}
}

