package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.PedidoX;

public class DAO_Envio extends ConnectBD{	
	
	public PedidoX buscarPedidoDeEnvio(Envio env) throws SQLException{
		PedidoX pedido = new PedidoX();
		String sql = 	" SELECT P.pedido_id, P.almacen_id_entrega, P.pedido_fecha_recojo , P.pedido_fecha_entrega_limite, E.envio_cantidad " +
					 	" FROM Envio E, Pedido P " +
					 	" WHERE E.envio_id=? AND E.pedido_id=P.pedido_id ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, env.id);
		pst.execute();
		ResultSet rs = pst.getResultSet();
		
		pedido.id=0;
		if(rs.next()){
			pedido.id = rs.getInt(1);
			pedido.almacen_entrega = rs.getInt(2);
			pedido.fecha_recojo = rs.getTimestamp(3);
			pedido.fecha_entrega_limite = rs.getTimestamp(4);
			pedido.cantidad = rs.getInt(5);
		}
		return pedido;
	}
	
	public void eliminarEnvio(Envio env) throws SQLException{
		String sql = "UPDATE Envio SET envio_estado = 2 WHERE envio_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, env.id);
		pst.execute();
	}
	
	public List<Envio> listarEnvios(PedidoX pedido) throws SQLException{
		List<Envio> listaEnvio = new ArrayList<Envio>();
		String sql;
		sql= "SELECT envio_id FROM Envio WHERE pedido_id = "+pedido.id;
		
		Statement st1 = conexion.conn.createStatement();
		st1.executeQuery(sql);
		ResultSet rs = st1.getResultSet();
		
		while(rs.next()){
			Envio envio = new Envio();
			envio.id = rs.getInt(1);
			listaEnvio.add(envio);
		}
		System.out.println("TAM LISTA ENVIO : "+listaEnvio.size());
		return listaEnvio;
	}
	
	public int registrarEnvio(PedidoX pedido, int cant) throws SQLException{
		int id=0;
		

		PreparedStatement pst;
		String sql = "SELECT envio_id FROM Envio ORDER BY envio_id DESC LIMIT 1";
		pst = conexion.conn.prepareStatement(sql);
		pst.execute();
		
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			id = rs.getInt(1);
		}
		id++;
		//if(id==0) return id;
		
		sql = 	" INSERT INTO Envio(envio_id, pedido_id, envio_cantidad, envio_estado) " +
				" VALUES ( ? , ? , ? , 1) ";
				//"VALUES (" + pedido.id + "," + cant + ", 1)";
//		System.out.println("sql : " + sql);
		
		pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1,id);
		pst.setInt(2,pedido.id);
		pst.setInt(3,cant);
//		System.out.println("antes");
		pst.execute();
//		System.out.println("despues");
//		sql = 	" SELECT last_insert_id()";
//		Statement st1 = conexion.conn.createStatement();
//		st1.executeQuery(sql);
//		rs = st1.getResultSet();
//		if(rs.next()){
//			id = rs.getInt(1);
//			return id;					
//		}
		return id;
	}
}
