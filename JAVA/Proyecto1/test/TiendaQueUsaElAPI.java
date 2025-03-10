package test;

import clases.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TiendaQueUsaElAPI {

	public static void main(String[] args) {
		ComercioAPI.init();
		
		int res = 0;
		Producto p = null;
		Proveedor pr = null;
		Cliente c = null;
		String codigoONombre;
		boolean firstTime = false;
		
		List<Usuario> ul = ComercioAPI.obtenerUsuarios();
		Usuario user = null;
		
		while (ul.isEmpty()) {
			firstTime = true;
			createDefaultUser();
			ul = ComercioAPI.obtenerUsuarios();
		}
		
		if (!firstTime) {
			while (user == null) {
				user = logUser();
			}
		} else {
			user = ul.get(0);
		}

		boolean repeat = true;
		
		while (repeat) {
			String opcion = printMenuAndGetSelectedOption();
			switch(opcion) {
				case "1":					
					res = ComercioAPI.crearProducto(createProducto());
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Producto agregado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar el producto", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				break;
				case "2":
					p = ComercioAPI.buscarProductoConCodigo(JOptionPane.showInputDialog("Codigo:"));
					printObject(p, "No se encontro un producto con este codigo");
					break;
				case "3":
					p = ComercioAPI.buscarProductoConNombre(JOptionPane.showInputDialog("Nombre:"));
					
					printObject(p, "No se encontro un producto con este nombre");
					break;
				case "4":
					List<Producto> lp = ComercioAPI.obtenerProductos();
					for (Producto prod : lp) {
						System.out.println(prod.toString());
					}
					break;
				case "5":
					res = addProveedorToProductoProveedor();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Relación producto-proveedor registrada exitosamente");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo registrar la relación es posible que el producto o"
								+ " proveedor indicados no existan", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "6":
					res = ComercioAPI.eliminarProductoConCodigo(JOptionPane.showInputDialog("Codigo:"));
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Producto eliminado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "7":
					res = ComercioAPI.crearProveedor(createProveedor());
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Proveedor agregado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo agregar el proveedor", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "8":
					pr = ComercioAPI.buscarProveedorPorNombre(JOptionPane.showInputDialog("Nombre:"));
					
					printObject(pr, "No se encontro un proveedor con este nombre");
					break;
				case "9":
					pr = ComercioAPI.buscarProveedorPorRFC(JOptionPane.showInputDialog("RFC:"));
					
					printObject(pr, "No se encontro un proveedor con este RFC");
					break;
				case "10":
					List<Proveedor> lprov = ComercioAPI.obtenerProveedores();
					for (Proveedor prov : lprov) {
						System.out.println(prov.toString());
					}
					break;
				case "11":
					res = ComercioAPI.eliminarProveedorPorRFC(JOptionPane.showInputDialog("RFC:"));
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "12":
					if (user.getAdmin()) {
						res = createUser();
						
						if (res > 0) {
							JOptionPane.showMessageDialog(null, "Usuario creado con exito");
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo crear el usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Solo el admin puede crear usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "13":
					res = ComercioAPI.eliminarUsuario(user, ComercioAPI.buscarUsuarioPorNombre(JOptionPane.showInputDialog("Nombre del usuario:")).getUid());
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Usuario eliminado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "14":
					if (user.getAdmin()) {
						List<Usuario> luser = ComercioAPI.obtenerUsuarios();
						for (Usuario us : luser) {
							System.out.println(us.toString());
						}
					} else {
						JOptionPane.showMessageDialog(null, "Solo el admin puede cargar la lista completa de usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "15":
					res = createClient();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Cliente creado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo crear el cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
					break;
				case "16":
					c = ComercioAPI.buscarClientePorRFC(JOptionPane.showInputDialog("RFC:"));
					
					printObject(c, "No se encontro ningun cliente con ese RFC");
					break;
				case "17":
					res = ComercioAPI.eliminarClientePorRFC(JOptionPane.showInputDialog("RFC:"));
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null, "Cliente eliminado con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo eliminar el cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				case "18":
					List<Cliente> lclient = ComercioAPI.obtenerClientes();
					for (Cliente client : lclient) {
						System.out.println(client.toString());
					}
					break;
				case "19":
					boolean continuar = true;
					List<Producto> plist = new ArrayList<Producto>();
					double subtotal = 0;
					
					while(continuar) {
						codigoONombre = JOptionPane.showInputDialog("Inserte nombre o codigo del producto:");
						p = ComercioAPI.buscarProductoConCodigo(codigoONombre);
						if (p == null) {
							p = ComercioAPI.buscarProductoConNombre(codigoONombre);
						}
						
						if (p == null) {
							printObject(p, "No se encontro un producto con este nombre o codigo");
						} else {
							int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
							if (p.getExistencias().intValue() > 0) {
								if (p.getExistencias().intValue() >= cantidad) {
									p.setExistencias(cantidad);
									subtotal += p.getPrecio() * cantidad;
									plist.add(p);
								} else {
									printObject(null, "La cantidad pedida es mas de la disponible");
								}
							} else {
								printObject(null, "No hay suficiente producto");
							}
						}
						String continuarString = JOptionPane.showInputDialog("Continuar (y/n)");
						System.out.println(continuarString);
						continuar = continuarString.equals("y");
					}
					
					ComercioAPI.crearTicket(subtotal, subtotal * .16, null, user, plist);
					break;
				case "20":
					List<Ticket> lTickets = ComercioAPI.obtenerTicketsDelDia(LocalDate.now());
					for (Ticket tick : lTickets) {
						System.out.println(tick.toString());
					}
					break;
				case "21":
					codigoONombre = JOptionPane.showInputDialog("Inserte nombre o codigo del producto:");
					p = ComercioAPI.buscarProductoConCodigo(codigoONombre);
					if (p == null) {
						p = ComercioAPI.buscarProductoConNombre(codigoONombre);
					}
					
					if (p == null) {
						printObject(p, "No se encontro un producto con este nombre o codigo");
					} else {
						int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Existencia actualizada:"));
						p.setExistencias(cantidad);
						ComercioAPI.actualizarProducto(p.getCodigo(), p);
					}
					break;
				case "22":
					repeat = false;
					break;
			}
		}
	}
	
	public static String printMenuAndGetSelectedOption() {
		String[] options = {
				".-Agregar producto\n",
				".-Buscar producto con codigo\n",
				".-Buscar producto con nombre\n",
				".-Ver todos los productos\n",
				".-Agregar proveedor de producto\n",
				".-Eliminar producto con codigo\n",
				".-Registrar proveedores\n",
				".-Buscar proveedor por nombre\n",
				".-Buscar proveedor por RFC\n",
				".-Ver todos los proveedores\n",
				".-Eliminar proveedor con RFC\n",
				".-Crear usuario\n",
				".-Eliminar usuario\n",
				".-Ver todos los usuarios\n",
				".-Agregar cliente\n",
				".-Buscar cliente por RFC\n",
				".-Eliminar cliente\n",
				".-Ver todos los clientes\n",
				".-Realizar venta\n",
				".-Revisar ticket de hoy\n",
				".-Resurtir producto\n",
				".-Salir"
		};
		
		String finalOptionString = "";
		
		for (int i = 1; i <= options.length; i++) {
			finalOptionString += i + options[i - 1];
		}
		
		return JOptionPane.showInputDialog(
				finalOptionString
			);
	}
	
	public static int createDefaultUser() {
		JOptionPane.showMessageDialog(null, "Crea tu usuario para comenzar");
		String name = JOptionPane.showInputDialog("Nombre de usuario:");
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Contraseña:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int op = JOptionPane.showOptionDialog(null, panel, "Contraseña", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (op == 0) {
			char[] password = pass.getPassword();
			return ComercioAPI.crearUsuario(new Usuario(name, true), new String(password));
		}
		
		return 0;
	}
	
	public static Usuario logUser() {
		JOptionPane.showMessageDialog(null, "Inicia sesión para continuar");
		String name = JOptionPane.showInputDialog("Nombre de usuario:");
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Contraseña:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int op = JOptionPane.showOptionDialog(null, panel, "Contraseña", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		if (op == 0) {
			char[] password = pass.getPassword();
			return ComercioAPI.validarUsuario(name, new String(password));
		}
		
		return null;
	}
	
	public static Producto createProducto() {
		String name = JOptionPane.showInputDialog("Nombre del producto:");
		String cod = JOptionPane.showInputDialog("Codigo:");
		double cost = Double.parseDouble(JOptionPane.showInputDialog("Costo:"));
		double pre = Double.parseDouble(JOptionPane.showInputDialog("Precio al publico:"));
		int exis = Integer.parseInt(JOptionPane.showInputDialog("Existencia:"));
		String cat = JOptionPane.showInputDialog("Categoria:");
		
		return new Producto(name, cost, cod, pre, exis, cat);
	}
	
	public static Proveedor createProveedor() {
		String rfc = JOptionPane.showInputDialog("RFC:");
		String name = JOptionPane.showInputDialog("Nombre:");
		String tel = JOptionPane.showInputDialog("Telefono:");
		String dom = JOptionPane.showInputDialog("Domicilio:");
		String razon = JOptionPane.showInputDialog("Razon Social:");
		
		return new Proveedor(rfc, name, tel, dom, razon);
	}
	
	public static int createUser() {
		String name = JOptionPane.showInputDialog("Nombre de usuario:");
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Contraseña:");
		JPasswordField pass = new JPasswordField(10);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK", "Cancel"};
		int op = JOptionPane.showOptionDialog(null, panel, "Contraseña", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		
		char[] password;

		if (op == 0) {
			password = pass.getPassword();
		} else {
			return 0;
		}
		
		String[] values = {"Admin", "Simple mortal"};

		Object selected = JOptionPane.showInputDialog(null, "Seleccione tipo de usuario", "Selección", JOptionPane.DEFAULT_OPTION, null, values, values[0]);
		if (selected != null ){
		    String selectedPermissions = selected.toString();
		    return ComercioAPI.crearUsuario(new Usuario(name, selectedPermissions == values[0] ? true : false), new String(password));
		} else {
		    return 0;
		}
	}
	
	public static int createClient() {
		String rfc = JOptionPane.showInputDialog("RFC:");
		String name = JOptionPane.showInputDialog("Nombre:");
		String tel = JOptionPane.showInputDialog("Telefono:");
		String dom = JOptionPane.showInputDialog("Domicilio:");
		
		return ComercioAPI.crearCliente(new Cliente(rfc, name, tel, dom));
	}
	
	public static void printObject(Object o, String errorMessage) {
		if (o != null) {
			JOptionPane.showMessageDialog(null, o.toString());
		} else {
			JOptionPane.showMessageDialog(null, errorMessage, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static int addProveedorToProductoProveedor() {
		Proveedor pr = ComercioAPI.buscarProveedorPorRFC(JOptionPane.showInputDialog("RFC:"));
		
		if (pr == null) {
			JOptionPane.showMessageDialog(null, "No existe un proveedor con ese RFC");
			return 0;
		}
		
		return ComercioAPI.agregarProveedorIdAProducto(JOptionPane.showInputDialog("Codigo del producto"), pr.getPid());
	}
}
