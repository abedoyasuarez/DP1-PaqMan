package paqman.Model.Bean;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Pedido {
	private int id;
	private Nodo nodoDestino;
	private int cantidadPaquetes;
	private Date horaLlegada;
	private int tiempoEntrega;//1-2-4-8-24
	//private List<Paquete> listaPaquetes; no importa que paquete cargo solo la cantidad
	private String cliente;
	
	public int getId() {
		return id;
	}
	
	public Nodo getNodoDestino() {
		return nodoDestino;
	}

	public void setNodoDestino(Nodo nodo) {
		this.nodoDestino = nodo;
	}

	public int getCantidadPaquetes() {
		return cantidadPaquetes;
	}
	public void setCantidadPaquetes(int cantidadPaquetes) {
		this.cantidadPaquetes = cantidadPaquetes;
	}
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	public int getTiempoEntrega() {
		return tiempoEntrega;
	}
	public void setTiempoEntrega(int tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	
}