package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import model.SymulatedDay;
import model.SymulatedHours;
import model.SymulatedMonth;
import model.SymulatedYear;
import model.MetodoMontecarlo;
import views.ScatterPlotDemo;
import views.ViewsGrafica;

public class Controller implements ActionListener,ItemListener{

	ArrayList<SymulatedYear> years;
	ArrayList<SymulatedMonth> months;
	ArrayList<SymulatedDay> days;
	ArrayList<SymulatedHours> hours;
	ScatterPlotDemo views;
	ViewsGrafica grafica;
	String graficaActual;
	int dias;
	int horas;
	int meses;
	int anios;
	int symulations;
	double[] probabilities;

	public Controller() {
		MetodoMontecarlo m = new MetodoMontecarlo();
		System.out.println(m.getProbanilidadBajo() + " bajo" + m.getBajo());
		System.out.println(m.getProbanilidadEstable()+ " estable" + m.getEstable());
		System.out.println(m.getProbanilidadSubio()+ " subio" + m.getSubio());
		probabilities = new double[] {m.getProbanilidadEstable(),m.getProbanilidadSubio(),m.getProbanilidadBajo()};
		grafica = new ViewsGrafica(this, this);
	}

	public void hours(int[] data) {
		int simulations =data[4];
		symulatedHours(simulations,data[0]);
		grafica.resetData();
		for (SymulatedHours symulatedHours : hours) {
			grafica.AddSeries( symulatedHours.getId(),symulatedHours.getStates(),8); 
		}
		grafica.graficaHoras("Horas");
	}

	public void days(int[] data) {
		int simulations =data[4];
		symulatedDays(simulations,data[1],data[0]);
		grafica.resetData();
		for (SymulatedDay symulatedDay : days) {
			grafica.AddSeries( symulatedDay.getId(),symulatedDay.getStates(),8); 
		}
		grafica.graficaHoras("dias");
	}

	public void mouth(int[] data) {
		int simulations =data[4];
		symulatedMouth(simulations,data[2],data[1],data[0]);
		grafica.resetData();
		for (SymulatedMonth symulatedDay : months) {
			grafica.AddSeries( symulatedDay.getId(),symulatedDay.getStates(),2); 
		}
		grafica.graficaHoras("Meses");
	}

	public void year(int[] data) {
		int simulations =data[4];
		symulatedYear(simulations,data[3],data[2],data[1],data[0]);
		grafica.resetData();
		for (SymulatedYear symulatedDay : years) {
			grafica.AddSeries( symulatedDay.getId(),symulatedDay.getStates(),1); 
		}
		grafica.graficaHoras("Años");
	}


	private void symulatedHours(int Symulations,int hoursAvalibles) {
		hours = new ArrayList<>();
		for (int i = 0; i < Symulations; i++) {
			hours.add(new SymulatedHours((i+1)+"H",hoursAvalibles,probabilities));
		}
	}

	private void symulatedDays(int Symulations,int daysAvalibles,int hoursAvalibles) {
		days = new ArrayList<>();
		for (int i = 0; i < Symulations; i++) {
			days.add(new SymulatedDay((i+1)+"D",daysAvalibles,hoursAvalibles,probabilities));
		}
	}


	private void symulatedMouth(int Symulations,int mouthAvalibles,int daysAvalibles,int hoursAvalibles) {
		months = new ArrayList<>();
		for (int i = 0; i < Symulations; i++) {
			months.add(new SymulatedMonth((i+1)+"M",mouthAvalibles,daysAvalibles,hoursAvalibles,probabilities));
		}
	}

	private void symulatedYear(int Symulations,int yearsAvalibles,int mouthAvalibles,int daysAvalibles,int hoursAvalibles) {
		years = new ArrayList<>();
		for (int i = 0; i < Symulations; i++) {
			years.add(new SymulatedYear ((i+1)+"M",yearsAvalibles,mouthAvalibles,daysAvalibles,hoursAvalibles,probabilities));
		}
	}

	

	


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Sencilla":
			if (graficaActual.equals("DIAS")) {
				grafica.resetData();
				grafica.AddSeries("1",days.get(0).getStates(),1); 
				grafica.graficaHoras("dias");
			}
			if (graficaActual.equals("HORAS")) {
				grafica.resetData();
				grafica.AddSeries( "1",hours.get(0).getStates(),8); 
				grafica.graficaHoras("horas");
			}
			if (graficaActual.equals("MESES")) {
				grafica.resetData();
				grafica.AddSeries("1",months.get(0).getStates(),8); 
				grafica.graficaHoras("horas");
			}
			if (graficaActual.equals("ANIOS")) {
				grafica.resetData();
				grafica.AddSeries("1",years.get(0).getStates(),8); 
				grafica.graficaHoras("horas");
			}

			break;
		case "Avanzada":
			if (graficaActual.equals("HORAS")) {
				grafica.resetData();
				graficaActual="HORAS";
				gardarData(grafica.getData());
				hours(grafica.getData());
			}
			if (graficaActual.equals("DIAS")) {
				grafica.resetData();
				graficaActual="DIAS";
				gardarData(grafica.getData());
				days(grafica.getData());
			}
			if (graficaActual.equals("MESES")) {
				grafica.resetData();
				graficaActual="MESES";
				gardarData(grafica.getData());
				mouth(grafica.getData());
			}
			if (graficaActual.equals("ANIOS")) {
				grafica.resetData();
				graficaActual="ANIOS";
				gardarData(grafica.getData());
				year(grafica.getData());
			}
			break;
		case "Volver":
			grafica.dataInicial(new int[] {horas,dias,meses,anios,symulations});
			break;



		default:
			break;
		}

	}

	public static void main(String[] args) {
		new Controller();
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		graficaSelecionada(e.getItem().toString());
	}

	private void gardarData(int[] data) {
		horas=data[0];
		dias=data[1];
		meses=data[2];
		anios=data[3];
		symulations=data[4];

	}

	private void graficaSelecionada(String e){
		switch (e) {
		case "Horas":
			graficaActual="HORAS";
			gardarData(grafica.getData());
			hours(grafica.getData());
			break;
		case "Dias":
			graficaActual="DIAS";
			gardarData(grafica.getData());
			days(grafica.getData());
			break;
		case "Meses":
			graficaActual="MESES";
			gardarData(grafica.getData());
			mouth(grafica.getData());
			break;
		case "Años":
			graficaActual="ANIOS";
			gardarData(grafica.getData());
			year(grafica.getData());
			break;


		}
	}
}
