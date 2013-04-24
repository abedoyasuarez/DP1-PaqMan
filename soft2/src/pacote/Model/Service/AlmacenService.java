package pacote.Model.Service;

import pacote.Model.Bean.Almacen;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Response.ListAlmacen;
import pacote.Model.Bean.Response.ListContinente;
import pacote.Model.Bean.Response.ListPais;
import pacote.Model.Bean.Response.ReporteMesResponse;
import pacote.Model.Bean.Response.ReportePaisResponse;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Almacen;
import pacote.Model.DAO.DAO_Generico;
import pacote.Model.DAO.DAO_Pedido;


public class AlmacenService {
	private static DAO_Almacen almacenDAO = new DAO_Almacen (); 
	private static DAO_Pedido pedidoDAO = new DAO_Pedido ();
	private DAO_Generico dao_generico = new DAO_Generico();
	private static Conexion conexion = new Conexion();
	
	public ReporteMesResponse reporteMes(int mes){
		ReporteMesResponse response = new  ReporteMesResponse();
		try{
			conexion.abrirConexion();
			pedidoDAO.setConexion(conexion);
			response.reporte = pedidoDAO.reporteMes(mes);
		}catch(Exception e){
			e.printStackTrace();
			response.me = "Error en Reporte";
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println("Error en DAO reporteMes");
			}
		}
		if(response.reporte.size()==0){
			response.me = "No hay datos";
		}
		return response;
	}
	
	public ReportePaisResponse reportePaises(){
		ReportePaisResponse response = new  ReportePaisResponse();
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			dao_generico.setConexion(conexion);
			
			TimeCambio timeCambio = dao_generico.obtenerFecha();
			response.reporte = almacenDAO.reporteAlmacenCapacidad(timeCambio);
		}catch(Exception e){
			e.printStackTrace();
			response.me = "Error en Reporte";
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println("Error en DAO reportePaises");
			}
		}
		if(response.reporte.size()==0){
			response.me = "No hay datos";
		}
		return response;
	}
	
	public ReportePaisResponse reportePais(int id){
		ReportePaisResponse response = new  ReportePaisResponse();
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			dao_generico.setConexion(conexion);
			TimeCambio timeCambio = dao_generico.obtenerFecha(); 
			response.reporte = almacenDAO.reporteAlmacenCapacidadPais(id, timeCambio);
		}catch(Exception e){
			e.printStackTrace();
			response.me = "Error en Reporte";
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println("Error en DAO reportePaises");
			}
		}
		if(response.reporte.size()==0){
			response.me = "No hay datos";
		}
		return response;
	}
	
	public Mensaje RegistrarAlmacen(Almacen almacen) {
		Mensaje mensaje = new Mensaje();
			
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);	
			mensaje.id = almacenDAO.insertarAlmacen(almacen);			
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en DAO, RegistrarAlmacen. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Registro";
		}
		return mensaje;
	}

	public ListAlmacen ListarAlmacenesActivos(){
		ListAlmacen rpta = new ListAlmacen();
		try {
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			rpta = almacenDAO.ListarAlmacenesActivos();
			conexion.cerrarConexion();
			System.out.println("Cierra");
		} catch (Exception e) {			
			System.out.println("Error en DAO, Listar Almacen. Error : " + e.toString() );
			rpta.me = "Error en Listar Almacenes";
		}
		return rpta;
	}
	
	
	public ListAlmacen ListarAlmacenes(int id){
		ListAlmacen rpta = new ListAlmacen();
		try {
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			rpta = almacenDAO.ListarAlmacenes(id);
			System.out.println(rpta.listaAlmacenes.size());
			System.out.println("Cierra");
		} catch (Exception e) {	
			e.printStackTrace();
			System.out.println("Error en DAO, Listar Almacen. Error : " + e.toString() );
			rpta.me = "Error en Listar Almacenes";
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				System.out.println("No se pudo cerrar conexion");
			}
		}
		return rpta;
	}
	
	public Mensaje ModificarAlmacen(Almacen almacen) {		

		Mensaje mensaje = new Mensaje();	
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			almacenDAO.actualizarAlmacen(almacen);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en query BD, RegistrarAlmacen. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Actualización";
		}
		return mensaje;
	}
	
	public Mensaje eliminarAlmacen(Almacen almacen) {		

		Mensaje mensaje = new Mensaje();	
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			almacenDAO.eliminarAlmacen(almacen);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en query BD, RegistrarAlmacen. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Eliminar Almacen";
		}
		return mensaje;
	}
	
	
	public ListContinente ListarContinentes() {
		ListContinente rpta = new ListContinente();
		try {
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			rpta = almacenDAO.ListarContinentes();
			conexion.cerrarConexion();
			System.out.println("Cierra");
		} catch (Exception e) {			
			System.out.println("Error en DAO, Listar Continentes. Error : " + e.toString() );
			rpta.me = "Error en Listar Continentes";
		}
		return rpta;	
	}

	public ListPais ListarPaises(int id_continente) {
		ListPais rpta = new ListPais();
		try {
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			rpta = almacenDAO.ListarPaises(id_continente);
			conexion.cerrarConexion();
			System.out.println("Cierra");
		} catch (Exception e) {			
			System.out.println("Error en DAO, Listar Paises. Error : " + e.toString() );
			rpta.me = "Error en Listar Continentes";
		}
		return rpta;	
	}
}
