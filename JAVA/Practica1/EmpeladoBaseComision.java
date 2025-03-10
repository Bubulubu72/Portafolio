package practica1;

public class EmpeladoBaseComision extends EmpleadoPorComision{
	
	private double SalarioBase;
	
	public EmpeladoBaseComision(String name, String app, String nss, double vm, double tc, double sb) {
		super(name, app, nss, vm, tc);
		setSalarioBase(sb);
	}
	
	public String toString() {
		String msg = String.format("%s y con salario base de : $%.2f \n", super.toString(), this.SalarioBase );
		return msg;
	}
	
	public double ingresos() {
		return (super.ingresos() + this.SalarioBase);
	}
	
	
	//SETTER & GETTER//
	public double getSalarioBase() {
		return SalarioBase;}

	public void setSalarioBase(double salarioBase) {
		SalarioBase = salarioBase;}	
}
