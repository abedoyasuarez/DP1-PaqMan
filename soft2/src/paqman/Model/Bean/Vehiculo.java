package paqman.Model.Bean;
import java.util.List;

public class Vehiculo {
	private int id;
	private int tipo; //AUTO 0 MOTO 1
	private int capacidad;
	private int cantidadPaquetes;
	//private List<Paquete> listaPaquetes; ya esta en ruta
	private int velocidad;
	private int tiempoTrabajo;
	private int estado; // 0 EN ALMACEN - 1 EN CAMINO - 2 DESCANZO 
	private int turno; //TURNO 0 - 1 - 2		
	private Nodo posicionRuta; //
	private double distanciaRecorrida; //distancia recorrida de la ruta
	private int posicionRelativa; //indice de un nodo de la ruta	
	private int	tiempoDescanso;
	private Ruta rutaActual;
	private double costoPorKm;
	//private List<Ruta> listaRutas; no se necesita historico
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCantidadPaquetes() {
		return cantidadPaquetes;
	}
	public void setCantidadPaquetes(int cantidadPaquetes) {
		this.cantidadPaquetes = cantidadPaquetes;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getTiempoTrabajo() {
		return tiempoTrabajo;
	}
	public void setTiempoTrabajo(int tiempoTrabajo) {
		this.tiempoTrabajo = tiempoTrabajo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	public Nodo getPosicionRuta() {
		return posicionRuta;
	}
	public void setPosicionRuta(Nodo posicionRuta) {
		this.posicionRuta = posicionRuta;
	}
	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}
	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}
	public int getPosicionRelativa() {
		return posicionRelativa;
	}
	public void setPosicionRelativa(int posicionRelativa) {
		this.posicionRelativa = posicionRelativa;
	}
	public int getTiempoDescanso() {
		return tiempoDescanso;
	}
	public void setTiempoDescanso(int tiempoDescanso) {
		this.tiempoDescanso = tiempoDescanso;
	}
	public Ruta getRutaActual() {
		return rutaActual;
	}
	public void setRutaActual(Ruta rutaActual) {
		this.rutaActual = rutaActual;
	}
	public double getCostoPorKm() {
		return costoPorKm;
	}
	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}
	
} 
