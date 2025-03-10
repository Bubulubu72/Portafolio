package practica2;

public class Racional {
	
	private int numerador = 1, denominador = 2;
	
	public Racional () {
	}
	
	public Racional ( int a, int b) {
		setNumerador(a);
		setDenominador(b);}

	public int getNumerador() {
		return numerador;}

	public int getDenominador() {
		return denominador;}

	public void setNumerador(int numerador) {
		this.numerador = numerador;}

	public void setDenominador(int denominador) {
		this.denominador = denominador;}
	
	public String toString() {
		String c = String.format("{ %d / %d }", getNumerador(), getDenominador());
		return c;
	}
	
	public Racional sumaRacional ( Racional s) {
		Racional tempo = new Racional(this.numerador, this.denominador);
		@SuppressWarnings("unused")
		Racional r;
		int op = 0, den = 0;
		if ( tempo.denominador == s.denominador) {
			op = tempo.numerador + s.numerador;
			return r = new Racional(op, s.denominador);}
		
		op = (tempo.numerador * s.denominador) + 
				( tempo.denominador * s.numerador);
		
		den = tempo.denominador * s.denominador;
		
		return r = new Racional(op, den);
	}
	
	public Racional restaRacional ( Racional s) {
		Racional tempo = new Racional(this.numerador, this.denominador);
		@SuppressWarnings("unused")
		Racional r;
		int op = 0, den = 0;
		if ( tempo.denominador == s.denominador) {
			op = tempo.numerador - s.numerador;
			return r = new Racional(op, s.denominador);}
		
		op = (tempo.numerador * s.denominador) - 
				( tempo.denominador * s.numerador);
		
		den = tempo.denominador * s.denominador;
		
		return r = new Racional( op, den);
	}
	
	public Racional multiplicacionRacional ( Racional s) {
		Racional tempo = new Racional(this.numerador, this.denominador);
		@SuppressWarnings("unused")
		Racional r;
		int op = 0, den = 0;
		
		op = tempo.numerador * s.numerador;
		den = tempo.denominador * s.denominador;
		
		return r = new Racional(op, den);
	}
	
	public Racional divisionRacional ( Racional s) {
		Racional tempo = new Racional(this.numerador, this.denominador);
		@SuppressWarnings("unused")
		Racional r;
		int op = 0, den = 0;
		
		op = tempo.numerador * s.denominador;
		den = tempo.denominador * s.numerador;
		
		return r = new Racional (op, den);
	}
	
	public String reducirRacional ( ) {
		String s = null; 
		int count = 0;
		
		s = String.format("%d enteros y %d / %d", 
				count, this.numerador, this.denominador);
		
		
		if( this.numerador == this.denominador) {
			count = 1; 
			this.denominador = 1;
			this.numerador = 1; 
			return s;}
		
		do {	
		if ( this.numerador > this.denominador) {
			 this.numerador /= this.denominador;
			 count ++;
		}
		
		}while ( this.numerador <= 0);
				
		return s;
	}
	
	
	public boolean equals(Racional r) {
		if ( this.numerador == r.numerador &&
				this.denominador == r.denominador) {
			return true;
		}
		
		return false;
	}
	
	public Racional clone() {
		Racional r = new Racional( this.numerador,this.denominador);
		return r;
	}
	
}
