package pacote.Model.Bean.Response;

import java.util.Date;
import java.util.ArrayList;

import pacote.Model.Bean.Response.AlmacenX;

public class DatoSimulacion {
	public Date fecha;
	public String fechaCad;
	public ArrayList<AlmacenX> almacenes = new ArrayList<AlmacenX>();
	public ArrayList<VueloX> vuelos = new ArrayList<VueloX>();
}

