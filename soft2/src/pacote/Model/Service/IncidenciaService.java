package pacote.Model.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Incidencia;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Vuelo;
import pacote.Model.Bean.Response.ListIncidencia;
import pacote.Model.Bean.Response.ListRutas;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Envio;
import pacote.Model.DAO.DAO_Generico;
import pacote.Model.DAO.DAO_Incidencia;
import pacote.Model.DAO.DAO_Movimiento;
import pacote.Model.DAO.DAO_Vuelo;
import pacote.Model.DAO.DAO_Vuelo_Mov;


public class IncidenciaService {
	private DAO_Incidencia incidenciaDAO = new DAO_Incidencia();
	private DAO_Generico dao_generico = new DAO_Generico();
	private DAO_Envio dao_envio = new DAO_Envio();
	private DAO_Vuelo_Mov dao_vuelo_mov = new DAO_Vuelo_Mov();
	private DAO_Vuelo dao_vuelo = new DAO_Vuelo();
	private DAO_Movimiento dao_movimiento = new DAO_Movimiento();
	private static Conexion conexion = new Conexion();
	
	public Mensaje RegistrarIncidencia(Incidencia incidencia){
		Service_Pedido3 service = new Service_Pedido3();
		Mensaje mensaje = new Mensaje();

		try{
			conexion.abrirConexion();
			incidenciaDAO.setConexion(conexion);
			dao_generico.setConexion(conexion);
			dao_envio.setConexion(conexion);
			dao_vuelo.setConexion(conexion);
			dao_movimiento.setConexion(conexion);
			dao_vuelo_mov.setConexion(conexion);

			TimeCambio timeCambio = dao_generico.obtenerFecha();
			mensaje.id = incidenciaDAO.RegistrarIncidencia(incidencia, timeCambio);
			
			System.out.println("ID Incidencia  : " + incidencia.vuelo_id);
			
			List<Envio> listaEnvio = dao_vuelo_mov.listarEnviosVuelo(incidencia.vuelo_id);
			Vuelo infVuelo = dao_vuelo.pedirVuelo(incidencia.vuelo_id);
			System.out.println("hora fin : " + infVuelo.hora_fin);
			
			System.out.println("ID Vuelo  : " + incidencia.vuelo_id);
			System.out.println("TAM lista : " + listaEnvio.size());
			 
			dao_vuelo.darBajaVuelo(incidencia.vuelo_id);
			conexion.commit();
			
			for(int i=0; i<listaEnvio.size(); i++){
				Envio env = listaEnvio.get(i);
				PedidoX pedido = dao_envio.buscarPedidoDeEnvio(env);
				pedido.almacen_partida = infVuelo.ciudad_ini;
				pedido.fecha_registro = infVuelo.hora_inicio; 
				
				service.imprimirPedido(pedido);
				
				Date fecha_limite = pedido.fecha_entrega_limite;
				while(true){
					System.out.println("ANTES DE RUTEAR");
					ListRutas listRutas = service.buscarRuta(pedido, conexion);
					System.out.println("DESPUES DE RUTEAR");
					if((listRutas.me).equals("")){
						System.out.println("Encontramos Ruta");						
						break;
					}
					else{
						Calendar hoy = Calendar.getInstance();

						hoy.setTime(pedido.fecha_entrega_limite);
						hoy.add(Calendar.HOUR, 24);
						pedido.fecha_entrega_limite = hoy.getTime();
						System.out.println("Cambio de Fecha : " + pedido.fecha_entrega_limite);
						conexion.rollback();
					}
				}
				//if(fecha_limite<pedido.fecha_entrega_limite){
				if((pedido.fecha_entrega_limite).compareTo(fecha_limite)>0){
					//envio Mensaje de Alerta
					System.out.println("Cambio de Fecha");
					//53291
				}
				//Eliminar el Envio
				System.out.println("Cambio de data en BD");
				dao_envio.eliminarEnvio(env);
				//Eliminar de Vuelo_Mov el Envio
				//dao_vuelo_mov.actualizarEstadoVueloMov(env);
				//Actualizar la capacidad de los Vuelos
				dao_vuelo.cambiarCapacidad(env.cantidad, incidencia.vuelo_id);
				//Eliminar los Movimientos del Envio
				dao_movimiento.eliminaMovEnvio(env);
				conexion.commit();
			}
			
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en DAO, RegistrarIncidencia. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Registro";
		}
		System.out.println("Mensaje : "+mensaje.me);
		return mensaje;
	}

	public Mensaje ModificarIncidencia(Incidencia incidencia){
		Mensaje mensaje = new Mensaje();	
		try{
			conexion.abrirConexion();
			incidenciaDAO.setConexion(conexion);
			System.out.println("desc: "+incidencia.incidencia_descripcion);
			incidenciaDAO.modificarIncidencia(incidencia);	
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en query BD, ModificarIncidencia. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Actualización de Incidencia";
		}
		return mensaje;
	}
	
	public Mensaje EliminarIncidencia(Incidencia incidencia){
		Mensaje mensaje = new Mensaje();	
		try{
			conexion.abrirConexion();
			incidenciaDAO.setConexion(conexion);
			incidenciaDAO.eliminarIncidencia(incidencia);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en query BD, EliminarIncidencia. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Eliminación";
		}
		return mensaje;		
	}

	public ListIncidencia ListIncidencias(int id_usuario) {
		ListIncidencia rpta = new ListIncidencia();
		try {
			conexion.abrirConexion();
			incidenciaDAO.setConexion(conexion);
			rpta = incidenciaDAO.listarIncidencias(id_usuario);
			conexion.cerrarConexion();
			System.out.println("Cierra");
		} catch (Exception e) {			
			System.out.println("Error en DAO, Listar Incidencias. Error : " + e.toString() );
			rpta.me = "Error en Listar Incidencias";
		}
		return rpta;
	}
}
