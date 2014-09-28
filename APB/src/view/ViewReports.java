package view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import model.Report;

import control.ReportController;

import view.SearchReport;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import exception.ReportException;

@SuppressWarnings("serial")
public class ViewReports extends JFrame 
{

	private JPanel contentPane;
	private double valueTotalBuy = 0;
	private String numberCliente;
	List<String> typeService = new ArrayList<String>();
	private int counterService = 0;
	private int numberTotalService = 0;
	private double valueTotalService = 0;
	private double valueTotalPay = 0;
	private double valueTotalBuyPay = 0;

	DecimalFormat decimal = new DecimalFormat("##0.00");

	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			
			// M�todo que mostra a janela de Vizualiza��o dos relat�rios
			public void run () 
			{
				try 
				{
					ViewReports frame = new ViewReports();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					showErrorMessage(e.getMessage());
				}
			}
		});
	}

	// Construtor dos componentes da janela VIzualizar Relatorios
	public ViewReports() throws SQLException, ReportException,
			NullPointerException, ParseException 
	{
		setTitle("Relat\u00F3rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 660, 486);
		contentPane.add(scrollPane);

		final DefaultTableModel modelo = new DefaultTableModel(null,
				new String[] { "Nome do Serviço", "Quantidade", "Valor total",
							   "Valor recebido" }) 
		{
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };
			
			// M�todo que verifica se a c�lula pode ser alterada
			public boolean isCellEditable (int row, int column) 
			{
				return columnEditables[column];
			}
		};

		final JTable table = new JTable(modelo);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);

		ReportController relatorioController = ReportController
				.getInstance();

		Report relatorio = new Report();

		if(SearchReport.searchType == 1) 
		{

			relatorio.setBarberName(SearchReport.barber);

			ResultSet instanceStatement = relatorioController.searchByBarber(relatorio);

			while (instanceStatement.next()) {

				if (typeService.contains(instanceStatement.getString("nome")) == false)
				{
					typeService.add(instanceStatement.getString("nome"));
					counterService++;
				}
			}

			for(int i=0; i<counterService; i++)
			{
				instanceStatement.beforeFirst();
				while ( instanceStatement.next() )
				{
					if( typeService.get(i).equals( instanceStatement.getString("nome") ) ) 
					{
						numberCliente = instanceStatement.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						.replace(".", ",")
						.valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						.replace(".", ",")
						.valueOf(decimal.format(valueTotalPay));
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				modelo.addRow(dados);

				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 2 )
		{

			relatorio.setBarberName(SearchReport.barber);
			relatorio.setServiceType(SearchReport.service);

			ResultSet rs = relatorioController
						   .searchByBarberAndService(relatorio);

			while ( rs.next() )
			{

				if( typeService.contains( rs.getString("nome") ) == false )
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
			{
				rs.beforeFirst();
				while ( rs.next() )
				{
					if( typeService.get(i).equals( rs.getString("nome") ) ) 
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 3 ) 
		{

			relatorio.setBarberName(SearchReport.barber);
			relatorio.setFinalDate(SearchReport.finalDate);
			relatorio.setInitialDate(SearchReport.initialDate);

			ResultSet rs = relatorioController
						   .searchByDateAndBarber(relatorio);

			while ( rs.next() )
			{

				if( typeService.contains( rs.getString("nome") ) == false ) 
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
			{
				rs.beforeFirst();
				while ( rs.next() ) 
				{
					if( typeService.get(i).equals( rs.getString("nome") ) ) 
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 4 ) 
		{

			relatorio.setBarberName(SearchReport.barber);
			relatorio.setServiceType(SearchReport.service);
			relatorio.setFinalDate(SearchReport.finalDate);
			relatorio.setInitialDate(SearchReport.initialDate);

			ResultSet rs = relatorioController
						   .searchByDateBarberAndService(relatorio);

			while ( rs.next() ) 
			{

				if( typeService.contains( rs.getString("nome") ) == false ) 
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
			{
				rs.beforeFirst();
				while ( rs.next() ) 
				{
					if( typeService.get(i).equals( rs.getString("nome") ) ) 
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;
						
						numberTotalService++;
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						  .replace(".", ",")
						  .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;

			}
		}
		if( SearchReport.searchType == 5 )
		{

			relatorio.setServiceType(SearchReport.service);

			ResultSet rs = relatorioController.searchByService(relatorio);

			while ( rs.next() )
			{

				if( typeService.contains( rs.getString("nome") ) == false )
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++)
			{
				rs.beforeFirst();
				while ( rs.next() ) 
				{
					if( typeService.get(i).equals( rs.getString("nome") ) )
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 6 )
		{

			relatorio.setServiceType(SearchReport.service);
			relatorio.setFinalDate(SearchReport.finalDate);
			relatorio.setInitialDate(SearchReport.initialDate);

			ResultSet rs = relatorioController
						   .searchByDateAndService(relatorio);

			while ( rs.next() )
			{

				if( typeService.contains( rs.getString("nome") ) == false )
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
			}

			for(int i = 0; i < counterService; i++)
			{
				rs.beforeFirst();
				while ( rs.next() )
				{
					if( typeService.get(i).equals( rs.getString("nome") ) ) 
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 7 ) 
		{

			relatorio.setFinalDate(SearchReport.finalDate);
			relatorio.setInitialDate(SearchReport.initialDate);

			ResultSet rs = relatorioController.searchByDate(relatorio);

			while ( rs.next() )
			{

				if( typeService.contains( rs.getString("nome") ) == false )
				{
					typeService.add( rs.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++)
			{
				rs.beforeFirst();
				while ( rs.next() )
				{
					if( typeService.get(i).equals( rs.getString("nome") ) )
					{
						numberCliente = rs.getString("preco").replace(",", ".");
						double valor = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + valor;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] dados = new String[4];
				dados[0] = typeService.get(i);
				dados[1] = Integer.toString(numberTotalService);
				dados[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				dados[3] = Double.toString(valueTotalPay)
						  .replace(".", ",")
						  .valueOf(decimal.format(valueTotalPay));

				modelo.addRow(dados);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		JButton btnPesquisar = new JButton("Pesquisar");
		
		btnPesquisar.addMouseListener(new MouseAdapter() 
		{
			
			// M�todo que permite a vizualiza��o da janela que permite a pesquisa de um relat�rio
			@Override
			public void mouseClicked (MouseEvent e) 
			{
				try
				{
					SearchReport.searchType = 0;
					SearchReport frame = new SearchReport();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
				catch (ParseException e1)
				{
					showErrorMessage(e1.getMessage());
				}
			}
		});
		btnPesquisar.setBounds(680, 13, 94, 62);
		contentPane.add(btnPesquisar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() 
		{
			
			// M�todo que permite novamente a vizualiza��o da janela de Menu principal
			@Override
			public void mouseClicked (MouseEvent e)
			{
				MainMenu frame = new MainMenu();
				frame.setVisible(true);
				frame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnVoltar.setBounds(680, 527, 94, 23);
		contentPane.add(btnVoltar);

		JPanel panel = new JPanel();
		panel.setBounds(10, 529, 660, 22);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLucroTotal = new JLabel("Valor total pesquisado:");
		lblLucroTotal.setBounds(6, 4, 174, 14);
		panel.add(lblLucroTotal);

		JLabel lblValor = new JLabel("R$ "
				+ String.valueOf(decimal.format(valueTotalBuy)));
		lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValor.setVerticalAlignment(SwingConstants.BOTTOM);
		lblValor.setBounds(476, 4, 174, 14);
		panel.add(lblValor);

		final JPanel painelGrafico = new JPanel();
		painelGrafico.setBounds(10, 10, 660, 486);
		contentPane.add(painelGrafico);
		painelGrafico.setVisible(true);

		if( SearchReport.searchType != 0 ) 
		{
			try 
			{
				CategoryDataset createDataSet;
				createDataSet = createDatasetRelatorio();
				String titulo = "Total Por Dia";
				String eixoy = "Valores";
				String txt_legenda = "Ledenda:";
				boolean legenda = true;
				boolean tooltips = true;
				boolean urls = true;
				JFreeChart graf = ChartFactory.createBarChart(titulo,
						txt_legenda, eixoy, createDataSet, PlotOrientation.VERTICAL,
						legenda, tooltips, urls);
				ChartPanel myChartPanel = new ChartPanel(graf, true);
				myChartPanel.setSize(painelGrafico.getWidth(),
						painelGrafico.getHeight());
				myChartPanel.setVisible(true);
				painelGrafico.removeAll();
				painelGrafico.add(myChartPanel);
				painelGrafico.revalidate();
				painelGrafico.repaint();

			} 
			catch (SQLException e) 
			{
				showErrorMessage(e.getMessage());
			} 
			catch (ReportException e) 
			{
				showErrorMessage(e.getMessage());
			}

		}

		JButton btnGrafico = new JButton("Gr\u00E1fico");
		btnGrafico.addMouseListener(new MouseAdapter() 
		{
			
			// M�todo que permite a vizualiza��o de um painel gr�fico dos relatorios
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if( SearchReport.searchType != 0 ) 
				{
					painelGrafico.setVisible(true);
					scrollPane.setVisible(false);
				} 
				else
				{
					JOptionPane
							.showMessageDialog(null,
									"Você deve fazer uma busca para visualizar o gráfico.");
				}
			}
		});
		btnGrafico.setBounds(680, 159, 94, 62);
		contentPane.add(btnGrafico);

		JButton btnTabela = new JButton("Tabela");
		btnTabela.addMouseListener(new MouseAdapter() 
		{
			
			// M�todo que permite a vizualiza��o da tabela de relat�rios
			@Override
			public void mouseClicked (MouseEvent arg0)
			{
				painelGrafico.setVisible(false);
				scrollPane.setVisible(true);
			}
		});
		btnTabela.setBounds(680, 86, 94, 62);
		contentPane.add(btnTabela);
		
		JPanel painelTotalPago = new JPanel();
		painelTotalPago.setBounds(10, 509, 660, 22);
		contentPane.add(painelTotalPago);
		painelTotalPago.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor total a ser pago para o barbeiro:");
		lblNewLabel.setBounds(6, 4, 235, 14);
		painelTotalPago.add(lblNewLabel);
		
		JLabel lblvalorTotalDoBarbeiro = new JLabel("R$ "
				+ String.valueOf(decimal.format(valueTotalBuyPay)));
		lblvalorTotalDoBarbeiro.setVerticalAlignment(SwingConstants.BOTTOM);
		lblvalorTotalDoBarbeiro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblvalorTotalDoBarbeiro.setBounds(476, 4, 174, 14);
		painelTotalPago.add(lblvalorTotalDoBarbeiro);
	}

	// Interface utilizada para implementar os dados a serem exibidos no gr�fico
	private CategoryDataset createDatasetRelatorio () throws SQLException,
			ReportException, NullPointerException, ParseException
			{

		Report relatorio = new Report();
		ResultSet rs = null;

		if( SearchReport.searchType != 0 ) 
		{
			if( SearchReport.searchType == 1 ) 
			{
				relatorio.setBarberName(SearchReport.barber);

				rs = ReportController.getInstance().searchByBarber(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 2 )
			{
				relatorio.setBarberName(SearchReport.barber);
				relatorio.setServiceType(SearchReport.service);

				rs = ReportController.getInstance()
									 .searchByBarberAndService(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 3 )
			{
				relatorio.setBarberName(SearchReport.barber);
				relatorio.setFinalDate(SearchReport.finalDate);
				relatorio.setInitialDate(SearchReport.initialDate);

				rs = ReportController.getInstance()
									 .searchByDateAndBarber(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 4 ) 
			{
				relatorio.setBarberName(SearchReport.barber);
				relatorio.setServiceType(SearchReport.service);
				relatorio.setFinalDate(SearchReport.finalDate);
				relatorio.setInitialDate(SearchReport.initialDate);

				rs = ReportController.getInstance()
									 .searchByDateBarberAndService(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 5 ) 
			{
				relatorio.setServiceType(SearchReport.service);

				rs = ReportController.getInstance().searchByService(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 6 ) 
			{
				relatorio.setServiceType(SearchReport.service);
				relatorio.setFinalDate(SearchReport.finalDate);
				relatorio.setInitialDate(SearchReport.initialDate);

				rs = ReportController.getInstance()
									 .searchByDateAndService(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 7 )
			{
				relatorio.setFinalDate(SearchReport.finalDate);
				relatorio.setInitialDate(SearchReport.initialDate);

				rs = ReportController.getInstance().searchByDate(relatorio);
			}
			else
			{
				// Nothing to do
			}
			
		}
		else
		{
			// Nothing to do
		}

		List<String> dias = new ArrayList<String>();

		while ( rs.next() )
		{
			if( dias.contains( rs.getString("data") ) == false )
			{
				dias.add( rs.getString("data") );
			}
			else
			{
				// Nothing to do
			}
		}

		double totalPorDia = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < dias.size(); i++)
		{
			rs.beforeFirst();
			while ( rs.next() )
			{
				if ( rs.getString("data").equals( dias.get(i) ) )
				{
					totalPorDia += Double.parseDouble(rs.getString("preco")
													  .replace(",", "."));
				}
				else
				{
					// Nothing to do
				}
			}
							
			
			dataset.addValue(totalPorDia, dias.get(i),
							 dias.get(0) + " - " 
							 + dias.get( dias.size() - 1) );
			totalPorDia = 0;
		}

		return dataset;
	}

	// M�todo que mostra uma mensagem de erro quando h� uma exce��o na classe
	private static void showErrorMessage (String exceptionInformation) 
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}
}