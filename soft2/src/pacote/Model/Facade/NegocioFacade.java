package pacote.Model.Facade;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Pago;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Pedido_Out;
import pacote.Model.Bean.Response.ListPedido;
import pacote.Model.Bean.Response.PedidoResponse;
import pacote.Model.Bean.Response.RutasPedido;
import pacote.Model.Service.Service_Pedido3;

public class NegocioFacade {
	private static Service_Pedido3 pedidoService = new Service_Pedido3();
	
	
	public Mensaje RetirarPedido(int pedido_id){
		return pedidoService.RetirarPedido(pedido_id);
	}	
	
	public RutasPedido ConsultarRuta(PedidoX pedido){
		return pedidoService.buscarRutasPedido(pedido);
	}
	public Mensaje verificaRastreo(PedidoX pedido){
		return pedidoService.validarCode(pedido);
	}
	public PedidoResponse RegistrarPedido(PedidoX pedido){
		return pedidoService.RegistrarPedido(pedido);
	}
	public Mensaje ModificarPedido(PedidoX pedido){
		Mensaje men=new Mensaje();
		//return pedidoService.ModificarPedido(pedido);
		return men;
	}
	public ListPedido ListarPedidos(int id_usuario) {
		return pedidoService.ListarPedidos(id_usuario);
	}
	public Mensaje RegistrarPago(Pago pago) {
		return pedidoService.RegistrarPago(pago);
	}
	public Mensaje EliminarPago(Pago pago) {
		return pedidoService.EliminarPago(pago);
	}
	
	public PedidoX CheckOut(Pedido_Out pedido_out){
		return pedidoService.CheckOut(pedido_out);
	}
	public Mensaje LeerFilePedido(String nombre){
		return pedidoService.LeerFilePedido(nombre);
	}
	
	public Mensaje LeerFileVuelo(String file){
		return pedidoService.LeerFileVuelo(file);
	}
	/*
	public Mensaje BuscarCliente(Pedido pedido){
		return pedidoService.ModificarPedido(pedido);
	}
	public Mensaje RegistrarCliente(Pedido pedido){
		return pedidoService.ModificarPedido(pedido);
	}
	public Mensaje RegistrarPago(Pedido pedido){
		return pedidoService.ModificarPedido(pedido);
	}
	*/
}
