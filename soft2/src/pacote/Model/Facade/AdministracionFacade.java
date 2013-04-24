package pacote.Model.Facade;

import pacote.Model.Bean.Almacen;
import pacote.Model.Bean.Incidencia;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Rol;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListAlmacen;
import pacote.Model.Bean.Response.ListContinente;
import pacote.Model.Bean.Response.ListIncidencia;
import pacote.Model.Bean.Response.ListPais;
import pacote.Model.Bean.Response.ReporteMesResponse;
import pacote.Model.Bean.Response.ReportePaisResponse;
import pacote.Model.Service.AlmacenService;
import pacote.Model.Service.IncidenciaService;
import pacote.Model.Service.RolService;
import pacote.Model.Service.UsuarioService1;


public class AdministracionFacade {
	private static AlmacenService almacenService = new AlmacenService();
	private static UsuarioService1 usuarioService = new UsuarioService1();
	private static RolService rolService = new RolService();
	private static IncidenciaService incidenciaService = new IncidenciaService();
	//REPORTE
	
	public ReporteMesResponse reporteMes(int mes){
		return almacenService.reporteMes(mes);
	}
	
	public ReportePaisResponse reportePaises(){
		return almacenService.reportePaises();
	}
	
	public ReportePaisResponse reportePais(int id){
		return almacenService.reportePais(id);
	}
	
	//ALMACEN
	public Mensaje RegistrarAlmacen(Almacen almacen){
		return almacenService.RegistrarAlmacen(almacen);
	}
	
	public Mensaje eliminarAlmacen(Almacen almacen) {		
		return almacenService.eliminarAlmacen(almacen);
	}

	
	public Mensaje ModificarAlmacen(Almacen almacen){
		return almacenService.ModificarAlmacen(almacen);
	}
	public ListAlmacen ListarAlmacenesActivos(){
		return almacenService.ListarAlmacenesActivos();
	}
	
	public ListAlmacen ListarAlmacenes(int id){
		return almacenService.ListarAlmacenes(id);
	}
	
	public ListContinente ListarContinentes() {
		return almacenService.ListarContinentes();
	}
	public ListPais ListarPaises(int id_continente) {
		return almacenService.ListarPaises(id_continente);
	}	
	
	/*
	public Mensaje EliminarAlmacen(Almacen almacen){
		return almacenService.ModificarAlmacen(almacen);
	}
	*/
	//USUARIO
	public Mensaje RegistrarUsuario(Usuario usuario){
		return usuarioService.RegistrarUsuario(usuario);
	}
	public Mensaje ModificarUsuario(Usuario usuario){
		return usuarioService.ModificarUsuario(usuario);
	}
	public Mensaje EliminarUsuario(Usuario usuario){
		return usuarioService.EliminarUsuario(usuario);
	}
	public Mensaje CambiarContra(Usuario usuario){
		return usuarioService.cambiarContra(usuario);
	}
	public Mensaje LoginUsuario(Usuario usuario){
		return usuarioService.loginUsuario(usuario);
	}
	/*
	//Documento
	public Mensaje RegistrarDocumento(Almacen almacen){
		return almacenService.ModificarAlmacen(usuario);
	}
	public Mensaje ModificarDocumento(Almacen almacen){
		return almacenService.ModificarAlmacen(almacen);
	}
	public Mensaje EliminarDocumento(Almacen almacen){
		return almacenService.ModificarAlmacen(almacen);
	}
	*/
	//ROL
	public Mensaje RegistrarRol(Rol rol){
		return rolService.RegistrarRol(rol);
	}
	public Mensaje ModificarRol(Rol rol){
		return rolService.ModificarRol(rol);
	}
	public Mensaje EliminarRol(Rol rol){
		return rolService.EliminarRol(rol);
	}
	
	
	//Incidencia
	public Mensaje RegistrarIncidencia(Incidencia incidencia){
		return incidenciaService.RegistrarIncidencia(incidencia);
	}
	
	public Mensaje ModificarIncidencia(Incidencia incidencia){
		return incidenciaService.ModificarIncidencia(incidencia);
	}
	
	public Mensaje EliminarIncidencia(Incidencia incidencia){
		return incidenciaService.EliminarIncidencia(incidencia);
	}

	public ListIncidencia ListarIncidencias(int id_usuario){
		return incidenciaService.ListIncidencias(id_usuario);
	}

	

}
