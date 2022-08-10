package model;

import java.util.ArrayList;

public class Probability {

	double[] rangoMujeres;

	ArrayList<String[]> tablero;

	public Probability(double[] probabilities) {
		rangoMujeres = new double[3];
		addDianas(probabilities[0],probabilities[1],probabilities[2]);
	}

	private void addDianas(double estable,double subio,double bajo) {
		rangoMujeres[0]=estable;
		rangoMujeres[1]=subio;
		rangoMujeres[2]=bajo;
	}

	public int validarPosicion(double ubicacionDiana) {
		int state=0;
			if (ubicacionDiana<=rangoMujeres[0]) {
				state = 1;
			}
			if (ubicacionDiana>=rangoMujeres[0]&&ubicacionDiana<=rangoMujeres[1]) {
				state = 2;
			}
			if (ubicacionDiana>=rangoMujeres[2]) {
				state = 0;
			}
		return state;
	}

}
