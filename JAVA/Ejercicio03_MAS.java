

import java.util.Scanner;

public class Ejercicio03_MAS {

	public static void main(String[] args) {
		
		// CAMBIO DE DIVISAS //
		
		String tipo;
		double cambio = 0, cuenta = 0;
		
		Scanner teclado = new Scanner ( System.in);
		
		System.out.print("Cuanto quieres cambiar: ");
		cambio = teclado.nextDouble();
		
		System.out.print("Tipo de cambio: ");
		tipo = teclado.next();		
		
		teclado.close();
		
		if (tipo.equals("USD")) {
			cuenta = cambio / 20.32;}
		
		if ( tipo.equals("MXN")) {
			cuenta = cambio * 20.32;}
		
		System.out.println("Cambiaste: " + cuenta);
		
		// MASA CORPORAL //
		
		Scanner teclado1 = new Scanner ( System.in);
		
		double imc;
		double peso = 0.0, estatura = 0.0;
		
		System.out.print("Cuanto es tu peso: ");
		peso = teclado1.nextDouble();
		
		System.out.print("Cuanto es tu estatura: ");
		estatura = teclado1.nextDouble();
		
		teclado1.close();
		
		imc = peso / Math.pow(estatura, 2);
		System.out.println("Tu IMC es de: " + imc);
		
		// TIEMPO PROMEDIO //
		
		Scanner teclado3 = new Scanner (System.in);
		
		double lunes, miercoles, viernes;
		
		System.out.print("Tiempo del lunes: ");
		lunes = teclado3.nextDouble();
		
		System.out.print("Tiempo del Miercoles: ");
		miercoles = teclado3.nextDouble();
		
		System.out.print("Tiempo del viernes: ");
		viernes = teclado3.nextDouble();
		
		teclado3.close();
		
		System.out.println("El promedio de tiempo es de: " +
				(lunes + miercoles + viernes) / 3);
		
		// **EMPAQUETADO** //
		
		
		//int lapices;
		
		Scanner teclado4 = new Scanner( System.in);
		
		System.out.print("Cantidad de lapices a producir: ");
		//lapices = teclado4.nextInt();
		
		teclado4.close();
		
		//int paquetes = 0;
	
		
		// HIPOTENUSA //
		
		int catA, catO, hipotenusa;
		
		Scanner teclado5 = new Scanner (System.in);
		
		System.out.print("Cateto Adyacente; ");
		catA = teclado5.nextInt();
		
		System.out.print("Cateto Opuesto; ");
		catO = teclado5.nextInt();
		
		teclado5.close();
		
		hipotenusa = (int) (Math.pow(catA, 2) + Math.pow(catO, 2));
		System.out.println("La hipotenusa es: " +
				Math.sqrt(hipotenusa));
		
		// AREA DEL CIRCULO //
		
		double radio = 0.0, area = 0.0;
		
		Scanner teclado6 = new Scanner (System.in);
		
		System.out.print("Radio del circulo: ");
		radio = teclado6.nextDouble();
		
		teclado6.close();
		
		area = Math.PI * Math.pow(radio, 2);
		System.out.println("El area del circulo es: " + 
				area);
		
		// BILLETES //
		
		int saldo = 1343;
		int b200, b50, b20;
		
		b200 = saldo / 200; saldo = saldo % 200;
		b50 = saldo / 50; saldo = saldo % 50;
		b20 = saldo / 20; saldo = saldo % 20;
		
		System.out.println(b200 + " Billetes de 200 \n"
				+ b50 + " Billetes de 50 \n" 
				+ b20 + " Billetes de 20 \n"
				+ saldo + " Monedas restantes");
		
	}

}
