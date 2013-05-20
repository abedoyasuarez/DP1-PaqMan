package pacote.Model.Bean.Response;

import java.util.Date;
import java.util.ArrayList;

import pacote.Model.Bean.Response.AlmacenX;

public class DatoSimulacion {
	public Date fecha;
	public String fechaCad;
	public ArrayList<AlmacenX> almacenes = new ArrayList<AlmacenX>();
	public ArrayList<VueloX> vuelos = new ArrayList<VueloX>();
	public Integer paquetes_T1;
	public Integer paquetes_T2;
	public Integer paquetes_T3;
	public Integer pedidos;
	public boolean colapso;
}

