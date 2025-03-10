package practica1;

import java.util.Calendar;

public interface IFecha {

	public int DIA_DEL_MES = Calendar.DAY_OF_MONTH, 
			   MES_DEL_AÑO = Calendar.MONTH, 
			   AÑO = Calendar.YEAR;
	
	public int Dia();
	public int mes();
	public int año();
}

