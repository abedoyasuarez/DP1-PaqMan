package paqman.Model.Bean;
import java.util.Date;

public class Paquete {
	private int trackNumber;
	private int estado; //En Cola 0 - En Camino 1 - Etregado 2
	private Date horaEntrega;
	private Pedido pedido;
	
	public int getTrackNumber() {
		return trackNumber;
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
	
}
