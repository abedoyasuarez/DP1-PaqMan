package paqman.Model.Bean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ruta {
	private int id;
	private int distancia;
	private List<Nodo> trayectoria;
	private Date salidaAlmacen;
	private Date llegadaAlmacen;
	private int minutoSalidaAlmacen;
	private int minutoLlegadaAlmacen;
	//private double costo;
	private int estado; //0 Normal 1 Incidencia
	private Vehiculo vehiculo;
	private List<Paquete> listaPaquetes;
	
	public int inicializarRuta(Vehiculo vehiculo){
		trayectoria=new ArrayList<Nodo>();
		trayectoria.add(Simulacion.almacen);
		listaPaquetes=new ArrayList<Paquete>();
		listaPaquetes.add(new Paquete(0,null,0,0));
		id=Simulacion.idRutas++;
		distancia=0;
		estado=0;
		this.vehiculo=vehiculo;
		this.minutoSalidaAlmacen=-1;
		this.minutoLlegadaAlmacen=-1;
		return 1;
	}
	
	
	public Nodo getUltimoNodo(){
		int indice=trayectoria.size();
		if (indice==0)return null;
		
		return trayectoria.get(indice);
	}
	
	public void añadirCamino(int nodo,int [] padre){
		
		if (padre[nodo]==nodo) return;
		añadirCamino(padre[nodo],padre);
		if (padre[nodo]!=nodo)trayectoria.add(new Nodo(nodo%Mapa.columnas,nodo/Mapa.columnas));	
		
		
	}
	public void añadirCaminoReversa(int nodo,int [] padre){
		
		int flag=0;
		while(true){
			
			if (flag==1)trayectoria.add(new Nodo(nodo%Mapa.columnas,nodo/Mapa.columnas));	
			flag=1;
			if (padre[nodo]==nodo)break;
			nodo=padre[nodo];
		}
		
	}
	
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


	public int getMinutoSalidaAlmacen() {
		return minutoSalidaAlmacen;
	}


	public void setMinutoSalidaAlmacen(int minutoSalidaAlmacen) {
		this.minutoSalidaAlmacen = minutoSalidaAlmacen;
	}


	public int getMinutoLlegadaAlmacen() {
		return minutoLlegadaAlmacen;
	}


	public void setMinutoLlegadaAlmacen(int minutoLlegadaAlmacen) {
		this.minutoLlegadaAlmacen = minutoLlegadaAlmacen;
	}
	
	
}