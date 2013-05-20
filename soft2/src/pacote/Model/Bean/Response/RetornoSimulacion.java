package pacote.Model.Bean.Response;

import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.AlmacenCaida;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.VueloCaida;

public class RetornoSimulacion extends Mensaje{	
	public ArrayList<DatoSimulacion> listaSim = new ArrayList<DatoSimulacion>();
	//public ArrayList<DatoSimulacion> listaSimVuelo = new ArrayList<DatoSimulacion>();
	//public List<AlmacenCaida> listaCaidos = new ArrayList<AlmacenCaida>();
	//public List<VueloCaida> listaCaidosVuelos = new ArrayList<VueloCaida>();
}

