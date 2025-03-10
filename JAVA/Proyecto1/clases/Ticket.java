package clases;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Fecha {
	private List<Producto> productos;
	private LocalDate fechaDeCreacion;
    private Cliente cliente;
    private double subtotal, impuesto, total;
    private int folio;
    private Usuario usuario;
    
    public Ticket(int f, double subtotal, double iva,Cliente c,Usuario u) {
    	
    	//constructor
    	this.setFolio(f);
    	this.setImpuesto(iva);
    	this.setCliente(c);
    	this.setUsuario(u);
    	this.setSubtotal(subtotal);
    	productos = new ArrayList<Producto>();
    }
    
public Ticket(double subtotal, double iva,Cliente c,Usuario u) {
    	
    	//constructor
    	this.setImpuesto(iva);
    	this.setCliente(c);
    	this.setUsuario(u);
    	this.setSubtotal(subtotal);
    	productos = new ArrayList<Producto>();
    }
    
    public Ticket(int f, double subtotal, double iva,Cliente c,Usuario u,LocalDate F) {
    	
    	//constructor para las querys
		this.setFolio(f);
		this.setImpuesto(iva);
		this.setCliente(c);
		this.setUsuario(u);
		this.setSubtotal(subtotal);
    	this.setDate(F);
    	productos = new ArrayList<Producto>();
    }
    
    //setters
    public void addToProductos(Producto pd) {
    	this.productos.add(pd);
    }
    public void setDate(LocalDate L) {this.setFechaDeCreacion(L);}
    public void setFolio(int f) {this.folio=f;}
    public void setTotal(double t) {this.total=t;}
    public void setImpuesto(double iva) {this.impuesto=iva;}
    public void setSubtotal(double st) {this.subtotal=st;}
    public void setCliente(Cliente cl) {this.cliente=cl; }
    public void setUsuario(Usuario user) {this.usuario=user;}

	/*
	 * los genters necesarios ya que la mayoria de veces se imprime el ticket y la
	 * info necesaria
	 */
    public int getFolio() { return this.folio; }
    public double getTotal() {return this.total;}
    public double getSubTotal(){return this.subtotal;}
    public Cliente getCliente() {return this.cliente;}
    public Usuario getUsuario() {return this.usuario;}
    
    public double getInpuesto() {return this.impuesto;}
    
  //funciones calculadoras 
    public double calculateTotal() {
    	
    	
    	double t=0.0;
    	for(Producto a: this.productos) {
    		t+=a.getPrecio();
    	}
    	double iva= t*.16;
    	
    	return t+iva;
    }
    
    public double calculateSubTotal() {
    	double t=0.0;
    	for(Producto a: this.productos) {
    		t+=a.getPrecio();
    	}

    	
    	return t;
    }
    
    public static int saveTicketDB(Ticket a) {
    	long createdAt = System.currentTimeMillis();
    	return DBConnection.insertIntoTable(TablasDB.TICKET, "SubTotal, Impuesto, fecha, IdUsuario",a.getSubTotal(),a.getInpuesto(),createdAt, a.getUsuario().getUid());
    }
    
    public static List<Ticket> getTicketByDate(LocalDate date) {
    	Timestamp timestamp = Timestamp.valueOf(date.atStartOfDay());
    	List<Ticket> tl = new ArrayList<Ticket>();
    	ResultSet rs = DBConnection.selectQuery("*", TablasDB.TICKET, "fecha >= " + timestamp.getTime());
    	
    	try {
			while(rs.next()) {
				int id = rs.getInt("IdTicket");
				double subtotal = rs.getDouble("SubTotal");
				double impuesto = rs.getDouble("Impuesto");
				int idCliente = rs.getInt("IdCliente");
				int idUsuario = rs.getInt("IdUsuario");
				Long time = rs.getLong("fecha");
				Timestamp fechaCreacion = new Timestamp(time);
				LocalDate fecha = fechaCreacion.toLocalDateTime().toLocalDate();
				
				Usuario u = Usuario.findUserById(idUsuario);
				Cliente c = null;
				if (idCliente > 0) {
					c = Cliente.findClientById(idCliente);
				}
				
				tl.add(new Ticket(id, subtotal, impuesto, c, u, fecha));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return tl;
    }

    @Override 
    public String toString() {
    	String rtn="------Empresa Fantasma---------\n";
    	rtn=rtn+"\n\n";
    	if (this.getCliente() != null) {
    		rtn=rtn+"Cliente: "+this.getCliente().getNombrePer()+" \n";
    	}
    	rtn=rtn+"Vendedor: "+this.getUsuario().getNombre()+" \n";
    	rtn=rtn+"Productos:\n";
    	for(Producto a: this.productos) {
    		Producto p = Producto.findProductById(a.getId());
    		rtn=rtn+p.getNombre()+"  precio: "+a.getPrecio()+" Cantidad: " + a.getExistencias() +  "\n";
    		
    	}
    	rtn=rtn+"-------------------------------------\n";
    	rtn=rtn+"Subtotal   "+this.getSubTotal()+"\n";
    	rtn=rtn+"IVA   "+this.getInpuesto()+"\n";
    	rtn=rtn+"total   "+ (this.getSubTotal() + this.getInpuesto());
    	
    	return rtn;
    }
    
	/**
	 * Retorna un string con la fecha formateada
	 */
	@Override
	public String formatearFecha(LocalDate date) {
		return date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear();
	}

	public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}
}
