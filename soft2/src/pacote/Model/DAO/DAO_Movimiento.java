package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Movimiento;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Response.AlmacenResponse;

public class DAO_Movimiento extends ConnectBD{
	
	public void actualizarMovimientoSQL(String sql) throws SQLException{
		PreparedStatement pst = conexion.conn.prepareStatement(sql);		
	    pst.execute();
	}
	
	public void eliminaMovEnvio(Envio env) throws SQLException{
		String sql = "UPDATE Movimiento SET movimiento_estado = 4 WHERE envio_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, env.id);
	    pst.execute();
	}
	
	public int numero_envios(PedidoX ped) throws SQLException{
		int res=0;
		String sql = "SELECT COUNT(*) FROM Envio WHERE pedido_id=?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, ped.id);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			res = rs.getInt(1);
		}
		return res;
	}
	
	public int numero_inicio(PedidoX ped) throws SQLException{
		int res=0;
		String sql = 	" SELECT COUNT(*), pedido_id FROM Movimiento " +
						" WHERE pedido_id=? AND almacen_id=? AND movimiento_estado=1";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, ped.id);
		pst.setInt(2, ped.almacen_partida);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			res = rs.getInt(1);
		}
		return res;
	}
	
	public int numero_fin(PedidoX ped) throws SQLException{
		int res=0;
		String sql = 	" SELECT COUNT(*), pedido_id FROM Movimiento " +
						" WHERE pedido_id=? AND almacen_id=? AND movimiento_estado=1";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, ped.id);
		pst.setInt(2, ped.almacen_entrega);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			res = rs.getInt(1);
		}
		return res;
	}
	
	public List<AlmacenResponse> devolverRuta(int id) throws SQLException{
		List<AlmacenResponse> listaResponse = new ArrayList<AlmacenResponse>();		
		PreparedStatement pst = conexion.conn.prepareStatement("SELECT A.almacen_latitud, A.almacen_longitud, M.movimiento_estado, M.movimiento_hora_entrada, M.movimiento_hora_salida, M.movimiento_cantidad, A.almacen_ciudad FROM Movimiento M, Almacen A WHERE M.envio_id = ? AND M.almacen_id =A.almacen_id");
		pst.setInt(1, id);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			float lat = rs.getFloat(1);
			float lng = rs.getFloat(2);
			int est = rs.getInt(3);
			Date hora_entrada = rs.getTimestamp(4);
			Date hora_salida = rs.getTimestamp(5);
			int cant = rs.getInt(6);
			String ciudad = rs.getString(7);
			String msje = "Cantidad : " + cant + ", Hora Entrada : " + hora_entrada + ", Hora Salida : " + hora_salida; 
			AlmacenResponse resp =  new AlmacenResponse(lat, lng, est, ciudad, msje); 
			listaResponse.add(resp);
		}
		return listaResponse;
	}
	
	public void insertar_Movimiento(Movimiento movimiento) throws SQLException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String f1 = sdf.format(movimiento.hora_entrada);
		String f2 = sdf.format(movimiento.hora_salida);
		String f3 = sdf.format(movimiento.pedido_fecha_entrega_limite);
		
		//System.out.println("CANTIDAD DE MOV : " + movimiento.cantidad );
		String sql = 	" INSERT INTO Movimiento(almacen_id, pedido_id, envio_id, movimiento_hora_entrada, movimiento_hora_salida, movimiento_cantidad, movimiento_estado, almacen_in, almacen_out, pedido_fecha_entrega_limite, pedido_almacen_entrega)" +
						" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
	
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.setInt(1, movimiento.almacen_id);
	    pst.setInt(2, movimiento.pedido_id);
	    pst.setInt(3, movimiento.envio);
	    pst.setString(4, f1);
	    pst.setString(5, f2);
	    pst.setInt(6, movimiento.cantidad);
	    pst.setInt(7, movimiento.estado);
	    pst.setInt(8, movimiento.almacen_in);
	    pst.setInt(9, movimiento.almacen_out);	    
	    pst.setString(10, f3);
	    pst.setInt(11, movimiento.pedido_almacen_entrega);
	    pst.execute();
	}
	
	public List<Movimiento> listarMovimiento() throws SQLException{
		List<Movimiento> lista = new ArrayList<Movimiento>();
		String sql = 	" SELECT M.almacen_id, M.pedido_id, M.movimiento_hora_entrada, M.movimiento_hora_salida, M.movimiento_estado, M.pedido_fecha_entrega_limite, M.pedido_almacen_entrega, M.movimiento_id, U.usuario_nombre, U.usuario_email, A.almacen_ciudad "+ 
						" FROM Movimiento M "+
						" INNER JOIN Pedido P ON M.pedido_id=P.pedido_id "+
						" INNER JOIN Usuario U ON P.cliente_envia_id=U.usuario_id "+
						" INNER JOIN Almacen A ON M.almacen_id = A.almacen_id "+
						" WHERE movimiento_estado < 2 ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			Movimiento mov = new Movimiento();
			mov.almacen_id = rs.getInt(1);
			mov.pedido_id = rs.getInt(2);
			mov.hora_entrada = rs.getTimestamp(3);
			mov.hora_salida = rs.getTimestamp(4);
			mov.estado = rs.getInt(5);
			mov.pedido_fecha_entrega_limite = rs.getTimestamp(6);
			mov.pedido_almacen_entrega = rs.getInt(7);
			mov.id = rs.getInt(8);
			mov.user_nombre = rs.getString(9);
			mov.user_correo = rs.getString(10);
			mov.almacen_ciudad = rs.getString(11);
			lista.add(mov);
		}
		System.out.println("size : " + lista.size());
		return lista;
	}
	
	public void actualizarMovimiento(Movimiento movimiento) throws SQLException{
		String sql = "UPDATE Movimiento SET movimiento_estado = ? WHERE movimiento_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, movimiento.estado);
	    pst.setInt(2, movimiento.id);
	    System.out.println(movimiento.estado + " " + movimiento.id);
	    pst.execute();
	}
}



