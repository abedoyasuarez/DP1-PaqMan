package pacote.Model.Bean;

import java.util.Date;

public class Movimiento {
	public int id;
	public int almacen_id;
	public int pedido_id;
	public int envio;
	public Date hora_entrada; 
	public Date hora_salida;
	public int cantidad;
	public int estado;
	public int almacen_in;
	public int almacen_out;
	public Date pedido_fecha_entrega_limite;
	public int pedido_almacen_entrega;
	public String user_nombre;
	public String user_correo;
	public String almacen_ciudad;
	
	public Movimiento(){}
	
	public Movimiento(int almacen, int pedido_id, int envio, Date mov_entr, Date mov_sal, int cant, int est, int almacen_in, int almacen_out, Date pedido_fecha_entrega_limite, int pedido_almacen_entrega){
		this.almacen_id = almacen;
		this.pedido_id = pedido_id;
		this.envio = envio;
		this.hora_entrada = mov_entr;
		this.hora_salida = mov_sal;
		this.cantidad = cant;
		this.estado = est;
		this.almacen_in = almacen_in;
		this.almacen_out = almacen_out;
		this.pedido_fecha_entrega_limite = pedido_fecha_entrega_limite;
		this.pedido_almacen_entrega = pedido_almacen_entrega;
	}
}
