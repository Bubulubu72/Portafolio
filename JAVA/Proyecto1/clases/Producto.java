package clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Producto {
	//Declaración de las variables
	private int id;
    private String nombre, codigo ,categoria;
    private double costo, precio;
    private Number existencia;
    private List<Integer> idsProveedores;
    
    public Producto(int id, double precio, int exis) 
    {    //Constructor
    	setId(id);
        setPrecio(precio);
        setExistencias(exis);
        
        idsProveedores= new ArrayList<Integer>();
        //primera creacion
        
    }
    
    public Producto(int id, String name, double cost, String cod, double pri, int exis, String cat) 
    {    //Constructor
    	setId(id);
        setNombre(name);
        setCosto(cost);
        setCodigo(cod);
        setPrecio(pri);
        setCategoria(cat);
        setExistencias(exis);
        
        idsProveedores= new ArrayList<Integer>();
        //primera creacion
        
    }

    public Producto(String name, double cost, String cod, double pri, int exis, String cat) 
    {    //Constructor
        setNombre(name);
        setCosto(cost);
        setCodigo(cod);
        setPrecio(pri);
        setCategoria(cat);
        setExistencias(exis);
        
        idsProveedores= new ArrayList<Integer>();
        //primera creacion
        
    }
    
    
    public static int addProductToDb(Producto p) {
    	//agregamos el objeto sacando los valores y metiendolos en una query
    	
    	int catId = getOrCreateCategoryByName(p.getCategoria());
    	
    	return DBConnection.insertIntoTable(TablasDB.PRODUCTO,"Codigo, Nombre, Costo, Precio, Existencia, IdCategoria",p.getCodigo(),p.getNombre(),p.getCosto(),p.getPrecio(),p.getExistencias(), catId);
    	
    }
    public static int  deleteAllProductRecords() {
    	//borra todas las entradas de la tabla producto 
    	
    	int a= DBConnection.deleteTableRecords(TablasDB.PRODUCTO, "");
    	
    	
    	return a;
    }
    public static int deleteProductByCode(String code) {
    	//mandamos un objeto producto para hacer update estos deben tener el id 
    	int updatedRows = 0;
    	
    		updatedRows = DBConnection.deleteTableRecords(TablasDB.PRODUCTO, "Codigo = '"+code+"'");
    
    	
    	return updatedRows;
    }
    public static int updateProductByCode(String code, Producto updatedProduct) {
    	//mandamos un objeto producto para hacer update estos deben tener el id 
    	int updatedRows = 0;
    	
    	int catId = getOrCreateCategoryByName(updatedProduct.getCategoria());
    	
    	updatedRows = DBConnection.updateTableRecords(TablasDB.PRODUCTO,"Nombre, Costo, Precio, Existencia, IdCategoria","Codigo = '"+code+"'", updatedProduct.getNombre(),updatedProduct.getCosto(),updatedProduct.getPrecio(),updatedProduct.getExistencias(),catId);
    
    	
    	return updatedRows;
    }
    public static Producto findProductByCode(String code) {
		/*
		 * Mandamos el codigo del producto y se agrega a una query lo que reusulta en un
		 * RS al que se le dara formato mediante formater para retornar el objeto
		 */
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "Codigo= '" + code + "'");
    	
    	try {
    		rs.next();
    	}
    	catch(SQLException P){
    		
    		P.printStackTrace();
    	}
    	
    	Producto p = formatProduct(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    public static Producto findProductById(int id) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "IdProducto= " + id + "");
    	
    	try {
    		rs.next();
    	}
    	catch(SQLException P){
    		
    		P.printStackTrace();
    	}
    	
    	Producto p = formatProduct(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    public static Producto findProductByName(String name) {
    	/*
		 * Mandamos el Name del producto y se agrega a una query lo que reusulta en un
		 * RS al que se le dara formato mediante formater para retornar el objeto
		 */
    	
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "Nombre= '" + name + "'");
    	try {
    		rs.next();
    	}catch(SQLException P){
    		P.printStackTrace();
    		}
    	
    	Producto p = formatProduct(rs);
    	try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return p;
    }
    
    /**
     * Obtiene y formatea todos los productos que hay en la tabla producto de la base de datos
     * @return ArrayList de productos con todos los productos de la tabla
     */
    public static ArrayList<Producto> getAllProducts() {
    	ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.PRODUCTO, "");
    	
    	try {
			while(!rs.isClosed() && rs.next()) {
				int id = rs.getInt("IdProducto");
				String nombre = rs.getString("Nombre");
				String codigo = rs.getString("Codigo");
				Double costo = rs.getDouble("Costo");
				Double precio = rs.getDouble("Precio");
				int existencia = rs.getInt("Existencia");
				String cat = findProductCategoryNameById(rs.getInt("IdCategoria"));
				
				listaDeProductos.add(new Producto(id, nombre, costo, codigo, precio, existencia,cat));
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		}
    	
    	return listaDeProductos;
    }
    
    private static Producto formatProduct(ResultSet rs) {
    	
		/*
		 * esta funcion retorna dentro de un objeto listo para utilizar por java el
		 * contenido de la query where implementada anteriormente
		 */    	
    	Producto a=null;
    	try {
    	String cod=rs.getString("Codigo");
    	String name=rs.getString("Nombre");
    	Double cost=rs.getDouble("Costo");
    	Double pre=rs.getDouble("Precio");
    	int exi=rs.getInt("Existencia");
    	int id = rs.getInt("IdProducto");
    	String cat = findProductCategoryNameById(rs.getInt("IdCategoria"));
    	a=new Producto(id,name,cost,cod,pre,exi,cat);
    	}catch(SQLException e){
    		e.printStackTrace();
    	}
    	
    	
    	return a;
    }
    
    private static int findProductCategoryIdByName(String cat) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CATEGORIA, "Categoria = '" + cat + "'");
    	
    	try {
    		if (rs.next()) {
	    		int id = rs.getInt("IdCategoria");
	    		rs.close();
	    		return id;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return 0;
    }
    
    private static String findProductCategoryNameById(int id) {
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.CATEGORIA, "IdCategoria = " + id);
    	
    	try {
    		if (rs.next()) {
	    		String cat = rs.getString("Categoria");
	    		rs.close();
				return cat;
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return "";
    }
    
    private static int getOrCreateCategoryByName(String name) {
    	// Buscamos el id de la categoria
    	int catId = findProductCategoryIdByName(name);
    	
    	// Si la categoria no existe (es nueva)
    	if (catId == 0) {
    		// La agregamos en la base de datos
    		int res = DBConnection.insertIntoTable(TablasDB.CATEGORIA, "Categoria", name);
    		if (res > 0) {
	    		// Buscamos su id
	    		catId = findProductCategoryIdByName(name);
    		}
    	}
    	
    	return catId;
    }
    
    
    private List<String> getProveedoresNames() {
    	List<String> names = new ArrayList<String>();
    	for (int provId : idsProveedores) {
    		ResultSet rs = DBConnection.selectQuery("Nombre", TablasDB.PROVEEDOR, "IdProveedor = " + provId);
    		try {
				if (rs.next()) {
					names.add(rs.getString("Nombre"));
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    	
    	return names;
    }
    
    
    
    public void setNombre(String name) {
        if (name.strip() != "") {
            //Acepta solo si no estï¿½ vacio
            this.nombre = name;
        }
    }
    public void setCodigo(String code) {
        if (code.strip() != "") {
            //Acepta solo si no estï¿½ vacio
            this.codigo = code;
        }
    }
    public void setCategoria(String category) {
        this.categoria = category;
    }
    public void setCosto(double cost) {
        if (cost > 0) {
            //No debe de ser negativo
            this.costo = cost;
        }
    }
    public void setPrecio(double price) {
        if (price > 0) { 	
            //No debe de ser negativo
            this.precio = price;
        }
    }
    public void setExistencias(int exist) {
        if (exist >= 0) {
            //No debe de ser negativo
            this.existencia = exist;
        }
    }
    public void addToIdsProveedores(int value) {
    	idsProveedores.add(value);
    }
    //Getters 
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public String getCategoria() {
        return categoria;
    }
    public double getCosto() {
        return costo;
    }
    public double getPrecio() {
        return precio;
    }
    public Number getExistencias() {
        return existencia;
    }
    
    @Override
    public String toString()
    {
	    return "Nombre: " + this.getNombre() + " cï¿½digo: " + this.getCodigo() + " Categoria: " + this.getCategoria() + " Costo: $" + this.getCosto()
	        + " Precio: $" + getPrecio() + " Existencia: " + getExistencias() + (idsProveedores.size() > 0 ? "\nLista de proveedores:\n" + getProveedoresNames() : "");
    }
    
    @Override
    public boolean equals(Object p)
    {
    if (!(p instanceof Producto)) {
            return false;
        }
        
    else{
        Producto producto = (Producto) p;
        boolean flag1,flag2,flag3;
        flag1= this.getNombre() == producto.getNombre();
        flag2= this.getCodigo()== producto.getCodigo();
        flag3= this.getCategoria()== producto.getCategoria();
        
           if (flag1 && flag2 && flag3) {
            return true;
        }
    }
    return false;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
