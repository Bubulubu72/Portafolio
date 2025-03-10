package test;

import java.util.ArrayList;

import clases.Cliente;

public class TestCliente {

	public static void main(String[] args) {
		Cliente c1 = new Cliente("1", "Diego Vazquez", "33333333", "Mi casa");
		
		int added = Cliente.saveClientInDB(c1);
		if (added == 1) {
			Cliente query1 = Cliente.findClientByName(c1.getNombrePer());
			System.out.println("Q1: " + query1.toString());
			Cliente query2 = Cliente.findClientByRFC(c1.getRfc());
			System.out.println("Q2: " + query2.toString());
			Cliente query3 = Cliente.findClientByNameAndRFC(c1.getNombrePer(), c1.getRfc());
			System.out.println("Q3: " + query3.toString());
		}
		
		Cliente c2 = new Cliente("vndfuiv2e", "Otro Diego Vazquez", "444444444", "Su casa");
		
		Cliente.saveClientInDB(c2);
		
		System.out.println("All clientes on table before updates\n");
		
		ArrayList<Cliente> queryList = Cliente.getAllClients();
		for (Cliente c : queryList) {
			System.out.println(c.toString());
		}
		
		String currentName = c1.getNombrePer();
		c1.setNombrePer("Juan");
		c1.setTelefono("8526751");
		
		Cliente.updateClientByName(currentName, c1);
		
		String currentRFC = c2.getRfc();
		c2.setNombrePer("Otro Juan");
		
		Cliente.updateClientByRFC(currentRFC, c2);
		
		System.out.println("\nAll clientes on table after updates but before deletions\n");
		
		queryList = Cliente.getAllClients();
		for (Cliente c : queryList) {
			System.out.println(c.toString());
		}
		
		Cliente.deleteClientByName(c1.getNombrePer());
		Cliente.deleteClientByRFC(c2.getRfc());
		
		System.out.println("\nAll clientes on table after deletions\n");
		
		queryList = Cliente.getAllClients();
		for (Cliente c : queryList) {
			System.out.println(c.toString());
		}
	}

}
