package mx.iteso.examen2;

public class Empleado {
	
	private String nombres, apellidos, nSeguroSocial;
	
	public Empleado( String name, String app, String nss) {
		setNombres(name);
		setApellidos(app);
		setnSeguroSocial(nss);
	}
	
	
	//SETTER´S & GETTER´S//
	
	public String getNombres() {
		return nombres;}

	public String getApellidos() {
		return apellidos;}

	public String getnSeguroSocial() {
		return nSeguroSocial;}

	public void setNombres(String nombres) {
		this.nombres = nombres;}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;}

	public void setnSeguroSocial(String nSeguroSocial) {
		this.nSeguroSocial = nSeguroSocial;}
	
	//METODOS//
	
	public String toString() {
		String msg = String.format("Nombre: %s %s \n No. Seguro Social: %s \n",
									this.nombres, this.apellidos, this.nSeguroSocial);
		return msg;
	}
	
	public double ingresos() {
		return 0;
	}
	
}
