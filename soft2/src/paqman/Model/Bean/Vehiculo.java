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
	public float getVelocidad() {
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


	public void setDistanciaRecorrida(int distanciaRecorrida) {
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

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	
} 
