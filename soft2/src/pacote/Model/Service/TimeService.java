package pacote.Model.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Movimiento;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Vuelo;
import pacote.Model.Bean.Response.PedidoResponse;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Generico;
import pacote.Model.DAO.DAO_Movimiento;
import pacote.Model.DAO.DAO_Pedido;
import pacote.Model.DAO.DAO_Vuelo;

public class TimeService {
	private static DAO_Movimiento dao_movimiento = new DAO_Movimiento();
	private static DAO_Vuelo dao_vuelo = new DAO_Vuelo();
	private static DAO_Pedido dao_pedido = new DAO_Pedido();
	private static DAO_Generico dao_generico = new DAO_Generico();
	private static Conexion conexion = new Conexion();
	private static CorreoService correoService = new CorreoService(); 
	private static Service_Pedido3 pedidoService = new Service_Pedido3();
	
	public void verificarPedidoLlego(int pedido_id){
		
	}
	
	public TimeCambio obtenerFecha(){
		TimeCambio mensaje = new TimeCambio();
		
		
		try{
			conexion.abrirConexion();
			dao_generico.setConexion(conexion);
			mensaje = dao_generico.obtenerFecha();
		}
		catch(Exception e){
			try{
				conexion.rollback();
			}catch(Exception ee){
				ee.printStackTrace();
			}
			mensaje.me = "Error en BD";
			e.printStackTrace();
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception eee){
				eee.printStackTrace();
			}
		}
		return mensaje;
	}
	
	public Mensaje cambiarFechaPedido(String fecha){
		Mensaje mensaje = new Mensaje();
		try {
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			dao_generico.setConexion(conexion);
			
			List<PedidoX> listPed = dao_pedido.listarPedido(fecha);

			System.out.println("TAM : " + listPed.size());
			
			String path = "/home/jose/Documents/pedido_" + fecha;
            File arch = new File(path);

			if (!arch.exists()) {
				arch.createNewFile();
			}
			FileWriter fw = new FileWriter(arch.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Date ff = new Date();
			String cad = "";
			cad = new Timestamp(ff.getTime()).toString(); 
			bw.write(cad);

            int pos = 1;
            
            System.out.println("TAM lista : " + listPed.size());
            for(int i=0; i<listPed.size(); i++){
            	PedidoX pedido = new PedidoX();
            	PedidoX ped = listPed.get(i);
            	
            	pedido.id = ped.id;
            	pedido.cantidad = 1;
            	pedido.almacen_partida = ped.almacen_partida;
            	pedido.almacen_entrega = ped.almacen_entrega;
            	pedido.cliente_envia = 1;
            	int cont1, cont2;
            	cont1=(pedido.almacen_entrega<=10)?1:2;
            	cont2=(pedido.almacen_partida<=10)?1:2;
            	
            	pedido.viaje = (cont1==cont2)?0:1;

            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            	String fc = sdf.format(ped.fecha_registro);
            	PedidoResponse response = pedidoService.RegistrarPedidoFile(pedido, fc);
            	bw.write(ped.id + ", ID : " + response.id + ", me : " + response.me+ " de: " + ped.almacen_partida + " a: " + pedido.almacen_entrega + '\n');
            	System.out.println("pos : " + pos++);
            }
            
			dao_generico.actualizarFecha(fecha);
			conexion.commit();			
			Date dd = new Date();
            cad = new Timestamp(dd.getTime()).toString(); 
			bw.write(cad);
            bw.close();
            
            System.out.println("antes");
			mensaje = cambiarFecha(fecha);
			System.out.println("despues");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conexion.cerrarConexion();
				System.out.println("Cerramos");
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
		return mensaje;
	}
	
	public Mensaje cambiarFecha(String fecha){
		Mensaje mensaje  = new Mensaje();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		try{
			
			
			String path = "/home/jose/Documents/movimiento_" + fecha;
            File arch = new File(path);

			if (!arch.exists()) {
				arch.createNewFile();
			}
			FileWriter fw = new FileWriter(arch.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Date firulaiz = new Date();
			String cad = "";
			cad = new Timestamp(firulaiz.getTime()).toString(); 
			bw.write(cad+'\n');
			
			
			conexion.abrirConexion();
			dao_movimiento.setConexion(conexion);
			dao_vuelo.setConexion(conexion);
			dao_generico.setConexion(conexion);
			dao_pedido.setConexion(conexion);

			List<Movimiento> listaMov = dao_movimiento.listarMovimiento();			
			Date ff =  dateFormat.parse(fecha);//fecha insertada en el View
			
//			System.out.println("Listar Pedidos");
//			List<PedidoX> listaPedido = dao_pedido.listarTodoPedidos();
//			int tamPed = listaPedido.size();
//			System.out.println("TAM PEDIDOS : " + tamPed);
			
			int tamMov = listaMov.size();
			
			String sql = "";
			if(tamMov>0){
				sql = 	" UPDATE Movimiento " +
						" SET movimiento_estado = " +  
						" case movimiento_id ";	
			}
			
			System.out.println("TAM MOVIMIENTOS : " + tamMov);
			for(int i=0; i<tamMov; i++){
				Movimiento mov =  listaMov.get(i);
				int actual = mov.almacen_id;
				int pedido_id = mov.pedido_id; 
				int destino = mov.pedido_almacen_entrega;
				Date f_entrada = mov.hora_entrada;
				Date f_salida  = mov.hora_salida;
				Date f_maxima  = mov.pedido_fecha_entrega_limite;
				
				String usuario_nombre = mov.user_nombre;
				String usuario_correo = mov.user_correo;
				String almacen_ciudad = mov.almacen_ciudad;
				
				int est1 = mov.estado;
				
				if(ff.compareTo(f_salida)>=0 ){
					mov.estado = 2;//	2 : paso por este almacen
				}else if(ff.compareTo(f_entrada)>=0){
					mov.estado = 1;//	1 : esta en este almacen
//					if(usuario_correo!=""){
//						String me="Estimado : " + usuario_nombre + "\nSu paquete ha llegado al almacen de : " + almacen_ciudad;
//						correoService.envioCorreo(usuario_correo, me);
//					}
				}else continue;
				
				int est2 = mov.estado;
				String palFace="";
				if(est1!=est2){ 
					sql +=  " when "+ mov.id + " then " + mov.estado;
					
					switch(mov.estado){
						case 1: palFace="ID : "+ mov.pedido_id + ", mov_id : "+ mov.id+" ,llego del almacen :" +mov.almacen_id+'\n';break;
						case 2: palFace="ID : "+ mov.pedido_id + ", mov_id : "+ mov.id+" ,salio del almacen :" +mov.almacen_id+'\n';break;
						default: break;
					}
					bw.write(palFace);
				}
			}
			Date fido = new Date();
			cad = "";
			cad = new Timestamp(fido.getTime()).toString(); 
			bw.write(cad);
			bw.close();
			
			if(tamMov>0){
				sql += " else movimiento_estado ";
				sql += " end ";
				System.out.println("SQL UPDATE: " + sql);
				dao_movimiento.actualizarMovimientoSQL(sql);
			}
	        
//	        
//	        sql = 	" UPDATE Pedido " +   
//					" SET movimiento_estado = " +  
//					" case movimiento_id ";	
//	        
//			for(int i=0; i<tamPed; i++){
//				PedidoX ped = listaPedido.get(i);
//				int num_env = dao_movimiento.numero_envios(ped);
//				int num_ini = dao_movimiento.numero_inicio(ped);
//				int num_fin = dao_movimiento.numero_fin(ped);
//				int est1 = ped.estado;
//				if(num_env==num_fin){
//					ped.estado = 4;//listo para recoger
//				}else if(num_env==num_ini){
//					ped.estado = 2;//aun en lugar de partida
//				}else{
//					ped.estado = 3;//en viaje
//				}
//				int est2 = ped.estado;
//				if(est1!=est2){
//					dao_pedido.actualizarPedidoEstado(ped);
//				}
//			}
			conexion.commit();
		}catch(Exception e){
			try{
				conexion.rollback();
			}catch(Exception ee){
				ee.printStackTrace();
			}
			mensaje.me = "Error en BD";
			e.printStackTrace();
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception eee){
				eee.printStackTrace();
			}
		}
		return mensaje;
	}
	
}
