package pacote.Model.Bean;

import java.util.List;

public class SimulaVuelo {
	public String vuelo_id;
	public String padre_id;
	public String almacen_partida= "";
	public String almacen_llegada= "";
	public int vuelo_capacidad;
	public List<SimulaDataVuelo> listaSimulaDataVuelo;
}
