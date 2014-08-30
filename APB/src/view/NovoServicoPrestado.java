package view;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ServicoPrestado;
import control.ServicoPrestadoController;
import dao.FactoryConnection;
import exception.ServicoException;

@SuppressWarnings("serial")
public class NovoServicoPrestado extends JFrame {

	private JPanel contentPane;
	private JTextField textValor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NovoServicoPrestado frame = new NovoServicoPrestado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// Construtor
	public NovoServicoPrestado() {

		// Define as descrições do painel
		setTitle("Criar nova presta\u00E7\u00E3o de servi\u00E7o");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 214);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Adiciona o label 'Serviço' ao painel
		JLabel lblServico = new JLabel("Servi\u00E7o:");
		lblServico.setBounds(27, 25, 46, 14);
		contentPane.add(lblServico);
		
		// Adiciona o label 'Realizado por:' ao painel
		JLabel lblRealizadoPor = new JLabel("Realizado por:");
		lblRealizadoPor.setBounds(27, 56, 92, 14);
		contentPane.add(lblRealizadoPor);
		
		// Adiciona o label 'Preço' ao painel 
		JLabel lblPreco = new JLabel("Pre\u00E7o (R$):");
		lblPreco.setBounds(27, 87, 71, 14);
		contentPane.add(lblPreco);
		
		// Adiciona um campo de texto ao painel
		textValor = new JTextField();
		textValor.setColumns(10);
		textValor.setBounds(129, 84, 114, 20);
		contentPane.add(textValor);
		
		// Adiciona o ComboBox 'Selecione um barbeiro' ao painel
		final JComboBox comboBoxBarbeiro = new JComboBox();
		comboBoxBarbeiro.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione um barbeiro" }));
		comboBoxBarbeiro.setBounds(129, 53, 289, 20);
		contentPane.add(comboBoxBarbeiro);
		
		// Adiciona o ComboBox 'Selecione o tipo de servico' ao painel
		final JComboBox comboBoxServico = new JComboBox();
		comboBoxServico.addItemListener(new ItemListener() {
			
			// Lê do banco de dados as informações do ComboBox
			public void itemStateChanged(ItemEvent arg0) {
				Connection connection;
				if (comboBoxServico.getSelectedIndex() != 0)
					try {
						String[] nome = comboBoxServico.getSelectedItem()
								.toString().split(" - ");
						connection = FactoryConnection.getInstance()
								.getConnection();
						java.sql.PreparedStatement pst1 = connection
								.prepareStatement("SELECT preco FROM tipoServico WHERE nome = \""
										+ nome[1] + "\";");
						ResultSet rs1 = pst1.executeQuery();
						rs1.next();

						textValor.setText(rs1.getString("preco"));
					} catch (SQLException e) {
						mostrarMensagemDeErro(e.getMessage());
					}
			}

		});
		comboBoxServico.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione um tipo de servi\u00E7o" }));
		comboBoxServico.setMaximumRowCount(4);
		comboBoxServico.setBounds(129, 22, 289, 20);
		contentPane.add(comboBoxServico);
		
		// Captura exceções SQL caso ocorram, e mostra mensagem de erro.
		try {
			int cont = 0;
			Connection connection = FactoryConnection.getInstance()
					.getConnection();
			java.sql.PreparedStatement pst = connection
					.prepareStatement("SELECT nome, cadeira FROM barbeiro ORDER BY cadeira;");
			java.sql.PreparedStatement pst2 = connection
					.prepareStatement("SELECT nome FROM tiposervico;");
			ResultSet rs = pst.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
			
			// Lista os nomes dos barbeiros por cadeira
			while (rs.next()) {
				String nome = rs.getString("nome");
				String cadeira = rs.getString("cadeira");
				comboBoxBarbeiro.addItem(cadeira + " - " + nome);
			}
			
			// Lista os tipos de serviço
			while (rs2.next()) {
				cont++;
				String nome = rs2.getString("nome");
				comboBoxServico.addItem(cont + " - " + nome);
			}

		} catch (SQLException e) {
			mostrarMensagemDeErro(e.getMessage());
		}
		
		// Adiciona o botão 'Salvar' ao painel
		JButton botaoSalvar = new JButton("Salvar");
		botaoSalvar.addMouseListener(new MouseAdapter() {
			@Override
			// Ação do botão
			public void mouseClicked(MouseEvent arg0) {
				
				// Captura excessões SQL, de Parse e do tipo Serviço Exception
				try {
					
					// Verifica se foi selecionado algum tipo de serviço
					if (comboBoxServico.getSelectedIndex() == 0)
						JOptionPane.showMessageDialog(null,
								"VocÃª deve selecionar um tipo de serviÃ§o.");
					// Verifica se foi selecionado algum barbeiro
					else if (comboBoxBarbeiro.getSelectedIndex() == 0)
						JOptionPane.showMessageDialog(null,
								"VocÃª deve selecionar um barbeiro.");
					else {
						String data;
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");
						data = sdf.format(d);

						String[] nome = comboBoxServico.getSelectedItem()
								.toString().split(" - ");
						String[] barbeiro = comboBoxBarbeiro.getSelectedItem()
								.toString().split(" - ");

						ServicoPrestado servico_prestado = new ServicoPrestado();

						servico_prestado.setNomeBarbeiro(barbeiro[1]);
						servico_prestado.setNomeServico(nome[1]);
						servico_prestado.setPreco(textValor.getText());
						servico_prestado.setData(data);
						
						// Faz o tratamento dos dados na classe ServicoPrestadoController
						ServicoPrestadoController servicoController = ServicoPrestadoController
								.getInstance();
						servicoController.inserir(servico_prestado);

						JOptionPane.showMessageDialog(null,
								"ServiÃ§o criado com sucesso");

						comboBoxBarbeiro.setSelectedIndex(0);
						comboBoxServico.setSelectedIndex(0);

						textValor.setText("");
					}
				} catch (ServicoException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (SQLException e) {
					mostrarMensagemDeErro(e.getMessage());
				} catch (ParseException e) {
					mostrarMensagemDeErro(e.getMessage());
				}

			}
		});
		botaoSalvar.setBounds(27, 129, 89, 23);
		contentPane.add(botaoSalvar);
		
		// Adiciona o botão 'Limpar Campos' ao painel
		JButton botaoLimparCampos = new JButton("Limpar Campos");
		botaoLimparCampos.addMouseListener(new MouseAdapter() {
			@Override
			// Ação do botão
			public void mouseClicked(MouseEvent arg0) {
				
				// "Apaga" os campos
				textValor.setText("");
				comboBoxBarbeiro.setSelectedIndex(0);
				comboBoxServico.setSelectedIndex(0);
			}
		});
		botaoLimparCampos.setBounds(152, 129, 148, 23);
		contentPane.add(botaoLimparCampos);
		
		// Adiciona o botão 'Voltar' ao painel
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.addMouseListener(new MouseAdapter() {
			@Override
			// Ação do botão
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				CadastrarServicoPrestado frame = new CadastrarServicoPrestado();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		botaoVoltar.setBounds(329, 129, 89, 23);
		contentPane.add(botaoVoltar);
	}
	
	// Fim construtor
	
	// Mostra uma mensagem de erro de acordo com uma dada informação
	private void mostrarMensagemDeErro(String informacao) {
		JOptionPane.showMessageDialog(null, informacao, "AtenÃ§Ã£o",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
