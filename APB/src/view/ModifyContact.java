
package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.PhonebookController;
import exception.BarberException;
import model.Phonebook;

@SuppressWarnings("serial")

public class ModifyContact extends JFrame
{
	private JPanel contentPane;
	
	// Contains the contact name that will replace the field 'Nome'
	private JTextField newContactNameTextField;
	
	// Contains the contact phone that will replace the field 'Telefone'
	private JTextField newContactPhoneTextField;
	
	// Contains the contact description that will replace the field 'Descricao' 
	private JTextField newContactDescriptionTextField;
	
	// Stores the contact name that will be replaced on DB
	private String contactNameToChange;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {
			public void run()
            {
				try
				{
					// Frame used to open a window to modify a contact 
					ModifyContact modifyContactFrame = new ModifyContact();
					modifyContactFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}


	public ModifyContact()
	{

		setTitle("Alterar Contato");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 225);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);

		newContactNameTextField = new JTextField();
		newContactNameTextField.setBounds(83, 22, 341, 20);
		contentPane.add(newContactNameTextField);
		newContactNameTextField.setColumns(10);

		newContactPhoneTextField = new JTextField();
		newContactPhoneTextField.setBounds(83, 53, 341, 20);
		contentPane.add(newContactPhoneTextField);
		newContactPhoneTextField.setColumns(10);

		newContactDescriptionTextField = new JTextField();
		newContactDescriptionTextField.setBounds(83, 84, 341, 41);
		contentPane.add(newContactDescriptionTextField);
		newContactDescriptionTextField.setColumns(10);
		
		// Label that holds the string 'Name:'
		JLabel lblName = new JLabel("Nome:");
		lblName.setBounds(10, 25, 46, 14);
		contentPane.add(lblName);
		
		// Label that holds the string 'Phone:'
		JLabel lblPhone = new JLabel("Telefone:");
		lblPhone.setBounds(10, 56, 46, 14);
		contentPane.add(lblPhone);
		
		// Label that holds the string 'Description:'
		JLabel lblDescription = new JLabel("Descri\u00E7\u00E3o:");
		lblDescription.setBounds(10, 97, 63, 14);
		contentPane.add(lblDescription);

		try
		{
			// Used to receive the contact name that will be modified, and then search on DB for it
			Phonebook contactToBeChanged = new Phonebook();
			
			// Instantiated to get access to the method 'searchByName()'
			PhonebookController phonebookController = PhonebookController.getInstance();
			
			contactToBeChanged.setPhonebookName(Phonebook.getNameTemporary() );
			
			// Used to receive the result from a search by 'contactToBeChanged.name' on DB
			ResultSet queryForNameResult = phonebookController.pesquisarPorNome(contactToBeChanged);

			while ( queryForNameResult.next() )
            {
				newContactNameTextField.setText( queryForNameResult.getString("nome") );
				newContactPhoneTextField.setText( queryForNameResult.getString("telefone") );
				newContactDescriptionTextField.setText( queryForNameResult.getString("descricao") );
			}
			contactNameToChange = newContactNameTextField.getText();
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage());
		}
		catch (BarberException e)
		{
			showErrorMessage(e.getMessage());
		}
		
		// Button that save on DB all changes made
		JButton btnSaveChanges = new JButton("Salvar Altera\u00E7\u00E3o");
		btnSaveChanges.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent arg0)
            {
				try
				{
					// Used to store data from the modified contact (new contact)
					Phonebook modifiedContact = new Phonebook();
					
					modifiedContact.setPhonebookName( newContactNameTextField.getText() );
					modifiedContact.setPhonebook( newContactPhoneTextField.getText() );
					modifiedContact.setPhonebookDs( newContactDescriptionTextField.getText() );

					// Instantiated to get access to the method 'alterar' 
					PhonebookController phonebookController = PhonebookController.getInstance();
					
					phonebookController.alterar(contactNameToChange, modifiedContact);

					JOptionPane.showMessageDialog(null, "Agenda "
												  + newContactNameTextField.getText()
												  + " foi alterado com sucesso");

					dispose();
					
					// Frame used to open the window with options within a Phonebook	
					RegisterPhonebook registerPhonebookFrame = new RegisterPhonebook();
					registerPhonebookFrame.setVisible(true);
					registerPhonebookFrame.setLocationRelativeTo(null);
				}
				catch (BarberException e1)
				{
					showErrorMessage(e1.getMessage());
				}
				catch (SQLException k)
				{
					showErrorMessage(k.getMessage());
				}
			}
		}
		);
		btnSaveChanges.setBounds(83, 136, 153, 31);
		contentPane.add(btnSaveChanges);
		
		/* 
		 * Button that calls the class 'RegisterPhonebook'
		 * (Create a frame to go back in phonebook options) 
		 */
		JButton btnBack = new JButton("Voltar");
		btnBack.addMouseListener(new MouseAdapter()
        {
			@Override
			public void mouseClicked(MouseEvent e)
            {
				dispose();
				
				// Frame used to open the window with options within a Phonebook
				RegisterPhonebook registerPhonebookFrame = new RegisterPhonebook();
				registerPhonebookFrame.setVisible(true);
				registerPhonebookFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnBack.setBounds(259, 136, 165, 31);
		contentPane.add(btnBack);
	}

	/** 
	 * Method that shows the error message when a exception is triggered
	 * @param exceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
