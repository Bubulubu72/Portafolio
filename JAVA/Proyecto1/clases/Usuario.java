package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {
	//Se declaran los atributos
    private int uid;
    private String nombreUsuario;
    private boolean admin;
    
    //Constructores
    public Usuario(String name, boolean admin) 
    {
        setNombreUs(name);
        setAdmin(admin);
    }

    public Usuario(int id, String name, boolean admin) 
    {
        setUid(id);
        setNombreUs(name);
        setAdmin(admin);
    }
    
    /**
     * Crea un usuario en la tabla Usuario de la base de datos
     * @param user Instancia de la clase usuario (con campos de nombre y admin rellenados)
     * @param password Contraseña del usuario
     * @return 1 si el usuario fue registrado con exito, 0 si no se completo la inserción
     */
    public static int saveUserInDB(Usuario user, String password) {
    	return DBConnection.insertIntoTable(TablasDB.USUARIO, "Nombre, Password, Admin", user.getNombre(), password, user.getAdmin() ? 1 : 0);
    }
    
    /**
     * Obtiene a un usuario basado en su nombre y contraseña
     * @param name Nombre del usuario
     * @param password Contraseña
     * @return Un objeto usuario con los campos llenados si el usuario existe y las credenciales son correctas
     * null en caso de que el usuario no exista o las credenciales sean incorrectas
     */
	public static Usuario userLogin(String name, String password) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.USUARIO, " Nombre = '" + name + "' AND Password = '" + password + "'");
    	Usuario user = null;

    	try {
			rs.next();
			int id = rs.getInt("IdUsuario");
			String nombre = rs.getString("Nombre");
			boolean admin = rs.getBoolean("admin");
			rs.close();
			
			user = new Usuario(id, nombre, admin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return user;
    }
    
    /**
     * Actualiza el nombre y/o la bandera de admin de un usuario en la base de datos
     * @param currentName Nombre actual del usuario
     * @param password Contraseña del usuario
     * @param updatedUser Instancia de usuario con los campos actualizados
     * @return 1 si se actualizo con exito 0 si no fue ase
     */
    public static int updateUserInDB(String currentName, String password, Usuario updatedUser) {
    	Usuario currentUser = userLogin(currentName, password);
    	int updatedRows = 0;
    	if (currentUser != null) {
    		updatedRows = DBConnection.updateTableRecords(TablasDB.USUARIO, "Nombre, Admin", "IdUsuario = " + currentUser.getUid(), updatedUser.getNombre(), updatedUser.getAdmin() ? 1 : 0);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Actualiza la contraseña del usuario en la base de datos
     * @param user Instancia de usuario con la información del usuario
     * @param currentPassword Contraseña actual
     * @param newPassword Nueva contraseña
     * @return 1 si se actualizo con exito 0 si no fue ase
     */
    public static int updateUserPasswordInDB(Usuario user, String currentPassword, String newPassword) {
    	Usuario currentUser = userLogin(user.getNombre(), currentPassword);
    	int updatedRows = 0;
    	if (currentUser != null) {
    		updatedRows = DBConnection.updateTableRecords(TablasDB.USUARIO, "Password", "IdUsuario = " + currentUser.getUid(), newPassword);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Elimina un usuario de la base de datos
     * @param user Instancia de objeto, debe ser un administrador si se quiere eliminar usuarios
     * @param id Id del usuario a eliminar
     * @return 1 si se elimino con exito 0 si no fue ase
     */
    public static int deleteUser(Usuario user, int id) {
    	int deletedRows = 0;
    	if (user.getAdmin()) {
			deletedRows = DBConnection.deleteTableRecords(TablasDB.USUARIO, "IdUsuario = " + id);
    	} else {
    		System.out.println("Solo un usuario con permisos de administrador puede eliminar usuarios");
    	}
    	
    	return deletedRows;
    }
    
    public static Usuario findUserByName(String name) {
    	ResultSet rs = DBConnection.selectQuery("IdUsuario, Nombre, Admin", TablasDB.USUARIO, " Nombre = '" + name + "'");
    	Usuario us = null;
		try {
			if (rs.next()) {
				us = new Usuario(rs.getInt("IdUsuario"), rs.getString("Nombre"), rs.getInt("Admin") == 1 ? true : false);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return us;
    }
    
    public static Usuario findUserById(int id) {
    	ResultSet rs = DBConnection.selectQuery("IdUsuario, Nombre, Admin", TablasDB.USUARIO, " IdUsuario = " + id + "");
    	Usuario us = null;
		try {
			if (rs.next()) {
				us = new Usuario(rs.getInt("IdUsuario"), rs.getString("Nombre"), rs.getInt("Admin") == 1 ? true : false);
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return us;
    }
    
    /**
     * Obtiene y formatea todos los usuarios que hay en la tabla usuario de la base de datos
     * @return ArrayList de usuarios con todos los usuarios de la tabla
     */
    public static ArrayList<Usuario> getAllUsers() {
    	ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
    	ResultSet rs = DBConnection.selectQuery("IdUsuario, Nombre, admin", TablasDB.USUARIO, "");
    	
    	try {
			while(rs.next()) {
				int id = rs.getInt("IdUsuario");
				String nombre = rs.getString("Nombre");
				boolean admin = rs.getBoolean("admin");
				
				listaDeUsuarios.add(new Usuario(id, nombre, admin));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return listaDeUsuarios;
    }
    
    //Setters
    
    // Mantenemos el setUid como privado, ya que solo debe ser modificado por lectura directa en la base de datos
    private void setUid(int id) {
        this.uid = id;
    }
    public void setNombreUs(String name) {
        if (name.strip() != "") {
            //Acepta solo si no está vacio
            this.nombreUsuario = name;
        }
    }
    public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    
    //Getters 
    public int getUid() {
        return uid;
    }
    public String getNombre() {
        return nombreUsuario;
    }
    public boolean getAdmin() {
        return admin;
    }
    
    @Override
    public String toString() {
    	return "Nombre de usuario: " + getNombre() + " Admin: " + getAdmin() + (getUid() > 0 ? " Id: " + getUid() : "");
    }
    
    @Override
    public boolean equals(Object user) {
    	if (!(user instanceof Usuario)) {
    		return false;
    	}
    	
    	Usuario u = (Usuario) user;
    	
    	return this.getUid() == u.getUid();
    }
    
    @Override
    public Object clone() {
    	return new Usuario(getNombre(), getAdmin());
    }
}
