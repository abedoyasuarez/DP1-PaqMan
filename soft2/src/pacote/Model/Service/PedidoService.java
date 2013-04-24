package pacote.Model.Service;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Response.ListRutas;
import pacote.Model.DAO.DAO_Pedido;

public class PedidoService {
	private static DAO_Pedido pedidoDAO = new DAO_Pedido ();
	private static Service_Pedido3 service_pedido = new Service_Pedido3();
	
	public Mensaje RegistrarPedido(PedidoX pedido){
		Mensaje mensaje = new Mensaje();
		//mensaje = pedidoDAO.insertarPedido(pedido);
		return mensaje;
	}
	
	public Mensaje ModificarPedido(PedidoX pedido){
		Mensaje mensaje = new Mensaje();
		//mensaje = pedidoDAO.insertarPedido(pedido);
		return mensaje;
	}
	
	public ListRutas buscarRuta(PedidoX pedido){
		ListRutas response = new ListRutas();
		try{
			//response = service_pedido.buscarRuta(pedido);
		}catch(Exception e){
			response.me = "Error en BD";
		}
		return response;
	}
	
	public void devolverInfoPedido(){
		
	}
}
