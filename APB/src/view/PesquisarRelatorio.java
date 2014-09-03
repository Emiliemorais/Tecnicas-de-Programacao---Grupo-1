package view;

import java.awt.Checkbox;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import exception.RelatorioException;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;

@SuppressWarnings("serial")
public class PesquisarRelatorio extends JFrame
{
	
	public static int tipoBusca = 0;
	public static String servico = "servico";
	public static String barbeiro = "barbeiro";
	public static String dataInicial = "dataInicial";
	public static String dataFinal = "dataFinal";
	
	private JPanel contentPane;
	private JTextField txtDataInicial;
	private JTextField txtDataFinal;
	private JTextField txtBarbeiro;
	private JTextField txtServico;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater
		(
			new Runnable()
			{
			
				// Método que inicializa a janela de pesquisa de relatório
				public void run() 
				{
					try
					{
						PesquisarRelatorio frame = new PesquisarRelatorio();
						frame.setVisible(true);
					} 
					catch (Exception e) 
					{
						mostrarMensagemDeErro( e.getMessage() );
					}
				}
			}
		);
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	// Construtor
	public PesquisarRelatorio() throws ParseException
	{
		setTitle("Tipo de Pesquisa do Relat\u00F3rio");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 300);
		contentPane = new JPanel();
		contentPane.setBorder( new LineBorder( new Color(0, 0, 0) ) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final MaskFormatter mascaraFormatoData = new MaskFormatter("##/##/####");

		JPanel panelData = new JPanel();
		panelData.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Por Data",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelData.setBounds(10, 11, 221, 97);
		contentPane.add(panelData);
		panelData.setLayout(null);

		txtDataInicial = new JFormattedTextField(mascaraFormatoData);
		txtDataInicial.setEnabled(false);
		txtDataInicial.setBounds(10, 66, 94, 20);
		panelData.add(txtDataInicial);
		txtDataInicial.setColumns(10);

		txtDataFinal = new JFormattedTextField(mascaraFormatoData);
		txtDataFinal.setEnabled(false);
		txtDataFinal.setBounds(114, 66, 94, 20);
		panelData.add(txtDataFinal);
		txtDataFinal.setColumns(10);

		JLabel lblDataInicial = new JLabel("Data Inicial");
		lblDataInicial.setBounds(10, 53, 86, 14);
		panelData.add(lblDataInicial);

		final Checkbox checkPorData = new Checkbox("Ativar");
		checkPorData.addItemListener(new ItemListener() 
		{
			
			/*
			 *  Método que realiza a ativação da pesquisa de um relatório por data,
			 *   se o usuário ativar essa opção
			 */
			public void itemStateChanged(ItemEvent e)
			{
				if ( checkPorData.getState() == false )
				{
					txtDataInicial.setEnabled(false);
					txtDataFinal.setEnabled(false);
				} 
				else 
				{
					txtDataInicial.setEnabled(true);
					txtDataFinal.setEnabled(true);
				}
			}
		});
		checkPorData.setBounds(6, 23, 71, 23);
		panelData.add(checkPorData);

		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setBounds(114, 53, 71, 14);
		panelData.add(lblDataFinal);

		JPanel panelBarbeiro = new JPanel();
		panelBarbeiro.setLayout(null);
		panelBarbeiro.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Por Barbeiro",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelBarbeiro.setBounds(10, 119, 221, 62);
		contentPane.add(panelBarbeiro);

		txtBarbeiro = new JTextField();
		txtBarbeiro.setText("Nome do barbeiro");
		txtBarbeiro.setEnabled(false);
		txtBarbeiro.setColumns(10);
		txtBarbeiro.setBounds(71, 23, 140, 20);
		panelBarbeiro.add(txtBarbeiro);

		final Checkbox checkBarbeiro = new Checkbox("Ativar");
		checkBarbeiro.addItemListener(new ItemListener()
		{
			
			/*
			 *  Método que realiza a ativação da pesquisa de um relatório por data,
			 *   se o usuário ativar essa opção
			 */
			public void itemStateChanged(ItemEvent e)
			{
				if ( checkBarbeiro.getState() == false )
				{
					txtBarbeiro.setEnabled(false);
					txtBarbeiro.setText("Nome do barbeiro");
				} 
				else 
				{
					txtBarbeiro.setEnabled(true);
					txtBarbeiro.setText("");
				}
			}
		});

		checkBarbeiro.setBounds(6, 23, 59, 23);
		panelBarbeiro.add(checkBarbeiro);

		JPanel panelServico = new JPanel();
		panelServico.setLayout(null);
		panelServico.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Por Servi\u00E7o",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelServico.setBounds(10, 189, 221, 62);
		contentPane.add(panelServico);

		txtServico = new JTextField();
		txtServico.setText("Tipo de Servi\u00E7o");
		txtServico.setEnabled(false);
		txtServico.setColumns(10);
		txtServico.setBounds(71, 23, 140, 20);
		panelServico.add(txtServico);

		final Checkbox checkServico = new Checkbox("Ativar");
		checkServico.addItemListener(new ItemListener()
		{
			/*
			 *  Método que realiza a ativação da pesquisa de um relatório por tipo de serviço,
			 *   se o usuário ativar essa opção
			 */
			public void itemStateChanged(ItemEvent e)
			{
				if ( checkServico.getState() == false ) 
				{
					txtServico.setEnabled(false);
					txtServico.setText("Tipo de Servi\u00E7o");
				} 
				else
				{
					txtServico.setEnabled(true);
					txtServico.setText("");
				}
			}
		});
		checkServico.setBounds(6, 23, 59, 23);
		panelServico.add(checkServico);

		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.addMouseListener(new MouseAdapter()
		{
			
			 // Método que recebe os dados digitados pelo usuario para pesquisa de um relatorio
			@Override
			public void mouseClicked(MouseEvent arg0)
			{

				if ( txtBarbeiro.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "Digite o nome do barbeiro.");
				} 
				else if ( txtServico.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "Digite um tipo de serviço.");
				} 
				else if ( txtDataFinal.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "Digite uma data final");
				} 
				else if ( txtDataInicial.getText().equals("") )
				{
					JOptionPane.showMessageDialog(null, "Digite uma data inicial");
				} 
				else 
				{

					if (checkBarbeiro.getState() == true
							&& checkPorData.getState() == false
							&& checkServico.getState() == false)
					{
						barbeiro = txtBarbeiro.getText();
						tipoBusca = 1;
					}
					else
					{
						// Nothing to do
					}

					if (checkBarbeiro.getState() == true
							&& checkPorData.getState() == false
							&& checkServico.getState() == true) 
					{
						barbeiro = txtBarbeiro.getText();
						servico = txtServico.getText();
						tipoBusca = 2;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkBarbeiro.getState() == true
							&& checkPorData.getState() == true
							&& checkServico.getState() == false) 
					{
						barbeiro = txtBarbeiro.getText();
						dataInicial = txtDataInicial.getText();
						dataFinal = txtDataFinal.getText();
						tipoBusca = 3;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkBarbeiro.getState() == true
							&& checkPorData.getState() == true
							&& checkServico.getState() == true) 
					{
						barbeiro = txtBarbeiro.getText();
						dataInicial = txtDataInicial.getText();
						dataFinal = txtDataFinal.getText();
						servico = txtServico.getText();
						tipoBusca = 4;
					}
					else
					{
						//Nothing to do
					}
					
					if (checkBarbeiro.getState() == false
							&& checkPorData.getState() == false
							&& checkServico.getState() == true)
					{
						servico = txtServico.getText();
						tipoBusca = 5;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkBarbeiro.getState() == false
							&& checkPorData.getState() == true
							&& checkServico.getState() == true)
					{
						dataInicial = txtDataInicial.getText();
						dataFinal = txtDataFinal.getText();
						servico = txtServico.getText();
						tipoBusca = 6;
					}
					else
					{
						// Nothing to do
					}
					
					if (checkBarbeiro.getState() == false
							&& checkPorData.getState() == true
							&& checkServico.getState() == false)
					{
						dataInicial = txtDataInicial.getText();
						dataFinal = txtDataFinal.getText();
						tipoBusca = 7;
					}
					else
					{
						// Nothing to do
					}
					
				}

				if (checkBarbeiro.getState() == false
						&& checkPorData.getState() == false
						&& checkServico.getState() == false)
				{
					JOptionPane.showMessageDialog(null, "Selecione uma opção de busca");
				} 
				else
				{
					// Nothing to do
				}
				
				if (tipoBusca != 0)
				{
					try 
					{
						VisualizarRelatorios frame = new VisualizarRelatorios();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						dispose();
					}
					catch (SQLException e)
					{
						mostrarMensagemDeErro( e.getMessage() );
					}
					catch (RelatorioException e) 
					{
						mostrarMensagemDeErro( e.getMessage() );
					}
					catch (NullPointerException e)
					{
						mostrarMensagemDeErro( e.getMessage() );
					}
					catch (ParseException e) 
					{
						mostrarMensagemDeErro( e.getMessage() );
					}

				}
				else
				{
					// Nothing to do
				}

			}
		});
		btnConcluir.setBounds(241, 11, 105, 62);
		contentPane.add(btnConcluir);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() 
		{
			
			// Método que mostra a janela de vizualização de relatorios
			@Override
			public void mouseClicked(MouseEvent e)
			{
				try
				{
					VisualizarRelatorios frame = new VisualizarRelatorios();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				} 
				catch (SQLException e1)
				{
					mostrarMensagemDeErro( e1.getMessage() );
				} 
				catch (RelatorioException e1)
				{
					mostrarMensagemDeErro( e1.getMessage() );
				} 
				catch (NullPointerException e1)
				{
					mostrarMensagemDeErro( e1.getMessage() );
				} 
				catch (ParseException e1) 
				{
					mostrarMensagemDeErro( e1.getMessage() );
				}
			}
		});
		btnVoltar.setBounds(241, 228, 105, 23);
		contentPane.add(btnVoltar);
	}
	
	// Fim Construtor
	
	public boolean action(Event evento, Object arg)
	{
		return false;
	}
	
	// Método que mostra uma mensagem de erro, utilizado no tratamento das exceções da classe
	private static void mostrarMensagemDeErro(String informacao)
	{
		JOptionPane.showMessageDialog(null, informacao, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}

}
