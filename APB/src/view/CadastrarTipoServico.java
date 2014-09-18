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
import exception.ServicoException;

@SuppressWarnings("serial")
public class CadastrarTipoServico extends JFrame 
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
			
			// Método que inicializa a janela de cadastro de tipo de serviço
			public void run ()
			{
				try
				{
					CadastrarTipoServico frame = new CadastrarTipoServico();
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
	
	// Construtor dos componentes da janela de Cadastro de tipo de serviço
	public CadastrarTipoServico() 
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
				new String[] { "ServiÃ§o", "Valor" });
		final JTable table = new JTable(modelo);
		try 
		{
			ServiceTypeController servicoController = ServiceTypeController.getInstance();
			ServiceType servico= new ServiceType();
			ResultSet rs = servicoController.showRegistredServiceTypes(servico);
			while (rs.next())
			{
				String[] dados = new String[5];
				dados[0] = rs.getString("nome");
				dados[1] = rs.getString("preco");
				modelo.addRow(dados);
			}
		}
		catch (SQLException e)
		{
			mostrarMensagemDeErro(e.getMessage());
		}

		scrollPane.setViewportView(table);

		JButton btnNovo = new JButton("Novo");
		btnNovo.addMouseListener(new MouseAdapter() 
		{
			
			// Método da VIEW que chama a janela de NovoTipoServico para realizar um cadastro
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
			
			// Método da VIEW que chama a janela de AlterarTipoServico para realizar uma alteração
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try 
				{
					ServiceType.setTemporaryName(modelo.getValueAt(table.getSelectedRow(), 0).toString());
					ModifyServiceType frame = new ModifyServiceType();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} 
				catch (ServicoException e1) 
				{
					mostrarMensagemDeErro(e1.getMessage());
				} 
				catch (ArrayIndexOutOfBoundsException e1)
				{
					mostrarMensagemDeErro("Selecione um Tipo de ServiÃ§o");
				}
			}
		});
		btnAlterar.setBounds(380, 58, 94, 23);
		contentPane.add(btnAlterar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() 
		{
			
			// Método da VIEW que realiza uma exclusão de um Tipo de Serviço
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String nome = (String) table.getValueAt(table.getSelectedRow(),	0);
				ServiceType tipoServico = new ServiceType();
				
				try 
				{	
					tipoServico.setServiceTypeName(nome);
				} 
				catch (ServicoException e1) 
				{
					e1.printStackTrace();
				}

				int confirmacao = JOptionPane.showConfirmDialog(null,
																"Remover " + nome + " da lista?");

				if(confirmacao == JOptionPane.YES_OPTION) 
				{
					ServiceTypeController tipoServicoController = ServiceTypeController.getInstance();
					try 
					{
						tipoServicoController.deleteServiceType(tipoServico);
					}
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					
					dispose();
					CadastrarTipoServico frame = new CadastrarTipoServico();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				}
				else
				{
					// Nothing to do
				}
			}
		});
		btnRemover.setBounds(380, 92, 94, 23);
		contentPane.add(btnRemover);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(380, 228, 94, 23);
		btnVoltar.addActionListener(new ActionListener() 
		{
			// Método da VIEW que volta para a janela administrativa
			public void actionPerformed (ActionEvent arg0)
			{
				dispose();
				Administrative frame = new Administrative();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		contentPane.add(btnVoltar);
	}
	
	// Método de acesso para recebimento de um nome temporario
	public static String getNomeTemp() 
	{
		return nomeTemp;
	}
	
	// Método que mostra uma mensagem de erro, utilizado no tratamento das exceções da classe
	private void mostrarMensagemDeErro(String informacao) 
	{
		JOptionPane.showMessageDialog(null, informacao, "AtenÃ§Ã£o",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
