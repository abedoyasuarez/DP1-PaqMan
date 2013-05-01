package paqman.Model.Bean;

import java.util.List;

public class Simulacion {
	public static int tiempoActual;
	public static double costo;
	public static int intervaloTiempo;
	public static double margenSeguridad;
	public static Nodo almacen;
	public static List<Pedido> listaPedidosRecibidos;
	public int inicializarSimulacion(int tiempoAct, int intervaloTiempo, double margenSeguridad,Nodo almacen){
		this.tiempoActual=tiempoAct;
		this.costo=0;
		this.intervaloTiempo=intervaloTiempo;
		this.margenSeguridad=margenSeguridad;
		this.almacen=almacen;
		this.listaPedidosRecibidos=null;
		return 1;
	}
}
