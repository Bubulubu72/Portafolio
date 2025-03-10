package practica1;

public class TestEmpleado {
	
	public static void imprimirNomina( Empleado [] emp) {
		for( Empleado name: emp) {
			System.out.println("Nombre: " + name.getNombres() + " " +
					name.getApellidos() + ". Salario de la Semana: " +
					name.ingresos());
		}
	}
	
	public static void main(String[] args) {
		
		EmpleadoAsalariado emp1 = new EmpleadoAsalariado("Gillermo", "de Baskerville", "12345678", 800);
		
		EmpleadoPorHoras emp2 = new EmpleadoPorHoras("Alan", "Cadwell", "87654321", 20, 15);
		
		EmpleadoPorComision emp3 = new EmpleadoPorComision("William", "Forrester", "87651234", 100000, 3.5);
		
		EmpeladoBaseComision emp4 = new EmpeladoBaseComision("James", "Bond", "007", 150000, 4.0, 800);
		
		Empleado [] emp = {emp1, emp2, emp3, emp4};
		
		for( Empleado ep : emp) {
			System.out.println(ep.toString());		
		}
		
		imprimirNomina(emp);
		
	}
}
