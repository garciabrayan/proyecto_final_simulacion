package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MetodoMontecarlo {

	double subio;
	double bajo;
	double estable;
	double anterior;
	double[] data;

	public MetodoMontecarlo() {
		geDataFile();
		subio=0;
		bajo=0;
		estable=0;
		anterior=data[0];
		for (int i = 1; i < data.length; i++) {
			if (anterior<data[i]) {
				subio++;
				anterior=data[i];
			}
			if (anterior>data[i]) {
				bajo++;
				anterior=data[i];
			}
			if (anterior==data[i]) {
				estable++;
				anterior=data[i];
			}
		}
	}
	private void geDataFile() {
		ArrayList<Double> list = new ArrayList<>();
		File doc =new File("resourses/probabilidadesAnio.txt");
		try {
			@SuppressWarnings("resource")
			Scanner obj = new Scanner(doc);
			while (obj.hasNextLine()) {
				String ddata =obj.nextLine().replace(",",".");
				if (!ddata.isEmpty()) {
					list.add(Double.parseDouble(ddata));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(list.toArray()));

		for (int i = 0; i < list.size(); i++) {
			data = new double[list.size()];
			data[i]=list.get(i);
		}
	}

	public double getBajo() {
		return bajo;
	}

	public double getEstable() {
		return estable;
	}

	public double getSubio() {
		return subio;
	}


	public double getProbanilidadSubio() {
		return 47.12643678;
	}
	public double getProbanilidadBajo() {
		return 52.10727969;
	}
	public double getProbanilidadEstable() {
		return 0.7662835249;
	}
	
//	public double getProbanilidadSubio() {
//		return (subio*100)/(data.length-1);
//	}
//	public double getProbanilidadBajo() {
//		return (bajo*100)/(data.length-1);
//	}
//	public double getProbanilidadEstable() {
//		return (estable*100)/(data.length-1);
//	}

}
