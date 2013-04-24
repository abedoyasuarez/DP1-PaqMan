package pacote.Model.DAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import pacote.Model.Bean.Almacen;
import pacote.Model.Bean.Vuelo;
import pacote.Model.Bean.Response.AlmacenX;
import pacote.Model.Bean.Response.DatoSimulacion;
import pacote.Model.Bean.Response.ListAlmacen;
import pacote.Model.Bean.Response.VueloX;
import pacote.Model.Service.Service_Pedido3;

public class DAO_Simulacion  extends ConnectBD implements Serializable{
	private static Conexion conexion = new Conexion();
	
	Service_Pedido3 pedidoService = new Service_Pedido3();
	DAO_Almacen almacenDAO = new DAO_Almacen();
	DAO_Vuelo vueloDAO = new DAO_Vuelo();
	
	
	private class Coordenadas {	float x; float y;}
	private class Parab {int id_almacen; float a; float b; float c;}
	private class abc {float a; float b; float c;}
	private class ParabVuelo {int id_almacen1; int id_almacen2; float a; float b; float c;}
	ArrayList<Parab> listaParabolas ;
	ArrayList<ParabVuelo> listaParabolasVuelos ;
	
	int numDiasTranscurridos = 0;
	int numDiasPedidos = 0;
	
	ArrayList<AlmacenX> misAlmacenes;
	ArrayList<DatoSimulacion> datosHistoricos;
	
	int contFechaActual;

	
	private void leeAlmacenes(){
		
		misAlmacenes = new ArrayList<AlmacenX>();
		ListAlmacen listaAlmacenes = new ListAlmacen();
		
		//LEEMOS LOS ALMACENES ACTIVOS
		try{
			conexion.abrirConexion();
			almacenDAO.setConexion(conexion);
			listaAlmacenes = almacenDAO.ListarAlmacenesActivos();
			conexion.cerrarConexion();

			//POR CADA ALMACEN ACTIVO, LO GUARDAMOS
			for(int i = 0; i< listaAlmacenes.listaAlmacenes.size();i++){
				AlmacenX myAlm = new AlmacenX();
				myAlm.id = listaAlmacenes.listaAlmacenes.get(i).id;
				myAlm.capacidad = listaAlmacenes.listaAlmacenes.get(i).capacidad;
				myAlm.latitud = listaAlmacenes.listaAlmacenes.get(i).latitud;
				myAlm.longitud = listaAlmacenes.listaAlmacenes.get(i).longitud;
				myAlm.nombre = listaAlmacenes.listaAlmacenes.get(i).ciudad;
				misAlmacenes.add(myAlm); //falta guardar cual es su llenado y su porcentaje
			}
		}catch(Exception e){
			System.out.println("SIM-Error al listar almacenes");
		}
		System.out.println("ALMACENES LEIDOS="+listaAlmacenes.listaAlmacenes.size());
		
	}

	private abc algoritmoSim(ArrayList<Coordenadas> datos){
		abc myABC = new abc();
		
		float x =0,y=0;
		float sumaX=0,sumaY=0,sumaXY=0,sumaX2=0,sumaX3=0,sumaX4=0,sumaX2Y=0;	
		float numDatos = 0;
		float dA=0;
		float dP=0;
		float a=0,b=0,c=0;
		
		numDatos = datos.size();
		for(int i= 0; i<datos.size();i++){
			x = datos.get(i).x;
			y = datos.get(i).y;
			sumaX = sumaX + x;
			sumaY = sumaY + y;
			sumaXY = sumaXY + x*y;
			sumaX2 = sumaX2 + x*x;
			sumaX3 = sumaX3 + x*x*x;
			sumaX4 = sumaX4 + x*x*x*x;
			sumaX2Y = sumaX2Y + x*x*y;
		}	
		System.out.println("Datos: x=<"+x+">,y=<"+y+">,sumaX=<"+sumaX+">,sumaY=<"+sumaY+">,sumaXY=<"+sumaXY+">,sumaX2=<"+sumaX2+">,sumaX3=<"+sumaX3+">,sumaX4=<"+sumaX4+">,sumaX2Y=<"+sumaX2Y+">");

		b = ((sumaXY-sumaX*sumaY/numDatos)*(sumaX4-sumaX2*sumaX2/numDatos)-(sumaX2Y-sumaX2*sumaY/numDatos)*(sumaX3-sumaX2*sumaX/numDatos))
					/((sumaX2-sumaX*sumaX/numDatos)*(sumaX4-sumaX2*sumaX2/numDatos)-(sumaX3-sumaX2*sumaX/numDatos)*(sumaX3-sumaX2*sumaX/numDatos));
		
		c = ((sumaX2-sumaX*sumaX/numDatos)*(sumaX2Y-sumaX2*sumaY/numDatos)-(sumaX3-sumaX2*sumaX/numDatos)*(sumaXY-sumaX*sumaY/numDatos))
					/((sumaX2-sumaX*sumaX/numDatos)*(sumaX4-sumaX2*sumaX2/numDatos)-(sumaX3-sumaX2*sumaX/numDatos)*(sumaX3-sumaX2*sumaX/numDatos));
		
		a = (sumaY-b*sumaX-c*sumaX2)/numDatos;
		
		myABC.a = a;
		myABC.b = b;
		myABC.c = c;
		return myABC;
	}
	
