
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
import java.util.List;

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

	public int inicializarSimulacion(int tiempoAct, int intervaloTiempo, double margenSeguridad,Nodo almacen){
		this.tiempoActual=tiempoAct;
		this.minutoAcumulado=0;
		this.costo=0;
		this.intervaloTiempo=intervaloTiempo;
		this.margenSeguridad=margenSeguridad;
		this.almacen=almacen;
		this.listaPedidosRecibidos=new ArrayList<Pedido>();
		this.listaPedidos=new ArrayList<Pedido>();
		this.indicePedidoInicial=0;
		this.indicePedidoFinal=0;
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
	
	public  static int cargarPedidos(){
		Path myDir = Paths.get(".");
		Path readFile=myDir.resolve("ArchivoPaquetes1.txt");
		try{
			String thisLine;
			BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
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
		    	Pedido pedido=new Pedido(coordX,coordY,numPaquetes,/*fechaLlegada,*/minutoLlegada, tiempoEntrega,splitDatosPedido[4]);
		    	listaPedidos.add(pedido);
		   }
		   br.close();
		} catch (IOException e){
			System.err.println("Error: " + e.toString());
		}
		return 1;
	}
	
	public static int ejecutarSimulacion(){
		Simulacion.cargarPedidos();
		Respuesta respuesta=new Respuesta();
		while(respuesta.planificarRespuesta()==1);
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

	
	public static int [] BFS(){
		
		int [] padre= new int [(Mapa.filas+1)*(Mapa.columnas+1) +1];
		boolean [] visitado=new boolean [(Mapa.filas+1)*(Mapa.columnas+1) +1];
		
		
		return null;
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

