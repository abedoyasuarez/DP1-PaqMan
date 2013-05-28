package paqman.Model.Bean;
import java.util.Date;

public class Paquete {
	private int estado; //En Cola 0 - En Camino 1 - Etregado 2
	private Date horaEntrega;
	private Pedido pedido;
	private int cantidad; //Cantidad de paquetes a dejar en un punto de entrega
	private int puntoEntrega;//Indice en la trayectoria
	private int distancia;
	private int minutoEntrega;
	
	
	public Paquete(int cantPaquetes,Pedido pedido,int distancia,int puntoEntrega){
		this.cantidad=cantPaquetes;
		this.pedido=pedido;
		this.distancia=distancia;
		this.puntoEntrega=puntoEntrega;
		this.estado=0;
	}
	
	
	public int getPuntoEntrega() {
		return puntoEntrega;
	}
	public void setPuntoEntrega(int puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Date getHoraEntrega() {
		return horaEntrega;
	}
	public void setHoraEntrega(Date horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getDistancia() {
		return distancia;
	}


	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}


	public int getMinutoEntrega() {
		return minutoEntrega;
	}


	public void setMinutoEntrega(int minutoEntrega) {
		this.minutoEntrega = minutoEntrega;
	}	
	
	
}
