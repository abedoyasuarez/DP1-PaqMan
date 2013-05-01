package paqman.Model.Bean;
import java.util.Date;

public class Paquete {
	private int estado; //En Cola 0 - En Camino 1 - Etregado 2
	private Date horaEntrega;
	private Pedido pedido;
	private int cantidad; //Cantidad de paquetes a dejar en un punto de entrega

}
