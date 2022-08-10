package model;

public class SymulatedDay {

	String id;
	SymulatedHours[] hours;
	StateCurrency state;
	double[][] values;
	int hoursValidate;
	int daisValidate;
	int valueHorurAnterior;
	Probability probability;
	int valoAnterior ;
	public SymulatedDay(String id,int daisValidate,int hoursValidate,double[] probabilitys) {
		this.id=id;
		this.hoursValidate=hoursValidate;
		this.daisValidate=daisValidate;
		state = StateCurrency.ESTABLE;
		probability = new Probability(probabilitys);
		generateDay(daisValidate,hoursValidate,probabilitys);
	}

	private SymulatedHours[] generateDay(int daysValidate,int hoursValidate,double[] probabilitys) {
		hours = new SymulatedHours[hoursValidate];
		for (int i = 0; i < hoursValidate; i++) {
			SymulatedHours hoursSimulate = new  SymulatedHours((i+1)+"D", hoursValidate,probabilitys);
			hours[i] = hoursSimulate;
		}
		valueHorurAnterior=hours[hoursValidate-1].getHours()[hoursValidate-1].getValue();
		getState();
		return hours;
	}

	public double[][] getStates() {
		return concatenarArrays();
	}


	public StateCurrency getState() {
		int state =0;
		for (SymulatedHours symulatedHours : hours) {
			state +=symulatedHours.getState().getValor();
		}
		return Util.state(state/hours.length);
	}

	private double[][] concatenarArrays() {
		values = new double[2][daisValidate*hoursValidate];
		data();
		return values;
	}

	public void data() {
		 valoAnterior =	(int) probability.validarPosicion(Util.pseudoaleatorios(0,100)) ;;
		values[0][0]=0;
		values[1][0]=valoAnterior;
		for (int i = 1; i < values.length; i++) {
			for (int j = 1; j < values[i].length; j++) {
				values[0][j]=j;
				int	valorGenearado=(int) probability.validarPosicion(Util.pseudoaleatorios(0,100)) ;
				if (Util.state(valorGenearado).toString().equals("SUBIO") ) {
					valoAnterior+=1;
				}
				if (Util.state(valorGenearado).toString().equals("BAJO") ) {
					valoAnterior-=1;
				}
				if (Util.state(valorGenearado).toString().equals("ESTABLE") ) {
					valoAnterior+=0;
				}
				values[1][j]=valoAnterior;
			}
		}
	}
	
	public int getValue(){
		return valoAnterior;
	}
	
	public StateCurrency getStateForIdHour(String id) {
		StateCurrency state = StateCurrency.ESTABLE;
		for (SymulatedHours hour : hours) {
			if (hour.getId().equals(id)) {
				state= hour.getState();
			}
		}
		return state;
	}


	public SymulatedHours[] getHours() {
		return hours;
	}

	public String getId() {
		return id;
	}
}
