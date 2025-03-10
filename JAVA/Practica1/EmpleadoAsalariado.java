package mx.iteso.examen2;

import java.util.Calendar;

public class EmpleadoAsalariado extends Empleado implements IFecha{
	
	private double Salario;
	
	public EmpleadoAsalariado(String name, String app, String nss, double sal) {
		super(name, app, nss);
		setSalario(sal);
	}

	@Override
	public int Dia() {
		return Calendar.DAY_OF_MONTH;}

	@Override
	public int mes() {
		return Calendar.MONTH;}

	@Override
	public int año() {
		return Calendar.YEAR;}
	
	public String toString() {
		String msg = String.format("%s con Salario semanal: %.2f \n", super.toString(), this.Salario);
		return msg;
	}
	
	public double ingresos() {
		return this.Salario;
	}
	
	//SETTER & GETTER//
	
	public double getSalario() {
		return Salario;}

	public void setSalario(double salario) {
		Salario = salario;}
	
}
