package model;

public class SymulatedYear {
	
	
	String id;
	SymulatedMonth[] month;
	StateCurrency state;
	double[][] values;
	int daisValidate;
	int hoursValidate;
	int monthValidate;
	int yearValidate;
	Probability probability;
	int valoAnterior;
	public SymulatedYear(String id,int yearValidate,int monthValidate,int daisValidate,int hoursValidate,double[] probabilitys) {
		this.id=id;
		this.hoursValidate=hoursValidate;
		this.daisValidate=daisValidate;
		this.monthValidate=monthValidate;
		this.yearValidate=yearValidate;
		probability = new Probability(probabilitys);
		state = StateCurrency.ESTABLE;
		generateYears(monthValidate,daisValidate,hoursValidate,probabilitys);
	}
	
	private SymulatedMonth[] generateYears(int monthvalidate,int daysValidate,int hoursValidate,double[] probabilitys) {
		month = new SymulatedMonth[monthvalidate];
		for (int i = 0; i < monthvalidate; i++) {
			SymulatedMonth hoursSimulate = new  SymulatedMonth((i+1)+""+id, monthvalidate,daysValidate,hoursValidate,probabilitys);
			month[i] = hoursSimulate;
		}
		getState();
		return month;
	}
	
	public double[][] getStates() {	
		return concatenarArrays();
	}
	
	public StateCurrency getState() {
		int state =0;
		for (SymulatedMonth symulatedHours : month) {
			state +=symulatedHours.getState().getValor();
		}
		return Util.state(state/month.length);
	}
	
	private double[][] concatenarArrays() {
		values = new double[2][((daisValidate*hoursValidate)*monthValidate)*yearValidate];
			data();
		return values;
	}
	
	public void data() {

		valoAnterior = (int) probability.validarPosicion(Util.pseudoaleatorios(0,100)) ;;;
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
	
	public StateCurrency getStateForIdHour(String id) {
		StateCurrency state = StateCurrency.ESTABLE;
		for (SymulatedMonth hour : month) {
			if (hour.getId().equals(id)) {
				state= hour.getState();
			}
		}
		return state;
	}
	
	public String getId() {
		return id;
	}
	
	public int getValor(){
		return valoAnterior;
	}
	
	public SymulatedMonth[] getMonth() {
		return month;
	}

}
