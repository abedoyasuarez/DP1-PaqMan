package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.SimulaAlmacen;
import pacote.Model.Bean.SimulaDataAlmacen;
import pacote.Model.Bean.SimulaDataVuelo;
import pacote.Model.Bean.SimulaDia;
import pacote.Model.Bean.SimulaVuelo;
import pacote.Model.Bean.Response.AlmacenX;
import pacote.Model.Bean.Response.VueloX;

public class DAO_Simula extends ConnectBD{
	
	public List<SimulaVuelo> dameListaSimulacionVuelo() throws SQLException{
		
		String sql = "SELECT padre_id, " +
		" (select almacen_ciudad from Almacen where almacen_id = A.almacen_partida), " +
		" (select almacen_ciudad from Almacen where almacen_id = almacen_llegada), " +
		" almacen_partida, almacen_llegada from Vuelo_Padre A";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		pst= conexion.conn.prepareStatement(sql);
 	    pst.execute();
		
 	    rs = pst.getResultSet();
		
 	    List<VueloX> listaVuelo = new ArrayList<VueloX>();
 	    
 	    while(rs.next()){
 		   VueloX vuelo = new VueloX();
 		   vuelo.padre_id = rs.getString(1);
 		   //vuelo.vuelo_id = Integer.parseInt(vuelo.padre_id);
 		   vuelo.almacenPartida = rs.getString(2);
 		   vuelo.almacenLlegada = rs.getString(3);
 		   vuelo.capacidad = 300; //preguntar de donde sacar esta capacidad
 		   vuelo.almacenPartidaID = rs.getInt(4);
 		   vuelo.almacenLlegadaID = rs.getInt(5);
 		   listaVuelo.add(vuelo);
 	    }
 	    
 	    rs.close();
 	    
 	    List<SimulaVuelo> lreturn = new ArrayList<SimulaVuelo>();
 	    
 	    sql = " SELECT high_priority padre_id, SUM(envio_cantidad) as cantidad_total, DATE_FORMAT(vuelo_mov_fecha,'%Y-%m-%d') as created_day " +  
 	    	  " FROM Vuelo_Mov " +
 			  " WHERE padre_id= ? " +
 			  " GROUP BY padre_id, created_day ";
 	    
            int listasize = listaVuelo.size();
 	    for(int i=0; i<listasize; i++){
 	    	VueloX vl = listaVuelo.get(i);
 	    	
 	    	System.out.println("Dame DATA Vuelo " + i + " : " + vl.padre_id);
 	    	
 	    	pst = conexion.conn.prepareStatement(sql);
 	    	
 	    	pst.setString(1, vl.padre_id);
 	    	pst.execute();
 	    	
 	    	SimulaVuelo simula = new SimulaVuelo();
 	    	
 	    	simula.padre_id = vl.padre_id;
 	    	simula.vuelo_id = vl.padre_id;
 	    	simula.almacen_partida = vl.almacenPartida;
 	    	simula.almacen_llegada = vl.almacenLlegada;
 	    	simula.vuelo_capacidad = vl.capacidad;
 	    	
 	    	rs = pst.getResultSet();
 	    	
 	    	List <SimulaDataVuelo> listaSimulaDataVuelo = new ArrayList<SimulaDataVuelo>();
 	    	
 	    	while(rs.next()){
 	    		SimulaDataVuelo simVuelo = new SimulaDataVuelo();
 	    		simVuelo.cantidadTotal = rs.getInt(2);
 	    		simVuelo.created_day = rs.getDate(3);
 	    		listaSimulaDataVuelo.add(simVuelo);
 	    	}
 	    	
 	    	simula.listaSimulaDataVuelo = listaSimulaDataVuelo;
 	    	lreturn.add(simula);
 	    	rs.close();
 	    }
 	    
 	    
 	    return lreturn;
	}
	
	
public List<SimulaVuelo> dameListaSimulacionVuelo2(int pais_inicio, int pais_fin) throws SQLException{
		
		String sql = "SELECT padre_id, " +
		" (select almacen_ciudad from Almacen where almacen_id = A.almacen_partida), " +
		" (select almacen_ciudad from Almacen where almacen_id = almacen_llegada), " +
		" almacen_partida, almacen_llegada from Vuelo_Padre A " +
		" WHERE almacen_partida = ? AND almacen_llegada = ? ";
		
		PreparedStatement pst;
		ResultSet rs = null;
		
		pst= conexion.conn.prepareStatement(sql);
		pst.setInt(1, pais_inicio);
		pst.setInt(2, pais_fin);
 	    pst.execute();
		
 	    rs = pst.getResultSet();
		
 	    List<VueloX> listaVuelo = new ArrayList<VueloX>();
 	    
 	    while(rs.next()){
 		   VueloX vuelo = new VueloX();
 		   vuelo.padre_id = rs.getString(1);
 		   //vuelo.vuelo_id = Integer.parseInt(vuelo.padre_id);
 		   vuelo.almacenPartida = rs.getString(2);
 		   vuelo.almacenLlegada = rs.getString(3);
 		   vuelo.capacidad = 300; //preguntar de donde sacar esta capacidad
 		   vuelo.almacenPartidaID = rs.getInt(4);
 		   vuelo.almacenLlegadaID = rs.getInt(5);
 		   listaVuelo.add(vuelo);
 	    }
 	    
 	    rs.close();
 	    
 	    System.out.println("Cantidad de vuelos a procesar: " + listaVuelo.size());
 	    
 	    List<SimulaVuelo> lreturn = new ArrayList<SimulaVuelo>();
 	    
 	    sql = " SELECT padre_id, SUM(envio_cantidad) as cantidad_total, DATE_FORMAT(vuelo_mov_fecha,'%Y-%m-%d') as created_day " +  
 	    	  " FROM Vuelo_Mov " +
 			  " WHERE padre_id= ? " +
 			  " GROUP BY padre_id, created_day ";
 	    
 	    for(int i=0; i<listaVuelo.size(); i++){
 	    	VueloX vl = listaVuelo.get(i);
 	    	
 	    	System.out.println("Dame DATA Vuelo " + i + " : " + vl.padre_id);
 	    	
 	    	pst = conexion.conn.prepareStatement(sql);
 	    	
 	    	pst.setString(1, vl.padre_id);
 	    	pst.execute();
 	    	
 	    	SimulaVuelo simula = new SimulaVuelo();
 	    	
 	    	simula.padre_id = vl.padre_id;
 	    	simula.vuelo_id = vl.padre_id;
 	    	simula.almacen_partida = vl.almacenPartida;
 	    	simula.almacen_llegada = vl.almacenLlegada;
 	    	simula.vuelo_capacidad = vl.capacidad;
 	    	
 	    	rs = pst.getResultSet();
 	    	
 	    	List <SimulaDataVuelo> listaSimulaDataVuelo = new ArrayList<SimulaDataVuelo>();
 	    	
 	    	while(rs.next()){
 	    		SimulaDataVuelo simVuelo = new SimulaDataVuelo();
 	    		simVuelo.cantidadTotal = rs.getInt(2);
 	    		simVuelo.created_day = rs.getDate(3);
 	    		listaSimulaDataVuelo.add(simVuelo);
 	    	}
 	    	
 	    	simula.listaSimulaDataVuelo = listaSimulaDataVuelo;
 	    	lreturn.add(simula);
 	    	rs.close();
 	    }
 	    
 	    
 	    return lreturn;
	}
	
	
	
