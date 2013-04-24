package pacote.Model.Facade;

import pacote.Model.Bean.Contactenos;
import pacote.Model.Bean.ListRol;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListUsuario;
import pacote.Model.Bean.Response.LoginResponse;
import pacote.Model.Service.UsuarioService1;

public class ClienteFacade {
	private static UsuarioService1 usuarioService = new UsuarioService1(); 
	
	public ListRol listarRoles(){
		return usuarioService.listarRoles();
	}
	
	public Mensaje recuperarContrasenha(Usuario usuario){
		return usuarioService.recuperarContra(usuario);
	}
	
	public Mensaje RegistrarUsuario(Usuario usuario){		
		return usuarioService.RegistrarUsuario(usuario);
		
	}
	public Mensaje ModificarUsuario(Usuario usuario){
		return usuarioService.ModificarUsuario(usuario);
	}
	
	public ListUsuario buscarUsuario(Usuario usuario){
		return usuarioService.buscarUsuario(usuario);
	}
	
	public Mensaje eliminarUsuario(Usuario usuario){
		return usuarioService.eliminarUsuario(usuario);
	}
	public LoginResponse LoginUsuario(Usuario usuario){
		System.out.println("FACADE");
		return usuarioService.loginUsuario(usuario);		
	}
	
	public LoginResponse LoginUsuarioMovil(Usuario usuario){
		System.out.println("FACADE");
		return usuarioService.LoginUsuarioMovil(usuario);		
	}
	
	public Mensaje VerificarCliente(String nombreUsuario){
		return usuarioService.verificarCliente(nombreUsuario);
	}
	
	public Mensaje contactenos(Contactenos cont){
		return usuarioService.contactenos(cont);
	}
}
