package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.Barber;
import control.BarberController;
import exception.BarberException;

@SuppressWarnings("serial")
public class ModifyBarber extends JFrame 
{
	
	// Jpanel class's instance to create the panel of change barber
	private JPanel contentPane;
	
	// Receives the name of a barber
	private JTextField textFieldBarberName;
	
	// Receives the  rg of a barber
	private JTextField textFieldBarberRg;
	
	// Receives the telephone of a barber
	private JTextField textFieldBarberTelephone;
	
	// Receives the chair of a barber
	private JTextField textFieldBarberChair;
	
	// Used for receives many things of a barber
	private String textGlobalBarber;

	// Receives the cpf of a barber
	private JTextField textFieldBarberCpf;

	public static void main(String[] args)
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{
				public void run() 
				{
					try
					{
						// Used to create a frame of change barber
						ModifyBarber changeBarberFrame = new ModifyBarber();
						changeBarberFrame.setVisible(true);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		);
	}
	
	// Constructor 
	public ModifyBarber() 
	{
		
		// Define the descriptions of the panel 
		setTitle("Alterar Barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 283);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Add the field barber name to the panel
		textFieldBarberName = new JTextField();
		textFieldBarberName.setColumns(10);
		textFieldBarberName.setBounds(92, 11, 354, 20);
		contentPane.add(textFieldBarberName);
		
		// Add the label name to the panel
		JLabel labelName = new JLabel("Nome:");
		labelName.setBounds(21, 14, 46, 14);
		contentPane.add(labelName);
		
		// Add the label cpf to the panel
		JLabel labelCpf = new JLabel("CPF:");
		labelCpf.setBounds(21, 43, 31, 14);
		contentPane.add(labelCpf);
		
		// Add the text field of the cpf to the panel
		textFieldBarberCpf = new JTextField();
		textFieldBarberCpf.setBounds(92, 40, 354, 20);
		contentPane.add(textFieldBarberCpf);
		textFieldBarberCpf.setColumns(10);
		
		// Add the text field of the cpf to the panel		
		textFieldBarberRg = new JTextField();
		textFieldBarberRg.setColumns(10);
		textFieldBarberRg.setBounds(92, 71, 354, 20);
		contentPane.add(textFieldBarberRg);
		
		// Add the label rg to the panel
		JLabel labelRg = new JLabel("RG:");
		labelRg.setBounds(21, 77, 46, 14);
		contentPane.add(labelRg);
		
		// Add the text field of the telephone to the panel		
		textFieldBarberTelephone = new JTextField();
		textFieldBarberTelephone.setColumns(10);
		textFieldBarberTelephone.setBounds(92, 102, 354, 20);
		contentPane.add(textFieldBarberTelephone);
		
		// Add the label telephone to the panel
		JLabel labelTelephone = new JLabel("Telefone:");
		labelTelephone.setBounds(21, 108, 61, 14);
		contentPane.add(labelTelephone);
		
		// Add the text field of the chair to the panel		
		textFieldBarberChair = new JTextField();
		textFieldBarberChair.setColumns(10);
		textFieldBarberChair.setBounds(92, 133, 354, 20);
		contentPane.add(textFieldBarberChair);
		
		// Add the label chair to the panel
		JLabel labelChair = new JLabel("Cadeira:");
		labelChair.setBounds(21, 139, 61, 14);
		contentPane.add(labelChair);

		try
		{
			// Barber class's instance to access the class
			Barber barber = new Barber();
			
			// BarberController class's instance to access the class
			BarberController barberController = BarberController.getInstance();
			barber.setBarberName( Barber.getTemporaryName() );
			
			// ResultSet interface`s instance used to consult the barbers in the database
			ResultSet queryForBarber = barberController.searchBarberByName(barber);
			
			while (queryForBarber.next()) 
			{
				textFieldBarberName.setText( queryForBarber.getString("nome") );
				textFieldBarberCpf.setText( queryForBarber.getString("cpf") );
				textFieldBarberRg.setText( queryForBarber.getString("rg") );
				textFieldBarberTelephone.setText( queryForBarber.getString("telefone") );
				textFieldBarberChair.setText( queryForBarber.getString("cadeira") );
			}
			textGlobalBarber = textFieldBarberCpf.getText();
		} 
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage());
		} 
		catch (BarberException e)
		{
			showErrorMessage(e.getMessage());
		}
		
		// Creates a button to save the changes
		JButton saveButton = new JButton("Salvar");
		saveButton.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed (ActionEvent e)
			{
				try 
				{
					Barber barber = new Barber();
					barber.setBarberName( textFieldBarberName.getText() );
					barber.setBarberCpf( textFieldBarberCpf.getText() );
					barber.setBarberRg( textFieldBarberRg.getText() );
					barber.setBarberTelephone( textFieldBarberTelephone.getText() );
					barber.setBarberChair( textFieldBarberChair.getText() );

					BarberController barberController = BarberController.getInstance();
					barberController.modifyBarber(textGlobalBarber, barber);
					
					String textToShowSucessModify = "Barbeiro "
													+ textFieldBarberName.getText()
													+ " foi alterado com sucesso";
					
					JOptionPane.showMessageDialog(null, textToShowSucessModify);

					dispose();
					
					// Used to show the frame of a register barber
					RegisterBarber registerBarberframe = new RegisterBarber();
					
					registerBarberframe.setVisible(true);
					registerBarberframe.setLocationRelativeTo(null);

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
		});
		
		saveButton.setBounds(10, 196, 125, 23);
		contentPane.add(saveButton);
		
		// Creates a button that clears the fields
		JButton clearFieldButton = new JButton("Limpar Campos");
		clearFieldButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed (ActionEvent e)
			{
				textFieldBarberName.setText("");
				textFieldBarberRg.setText("");
				textFieldBarberTelephone.setText("");
				textFieldBarberChair.setText("");
			}
		});
		clearFieldButton.setBounds(308, 196, 138, 23);
		contentPane.add(clearFieldButton);
		
		// Creates a button that returns to the frame Register Given Service
		JButton returnButton = new JButton("Voltar");
		returnButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				RegisterBarber registerBarberFrame = new RegisterBarber();
				registerBarberFrame.setVisible(true);
				registerBarberFrame.setLocationRelativeTo(null);
			}
		});
		returnButton.setBounds(158, 196, 125, 23);
		contentPane.add(returnButton);
				
	}
	
	/**
	 *  Method used to show an error message for exception treatment
	 *	@param errorMessage - Receives an error message
	 */
	private void showErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(null, errorMessage, "Aten��o",
									  JOptionPane.INFORMATION_MESSAGE);
	}
	
}
