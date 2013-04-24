package pacote.Model.Facade;



import java.util.Date;

import pacote.Model.Bean.Response.RetornoSimulacion;
import pacote.Model.Service.SimulacionService;

public class SimulacionFacade {
	SimulacionService servicioSimulacion = new SimulacionService();

	public RetornoSimulacion simular(Date fecha){
		//return servicioSimulacion.simular(fecha);
		return new RetornoSimulacion();

	}
}

