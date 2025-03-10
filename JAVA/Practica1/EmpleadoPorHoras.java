package practica1;

public class EmpleadoPorHoras extends Empleado implements IFecha{
	
	private double sueldoHora;
	private int HorasPorSeamana;
	
	public EmpleadoPorHoras(String name, String app, String nss, double sh, int hps) {
		super(name, app, nss);
		setSueldoHora(sh);
		setHorasPorSeamana(hps);
	}

	@Override
	public int Dia() {
		return DIA_DEL_MES;}

	@Override
	public int mes() {
		return MES_DEL_AÑO;}

	@Override
	public int año() {
		return AÑO;}
	
	public String toString() {
		String msg = String.format("%s Empleado por horas con salario por horas: %.2f \n "
				+ "Horas por semana: %d \n", super.toString(), this.sueldoHora, this.HorasPorSeamana );
		return msg;
	}
	
	public double ingresos() {
		return (this.sueldoHora * this.HorasPorSeamana);
	}
	
	//GETTER´S & SETTER´S//
	
	public double getSueldoHora() {
		return sueldoHora;}

	public int getHorasPorSeamana() {
		return HorasPorSeamana;}

	public void setSueldoHora(double sueldoHora) {
		this.sueldoHora = sueldoHora;}

	public void setHorasPorSeamana(int horasPorSeamana) {
		HorasPorSeamana = horasPorSeamana;}	
}
