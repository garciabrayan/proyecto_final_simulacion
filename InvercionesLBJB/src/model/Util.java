package model;


public class Util {


	/**
	 *@param minimo numero minimo del rango para generar un numero pseudoaleatorio
	 *@param maximo numero maximo del rango para generar un numero pseudoaleatorio
	 *@return primer numero aleatorio generado por congruencialineal
	 */
	public static double pseudoaleatorios(int minimo, int maximo) {
		int seed = 1357;
		int cantidadDatosAGenerar = 1;
		int k = (int) (Math.random()*10)+1;
		int g = (int) (Math.random()*10)+1;
		int c = (int) (Math.random()*10)+1;
		CongruenciaLineal congruenciaLineal = new CongruenciaLineal(seed, k, g, c, cantidadDatosAGenerar);
		congruenciaLineal.llenarXi();
		congruenciaLineal.llenarRi();
		congruenciaLineal.fillNiUniforme(minimo, maximo);
		return congruenciaLineal.getNiUniforme().get(0).intValue();
	}

	public static StateCurrency state(int value) {
		StateCurrency state = StateCurrency.ESTABLE;
		switch (value) {
		case 0:
			state = StateCurrency.BAJO;
			break;
		case 1:
			state = StateCurrency.ESTABLE;
			break;
		case 2:
			state = StateCurrency.SUBIO;
			break;
		}
		return state;
	}
}

