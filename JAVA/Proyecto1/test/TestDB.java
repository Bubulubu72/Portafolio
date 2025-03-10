package test;

import java.sql.ResultSet;
import java.sql.SQLException;

import clases.DBConnection;
import clases.TablasDB;

public class TestDB {

	public static void main(String[] args) {
		// DBConnection.connect();
		// DBConnection.createNewTable("PRODUCTOS", "id integer primary key, \ncodigo text not null, \nnombre text not null, \ncosto real not null, \nprecio real not null, \nexistencia real not null\n");
		/*DBConnection.insertIntoTable("PRODUCTOS", "id, codigo, nombre, costo, precio, existencia", 1, "123456", "Producto 1", 15.50, 21.0, 0);
		DBConnection.insertIntoTable("PRODUCTOS", "id, codigo, nombre, costo, precio, existencia", 2, "456321", "Producto 2", 30.50, 45.75, 10);*/
		//DBConnection.insertIntoTable("PRODUCTOS", "id, codigo, nombre, costo, precio, existencia", 3, "987654", "Producto 3", 50.25, 100.00, 3);
		
		//ResultSet rs = DBConnection.selectQuery("id, codigo, existencia", "PRODUCTOS", "");
		//ResultSet rs1 = DBConnection.selectQuery("id, codigo, nombre, costo", "PRODUCTOS", "id = 1");
		
		/*try {
			while(rs.next()) {
				try {
					System.out.println(rs.getInt("id"));
					System.out.println(rs.getString("codigo"));
					System.out.println(rs.getDouble("existencia"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(rs1.next()) {
				try {
					System.out.println(rs1.getInt("id"));
					System.out.println(rs1.getString("codigo"));
					System.out.println(rs1.getString("nombre"));
					System.out.println(rs1.getDouble("costo"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		// DBConnection.updateTableRecords("PRODUCTOS", "codigo, nombre", "id = 1", "789", "Otro producto");
		// DBConnection.updateTableRecords("PRODUCTOS", "existencia", "", 5.0);
		
		// DBConnection.deleteTableRecords("PRODUCTOS", "id = 1");
		// DBConnection.deleteTableRecords("PRODUCTOS", "");
		//DBConnection.createProyectTables();
	}

}
