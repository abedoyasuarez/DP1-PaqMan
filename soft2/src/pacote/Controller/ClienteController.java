package pacote.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.Contactenos;
import pacote.Model.Bean.ListRol;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListUsuario;
import pacote.Model.Bean.Response.LoginResponse;
import pacote.Model.Facade.ClienteFacade;
//ASD
@Controller
public class ClienteController {
	private static ClienteFacade clienteFacade = new ClienteFacade();
	private static int CLIENTE = 2;
	
	
	@RequestMapping(value = "/Usuario/registrarUsuario")
    public String RegistrarCliente() {        
        return "../WEB-INF/Sistema Web/Usuario/registrarUsuario.jsp";
    }
	
	@RequestMapping(value = "/Usuario/recuperarContra", method = RequestMethod.POST)
    public @ResponseBody Mensaje recuperarContrasenha(@RequestBody Usuario usuario) {
		System.out.println("Recuperacion de Contrasenha Usuario");
        Mensaje mensaje = new Mensaje();
        mensaje = clienteFacade.recuperarContrasenha(usuario);
        return mensaje;
    }
	
	@RequestMapping(value = "/Usuario/registrarUsuario", method = RequestMethod.POST)
    public @ResponseBody Mensaje RegistrarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Registra " +
				"usuario");
        Mensaje mensaje = new Mensaje();
        mensaje = clienteFacade.RegistrarUsuario(usuario);
        return mensaje;
    }
	
	@RequestMapping(value = "/Usuario/buscarUsuario", method = RequestMethod.POST)   
	public @ResponseBody ListUsuario BuscarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Buscar Usuario");
        ListUsuario lista = clienteFacade.buscarUsuario(usuario);
        return lista;
    }
	
	@RequestMapping(value = "/Usuario/modificarUsuario")
    public String ModificarUsuario() {        
        return "../WEB-INF/Sistema Web/Usuario/modificarUsuario.jsp";
    }
	
	@RequestMapping(value = "/Usuario/modificarUsuario", method = RequestMethod.POST)
    public @ResponseBody Mensaje ModificarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Modifica Usuario");
        Mensaje mensaje = clienteFacade.ModificarUsuario(usuario);
        return mensaje;
    }
	
	@RequestMapping(value = "/Usuario/verificarCliente", method = RequestMethod.POST)
    public @ResponseBody Mensaje VerificarCliente(@RequestBody String nombreUsuario) {
		System.out.println("Modifica Usuario");
        return clienteFacade.VerificarCliente(nombreUsuario);
    }
	
	@RequestMapping(value = "/Usuario/eliminarUsuario")
    public String EliminarUsuario() {        
        return "../WEB-INF/Sistema Web/Usuario/eliminarUsuario.jsp";
    }
	
	@RequestMapping(value = "/Usuario/eliminarUsuario", method = RequestMethod.POST)
    public @ResponseBody Mensaje EliminarUsuario(@RequestBody Usuario usuario) {
		System.out.println("Elimina Usuario");
        Mensaje mensaje = clienteFacade.eliminarUsuario(usuario);
        return mensaje;
    }
	
	
	
	@RequestMapping(value = "/Cliente/consultarDisponibilidad", method = RequestMethod.POST)
    public void ConsultarDipsonibilidad(@RequestBody Usuario usuario) {
		
	}
	
	@RequestMapping(value = "/Usuario/consultarPedido", method = RequestMethod.POST)
    public void ConsultarPedido(@RequestBody Usuario usuario) {		
	}
	
	@RequestMapping(value = "/Extranet/cancelarPedido")
    public String CancelarPedido() {        
        return "../WEB-INF/Extranet/cancelarPedido.jsp";
    }
	
	
	@RequestMapping(value = "/Extranet/cancelarPedido", method = RequestMethod.POST)
    public void CancelarPedido(@RequestBody Usuario usuario) {		
	}
	
	
	
	@RequestMapping(value = "/Usuario/consultarHistorial", method = RequestMethod.POST)
    public void ConsultarHistorial(@RequestBody Usuario usuario) {		
	}
	
	
	@RequestMapping(value = "/Usuario/login", method = RequestMethod.POST)
    public @ResponseBody LoginResponse login(@RequestBody Usuario usuario) {
		System.out.println("Login Usuario");
		LoginResponse login= clienteFacade.LoginUsuario(usuario);
		System.out.println(login.nombre + " " + login.me);
		return login;
	}
	
        /* Servicio Login para Moviles */
        @RequestMapping(value = "/Usuario/loginmovil", method = RequestMethod.POST)
    public @ResponseBody LoginResponse loginmovil(HttpServletRequest request) {
        String user = request.getParameter("email");
        String password = request.getParameter("password");
        Usuario usuario = new Usuario();
        usuario.user = user;
        usuario.password = password;
        LoginResponse login =  clienteFacade.LoginUsuarioMovil(usuario);
		System.out.println(login.nombre + login.permisos+login.me);
		return login;
	}
        /*Fin servicio*/
        
	@RequestMapping(value = "/Usuario/contactenos", method = RequestMethod.POST)
    public @ResponseBody Mensaje Contactenos(@RequestBody Contactenos cont) {
		System.out.println("Contactenos");
		return clienteFacade.contactenos(cont);
	}
        /* Servicio Contacto para Moviles */
        @RequestMapping(value = "/Usuario/contactenosmovil", method = RequestMethod.POST)
    public @ResponseBody Mensaje ContactenosMovil(HttpServletRequest request) {
                String email = request.getParameter("email");
                String mensaje = request.getParameter("mensaje");
                Contactenos cont = new Contactenos();
                cont.email = email;
                cont.mensaje = mensaje;
                cont.nombre = "Nombre";
		System.out.println("Contactenos");
		return clienteFacade.contactenos(cont);
	}
        /* Fin servicio*/
       
        
	@RequestMapping(value = "/Usuario/roles", method = RequestMethod.POST)
    public @ResponseBody ListRol listarRoles() {
		System.out.println("Listar Roles");
		return clienteFacade.listarRoles();
	}
}
