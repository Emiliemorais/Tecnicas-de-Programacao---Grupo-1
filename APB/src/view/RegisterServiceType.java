package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import control.ServiceTypeController;
import model.ServiceType;
import exception.ServiceException;

@SuppressWarnings("serial")
public class RegisterServiceType extends JFrame 
{

	private JPanel contentPane;
	private static String nomeTemp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			
			// Method that initializes the window registration service type
			public void run()
			{
				try
				{
					RegisterServiceType frame = new RegisterServiceType();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	// Class constructor
	public RegisterServiceType() 
	{
		setTitle("Tipo de Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 360, 240);
		contentPane.add(scrollPane);

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Serviço", "Valor" });
		final JTable table = new JTable(modelo);
		try 
		{
			ServiceTypeController servicoController = ServiceTypeController.getInstance();
			ServiceType servico= new ServiceType();
			ResultSet instanceStatement = servicoController.showRegistredServiceTypes(servico);
			while (instanceStatement.next())
			{
				String[] data = new String[5];
				data[0] = instanceStatement.getString("nome");
				data[1] = instanceStatement.getString("preco");
				modelo.addRow(data);
			}
		}
		catch (SQLException e)
		{
			showErrorMessage(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked (MouseEvent arg0)
			{

				dispose();
				NewServiceType frame = new NewServiceType();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);

			}
		});
		btnNovo.setBounds(380, 24, 94, 23);
		contentPane.add(btnNovo);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that calls the window changeTypeService to make a change
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					String modeloStringGetValue = modelo.getValueAt(table.getSelectedRow(), 0).toString();
					
					ServiceType.setTemporaryName(modeloStringGetValue);
					ModifyServiceType frame = new ModifyServiceType();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} 
				catch (ServiceException e1) 
				{
					showErrorMessage(e1.getMessage());
				} 
				catch (ArrayIndexOutOfBoundsException e1)
				{
					showErrorMessage("Selecione um Tipo de Serviço");
				}
			}
		});
		btnAlterar.setBounds(380, 58, 94, 23);
		contentPane.add(btnAlterar);

		JButton btnRemove = new JButton("Remover");
		btnRemove.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that performs a delete of a Type of Service
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String nameTypeService = (String) table.getValueAt(table.getSelectedRow(),	0);
				ServiceType serviceType = new ServiceType();
				
				try 
				{	
					serviceType.setServiceTypeName(nameTypeService);
				} 
				catch (ServiceException e1) 
				{
					e1.printStackTrace();
				}
				
				String paramOfShowConfirmDialog = "Remover " + nameTypeService + " da lista?";

				int confirmation = JOptionPane.showConfirmDialog(null,paramOfShowConfirmDialog);

				if(confirmation == JOptionPane.YES_OPTION) 
				{
					ServiceTypeController tipoServicoController = ServiceTypeController.getInstance();
					try 
					{
						tipoServicoController.deleteServiceType(serviceType);
					}
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					
					dispose();
					RegisterServiceType frame = new RegisterServiceType();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				}
				else
				{
					// Nothing to do
				}
			}
		});
		btnRemove.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemove);

		JButton btnBack = new JButton("Voltar");
		btnBack.setBounds(380, 228, 94, 23);
		btnBack.addActionListener(new ActionListener() 
		{
			// VIEW method of returning to the administrative window
			public void actionPerformed (ActionEvent arg0)
			{
				dispose();
				Administrative frame = new Administrative();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		contentPane.add(btnBack);
	}
	
	// Access method for receiving a temporary name
	public static String getNameTemporary() 
	{
		return nomeTemp;
	}
	
	// Method that shows an error message, used in the treatment of exceptions class
	private void showErrorMessage(String information) 
	{
		JOptionPane.showMessageDialog(null, information, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