	private abc algoritmoSim0(ArrayList<Coordenadas> datos){
		abc myABC = new abc();
		
		float x =0,y=0;
		float sumaX=0,sumaY=0,sumaXY=0,sumaX2=0,sumaX3=0,sumaX4=0,sumaX2Y=0;	
		float numDatos = 0;
		float dA=0;
		float dP=0;
		float a=0,b=0,c=0;
		
		for(int i= 0; i<datos.size();i++){
			x = datos.get(i).x;
			y = datos.get(i).y;
			sumaX = sumaX + x;
			sumaY = sumaY + y;
			sumaXY = sumaXY + x*y;
			sumaX2 = sumaX2 + x*x;
			sumaX3 = sumaX3 + x*x*x;
			sumaX4 = sumaX4 + x*x*x*x;
			sumaX2Y = sumaX2Y + x*x*y;
		}	
		System.out.println("Datos: x=<"+x+">,y=<"+y+">,sumaX=<"+sumaX+">,sumaY=<"+sumaY+">,sumaXY=<"+sumaXY+">,sumaX2=<"+sumaX2+">,sumaX3=<"+sumaX3+">,sumaX4=<"+sumaX4+">,sumaX2Y=<"+sumaX2Y+">");
		dA = sumaY*sumaX2*sumaX2 + numDatos*sumaXY*sumaX3 + sumaX*sumaX*sumaX2Y;
		dA = dA - numDatos*sumaX2*sumaX2Y - sumaX*sumaX3*sumaY - sumaX*sumaXY*sumaX2;
		dP = sumaX2*sumaX2*sumaX2 + numDatos*sumaX3*sumaX3 + sumaX4*sumaX*sumaX;
		dP = dP - numDatos*sumaX2*sumaX4 - 2*sumaX*sumaX2*sumaX3;
		System.out.println("Datos2: dA=<"+dA+">,dP=<"+dP+">");
		a = dA/dP;
		b = (sumaX*(sumaY-a*sumaX2)) - numDatos*(sumaXY-a*sumaX3)/(sumaX*sumaX-numDatos*sumaX2);
		c = (sumaY - a*sumaX2 - b*sumaX ) / numDatos;
		
		myABC.a = a;
		myABC.b = b;
		myABC.c = c;
		return myABC;
	}
	/*
	private ArrayList<VueloX> leeVuelos_(Date fecha1, Date fecha2){
		
		ArrayList<VueloX> respListaVuelos = new ArrayList<VueloX>();
		
		//LEEMOS LOS ALMACENES ACTIVOS
		try{
			conexion.abrirConexion();
			setConexion(conexion);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String f1 = sdf.format(fecha1);
			String f2 = sdf.format(fecha2);
			
			
			String sql = "SELECT V.vuelo_id, V.vuelo_capacidad, V.almacen_partida, V.almacen_llegada, V.vuelo_hora_partida, V.vuelo_hora_llegada,"
						 +" A1.almacen_ciudad, A1.almacen_latitud, A1.almacen_longitud, A1.almacen_ciudad, A1.almacen_latitud, A1.almacen_longitud"
						 +" FROM Vuelo V, Almacen A1, Almacen A2"
						 +" WHERE V.vuelo_estado=1 AND V.almacen_partida=A1.almacen_id AND V.almacen_Llegada=A2.almacen_id AND (V.vuelo_hora_partida between '"+f1+"' AND '"+f2+"'  ) ";
			PreparedStatement pst = conexion.conn.prepareStatement(sql);
		    pst.execute();
			ResultSet rs = pst.getResultSet();
			while(rs.next()){
				VueloX myVueloX = new VueloX();
				myVueloX.vuelo_id = rs.getInt(1);
				myVueloX.capacidad = rs.getInt(2);
				myVueloX.almacenPartidaID = rs.getInt(3);
				myVueloX.almacenLlegadaID = rs.getInt(4);
				myVueloX.horaSalidaDate = rs.getDate(5);
				myVueloX.horaLlegadaDate = rs.getDate(6);
				myVueloX.almacenPartida = rs.getString(7);
				myVueloX.almacenPartidaLatitud = rs.getFloat(8);
				myVueloX.almacenPartidaLongitud = rs.getFloat(9);
				myVueloX.almacenLlegada = rs.getString(10);
				myVueloX.almacenLlegadaLatitud = rs.getFloat(11);
				myVueloX.almacenLlegadaLongitud = rs.getFloat(12);	
				respListaVuelos.add(myVueloX);
				misVuelos.add(myVueloX);
			}
			
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("SIM-Error al listar vuelos");
		}
		
		System.out.println("Vuelos LEIDOS="+respListaVuelos.size());
		return respListaVuelos;
	}
	*/
	private ArrayList<VueloX> leeVuelos(Date fecha1){
		
		//ArrayList<VueloX> respListaVuelos = new ArrayList<VueloX>();
		ArrayList<VueloX> misVuelos = new ArrayList<VueloX>();
		//LEEMOS LOS ALMACENES ACTIVOS
		try{
			conexion.abrirConexion();
			setConexion(conexion);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
			String f1 = sdf.format(fecha1);
			
			String sql = "select  VM.vuelo_code , A1.almacen_id,A1.almacen_ciudad, A1.almacen_latitud, A1.almacen_longitud, A2.almacen_id,A2.almacen_ciudad,A2.almacen_latitud, A2.almacen_longitud,sum(E.envio_cantidad)" 
						+" from Envio E, Vuelo_Mov VM, Almacen A1, Almacen A2"
						+" where E.envio_id = VM.envio_id and A1.almacen_id = SUBSTRING(VM.vuelo_code,9,2) and A2.almacen_id = SUBSTRING(VM.vuelo_code,11,2)"
						+" AND SUBSTRING(VM.vuelo_code,1,8) = '"+f1+"'"
						+" group by VM.vuelo_code , A1.almacen_id,A1.almacen_ciudad, A1.almacen_latitud, A1.almacen_longitud, A2.almacen_id,A2.almacen_ciudad,A2.almacen_latitud, A2.almacen_longitud";
			
			PreparedStatement pst = conexion.conn.prepareStatement(sql);
		    pst.execute();
			ResultSet rs = pst.getResultSet();
			while(rs.next()){
				VueloX myVueloX = new VueloX();
				//myVueloX.vuelo_id = rs.getInt(1);
				//myVueloX.codVuelo = rs.getString(1);
				myVueloX.capacidad = 300;
				
				myVueloX.almacenPartidaID = rs.getInt(2);
				myVueloX.almacenPartida = rs.getString(3);
				myVueloX.almacenPartidaLatitud = rs.getFloat(4);
				myVueloX.almacenPartidaLongitud = rs.getFloat(5);

				myVueloX.almacenLlegadaID = rs.getInt(6);
				myVueloX.almacenLlegada = rs.getString(7);
				myVueloX.almacenLlegadaLatitud = rs.getFloat(8);
				myVueloX.almacenLlegadaLongitud = rs.getFloat(9);
				
				myVueloX.cantidadLleno = rs.getInt(10);
				
				//respListaVuelos.add(myVueloX);
				misVuelos.add(myVueloX);
			}
			
			conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("SIM-Error al listar vuelos");
		}
		
		System.out.println("Vuelos LEIDOS="+misVuelos.size());
		return misVuelos;
	}
	
