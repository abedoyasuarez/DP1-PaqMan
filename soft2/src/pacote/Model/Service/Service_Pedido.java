package pacote.Model.Service;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Fech_Capac;
import pacote.Model.Bean.Movimiento;
import pacote.Model.Bean.Pedido;
import pacote.Model.Bean.Ruta;
import pacote.Model.Bean.Vuelo;
import pacote.Model.DAO.DAO_Almacen;
import pacote.Model.DAO.DAO_Envio;
import pacote.Model.DAO.DAO_Movimiento;
import pacote.Model.DAO.DAO_Pedido;
import pacote.Model.DAO.DAO_Vuelo;


public class Service_Pedido {
//	private DAO_Pedido dao_pedido = new DAO_Pedido();
//	private DAO_Vuelo dao_vuelo = new DAO_Vuelo();
//	private DAO_Almacen dao_almacen = new DAO_Almacen();	
//	private DAO_Envio dao_envio = new DAO_Envio();
//	private DAO_Movimiento dao_movimiento = new DAO_Movimiento();
//
//	public void buscarRuta(Pedido pedido) throws SQLException{	
//		List<Ruta> RutasDeViaje = new ArrayList<Ruta>();
//		int estado = 0;
//		
//		Date systemTime = new Date();
//		List <Vuelo> listaVuelos;
//		listaVuelos = dao_vuelo.ListarVuelos();
//		
//		/*int cityPartida = 1;
//		int cityFinal = 5;
//		Date fechaInicio = new Date(112,8,13,10,0,0);
//		Date fechaFin = new Date(112,8,16,10,0,0);
//		int cantidadEnviar = 7;*/
//		
//		//Pedido pedido = new Pedido(cantidadEnviar,cityPartida,cityFinal,fechaInicio,fechaFin,"OK");
//		
//		Integer ICiudad = pedido.almacen_partida;
//		Integer FCiudad = pedido.almacen_entrega;
//
//		Vuelo lectorVueloLista;
//		Vuelo lectorVuelo;
//		Vuelo insertVuelo;
//		//Lo utilizo para leer de mi lista de rutas (hallando los caminos)
//		ArrayList lectorListaRutas;
//		ArrayList rutas = new ArrayList();
//		Integer lectorCiudadFin;
//		ArrayList rutasPropuestas = new ArrayList();
//		//Service_Pedido2 metodos = new Service_Pedido2();
//		
//		//****************************************
//		
//		
//		/*Inicializo la lista con los primeros vuelos que deberian iniciar
//		 * Puedo utilizar este primer bucle para crear el grafo
//		 * el cual haria mas eficiente el algoritmo
//		 */
//		for(int i = 0; i < listaVuelos.size(); i++){
//			/*Mientras que no se haya leido toda la tabla vuelos*/
//			
//			lectorVuelo = listaVuelos.get(i);
//		
//			/*Verifico si el vuelo tiene como inicio "leerCiudad"*/
//			
//			/*si la tiene, agrego a la lista, sino sigo denuevo con el bucle*/
//			if (ICiudad == lectorVuelo.ciudad_ini){
//				if (pedido.fecha_registro.getTime() < lectorVuelo.hora_inicio.getTime()){
//					ArrayList opcionR = new ArrayList();
//					insertVuelo = lectorVuelo;
//					opcionR.add(insertVuelo);
//					rutas.add(opcionR);
//				}
//			}
//		}
//
//		while (!rutas.isEmpty()){	
//			//Cojo la lista de vuelos que toca la primera ruta como opcion
//			lectorListaRutas = (ArrayList)rutas.get(0);
//			rutas.remove(0);
//			
//			//Cojo el ultimo vuelo en el que me quede de la ruta que cree
//			int pos = lectorListaRutas.size() - 1;			
//			lectorVueloLista = (Vuelo)lectorListaRutas.get(pos);
//			lectorListaRutas.remove(pos);
//			lectorCiudadFin = lectorVueloLista.ciudad_fin;
//
//			if (lectorCiudadFin == FCiudad){				
//				Ruta opcionR = new Ruta(null,0);;
//				//System.out.println("****Ruta encontrada****");
//				lectorListaRutas.add(lectorVueloLista);
//				opcionR.listaVuelos = copiarListaVuelos(lectorListaRutas);
//				rutasPropuestas.add(opcionR);
//				//imprimirRuta(lectorListaRutas);
//				
//			}
//			
//			for(int i = 0; i < listaVuelos.size(); i++){
//				/*Mientras que no se haya leido toda la tabla vuelos*/
//				lectorVuelo = listaVuelos.get(i);
//				/*Verifico si el vuelo tiene como inicio "leerCiudad"*/
//				/*si la tiene, agrego a la lista, sino sigo denuevo con el bucle*/
//				if (lectorCiudadFin == lectorVuelo.ciudad_ini){
//					if (lectorVueloLista.hora_fin.getTime() < lectorVuelo.hora_inicio.getTime()){
//						ArrayList opcionR;
//						opcionR = copiarListaVuelos(lectorListaRutas);
//						opcionR.add(copiarVuelo(lectorVueloLista));
//						opcionR.add(copiarVuelo(lectorVuelo));
//					
//						rutas.add(opcionR);
//					}
//				}	
//			}
//		}
//		
//		//Segunda Parte del algoritmo
//		List <Ruta> iterador = rutasPropuestas;
//		
//		if (iterador.size() == 0){
//			estado = 0;
//			System.out.println("No se encontro posibles rutas");
//			return;
//		}
//		
//		//Registro temporal del pedido
//		int ult_pedido = dao_pedido.registrarPedido(pedido);
//		pedido.id = ult_pedido;
//
//		while (pedido.cantidad > 0){
//			
//			if (iterador.size() == 0){
//				System.out.println("No se encontr� una ruta");
//				estado = 0;
//				return;
//			}
//			
//			for(int i = 0; i < iterador.size();i++){
//				int factor = 0;
//				iterador.get(i).capacidad = /*metodos.*/devolver_capacidad_real(iterador.get(i),pedido);
//				iterador.get(i).factor = factor;
//			}
//			
//			limpiarVacios(iterador);
//			ordenarRutasPropuestas(iterador);
//
//			if (iterador.size() == 0){
//				System.out.println("No se encontr� una ruta");
//				estado = 0;
//				return;
//			}
//
//			Ruta ruta_ins; 
//			Envio envio_insertar;
//
//			if (pedido.cantidad <= iterador.get(0).capacidad){
//				
//				iterador.get(0).cantidadEnviada = pedido.cantidad;
//				iterador.get(0).capacidad -= iterador.get(0).cantidadEnviada;
//				
//				envio_insertar = /*metodos.*/actualizacion_cache(pedido, iterador.get(0).cantidadEnviada, iterador.get(0));
//				
//				ruta_ins = iterador.get(0);
//				ruta_ins.envio = envio_insertar;
//				RutasDeViaje.add(ruta_ins);
//				
//				iterador.remove(0);
//				
//				estado = 1;
//				
//				break;
//			}
//			else{
//				
//				iterador.get(0).cantidadEnviada = iterador.get(0).capacidad;
//				iterador.get(0).capacidad -= iterador.get(0).cantidadEnviada;				
//				pedido.cantidad = pedido.cantidad - iterador.get(0).cantidadEnviada;				
//				envio_insertar = /*metodos.*/actualizacion_cache(pedido, iterador.get(0).cantidadEnviada, iterador.get(0));				
//				ruta_ins = iterador.get(0);
//				ruta_ins.envio = envio_insertar;
//				RutasDeViaje.add(ruta_ins);
//				iterador.remove(0);
//			}
//		}
//		if(estado==1){
//			System.out.println("Ruta Encontrada");
//			imprimirRuta(RutasDeViaje, pedido);
//		}
//		else{
//			System.out.println("No se encontr� ruta");
//		}
//	}
//	/*METODOS*/
//	public int devolver_capacidad_real(Ruta ruta, Pedido pedido) throws SQLException{
//		int capac_real=Integer.MAX_VALUE, vuelo_capac, capac_almacen, almacen_id; Date fech_ini, fech_fin; Vuelo vuelo1, vuelo2;
//		
//		List<Vuelo> listVuelos = ruta.listaVuelos;
//		int tamListVuelos = listVuelos.size();
//		
//		vuelo1=listVuelos.get(0);
//		capac_real = vuelo1.capacidad - vuelo1.capacidad_actual;
//		
//		for(int i=1; i<tamListVuelos; i++){
//			vuelo1 = listVuelos.get(i-1);
//			vuelo2 = listVuelos.get(i);
//			fech_ini = vuelo1.hora_fin;
//			fech_fin = vuelo2.hora_inicio;
//			almacen_id = vuelo1.ciudad_fin;
//			vuelo_capac = vuelo1.capacidad - vuelo1.capacidad_actual;
//			capac_almacen = capacidad_almacen(almacen_id, fech_ini, fech_fin);
//			capac_real = Math.min(capac_real, Math.min(capac_almacen, vuelo_capac));
//		}
//		vuelo2 = listVuelos.get(tamListVuelos-1);
//		capac_almacen = capacidad_almacen(vuelo2.ciudad_fin, vuelo2.hora_fin, pedido.fecha_entrega);
//		capac_real = Math.min(capac_real, capac_almacen);
//		return capac_real;
//	}
//	
//	
//	public int capacidad_almacen(int almacen_id, Date fech_ini, Date fech_fin) throws SQLException{
//		int capac=0; int arr_cant[]; Fech_Capac f_c;
//		List<Fech_Capac> list_fech_capac = (this.dao_almacen.consultar_movimientos_rango(almacen_id, fech_ini, fech_fin));
//		int tam_list = list_fech_capac.size();
//		//System.out.println("INI: " + fech_ini + ", FIN: "+ fech_fin );
//		
//		if(tam_list>0){
//			long min_fech=Long.MAX_VALUE, max_fech=Long.MIN_VALUE;
//			
//			for(int i=0; i<tam_list; i++){
//				f_c = list_fech_capac.get(i);
//				min_fech = Math.min(min_fech, f_c.fech_ini.getTime());
//				max_fech = Math.max(max_fech, f_c.fech_fin.getTime());
//			}
//			Long tam_arr = (max_fech - min_fech)/3600000;
//			arr_cant = new int[(int)(long)tam_arr];
//			
//			Long ii, ff;
//			for(int i=0; i<tam_list; i++){
//				f_c = list_fech_capac.get(i);
//				ii = f_c.fech_ini.getTime();
//				ff = f_c.fech_fin.getTime();
//				//System.out.println("ini : " + f_c.fech_ini + " ,  fin : " + f_c.fech_fin+", almacen : " + f_c.almacen_id);
//				int x = (int)(long)(ii - min_fech)/3600000;
//				int y = (int)(long)((tam_arr - (max_fech - ff)/3600000));
//				for(int var = x; var<y; var++){
//					arr_cant[var] += f_c.cant;
//				}
//			}
//			capac = Integer.MIN_VALUE;
//			for(int i=0; i<tam_arr; i++){
//				capac = Math.max(capac, arr_cant[i]);
//			}
//		}
//		int dev = dao_almacen.capacidad_almacen(almacen_id) - capac;
//		return dev;
//	}
//
//	public Envio actualizacion_cache(Pedido pedido, int cant, Ruta ruta){
//		Vuelo vuelo1, vuelo2;
//		
//		int ult_envio = dao_envio.registrarEnvio(pedido, cant, "OK");
//		Envio envio = new Envio();
//		envio.id = ult_envio;
//		envio.cantidad = cant;
//		
//		List <Vuelo> listVuelos = ruta.listaVuelos;
//		int tamList = listVuelos.size();
//		for(int i=0; i<tamList-1; i++){
//			vuelo1 = listVuelos.get(i);
//			vuelo2 = listVuelos.get(i+1);
//			Movimiento mov = new Movimiento(vuelo1.ciudad_fin, ult_envio, vuelo1.hora_fin, vuelo2.hora_inicio, cant, "OK");
//			dao_movimiento.insertar_Movimiento(mov);
//			dao_vuelo.actualizar_capacidad(envio, vuelo1);
//		}
//		vuelo1 = listVuelos.get(tamList - 1);
//		Movimiento mov = new Movimiento(vuelo1.ciudad_fin, ult_envio, vuelo1.hora_fin, pedido.fecha_entrega, cant, "OK");
//		dao_movimiento.insertar_Movimiento(mov);
//		dao_vuelo.actualizar_capacidad(envio, vuelo1);
//		return envio;
//	}
//	//ANTIGUA
//	
//	public static Vuelo copiarVuelo(Vuelo vuelo){
//		return new Vuelo(vuelo.vuelo_id,vuelo.ciudad_ini,vuelo.ciudad_fin, vuelo.hora_inicio,vuelo.hora_fin, vuelo.capacidad,vuelo.capacidad_actual,vuelo.estado);
//	}
//	
//	public static ArrayList copiarListaVuelos(ArrayList lista){
//		
//		ArrayList devolver = new ArrayList();
//		for(int i = 0; i < lista.size();i++){
//			Vuelo flight = (Vuelo)lista.get(i);
//			devolver.add(copiarVuelo(flight));
//		}
//		return devolver;
//	}
//	
//	public static void limpiarVacios(List <Ruta> iterador){
//		for(int i = 0; i < iterador.size();i++){
//			if(iterador.get(i).capacidad == 0){
//				iterador.remove(i);
//			}
//		}
//	}
//	
//	
//	public static void ordenarRutasPropuestas(List <Ruta> rutasPropuestas){
//		
//		for(int i = 0; i < rutasPropuestas.size() - 1;i++){
//			for(int j = i; j < rutasPropuestas.size();j++){
//				Ruta ruta1;
//				Ruta ruta2;
//				if (rutasPropuestas.get(i).capacidad < rutasPropuestas.get(j).capacidad){
//					ruta1 = rutasPropuestas.get(i);
//					ruta2 = rutasPropuestas.get(j);
//					rutasPropuestas.set(i, ruta2);
//					rutasPropuestas.set(j, ruta1);	
//				}		
//			}	
//		}
//	}
//
//	public void imprimirRuta(List<Ruta> listaRutas, Pedido pedido){
//		imprimirPedido(pedido);
//		int tamList = listaRutas.size();
//		System.out.println("Lista de Envios:");
//		System.out.println();
//		
//		System.out.println("**********************************************************************");
//		for(int i=0; i<tamList; i++){
//			Ruta ruta = listaRutas.get(i);
//			//System.out.println("--------------------------------------------------------------");
//			System.out.println("EnvioID : " + ruta.envio.id + ", Cant : " + ruta.envio.cantidad);
//			System.out.println();
//			List<Vuelo> listVuelos = ruta.listaVuelos;
//			int tamVuel = listVuelos.size();
//			for(int j=0;j<tamVuel; j++){
//				Vuelo vuelo = listVuelos.get(j); 
//				System.out.println("VueloID : " + vuelo.vuelo_id);
//				System.out.println("--> Almacen Ini : " + vuelo.ciudad_ini + ", Hora Ini : " + vuelo.hora_inicio);
//				System.out.println("--> Almacen Fin : " + vuelo.ciudad_fin + ", Hora Fin : " + vuelo.hora_fin);
//			}
//			System.out.println("--------------------------------------------------------------");
//		}
//	}
//	
//	public void imprimirPedido(Pedido pedido){
//		System.out.println("Datos del Pedido");
//		System.out.println("Pedido(ID) : " + pedido.id + ", Cantidad : " + pedido.cantidad);
//		System.out.println("Almacen de inicio : " + pedido.almacen_partida + ", Hora de inicio : " + pedido.fecha_registro);
//		System.out.println("Almacen dinal : " + pedido.almacen_entrega + ", Hora final : " + pedido.fecha_entrega);
//		
//	}
//	
//	//**************8
//	public static void imprimirVuelo(Vuelo flight){
//		/*Imprime los datos del vuelo*/
//		System.out.println("N�mero de vuelo: " + flight.vuelo_id);
//		System.out.println("Ciudad de Inicio: " + flight.ciudad_ini);
//		System.out.println("Ciudad final: " + flight.ciudad_fin);
//		System.out.println("Hora de partida: "  + (flight.hora_inicio).toString());
//		System.out.println("Hora de llegada: " + (flight.hora_fin).toString());
//		System.out.println("Capacidad: " + flight.capacidad);
//	}
//	
//	public static void imprimirRuta(ArrayList listaDeVuelos){	
//		/*Imprime una lista de vuelos*/
//		Vuelo lectorVuelo;
//		System.out.println();
//		System.out.println("*Posibilidad de ruta a seguir*");
//		System.out.println();
//		for(int i = 0; i < listaDeVuelos.size();i++){
//			lectorVuelo = (Vuelo)listaDeVuelos.get(i);
//			System.out.println("->Vuelo #" + i);
//			imprimirVuelo(lectorVuelo);
//		}
//		System.out.println();
//		System.out.println("*Fin de Posibilidad de ruta a seguir*");
//		System.out.println();
//	}
//	
	
}
