
package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.ContactController;
import control.ServiceTypeController;
import exception.BarberException;
import exception.ServiceException;
import model.Contact;
import model.ServiceType;

@SuppressWarnings("serial")
public class NewServiceType extends JFrame
{
	private JPanel contentPane;
	
	// Text Field that will store the new name of service type
	private JTextField textFieldServiceTypeName;
	
	// Text Field that will store the new price of service type
	private JTextField textFieldServiceTypePrice;

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
					NewServiceType newServiceTypeFrame = new NewServiceType();
					newServiceTypeFrame.setVisible(true);
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
	public NewServiceType()
	{
		// These methods are used to initialize the components  
		initializeFrame();
		initializePanel();
	
	}
	// This method is used to initialize the main frame of the application
		private void initializeFrame() 
		{
			setTitle("Cadastar novo tipo de servi\u00E7o");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 320, 180);

		}
			
		// This method is used to initialize the panel to insert the components
		private void initializePanel() 
		{
			contentPane = new JPanel();
			contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			createLabels(contentPane);
			initializeTextFields(contentPane);
			initializeButtons(contentPane);
		}
		
		/**
		 * This method is used to create the labels on the frame
		 * @param contentPane - Panel that contains the components
		 */
		private void createLabels(JPanel contentPane) 
		{
			// Label to textFieldServiceTypeName
			JLabel lblServiceTypeName = new JLabel("Serviço:");
			lblServiceTypeName.setBounds(29, 33, 46, 14);
			contentPane.add(lblServiceTypeName);
			
			// Label to textFieldServiceTypePrice
			JLabel lblServiceTypePrice = new JLabel("Pre\u00E7o (R$):");
			lblServiceTypePrice.setBounds(29, 65, 65, 17);
			contentPane.add(lblServiceTypePrice);
			
		}

		/**
		 * This method is used to initialize the text fields on the frame
		 * @param contentPane- Panel that contains the components
		 */
		private void initializeTextFields(JPanel contentPane) 
		{
			textFieldServiceTypeName = new JTextField();
			textFieldServiceTypeName.setBounds(100, 30, 170, 20);
			contentPane.add(textFieldServiceTypeName);
			textFieldServiceTypeName.setColumns(10);

			textFieldServiceTypePrice = new JTextField();
			textFieldServiceTypePrice.setBounds(100, 63, 170, 20);
			contentPane.add(textFieldServiceTypePrice);
			textFieldServiceTypePrice.setColumns(10);
			
		}

		/**
		 * This method is used to initialize the buttons on the frame
		 * @param contentPane - Panel that contains the components
		 */
		private void initializeButtons(JPanel contentPane) 
		{
				
			createButtonToSaveTheChanges(contentPane);				
			createButtonToOpenRegisterServiceTypeFrame(contentPane);

		}

		/**
		 * This method is used to create the button and the action 
		 * that open the register service type frame
		 * @param contentPane - Panel that contains the components
		 */
		private void createButtonToOpenRegisterServiceTypeFrame(JPanel contentPane) {
			
			// Create a frame to go back in service types options
			JButton btnBack = new JButton("Voltar");
			btnBack.addActionListener(new ActionListener()
	        {
				public void actionPerformed(ActionEvent e)
	            {
					// Frame used to "go back" to the service types menu
					RegisterServiceType registerServiceTypeFrame = new RegisterServiceType();
					registerServiceTypeFrame.setVisible(true);
					registerServiceTypeFrame.setLocationRelativeTo(null);
					
					dispose();
				}
			}
			);
			btnBack.setBounds(181, 108, 89, 23);
			contentPane.add(btnBack);		
			
		}

		/**
	     * This method is used to create the button and the action 
		 * that save the changes 
		 * @param contentPane - Panel that contains the components
		 */
		private void createButtonToSaveTheChanges(JPanel contentPane) {
			// Button that save on DB all changes made
			JButton btnSaveChanges = new JButton("Salvar");
			btnSaveChanges.addMouseListener(new MouseAdapter()
	        {
				@Override
				public void mouseClicked(MouseEvent arg0)
	            {
					try
					{
						// Instance used to set the changes made and then save on DB
						ServiceType serviceType = new ServiceType();
						
						serviceType.setServiceTypeName(textFieldServiceTypeName.getText() );
						serviceType.setServiceTypePrice(textFieldServiceTypePrice.getText() );
						
						// Instantiated to get access to the method 'includeServiceType' 
						ServiceTypeController serviceTypeController = ServiceTypeController
																	  .getInstance();
						
						serviceTypeController.includeServiceType(serviceType);

						JOptionPane.showMessageDialog(null, "Serviço "
													  + textFieldServiceTypeName.getText()
													  + " foi cadastrado com sucesso");

						dispose();
						
						// Frame used to go back on service types options (called after save changes)
						RegisterServiceType registerServiceTypeFrame = new RegisterServiceType();
						registerServiceTypeFrame.setLocationRelativeTo(null);
						registerServiceTypeFrame.setVisible(true);

					}
					catch (ServiceException e)
					{
						mostrarMensagemDeErro(e.getMessage());
					}
					catch (IllegalArgumentException e)
					{
						mostrarMensagemDeErro(e.getMessage());
					}
					catch (SQLException e)
					{
					    // Nothing to do
					}
				}
			}
			);
			btnSaveChanges.setBounds(29, 108, 89, 23);
			contentPane.add(btnSaveChanges);

		}
	
	/** 
	 * Method that shows the error message when a exception is triggered
	 * @param exceptionInformation - String that contains the message from the exception 
	 */
	private void mostrarMensagemDeErro(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
