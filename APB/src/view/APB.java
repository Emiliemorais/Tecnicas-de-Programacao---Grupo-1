package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class APB extends JFrame
{
	// Contains the main panel of class
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
					// Main frame of class
					APB apbFrame = new APB();
					apbFrame.setVisible(true);
					apbFrame.setLocationRelativeTo(null);
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
	public APB()
	{
		// These methods are used to initialize the components  
		initializeFrame();
		initializePanel();
		createLabels();
		initializeButtons();

	}

	// This method is used to initialize the main frame of the application
	private void initializeFrame() 
	{
		setTitle("APB");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 357);				
	}
	
	// This method is used to initialize the panel to insert the components
	private void initializePanel() 
	{
		contentPane = new JPanel();
		contentPane.setForeground( new Color(255, 255, 255) );
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	// This method is used to initialize the buttons
	private void initializeButtons() 
	{

		createButtonToStart();
		createButtonToClose();

	}
	
	// This method is used to create the button and the action that close the application
	private void createButtonToClose() 
	{
		JButton btnClose = new JButton("Fechar");
		btnClose.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				System.exit(0);
			}
		}
		);
		btnClose.setIcon(new ImageIcon
						(APB.class.getResource("/resources/cancel-button-icone-7221-48.png") ) );
		btnClose.setBounds(338, 242, 145, 65);
		contentPane.add(btnClose);
		
	}
	
	// This method is used to create the button and the action that starts the application
	private void createButtonToStart() 
	{
		JButton btnStart = new JButton("Iniciar");
		btnStart.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0)
            {
				dispose();
				
				MainMenu mainMenuFrame = new MainMenu();
				mainMenuFrame.setVisible(true);
				mainMenuFrame.setLocationRelativeTo(null);
			}
		});
		btnStart.setIcon(new ImageIcon
						(APB.class.getResource("/resources/ButtonAccept.png") ) );
		btnStart.setBounds(82, 242, 137, 65);
		contentPane.add(btnStart);
		
	}

	// This method is used to create the labels of the frame
	private void createLabels() 
	{
		// Label to welcome
		JLabel lblWelcome = new JLabel("Bem Vindo");
		lblWelcome.setFont( new Font("Tahoma", Font.PLAIN, 17) );
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(195, 13, 160, 38);
		contentPane.add(lblWelcome);

		// Label to describe the 'Barbearia Peixoto'
		JLabel lblAPB = new JLabel("Automa\u00E7\u00E3o de Processos da Barbearia");
		lblAPB.setFont(new Font("Tahoma", Font.PLAIN, 15) );
		lblAPB.setHorizontalAlignment(SwingConstants.CENTER);
		lblAPB.setBounds(127, 175, 288, 57);
		contentPane.add(lblAPB);		
			
		
		// Object that refers to the icon of 'Barbearia Peixoto' logo.
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon
					   (APB.class.getResource("/resources/19fb55_f0fe0bafb3f2bec53376838a10e0825a."
					   						  + "jpg_srz_401_146_75_22_0.50_1.20_0.00_jpg_srz.jpg")) );
		lblIcon.setBounds(82, 62, 401, 119);
		contentPane.add(lblIcon);
	}

}
