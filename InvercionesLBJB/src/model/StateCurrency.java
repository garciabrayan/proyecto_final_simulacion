package model;

public enum StateCurrency {
		
	SUBIO(3) ,BAJO(2) ,ESTABLE(1);
	private StateCurrency(int valor) {
		this.valor=valor;
	}
	
	private int valor;
	
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
