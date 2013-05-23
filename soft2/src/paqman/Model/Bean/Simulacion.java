
package paqman.Model.Bean;

import java.io.BufferedReader;
import java.io.File;
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
	public static List<Pedido> listaPedidosRecibidos;
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

	public  int inicializarSimulacion(int tiempoAct, int intervaloTiempo, double margenSeguridad,Nodo almacen,int cantAutos,int cantMotos, int vAutos,int vMotos,
									 int capacidadAutos, int capacidadMotos, double costoKmAutos, double costoKmMotos, double costoHEMoto, double costoHECarro){
		this.tiempoActual=tiempoAct;
		this.minutoAcumulado=0;
		this.costo=0;
		this.intervaloTiempo=intervaloTiempo;
		this.margenSeguridad=margenSeguridad;
		this.almacen=almacen;
		this.listaPedidosRecibidos=new ArrayList<Pedido>();
		this.listaPedidos=new ArrayList<Pedido>();
		this.listaIncidencias=new ArrayList<Incidencia>();
		this.indicePedidoInicial=0;
		this.indicePedidoFinal=0;
		this.cantidadAutos=cantidadAutos;
		this.cantidadMotos=cantidadMotos;
		this.velocidadAutos=vAutos;
		this.velocidadMotos=vMotos;
		this.capacidadAutos=capacidadAutos;
		this.capacidadMotos=capacidadMotos;
		this.costoKmAutos=costoKmAutos;
		this.costoKmMotos=costoKmMotos;
		this.costoHEMoto=costoHEMoto;
		this.costoHECarro=costoHECarro;
		this.idRutas=0;
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
		Path readFile=myDir.resolve("ArchivoIncidencias1.txt");
		try{
			String thisLine;
			BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
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
		Path myDir = Paths.get(".");
		Path readFile=myDir.resolve("ArchivoPedidos1.txt");
		try{
			String thisLine;
			BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
			int id=0;
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
		    	Pedido pedido=new Pedido(coordX,coordY,numPaquetes,/*fechaLlegada,*/minutoLlegada, tiempoEntrega,splitDatosPedido[4],id);
		    	listaPedidos.add(pedido);
		    	id++;
		   }
		   br.close();
		} catch (IOException e){
			System.err.println("Error: " + e.toString());
		}
		return 1;
	}	
	
	public  int ejecutarSimulacion(){
		this.inicializarSimulacion(/*tiempoActual*/0,/*intervaloTiempo*/ 2,/*margenSeguridad*/ 5,/*almacen*/ new Nodo (45,30),/*cantAutos*/ 20, /*cantMotos*/40,
								   /*vAutos*/30,/*vMotos*/ 60, /*capacidadAutos*/25, /*capacidadMotos*/4, /*costoKmAutos*/5, /*costoKmMotos*/3, /*costoHEMoto*/8.0,/*costoHECarro*/12.0);
		cargarPedidos();
		patriarca=BFS(almacen,new Nodo(2000000,2000000));
		Respuesta respuesta=new Respuesta();
		while(respuesta.planificarRespuesta()==1);
		System.out.printf("El costo Total es: %lf \n",Simulacion.costo);
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
		visitado[nodoInicio.getCoorAbs()]=true;
		for( int i=0 ; i < (Mapa.filas+1)*(Mapa.columnas+1) +1 ; i++ )visitado[i]=false;
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
					q.add(new Pair(Mapa.grafo.get(dir[i]),dist+1));					
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

