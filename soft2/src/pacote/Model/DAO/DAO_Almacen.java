package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pacote.Model.Bean.Almacen;
import pacote.Model.Bean.Continente;
import pacote.Model.Bean.Fech_Capac;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Pais;
import pacote.Model.Bean.ReportePais;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Response.AlmacenX;
import pacote.Model.Bean.Response.ListAlmacen;
import pacote.Model.Bean.Response.ListContinente;
import pacote.Model.Bean.Response.ListPais;

public class DAO_Almacen extends ConnectBD {
	//private Conexion conexion;
	
	public HashMap<String, Integer> mapeoAlmacen() throws SQLException{
		HashMap<String, Integer> mapeo = new HashMap<String, Integer>();
		String sql = "SELECT almacen_id, pais_code FROM Almacen ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.execute();
		
		ResultSet rs = pst.getResultSet();
		while(rs.next()){			
			int id = rs.getInt(1);
			String code = rs.getString(2);
			mapeo.put(code,id); 
		}
		System.out.println("tam map : " + mapeo.size() + mapeo.get("UMMS"));
		rs.close();
		
		return mapeo;
	}
	
	public List<AlmacenX> lAlmacen() throws SQLException {
		List<AlmacenX> lista = new ArrayList<AlmacenX>();
		String sql = "SELECT almacen_id, almacen_latitud, almacen_longitud FROM Almacen";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.execute();
		
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			AlmacenX alm = new AlmacenX();
			alm.id = rs.getInt(1);
			alm.latitud = rs.getInt(2);
			alm.longitud = rs.getInt(3);
			lista.add(alm);
		}
		rs.close();
		return lista;
	}
	
	

	public List<ReportePais> reporteAlmacenCapacidad(TimeCambio timeCambio) throws Exception{
		List<ReportePais> lista = new ArrayList<ReportePais>();

		String sql = 	" SELECT SUM(M.movimiento_cantidad), M.almacen_id, P.pais_nombre " +
						" FROM Movimiento M, Almacen A, Pais P " +
						" WHERE M.movimiento_estado=0 AND (movimiento_hora_entrada BETWEEN ? AND ?) AND M.almacen_id = A.almacen_id AND A.pais_id = P.pais_id" +
						" GROUP BY M.almacen_id";

		
		System.out.println("sql : " + sql);
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");

		Date todayWithZeroTime =formatter.parse(timeCambio.fecha);
		System.out.println("FECHAV : " + todayWithZeroTime);
		hoy.setTime(todayWithZeroTime);
		
		Date df1 = hoy.getTime();		
		hoy.add(Calendar.HOUR, 24);
		Date df2 = hoy.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String f1 = sdf.format(df1);
		System.out.println(f1);
		String f2 = sdf.format(df2);
		System.out.println(f2);		
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, f1);
		pst.setString(2, f2);
		pst.execute();
		
		ResultSet rs = pst.getResultSet();

		while(rs.next()){
			ReportePais reporte =  new ReportePais();
			reporte.id=rs.getInt(2);
			reporte.cantidad=rs.getInt(1);
			reporte.nombre=rs.getString(3);
			lista.add(reporte);
		}
		rs.close();
		return lista;
	}
	
	public List<ReportePais> reporteAlmacenCapacidadPais(int id, TimeCambio timeCambio) throws Exception{
		List<ReportePais> lista = new ArrayList<ReportePais>();
		
		String sql = 	" SELECT P.pais_rpt, SUM(M.movimiento_cantidad), A.almacen_capacidad, A.almacen_ciudad " +
						" FROM Movimiento M, Almacen A, Pais P " +
						" WHERE M.movimiento_estado=0 AND (movimiento_hora_entrada BETWEEN ? AND ?) AND M.almacen_id = A.almacen_id AND A.pais_id = P.pais_id AND P.pais_id = ? " +
						" GROUP BY M.almacen_id";


		System.out.println("sql : " + sql);
		System.out.println("ID : " + id);
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");

		Date todayWithZeroTime =formatter.parse(timeCambio.fecha);
		System.out.println("FECHAV : " + todayWithZeroTime);
		hoy.setTime(todayWithZeroTime);
		
		Date df1 = hoy.getTime();		
		hoy.add(Calendar.HOUR, 24);
		Date df2 = hoy.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String f1 = sdf.format(df1);
		System.out.println(f1);
		String f2 = sdf.format(df2);
		System.out.println(f2);		
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, f1);
		pst.setString(2, f2);
		pst.setInt(3, id);		
		pst.execute();
		
		ResultSet rs = pst.getResultSet();

		while(rs.next()){
			ReportePais reporte =  new ReportePais();
			reporte.code=rs.getString(1);
			System.out.println("CODE : " + reporte.code);
			reporte.cantidad=rs.getInt(2);
			reporte.capacidad=rs.getInt(3);
			reporte.ciudad=rs.getString(4);
			lista.add(reporte);
		}
		rs.close();
		return lista;
	}

	
	public int  insertarAlmacen(Almacen almacen) throws SQLException{
		int id = 0;
		
		String 	sql;
		sql = 	" INSERT INTO Almacen( almacen_ciudad, almacen_capacidad, almacen_estado, almacen_latitud, almacen_longitud, pais_id, continente_id ) " +
				" VALUES ('"+almacen.ciudad+"'," +almacen.capacidad +","+almacen.estado+","+almacen.latitud+","+almacen.longitud+","+almacen.pais_id+","+almacen.continente_id+")";

		System.out.println("sql : " + sql);
		Statement st = conexion.conn.createStatement();
		st.executeUpdate(sql);
		
		sql = "SELECT LAST_INSERT_ID()";
		System.out.println("sql LastId: " + sql);				
		st.executeQuery(sql);
		ResultSet rs = st.getResultSet();
		
		if(rs.next()){
			id = rs.getInt(1);
		}
		return id;
	}
	
	/**EMF-FALTA
	 * @throws SQLException **/
	public void actualizarAlmacen(Almacen almacen) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		String 	sql;
		sql = 	" UPDATE  Almacen " +
				" SET almacen_ciudad= '"+almacen.ciudad+"',  almacen_capacidad = "+almacen.capacidad+ ", almacen_latitud = "+ almacen.latitud+", almacen_longitud = "+ almacen.longitud +", pais_id = "+ almacen.pais_id +", continente_id = " + almacen.continente_id+ " " +  
				" WHERE almacen_id = " + almacen.id;
		System.out.println(sql);
		Statement st = conexion.conn.createStatement();
		st.executeUpdate(sql);
	}
	
	public void eliminarAlmacen(Almacen almacen) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		String 	sql;
		sql = 	" UPDATE  Almacen " +
				" SET almacen_estado= 2 " +  
				" WHERE almacen_id = ? ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);		
		pst.setInt(1, almacen.id);		
		pst.execute();
	}

	
	public ListAlmacen ListarAlmacenes( int alm_id ) throws SQLException{
		ListAlmacen rpta = new ListAlmacen();
		
		String sql = "SELECT A.almacen_id, A.almacen_ciudad, A.almacen_capacidad, P.pais_nombre, C.continente_nombre FROM Almacen A, Pais P, Continente C WHERE ";
		if(alm_id!=-1){
			sql+=" A.almacen_id = ? AND ";
		}
		sql += " A.almacen_estado = 1 AND A.pais_id = P.pais_id AND A.continente_id = C.continente_id";

		System.out.println("sql : " + sql);

		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		if(alm_id!=-1){
			pst.setInt(1, alm_id);
		}
		pst.execute();
	    ResultSet rs = pst.getResultSet();

		while(rs.next()){
			Almacen almacen =  new Almacen();
			almacen.id=rs.getInt(1);
			almacen.ciudad=rs.getString(2);
			almacen.capacidad=rs.getInt(3);			
			almacen.pais_nombre=rs.getString(4);
			almacen.continente_nombre=rs.getString(5);
			rpta.listaAlmacenes.add(almacen);
		}
		rs.close();
		return rpta;
	}
	
	
	public ListAlmacen ListarAlmacenesActivos() /*throws Exception*/{
		ListAlmacen rpta = new ListAlmacen();
		rpta.listaAlmacenes =  new ArrayList<Almacen>();
		System.out.println("createStatement");
		Statement s = null;
		ResultSet rs = null;
		try{
			s = conexion.conn.createStatement();
		
			System.out.println("desue createStatement");
			s.executeQuery("SELECT almacen_id, almacen_ciudad, almacen_capacidad, almacen_estado, almacen_latitud, almacen_longitud, pais_id, continente_id FROM Almacen WHERE almacen_estado=1");
			rs = s.getResultSet();
		
		}catch(Exception e){
			rpta.me="Error al listar Almacenes";
			System.out.println("Exception : " + e.toString() + "/" + e.getClass());
			return rpta;
		}
		
		try{
			while(rs.next()){
				int id=rs.getInt(1);
				String ciudad=rs.getString(2);
				int capacidad=rs.getInt(3);	
				int estado=rs.getInt(4);
				float latitud=rs.getFloat(5);
				float longitud=rs.getFloat(6);
				int pais_id=rs.getInt(7);
				int continente_id=rs.getInt(8);
				
				Almacen almacen = new Almacen(id, ciudad, capacidad, estado, latitud, longitud, pais_id, continente_id);
				rpta.listaAlmacenes.add(almacen);
			}
		}catch(Exception e){
			System.out.println("  TOTAL :" + e.toString());
			rpta.me="Error a listar almacenes";
		}
		return rpta;
	}
	
	public int capacidad_almacen(int almacen_id) throws SQLException {
		int capac = 0;
		Statement s = conexion.conn.createStatement();
		s.executeQuery("SELECT almacen_capacidad FROM Almacen WHERE almacen_id = " + almacen_id);
		ResultSet rs = s.getResultSet();
		if(rs.next()){
			capac = rs.getInt(1);
		}
		return capac;
	}
	
	public List<Fech_Capac> consultar_movimientos_rango(int almacen_id, Date fech_ini, Date fech_fin) throws SQLException {
		List<Fech_Capac> list_fech_capac =  new ArrayList<Fech_Capac>();
		Statement s;

		s = conexion.conn.createStatement();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String f1 = sdf.format(fech_ini);
		String f2 = sdf.format(fech_fin);
		
		String sql = 	" SELECT almacen_id, movimiento_cantidad, movimiento_id, movimiento_hora_entrada, movimiento_hora_salida " +
						" FROM Movimiento " + 
						" WHERE almacen_id = "+ almacen_id +" AND ((('"+f1+"' BETWEEN movimiento_hora_entrada AND movimiento_hora_salida ) " +
						" AND	('"+f2+"' BETWEEN movimiento_hora_entrada AND movimiento_hora_salida )) " +
						" OR 	((movimiento_hora_entrada BETWEEN '"+f1+"' AND '"+f2+"' ) " + 
						" AND	( movimiento_hora_salida BETWEEN '"+f1+"' AND '"+f2+"' )))" ;
		//System.out.println(sql);			
		s.executeQuery(sql);
		ResultSet rs = s.getResultSet();
		
		while(rs.next()){
			int alm = rs.getInt(1);
			int cant = rs.getInt(2);
			int mov_id = rs.getInt(3);
			Date f_ini = rs.getTimestamp(4);
			Date f_fin = rs.getTimestamp(5);
			Fech_Capac fech_capac = new Fech_Capac(alm, cant, mov_id, f_ini, f_fin);
			list_fech_capac.add(fech_capac);
		}
		
		return list_fech_capac;
	}

	public ListContinente ListarContinentes() {
		ListContinente rpta = new ListContinente();

		ResultSet rs;
		try{
			Statement s = conexion.conn.createStatement();
			s.executeQuery("SELECT * FROM Continente");
			rs = s.getResultSet();
		
			while(rs.next()){
				Continente miContinente = new Continente();
				miContinente.id = rs.getInt(1);
				miContinente.nombre=rs.getString(2);
				
				rpta.listaContinente.add(miContinente);
			}
			rs.close();
		}catch(Exception e ){
			System.out.println("Exception " + e.toString());
			rpta.me ="Error al Listar Continentes";
		}
		return rpta;
	}

	public ListPais ListarPaises(int id_continente) {
		ListPais rpta = new ListPais();

		ResultSet rs;
		try{
			Statement s = conexion.conn.createStatement();
			
			if (id_continente == -1){
				s.executeQuery("SELECT * FROM Pais");
			}else{
				s.executeQuery("SELECT * FROM Pais WHERE continente_id = "
								+ id_continente	);
			}
			rs = s.getResultSet();
		
			while(rs.next()){
				Pais miPais = new Pais();
				miPais.id = rs.getInt(1);
				miPais.nombre=rs.getString(2);
				miPais.continente_id = rs.getInt(4);
				
				rpta.listaPais.add(miPais);
			}
			rs.close();

		}catch(Exception e ){
			System.out.println("Exception " + e.toString());
			rpta.me ="Error al Listar Paises";
		}
		return rpta;
	}
}
