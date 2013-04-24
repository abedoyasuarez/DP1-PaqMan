package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.TimeCambio;

public class DAO_Generico extends ConnectBD{
	public TimeCambio obtenerFecha() throws SQLException{
		
		TimeCambio mensaje = new TimeCambio();
		
		Date d1 = new Date();		
		String sql;
		
		sql = "SELECT SystemTime FROM Generico LIMIT 1";		
		Statement st1 = conexion.conn.createStatement();
		st1.executeQuery(sql);
		
		mensaje.me = "Error al Obtener Fecha";
		ResultSet rs = st1.getResultSet();
		if(rs.next()){
			mensaje.me = "";
			d1 = rs.getTimestamp(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			mensaje.fecha = sdf.format(d1);
		}
		return mensaje;
	}
	
	public void actualizarFecha(String fecha) throws SQLException{
		
		String sql;
		
		System.out.println(fecha);
		
		sql = "UPDATE Generico SET SystemTime = ? ";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.setString(1, fecha);
	    System.out.println("fecha : " + fecha);
	    pst.execute();
		
	}
}
