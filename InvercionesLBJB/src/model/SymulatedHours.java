package model;

public class SymulatedHours {

	String id;
	Hour[] hours;
	int hoursValidate;
	int hoursAnterior;
	int valoAnterior;
	Probability probability;
	public SymulatedHours(String id,int hoursValidate,double[] probabilitys) {
		this.id=id;
		this.hoursValidate=hoursValidate+1;
		hoursAnterior=0;
		probability = new Probability(probabilitys);
		generateHours(hoursValidate);
	}

	private Hour[] generateHours(int hoursValidate) {
		hours = new Hour[this.hoursValidate];
		for (int i = 0; i < this.hoursValidate; i++) {
			Hour hour = new Hour((i+1)+"H"+id,hoursAnterior);
			hoursAnterior=hour.getValue();
			int data = (int)Util.pseudoaleatorios(0,100);
			hour.setState(Util.state(data));
			hours[i]= hour;
		}
		return hours;
	}

	public double[][] getStates() {
		valoAnterior = (int) probability.validarPosicion(Util.pseudoaleatorios(0,100)) ;;
		double[][] values = new double[2][hoursValidate];
		values[0][0]=0;
		values[1][0]=valoAnterior;
		for (int i = 1; i < hoursValidate; i++) {
			values[0][i]=i;
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
			values[1][i]=valoAnterior;
		}
		return values;
	}

	public StateCurrency getStateForIdHour(String id) {
		StateCurrency state = null;
		for (Hour hour : hours) {
//			System.out.println(hour.getIdHour()+" id actual");
//			System.out.println(id+" ingresado");
			if (hour.getIdHour().equals(id+id)) {
				state= hour.getState();
			}
		}
		return state;
	}

	public StateCurrency getState() {
		int state =0;
		for (Hour hour : hours) {
			state +=hour.getState().getValor();
		}
		return Util.state(state/hours.length);
	}

	public int  getEverage(){
		int average =0;
		for (Hour hour : hours) {
			average +=hour.getState().getValor();
		}
		return average/hours.length;
	}

	public int getValor(){
		return valoAnterior;
	}

	public Hour[] getHours() {
		return hours;
	}

	public String getId() {
		return id;
	}

}
