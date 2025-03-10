package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Timestamp;

public class Cliente extends Persona implements Fecha {
	
	private int id;
	private LocalDate fechaDeCreacion;
    //Constructores
	public Cliente(String rfc, String name, String tel, String dom)
    {
    	super(rfc, name, tel, dom);
    }
    public Cliente(int id, String rfc, String name, String tel, String dom)
    {
    	super(rfc, name, tel, dom);
        setCid(id);
    }
    
    public Cliente(String rfc, String name, String tel, String dom, LocalDate fecha)
    {
    	super(rfc, name, tel, dom);
        setFechaDeCreacion(fecha);
    }
    
    public Cliente(int id, String rfc, String name, String tel, String dom, LocalDate fecha)
    {
    	super(rfc, name, tel, dom);
        setCid(id);
        setFechaDeCreacion(fecha);
    }
    
    /**
     * Crea un registro en la base de datos para el usuario dado
     * @param c Cliente con los datos a guardar
     * @return 1 si el cliente se creo correctamente 0 si no
     */
    public static int saveClientInDB(Cliente c) {
    	long createdAt = System.currentTimeMillis();
    	return DBConnection.insertIntoTable(TablasDB.CLIENTE, "Nombre, RFC, Telefono, Domicilio, FechaCreacion", c.getNombrePer(), c.getRfc(), c.getTelefono(), c.getDomicilio(), createdAt);
    }
    
