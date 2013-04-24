package pacote.Model.Service;

import java.sql.SQLException;

import pacote.Model.Bean.Contactenos;
import pacote.Model.Bean.ListRol;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListUsuario;
import pacote.Model.Bean.Response.LoginResponse;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Usuario;

public class UsuarioService1 {
	private static DAO_Usuario usuarioDAO = new DAO_Usuario ();
	private static CorreoService correoService = new CorreoService(); 
	private static Conexion conexion = new Conexion();

	public LoginResponse LoginUsuarioMovil(Usuario usuario){
		LoginResponse login =  new LoginResponse();
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			login  = usuarioDAO.loginUsuario(usuario);
			login.code = usuarioDAO.ultimoPedido(login.id);
			conexion.cerrarConexion();
		}catch(Exception e){
			login.me = "Error en Login de usuario, vuelva a intentar mas tarde";
			System.out.println("Error en Login Movil: " + e.toString());
		}
		return login;		
	}	
	
	public ListRol listarRoles(){
		ListRol lRoles = new ListRol();
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			lRoles = usuarioDAO.listarRoles();
			System.out.println("tam : "+ lRoles.lRol.size());
		}catch(Exception e){
			e.printStackTrace();
			lRoles.me="Error en BD al Listar Roles";
		}finally{
			try {
				conexion.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("En cerrar Conexion");
			}
		}
		return lRoles;
		
	}
	
	public Mensaje RegistrarUsuario(Usuario usuario) {
		Mensaje mensaje = new Mensaje();
		
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			usuario = usuarioDAO.insertarUsuario(usuario);
			mensaje.id = usuario.id;
			String msje= "Estimado : " + usuario.nombre + " " + usuario.apellidos +", le agradecemos por registrarse al Sistema de Redex, su username es : " + usuario.user + ", y su password es : "+ usuario.password;
			correoService.envioCorreo(usuario.email, msje);
			conexion.commit();
		}catch(Exception e){
			System.out.println("Error en DAO, RegistrarAlmacen. Error : " + e.toString() );
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				System.out.println("En rollback ... : " + e.toString() );
			}
			mensaje.me = "Sucedio un error al momento de Registro";
			System.out.println(mensaje.me);
		}finally{
			try {
				conexion.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Cerramos");
				e.printStackTrace();
			}
		}
		System.out.println(mensaje.me);
		return mensaje;
	}
	
	public Mensaje recuperarContra(Usuario usuario){
		Mensaje mensaje = new Mensaje();
		Usuario user = null;
		try{
			conexion.abrirConexion();			
			usuarioDAO.setConexion(conexion);
			user = usuarioDAO.buscarUsuarioX(usuario);
			System.out.println("id : " + user.id);
		}catch(Exception e){
			System.out.println("Error en BD");
			mensaje.me = "Error en BD";
			return mensaje;
		}
		if(user.id==0){
			mensaje.me="Verifique su Username por favor";
			return mensaje;
		}
		System.out.println("Cambio de password");
		user.password = (java.util.UUID.randomUUID().toString()).substring(0, 6);
		String msje = "Su nueva contrasenha es : " + user.password;
		mensaje = ModificarUsuario(user);
		if(mensaje.me==""){
			try{
				System.out.println("Antes de envar correo");
				if(user.email!="")correoService.envioCorreo(user.email, msje);
				System.out.println("Despues de envar correo");
			}catch(Exception e){
				e.printStackTrace();
				mensaje.me = "Error al enviar correo electrónico";
			}
		}
		System.out.println("mensaje : "+mensaje.me);
		return mensaje;
	}
	
	public ListUsuario buscarUsuario(Usuario usuario){
		ListUsuario list =  null;
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			list  = usuarioDAO.buscarUsuario(usuario);
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("buscarUsuario : " + e.toString());
			list.me = "Sucedio un error al buscarUsuario";
		}
		return list;
	}
	
	public Mensaje ModificarUsuario(Usuario usuario){
		Mensaje mensaje =  null;
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			mensaje  = usuarioDAO.actualizarUsuario(usuario);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Modificar  Usuarios: " + e.toString());
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				System.out.println("En rollback ... : " + e.toString() );
			}
			mensaje.me = "Sucedio un error al ModificarUsuario";
		}
		return mensaje;
	}
	
	public Mensaje eliminarUsuario(Usuario usuario){
		Mensaje mensaje =  new Mensaje();
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			mensaje  = usuarioDAO.eliminarUsuario(usuario);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error al eliminarUsuario : " + e.toString());
			try{
				conexion.rollback();
			}catch(SQLException s){
				System.out.println("En rollback ... " + s.toString());
			}
			mensaje.me = "Error en eliminacion de usuario";
		}
		return mensaje;
	}
	
	public LoginResponse loginUsuario(Usuario usuario){
		LoginResponse login =  new LoginResponse();
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);
			login  = usuarioDAO.loginUsuario(usuario);
			conexion.cerrarConexion();
		}catch(Exception e){
			login.me = "Error en Login de usuario, vuelva a intentar mas tarde";
			System.out.println("Erro en Login : " + e.toString());
		}
		return login;
	}
	
	public Mensaje EliminarUsuario(Usuario usuario){
		return new Mensaje();
	}
	public Mensaje cambiarContra(Usuario usuario){
		return new Mensaje();		
	}

	public Mensaje verificarCliente(String nombreUsuario) {
		Mensaje mensaje = new Mensaje();
		try{
			conexion.abrirConexion();
			usuarioDAO.setConexion(conexion);	
			mensaje = usuarioDAO.verficarCliente(nombreUsuario);
			//conexion.commit();
			conexion.cerrarConexion();	
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("ERROR VERIFICAR : " + e.toString());
			mensaje.me = "Error en verificacion de Cliente";
		}
			
		return mensaje;
	}
	
	public Mensaje contactenos(Contactenos cont){
		Mensaje mens = new Mensaje();
		String  mensaje;
		try {
			mensaje = "De : " + cont.nombre + ", correo : " + cont.email + ", Mensaje : " + cont.mensaje;
			correoService.envioCorreo("redexdp1@gmail.com", mensaje);
			mensaje = "Gracias por contactarse con nosotros, atenderemos su consulta a la brevedad";
			correoService.envioCorreo(cont.email, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			mens.me = "Ocurrio un error al enviar correo, intentelo más tarde.";
		} 
		return mens;
	}
}
