package pacote.Model.Bean;

public class Almacen {
	public int id;
	public String ciudad;	
	public int capacidad;
	public int estado;
	public float latitud;
	public float longitud;
	public int pais_id;
	public int continente_id;
	public String continente_nombre;
	public String pais_nombre;
	//public int capacidad_actual;
	
	
	public Almacen(){}
		
	public Almacen(int id,	String ciudad, int capacidad, int estado,	float latitud, 	float longitud, int pais_id, int continente_id){
		this.id = id;
		this.ciudad = ciudad;
		this.capacidad = capacidad;
		this.estado = estado;
		this.latitud = latitud;
		this.longitud = longitud;
		this.pais_id = pais_id;
		this.continente_id = continente_id;

	}
}
