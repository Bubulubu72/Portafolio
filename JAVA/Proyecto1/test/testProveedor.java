package test;

import java.util.*;

import clases.Proveedor;

public class testProveedor {
     public static void main(String[] args) 
     {
    	 Proveedor p1 = new Proveedor("1", "Santorini", "55555555", "Lugar","123");
 		
 		int added = Proveedor.saveProveedorInDB(p1);
 		if (added == 1) {
 			Proveedor query1 = Proveedor.findProveedortByName(p1.getNombrePer());
 			System.out.println("Q1: " + query1.toString());
 			Proveedor query2 = Proveedor.findProveedorByRFC(p1.getRfc());
 			System.out.println("Q2: " + query2.toString());
 			Proveedor query3 = Proveedor.findProveedorByNameAndRFC(p1.getNombrePer(), p1.getRfc());
 			System.out.println("Q3: " + query3.toString());
 		}
 		
 		Proveedor p2 = new Proveedor("abcdefg", "Otro David", "33333333", "Tu casa","456");
 		
 		Proveedor.saveProveedorInDB(p2);
 		
 		System.out.println("All clientes on table before updates\n");
 		
 		ArrayList<Proveedor> queryList = Proveedor.getAllProveedores();
 		for (Proveedor p : queryList) {
 			System.out.println(p.toString());
 		}
 		
 		String currentName = p1.getNombrePer();
 		p1.setNombrePer("Juan");
 		p1.setTelefono("8526751");
 		
 		Proveedor.updateProveedorByName(currentName, p1);
 		
 		String currentRFC = p2.getRfc();
 		p2.setNombrePer("Otro Juan");
 		
 		Proveedor.updateProveedorByRFC(currentRFC, p2);
 		
 		System.out.println("\nAll clientes on table after updates but before deletions\n");
 		
 		queryList = Proveedor.getAllProveedores();
 		for (Proveedor p : queryList) {
 			System.out.println(p.toString());
 		}
 		
 		Proveedor.deleteProveedorByName(p1.getNombrePer());
 		Proveedor.deleteProveedorByRFC(p2.getRfc());
 		
 		System.out.println("\nAll clientes on table after deletions\n");
 		
 		queryList = Proveedor.getAllProveedores();
 		for (Proveedor p : queryList) {
 			System.out.println(p.toString());
 		}
	
}
}
