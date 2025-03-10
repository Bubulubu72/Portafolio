import javax.swing.JOptionPane;

public class Actividad04 {

	public static void main(String[] args) {
		
	// Ejecicio 1 //	
	double l = 0, h = 0;
	
	String enter = JOptionPane.showInputDialog("Cuanto mide el lado: ");
	l = Double.parseDouble(enter);
		
	enter = JOptionPane.showInputDialog("Cuanto mide la altura: ");
	h = Double.parseDouble(enter);
	
	// Ejercicio 2 //
	int dias = 0, horas = 0, minutos = 0, seg = 0;
	
	String enter4 = JOptionPane.showInputDialog("Cuantos segundos: ");
	
	int segundosOr = Integer.parseInt(enter4);
	
	dias = segundosOr / 86400;
	segundosOr = segundosOr % 86400;
	
	horas = segundosOr / 3600;
	segundosOr = segundosOr % 3600;
	
	minutos = segundosOr / 60;//
	seg = segundosOr % 60;
	
	String mensaje = String.format("%d Segundos corresponden a  \n\t %d dias, \n\t %d horas " +
			" \n \t %d minutos y \n \t %d segundos", segundosOr, dias, horas, minutos, seg);
	
	JOptionPane.showInputDialog(null, mensaje, 1);
	
	}

}
