
public class Actividad02 {

	public static void main(String[] args) {
	
	//Ejercicio 1//
		
	float base = 4, leght = 5;
	double area = ( (base * leght) / 2);
	System.out.println("El area de un triangulo es: " + area);
		
	//Ejercicio 2//
		
	int cal1 = 85, cal2 = 77, cal3 = 54;
	double promedio = ( (cal1 + cal2 + cal3) / 3);
	System.out.println("El promedio es: " + promedio);	
	
	//Ejercicio 3//
	
	double cuenta = 10000.00;
	cuenta -= 2340.00;
	cuenta -= 354.50;
	cuenta -= 1299.99;
	System.out.println("El saldo restatne es de: " + cuenta);
	
	//Ejercicio 4//
	
	int x = 4;
	long operacion = ( 7*(3*x+2)-5*(4*x-3)-1) / 4;
	System.out.println("El resultado es: " + operacion);
	
	//Ejercicio 5//
	
	int num = 0;
	boolean flag = false;
	
	while ( flag != true) {
		long operacion1 = 0;
		num += 1;
		operacion1 = (num / 8) + 12;
		if( operacion1 == 52) {
			flag = true;
			System.out.println("El numero es: " + num);
		}
	}
	
	//Ejercicio 6// 
	
	int perimetro = 252; //metros
	double base1 = perimetro / 3;
	System.out.println("la base es: " + base1);
	
	
	}

}
