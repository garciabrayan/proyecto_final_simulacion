package views;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import model.Hour;


public class ScatterPlotDemo extends JFrame{

	private static final long serialVersionUID = 1L;
	JFreeChart chart;//declaramos un objeto de la clase JFreeChart para construir el grafico4
	XYSeriesCollection dataset;

	
	 /**
	  *@param rondas lista que contiene todas las rondas que se van a graficar 
*/
	public ScatterPlotDemo(Hour[] hours) {
		super("Gráfica de Horas vs Estado");
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(new BorderLayout());
		setLocationRelativeTo(rootPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		crearGraficoSymulateHours(hours);

		ChartPanel panel = new ChartPanel(chart, false);//ChartPanel es una clase del paquete JFreeChart
		add(panel,BorderLayout.CENTER); //anadimos el panel al JFrame

	}

	 /**
	  * este metodo carga las series por cada jugador 
	  *@param jugadoresEquipoUno lista que contiene todas los jugadores que participaron en los juegos 
*/
	public void crearGraficoSymulateHours(Hour[] hours) { 
		dataset = new XYSeriesCollection();
		for (Hour hour : hours) {
			XYSeries  seriesPA = new XYSeries(hour.getIdHour());//"Producto A" es la etiqueta o nombre
			dataset.addSeries(seriesPA);//anadir la serie del producto A
			System.out.println(hour.getIdHour());
		}
		chart = ChartFactory.createScatterPlot(
				"Gráfica Horas vs Estado", // Titulo
				"Horas", // Etiqueta Coordenada X
				"Estado", // Etiqueta Coordenada Y
				dataset, // Datos
				PlotOrientation.VERTICAL,
				true, // Muestra la leyenda de los productos en el eje de la X
				true,// mostrar la leyenda en cada punto
				false
				);
		System.out.println("holaaa ");
	}
	
	 /**
	  * este metodo carga los datos para cada uno de los jugadores  (series)
	  *@param nombre representa el nombre de cada serie (jugador)
	  *@param puntajeJuegos lista que contiene cada uno de los puntajes obtenidos por el jugador (serie) 
*/
	public void addInforHoras(String id,int[] puntajeJuegos) {
		System.out.println("---------"+id+"---------");
		System.out.println("este es el nmbre a agregar"+ id);
		for (int i = 0; i < puntajeJuegos.length; i++) {
			dataset.getSeries(id).add(i,puntajeJuegos[i]);
			System.out.println(puntajeJuegos[i]);
		}
	}
	 /**
	  * este metodo re valida , re pinta y muestra la grafica con todos los datos ingresados
*/
	public void iniciarGrafica() {
		revalidate();
		repaint();
		setVisible(true);
	}
}
