package pacote.Model.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Fech_Capac;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Movimiento;
import pacote.Model.Bean.Pago;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Pedido_Out;
import pacote.Model.Bean.Ruta;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Vuelo;
import pacote.Model.Bean.Vuelo_Mov;
import pacote.Model.Bean.Response.AlmacenResponse;
import pacote.Model.Bean.Response.ListPedido;
import pacote.Model.Bean.Response.ListRutas;
import pacote.Model.Bean.Response.ListUsuario;
import pacote.Model.Bean.Response.PedidoResponse;
import pacote.Model.Bean.Response.RutasPedido;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Almacen;
import pacote.Model.DAO.DAO_Envio;
import pacote.Model.DAO.DAO_Generico;
import pacote.Model.DAO.DAO_Movimiento;
import pacote.Model.DAO.DAO_Pedido;
import pacote.Model.DAO.DAO_Vuelo;
import pacote.Model.DAO.DAO_Vuelo_Mov;


public class Service_Pedido3 {	
	private DAO_Vuelo dao_vuelo = new DAO_Vuelo();
	private DAO_Almacen dao_almacen = new DAO_Almacen();	
	private DAO_Envio dao_envio = new DAO_Envio();
	private DAO_Movimiento dao_movimiento = new DAO_Movimiento();
	private DAO_Pedido dao_pedido = new DAO_Pedido ();
	private DAO_Vuelo_Mov dao_vuelo_mov = new DAO_Vuelo_Mov();
	private DAO_Generico dao_generico = new DAO_Generico();
	private CorreoService correoService = new CorreoService();
	private UsuarioService1 usuarioService = new UsuarioService1(); 
	private static Conexion conexion= new Conexion();

	
	public Mensaje RetirarPedido(int pedido_id){
		Mensaje mensaje = new Mensaje();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			
			PedidoX pedido = new PedidoX();
			pedido.id = pedido_id;
			pedido.estado = 5;
			
			dao_pedido.entregaPedido(pedido);
			conexion.commit();
			conexion.cerrarConexion();
			System.out.println("TODO BIEN");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en Retirar Pedido");
		}
		return mensaje;
	}	
	
	public Mensaje LeerFileVuelo(String file){
		Mensaje mensaje = new Mensaje();
		try {
			InputStream is = new ByteArrayInputStream(file.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line; int i=0;
            while ((line = br.readLine()) != null) {
            	//SKBO-SBBR-08:00-22:00
            	String c_ini = line.substring(0, 3);
            	String c_fin = line.substring(5, 8);
            	String h_ini = line.substring(10, 14);
            	String h_fin = line.substring(16, 20);            	
            	//System.out.println(c_ini + " " + c_fin);
            	
            }
		}catch(Exception e){
			e.printStackTrace();
		}
		return mensaje;
	}
	
	public Mensaje LeerFilePedido(String file){
		Mensaje mensaje = new Mensaje();
		try {
			conexion.abrirConexion();
			dao_almacen.setConexion(conexion);
			
			System.out.println(file);
			
			HashMap<String, Integer> map_pais = dao_almacen.mapeoAlmacen(); 
			
			InputStream is = new ByteArrayInputStream(file.getBytes());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line; int i=0;
            for(int j=0; j<8; j++){
            	line = br.readLine();
            }
            
//            FileWriter fstream = new FileWriter("out.txt");
//            BufferedWriter out = new BufferedWriter(fstream);
            
            File arch = new File("/home/jose/Documents/salida.txt");
            
			// if file doesnt exists, then create it
			if (!arch.exists()) {
				arch.createNewFile();
			}
 
			FileWriter fw = new FileWriter(arch.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			Date ff = new Date();
			String cad = "";
			cad = new Timestamp(ff.getTime()).toString(); 
			bw.write(cad);
            
            //Close the output stream
            
            int pos = 1;
            while ((line = br.readLine()) != null) {
            	//100006232 2012 12 17 15:03 BIKF
            	if(line.length()==0) break;            	
            	String code = line.substring(0, 8);
            	String anho = line.substring(9, 13);
            	String mes = line.substring(13, 15);
            	String dia = line.substring(15, 17);
            	String hora = line.substring(17, 21);
            	String ciudad = line.substring(22, 26);
            	
            	String fecha = anho+"-"+mes+"-"+dia+" "+hora;
            	//System.out.println("fecha -----------------------> " + fecha);
            	
            	PedidoX pedido = new PedidoX();
            	pedido.id = Integer.parseInt(code);
            	
            	pedido.cantidad = 1;
            	pedido.almacen_partida = 30;
            	//System.out.println("ciudad : " + ciudad);
            	pedido.almacen_entrega = (Integer) map_pais.get(ciudad);
            	pedido.cliente_envia = 1;
            	
            	int cont1, cont2;

            	cont1=(pedido.almacen_entrega<=10)?1:2;
            	cont2=(pedido.almacen_partida<=10)?1:2;
            	
            	pedido.viaje = (cont1==cont2)?0:1;

            	PedidoResponse response = RegistrarPedidoFile(pedido, fecha);
            	//System.out.println(" Mensaje : " + response.id + ", me : " + response.me);
//            	out.write(line + ", ID : " + response.id + ", me : " + response.me);
            	bw.write(line + ", ID : " + response.id + ", me : " + response.me+ " de: " + 15 + " a: " + pedido.almacen_entrega + '\n');
            	
            	//System.out.println("DE: " + pedido.almacen_partida + ", A: " + pedido.almacen_entrega);
            	//System.out.println("cadena : " + line);
            	
            	System.out.println("pos : " + pos++);
            	//if(pos==20)break;
            	//break;
            }
            Date dd = new Date();
            cad = new Timestamp(dd.getTime()).toString(); 
			bw.write(cad);
            bw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return mensaje;
	}
	
	
	public PedidoX CheckOut(Pedido_Out pedido_out){
		PedidoX pedido = new PedidoX();
		try {
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			pedido = dao_pedido.buscarPedido(pedido_out.pedido_code);
			
		} catch (Exception e) {
			e.printStackTrace();
			pedido.me = "Error en BD";
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println("Error en cerrar Conexion");
			}
		}
		return pedido;
	}
	
	public Mensaje EntregaPedido(PedidoX pedido){
		Mensaje mensaje = new Mensaje();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			dao_pedido.entregaPedido(pedido);
			conexion.commit();
		}catch(Exception e){
			mensaje.me="Error en BD";
			try{
				conexion.rollback();
			}catch(Exception ee1){
				ee1.printStackTrace();
				System.out.println("Error en Rollback");
			}
			e.printStackTrace();
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				ee.printStackTrace();
				System.out.println("En cerrar conexion");
			}
		}
		return mensaje;
	}
	
	
	public PedidoResponse RegistrarPedidoFile(PedidoX pedido, String fecha){
		PedidoResponse mensaje = new PedidoResponse();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			dao_generico.setConexion(conexion);
			
			pedido.estado = 1;
			
			TimeCambio timeCambio = new TimeCambio();
			timeCambio.fecha = fecha;
			mensaje.id = dao_pedido.insertarPedido(pedido, timeCambio);
			pedido.id = mensaje.id;
			conexion.commit();			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en DAO, RegistrarPedido. Error : " + e.toString() );
			mensaje.me = "Sucedio un error al momento de Registro";
			try{
				conexion.rollback();
				conexion.cerrarConexion();
				
			}catch(Exception ee){
				System.out.println("Error al cerrar Conexion. Error : " + ee.toString() );
			}
			return mensaje;
		}

		try{
			
			ListRutas listRutas = buscarRuta(pedido, conexion);
			dao_pedido.setConexion(conexion);			
			
			if(listRutas.me==""){
				System.out.println("SE ENCONTRO RUTA : D");
				pedido.estado = 2;
				pedido.code = ""+pedido.id;
				dao_pedido.actualizarPedido(pedido);
				Usuario user = new Usuario();
				user.id = pedido.cliente_envia;
				//ListUsuario ll = usuarioService.buscarUsuario(user);
//				if(ll.listaUser.size()==1){
//					user = ll.listaUser.get(0);
//					String msje = "Estimado cliente " + user.apellidos +". Gracias por utilizar nuestro servicio su paquete ha sido registrado y su codigo es: " + pedido.id;
//					if(user.email!="") correoService.envioCorreo(user.email, msje);
//					mensaje.code = pedido.code;
//					mensaje.user = user.apellidos;
//				}
				conexion.commit();
			}else{
				System.out.println("NO SE ENCONTRO RUTA :(");
				//pedido.estado = 3;
				//dao_pedido.actualizarPedido(pedido);
				conexion.rollback();
			}
			
			mensaje.me = listRutas.me;
//			imprimirRuta(listRutas.listaRutas, pedido);
		}catch(Exception e){
			e.printStackTrace();
			mensaje.me = "Error al buscar Ruta";			
			System.out.println("Error En las rutas");
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				System.out.println("En cerrar Conexion");
			}
		}
		return mensaje;
	}
	
	
	public PedidoResponse RegistrarPedido(PedidoX pedido){
		PedidoResponse mensaje = new PedidoResponse();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			dao_generico.setConexion(conexion);
			
			pedido.estado = 1;
			
			TimeCambio timeCambio = dao_generico.obtenerFecha();
			mensaje.id = dao_pedido.insertarPedido(pedido, timeCambio);
			pedido.id = mensaje.id;
			conexion.commit();			
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error en DAO, RegistrarPedido. Error : " + e.toString() );
			mensaje.me = "Sucedio un error al momento de Registro";
			try{
				conexion.rollback();
				conexion.cerrarConexion();
				
			}catch(Exception ee){
				System.out.println("Error al cerrar Conexion. Error : " + ee.toString() );
			}
			return mensaje;
		}

		try{
			
			ListRutas listRutas = buscarRuta(pedido, conexion);
			dao_pedido.setConexion(conexion);			
			
			if(listRutas.me==""){
				System.out.println("SE ENCONTRO RUTA : D");
				pedido.estado = 2;
				pedido.code = ""+pedido.id;
				dao_pedido.actualizarPedido(pedido);
				Usuario user = new Usuario();
				user.id = pedido.cliente_envia;
				ListUsuario ll = usuarioService.buscarUsuario(user);
				if(ll.listaUser.size()==1){
					user = ll.listaUser.get(0);
					String msje = "Estimado cliente " + user.apellidos +". Gracias por utilizar nuestro servicio su paquete ha sido registrado y su codigo es: " + pedido.id;
					if(user.email!="") correoService.envioCorreo(user.email, msje);
					mensaje.code = pedido.code;
					mensaje.user = user.apellidos;
				}
				conexion.commit();
			}else{
				System.out.println("NO SE ENCONTRO RUTA :(");
				//pedido.estado = 3;
				//dao_pedido.actualizarPedido(pedido);
				conexion.rollback();
			}
			
			mensaje.me = listRutas.me;
//			imprimirRuta(listRutas.listaRutas, pedido);
		}catch(Exception e){
			e.printStackTrace();
			mensaje.me = "Error al buscar Ruta";			
			System.out.println("Error En las rutas");
		}finally{
			try{
				conexion.cerrarConexion();
			}catch(Exception ee){
				System.out.println("En cerrar Conexion");
			}
		}
		return mensaje;
	}
	
	public Mensaje validarCode(PedidoX pedido){
		Mensaje mensaje = new Mensaje();
		int res = 0;
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			res = dao_pedido.esCode(pedido);			
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();
			mensaje.me = "Error en BD";
			return mensaje;
		}
		if(res==0){
			mensaje.me = "Error, no se encontro dicho código";
		}else{
			mensaje.id = res;
		}
		return mensaje;
	}

	public RutasPedido buscarRutasPedido(PedidoX pedido){
		RutasPedido rutas = new RutasPedido();
		try{
			conexion.abrirConexion();
			dao_envio.setConexion(conexion);
			dao_movimiento.setConexion(conexion);
			
			List<Envio> listaEnvios= dao_envio.listarEnvios(pedido);
			for(int i=0; i<listaEnvios.size(); i++){
				Envio envio = listaEnvios.get(i);
				List<AlmacenResponse> lista = dao_movimiento.devolverRuta(envio.id);
				rutas.lRuta.add(lista);
			}
			conexion.cerrarConexion();
			
		}catch(Exception e){
			e.printStackTrace();
			rutas.me = "Error en BD";
		}
		return rutas;
	}
	public ListRutas buscarRuta(PedidoX pedido, Conexion conexion) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		//imprimirPedido(pedido);
 
		ListRutas response = new ListRutas();		
		List<Ruta> RutasDeViaje = new ArrayList<Ruta>();

		dao_vuelo.setConexion(conexion);
		dao_almacen.setConexion(conexion);
		dao_envio.setConexion(conexion);
		dao_movimiento.setConexion(conexion);
		dao_vuelo_mov.setConexion(conexion);
		
		int estado = 0;
		List <Vuelo> listaVuelos;
		listaVuelos = dao_vuelo.ListarVuelos(pedido);

