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
		inicializarComponentes();
	}

	// Builder components of the search window service
	public void inicializarComponentes ()
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

		final DefaultTableModel modelo = new DefaultTableModel(null,
																new String[] { "Serviço", "Realizado por", "Valor", "Data" });
		final JTable table = new JTable(modelo);
		scrollPane.setViewportView(table);

		textField = new JTextField();
		textField.setBounds(82, 137, 392, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(20, 137, 66, 14);
		contentPane.add(lblPesquisar);

		JButton btnPesquisarServico = new JButton("Pesquisar Serviço");
		btnPesquisarServico.addActionListener(new ActionListener() {
			
			// VIEW method that is used to search a Service Provided
			public void actionPerformed (ActionEvent arg0) 
			{
				try 
				{
					DoneService servico = new DoneService();
					servico.setServiceName(textField.getText());

					connection = FactoryConnection.getInstance().getConnection();
					ResultSet instanceStatement = connection.createStatement().executeQuery(
																			"SELECT nome, preco, barbeiro,"
																			+ " data FROM servicoprestado WHERE nome = '"
																			+ servico.getServiceName() + "' ORDER BY data;");

					while(instanceStatement.next()) 
					{
						String[] data = new String[4];
						data[0] = instanceStatement.getString("nome");
						data[1] = instanceStatement.getString("barbeiro");
						data[2] = instanceStatement.getString("preco");
						data[3] = servico.convertServiceDateToABNT(instanceStatement.getString("data"));
						modelo.addRow(data);
					}
				} 
				catch (ServiceException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (SQLException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (ParseException e)
				{
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarServico.setBounds(10, 168, 148, 23);
		contentPane.add(btnPesquisarServico);

		JButton btnPesquisarBarbeiro = new JButton("Pesquisar Barbeiro");
		btnPesquisarBarbeiro.addMouseListener(new MouseAdapter() {
			
			// VIEW method that is used to search a Barber
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try
				{
					DoneService servico = new DoneService();
					servico.setBarberName(textField.getText());

					connection = FactoryConnection.getInstance().getConnection();
					ResultSet rs = connection.createStatement().executeQuery(
																			"SELECT nome, preco, barbeiro,"
																			+ " data FROM servicoprestado WHERE barbeiro = '"
																			+ servico.getBarberName() + "' ORDER BY data;");

					while (rs.next())
					{
						String[] dados = new String[4];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("barbeiro");
						dados[2] = rs.getString("preco");
						dados[3] = servico.convertServiceDateToABNT(rs.getString("data"));
						modelo.addRow(dados);
					}
				}
				catch (ServiceException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				}
				catch (SQLException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (ParseException e)
				{
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarBarbeiro.setBounds(168, 168, 148, 23);
		contentPane.add(btnPesquisarBarbeiro);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that is used to remove a service provided
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try 
				{
					String nome = (String) table.getValueAt(table.getSelectedRow(), 0);
					String barbeiro = (String) table.getValueAt(table.getSelectedRow(), 1);
					String valor = (String) table.getValueAt(table.getSelectedRow(), 2);
					String data = (String) table.getValueAt(table.getSelectedRow(), 3);
					DoneService servico = new DoneService();
					servico.setServiceName(nome);
					servico.setBarberName(barbeiro);
					servico.setPrice(valor);
					servico.setDate(data);

					int confirmacao = JOptionPane.showConfirmDialog(null,
							"Remover " + nome + " da lista?");

					if(confirmacao == JOptionPane.YES_OPTION)
					{
						DoneServiceController servicoController = DoneServiceController.getInstance();
						servicoController.deleteProvidedService(servico);

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
					mostrarMensagemDeErro("Selecione um Serviço para remover");
				} 
				catch (ServiceException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				}
				catch (SQLException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (ParseException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnRemover.setBounds(123, 228, 89, 23);
		contentPane.add(btnRemover);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() 
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
		btnVoltar.setBounds(279, 228, 89, 23);
		contentPane.add(btnVoltar);

		JButton btnPesquisarData = new JButton("Pesquisar Data");
		btnPesquisarData.addMouseListener(new MouseAdapter() 
		{
			
			// VIEW method that is used to search for services rendered by date
			@Override
			public void mouseClicked (MouseEvent arg0) 
			{
				try
				{
					DoneService servico = new DoneService();
					servico.setDate(textField.getText());

					connection = FactoryConnection.getInstance().getConnection();
					ResultSet rs = connection.createStatement().executeQuery(
																			"Select nome, preco, barbeiro,"
																			+ " data from servicoprestado where data = '"
																			+ servico.getDate() + "' order by data;");

					while (rs.next()) 
					{
						String[] dados = new String[4];
						dados[0] = rs.getString("nome");
						dados[1] = rs.getString("barbeiro");
						dados[2] = rs.getString("preco");
						dados[3] = servico.convertServiceDateToABNT(rs.getString("data"));
						modelo.addRow(dados);
					}
				}
				catch (SQLException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (ParseException e)
				{
					mostrarMensagemDeErro(e.getMessage());
				} 
				catch (ServiceException e) 
				{
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		btnPesquisarData.setBounds(326, 168, 148, 23);
		contentPane.add(btnPesquisarData);
	}

	// Method that shows an error message, used in the treatment of exceptions class
	private void mostrarMensagemDeErro (String informacao) 
	{
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	// Method of accessing the value of the temporary variable name
	public static String getTempNome () 
	{
		return tempNome;
	}
}
