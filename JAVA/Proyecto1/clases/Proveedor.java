package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public class Proveedor extends Persona implements Fecha{
	
	private String razonSocial;
	private int pId;
	private LocalDate fechaDeCreacion;
	
    //Constructores
	public Proveedor(String rfc, String name, String tel, String dom,String razon)
    {
    	super(rfc, name, tel, dom);
    	setRazon(razon);
    }
    public Proveedor(int id, String rfc, String name, String tel, String dom,String razon)
    {
    	super(rfc, name, tel, dom);
        setPid(id);
        setRazon(razon);
    }
    
    public Proveedor(String rfc, String name, String tel, String dom,String razon, LocalDate fecha)
    {
    	super(rfc, name, tel, dom);
        setFechaDeCreacion(fecha);
        setRazon(razon);
    }
    
    public Proveedor(int id, String rfc, String name, String tel, String dom, String razon, LocalDate fecha)
    {
    	super(rfc, name, tel, dom);
        setPid(id);
        setRazon(razon);
        setFechaDeCreacion(fecha);
    }
    
    //Metodos para la base
    /**
     * Crea un registro en la base de datos para el usuario dado
     * @param p Proveedor con los datos a guardar
     * @return 1 si el proveedor se creo correctamente 0 si no
     */
    public static int saveProveedorInDB(Proveedor p) {
    	long createdAt = System.currentTimeMillis();
    	return DBConnection.insertIntoTable(TablasDB.PROVEEDOR, "Nombre, RFC, Telefono, Domicilio,RazonSocial, FechaCreacion", p.getNombrePer(), p.getRfc(), p.getTelefono(), p.getDomicilio(),p.getRazon(), createdAt);
    }
    
    /**
     * Busca un proveedor con base en su nombre
     * @param name Nombre del proveedor a buscar
     * @return Retorna el primer proveedor que coincida con el nombre dado, nulo si no encuentra coincidencias
     */
    public static Proveedor findProveedortByName(String name) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PROVEEDOR, "Nombre = '" + name + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Proveedor p = formatProveedor(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    /**
     * Busca un proveedor con base en su RFC
     * @param rfc RFC del proveedor a buscar
     * @return Retorna el primer proveedor que coincida con el RFC dado, nulo si no encuentra coincidencias
     */
    public static Proveedor findProveedorByRFC(String rfc) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PROVEEDOR, "RFC = '" + rfc + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Proveedor p = formatProveedor(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    /**
     * Busca un proveedor con base en su nombre Y su RFC
     * @param name Nombre del proveedor a buscar
     * @param rfc RFC del proveedor a buscar
     * @return Retorna el primer proveedor que coincida con el nombre Y RFC dado, nulo si no encuentra coincidencias
     */
    public static Proveedor findProveedorByNameAndRFC(String name, String rfc) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PROVEEDOR, "Nombre = '" + name + "' AND " + " RFC = '" + rfc + "'");
    	try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	Proveedor p = formatProveedor(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    /**
     * Actualiza un proveedor basado en su id
     * @param updatedClient Objeto proveedor con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    private static int updateProveedortById(Proveedor updatedProveedor) {
    	return DBConnection.updateTableRecords(TablasDB.PROVEEDOR, "Nombre, RFC, Telefono, Domicilio","RazonSocial", "IdProveedor = " + updatedProveedor.getPid(), updatedProveedor.getNombrePer(), updatedProveedor.getRfc(), updatedProveedor.getTelefono(), updatedProveedor.getDomicilio(),updatedProveedor.getRazon());
    }
    
    /**
     * Actualiza un proveedor basado en su nombre
     * @param proveedorCurrentName Nombre actual del proveedor
     * @param updatedProveedor Objeto proveedor con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    public static int updateProveedorByName(String proveedorCurrentName, Proveedor updatedProveedor) {
    	int updatedRows = 0;
    	Proveedor temp = findProveedortByName(proveedorCurrentName);
    	if (temp != null) {
	    	updatedProveedor.setPid(temp.getPid());
	    	updatedRows = updateProveedortById(updatedProveedor);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Actualiza un proveedor basado en su RFC
     * @param proveedorCurrentRFC RFC actual del proveedor
     * @param updatedProveedor Objeto proveedor con los campos actualizados al valor deseado
     * @return 1 si se actualizo correctamente 0 si no
     */
    public static int updateProveedorByRFC(String proveedorCurrentRFC, Proveedor updatedProveedor) {
    	int updatedRows = 0;
    	Proveedor temp = findProveedorByRFC(proveedorCurrentRFC);
    	if (temp != null) {
    		updatedProveedor.setPid(temp.getPid());
    		updatedRows = updateProveedortById(updatedProveedor);
    	}
    	
    	return updatedRows;
    }
    
    /**
     * Elimina un proveedor de la tabla con base en su id
     * @param id Identificador de proveedor
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    private static int deleteProveedorById(int id) {
    	int deletedRows = 0;
    	
    	deletedRows = DBConnection.deleteTableRecords(TablasDB.PROVEEDOR, "IdProveedor = " + id);
    	
    	return deletedRows;
    }
    
    /**
     * Elimina el primer proveedor de la tabla que coincida con el nombre dado
     * Notese que puede haber 2 proveedores con el mismo nombre, por lo que si se desea
     * eliminar a todos debera llamarse este metodo varias veces
     * @param name Nombre del proveedor
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    public static int deleteProveedorByName(String name) {
    	int deletedRows = 0;
    	Proveedor temp = findProveedortByName(name);
    	if (temp != null) {
    		deletedRows = deleteProveedorById(temp.getPid());
    	}
    	
    	return deletedRows;
    }
    
    /**
     * Elimina un proveedor de la tabla con base en su rfc
     * @param rfc RFC del proveedor
     * @return 1 si se elimino correctamente 0 de otra forma
     */
    public static int deleteProveedorByRFC(String rfc) {
    	int deletedRows = 0;
    	Proveedor temp = findProveedorByRFC(rfc);
    	if (temp != null) {
    		deletedRows = deleteProveedorById(temp.getPid());
    	}
    	
    	return deletedRows;
    }
    
    /**
     * Obtiene todos los proveedores de la tabla Proveedor
     * @return ArrayList con todos los proveedores encontrados en la tabla
     */
	public static ArrayList<Proveedor> getAllProveedores() {
    	ArrayList<Proveedor> listaDeProveedores = new ArrayList<Proveedor>();
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PROVEEDOR, "");
		
    	try {
			while (rs.next()) {
				Proveedor p = formatProveedor(rs);
				
				if (p != null) {
					listaDeProveedores.add(p);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return listaDeProveedores;
	}
    
	/**
	 * Formatea un proveedor con base en una instancia dada de ResultSet
	 * @param rs ResultSet de una select query a la tabla proveedores en donde se seleccionaron todos los campos
	 * @return Un objeto proveedor lleno en todos sus campos o nulo
	 */
    private static Proveedor formatProveedor(ResultSet rs) {
    	Proveedor p = null;
		try {
			int id = rs.getInt("IdProveedor");
			String nombre = rs.getString("Nombre");
			String rfc = rs.getString("RFC");
			String telefono = rs.getString("Telefono");
			String domicilio = rs.getString("Domicilio");
			String razon= rs.getString("RazonSocial");
			Timestamp fechaCreacion = new Timestamp(rs.getLong("FechaCreacion"));
			LocalDate fecha = fechaCreacion.toLocalDateTime().toLocalDate();
			p = new Proveedor(id, rfc, nombre, telefono, domicilio,razon, fecha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
    }

    //setters
    public void setPid(int id) {
      this.pId=id;
        }
    
    public void setRazon(String razon) {
        if (razon.strip() != "") {
            //Acepta solo si no está vacio
            this.razonSocial = razon;
        }
    }
    
	public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
	
	
    //getters
    public int getPid() {
        return pId;
    }
    
    public String getRazon()
    {
    return razonSocial;
    }
    
	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}
	
	/**
	 * Retorna un string con la fecha formateada
	 */
	@Override
	public String formatearFecha(LocalDate date) {
		return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
	}
	
    //Metodo toString
    @Override
    public String toString()
    {
  return "Nombre: " + this.getNombrePer() + " RFC: " + this.getRfc() + " Telefono: " + this.getTelefono() + " Domicilio: " + this.getDomicilio()
        + " Razon Social: " + this.getRazon() + (Integer.valueOf(this.getPid())> 0 ? (" Id: " + this.getPid()) : "") + " Agregado el: "
		+ formatearFecha(this.getFechaDeCreacion());
    
    }
    
    //Metodo equals
    @Override
    public boolean equals(Object pr)
    {
    if (!(pr instanceof Proveedor)) {
            return false;
        }
        
    else{
        Proveedor proveedor = (Proveedor) pr;
        boolean flag1,flag2,flag3,flag4,flag5,flag6;
        flag1= this.getNombrePer()== proveedor.getNombrePer();
        flag2= this.getRfc()== proveedor.getRfc();
        flag3= this.getTelefono()== proveedor.getTelefono();
        flag4= this.getDomicilio()==proveedor.getDomicilio();
        flag5= this.getPid()==proveedor.getPid();
        flag6= this.getRazon()== proveedor.getRazon();
        
           if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {
            return true;
        }
    }
    return false;
    }
}
