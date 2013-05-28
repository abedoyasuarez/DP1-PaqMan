
package paqman.Model.Bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Simulacion {
	public static int tiempoActual;
	public static int minutoAcumulado;
	public static Date fechaInicio;
	public static double costo;
	public static int intervaloTiempo;
	public static double margenSeguridad;
	public static Nodo almacen;
	public static int indicePedidoInicial;
	public static int indicePedidoFinal;
	public static List<Pedido> listaPedidosConIncidencia;
	public static List<Pedido> listaPedidos; //Para todos los pedidos
	public static List<Incidencia> listaIncidencias;
	public static int [] patriarca;
	public static int cantidadAutos;
	public static int cantidadMotos;
	public static int velocidadAutos;
	public static int velocidadMotos;
	public static int capacidadAutos;
	public static int capacidadMotos;
	public static double costoKmAutos;
	public static double costoKmMotos;
	public static double costoHEMoto;
	public static double costoHECarro;
	public static int idRutas;
	public static int idPedidos;
	public static int indiceIncidenciaInicial;
	public static int indicePedidoConIncidenciaInicial;

	public  int inicializarSimulacion(int PtiempoAct, int PintervaloTiempo, double PmargenSeguridad,Nodo Palmacen,
									  int PcantidadAutos,int PcantidadMotos, int PvAutos,int PvMotos,
									  int PcapacidadAutos, int PcapacidadMotos, double PcostoKmAutos, double PcostoKmMotos, double PcostoHEMoto, double PcostoHECarro){
		
		tiempoActual=PtiempoAct;
		minutoAcumulado=0;
		costo=0;
		intervaloTiempo=PintervaloTiempo;
		margenSeguridad=PmargenSeguridad;
		almacen=Palmacen;
		listaPedidosConIncidencia=new ArrayList<Pedido>();
		listaPedidos=new ArrayList<Pedido>();
		listaIncidencias=new ArrayList<Incidencia>();
		indicePedidoInicial=0;
		indicePedidoFinal=-1;
		indiceIncidenciaInicial=0;
		indicePedidoConIncidenciaInicial=0;
		cantidadAutos=PcantidadAutos;
		cantidadMotos=PcantidadMotos;
		velocidadAutos=PvAutos;
		velocidadMotos=PvMotos;
		capacidadAutos=PcapacidadAutos;
		capacidadMotos=PcapacidadMotos;
		costoKmAutos=PcostoKmAutos;
		costoKmMotos=PcostoKmMotos;
		costoHEMoto=PcostoHEMoto;
		costoHECarro=PcostoHECarro;
		idRutas=0;
		idPedidos=0;
		
		return 1;
	}

	public static Date getFechaActual(boolean intervalo) {
		int factor=intervalo?1:0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaInicio);
		cal.add(Calendar.MINUTE, tiempoActual+factor*(intervaloTiempo+1));
		return cal.getTime();
	}
	//cargarInicidencias();
	
	public static int cargarDirectorio(String Ruta){
		File dir = new File(Ruta);
		
		
		return 1;
	}
	
	public  static int cargarIncidencia(){
		Path myDir = Paths.get(".");
		Path readFile=myDir.resolve("C:\\Users\\Diego\\Documents\\GitHub\\DP1-PaqMan\\soft2\\src\\paqman\\Model\\Bean\\ArchivoIncidencias1.txt");
		try{
			String thisLine;
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Diego\\Documents\\GitHub\\DP1-PaqMan\\soft2\\src\\paqman\\Model\\Bean\\ArchivoIncidencias1.txt"));
		    while ((thisLine = br.readLine()) != null) {
		    	System.out.println(thisLine);
		    	String[] splitDatosIncidencia=thisLine.split(" ");
		    	String[] splitFecha=splitDatosIncidencia[0].split(":");
		    	//Date fechaLlegada=new Date();
		    	//fechaLlegada.setHours(Integer.parseInt(splitFecha[0]));
		    	//fechaLlegada.setMinutes(Integer.parseInt(splitFecha[1]));
		    	int minutoOcurrencia=Integer.parseInt(splitFecha[0])*60+Integer.parseInt(splitFecha[1]);
		    	int id_Pedido=Integer.parseInt(splitDatosIncidencia[1]);
		    	Incidencia incidencia=new Incidencia(minutoOcurrencia,id_Pedido);
		    	listaIncidencias.add(incidencia);
		   }
		   br.close();
		} catch (IOException e){
			System.err.println("Error: " + e.toString());
		}
		return 1;
	}
	

	public  static int cargarPedidos(){

		try{
			String thisLine=null;
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Diego\\Documents\\GitHub\\DP1-PaqMan\\soft2\\src\\paqman\\Model\\Bean\\ArchivoPedidos2.txt"));
			//int id=0;
		    while ((thisLine = br.readLine()) != null) {
		    	System.out.println(thisLine);
		    	String[] splitDatosPedido=thisLine.split(" ");
		    	String[] splitFecha=splitDatosPedido[0].split(":");
		    	//Date fechaLlegada=new Date();
		    	//fechaLlegada.setHours(Integer.parseInt(splitFecha[0]));
		    	//fechaLlegada.setMinutes(Integer.parseInt(splitFecha[1]));
		    	int minutoLlegada=Integer.parseInt(splitFecha[0])*60+Integer.parseInt(splitFecha[1]);
		    	
		    	int coordX=Integer.parseInt(splitDatosPedido[1]);
		    	int coordY=Integer.parseInt(splitDatosPedido[2]);
		    	int numPaquetes=Integer.parseInt(splitDatosPedido[3]);
		    	int tiempoEntrega=Integer.parseInt(splitDatosPedido[5]);
		    	Pedido pedido=new Pedido(coordX,coordY,numPaquetes,/*fechaLlegada,*/minutoLlegada, tiempoEntrega,splitDatosPedido[4],Simulacion.idPedidos++);
		    	listaPedidos.add(pedido);
		    	//id++;
		   }
		   br.close();
		} catch (IOException e){
			System.err.println("Error: " + e.toString());
		}
		return 1;
	}	
	
	public  int ejecutarSimulacion(){
		new Mapa().crearMapa(10, 10);
		this.inicializarSimulacion(/*tiempoActual*/0,/*intervaloTiempo*/ 2,/*margenSeguridad*/ 5,/*almacen*/ new Nodo (5,5),/*cantAutos*/ 20, /*cantMotos*/40,
								   /*vAutos*/30,/*vMotos*/ 60, /*capacidadAutos*/25, /*capacidadMotos*/4, /*costoKmAutos*/5, /*costoKmMotos*/3, /*costoHEMoto*/8.0,/*costoHECarro*/12.0);
		cargarPedidos();
		//cargarIncidencia();
		Mapa.imrimeMapa();
		patriarca=BFS(almacen,new Nodo(2000000,2000000));
		Respuesta respuesta=new Respuesta();
		respuesta.inicializarRespuesta();
		while(respuesta.planificarRespuesta()==1){
			minutoAcumulado+=intervaloTiempo;
			respuesta.modificarRespuesta();
			respuesta.imprimirRespuesta();
			System.out.println("El costo acumulado: " + Simulacion.costo);
			
		}
		System.out.println("El costo Total: " + Simulacion.costo);
		return 1;
	}
	
	public static boolean enIntervalo(Pedido pedido){
		Date fechaActual=getFechaActual(false);
		Date fechaIntervalo=getFechaActual(true);
		
		return (pedido.getHoraLlegada().after(fechaActual)&&pedido.getHoraLlegada().before(fechaIntervalo));
	}
	
	public static boolean hastaIntervalo(Pedido pedido){
		Date fechaIntervalo=getFechaActual(true);
		return (pedido.getHoraLlegada().before(fechaIntervalo));
	}

	
	public static int [] BFS(Nodo nodoInicio,Nodo nodoFin){
		
		int [] padre= new int [(Mapa.filas+1)*(Mapa.columnas+1) +3];
		boolean [] visitado=new boolean [(Mapa.filas+1)*(Mapa.columnas+1) +1];
		
		Queue <Pair<Nodo,Integer> >q = new LinkedList<Pair<Nodo,Integer>>(); 
		for( int i=0 ; i < (Mapa.filas+1)*(Mapa.columnas+1) +1 ; i++ )visitado[i]=false;
		visitado[nodoInicio.getCoorAbs()]=true;
		padre[nodoInicio.getCoorAbs()]=nodoInicio.getCoorAbs();
		q.add(new Pair(nodoInicio,0));
		padre[(Mapa.filas+1)*(Mapa.columnas+1) +2]=2000000;
		int [] dir= new int [4];
		while (q.peek()!=null){
			Nodo u=q.peek().getFirst();
			int dist=q.peek().getSecond();
			q.poll();
			if (u.getCoorAbs()==nodoFin.getCoorAbs()){
					padre[(Mapa.filas+1)*(Mapa.columnas+1) +2]=dist;
					return padre;
			}
	
			dir[0]=Mapa.grafo.get(u.getCoorAbs()).up;
			dir[1]=Mapa.grafo.get(u.getCoorAbs()).left;
			dir[2]=Mapa.grafo.get(u.getCoorAbs()).down;
			dir[3]=Mapa.grafo.get(u.getCoorAbs()).right;
			
			for( int i=0 ; i < 4 ; i++ ){
				if(dir[i]!=-1 && !visitado[dir[i]]){
					visitado[dir[i]]=true;
					padre[dir[i]]=u.getCoorAbs();
					q.offer(new Pair(Mapa.grafo.get(dir[i]),dist+1));					
				}
				
			}
				
		}
		
		return padre;
	}
	
//	
//	public static void leerPedidos(){
//		for(Pedido pedido:listaPedidos){
//			if(enIntervalo(pedido)){
//				listaPedidosRecibidos.add(pedido);
//			}			
//		}
//	}
	
}

