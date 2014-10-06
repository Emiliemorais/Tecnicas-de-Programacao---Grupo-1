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
			
			// Method shows the pane of view reports
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

	// Builder of components of pane view reposts
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

		final DefaultTableModel model = new DefaultTableModel(null,
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

		final JTable table = new JTable(model);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);

		ReportController reportController = ReportController
				.getInstance();

		Report report = new Report();

		if(SearchReport.searchType == 1) 
		{

			report.setBarberName(SearchReport.barber);

			ResultSet instanceStatement = reportController.searchByBarber(report);

			while (instanceStatement.next()) {

				if (typeService.contains(instanceStatement.getString("nome")) == false)
				{
					typeService.add(instanceStatement.getString("nome"));
					counterService++;
				}
				else
				{
					// Nothing to do
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService)
						.replace(".", ",")
						.valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				data[3] = Double.toString(valueTotalPay)
						.replace(".", ",")
						.valueOf(decimal.format(valueTotalPay));
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				model.addRow(data);

				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		else{
			//Noting to do
		}
		if( SearchReport.searchType == 2 )
		{

			report.setBarberName(SearchReport.barber);
			report.setServiceType(SearchReport.service);

			ResultSet instanceStatement = reportController
						   .searchByBarberAndService(report);

			while ( instanceStatement.next() )
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false )
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				valueTotalPay = valueTotalService / 2;
				data[3] = Double.toString(valueTotalPay)
						   .replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				model.addRow(data);
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		else
		{
			// Nothing to do
		}
		if( SearchReport.searchType == 3 ) 
		{

			report.setBarberName(SearchReport.barber);
			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController
						   .searchByDateAndBarber(report);

			while ( instanceStatement.next() )
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false ) 
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				
				data[2] = Double.toString(valueTotalService).replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				
				valueTotalPay = valueTotalService / 2;
				
				data[3] = Double.toString(valueTotalPay).replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				model.addRow(data);
				
				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		else
		{
			// Nothing to do
		}
		if( SearchReport.searchType == 4 ) 
		{

			report.setBarberName(SearchReport.barber);
			report.setServiceType(SearchReport.service);
			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController
						   .searchByDateBarberAndService(report);

			while ( instanceStatement.next() ) 
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false ) 
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++) 
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService).replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				
				valueTotalPay = valueTotalService / 2;
				
				data[3] = Double.toString(valueTotalPay).replace(".", ",")
						  .valueOf(decimal.format(valueTotalPay));

				model.addRow(data);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;

			}
		}
		else
		{
			// Nothing to do
		}
		if( SearchReport.searchType == 5 )
		{

			report.setServiceType(SearchReport.service);

			ResultSet instanceStatement = reportController.searchByService(report);

			while ( instanceStatement.next() )
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false )
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++)
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService).replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				
				valueTotalPay = valueTotalService / 2;
				data[3] = Double.toString(valueTotalPay).replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));
				

				model.addRow(data);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		if( SearchReport.searchType == 6 )
		{

			report.setServiceType(SearchReport.service);
			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController
						   .searchByDateAndService(report);

			while ( instanceStatement.next() )
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false )
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++)
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

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService).replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				
				valueTotalPay = valueTotalService / 2;
				data[3] = Double.toString(valueTotalPay).replace(".", ",")
						   .valueOf(decimal.format(valueTotalPay));

				model.addRow(data);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		else
		{
			// Nothing to do
		}
		if( SearchReport.searchType == 7 ) 
		{

			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController.searchByDate(report);

			while ( instanceStatement.next() )
			{

				if( typeService.contains( instanceStatement.getString("nome") ) == false )
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for(int i = 0; i < counterService; i++)
			{
				instanceStatement.beforeFirst();
				while ( instanceStatement.next() )
				{
					if( typeService.get(i).equals( instanceStatement.getString("nome") ) )
					{
						numberCliente = instanceStatement.getString("preco").replace(",", ".");
						double value = Double.parseDouble(numberCliente);
						valueTotalService = valueTotalService + value;

						numberTotalService++;
					}
					else
					{
						// Nothing to do
					}
				}

				String[] data = new String[4];
				data[0] = typeService.get(i);
				data[1] = Integer.toString(numberTotalService);
				data[2] = Double.toString(valueTotalService).replace(".", ",")
						   .valueOf(decimal.format(valueTotalService));
				
				valueTotalPay = valueTotalService / 2;
				data[3] = Double.toString(valueTotalPay).replace(".", ",")
						  .valueOf(decimal.format(valueTotalPay));

				model.addRow(data);

				valueTotalBuy = valueTotalBuy + valueTotalService;
				valueTotalBuyPay = valueTotalBuyPay + valueTotalPay;
				
				numberTotalService = 0;
				valueTotalPay = 0;
				valueTotalService = 0;
			}
		}
		else
		{
			// Nothing to do
		}
		JButton btnPesquisar = new JButton("Pesquisar");
		
		btnPesquisar.addMouseListener(new MouseAdapter() 
		{
			
			//method permit show pane of search report
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
			
			
			// method permit show pane menu  again
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

		JLabel lblprofitTotal = new JLabel("Valor total pesquisado:");
		lblprofitTotal.setBounds(6, 4, 174, 14);
		panel.add(lblprofitTotal);

		JLabel lblValue = new JLabel("R$ "
				+ String.valueOf(decimal.format(valueTotalBuy)));
		lblValue.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValue.setVerticalAlignment(SwingConstants.BOTTOM);
		lblValue.setBounds(476, 4, 174, 14);
		panel.add(lblValue);

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
				String title = "Total Por Dia";
				String eixoy = "Valores";
				String txt_legenda = "Ledenda:";
				boolean legenda = true;
				boolean tooltips = true;
				boolean urls = true;
				
				
				JFreeChart graf = ChartFactory.createBarChart(title,
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
		else
		{
			// Nothing to do
		}

		JButton btnGraphic = new JButton("Gr\u00E1fico");
		btnGraphic.addMouseListener(new MouseAdapter() 
		{
			
			// Method that allows the display of the table reports
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				String paramOfShowMessageDialog = "Você deve fazer uma busca para visualizar o gráfico.";
				if( SearchReport.searchType != 0 ) 
				{
					painelGrafico.setVisible(true);
					scrollPane.setVisible(false);
				} 
				else
				{
					JOptionPane.showMessageDialog(null,paramOfShowMessageDialog);
				}
			}
		});
		btnGraphic.setBounds(680, 159, 94, 62);
		contentPane.add(btnGraphic);

		JButton btnTable = new JButton("Tabela");
		btnTable.addMouseListener(new MouseAdapter() 
		{
			
			// Method that allows the display of the table reports
			@Override
			public void mouseClicked (MouseEvent arg0)
			{
				painelGrafico.setVisible(false);
				scrollPane.setVisible(true);
			}
		});
		btnTable.setBounds(680, 86, 94, 62);
		contentPane.add(btnTable);
		
		JPanel panelTotalPay = new JPanel();
		panelTotalPay.setBounds(10, 509, 660, 22);
		contentPane.add(panelTotalPay);
		panelTotalPay.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor total a ser pago para o barbeiro:");
		lblNewLabel.setBounds(6, 4, 235, 14);
		panelTotalPay.add(lblNewLabel);
		
		JLabel lblvalueTotalOfBarber = new JLabel("R$ "
				+ String.valueOf(decimal.format(valueTotalBuyPay)));
		lblvalueTotalOfBarber.setVerticalAlignment(SwingConstants.BOTTOM);
		lblvalueTotalOfBarber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblvalueTotalOfBarber.setBounds(476, 4, 174, 14);
		panelTotalPay.add(lblvalueTotalOfBarber);
	}

	// Interface used to implement the data to be displayed in the graphic
	private CategoryDataset createDatasetRelatorio () throws SQLException,
			ReportException, NullPointerException, ParseException
			{

		Report instanceStatement = new Report();
		ResultSet rs = null;

		if( SearchReport.searchType != 0 ) 
		{
			if( SearchReport.searchType == 1 ) 
			{
				instanceStatement.setBarberName(SearchReport.barber);

				rs = ReportController.getInstance().searchByBarber(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 2 )
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setServiceType(SearchReport.service);

				ReportController searchByBarberAndServiceInstance = ReportController.getInstance();
				
				rs = searchByBarberAndServiceInstance.searchByBarberAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 3 )
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateAndBarberInstace = ReportController.getInstance();
				rs = searchByDateAndBarberInstace.searchByDateAndBarber(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 4 ) 
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setServiceType(SearchReport.service);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateBarberAndServiceInstance = ReportController.getInstance();
				rs = searchByDateBarberAndServiceInstance.searchByDateBarberAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 5 ) 
			{
				instanceStatement.setServiceType(SearchReport.service);

				ReportController searchByServiceInstance = ReportController.getInstance();
				rs = searchByServiceInstance.searchByService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 6 ) 
			{
				instanceStatement.setServiceType(SearchReport.service);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateAndServiceInstace = ReportController.getInstance();
						 
				rs = searchByDateAndServiceInstace.searchByDateAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( SearchReport.searchType == 7 )
			{
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDate = ReportController.getInstance();
				rs = searchByDate.searchByDate(instanceStatement);
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

		List<String> days = new ArrayList<String>();

		while ( rs.next() )
		{
			if( days.contains( rs.getString("data") ) == false )
			{
				days.add( rs.getString("data") );
			}
			else
			{
				// Nothing to do
			}
		}

		double totalForDay = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < days.size(); i++)
		{
			rs.beforeFirst();
			while ( rs.next() )
			{
				if ( rs.getString("data").equals( days.get(i) ) )
				{
					totalForDay += Double.parseDouble(rs.getString("preco")
													  .replace(",", "."));
				}
				else
				{
					// Nothing to do
				}
			}
							
			
			dataset.addValue(totalForDay, days.get(i),
							 days.get(0) + " - " 
							 + days.get( days.size() - 1) );
			totalForDay = 0;
		}

		return dataset;
	}

	// Method that displays an error message when there is an exception in class
	private static void showErrorMessage (String exceptionInformation) 
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}
}
