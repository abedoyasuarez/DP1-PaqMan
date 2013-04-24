package pacote.Model.Bean;

import java.util.Date;

public class PedidoX extends Mensaje {
	public int id;
	public int cantidad;
	public int almacen_partida;
	public int almacen_entrega;	
	public Date fecha_registro;
	public Date fecha_recojo;
	public Date fecha_entrega_limite;
	public String code;
	public int viaje;
	public int estado;
	public int cliente_envia;	
	public String receptor_nombre;
	public String receptor_apellidos;
	public int receptor_documento_id;
	public String receptor_tipo_doc;
	public String receptor_numero_documento;
	public String receptor_telefono;
	public String code_ident="";
}
