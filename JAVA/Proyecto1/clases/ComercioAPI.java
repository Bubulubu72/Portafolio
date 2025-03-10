package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ComercioAPI {
	/**
	 * Inicializa el proyecto llamando al metodo connect que se conecta a la base de datos,
	 * si esta no existe la crea y se conecta y se crean las tablas
	 */
	public static void init() {
		DBConnection.connect();
		createProyectTables();
	}
	
	/**
     * Crea todas las tablas necesarias para el proyecto
     */
    private static void createProyectTables() {
    	DBConnection.createNewTable(TablasDB.USUARIO, "IdUsuario INTEGER primary key, Nombre TEXT NOT NULL, Password TEXT NOT NULL, admin INTEGER DEFAULT 0");
    	DBConnection.createNewTable(TablasDB.CLIENTE, "IdCliente INTEGER primary key, Nombre TEXT NOT NULL, RFC TEXT NOT NULL, Telefono TEXT, Domicilio TEXT, FechaCreacion INTEGER NOT NULL");
    	DBConnection.createNewTable(TablasDB.PROVEEDOR, "IdProveedor INTEGER primary key, Nombre TEXT NOT NULL, RFC TEXT NOT NULL, Telefono TEXT, Domicilio TEXT, RazonSocial TEXT, FechaCreacion INTEGER NOT NULL");
    	DBConnection.createNewTable(TablasDB.CATEGORIA, "IdCategoria INTEGER primary key, Categoria TEXT NOT NULL");
    	DBConnection.createNewTable(TablasDB.PRODUCTO, "IdProducto INTEGER primary key, Codigo TEXT NOT NULL UNIQUE, Nombre TEXT NOT NULL, Costo REAL NOT NULL, PRECIO REAL NOT NULL, Existencia REAL NOT NULL, IdCategoria INTEGER NOT NULL, " + setForeignKeyConstraint("IdCategoria", TablasDB.CATEGORIA));
    	DBConnection.createNewTable(TablasDB.PRODUCTOPROVEEDOR, "IdProductoProveedor INTEGER primary key, Codigo TEXT NOT NULL, Costo REAL NOT NULL, IdProducto INTEGER NOT NULL, IdProveedor INTEGER NOT NULL, " + setForeignKeyConstraint("IdProducto", TablasDB.PRODUCTO) + ", " + setForeignKeyConstraint("IdProveedor", TablasDB.PROVEEDOR));
    	DBConnection.createNewTable(TablasDB.TICKET, "IdTicket INTEGER primary key, SubTotal REAL NOT NULL, Impuesto REAL NOT NULL, fecha INTEGER NOT NULL, IdUsuario INTEGER NOT NULL, IdCliente INTEGER, " + setForeignKeyConstraint("IdUsuario", TablasDB.USUARIO) + ", " + setForeignKeyConstraint("IdCliente", TablasDB.CLIENTE));
    	DBConnection.createNewTable(TablasDB.DETALLETICKET, "IdTicket INTEGER NOT NULL, IdProducto INTEGER NOT NULL, Precio REAL NOT NULL, Cantidad REAL NOT NULL, " + setForeignKeyConstraint("IdTicket", TablasDB.TICKET) + ", " + setForeignKeyConstraint("IdProducto", TablasDB.PRODUCTO));
    }
    
    /**
     * Genera un string con la sintaxis de llave foranea valida en SQLite
     * Helper para sintaxis larga de SQLite
     * 
     * @param foreignKey Nombre de la llave foranea
     * @param tableName Nombre de la tabla
     * @return Sintaxis de llave foranea para sentencias de creación/modificación de tablas en la base de datos
     */
    private static String setForeignKeyConstraint(String foreignKey, String tableName) {
    	return "FOREIGN KEY (" + foreignKey + ") REFERENCES " + tableName + " (" + foreignKey + ")";
    }
    
    // #region Producto
    
    public static int crearProducto(Producto p) {
    	return Producto.addProductToDb(p);
    }
    
    public static int actualizarProducto(String oldCode, Producto p) {
    	return Producto.updateProductByCode(oldCode, p);
    }
    
    public static int eliminarProductoConCodigo(String code) {
    	return Producto.deleteProductByCode(code);
    }
    
    public static Producto buscarProductoConCodigo(String code) {
    	Producto p = Producto.findProductByCode(code);
    	
    	if (p != null) {
    		agregarProvedoresIdAProducto(p);
    	}
    	
    	return p;
    }
    
    public static Producto buscarProductoConNombre(String name) {
    	Producto p = Producto.findProductByName(name);
    	
    	if (p != null) {
	    	
    		agregarProvedoresIdAProducto(p);
    	}
    	
    	return p;
    }
    
    public static List<Producto> obtenerProductos() {
    	List<Producto> productos = Producto.getAllProducts();
    	
    	for (Producto producto : productos) {
    		agregarProvedoresIdAProducto(producto);
    	}
    	
    	return productos;
    }
    
    public static int agregarProveedorIdAProducto(String codigo, int provedorId) {
    	Producto p = buscarProductoConCodigo(codigo);
    	if (p != null) {
    		return DBConnection.insertIntoTable(TablasDB.PRODUCTOPROVEEDOR, "Codigo, Costo, IdProducto, IdProveedor", codigo, p.getCosto(), p.getId(), provedorId);
    	}
    	
    	return 0;
    }
    
    private static void agregarProvedoresIdAProducto(Producto p) {
    	try {
    		ResultSet IdProveedores = DBConnection.selectQuery("IdProveedor", TablasDB.PRODUCTOPROVEEDOR, "IdProducto = " + p.getId());
			while (IdProveedores.next()) {
				int idProveedor = IdProveedores.getInt("IdProveedor");
				p.addToIdsProveedores(idProveedor);
			}
			
			IdProveedores.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    // #endRegion Producto
    
    // #region Usuario
    
    public static Usuario validarUsuario(String name, String password) {
    	return Usuario.userLogin(name, password);
    }
    
    public static int crearUsuario(Usuario u, String password) {
    	return Usuario.saveUserInDB(u, password);
    }
    
    public static Usuario buscarUsuarioPorNombre(String name) {
    	return Usuario.findUserByName(name);
    }
    
    public static int actualizarUsuario(String currentName, String password, Usuario updatedUser) {
    	return Usuario.updateUserInDB(currentName, password, updatedUser);
    }
    
    public static int actualizarContraseñaDeUsuario(Usuario user, String currentPassword, String newPassword) {
    	return Usuario.updateUserPasswordInDB(user, currentPassword, newPassword);
    }
    
    public static int eliminarUsuario(Usuario user, int id) {
    	return Usuario.deleteUser(user, id);
    }
    
    public static List<Usuario> obtenerUsuarios() {
    	return Usuario.getAllUsers();
    }
    
    // #endRegion usuario
    
    // #region Cliente
    
    public static int crearCliente(Cliente c) {
    	return Cliente.saveClientInDB(c);
    }
    
    public static Cliente buscarClientePorNombre(String name) {
    	return Cliente.findClientByName(name);
    }
    
    public static Cliente buscarClientePorRFC(String RFC) {
    	return Cliente.findClientByRFC(RFC);
    }
    
    public static int actualizarClientePorNombre(String name, Cliente c) {
    	return Cliente.updateClientByName(name, c);
    }
    
    public static int actualizarClientePorRFC(String RFC, Cliente c) {
    	return Cliente.updateClientByRFC(RFC, c);
    }
    
    public static int eliminarClientePorNombre(String name) {
    	return Cliente.deleteClientByName(name);
    }
    
    public static int eliminarClientePorRFC(String RFC) {
    	return Cliente.deleteClientByRFC(RFC);
    }
    
    public static List<Cliente> obtenerClientes() {
    	return Cliente.getAllClients();
    }
    
    // #endRegion Cliente
    
    // #region Proveedor
    
    public static int crearProveedor(Proveedor p) {
    	return Proveedor.saveProveedorInDB(p);
    }
    
    public static Proveedor buscarProveedorPorNombre(String name) {
    	return Proveedor.findProveedortByName(name);
    }
    
    public static Proveedor buscarProveedorPorRFC(String RFC) {
    	return Proveedor.findProveedorByRFC(RFC);
    }
    
    public static int actualizarProveedorPorNombre(String name, Proveedor p) {
    	return Proveedor.updateProveedorByName(name, p);
    }
    
    public static int actualizarProveedorPorRFC(String RFC, Proveedor p) {
    	return Proveedor.updateProveedorByRFC(RFC, p);
    }
    
    public static int eliminarProveedorPorNombre(String name) {
    	return Proveedor.deleteProveedorByName(name);
    }
    
    public static int eliminarProveedorPorRFC(String RFC) {
    	return Proveedor.deleteProveedorByRFC(RFC);
    }
    
    public static List<Proveedor> obtenerProveedores() {
    	return Proveedor.getAllProveedores();
    }
    
    // #endRegion Proveedor
    
    // #region Ticket
    
    public static int crearTicket(double subtotal, double impuesto, Cliente c, Usuario u, List<Producto> prods) {
    	int res = 0;
    	res = Ticket.saveTicketDB(new Ticket(subtotal, impuesto, c, u));
    	
    	ResultSet rs = DBConnection.selectQueryOrderBy("IdTicket", TablasDB.TICKET, "IdTicket DESC");
    	try {
			rs.next();
			int idTicket = rs.getInt("IdTicket");
			rs.close();
			int prevRes = res;
	    	for (Producto p : prods) {
	    		res += DBConnection.insertIntoTable(TablasDB.DETALLETICKET, "IdTicket, IdProducto, Precio, Cantidad", idTicket, p.getId(), p.getPrecio(), p.getExistencias());
	    		if (res > prevRes) {
	    			Producto updatedProduct = Producto.findProductById(p.getId());
	    			updatedProduct.setExistencias(updatedProduct.getExistencias().intValue() - p.getExistencias().intValue());
	    			Producto.updateProductByCode(updatedProduct.getCodigo(), updatedProduct);
	    		}
	    		
	    		prevRes = res;
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return res == prods.size() + 1 ? 1 : 0;
    }
    
    public static List<Ticket> obtenerTicketsDelDia(LocalDate day) {
    	List<Ticket> tl = Ticket.getTicketByDate(day);
    	
    	for (Ticket t : tl) {
    	
	    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.DETALLETICKET, "IdTicket = " + t.getFolio());
	    	
	    	try {
				while (rs.next()) {
					Producto p = new Producto(rs.getInt("IdProducto"), rs.getDouble("Precio"), rs.getInt("Cantidad"));
					t.addToProductos(p);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	return tl;
    }
    
    // #endRegion Ticket
}
