package view;
// Importando bibliotecas
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import model.Barbeiro;
import control.BarberController;
import exception.BarbeiroException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
// Iniciando classe 
@SuppressWarnings ( "serial" )
public class NovoBarbeiro extends JFrame
{

	// Instancia de recursos para a interface
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCpf;
	private JTextField textFieldRg;
	private JTextField textFieldTelefone;
	private JButton botaoSalvar;
	private JButton botaoLimparCampos;
	private JTextField textFieldCadeira;
	private JLabel lblCadeira;
	private JButton botaoVoltar;

	// Metodo main dentro da classe
	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater ( new Runnable () 
		{
			public void run () 
			{
				try {
					NovoBarbeiro frame = new NovoBarbeiro();
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
				} catch ( Exception e ) 
				{
					e.printStackTrace ();
				}
			}// Fim do metodo
		});
	}// Fim do metodo

	// Metodo que chama o metodo inicializarComponentes()
	public NovoBarbeiro() throws ParseException 
	{
		inicializarComponentes ();
	}// Fim do metodo

	// Metodo para dar valores iniciais aos componentes
	public void inicializarComponentes() throws ParseException 
	{
		setTitle ( "Cadastrar Barbeiro" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 474, 253);
		contentPane = new JPanel ();
		contentPane.setBorder( new EmptyBorder ( 5, 5, 5, 5));
		setContentPane ( contentPane );
		getContentPane().setLayout ( null );
		contentPane.setLayout ( null );
		

	
		MaskFormatter mascraFormatTel = new MaskFormatter ( "(##)####-####" );
		MaskFormatter mascraFormatCpf = new MaskFormatter ( "###.###.###-##" );
	

		textFieldNome = new JTextField();
		textFieldNome.setBounds ( 92, 11, 354, 20);
		getContentPane().add ( textFieldNome );
		textFieldNome.setColumns ( 10 );

		JLabel lblNome = new JLabel ( "Nome:" );
		lblNome.setBounds ( 21, 14, 46, 14 );
		getContentPane().add ( lblNome );

		textFieldCpf = new JFormattedTextField ( mascraFormatCpf );
		textFieldCpf.setBounds ( 92, 42, 354, 20 );
		getContentPane().add ( textFieldCpf );
		textFieldCpf.setColumns(10);

		JLabel lblCpf = new JLabel ( "CPF:" );
		lblCpf.setBounds ( 21, 45, 46, 14 );
		getContentPane().add ( lblCpf );

		textFieldRg = new JTextField ();
		textFieldRg.setBounds ( 92, 73, 354, 20 );
		getContentPane().add ( textFieldRg );
		textFieldRg.setColumns ( 10 );

		JLabel lblRg = new JLabel ( "RG:" );
		lblRg.setBounds ( 21, 76, 46, 14 );
		getContentPane().add ( lblRg );

		textFieldTelefone = new JFormattedTextField ( mascraFormatTel );
		textFieldTelefone.setBounds ( 92, 104, 354, 20 );
		getContentPane().add ( textFieldTelefone );
		textFieldTelefone.setColumns ( 10 );

		JLabel lblTelefone = new JLabel ( "Telefone:" );
		lblTelefone.setBounds ( 21, 107, 61, 14 );
		getContentPane().add ( lblTelefone );

		lblCadeira = new JLabel ( "Cadeira:" );
		lblCadeira.setBounds ( 21, 136, 61, 14 );
		contentPane.add ( lblCadeira );
		

	

		botaoSalvar = new JButton ( "Salvar" );
		botaoSalvar.addMouseListener(new MouseAdapter () 
		{
			@Override
			// Inicio do metodo para habilitar o uso da interface com o mouse
			public void mouseClicked ( MouseEvent k ) 
			{
				try {
					Barbeiro barbeiro = new Barbeiro ();
					barbeiro.setNome ( textFieldNome.getText () );
					barbeiro.setCpf ( textFieldCpf.getText () );
					barbeiro.setRg ( textFieldRg.getText () );
					barbeiro.setTelefone ( textFieldTelefone.getText ( ) );
					barbeiro.setCadeira ( textFieldCadeira.getText () );

					BarberController barbeiroController = BarberController.getInstance ();
					barbeiroController.includeBarber ( barbeiro );

					JOptionPane.showMessageDialog (null, "Barbeiro "
							+ textFieldNome.getText ()
							+ " foi cadastrado com sucesso" );

					dispose();
					CadastrarBarbeiro frame = new CadastrarBarbeiro ();
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
				} catch ( BarbeiroException e )
				{
					mostrarMensagemDeErro ( e.getMessage () );
				} catch ( SQLException e ) 
				{
					mostrarMensagemDeErro ( e.getMessage () );
				}
			}// Fim do metodo

		});

		textFieldCadeira = new JTextField ();
		textFieldCadeira.setBounds ( 92, 133, 354, 20 );
		contentPane.add ( textFieldCadeira );
		textFieldCadeira.setColumns ( 10 );
		botaoSalvar.setBounds ( 10, 177, 125, 23 );
		contentPane.add ( botaoSalvar );

		botaoLimparCampos = new JButton ( "Limpar Campos" );
		botaoLimparCampos.addMouseListener ( new MouseAdapter ()
		{
			@Override
			public void mouseClicked ( MouseEvent e )
			{
				textFieldNome.setText ( "" );
				textFieldCpf.setText ( "" );
				textFieldRg.setText ( "" );
				textFieldTelefone.setText ( "" );
				textFieldCadeira.setText ( "" );
			}// Fim do metodo
		});
		botaoLimparCampos.setBounds(308, 177, 138, 23);
		contentPane.add ( botaoLimparCampos );

		botaoVoltar = new JButton ( "Voltar" );
		botaoVoltar.addMouseListener ( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				CadastrarBarbeiro frame = new CadastrarBarbeiro ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}// Fim do metodo
		});
		botaoVoltar.setBounds ( 158, 177, 125, 23 );
		contentPane.add ( botaoVoltar );

	}// Fim do metodo

	// Metodo que volta mensagem de erro caso os metodos para identificar o erro encontre-o
	private void mostrarMensagemDeErro (String informacao ) 
	{
		JOptionPane.showMessageDialog( null, informacao, "Atenção",
				JOptionPane.INFORMATION_MESSAGE );
	}// Fim do metodo
}// Fim da classe