	public List<SimulaAlmacen> dameListaSimulacionAlmacen() throws SQLException{
		
		String sql = "SELECT almacen_id, almacen_latitud, almacen_longitud, almacen_capacidad, almacen_ciudad FROM Almacen WHERE almacen_estado=1";
		PreparedStatement pst;
		ResultSet rs = null;
		
		pst= conexion.conn.prepareStatement(sql);
 	    pst.execute();
		
 	    rs = pst.getResultSet();

		List<AlmacenX> listaAlmacen = new ArrayList<AlmacenX>();
		while(rs.next()){
			AlmacenX almacen = new AlmacenX();
			almacen.id = rs.getInt(1);
			System.out.println("ID " + almacen.id );
			almacen.latitud = rs.getFloat(2);
			almacen.longitud = rs.getFloat(3);
			almacen.capacidad = rs.getInt(4);
			almacen.nombre = rs.getString(5);
			listaAlmacen.add(almacen);
		}
 	    rs.close();

		List<SimulaAlmacen> lreturn = new ArrayList<SimulaAlmacen>();

		sql = 	" SELECT almacen_id, SUM(movimiento_cantidad) as cantidad_total, DATE_FORMAT(movimiento_hora_entrada,'%Y-%m-%d') as created_day " +
						" FROM Movimiento " +
						" WHERE almacen_id=? " +
						" GROUP BY almacen_id, created_day ";
		
		
	    for(int i=0; i<listaAlmacen.size(); i++){
	    	AlmacenX alm = listaAlmacen.get(i);
	    	
	    	System.out.println("Dame DATA ALMACEN : " + alm.id);
	    	pst= conexion.conn.prepareStatement(sql);
	    	pst.setInt(1, alm.id);
	 	    pst.execute();

	 	    SimulaAlmacen simula = new SimulaAlmacen();	

			simula.almacen_id = alm.id;
			simula.almacen_ciudad = alm.nombre;
			simula.almacen_latitud = alm.latitud;
			simula.almacen_longitud = alm.longitud;
			simula.almacen_capacidad = alm.capacidad;
			
			rs = pst.getResultSet();

			List<SimulaDataAlmacen> listaSimulaDataAlmacen = new ArrayList<SimulaDataAlmacen>();
			while(rs.next()){
				SimulaDataAlmacen simAlm =  new SimulaDataAlmacen();
				simAlm.cantidadTotal = rs.getInt(2);
				simAlm.created_day = rs.getDate(3);			
				listaSimulaDataAlmacen.add(simAlm);
			}
			simula.listaSimulaDataAlmacen = listaSimulaDataAlmacen; 
			lreturn.add(simula);
	    }

		rs.close();
		return lreturn;
	}
	
public List<SimulaDia> dameListaSimulacionDia() throws SQLException{
		
		String sql = "SELECT fecha,paquetes_t1, paquetes_t2, paquetes_t3, pedidos_totales FROM Dato_Historico ";
		PreparedStatement pst;
		ResultSet rs = null;
		
		pst= conexion.conn.prepareStatement(sql);
 	    pst.execute();
		
 	    rs = pst.getResultSet();

		List<SimulaDia> listaDia = new ArrayList<SimulaDia>();
		while(rs.next()){
			SimulaDia dia = new SimulaDia();
			dia.fecha=rs.getDate(1);
			System.out.println("DIA " + dia.fecha );
			dia.paquetes_T1=rs.getInt(2);
			dia.paquetes_T2=rs.getInt(3);
			dia.paquetes_T3=rs.getInt(4);
			dia.pedidos=rs.getInt(5);
			listaDia.add(dia);		
		}
		
		rs.close();
		return listaDia;
	}
}
