package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import pacote.Model.Bean.Incidencia;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Response.ListIncidencia;

public class DAO_Incidencia extends ConnectBD{
	
	public int RegistrarIncidencia(Incidencia incidencia, TimeCambio timeCambio)  throws SQLException, ParseException{
		int id = 0;
		String 	sql;
		sql = 	" INSERT INTO Incidencia(usuario_id,incidencia_descripcion,incidencia_fecha_registro,incidencia_fecha_actualizacion,incidencia_estado,vuelo_id  ) " +
				" VALUES (?,?,?,?,?,?)";
		
		System.out.println("sql : " + sql);

		PreparedStatement pst = conexion.conn.prepareStatement(sql);		
		pst.setInt(1, incidencia.usuario_id);
		pst.setString(2, incidencia.incidencia_descripcion);
		pst.setString(3, timeCambio.fecha);
		pst.setString(4, timeCambio.fecha);
		pst.setInt(5, 1);
		pst.setInt(6, incidencia.vuelo_id);
		
		pst.execute();
		
		sql = "SELECT LAST_INSERT_ID()";
		System.out.println("sql LastId: " + sql);				
		pst.executeQuery(sql);
		ResultSet rs = pst.getResultSet();
		
		if(rs.next()){
			id = rs.getInt(1);
		}
		return id;
	}
	
	public void modificarIncidencia(Incidencia incidencia) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		Calendar hoy = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date now = hoy.getTime();
		String ff = sdf.format(now);
		
		String 	sql;
		sql = 	" UPDATE  Incidencia " +
				" SET incidencia_descripcion = ?, incidencia_fecha_actualizacion = ?" +  
				" WHERE incidencia_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		
		pst.setString(1, incidencia.incidencia_descripcion);
		System.out.println("Incidencia : " + incidencia.incidencia_descripcion);
		pst.setString(2, ff);
		pst.setInt(3, incidencia.incidencia_id);
		System.out.println("ID Incidencia : " + incidencia.incidencia_id);
		pst.execute();
	}
	
	public void eliminarIncidencia(Incidencia incidencia) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		String 	sql;
		sql = 	" UPDATE  Incidencia " +
				" SET incidencia_estado = 2 " +  
				" WHERE incidencia_id = ? ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, incidencia.incidencia_id);		
		pst.execute();
	}

	public ListIncidencia listarIncidencias(int id_usuario) throws SQLException {
		ListIncidencia rpta = new ListIncidencia();

		
		String sql;
		
		if ( id_usuario == -1){
			sql = "SELECT * FROM Incidencia WHERE incidencia_estado = 1";
		}else{
			sql = "SELECT * FROM Incidencia WHERE incidencia_estado = 1 AND incidencia_id= ?";
		}
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		
		if(id_usuario!=-1)pst.setInt(1, id_usuario);
		
		pst.execute();
		
		ResultSet rs = pst.getResultSet();
	
		while(rs.next()){
			Incidencia miIncidencia = new Incidencia();
			miIncidencia.incidencia_id= rs.getInt(1);
			miIncidencia.usuario_id=rs.getInt(2);
			miIncidencia.incidencia_descripcion = rs.getString(3);
			miIncidencia.incidencia_fecha_registro = rs.getDate(4);
			miIncidencia.incidencia_fecha_actualizacion = rs.getDate(5);
			miIncidencia.incidencia_estado = rs.getInt(6);
			rpta.ListaIncidencia.add(miIncidencia);
		}
		rs.close();
		
		return rpta;
	}
	
	
}