    public static Cliente findClientById(int id) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CLIENTE, "Nombre = " + id + "");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Cliente c = formatClient(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return c;
    }
    
    /**
     * Busca un cliente con base en su nombre
     * @param name Nombre del cliente a buscar
     * @return Retorna el primer cliente que coincida con el nombre dado, nulo si no encuentra coincidencias
     */
    public static Cliente findClientByName(String name) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CLIENTE, "Nombre = '" + name + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Cliente c = formatClient(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return c;
    }
    
    /**
     * Busca un cliente con base en su RFC
     * @param rfc RFC del cliente a buscar
     * @return Retorna el primer cliente que coincida con el RFC dado, nulo si no encuentra coincidencias
     */
    public static Cliente findClientByRFC(String rfc) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CLIENTE, "RFC = '" + rfc + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Cliente c = formatClient(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return c;
    }
    
    /**
     * Busca un cliente con base en su nombre Y su RFC
     * @param name Nombre del cliente a buscar
     * @param rfc RFC del cliente a buscar
     * @return Retorna el primer cliente que coincida con el nombre Y RFC dado, nulo si no encuentra coincidencias
     */
    public static Cliente findClientByNameAndRFC(String name, String rfc) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CLIENTE, "Nombre = '" + name + "' AND " + " RFC = '" + rfc + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Cliente c = formatClient(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return c;
    }
    
    /**
     * Actualiza un cliente basado en su id
     * @param updatedClient Objeto cliente con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    private static int updateClientById(Cliente updatedClient) {
    	return DBConnection.updateTableRecords(TablasDB.CLIENTE, "Nombre, RFC, Telefono, Domicilio", "IdCliente = " + updatedClient.getCid(), updatedClient.getNombrePer(), updatedClient.getRfc(), updatedClient.getTelefono(), updatedClient.getDomicilio());
    }
    
    /**
     * Actualiza un cliente basado en su nombre
     * @param clientCurrentName Nombre actual del cliente
     * @param updatedClient Objeto cliente con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    public static int updateClientByName(String clientCurrentName, Cliente updatedClient) {
    	int updatedRows = 0;
    	Cliente temp = findClientByName(clientCurrentName);
    	if (temp != null) {
	    	updatedClient.setCid(temp.getCid());
	    	updatedRows = updateClientById(updatedClient);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Actualiza un cliente basado en su RFC
     * @param clientCurrentRFC RFC actual del cliente
     * @param updatedClient Objeto cliente con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    public static int updateClientByRFC(String clientCurrentRFC, Cliente updatedClient) {
    	int updatedRows = 0;
    	Cliente temp = findClientByRFC(clientCurrentRFC);
    	if (temp != null) {
    		updatedClient.setCid(temp.getCid());
    		updatedRows = updateClientById(updatedClient);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Elimina un cliente de la tabla con base en su id
     * @param id Identificador de cliente
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    private static int deleteClientById(int id) {
    	int deletedRows = 0;
    	
    	deletedRows = DBConnection.deleteTableRecords(TablasDB.CLIENTE, "IdCliente = " + id);
    	
    	return deletedRows;
    }
    
    /**
     * Elimina el primer cliente de la tabla que coincida con el nombre dado
     * Notese que puede haber 2 usuarios con el mismo nombre, por lo que si se desea
     * eliminar a todos debera llamarse este metodo varias veces
     * @param name Nombre del cliente
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    public static int deleteClientByName(String name) {
    	int deletedRows = 0;
    	Cliente temp = findClientByName(name);
    	if (temp != null) {
    		deletedRows = deleteClientById(temp.getCid());
    	}
    	
    	return deletedRows;
    }
    
    /**
     * Elimina un cliente de la tabla con base en su rfc
     * @param rfc RFC del cliente
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    public static int deleteClientByRFC(String rfc) {
    	int deletedRows = 0;
    	Cliente temp = findClientByRFC(rfc);
    	if (temp != null) {
    		deletedRows = deleteClientById(temp.getCid());
    	}
    	
    	return deletedRows;
    }
    
    /**
     * Obtiene todos los clientes de la tabla Cliente
     * @return ArrayList con todos los clientes encontrados en la tabla
     */
	public static ArrayList<Cliente> getAllClients() {
    	ArrayList<Cliente> listaDeClientes = new ArrayList<Cliente>();
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CLIENTE, "");
		
    	try {
			while (rs.next()) {
				Cliente c = formatClient(rs);
				
				if (c != null) {
					listaDeClientes.add(c);
				}
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return listaDeClientes;
	}
    
	/**
	 * Formatea un cliente con base en una instancia dada de ResultSet
	 * @param rs ResultSet de una select query a la tabla clientes en donde se seleccionaron todos los campos
	 * @return Un objeto cliente lleno en todos sus campos o nulo
	 */
    private static Cliente formatClient(ResultSet rs) {
    	Cliente c = null;
		try {
			int id = rs.getInt("IdCliente");
			String nombre = rs.getString("Nombre");
			String rfc = rs.getString("RFC");
			String telefono = rs.getString("Telefono");
			String domicilio = rs.getString("Domicilio");
			Timestamp fechaCreacion = new Timestamp(rs.getLong("FechaCreacion"));
			LocalDate fecha = fechaCreacion.toLocalDateTime().toLocalDate();
			c = new Cliente(id, rfc, nombre, telefono, domicilio, fecha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
    }
    
    //setter
    public void setCid(int id) {
        this.id = id;
    }
    //getter
    private int getCid() {
        return id;
    }
    
	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	/**
	 * Retorna un string con la fecha formateada
	 */
	@Override
	public String formatearFecha(LocalDate date) {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}
	
	@Override
	public String toString() {
		return "Nombre: " + this.getNombrePer() + " RFC: " + this.getRfc() + " Telefono: " + this.getTelefono() + " Domicilio: " + this.getDomicilio()
		+ " Fecha de registro: " + formatearFecha(this.getFechaDeCreacion()) + (this.getCid() > 0 ? (" Id: " + this.getCid()) : "");
	}

	@Override
    public boolean equals(Object c)
    {
        if (!(c instanceof Cliente)) {
                return false;
            }
            
        else{
            Cliente client = (Cliente) c;
            boolean flag1,flag2,flag3,flag4,flag5;
            flag1= this.getNombrePer()== client.getNombrePer();
            flag2= this.getRfc()== client.getRfc();
            flag3= this.getTelefono()== client.getTelefono();
            flag4= this.getDomicilio()==client.getDomicilio();
            flag5= this.getCid()==client.getCid();
            
                if (flag1 && flag2 && flag3 && flag4 && flag5) {
                return true;
            }
        }
        return false;
    }
}
