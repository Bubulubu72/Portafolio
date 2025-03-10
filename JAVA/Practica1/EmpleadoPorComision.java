package Practica1;

public class EmpleadoPorComision extends Empleado implements IFecha{
	
	private double ventasMes, tarifaComision;
	
	
	public EmpleadoPorComision(String name, String app, String nss, double vm, double tc) {
		super(name, app, nss);
		setVentasMes(vm);
		setTarifaComision(tc);
	}

	@Override
	public int Dia() {
		return DIA_DEL_MES;
	}

	@Override
	public int mes() {
		return MES_DEL_AÑO;
	}

	@Override
	public int año() {
		return AÑO;
	}
	
	public String toString() {
		String msg = String.format("%s Empleado por comision \n "
				+ "Ventas del mes: $%.2f \n"
				+ " Tarifa comision %.2f %% \n", super.toString(), this.ventasMes, this.tarifaComision );
		return msg;
	}
	
	public double ingresos() {
		return (this.ventasMes * (this.tarifaComision / 100));
	}
	
	//SETTER´S & GETTER´S//
	public double getVentasMes() {
		return ventasMes;}

	public double getTarifaComision() {
		return tarifaComision;}

	public void setVentasMes(double ventasMes) {
		this.ventasMes = ventasMes;}

	public void setTarifaComision(double tarifaComision) {
		this.tarifaComision = tarifaComision;}
}
