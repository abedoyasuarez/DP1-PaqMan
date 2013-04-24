package pacote.Model.Bean.Response;

import pacote.Model.Bean.Almacen;

public class AlmacenResponse extends Almacen{
	public int paso;
	public String msje="";
	public AlmacenResponse(float latitud, float longitud, int paso, String ciudad, String msje){
		this.ciudad = ciudad;
		this.estado = paso;
		this.latitud = latitud;
		this.longitud = longitud;
		this.paso = paso;
		this.msje = msje;
	}
}
