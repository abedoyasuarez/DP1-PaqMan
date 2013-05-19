
package paqman.Model.Bean;

import java.io.BufferedReader;
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
	public static Date fechaInicio;
	public static double costo;
	public static int intervaloTiempo;
	public static double margenSeguridad;
	public static Nodo almacen;
	public static List<Pedido> listaPedidosRecibidos;
	public static List<Pedido> listaPedidos; //Para todos los pedidos

	public int inicializarSimulacion(int tiempoAct, int intervaloTiempo, double margenSeguridad,Nodo almacen){
		this.tiempoActual=tiempoAct;
		this.costo=0;
		this.intervaloTiempo=intervaloTiempo;
		this.margenSeguridad=margenSeguridad;
		this.almacen=almacen;
		this.listaPedidosRecibidos=new ArrayList<Pedido>();
		this.listaPedidos=new ArrayList<Pedido>();
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
	public static int cargarPedidos(){
		Path myDir = Paths.get(".");
		Path readFile=myDir.resolve("ArchivoPaquetes1.txt");
		try{
			String thisLine;
			BufferedReader br = Files.newBufferedReader(readFile,Charset.forName("UTF-8"));
		    while ((thisLine = br.readLine()) != null) {
		    	System.out.println(thisLine);
		    	String[] splitDatosPedido=thisLine.split(" ");
		    	String[] splitFecha=splitDatosPedido[0].split(":");
		    	Date fechaLlegada=new Date();
		    	fechaLlegada.setHours(Integer.parseInt(splitFecha[0]));
		    	fechaLlegada.setMinutes(Integer.parseInt(splitFecha[1]));
		    	int coordX=Integer.parseInt(splitDatosPedido[1]);
		    	int coordY=Integer.parseInt(splitDatosPedido[2]);
		    	int numPaquetes=Integer.parseInt(splitDatosPedido[3]);
		    	int tiempoEntrega=Integer.parseInt(splitDatosPedido[5]);
		    	Pedido pedido=new Pedido(coordX,coordY,numPaquetes,fechaLlegada,tiempoEntrega,splitDatosPedido[4]);
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

//	
//	public static void leerPedidos(){
//		for(Pedido pedido:listaPedidos){
//			if(enIntervalo(pedido)){
//				listaPedidosRecibidos.add(pedido);
//			}			
//		}
//	}
	
}

