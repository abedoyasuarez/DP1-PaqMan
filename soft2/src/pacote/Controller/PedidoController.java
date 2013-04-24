package pacote.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.FileUlt;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Pago;
import pacote.Model.Bean.Pedido;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Pedido_Out;
import pacote.Model.Bean.Response.ListPedido;
import pacote.Model.Bean.Response.PedidoResponse;
import pacote.Model.Bean.Response.RutasPedido;
import pacote.Model.Facade.NegocioFacade;
import sun.misc.BASE64Decoder;

@Controller
public class PedidoController {
	private static NegocioFacade pedidoFacade = new NegocioFacade();	
	
	@RequestMapping(value = "/Pedido/ConsultarRuta", method = RequestMethod.POST)
    public @ResponseBody RutasPedido ConsultarRuta(@RequestBody PedidoX pedido) {
		System.out.println("Consultar Ruta");
		RutasPedido rutasPedido = pedidoFacade.ConsultarRuta(pedido);        
        return rutasPedido;
    }
	
	@RequestMapping(value = "/Pedido/ConsultarRutaMovil", method = RequestMethod.POST)
    public @ResponseBody RutasPedido ConsultarRutaMovil(HttpServletRequest request) {
		System.out.println("Consultar Ruta Movil");
		PedidoX pedido = new PedidoX();
		pedido.id = Integer.parseInt(request.getParameter("id"));
		RutasPedido rutasPedido = pedidoFacade.ConsultarRuta(pedido);        
        return rutasPedido;
    }
	
	@RequestMapping(value = "/Pedido/VerificaRastreo", method = RequestMethod.POST)
    public @ResponseBody Mensaje VerificaRastreo(@RequestBody PedidoX pedido) {
		System.out.println("Verifica Rastreo");
        Mensaje mensaje = pedidoFacade.verificaRastreo(pedido);        
        return mensaje;
    }
	
	@RequestMapping(value = "/Pedido/modificarPedido")
    public String ModificarPedido() {        
        return "../WEB-INF/Sistema Web/Pedido/modificarPedido.jsp";
    }
	
	@RequestMapping(value = "/Pedido/cargarPedido")
    public String CargarPedido() {        
        return "../WEB-INF/Sistema Web/Pedido/cargarPedido.jsp";
    }

	
	@RequestMapping(value = "/Pedido/modificarPedido", method = RequestMethod.POST)
    public @ResponseBody Mensaje ModificarPedido(@RequestBody Pedido pedido) {
		System.out.println("Modifica Pedido");
        Mensaje mensaje = new Mensaje();
        mensaje.me = "MODIFICADO";
        return mensaje;
    }
	
    @RequestMapping(value = "/Pedido/CheckOut")
    public String CheckOutPedido() {        
        return "../WEB-INF/Sistema Web/Pedido/checkoutpedido.jsp";
    } 
        
        
	@RequestMapping(value = "/Pedido/eliminarPedido")
    public String EliminarPedido() {        
        return "../WEB-INF/Sistema Web/Pedido/eliminarPedido.jsp";
    }
	
	@RequestMapping(value = "/Pedido/eliminarPedido", method = RequestMethod.POST)
    public @ResponseBody Mensaje EliminarPedido(@RequestBody Pedido pedido) {
		System.out.println("Elimina Pedido");

        Mensaje mensaje = new Mensaje();
        mensaje.me = "ELIMINADO";
        return mensaje;

    }
	
	@RequestMapping(value = "/Pedido/ListarPedidos", method = RequestMethod.POST)
    public @ResponseBody ListPedido ListarPedidos(@RequestBody int id_usuario) {
		System.out.println("Hola amiguitos :)");
		return pedidoFacade.ListarPedidos(id_usuario);
    }
	
	
	@RequestMapping(value = "/Pedido/registrarPedido")
    public String RegistrarPedido() {        
        return "../WEB-INF/Sistema Web/Pedido/registrarPedido.jsp";
    }
	
	@RequestMapping(value = "/Pedido/registrarPedido", method = RequestMethod.POST)
    public @ResponseBody PedidoResponse RegistrarPedido(@RequestBody PedidoX pedido) {
		System.out.println("Registra " +
				"almacen");
		PedidoResponse mensaje = new PedidoResponse();
        mensaje = pedidoFacade.RegistrarPedido(pedido);        
        return mensaje;
    }
	
	@RequestMapping(value = "/Pedido/cancelarPedido")
    public String CancelarPedido() {        
        return "../WEB-INF/Extranet/cancelarPedido.jsp";
    }

	
	@RequestMapping(value = "/Pedido/registrarPago", method = RequestMethod.POST)
    public @ResponseBody Mensaje RegistrarPago(@RequestBody Pago pago) {
        Mensaje mensaje = new Mensaje();
        mensaje = pedidoFacade.RegistrarPago(pago);        
        return mensaje;
    }
	
	@RequestMapping(value = "/Pedido/eliminarPago", method = RequestMethod.POST)
    public @ResponseBody Mensaje EliminarPago(@RequestBody Pago pago) {
        Mensaje mensaje = new Mensaje();
        mensaje = pedidoFacade.EliminarPago(pago);        
        return mensaje;
    }	
	
	@RequestMapping(value = "/Pedido/CheckOutPedido", method = RequestMethod.POST)
    public @ResponseBody Mensaje CheckOut(@RequestBody Pedido_Out pedido_out){
        Mensaje mensaje = new Mensaje();
        mensaje = pedidoFacade.CheckOut(pedido_out);
        return mensaje;
    }

	@RequestMapping(value = "/Pedido/RetirarPedido", method = RequestMethod.POST)
    public @ResponseBody Mensaje RetirarPedido(@RequestBody PedidoX pedido){
		System.out.println("Retirar Pedido " + pedido.id);
        Mensaje mensaje = new Mensaje();
        mensaje = pedidoFacade.RetirarPedido(pedido.id);
        return mensaje;
    }
	
	@RequestMapping(value = "/Pedido/CargarFile", method = RequestMethod.POST)
    public @ResponseBody Mensaje LeerFilePedido(@RequestBody FileUlt file){
        Mensaje mensaje = new Mensaje();
        System.out.println(file.nombre);
        //System.out.println(file.nombre);        
        mensaje = pedidoFacade.LeerFilePedido(file.nombre);
        return mensaje;
    }
}
