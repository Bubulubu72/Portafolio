package mx.iteso.util;

public class testRacional {

	public static void main(String[] args) {
		
		Racional r1 = new Racional();
		
		Racional r2 = new Racional (2, 3);
		
		Racional r3 = new Racional (8, 12);
		
		Racional r4 = new Racional (12, 5);
		
		Racional r5 = new Racional (16, 8);
		
		Racional r6 = r3.clone();
		
		r1 = new Racional ( 1, 9);
		
		Racional r7 = r1.sumaRacional(r2);
		
		Racional r8 = r4.restaRacional(r5);
		
		Racional r9 = r2.multiplicacionRacional(r6);
		
		Racional r10 = r3.divisionRacional(r3);
		
		System.out.println(r5.equals(r2));
		
		System.out.println(r3.equals(r2));
		
		Racional [] r = {r1, r2, r3, r4, r5, r6, r7, r8, r9, r10};
		
		for( Racional name : r) {
			System.out.println(name);
		}
	
	}

}

