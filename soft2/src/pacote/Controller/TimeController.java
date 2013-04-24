package pacote.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Facade.TimeFacade;

@Controller
public class TimeController {
	private static TimeFacade timeFacade = new TimeFacade();
	public void SubirPedidos(){
	} 

	@RequestMapping(value = "/Time/CambiarFecha", method = RequestMethod.POST)
	public @ResponseBody Mensaje cambiarFecha(@RequestBody TimeCambio time){
		System.out.println(" En cambio de Fecha Pedido " );
		return timeFacade.cambiarFechaPedido(time.fecha);
	}
	
	@RequestMapping(value = "/Time/ObtenerFecha", method = RequestMethod.POST)
	public @ResponseBody TimeCambio obtenerFecha(){
		//System.out.println("En cambio de Fecha : " + time.fecha );
		return timeFacade.obtenerFecha();
	}
	
	
}
