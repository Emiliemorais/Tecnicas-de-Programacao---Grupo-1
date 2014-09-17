
package view;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import control.ServiceTypeController;
import exception.ServicoException;
import model.TipoServico;

@SuppressWarnings("serial")
public class ModifyServiceType extends JFrame
{
	private JPanel contentPane;
	
	// Store the new name of service type that will replace the old one (where 'serviceTypeToBeChangedName' on DB)
	private JTextField textFieldServiceTypeName;
	
	// Store the new price of service type that will replace the old one (where 'serviceTypeToBeChangedName' on DB)
	private JTextField textFieldServiceTypePrice;
	
	// String that contains the service type name that will be changed
	private String serviceTypeToBeChangedName;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
        {
			public void run()
            {
				try
				{
					// Window to choose the service type to be changed
					ModifyServiceType modifyServiceTypeFrame = new ModifyServiceType();
					modifyServiceTypeFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		);
	}


	public ModifyServiceType()
	{
		
		setTitle("Alterar Tipo Servico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 163);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldServiceTypeName = new JTextField();
		textFieldServiceTypeName.setColumns(10);
		textFieldServiceTypeName.setBounds(121, 11, 289, 20);
		contentPane.add(textFieldServiceTypeName);
		
		// Label to textFieldServiceTypeName
		JLabel lblServiceTypeName = new JLabel("Tipo de servi\u00E7o:");
		lblServiceTypeName.setBounds(21, 14, 90, 14);
		contentPane.add(lblServiceTypeName);

		textFieldServiceTypePrice = new JTextField();
		textFieldServiceTypePrice.setColumns(10);
		textFieldServiceTypePrice.setBounds(121, 42, 289, 20);
		contentPane.add(textFieldServiceTypePrice);
		
		// Label to textFieldServiceTypePrice
		JLabel lblServiceTypePrice = new JLabel("Pre\u00E7o:");
		lblServiceTypePrice.setBounds(21, 45, 61, 14);
		contentPane.add(lblServiceTypePrice);

		try
		{
			/* Used to pass as argument on the method 'searchServiceTypeByName' 
			 * (service type name to search on DB )
			 */
			TipoServico serviceType = new TipoServico();
			
			// Intantiated to get access to the method 'searchServiceTypeByName'
			ServiceTypeController serviceTypeController = ServiceTypeController.getInstance();
			
			serviceType.setNomeTipoServico(TipoServico.getTempNome() );
			
			// Used to receive the result from the method 'searchServiceTypeByName'
			ResultSet queryForServicesTypeResult;
			
			queryForServicesTypeResult = serviceTypeController
										 .searchServiceTypeByName(serviceType);

			while ( queryForServicesTypeResult.next() )
            {
				textFieldServiceTypeName.setText( queryForServicesTypeResult.getString("nome") );
				textFieldServiceTypePrice.setText( queryForServicesTypeResult.getString("preco") );
			}
			
			serviceTypeToBeChangedName = textFieldServiceTypeName.getText();
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage() );
		}
		catch (ServicoException e)
		{
			showErrorMessage(e.getMessage() );
		}
		
		// Button that save on DB all changes made
		JButton btnSaveChanges = new JButton("Salvar");
		btnSaveChanges.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				try
				{
					// Instance used to set the changes made and then save on DB
					TipoServico serviceType = new TipoServico();
					
					serviceType.setNomeTipoServico(textFieldServiceTypeName.getText() );
					serviceType.setPreco(textFieldServiceTypePrice.getText() );
					
					// Intantiated to get access to the method 'modifyServiceType' 
					ServiceTypeController serviceTypeController;
					
					serviceTypeController = ServiceTypeController.getInstance();
					
					serviceTypeController.modifyServiceType(serviceTypeToBeChangedName,
															serviceType);

					JOptionPane.showMessageDialog(null, "Tipo de Serviço "
												  + textFieldServiceTypeName.getText()
												  + " foi alterado com sucesso");

					dispose();
					
					// Frame used to go back on service types options (called after save changes)
					CadastrarTipoServico registerServiceTypeFrame = new CadastrarTipoServico();
					registerServiceTypeFrame.setVisible(true);
					registerServiceTypeFrame.setLocationRelativeTo(null);
				}
				catch (ServicoException e1)
				{
					showErrorMessage(e1.getMessage() );
				}
				catch (SQLException k)
				{
					showErrorMessage(k.getMessage() );
				}
			}
		}
		);
		btnSaveChanges.setBounds(10, 86, 124, 23);
		contentPane.add(btnSaveChanges);
		
		// Button that clear all fields from table
		JButton btnClearFields = new JButton("Limpar Campos");
		btnClearFields.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				textFieldServiceTypeName.setText("");
				textFieldServiceTypePrice.setText("");
			}
		}
		);
		btnClearFields.setBounds(282, 86, 128, 23);
		contentPane.add(btnClearFields);
		
		// Button that go back on service types options
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
            {
				dispose();
				
				// Frame used to go back on service types options
				CadastrarTipoServico registerServiceTypeFrame = new CadastrarTipoServico();
				registerServiceTypeFrame.setVisible(true);
				registerServiceTypeFrame.setLocationRelativeTo(null);
			}
		}
		);
		btnVoltar.setBounds(144, 86, 128, 23);
		contentPane.add(btnVoltar);
	}
	
	/* Method that shows the error message when a exception is triggered
	 * Parameter: exceptionInformation - String that contains the message from the exception 
	 */
	private void showErrorMessage(String exceptionInformation)
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
