package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.Vuelo_Padre;
import pacote.Model.Bean.Response.AlmacenX;

public class DAO_Vuelo_Padre extends ConnectBD {
	
	public List<Vuelo_Padre> listaVPadres() throws SQLException{
		List<Vuelo_Padre> lista = new ArrayList<Vuelo_Padre>();
		String sql = "SELECT * FROM Vuelo_Padre";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.execute();
		
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			Vuelo_Padre vpadre = new Vuelo_Padre();
			vpadre.id = rs.getString(1);
			vpadre.almacen_partida = rs.getInt(2);
			vpadre.almacen_llegada = rs.getInt(3);
			//vpadre.hora_partida = rs.getTime(4);
			//vpadre.hora_llegada = rs.getTime(5);
			lista.add(vpadre);
		}
		rs.close();
		System.out.println("TAM Padre : " + lista.size());
		return lista;
	}
}