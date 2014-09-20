package view;
// Importando biblioteca
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import control.AgendaController;
import exception.BarberException;
import model.Agenda;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.text.ParseException;
 
@SuppressWarnings ( "serial" )
public class NovoContato extends JFrame 
{

	// Instancia de recursos para a interface
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldDescricao;

	// Metodo main dentro da classe
	public static void main ( String[] args ) 
	{
		EventQueue.invokeLater ( new Runnable ( ) 
		{
			public void run () 
			{
				try 
				{
					NovoContato frame = new NovoContato ();
					frame.setVisible ( true );
				} catch ( Exception e ) 
				{
					e.printStackTrace ();
				}
			}
		});
	}// Fim do metodo

	// Metodo que chama o metodo inicializarComponentes()
	public NovoContato () throws ParseException 
	{
		inicializarComponentes();
	}// Fim do metodo
	// Metodo para dar valores iniciais aos componentes
	public void inicializarComponentes () throws ParseException 
	{
		setTitle ( "Novo Contato" );
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 450, 300 );
		contentPane = new JPanel ();
		contentPane.setBorder(new EmptyBorder ( 5, 5, 5, 5 ) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );
		MaskFormatter mascraFormatTel = new MaskFormatter("(##)####-####");

		JButton btnSalvar = new JButton( "Salvar" );
		btnSalvar.addMouseListener(new MouseAdapter () 
		{
			@Override// Inicio do metodo para habilitar as funcoes com uso do mouse na interface 
			public void mouseClicked ( MouseEvent e ) 
			{
				try 
				{
					Agenda agenda = new Agenda ();
					agenda.setNome ( textFieldNome.getText () );
					agenda.setTelefone ( textFieldTelefone.getText () );
					agenda.setDescricao ( textFieldDescricao.getText () );

					AgendaController agendaController = AgendaController.getInstance ();
					agendaController.incluir ( agenda );

					JOptionPane.showMessageDialog(null, "Contato "
							+ textFieldNome.getText ()
							+ " foi adicionado com sucesso" );
					
					textFieldNome.setText ( "" );
					textFieldTelefone.setText ( "" );
					textFieldDescricao.setText ( "" );
					
					dispose();
					RegisterPhonebook frame =  new RegisterPhonebook () ;
					frame.setVisible ( true );
					frame.setLocationRelativeTo ( null );
					
				} catch ( SQLException e1 ) 
				{
					mostrarMensagemDeErro ( e1.getMessage () );
				} catch ( BarberException e1 )
				{
					mostrarMensagemDeErro ( e1.getMessage () );
				}
			}// Fim do metodo

		});

		btnSalvar.setBounds ( 26, 218, 109, 33 );
		contentPane.add(btnSalvar);

		JButton btnVoltar = new JButton ( "Voltar" );
		btnVoltar.addMouseListener ( new MouseAdapter () 
		{
			@Override
			// Inicio do metodo para habilitar as funcoes com uso do mouse na interface 
			public void mouseClicked ( MouseEvent e ) 
			{
				dispose();
				RegisterPhonebook frame = new RegisterPhonebook ();
				frame.setVisible ( true );
				frame.setLocationRelativeTo ( null );
			}// Fim do metodo
		});

		btnVoltar.setBounds(166, 218, 100, 33);
		contentPane.add(btnVoltar);

		// Opcao para limpar campos 
		JButton btnLimparCampos = new JButton( "Limpar Campos" );
		btnLimparCampos.addMouseListener( new MouseAdapter () 
		{
			@Override
			public void mouseClicked ( MouseEvent e ) 
			{
				textFieldNome.setText ( "" );
				textFieldTelefone.setText ( "" );
				textFieldDescricao.setText ( "" );
			}// Fim do metodo
		});
		// Opcao para limpar campos 
		btnLimparCampos.setBounds ( 287, 218, 125, 33 );
		contentPane.add ( btnLimparCampos );

		textFieldNome = new JTextField();
		textFieldNome.setBounds(85, 23, 327, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JFormattedTextField ( mascraFormatTel );
		textFieldTelefone.setBounds(85, 67, 327, 20);
		contentPane.add ( textFieldTelefone );
		textFieldTelefone.setColumns(10);

		textFieldDescricao = new JTextField ();
		textFieldDescricao.setBounds(85, 117, 327, 44);
		contentPane.add ( textFieldDescricao );
		textFieldDescricao.setColumns(10);

		JLabel lblNome = new JLabel ( "Nome:" );
		lblNome.setBounds ( 22, 26, 46, 14 );
		contentPane.add ( lblNome );

		JLabel lblTelefone = new JLabel ( "Telefone:" );
		lblTelefone.setBounds ( 22, 70, 64, 14 );
		contentPane.add ( lblTelefone );

		JLabel lblDescricao = new JLabel ( "Descri\u00E7\u00E3o:" );
		lblDescricao.setBounds ( 22, 117, 64, 14 );
		contentPane.add ( lblDescricao );
	}// Fim do metodo
	// Metodo que volta mensagem de erro caso os metodos para identificar o erro encontre-o
	private void mostrarMensagemDeErro( String informacao ) 
	{
		JOptionPane.showMessageDialog( null, informacao, "Aten��o",
				JOptionPane.INFORMATION_MESSAGE );
	}// Fim do metodo
}// Fim da classe
