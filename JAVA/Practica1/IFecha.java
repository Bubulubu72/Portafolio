package mx.iteso.examen2;

import java.util.Calendar;

public interface IFecha {

	public int DIA_DEL_MES = Calendar.DAY_OF_MONTH, 
			   MES_DEL_A�O = Calendar.MONTH, 
			   A�O = Calendar.YEAR;
	
	public int Dia();
	public int mes();
	public int a�o();
}

