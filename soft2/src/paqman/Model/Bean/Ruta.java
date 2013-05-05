package paqman.Model.Bean;
import java.util.Date;
import java.util.List;

public class Ruta {
	private int id;
	private int distancia;
	private List<Nodo> trayectoria;
	private Date salidaAlmacen;
	private Date llegadaAlmacen;
	//private double costo;
	private int estado; //0 Normal 1 Incidencia
	private Vehiculo vehiculo;
	private List<Paquete> listaPaquetes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public List<Nodo> getTrayectoria() {
		return trayectoria;
	}
	public void setTrayectoria(List<Nodo> trayectoria) {
		this.trayectoria = trayectoria;
	}
	public Date getSalidaAlmacen() {
		return salidaAlmacen;
	}
	public void setSalidaAlmacen(Date salidaAlmacen) {
		this.salidaAlmacen = salidaAlmacen;
	}
	public Date getLlegadaAlmacen() {
		return llegadaAlmacen;
	}
	public void setLlegadaAlmacen(Date llegadaAlmacen) {
		this.llegadaAlmacen = llegadaAlmacen;
	}
//	public double getCosto() {
//		return costo;
//	}
//	public void setCosto(double costo) {
//		this.costo = costo;
//	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public List<Paquete> getListaPaquetes() {
		return listaPaquetes;
	}
	public void setListaPaquetes(List<Paquete> listaPaquetes) {
		this.listaPaquetes = listaPaquetes;
	}
	
	
}