//		System.out.println("TAM VUELOS : " + listaVuelos.size());
//		for(int i=0; i<listaVuelos.size(); i++){
//			Vuelo  v = listaVuelos.get(i);
//			System.out.println("Vuelo : " + v.ciudad_ini + " - " + v.ciudad_fin + " -- " + v.id);
//		}
//		System.out.println("Fin Vuelo");		
		Integer ICiudad = pedido.almacen_partida;
		Integer FCiudad = pedido.almacen_entrega;

		Vuelo lectorVueloLista;
		Vuelo lectorVuelo;
		Vuelo insertVuelo;
		//Lo utilizo para leer de mi lista de rutas (hallando los caminos)
		ArrayList lectorListaRutas;
		ArrayList rutas = new ArrayList();
		Integer lectorCiudadFin;
		ArrayList rutasPropuestas = new ArrayList();
		
		//****************************************
		
		
		/*Inicializo la lista con los primeros vuelos que deberian iniciar
		 * Puedo utilizar este primer bucle para crear el grafo
		 * el cual haria mas eficiente el algoritmo
		 */
		//System.out.println("listaVuelos : " + listaVuelos.size());
		for(int i = 0; i < listaVuelos.size(); i++){
			/*Mientras que no se haya leido toda la tabla vuelos*/
			
			lectorVuelo = listaVuelos.get(i);
		
			/*Verifico si el vuelo tiene como inicio "leerCiudad"*/
			
			/*si la tiene, agrego a la lista, sino sigo denuevo con el bucle*/
			if (ICiudad == lectorVuelo.ciudad_ini){
				if (pedido.fecha_registro.getTime() < lectorVuelo.hora_inicio.getTime()){
					ArrayList opcionR = new ArrayList();
					insertVuelo = lectorVuelo;
					opcionR.add(insertVuelo);
					rutas.add(opcionR);
				}
//				System.out.println("->" + lectorVuelo.id + " " + lectorVuelo.ciudad_fin); 
			}
		}
		//System.out.println("Numero de Rutas : " + rutas.size());
		System.out.println("En Rutas");
		while (!rutas.isEmpty()){	
			//Cojo la lista de vuelos que toca la primera ruta como opcion
			lectorListaRutas = (ArrayList)rutas.get(0);
			//System.out.println("TAM " + lectorListaRutas.size());
			rutas.remove(0);

			//Cojo el ultimo vuelo en el que me quede de la ruta que cree
			int pos = lectorListaRutas.size() - 1;
			lectorVueloLista = (Vuelo)lectorListaRutas.get(pos);
			lectorListaRutas.remove(pos);
			lectorCiudadFin = lectorVueloLista.ciudad_fin;
			
//			System.out.println("->" + lectorVueloLista.id + " " + lectorVueloLista.ciudad_fin); 
			if (lectorCiudadFin == FCiudad){
				Ruta opcionR = new Ruta(null,0);
				lectorListaRutas.add(lectorVueloLista);
				opcionR.listaVuelos = copiarListaVuelos(lectorListaRutas);
				rutasPropuestas.add(opcionR);
//				System.out.println("-->" + lectorCiudadFin);
			}else{
			
				for(int i = 0; i < listaVuelos.size(); i++){
					/*Mientras que no se haya leido toda la tabla vuelos*/
					lectorVuelo = listaVuelos.get(i);
					/*Verifico si el vuelo tiene como inicio "leerCiudad"*/
					/*si la tiene, agrego a la lista, sino sigo denuevo con el bucle*/
					//System.out.println(lectorVuelo.ciudad_ini);
//					if (lectorCiudadFin == lectorVuelo.ciudad_ini)
//						if (lectorVueloLista.hora_fin.getTime() < lectorVuelo.hora_inicio.getTime()){//aca debo midifcar el time : && (diferencia de tiempo < algo)
					if( 	no_repite(lectorListaRutas, lectorVuelo.ciudad_ini) &&
							(lectorCiudadFin == lectorVuelo.ciudad_ini) &&
							(lectorVueloLista.hora_fin.getTime() < lectorVuelo.hora_inicio.getTime()) 
						)
					{
						ArrayList opcionR;
						opcionR = copiarListaVuelos(lectorListaRutas);
						opcionR.add(copiarVuelo(lectorVueloLista));
						opcionR.add(copiarVuelo(lectorVuelo));
						rutas.add(opcionR);
					}
				}
			}
		}

		//Segunda Parte del algoritmo
		List <Ruta> iterador = rutasPropuestas;
		
		//System.out.println("iterador tamanho " + iterador.size());
		
		if (iterador.size() == 0){
			estado = 0;
			System.out.println("No se encontro posibles rutas 0");
			response.me = "No se encontro posibles rutas"; 
			return response;
		}
		//buscar donde puso para el maximo de tiempo de busqueda
		//Hasta aqui ya tengo todas las rutas posibles, falta verificar si tengo capacidad para transportar
		//Divido los paquetes


		/*for(int i = 0; i < iterador.size();i++){					
			int capa = devolver_capacidad_real(iterador.get(i),pedido);
			System.out.println("****** Capacidad Real de ruta" + i + ": " + capa);
			iterador.get(i).capacidad = capa;
		}*/

		int iter = 0 ;
		while (pedido.cantidad > 0){
			//System.out.println("-----------ITERACION " + (iter++) + "----------");
			
			if(iterador.size()<=0)break;
			
			int capa = devolver_capacidad_real(iterador.get(0),pedido);
			System.out.println("****** Capacidad Real de ruta : " + capa);
			if(capa<=0){
				/*try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				iterador.remove(0);
				continue;
			}
			iterador.get(0).capacidad = capa;
			
			if (iterador.size() == 0){
				System.out.println("No se encontró una ruta");					
				estado = 0;
				response.me = "No se encontró una ruta 1";
				return response;
			}
			//System.out.println("Numero de Rutas : " + iterador.size());
			//System.out.println("--------------------------------------------------------");			
			
//			for(int i = 0; i < iterador.size();i++){					
//				int capa = devolver_capacidad_real(iterador.get(i),pedido);
//				System.out.println("****** Capacidad Real de ruta" + i + ": " + capa);
//				iterador.get(i).capacidad = capa;					
//			}
//
//			System.out.println(" TAM ITERADOR :" + iterador.size());
//			limpiarVacios(iterador);

//			if (iterador.size() == 0){
//				System.out.println("No se encontró una ruta");
//				response.me = "No se encontró una ruta";
//				return response;
//			}
//			ordenarRutasPropuestas(iterador);
			
			
			//quicksort(iterador,0,iterador.size()-1);
			Ruta ruta_ins; 
			Envio envio_insertar;

			//System.out.println("++++++ cantidad : " + pedido.cantidad);
			//System.out.println("++++++ capacidad : " + iterador.get(0).capacidad);

			if (pedido.cantidad <= iterador.get(0).capacidad){
				iterador.get(0).cantidadEnviada = pedido.cantidad;
				iterador.get(0).capacidad -= iterador.get(0).cantidadEnviada;
				envio_insertar = actualizacion_cache(pedido, iterador.get(0).cantidadEnviada, iterador.get(0));
				ruta_ins = iterador.get(0);
				ruta_ins.envio = envio_insertar;
				RutasDeViaje.add(ruta_ins);
				estado = 1;
				response.listaRutas = RutasDeViaje;
				break;
			}
			else{
				
				iterador.get(0).cantidadEnviada = iterador.get(0).capacidad;
				iterador.get(0).capacidad -= iterador.get(0).cantidadEnviada;				
				pedido.cantidad = pedido.cantidad - iterador.get(0).cantidadEnviada;				
				envio_insertar = actualizacion_cache(pedido, iterador.get(0).cantidadEnviada, iterador.get(0));				
				ruta_ins = iterador.get(0);
				ruta_ins.envio = envio_insertar;
				RutasDeViaje.add(ruta_ins);
			}
			iterador.remove(0);
		}
		if(estado==1){
			System.out.println("Ruta Encontrada");
//			imprimirRuta(RutasDeViaje, pedido);
		}
		else{
			System.out.println("No se encontró ruta");
			response.me = "No se encontró ruta";
		}
		return response;
	}
	
	
	/*METODOS*/
	
	public boolean no_repite(ArrayList lectorListaRutas, int ciudad_ini){
		List<Vuelo> listaVuelo = lectorListaRutas;
		for(int i=0; i<listaVuelo.size(); i++){
			Vuelo vuelo = listaVuelo.get(i);
			if(vuelo.ciudad_ini == ciudad_ini) return false;
		}
		return true;
	}
	
	public Envio actualizacion_cache(PedidoX pedido, int cant, Ruta ruta) throws SQLException{
		Vuelo vuelo1, vuelo2;
		
		int ult_envio = dao_envio.registrarEnvio(pedido, cant);
		Envio envio = new Envio();
		envio.id = ult_envio;
		envio.cantidad = cant;
		
		List <Vuelo> listVuelos = ruta.listaVuelos;
		int tamList = listVuelos.size();
		
		Vuelo vv = listVuelos.get(0);
		Movimiento movi = new Movimiento(pedido.almacen_partida, pedido.id, ult_envio, pedido.fecha_registro, vv.hora_inicio, cant, 1, -1, vv.ciudad_fin, pedido.fecha_entrega_limite, pedido.almacen_entrega);
		dao_movimiento.insertar_Movimiento(movi);
		
		Calendar c_parte = null;
		Calendar c_llega = null;
		
		for(int i=0; i<tamList-1; i++){
			vuelo1 = listVuelos.get(i);
			vuelo2 = listVuelos.get(i+1);			
			Movimiento mov = new Movimiento(vuelo1.ciudad_fin, pedido.id, ult_envio, vuelo1.hora_fin, vuelo2.hora_inicio, cant, 0, vuelo1.ciudad_ini, vuelo2.ciudad_fin,pedido.fecha_entrega_limite, pedido.almacen_entrega);
			dao_movimiento.insertar_Movimiento(mov);
			dao_vuelo.actualizar_capacidad(envio, vuelo1);			
			
			Date parte = vuelo1.hora_inicio;
			c_parte = Calendar.getInstance();
			c_parte.setTime(parte);
			int anho = c_parte.get(Calendar.YEAR);
			int mes = c_parte.get(Calendar.MONTH);
			int dia = c_parte.get(Calendar.DAY_OF_MONTH);
			int h_parte = c_parte.get(Calendar.HOUR_OF_DAY);
			
			Date llega = vuelo1.hora_fin;
			c_llega = Calendar.getInstance();
			c_llega.setTime(parte);
			int h_llega = c_llega.get(Calendar.HOUR_OF_DAY);
			
			int alm_parte = vuelo1.ciudad_ini;
			int alm_llega = vuelo1.ciudad_fin;
			
			String padre_id = String.format("%02d%02d%02d%02d",alm_parte,alm_llega,h_parte,h_llega );
			
			dao_vuelo_mov.insertar_Vuelo_Mov(new Vuelo_Mov(vuelo1.id, envio.id), envio.cantidad, padre_id, vuelo1.hora_inicio);
		}
		vuelo1 = listVuelos.get(tamList - 1);
		Movimiento mov = new Movimiento(vuelo1.ciudad_fin, pedido.id, ult_envio, vuelo1.hora_fin, pedido.fecha_entrega_limite, cant, 0, vuelo1.ciudad_ini, -1, pedido.fecha_entrega_limite, pedido.almacen_entrega);
		dao_movimiento.insertar_Movimiento(mov);
		dao_vuelo.actualizar_capacidad(envio, vuelo1);
		
		Date parte = vuelo1.hora_inicio;
		System.out.println("parte : " + parte);
		
		c_parte = Calendar.getInstance();
		c_parte.setTime(parte);
		int anho = c_parte.get(Calendar.YEAR);
		int mes = c_parte.get(Calendar.MONTH);
		int dia = c_parte.get(Calendar.DAY_OF_MONTH);
		int h_parte = c_parte.get(Calendar.HOUR_OF_DAY);
		
		Date llega = vuelo1.hora_fin;
		
		c_llega = Calendar.getInstance();
		c_llega.setTime(llega);
		int h_llega = c_llega.get(Calendar.HOUR_OF_DAY);
		
		int alm_parte = vuelo1.ciudad_ini;
		int alm_llega = vuelo1.ciudad_fin;
		
		//String vuelo_code = String.format("%04d%02d%02d%02d%02d%02d%02d",anho,mes,dia,alm_parte,alm_llega,h_parte,h_llega );
		String padre_id = String.format("%02d%02d%02d%02d",alm_parte,alm_llega,h_parte,h_llega );
		dao_vuelo_mov.insertar_Vuelo_Mov(new Vuelo_Mov(vuelo1.id, envio.id), envio.cantidad, padre_id, vuelo1.hora_inicio);
		return envio;
	}

	
	public int devolver_capacidad_real(Ruta ruta, PedidoX pedido) throws SQLException{
		int capac_real=Integer.MAX_VALUE, vuelo_capac, capac_almacen, almacen_id; Date fech_ini, fech_fin; Vuelo vuelo1, vuelo2;
		List<Vuelo> listVuelos = ruta.listaVuelos;
		int tamListVuelos = listVuelos.size();
		vuelo1=listVuelos.get(0);
		capac_real = vuelo1.capacidad - vuelo1.capacidad_usada;
//		System.out.println("INICIO DE DEVOLVER CAPACIDA REAL" );
		
		//System.out.println("Vuelo " + vuelo1.id + ", capacidad: " + capac_real );
		for(int i=1; i<tamListVuelos; i++){
			vuelo1 = listVuelos.get(i-1);
			vuelo2 = listVuelos.get(i);
			fech_ini = vuelo1.hora_fin;
			fech_fin = vuelo2.hora_inicio;
			almacen_id = vuelo1.ciudad_fin;
			vuelo_capac = vuelo2.capacidad - vuelo2.capacidad_usada;
			capac_almacen = capacidad_almacen(almacen_id, fech_ini, fech_fin);
			capac_real = Math.min(capac_real, Math.min(capac_almacen, vuelo_capac));

//			System.out.println("De : " + vuelo1.ciudad_ini + ", a : " + vuelo1.ciudad_fin);
			//System.out.println("Almacen : " + almacen_id + " capac_almacen " + capac_almacen);
			//System.out.println("capac_real " + capac_real);
			//System.out.println("Vuelo " + vuelo2.id + ", capacidad: " + vuelo_capac + ", capacidad_usada : " + vuelo1.capacidad_usada );			
			//System.out.println("Vuelo " + vuelo2.id);
		}
		vuelo2 = listVuelos.get(tamListVuelos-1);
		capac_almacen = capacidad_almacen(vuelo2.ciudad_fin, vuelo2.hora_fin, pedido.fecha_entrega_limite);
		//System.out.println("-->capac_almacen " + capac_almacen);
		capac_real = Math.min(capac_real, capac_almacen);
		//System.out.println("-->capac_real " + capac_real);
		
//		System.out.println("Fin : " + vuelo2.ciudad_fin);
		
//		System.out.println("FIN DE DEVOLVER CAPACIDA REAL" );
		return capac_real;
	}
	
	
	public int capacidad_almacen(int almacen_id, Date fech_ini, Date fech_fin) throws SQLException {
		int capac=0; int arr_cant[]; Fech_Capac f_c;		
		List<Fech_Capac> list_fech_capac = dao_almacen.consultar_movimientos_rango(almacen_id, fech_ini, fech_fin);
		int tam_list = list_fech_capac.size();
		if(tam_list>0){
			long min_fech=Long.MAX_VALUE, max_fech=Long.MIN_VALUE;
			
			for(int i=0; i<tam_list; i++){
				f_c = list_fech_capac.get(i);
				min_fech = Math.min(min_fech, f_c.fech_ini.getTime());
				max_fech = Math.max(max_fech, f_c.fech_fin.getTime());
			}
			Long tam_arr = (max_fech - min_fech)/3600000;
			arr_cant = new int[(int)(long)tam_arr];
			
			Long ii, ff;
			for(int i=0; i<tam_list; i++){
				f_c = list_fech_capac.get(i);
				//System.out.println("almacen : "+ f_c.almacen_id + " , fini : " + f_c.fech_ini + " , ffin : "+f_c.fech_fin+ " , cant: "+f_c.cant);
				ii = f_c.fech_ini.getTime();
				ff = f_c.fech_fin.getTime();
				int x = (int)(long)(ii - min_fech)/3600000;
				int y = (int)(long)((tam_arr - (max_fech - ff)/3600000));
				for(int var = x; var<y; var++){
					arr_cant[var] += f_c.cant;
				}
			}
			
			capac = Integer.MIN_VALUE;
			for(int i=0; i<tam_arr; i++){				
				capac = Math.max(capac, arr_cant[i]);
				//System.out.println("capac : " + arr_cant[i]);
			}
			if(capac<0){
					System.out.println("almacen_id : " + almacen_id + " ini: " + fech_ini + " fin: " + fech_fin);
					System.out.println("------------------>" + tam_arr);
					System.out.println(max_fech - min_fech);
			}
			
		}
		
		int dev = dao_almacen.capacidad_almacen(almacen_id);
		//System.out.println("dev : " + dev + ", capac : " + capac);
		dev = dev - capac;
		return dev;
	}

	//ANTIGUA
	
	public static Vuelo copiarVuelo(Vuelo vuelo){
		return new Vuelo(vuelo.id,vuelo.ciudad_ini,vuelo.ciudad_fin, vuelo.hora_inicio,vuelo.hora_fin, vuelo.capacidad,vuelo.capacidad_usada,vuelo.estado);
	}
	
	public static ArrayList copiarListaVuelos(ArrayList lista){
		
		ArrayList devolver = new ArrayList();
		for(int i = 0; i < lista.size();i++){
			Vuelo flight = (Vuelo)lista.get(i);
			devolver.add(copiarVuelo(flight));
		}
		return devolver;
	}
	
	public static void limpiarVacios(List <Ruta> iterador){
		for(int i = 0; i < iterador.size();i++){
			if(iterador.get(i).capacidad == 0){
				iterador.remove(i);
			}
		}
	}
	
	
	public static void ordenarRutasPropuestas(List <Ruta> rutasPropuestas){
		
		for(int i = 0; i < rutasPropuestas.size() - 1;i++){
			for(int j = i; j < rutasPropuestas.size();j++){
				Ruta ruta1;
				Ruta ruta2;
				if (rutasPropuestas.get(i).capacidad < rutasPropuestas.get(j).capacidad){
					ruta1 = rutasPropuestas.get(i);
					ruta2 = rutasPropuestas.get(j);
					rutasPropuestas.set(i, ruta2);
					rutasPropuestas.set(j, ruta1);	
				}		
			}	
		}
	}
	
	public static void exchange(List <Ruta> rutasPropuestas,int i, int j) {
		Ruta ruta1;
		Ruta ruta2;
		ruta1 = rutasPropuestas.get(i);
		ruta2 = rutasPropuestas.get(j);
		rutasPropuestas.set(i, ruta2);
		rutasPropuestas.set(j, ruta1);	
	  }
	
	public static void quicksort(List <Ruta> rutasPropuestas,int inf,int sup){
		int left,rigth;
	    int half;
	    System.out.println("quick");
	    left=inf;
	    rigth=sup;
	    
	    System.out.println("left: " + left);
	    System.out.println("rigth: " + rigth);
	    
	    half=rutasPropuestas.get((left+rigth)/2).capacidad;
	    
	    while(left<=rigth){
            while(rutasPropuestas.get(left).capacidad <half)
                left++;
	
            while(half < rutasPropuestas.get(rigth).capacidad)
                rigth--;
            
            if (left <= rigth) {
                exchange(rutasPropuestas,left, rigth);
                left++;
                rigth--;
            }            
 
            if(inf<rigth) quicksort(rutasPropuestas,inf,rigth);
            if(left<sup) quicksort(rutasPropuestas,left,sup);
	
        }
	
    }


	public void imprimirRuta(List<Ruta> listaRutas, PedidoX pedido){
		//imprimirPedido(pedido);
		int tamList = listaRutas.size();
		//System.out.println("Lista de Envios:");
		//System.out.println();
		
		//System.out.println("**********************************************************************");
		for(int i=0; i<tamList; i++){
			Ruta ruta = listaRutas.get(i);
			//System.out.println("--------------------------------------------------------------");
			//System.out.println("EnvioID : " + ruta.envio.id + ", Cant : " + ruta.envio.cantidad);
			//System.out.println();
			List<Vuelo> listVuelos = ruta.listaVuelos;
			int tamVuel = listVuelos.size();
			for(int j=0;j<tamVuel; j++){
				Vuelo vuelo = listVuelos.get(j); 
				System.out.println("VueloID : " + vuelo.id);
				System.out.println("--> Almacen Ini : " + vuelo.ciudad_ini + ", Hora Ini : " + vuelo.hora_inicio);
				System.out.println("--> Almacen Fin : " + vuelo.ciudad_fin + ", Hora Fin : " + vuelo.hora_fin);
			}
			//System.out.println("--------------------------------------------------------------");
		}
	}
	
	public void imprimirPedido(PedidoX pedido){
		System.out.println("Datos del Pedido");
		System.out.println("Pedido(ID) : " + pedido.id + ", Cantidad : " + pedido.cantidad);
		System.out.println("Almacen de inicio : " + pedido.almacen_partida + ", Hora de inicio : " + pedido.fecha_registro);
		System.out.println("Almacen final : " + pedido.almacen_entrega + ", Hora final : " + pedido.fecha_entrega_limite);
		
	}
	
	//**************8
	public static void imprimirVuelo(Vuelo flight){
		/*Imprime los datos del vuelo*/
		//System.out.println("N�mero de vuelo: " + flight.id);
		//System.out.println("Ciudad de Inicio: " + flight.ciudad_ini);
		//System.out.println("Ciudad final: " + flight.ciudad_fin);
		//System.out.println("Hora de partida: "  + (flight.hora_inicio).toString());
		//System.out.println("Hora de llegada: " + (flight.hora_fin).toString());
		//System.out.println("Capacidad: " + flight.capacidad);
	}
	
	public static void imprimirRuta(ArrayList listaDeVuelos){	
		/*Imprime una lista de vuelos*/
		Vuelo lectorVuelo;
		//System.out.println();
		//System.out.println("*Posibilidad de ruta a seguir*");
		//System.out.println();
		for(int i = 0; i < listaDeVuelos.size();i++){
			lectorVuelo = (Vuelo)listaDeVuelos.get(i);
			//System.out.println("->Vuelo #" + i);
			imprimirVuelo(lectorVuelo);
		}
		//System.out.println();
		//System.out.println("*Fin de Posibilidad de ruta a seguir*");
		//System.out.println();
	}

	public ListPedido ListarPedidos(int id_usuario) {
		ListPedido rpta = new ListPedido();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			rpta=dao_pedido.ListarPedidos(id_usuario);
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en DAO, RegistrarAlmacen. Error : " + e.toString() );
			rpta.me = "Se registro un error al momento de Registro";
		}
		return rpta;
	}

	public Mensaje RegistrarPago(Pago pago) {
		Mensaje mensaje = new Mensaje();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			mensaje.id=dao_pedido.RegistrarPago(pago);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en DAO, RegistrarPago. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Registro";
		}
		return mensaje;
	}

	public Mensaje EliminarPago(Pago pago) {
		Mensaje mensaje = new Mensaje();
		try{
			conexion.abrirConexion();
			dao_pedido.setConexion(conexion);
			dao_pedido.EliminarPago(pago);
			conexion.commit();
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Error en DAO, RegistrarPago. Error : " + e.toString() );
			mensaje.me = "Se registro un error al momento de Registro";
		}
		return mensaje;
	}


	public void setearConexion(Conexion con){
		dao_almacen.setConexion(con);
	}
}