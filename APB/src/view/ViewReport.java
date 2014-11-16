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
public class ViewReport extends JFrame 
{
	private JPanel receiptPanel;
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
			
			public void run () 
			{
				try 
				{
					ViewReport viewReportFrame = new ViewReport();
					viewReportFrame.setVisible(true);
				} 
				catch (Exception e)
				{
					showErrorMessage(e.getMessage());
				}
			}
		});
	}
	
	private void initializeComponents() throws SQLException, ReportException,
	   									NullPointerException, ParseException
	{
		
		setTitle("Relat\u00F3rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		receiptPanel = new JPanel();
		receiptPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(receiptPanel);
		receiptPanel.setLayout(null);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 660, 486);
		receiptPanel.add(scrollPane);

		final DefaultTableModel model = new DefaultTableModel(null,
				new String[] { 
					"Nome do Serviço", "Quantidade", "Valor total", "Valor recebido" 
				}) 
		{
			boolean[] columnEditables = new boolean[] 
					{ 
						false, false, false, false 
					};
			
			// Method checking whether the cell can be changed
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

		int typeOfSearch = SearchReport.searchType;
		if (typeOfSearch == 1) 
		{

			report.setBarberName(SearchReport.barber);

			ResultSet instanceStatement = reportController.searchByBarber(report);

			while (instanceStatement.next()) 
			{

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
				
				while (instanceStatement.next())
				{
					boolean ifGetStringEqualsTypeService = typeService.get(i).equals( instanceStatement.getString("nome") );
					if ( ifGetStringEqualsTypeService ) 
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
		else
		{
			//Noting to do
		}
		if ( typeOfSearch == 2 )
		{

			report.setBarberName(SearchReport.barber);
			report.setServiceType(SearchReport.service);

			ResultSet instanceStatement = reportController
						   .searchByBarberAndService(report);

			while ( instanceStatement.next() )
			{

				boolean typeServiceGetName = typeService.contains( instanceStatement.getString("nome") );
				if( typeServiceGetName == false )
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
					boolean ifStringNameEqualsTypeServiceStored = typeService.get(i).equals( instanceStatement.getString("nome"));
					
					if( ifStringNameEqualsTypeServiceStored ) 
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
		
		if( typeOfSearch == 3 ) 
		{

			report.setBarberName(SearchReport.barber);
			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController
						   .searchByDateAndBarber(report);

			while ( instanceStatement.next() )
			{

				boolean paramTypeServiceName = typeService.contains( instanceStatement.getString("nome") );
				if( paramTypeServiceName == false ) 
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
					boolean nameGetStringEqualsTypeservice = typeService.get(i).equals( instanceStatement.getString("nome") );
					
					if( nameGetStringEqualsTypeservice ) 
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
		
		if( typeOfSearch == 4 ) 
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
					boolean nameGetStringEqualsTypeservice = typeService.get(i).equals( instanceStatement.getString("nome") );
					
					if( nameGetStringEqualsTypeservice ) 
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
		
		if( typeOfSearch == 5 )
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
		if( typeOfSearch == 6 )
		{

			report.setServiceType(SearchReport.service);
			report.setFinalDate(SearchReport.finalDate);
			report.setInitialDate(SearchReport.initialDate);

			ResultSet instanceStatement = reportController
						   .searchByDateAndService(report);

			while ( instanceStatement.next() )
			{

				boolean paramGetStringNome =  typeService.contains( instanceStatement.getString("nome") );
				if( paramGetStringNome == false )
				{
					typeService.add( instanceStatement.getString("nome") );
					counterService++;
				}
				else
				{
					// Nothing to do
				}
			}

			for (int i = 0; i < counterService; i++)
			{
				instanceStatement.beforeFirst();
				while ( instanceStatement.next() )
				{
					boolean ifNameGetEqualsNameStored = typeService.get(i).equals( instanceStatement.getString("nome") );
					if( ifNameGetEqualsNameStored ) 
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
		
		if( typeOfSearch == 7 ) 
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

			for (int i = 0; i < counterService; i++)
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
			
			// Method that permit show panel of search report
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
		receiptPanel.add(btnPesquisar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addMouseListener(new MouseAdapter() 
		{
			
			// Method that permit show menu panel again
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
		receiptPanel.add(btnVoltar);

		JPanel panel = new JPanel();
		panel.setBounds(10, 529, 660, 22);
		receiptPanel.add(panel);
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
		receiptPanel.add(painelGrafico);
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
		receiptPanel.add(btnGraphic);

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
		receiptPanel.add(btnTable);
		
		JPanel panelTotalPay = new JPanel();
		panelTotalPay.setBounds(10, 509, 660, 22);
		receiptPanel.add(panelTotalPay);
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
	
	// Constructor of components of pane view reposts
	public ViewReport() throws SQLException, ReportException,
						NullPointerException, ParseException
	{
		initializeComponents();
	}

	// Interface used to implement the data to be displayed in the graphic
	private CategoryDataset createDatasetRelatorio () throws SQLException,
			ReportException, NullPointerException, ParseException
			{

		Report instanceStatement = new Report();
		ResultSet secondInstanceStatement = null;

		int typeSearch = SearchReport.searchType;
		if( typeSearch != 0 ) 
		{
			if( typeSearch == 1 ) 
			{
				instanceStatement.setBarberName(SearchReport.barber);

				secondInstanceStatement = ReportController.getInstance().searchByBarber(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 2 )
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setServiceType(SearchReport.service);

				ReportController searchByBarberAndServiceInstance = ReportController.getInstance();
				
				secondInstanceStatement = searchByBarberAndServiceInstance.searchByBarberAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 3 )
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateAndBarberInstace = ReportController.getInstance();
				secondInstanceStatement = searchByDateAndBarberInstace.searchByDateAndBarber(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 4 ) 
			{
				instanceStatement.setBarberName(SearchReport.barber);
				instanceStatement.setServiceType(SearchReport.service);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateBarberAndServiceInstance = ReportController.getInstance();
				secondInstanceStatement = searchByDateBarberAndServiceInstance.searchByDateBarberAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 5 ) 
			{
				instanceStatement.setServiceType(SearchReport.service);

				ReportController searchByServiceInstance = ReportController.getInstance();
				secondInstanceStatement = searchByServiceInstance.searchByService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 6 ) 
			{
				instanceStatement.setServiceType(SearchReport.service);
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDateAndServiceInstace = ReportController.getInstance();
						 
				secondInstanceStatement = searchByDateAndServiceInstace.searchByDateAndService(instanceStatement);
			}
			else
			{
				// Nothing to do
			}
			
			if( typeSearch == 7 )
			{
				instanceStatement.setFinalDate(SearchReport.finalDate);
				instanceStatement.setInitialDate(SearchReport.initialDate);

				ReportController searchByDate = ReportController.getInstance();
				secondInstanceStatement = searchByDate.searchByDate(instanceStatement);
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

		while ( secondInstanceStatement.next() )
		{
			String getStringDataParam = secondInstanceStatement.getString("data");
			boolean daysGetStringDataParam = days.contains( getStringDataParam );
			
			if( daysGetStringDataParam == false )
			{
				days.add( getStringDataParam );
			}
			else
			{
				// Nothing to do
			}
		}

		double totalForDay = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		int quantidyOfDays = days.size();
		
		for (int i = 0; i < quantidyOfDays ; i++)
		{
			secondInstanceStatement.beforeFirst();
			while ( secondInstanceStatement.next() )
			{
				boolean ifDategetEqualsDategetString = secondInstanceStatement.getString("data").equals( days.get(i) );
				
				if ( ifDategetEqualsDategetString )
				{
					double sumTotalOnDay = Double.parseDouble(secondInstanceStatement.getString("preco")
							  .replace(",", "."));
					
					totalForDay += sumTotalOnDay;
				}
				else
				{
					// Nothing to do
				}
			}
							
			String paramDaysDataset = days.get(0) + " - "  + days.get( days.size() - 1);
			
			dataset.addValue(totalForDay, days.get(i), paramDaysDataset);
			totalForDay = 0;
		}

		return dataset;
	}

	/**
	 * Display the error that occurred by the message
	 * @param exceptionInformation - Message with the information of the exception raised
	 */
	private static void showErrorMessage (String exceptionInformation) 
	{
		JOptionPane.showMessageDialog(null, exceptionInformation, "Atenção",
									  JOptionPane.INFORMATION_MESSAGE);
	}
}
