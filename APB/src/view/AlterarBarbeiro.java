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
import exception.BarbeiroException;

@SuppressWarnings("serial")
public class AlterarBarbeiro extends JFrame 
{

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldRg;
	private JTextField textFieldTelefone;
	private JTextField textFieldCadeira;
	private String nome;
	private JTextField textFieldCpf;

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
						AlterarBarbeiro frame = new AlterarBarbeiro();
						frame.setVisible(true);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}
		);
	}
	
	// Construtor 
	public AlterarBarbeiro() 
	{
		
		// Define as descrições do painel 
		setTitle("Alterar Barbeiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 283);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5, 5, 5, 5) );
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Adiciona o campo de texto 'Nome' ao painel
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(92, 11, 354, 20);
		contentPane.add(textFieldNome);
		
		// Adiciona o label 'Nome' ao painel
		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(21, 14, 46, 14);
		contentPane.add(labelNome);
		
		// Adiciona o label 'CPF' ao painel
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(21, 43, 31, 14);
		contentPane.add(lblCpf);
		
		// Adiciona o campo de texto 'CPF' ao painel
		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(92, 40, 354, 20);
		contentPane.add(textFieldCpf);
		textFieldCpf.setColumns(10);
		
		// Adiciona o campo de texto 'Rg' ao painel
		textFieldRg = new JTextField();
		textFieldRg.setColumns(10);
		textFieldRg.setBounds(92, 71, 354, 20);
		contentPane.add(textFieldRg);
		
		// Adiciona o label 'Rg' ao painel
		JLabel labelRg = new JLabel("RG:");
		labelRg.setBounds(21, 77, 46, 14);
		contentPane.add(labelRg);
		
		// Adiciona o campo de texto 'Telefone' ao painel
		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(92, 102, 354, 20);
		contentPane.add(textFieldTelefone);
		
		// Adiciona o label 'Telefone' ao painel
		JLabel labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(21, 108, 61, 14);
		contentPane.add(labelTelefone);
		
		// Adiciona o campo de texto 'Cadeira' ao painel
		textFieldCadeira = new JTextField();
		textFieldCadeira.setColumns(10);
		textFieldCadeira.setBounds(92, 133, 354, 20);
		contentPane.add(textFieldCadeira);
		
		// Adiciona o label 'Cadeira' ao painel
		JLabel labelCadeira = new JLabel("Cadeira:");
		labelCadeira.setBounds(21, 139, 61, 14);
		contentPane.add(labelCadeira);

		// Testa se não há disparo de exceções com os dados informados
		try
		{
			Barber barber = new Barber();
			BarberController barbeiroController = BarberController.getInstance();
			barber.setBarberName( Barber.getTemporaryName() );
			
			ResultSet rs = barbeiroController.searchBarberByName(barber);
			
			while ( rs.next() ) 
			{
				textFieldNome.setText( rs.getString("nome") );
				textFieldCpf.setText( rs.getString("cpf") );
				textFieldRg.setText( rs.getString("rg") );
				textFieldTelefone.setText( rs.getString("telefone") );
				textFieldCadeira.setText( rs.getString("cadeira") );
			}
			nome = textFieldCpf.getText();
		} 
		catch (SQLException e)
		{
			mostrarMensagemDeErro( e.getMessage() );
		} 
		catch (BarbeiroException e)
		{
			mostrarMensagemDeErro( e.getMessage() );
		}
		
		// Cria um botão 'Salvar' e o adiciona no painel
		JButton buttonSalvar = new JButton("Salvar");
		buttonSalvar.addActionListener(new ActionListener() 
		{
			
			// Define ação do botão
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					Barber barber = new Barber();
					barber.setBarberName( textFieldNome.getText() );
					barber.setBarberCpf( textFieldCpf.getText() );
					barber.setBarberRg( textFieldRg.getText() );
					barber.setBarberTelephone( textFieldTelefone.getText() );
					barber.setBarberChair( textFieldCadeira.getText() );

					BarberController barbeiroController = BarberController.getInstance();
					barbeiroController.alterar(nome, barber);

					JOptionPane.showMessageDialog(null, "Barbeiro "
												  		 + textFieldNome.getText()
												  		 + " foi alterado com sucesso");

					dispose();
					CadastrarBarbeiro frame = new CadastrarBarbeiro();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

				}
				catch (BarbeiroException e1)
				{
					mostrarMensagemDeErro( e1.getMessage() );
				} 
				catch (SQLException k) 
				{
					mostrarMensagemDeErro( k.getMessage() );
				}
			}
		});
		buttonSalvar.setBounds(10, 196, 125, 23);
		contentPane.add(buttonSalvar);
		
		// Cria um botão 'Limpar Campos' e o adiciona no painel
		JButton buttonLimpar = new JButton("Limpar Campos");
		buttonLimpar.addActionListener(new ActionListener()
		{
			
			// Define a ação do botão
			public void actionPerformed(ActionEvent e)
			{
				textFieldNome.setText("");
				textFieldRg.setText("");
				textFieldTelefone.setText("");
				textFieldCadeira.setText("");
			}
		});
		buttonLimpar.setBounds(308, 196, 138, 23);
		contentPane.add(buttonLimpar);
		
		// Cria um botão 'Voltar' e o adiciona no painel
		JButton buttonVoltar = new JButton("Voltar");
		buttonVoltar.addActionListener(new ActionListener()
		{
			
			// Define a ação do botão
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				CadastrarBarbeiro frame = new CadastrarBarbeiro();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
			}
		});
		buttonVoltar.setBounds(158, 196, 125, 23);
		contentPane.add(buttonVoltar);
				
	}
	// Fim do construtor
	
	// Mostra uma mensagem de erro de acordo com uma dada informação
	private void mostrarMensagemDeErro(String informacao)
	{
		JOptionPane.showMessageDialog(null, informacao, "Atenï¿½ï¿½o",
									  JOptionPane.INFORMATION_MESSAGE);
	}
	
}