	/*
	private ArrayList<Integer> obtenDestinos(int id_almacen){
	ArrayList<Integer> destinos = new ArrayList<Integer>();
	try{
		conexion.abrirConexion();
		setConexion(conexion);
		
		String sql = "SELECT DISTINCT(V.almacen_Llegada) FROM Vuelo V"
					 +" WHERE V.vuelo_estado=1 AND V.almacen_partida="+id_almacen;
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			destinos.add(rs.getInt(1));
		}
		
		conexion.cerrarConexion();
	}catch(Exception e){
		System.out.println("SIM-Error al listar vuelos");
	}
	
	
	return destinos;
}
*/
/*
	private int calculaLlenadoVuelo(int id_vuelo){
	int llenado=0;

	try{
		conexion.abrirConexion();
		setConexion(conexion);
		String sql = "SELECT SUM(E.envio_cantidad)"
					+" FROM Envio E, Vuelo_Mov VM"
					+" WHERE E.envio_id=VM.envio_id AND VM.vuelo_id="+id_vuelo+" AND E.envio_estado!=0";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			llenado = rs.getInt(1);	
		}
		conexion.cerrarConexion();
	}catch(Exception e){
		System.out.println("SIM-Error al calcular llenado");
	}

	return llenado;	
}
*/
/*
	private ArrayList<VueloX> llenaCapacidadesVuelos(ArrayList<VueloX> vuelos){
		for(int i=0; i<vuelos.size();i++){
			vuelos.get(i).cantidadLleno = calculaLlenadoVuelo(vuelos.get(i).vuelo_id);
			vuelos.get(i).porcentaje = (vuelos.get(i).cantidadLleno  / vuelos.get(i).capacidad ) * 100;
		}
		return vuelos;
	}
*/
	/*
	public ArrayList<VueloX> getVuelosPorFecha(Date fecha1, Date fecha2){
		ArrayList<VueloX> vuelosFecha =leeVuelos(fecha1, fecha2);
		vuelosFecha = llenaCapacidadesVuelos(vuelosFecha);		
		return vuelosFecha;
	}
	
	*/
	
