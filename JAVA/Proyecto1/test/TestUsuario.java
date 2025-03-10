package test;

import java.util.ArrayList;

import clases.DBConnection;
import clases.TablasDB;
import clases.Usuario;

public class TestUsuario {

	public static void main(String[] args) {
		Usuario u1 = new Usuario("Diego", true);
		Usuario.saveUserInDB(u1, "123456");
		Usuario loggedUser = Usuario.userLogin(u1.getNombre(), "123456");
		
		System.out.println(loggedUser.toString());
		
		String currentName = loggedUser.getNombre();
		loggedUser.setNombreUs("Diego Vazquez");
		Usuario.updateUserInDB(currentName, "123456", loggedUser);
		
		Usuario u2 = new Usuario("Otro Diego", true);
		Usuario.saveUserInDB(u2, "789");
		
		Usuario thirdUser = Usuario.userLogin(loggedUser.getNombre(), "123456");
		System.out.println(thirdUser.toString());
		
		// Print all users table
		ArrayList<Usuario> users = Usuario.getAllUsers();
		for (Usuario user : users) {
			System.out.println(user.toString());
		}
		
		int u = Usuario.deleteUser(loggedUser, 2);
		if (u > 0) {
			System.out.println("Usuario 2 Eliminado correctamente");
		}
		
		users = Usuario.getAllUsers();
		for (Usuario user : users) {
			System.out.println(user.toString());
		}
		
		DBConnection.deleteTableRecords(TablasDB.USUARIO, "");
	}

}
