package model;

public class SymulatedMonth {
	
	String id;
	SymulatedDay[] days;
	StateCurrency state;
	double[][] values;
	int daisValidate;
	int hoursValidate;
	int monthValidate;
	int valueDayAnterior;
	Probability probability;
	public SymulatedMonth(String id,int monthValidate,int daisValidate,int hoursValidate,double[] probabilitys) {
		this.id=id;
		this.hoursValidate=hoursValidate;
		this.daisValidate=daisValidate;
		this.monthValidate=monthValidate;
		probability = new Probability(probabilitys);
		state = StateCurrency.ESTABLE;
		generateMonth(daisValidate,hoursValidate,probabilitys);
	}

	private SymulatedDay[] generateMonth(int daysValidate,int hoursValidate,double[] probabilitys) {
		days = new SymulatedDay[daysValidate];
		for (int i = 0; i < daysValidate; i++) {
			SymulatedDay hoursSimulate = new  SymulatedDay((i+1)+""+id, daysValidate,hoursValidate,probabilitys);
			days[i] = hoursSimulate;
		}
		valueDayAnterior=days[daysValidate-1].getHours()[hoursValidate-1].getValor();
		getState();
		return days;
	}

	public double[][] getStates() {	
		return concatenarArrays();
	}
	
	
	public StateCurrency getState() {
		int state =0;
		for (SymulatedDay symulatedHours : days) {
			state +=symulatedHours.getState().getValor();
		}
		return Util.state(state/days.length);
	}
	
	private double[][] concatenarArrays() {
		values = new double[2][(daisValidate*hoursValidate)*monthValidate];
			data();
		return values;
	}
	
	public void data() {
		int valoAnterior = (int) probability.validarPosicion(Util.pseudoaleatorios(0,100)) ;;;
		values[0][0]=0;
		values[1][0]=valoAnterior;
		for (int i = 1; i < values.length; i++) {
			for (int j = 1; j < values[i].length; j++) {
				values[0][j]=j;
				if(i==120) {
					System.out.println("nuevo mes "+valoAnterior);
				}
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
	
	public StateCurrency getStateForIdHour(String id) {
		StateCurrency state = StateCurrency.ESTABLE;
		for (SymulatedDay hour : days) {
			if (hour.getId().equals(id)) {
				state= hour.getState();
			}
		}
		return state;
	}
	
	public String getId() {
		return id;
	}
	
	public int getValor() {
		return valueDayAnterior;
	}
	
	public SymulatedDay[] getDays() {
		return days;
	}

}