	private Date calculaFechaInicial(int diasMenos){
		Date fechaInicial = calculaFechaActual();
		diasMenos=15;//solo para pruebas
		try{
			Statement s = null;
			ResultSet rs = null;
			conexion.abrirConexion();
			setConexion(conexion);
			s = conexion.conn.createStatement();
			if(diasMenos==0){
				s.executeQuery("SELECT min(movimiento_hora_entrada) FROM Movimiento WHERE movimiento_estado=1");
			}else{
				s.executeQuery("SELECT ADDDATE(SYSDATE(), INTERVAL "+(-1*diasMenos)+" DAY)");			
			}

			rs = s.getResultSet();
			while(rs.next()){
				fechaInicial = rs.getDate(1);
			}
			conexion.cerrarConexion();
			
		}catch(Exception e){
			System.out.println("error en calcular fecha Inicial+"+e.toString());
		}
		System.out.println("Fecha Inicial = "+fechaInicial.toString());
		return fechaInicial;
	}
	
	private Date calculaFechaActual(){
		Date fechaActual = new Date(new Date().getTime());
		System.out.println("Fecha Actual = "+fechaActual.toString());

		return fechaActual;
	}
	

	private int calculaLlenado(int id_almacen,int cap, Date fecha1, Date fecha2){
		int llenado = 0;
		int minimoDisponible = 0;
		try {
			conexion.abrirConexion();
			pedidoService.setearConexion(conexion);
			minimoDisponible = pedidoService.capacidad_almacen(id_almacen, fecha1, fecha2);
			conexion.cerrarConexion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		llenado = cap - minimoDisponible;
		System.out.println("LLENADO PARA ALMACEN ID="+id_almacen+"->"+llenado +"tuvo cap="+cap + " y min="+minimoDisponible);
		return llenado;
	}
	
	
	private void llenaParabolas(){
		listaParabolas = new ArrayList<Parab>();
		listaParabolasVuelos = new ArrayList<ParabVuelo>();
		
		//por cada almacen hallo su parabola
		for(int i = 0; i<misAlmacenes.size(); i++){
			
			AlmacenX myAlm = misAlmacenes.get(i);
			Parab myParab = calculaParabola(myAlm.id);
			listaParabolas.add(myParab);
			
			for(int j=0; j<misAlmacenes.size();j++){
				ParabVuelo myParabVuelo = calculaParabolaVuelo(misAlmacenes.get(i).id, misAlmacenes.get(j).id);
				listaParabolasVuelos.add(myParabVuelo);
			}
		}

			
	}
	
	private ArrayList<Coordenadas> buscaDatos(int id_almacen){
		ArrayList<Coordenadas> datos = new ArrayList<Coordenadas>();
		
		System.out.print("DAtos para almacen=<"+id_almacen+">");
		for(int i=0;i<datosHistoricos.size();i++){
			Coordenadas myDato = new Coordenadas();
			
			myDato.x = i+1;
						
			for(int j=0;j<datosHistoricos.get(i).almacenes.size();j++){
				if (datosHistoricos.get(i).almacenes.get(j).id == id_almacen){
					myDato.y = datosHistoricos.get(i).almacenes.get(j).cantidadLleno;
				}
			}
			System.out.print("<"+myDato.x+","+myDato.y+">");
			datos.add(myDato);
		}
		System.out.println("");
		
		return datos;
	}
	
	
	private ArrayList<Coordenadas> buscaDatosVuelo(int id_almacen1, int id_almacen2){
		ArrayList<Coordenadas> datos = new ArrayList<Coordenadas>();
		
		for(int i=0;i<datosHistoricos.size();i++){
			Coordenadas myDato = new Coordenadas();
			
			myDato.x = i+1;
			myDato.y =0;
			for(int j=0;j<datosHistoricos.get(i).vuelos.size();j++){
				if((datosHistoricos.get(i).vuelos.get(j).almacenPartidaID==id_almacen1)&&(datosHistoricos.get(i).vuelos.get(j).almacenLlegadaID==id_almacen2)){
					if(myDato.y< datosHistoricos.get(i).vuelos.get(j).cantidadLleno){
						myDato.y = datosHistoricos.get(i).vuelos.get(j).cantidadLleno;
					}
				}
			}
					
			System.out.print("<"+myDato.x+","+myDato.y+">");
			datos.add(myDato);
		}
		System.out.println("");
		
		return datos;
	}
	
	
	
	private ParabVuelo calculaParabolaVuelo(int id_almacen1, int id_almacen2){
		ParabVuelo myParabola = new ParabVuelo();

		
		ArrayList<Coordenadas> datos = buscaDatosVuelo(id_almacen1, id_almacen2);
		
		abc myABC = algoritmoSim(datos);
		
		myParabola.id_almacen1 = id_almacen1;
		myParabola.id_almacen2 = id_almacen2;
		myParabola.a = myABC.a;
		myParabola.b = myABC.b;
		myParabola.c = myABC.c;
		
		System.out.println("PARABOLA PARA VUELO <"+id_almacen1+","+id_almacen2+"> a=<"+myABC.a+">,b=<"+myABC.b+">,c=<"+myABC.c+">");
		return myParabola;
	}
	
	private Parab calculaParabola(int id_almacen){
		Parab myParabola = new Parab();

		
		ArrayList<Coordenadas> datos = buscaDatos(id_almacen);
		
		abc myABC = algoritmoSim(datos);
		
		myParabola.id_almacen = id_almacen;
		myParabola.a = myABC.a;
		myParabola.b = myABC.b;
		myParabola.c = myABC.c;
		
		System.out.println("PARABOLA PARA ALMACEN <"+id_almacen+"> a=<"+myABC.a+">,b=<"+myABC.b+">,c=<"+myABC.c+">");
		return myParabola;
	}
	
	private int calculaY(int id_almacen,int nuevo_dato){
		for(int i=0;i<listaParabolas.size();i++){
			if(listaParabolas.get(i).id_almacen==id_almacen){
				return calculaY(nuevo_dato,listaParabolas.get(i).a,listaParabolas.get(i).b,listaParabolas.get(i).c);
			}
		}
		return 0;
	}
	
	private int calculaYVuelo(int id_almacen1, int id_almacen2,int nuevo_dato){
		for(int i=0;i<listaParabolas.size();i++){
			if((listaParabolasVuelos.get(i).id_almacen1==id_almacen1)&&(listaParabolasVuelos.get(i).id_almacen2==id_almacen2)){
				return calculaY(nuevo_dato,listaParabolasVuelos.get(i).a,listaParabolasVuelos.get(i).b,listaParabolasVuelos.get(i).c);
			}
		}
		return 0;
	}
	
	
	private int calculaY(int myX,float a, float b, float c){
		float myY = 0;
		//http://reyesestadistica.blogspot.com/2011/07/analisis-de-regresion-cuadratica.html
		myY = c*myX*myX + b*myX + a;
		return (int) myY;
	}
	
	private ArrayList<AlmacenX> copiarAlmacenes(){
		ArrayList<AlmacenX> listAlm = new ArrayList<AlmacenX>();
		for(int i=0;i<misAlmacenes.size();i++){
			AlmacenX myAlm= new AlmacenX();
			myAlm.id =	misAlmacenes.get(i).id;
			myAlm.capacidad = misAlmacenes.get(i).capacidad;
			myAlm.nombre = misAlmacenes.get(i).nombre;
			myAlm.latitud = misAlmacenes.get(i).latitud;
			myAlm.longitud = misAlmacenes.get(i).longitud;
			listAlm.add(myAlm);
		}
		return listAlm;
	}
	
	private ArrayList<VueloX> generaVuelos(){
		ArrayList<VueloX> vuelitos = new ArrayList<VueloX>();
		try {
			conexion.abrirConexion();

			setConexion(conexion);

			String sql ="SELECT V.vuelo_capacidad, V.almacen_partida, V.almacen_llegada, A1.almacen_ciudad, A1.almacen_latitud, A1.almacen_longitud, A2.almacen_ciudad, A2.almacen_latitud, A2.almacen_longitud"
						+" FROM Vuelo V, Almacen A1, Almacen A2"
						+" Where  V.almacen_partida=A1.almacen_id AND V.almacen_Llegada=A2.almacen_id  AND DATE_FORMAT(vuelo_hora_partida,'%Y%m%d') = DATE_FORMAT(SYSDATE(),'%Y%m%d')";
			PreparedStatement pst;

			pst = conexion.conn.prepareStatement(sql);
			pst.execute();
				ResultSet rs = pst.getResultSet();
				while(rs.next()){
					VueloX myVueloX = new VueloX();
					
					myVueloX.capacidad = rs.getInt(1);
					
					myVueloX.almacenPartidaID = rs.getInt(2);
					myVueloX.almacenLlegadaID = rs.getInt(3);
					
					myVueloX.almacenPartida = rs.getString(4);
					myVueloX.almacenPartidaLatitud = rs.getFloat(5);
					myVueloX.almacenPartidaLongitud = rs.getFloat(6);

					myVueloX.almacenLlegada = rs.getString(7);
					myVueloX.almacenLlegadaLatitud = rs.getFloat(8);
					myVueloX.almacenLlegadaLongitud = rs.getFloat(9);
							
					vuelitos.add(myVueloX);
		}
			conexion.cerrarConexion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR EN LEER VUELOS DIARIOS");
		}
		
		return vuelitos;		
	}
	
	
	private ArrayList<DatoSimulacion> calculaDatosSimulados(Date fechaSim){
		ArrayList<DatoSimulacion> listaSim = new ArrayList<DatoSimulacion>();
		
		Date fechActual = calculaFechaActual();	
		Date fechaX = fechActual;
	
		ArrayList<VueloX> vuelitos= generaVuelos();
		
		while(fechaX.before(fechaSim)){
			fechaX = getFechaMasUno(fechaX); //ahora aumento en 1 mi contador.
			contFechaActual++;
			DatoSimulacion myDato = new DatoSimulacion();
			myDato.fecha = fechaX;	//guardo esa fecha
			
			java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");			
			myDato.fechaCad = sdf.format(myDato.fecha);
			myDato.almacenes = copiarAlmacenes(); //guardo todos los almacenes para esa fecha
		
			//por cada almacen tengo que guardar su llenado en esa epoca
			for(int i = 0; i< myDato.almacenes.size();i++){
				myDato.almacenes.get(i).cantidadLleno=calculaY(myDato.almacenes.get(i).id,contFechaActual);
				myDato.almacenes.get(i).porcentajeLlenado = (myDato.almacenes.get(i).cantidadLleno * 100 / myDato.almacenes.get(i).capacidad); 		
			}
			
			//por cada vuelo, guardo su llenado hasta esa epoca
			try{
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				oos.writeObject(vuelitos);
	
				// Construir en byteArrayInputStream con el objeto y leerlo.
				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
				ObjectInputStream ois = new ObjectInputStream(bais);
				
				@SuppressWarnings("unchecked")
				ArrayList<VueloX> vuelitos2 = (ArrayList<VueloX>)ois.readObject();
				
				for(int j=0;j<vuelitos2.size();j++){
					vuelitos2.get(j).cantidadLleno=calculaYVuelo(vuelitos2.get(j).almacenPartidaID,vuelitos2.get(j).almacenLlegadaID,contFechaActual);
					System.out.println("-->Calculo su cantidadLLeno="+vuelitos2.get(j).cantidadLleno);
					vuelitos2.get(j).porcentaje = ((float)vuelitos2.get(j).cantidadLleno * 100 / (float)vuelitos2.get(j).capacidad);
					System.out.println("--------->porcentaje ="+vuelitos2.get(j).porcentaje);
				}
				
				myDato.vuelos = vuelitos2;
			}catch(Exception e){
				System.out.println("Error al generar vuelos simulados !!");
			}
			listaSim.add(myDato);
						
			/*	for(int j=0;j<obtenDestinos(myDato.almacenes.get(i).id).size();j++){ //leemos cada almacenDestino
				
					VueloX myVuelo = new VueloX();
					
					myVuelo.cantidadLleno=0;
					myVuelo.capacidad=1;
					myVuelo = buscaVuelo(myDato.almacenes.get(i).id,obtenDestinos(myDato.almacenes.get(i).id).get(j));
					if(myVuelo.existe==1){
						myVuelo.cantidadLleno = calculaYVuelo(myDato.almacenes.get(i).id,obtenDestinos(myDato.almacenes.get(i).id).get(j),contFechaActual);
						System.out.println("-->Calculo su cantidadLLeno="+myVuelo.cantidadLleno);
						myVuelo.porcentaje = (myVuelo.cantidadLleno * 100 / myVuelo.capacidad);
						myDato.vuelos.add(myVuelo);
					}
				}
			}
			*/
		}
		
		return listaSim;
	}
	/*
	private VueloX buscaVuelo(int id_almacen1, int id_almacen2) {
		
		System.out.println("-------------------->BUSCAVUELOS<"+id_almacen1+","+id_almacen2+">-vuelosSize="+misVuelos.size());
		VueloX myVuelo = new VueloX();
		myVuelo.existe = 0;
		myVuelo.capacidad=0;
		
		for(int i=0;i<misVuelos.size();i++){
			if ((misVuelos.get(i).almacenPartidaID==id_almacen1)&&(misVuelos.get(i).almacenLlegadaID==id_almacen2)){
				if(myVuelo.capacidad<misVuelos.get(i).capacidad){
					myVuelo.capacidad = misVuelos.get(i).capacidad;
					myVuelo.almacenPartidaID = misVuelos.get(i).almacenPartidaID;
					myVuelo.almacenLlegadaID = misVuelos.get(i).almacenLlegadaID;
					myVuelo.horaSalidaDate = misVuelos.get(i).horaSalidaDate;
					myVuelo.horaLlegadaDate = misVuelos.get(i).horaLlegadaDate;
					myVuelo.almacenPartida =misVuelos.get(i).almacenPartida;
					myVuelo.almacenPartidaLatitud = misVuelos.get(i).almacenPartidaLatitud;
					myVuelo.almacenPartidaLongitud = misVuelos.get(i).almacenPartidaLongitud;
					myVuelo.almacenLlegada = misVuelos.get(i).almacenLlegada;
					myVuelo.almacenLlegadaLatitud = misVuelos.get(i).almacenLlegadaLatitud;
					myVuelo.almacenLlegadaLongitud = misVuelos.get(i).almacenLlegadaLongitud;
					
					myVuelo.existe = 1;
					System.out.println("Encontro con capacidad="+myVuelo.capacidad);
				}
			}
		}
		
		return myVuelo;
	}

*/


	private Date getFechaMasUno(Date fech){
		Date myFecha = new Date(fech.getTime()+(24*60*60*1000));
       // System.out.println("FechamasUno("+fech.toString()+")="+myFecha.toString());
		return myFecha;
	}
	
	
	private void llenaDatosHistoricos(int diasMenos){
		datosHistoricos = new ArrayList<DatoSimulacion>();
		Date fechaX = calculaFechaInicial(diasMenos);
		Date fechActual = calculaFechaActual();

		//misVuelos = new ArrayList<VueloX>();
		contFechaActual = 0;
		while(fechaX.before(fechActual)){
			System.out.println("---------------------------------->FECHA="+fechaX+" FechaFin="+getFechaMasUno(fechaX));
			//System.out.println("es menor");
			contFechaActual++;
			DatoSimulacion myDato = new DatoSimulacion();
			myDato.fecha = fechaX;	//guardo esa fecha
			myDato.almacenes = copiarAlmacenes(); //guardo todos los almacenes para esa fecha
			//por cada almacen tengo que guardar su llenado en esa epoca
			for(int i = 0; i< myDato.almacenes.size();i++){
				myDato.almacenes.get(i).cantidadLleno = calculaLlenado(myDato.almacenes.get(i).id, myDato.almacenes.get(i).capacidad,fechaX,getFechaMasUno(fechaX));
			}
			//myDato.vuelos = getVuelosPorFecha(fechaX,getFechaMasUno(fechaX)); //comentado por cambio en BD
			myDato.vuelos = leeVuelos(fechaX);
			datosHistoricos.add(myDato);	//guardo los datos de esa fecha	

			
//			System.out.println("Se supone imprime >"+datosHistoricos.get(datosHistoricos.size()-1).almacenes.get(3).cantidadLleno);
//			imprimeAlmacenes();
//			imprimeDatosHistoricos();
			fechaX = getFechaMasUno(fechaX); //ahora aumento en 1 mi contador.
		}	
		System.out.println("Se recopilo datosHistoricos="+datosHistoricos.size());
	}
	
	
	/*metodo que simula*/
	public ArrayList<DatoSimulacion> simular(Date fecha,int diasMenos){
		System.out.println("entro a simulacion");
		ArrayList<DatoSimulacion> listaSim;
		//fecha = getFechaMasUno(getFechaMasUno(calculaFechaActual())); //SOLO PRUEBAS
		
		leeAlmacenes(); //lee los almacenes que existen, listaAlmacenes
		llenaDatosHistoricos(diasMenos); //llena los datos historicos, datosHistoricos
		
		//imprimeDatosHistoricos();
		
		llenaParabolas();
		listaSim = calculaDatosSimulados(fecha);
		
		//imprimeSimulacion(listaSim);
		
		System.out.println("sale de simulacion");
		return listaSim;		
	}
	
	public void imprimeDatosHistoricos(){
		System.out.println("DATOS HISTORICOS");
		System.out.println("===================================");
		for(int i =0;i<datosHistoricos.size();i++){
			System.out.println("->Fecha:"+datosHistoricos.get(i).fecha.toString());
			for(int j=0;j<datosHistoricos.get(i).almacenes.size();j++){
				System.out.println("------>Almacen id=<"+datosHistoricos.get(i).almacenes.get(j).id+">,lleno=<"+datosHistoricos.get(i).almacenes.get(j).cantidadLleno+">");
			}
		}
	}

	public void imprimeSimulacion(ArrayList<DatoSimulacion> listaSim){
		System.out.println("DATOS SIMULADOS");
		System.out.println("===================================");
		for(int i =0;i<listaSim.size();i++){
			System.out.println("->Fecha:"+listaSim.get(i).fecha.toString());
			for(int j=0;j<listaSim.get(i).almacenes.size();j++){
				System.out.println("------>Almacen id=<"+listaSim.get(i).almacenes.get(j).id+">,lleno=<"+listaSim.get(i).almacenes.get(j).cantidadLleno+">");
			}
		}
	}
	
	public void imprimeAlmacenes(){
		System.out.println("MIS ALMACENES");
		System.out.println("===================================");
		for(int i =0;i<misAlmacenes.size();i++){
			System.out.println("Almacen N="+misAlmacenes.get(i).id+" ,lleno="+misAlmacenes.get(i).cantidadLleno);
		}
	}
	
}
