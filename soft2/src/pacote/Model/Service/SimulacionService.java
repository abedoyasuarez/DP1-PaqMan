package pacote.Model.Service;

import java.util.Date;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Response.RetornoSimulacion;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Simulacion;

public class SimulacionService {
	DAO_Simulacion simulacionDAO = new DAO_Simulacion();

	public RetornoSimulacion simular(Date fecha, int diasMenos){
		RetornoSimulacion ret = new RetornoSimulacion();
		try{	
			//ret.listaSim = simulacionDAO.simular(fecha);			

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en DAO, Simular. Error : " + e.toString());
			ret.me = "Se registro un error al momento de Simular";
		}
		return ret;
	}
}

