package pacote.Model.Facade;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Service.TimeService;

public class TimeFacade {
	private static TimeService timeService = new TimeService(); 
	public Mensaje cambiarFecha(String fecha){
		System.out.println("En cambio de Fecha");
		return timeService.cambiarFecha(fecha);
	}
	
	public Mensaje cambiarFechaPedido(String fecha){
		System.out.println("En cambio de Fecha Ped");
		return timeService.cambiarFechaPedido(fecha);
	}
	
	public TimeCambio obtenerFecha(){
		return timeService.obtenerFecha();
	}
}
