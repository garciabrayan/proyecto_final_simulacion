package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class ViewsGrafica extends JFrame{
	private static final long serialVersionUID = 1L;
	DefaultXYDataset dataset;
	JPanel panelCentral;
	JComboBox<String> graficas;
	JPanel opciones;
	JPanel configs;
	ItemListener itemListener;
	ActionListener listener;
	String graficaActual;
	JSpinner horas;
	JSpinner dias;
	JSpinner meses;
	JSpinner anios;
	JSpinner simulations;
	public ViewsGrafica(ActionListener listener,ItemListener itemListener) {
		super("Gráfica de Horas vs Estado");
		this.itemListener=itemListener;
		this.listener=listener;
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setLayout(new BorderLayout());
		dataset = new DefaultXYDataset();

		opciones = new JPanel();
		opciones.setBackground(Color.WHITE);
		opciones.setLayout(new GridLayout(1,2));
		opciones.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

		add(panelCentral,BorderLayout.CENTER);

		add(opciones,BorderLayout.PAGE_END);

		dataInicial(new int[] {1,1,1,1,1});


		setVisible(true);
	}

	public void dataInicial(int[] data) {
		panelCentral.removeAll();
		opciones.removeAll();
		dataset = new DefaultXYDataset();


		graficas = new JComboBox<>();
		graficas.addItemListener(itemListener);
		graficas.addItem("");
		graficas.addItem("Horas");
		graficas.addItem("Dias");
		graficas.addItem("Meses");
		graficas.addItem("Años");
		opciones.add(graficas);

		configs = new JPanel();
		configs.setLayout(new BorderLayout());
		configs.setBackground(Color.WHITE);
		opciones.add(configs);

		JPanel panelConten = new JPanel();
		panelConten.setBackground(Color.WHITE);
		panelConten.setBorder(BorderFactory.createEmptyBorder(150,450,350,450));
		panelConten.setLayout(new BoxLayout(panelConten,BoxLayout.Y_AXIS));

		JLabel label = new JLabel("¡Bienvenido!");
		label.setBackground(Color.WHITE);
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		label.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setHorizontalAlignment(JLabel.CENTER);
		JLabel labelDos = new JLabel("Ingresa la configuracion inicial");
		labelDos.setBackground(Color.WHITE);
		labelDos.setFont(new Font(Font.SANS_SERIF, Font.BOLD,25));
		labelDos.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
		labelDos.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		labelDos.setHorizontalAlignment(JLabel.CENTER);
		panelConten.add(label);
		panelConten.add(labelDos);
		JPanel configs = new JPanel();
		configs.setBackground(Color.WHITE);
		configs.setLayout(new BoxLayout(configs,BoxLayout.Y_AXIS));
		configs.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
		SpinnerNumberModel modelHoras =new SpinnerNumberModel(data[0],1,10000,1);
		horas = new JSpinner(modelHoras);
		horas.setBackground(Color.WHITE);
		horas.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,0,0),  BorderFactory.createTitledBorder("Horas Habiles")));
		SpinnerNumberModel modelDias =new SpinnerNumberModel(data[1],1,10000,1);
		dias = new JSpinner(modelDias);
		dias.setBackground(Color.WHITE);
		dias.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,0,0),  BorderFactory.createTitledBorder("Dias Habiles")));
		SpinnerNumberModel modelMeses =new SpinnerNumberModel(data[2],1,10000,1);
		meses = new JSpinner(modelMeses);
		meses.setBackground(Color.WHITE);
		meses.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,0,0),  BorderFactory.createTitledBorder("Meses Habiles")));
		SpinnerNumberModel modelAnios =new SpinnerNumberModel(data[3],1,10000,1);
		anios = new JSpinner(modelAnios);
		anios.setBackground(Color.WHITE);
		anios.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,0,0),  BorderFactory.createTitledBorder("Años Habiles")));
		SpinnerNumberModel modelsymu =new SpinnerNumberModel(data[4],1,10000,1);
		simulations = new JSpinner(modelsymu);
		simulations.setBackground(Color.WHITE);
		simulations.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,0,0,0),  BorderFactory.createTitledBorder("Cantidad de simulaciones ")));
		configs.add(horas);
		configs.add(dias);
		configs.add(meses);
		configs.add(anios);
		configs.add(simulations);

		panelConten.add(configs);
		panelConten.add(graficas);
		panelCentral.add(panelConten);

		revalidate();
		repaint();
	}

	public void AddSeries(String id,double[][] data,int tiempoDivicion) {
		dataset.addSeries(id,data);
	}
	public void resetData() {
		dataset  = new DefaultXYDataset();
	}


	public void paintHoursSencilla(double[][] data) {
		dataset  = new DefaultXYDataset();
		dataset.addSeries("",data);
	}

	public void graficaHoras(String tiempo) {
		JFreeChart chart = ChartFactory.createXYLineChart("Tiempo Vs Estado", tiempo,"Estado", dataset);
		XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
		chart.getXYPlot().setRenderer(render);

		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		panelCentral.removeAll();
		panelCentral.add(panel,BorderLayout.CENTER);
		panelCentral.revalidate();
		panelCentral.repaint();
		opciones();
	}


	private void opciones() {
		configs.removeAll();
		JPanel panelGraficas = new JPanel();
		panelGraficas.setLayout(new GridLayout(2,1));
		panelGraficas.setBackground(Color.WHITE);
		panelGraficas.setBorder(BorderFactory.createTitledBorder("Tipo de Grafica"));
		JButton avanzada = new JButton("Avanzada");
		avanzada.setBackground(Color.GRAY);
		avanzada.setActionCommand("Avanzada");
		avanzada.addActionListener(listener);
		avanzada.setForeground(Color.WHITE);
		JButton simple = new JButton("Simple");
		simple.setBackground(Color.GRAY);
		simple.addActionListener(listener);
		simple.setActionCommand("Sencilla");
		simple.setForeground(Color.WHITE);
		JButton volver = new JButton("Volver");
		volver.setBackground(Color.GRAY);
		volver.setForeground(Color.WHITE);
		volver.addActionListener(listener);
		volver.setActionCommand("Volver");

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,2));
		p.add(simple);
		p.add(avanzada);
		panelGraficas.add(p);
		panelGraficas.add(volver);
		configs.add(panelGraficas,BorderLayout.PAGE_END);

		configs.revalidate();
		configs.repaint();

	}

	public int[] getData() {
		return new int[] {(int) horas.getValue(),(int) dias.getValue(),(int) meses.getValue(),(int) anios.getValue(),(int) simulations.getValue()};
	}


	public String  cantidadSimulation() {
		graficas.setPopupVisible(false);
		return "12";
	}

}
