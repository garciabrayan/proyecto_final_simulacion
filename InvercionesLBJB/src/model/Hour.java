package model;

public class Hour {
	String idHour;
	StateCurrency state;
	int value;
		
	public Hour(String idHour,int valor) {
		this.idHour=idHour;
		this.value=valor;
	}
		
	public void setState(StateCurrency state) {
		this.state = state;
	}
	
	public int getValue() {
		return value;
	}

	public StateCurrency getState() {
		return state;
	}
	
	public String getIdHour() {
		return idHour;
	}
}
