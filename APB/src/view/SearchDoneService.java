	package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import control.DoneServiceController;

import dao.FactoryConnection;
import exception.ServiceException;

import model.DoneService;

@SuppressWarnings("serial")
public class SearchDoneService extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;
	private Connection connection;
	private static String tempNome;

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			
			// method that initializes the search window service
			public void run () 
			{
				try 
				{
					SearchDoneService frame = new SearchDoneService();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	// Builder components of the search window service
	public SearchDoneService() 
	{
		startComponents();
	}

	// Builder components of the search window service
	public void startComponents ()
	{
		setTitle("Pesquisar Servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 464, 115);
		contentPane.add(scrollPane);

		final DefaultTableModel model = new DefaultTableModel(null,
																new String[] { "Serviço", "Realizado por", "Valor", "Data" });
																// Lembrar de pedir pro italo ajudar com esse metodo////
		final JTable table = new JTable(model);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(82, 137, 392, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(20, 137, 66, 14);
		contentPane.add(lblPesquisar);

		JButton btnSearchService = new JButton("Pesquisar Serviço");
		btnSearchService.addActionListener(new ActionListener() {
			
			// VIEW method that is used to search a Service Provided
			public void actionPerformed (ActionEvent arg0) 
			{
				try 
				{
					DoneService servico = new DoneService();
					servico.setServiceName(textField.getText());
					
					String paramCreateStatement = "SELECT nome, preco, barbeiro,"
												+ " data FROM servicoprestado WHERE nome = '"
												+ servico.getServiceName() + "' ORDER BY data;";

					connection = FactoryConnection.getInstance().getConnection();
					ResultSet instanceStatement = connection.createStatement().executeQuery(paramCreateStatement);

					while(instanceStatement.next()) 
					{
						String[] data = new String[4];
						data[0] = instanceStatement.getString("nome");
						data[1] = instanceStatement.getString("barbeiro");
						data[2] = instanceStatement.getString("preco");
						data[3] = servico.convertServiceDateToABNT(instanceStatement.getString("data"));
						model.addRow(data);
					}
				} 
				catch (ServiceException e) 
				{
					showMessageError(e.getMessage());
				} 
				catch (SQLException e) 
				{
					showMessageError(e.getMessage());
				} 
				catch (ParseException e)
				{
					showMessageError(e.getMessage());
				}

			}
		});
		btnSearchService.setBounds(10, 168, 148, 23);
		contentPane.add(btnSearchService);

		JButton btnSearchBarber = new JButton("Pesquisar Barbeiro");
		btnSearchBarber.addMouseListener(new MouseAdapter() {
			
			// VIEW method that is used to search a Barber
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try
				{
					DoneService servico = new DoneService();
					servico.setBarberName(textField.getText());

					connection = FactoryConnection.getInstance().getConnection();
					
					String paramCreateStatement = "SELECT nome, preco, barbeiro,"
												+ " data FROM servicoprestado WHERE barbeiro = '"
												+ servico.getBarberName() + "' ORDER BY data;";
							
					ResultSet instanceStatement = connection.createStatement().executeQuery(paramCreateStatement);

					while (instanceStatement.next())
					{
						String[] data = new String[4];
						data[0] = instanceStatement.getString("nome");
						data[1] = instanceStatement.getString("barbeiro");
						data[2] = instanceStatement.getString("preco");
						data[3] = servico.convertServiceDateToABNT(instanceStatement.getString("data"));
						model.addRow(data);
					}
				}
				catch (ServiceException e) 
				{
					showMessageError(e.getMessage());
				}
				catch (SQLException e) 
				{
					showMessageError(e.getMessage());
				} 
				catch (ParseException e)
				{
					showMessageError(e.getMessage());
				}

			}
		});
		btnSearchBarber.setBounds(168, 168, 148, 23);
		contentPane.add(btnSearchBarber);

		JButton btnRemove = new JButton("Remover");
		btnRemove.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that is used to remove a service provided
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try 
				{
					String name = (String) table.getValueAt(table.getSelectedRow(), 0);
					String barber = (String) table.getValueAt(table.getSelectedRow(), 1);
					String value = (String) table.getValueAt(table.getSelectedRow(), 2);
					String data = (String) table.getValueAt(table.getSelectedRow(), 3);
					DoneService service = new DoneService();
					service.setServiceName(name);
					service.setBarberName(barber);
					service.setPrice(value);
					service.setDate(data);

					int confirmation = JOptionPane.showConfirmDialog(null,
							"Remover " + name + " da lista?");

					if(confirmation == JOptionPane.YES_OPTION)
					{
						DoneServiceController servicoController = DoneServiceController.getInstance();
						servicoController.deleteProvidedService(service);

						dispose();
						RegisterDoneService frame = new RegisterDoneService();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
					}
					else{
						// Nothing to do
					}
				} 
				catch (ArrayIndexOutOfBoundsException e) 
				{
					showMessageError("Selecione um Serviço para remover");
				} 
				catch (ServiceException e) 
				{
					showMessageError(e.getMessage());
				}
				catch (SQLException e) 
				{
					showMessageError(e.getMessage());
				} 
				catch (ParseException e) 
				{
					showMessageError(e.getMessage());
				}

			}
		});
		btnRemove.setBounds(123, 228, 89, 23);
		contentPane.add(btnRemove);

		JButton btnBack = new JButton("Voltar");
		btnBack.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that is used to return the window to Sign Servico
			@Override
			public void mouseClicked (MouseEvent e) 
			{
				dispose();
				RegisterDoneService frame = new RegisterDoneService();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		btnBack.setBounds(279, 228, 89, 23);
		contentPane.add(btnBack);

		JButton btnSearchForDate = new JButton("Pesquisar Data");
		btnSearchForDate.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that is used to search for services rendered by date
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try
				{
					DoneService service = new DoneService();
					service.setDate(textField.getText());

					connection = FactoryConnection.getInstance().getConnection();
					
					String paramCreateStatement = "Select nome, preco, barbeiro,"
												+ " data from servicoprestado where data = '"
												+ service.getDate() + "' order by data;";
					
					ResultSet instanceStatement = connection.createStatement().executeQuery(paramCreateStatement);

					while (instanceStatement.next()) 
					{
						String[] data = new String[4];
						data[0] = instanceStatement.getString("nome");
						data[1] = instanceStatement.getString("barbeiro");
						data[2] = instanceStatement.getString("preco");
						data[3] = service.convertServiceDateToABNT(instanceStatement.getString("data"));
						model.addRow(data);
					}
				}
				catch (SQLException e) 
				{
					showMessageError(e.getMessage());
				} 
				catch (ParseException e)
				{
					showMessageError(e.getMessage());
				} 
				catch (ServiceException e) 
				{
					showMessageError(e.getMessage());
				}

			}
		});
		btnSearchForDate.setBounds(326, 168, 148, 23);
		contentPane.add(btnSearchForDate);
	}

	// Method that shows an error message, used in the treatment of exceptions class
	private void showMessageError (String informacao) 
	{
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Method of accessing the value of the temporary variable name
	public static String getNameTemporary () 
	{
		return tempNome;
	}
}
