package test;
import clases.*;

public class testTicket {

	public static void main(String[] args) {
		
		Producto searched=	Producto.findProductByCode("ahsdasda");
		
		//buscamos el producto en la base de datos
		Producto searched2=Producto.findProductByName("PlatanoChiapasMamadisimo");
		
		//System.out.println(searched.getNombre());
		//System.out.println(searched2.getNombre());
		Producto a[]= {searched,searched2};
		
		Cliente q1=Cliente.findClientByName("Juan");
		
		//Ticket test= new Ticket("69420",a,.16,q1,q2);
		//test.printProductos();
		//System.out.println("el Sub-total es "+test.getSubTotal());
		//System.out.println("el total es "+test.getTotal());
		//System.out.println(test);
	}

}
