package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.MesReporte;
import pacote.Model.Bean.Pago;
import pacote.Model.Bean.Pedido;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.PedidoY;
import pacote.Model.Bean.ReportePais;
import pacote.Model.Bean.TimeCambio;
import pacote.Model.Bean.Response.ListPedido;

public class DAO_Pedido extends ConnectBD {
	public DAO_Pedido(){}
	
	public List<PedidoX> listarPedido(String fecha) throws SQLException{
		List<PedidoX> listaPed =  new ArrayList<PedidoX>();
		String sql = "SELECT * FROM Nuevo_Pedido WHERE pedido_estado=0 AND pedido_fecha_registro<?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, fecha);
	    pst.execute();
	    
	    ResultSet rs = pst.getResultSet();
	    while(rs.next()){
	    	PedidoX ped = new PedidoX();
	    	ped.id = rs.getInt(1);
	    	ped.almacen_partida = rs.getInt(2);
	    	ped.almacen_entrega = rs.getInt(3);
	    	ped.fecha_registro = rs.getTimestamp(4);
	    	listaPed.add(ped);
	    }
	    rs.close();
	    
	    sql = "UPDATE Nuevo_Pedido SET pedido_estado=1 WHERE pedido_estado=0 AND pedido_fecha_registro<?";
	    pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, fecha);
	    pst.execute();
	    
		return listaPed;
	}
	
	public void actualizarPedidoEstado(PedidoX pedido) throws SQLException{
		String 	sql;
		
		sql = 	"UPDATE Pedido SET pedido_estado =? WHERE pedido_id = ? ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, pedido.estado);		
		pst.setInt(2, pedido.id);
	    pst.execute();		
	}
	
	
	public List<MesReporte>reporteMes(int mes) throws SQLException{
		List<MesReporte> lista = new ArrayList<MesReporte>();
		
		String sql = 	" SELECT DATE_FORMAT(pedido_fecha_registro, '%m') as mes, SUM(pedido_cantidad) " +
						" FROM Pedido " + 
						" WHERE DATE_FORMAT(pedido_fecha_registro, '%Y')=? " +
						" GROUP BY mes";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1,mes);
		pst.execute();

		ResultSet rs = pst.getResultSet();

		while(rs.next()){
			MesReporte reporte =  new MesReporte();
			reporte.mes=rs.getInt(1);
			reporte.cantidad=rs.getInt(2);			
			lista.add(reporte);
		}
		rs.close();
		return lista;
	}
	
	public int registrarPedido(Pedido pedido) throws SQLException{
		int est = 0;
		
		String sql;
			
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String f1 = sdf.format(pedido.fecha_registro);
		String f2 = sdf.format(pedido.fecha_entrega);
		//sql = 	" INSERT INTO Pedido(pedido_cantidad, almacen_id_partida, almacen_id_entrega, pedido_fecha_registro, pedido_fecha_entrega, pedido_estado) " +
		//		" VALUES (" + pedido.cantidad + "," + pedido.almacen_partida + ","+pedido.almacen_entrega+",'"+ f1+"','"+f2+"','"+pedido.estado+"')";
		sql = 	" INSERT INTO Pedido(pedido_cantidad, almacen_id_partida, almacen_id_entrega, pedido_fecha_registro, pedido_fecha_entrega, pedido_estado, pedido_code_ident ) " +
				" VALUES (? , ?, ? , ?, ? ,?, ?)";
		//System.out.println("SQL : " + sql);

		//Statement st = conexion.conn.createStatement();
		//st.executeUpdate(sql);
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1,pedido.cantidad);
		pst.setInt(2,pedido.almacen_partida);
		pst.setInt(3,pedido.almacen_entrega);
		pst.setString(4,f1);
		pst.setString(5,f2);
		pst.setInt(6,pedido.estado);
		pst.setString(7,"xD");
		pst.execute();
		
		sql = 	" SELECT last_insert_id()";				
		pst.executeQuery(sql);
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			est = rs.getInt(1);
		}

		return est;
	}

	public int insertarPedido(PedidoX pedido, TimeCambio timeCambio) throws SQLException, ParseException{
		int id=0;
		PreparedStatement pst;
		String sql = "SELECT pedido_id FROM Pedido ORDER BY pedido_id DESC LIMIT 1";
		pst = conexion.conn.prepareStatement(sql);
		pst.execute();

		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			id = rs.getInt(1);
		}
		id++;
		//System.out.println("ID : " + id);
		
		//System.out.println("ANTES DE INSERTAR PEDIDO");
		String 	f1="",f2= "",f3="";
		Calendar hoy = Calendar.getInstance();
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
		
		//Date today = new Date();
		//Date todayWithZeroTime =formatter.parse(formatter.format(today));
		Date todayWithZeroTime =formatter.parse(timeCambio.fecha);
		//System.out.println("FECHAV : " + todayWithZeroTime);
		hoy.setTime(todayWithZeroTime);
		
		pedido.fecha_registro = hoy.getTime();
		if(pedido.viaje==0){//EN EL MISMO CONTINENTE
			hoy.add(Calendar.HOUR, 24);
			System.out.println("12");
		}else{//DIFERENTES CONTINENTES
			hoy.add(Calendar.HOUR, 48);
			System.out.println("24");
		}
		pedido.fecha_recojo = hoy.getTime();
		pedido.fecha_entrega_limite = hoy.getTime();
		
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			f1 = sdf.format(pedido.fecha_registro);
//			System.out.println(f1);
			f2 = sdf.format(pedido.fecha_recojo);
//			System.out.println(f2);
			f3 = sdf.format(pedido.fecha_entrega_limite);
//			System.out.println(f3);
		}catch(Exception e){
			System.out.println(e.toString());
		}
		
		String code = "No Ruta";
		
		//System.out.println("ENVIA : " + pedido.cliente_envia);
		sql = 	" INSERT INTO Pedido(pedido_cantidad, almacen_id_partida, almacen_id_entrega, pedido_fecha_registro, pedido_fecha_recojo, pedido_fecha_entrega_limite, pedido_code, pedido_viaje, pedido_estado, cliente_envia_id, receptor_nombre, receptor_apellidos, receptor_documento_id, receptor_numero_documento, receptor_telefono, pedido_id)" +
				" VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		//System.out.println("sql : " + sql);
		
		pst = conexion.conn.prepareStatement(sql);
		
		pst.setInt(1, pedido.cantidad);
		pst.setInt(2, pedido.almacen_partida);
		pst.setInt(3, 	pedido.almacen_entrega);
		pst.setString(4, f1);
		pst.setString(5, f2);
		pst.setString(6, f3);
		pst.setString(7, code);
		pst.setInt(8, pedido.viaje);
		pst.setInt(9, pedido.estado);
		pst.setInt(10, pedido.cliente_envia);
		pst.setString(11, pedido.receptor_nombre);
		pst.setString(12, pedido.receptor_apellidos);
		pst.setInt(13, pedido.receptor_documento_id);
		pst.setString(14, pedido.receptor_numero_documento);
		pst.setString(15, pedido.receptor_telefono);
		pst.setInt(16, id);
	    pst.execute();		

		return id;
	}
	
	public void actualizarPedido(PedidoX pedido) throws SQLException{
		String 	sql;
		
		sql = 	"UPDATE Pedido SET pedido_estado =?, pedido_code = ? WHERE pedido_id = ? ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, pedido.estado);
		pst.setString(2, pedido.code);
		pst.setInt(3, pedido.id);
	    pst.execute();		
	}
	
	public void entregaPedido(PedidoX pedido) throws SQLException{
		String 	sql;
		PreparedStatement pst;
		sql = 	"UPDATE Pedido SET pedido_estado = 5 WHERE pedido_id = ? ";//ENTREGADO		
		pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, pedido.id);
	    pst.execute();
	    
	    sql = 	"UPDATE Movimiento SET movimiento_estado = 4 WHERE pedido_id = ? ";//ENTREGADO		
		pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, pedido.id);
	    pst.execute();
	}
	
	public int esCode(PedidoX pedido) throws SQLException{
		int ret=0;
		String sql = "SELECT pedido_id FROM Pedido WHERE pedido_code = ? "; //AND pedido_estado = 2";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, pedido.code);
		pst.execute();		
		ResultSet rs = pst.getResultSet();
		if(rs.next()){
			ret = rs.getInt(1);
		}
		return ret;
	}
	
	public List<PedidoX> listarTodoPedidos() throws SQLException{
		List<PedidoX> lreturn = new ArrayList<PedidoX>();
		//1 : no se hallo ruta
		//2 : registrado
		//3 : en viaje
		//4 : llego a destino
		//5 : entregado
		String sql = "SELECT pedido_id, almacen_id_partida, almacen_id_entrega, pedido_estado FROM Pedido WHERE pedido_estado<5 AND pedido_estado>1";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.execute();
		
	    ResultSet rs = pst.getResultSet();
	    while(rs.next()){
			PedidoX pedido = new PedidoX();
			pedido.id = rs.getInt(1);
			pedido.almacen_partida = rs.getInt(2);
			pedido.almacen_entrega = rs.getInt(3);
			pedido.estado = rs.getInt(4);
			lreturn.add(pedido);
		}
		return lreturn;
	}
	
	public ListPedido ListarPedidos(int id_usuario) throws SQLException {
		ListPedido rpta = new ListPedido();
		rpta.listaPedidos =  new ArrayList<PedidoY>();
		
		String sql = "	SELECT P.pedido_id,P.pedido_cantidad,A1.almacen_ciudad,"
					+"	A2.almacen_ciudad,P.pedido_fecha_registro,P.pedido_fecha_recojo"
					+"	,P.pedido_estado,P.receptor_nombre,P.receptor_apellidos, P.pedido_code" 
					+"  FROM Pedido P,Almacen A1,Almacen A2 "
					+"	WHERE  A1.almacen_id=P.almacen_id_partida AND  A2.almacen_id=P.almacen_id_entrega AND P.pedido_estado>1";
		if (id_usuario!=-1){
			sql += 	" 	and cliente_envia_id = ? ";
		}
		sql += " ORDER BY pedido_id DESC LIMIT 14";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		if (id_usuario!=-1){
			pst.setInt(1, id_usuario);
		}
	    pst.execute();
		
	    ResultSet rs = pst.getResultSet();
		//System.out.println("sql en listar Pedido : " +  sql);
		
		while(rs.next()){
			PedidoY pedido = new PedidoY();
			pedido.id = rs.getInt(1);
			pedido.cantidad = rs.getInt(2);
			pedido.ciudadPartida = rs.getString(3);
			pedido.ciudadLLegada = rs.getString(4);
			pedido.fechaSalida = rs.getDate(5);
			pedido.fechaLLegada = rs.getDate(6);
			pedido.estado = rs.getInt(7);			
			switch(pedido.estado){
				case 1: pedido.estado_mensaje = "No se hallo ruta"; break; 
				case 2: pedido.estado_mensaje = "Registrado"; break;
				case 3: pedido.estado_mensaje = "En viaje"; break;
				case 4: pedido.estado_mensaje = "Llego a destino"; break;
				case 5: pedido.estado_mensaje = "Entregado"; break;
			}
			String nombre_receptor = rs.getString(8);
			String apellido_receptor = rs.getString(9);
			pedido.code = rs.getString(10);
			pedido.clienteRecibe = nombre_receptor + " " + apellido_receptor;
			rpta.listaPedidos.add(pedido);
		}
		return rpta;
	}

	public PedidoX buscarPedido(String pedido_code) throws SQLException{
		PedidoX pedido = new PedidoX();
		pedido.id = 0;
		String sql = "SELECT * FROM Pedido WHERE pedido_code = ?";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, pedido_code);		
		pst.execute();
		ResultSet rs =  pst.getResultSet();
		if(rs.next()){
			pedido.id = rs.getInt(1);
			pedido.cantidad = rs.getInt(2);
			pedido.almacen_partida = rs.getInt(3);
			pedido.almacen_entrega = rs.getInt(4);	
			pedido.fecha_registro = rs.getDate(5);
			pedido.fecha_recojo = rs.getDate(6);
			pedido.fecha_entrega_limite = rs.getDate(7);
			pedido.code = rs.getString(8);
			pedido.viaje = rs.getInt(9);
			pedido.estado = rs.getInt(10);
			pedido.cliente_envia = rs.getInt(11);
			pedido.receptor_nombre = rs.getString(12);
			pedido.receptor_apellidos = rs.getString(13);
			pedido.receptor_documento_id = rs.getInt(14);
			pedido.receptor_numero_documento = rs.getString(15);
			pedido.receptor_telefono = rs.getString(16);
			switch(pedido.receptor_documento_id){
				case 0:		pedido.receptor_tipo_doc="DNI";break;
				case 1:		pedido.receptor_tipo_doc="Pasaporte";break;
				default:	pedido.receptor_tipo_doc="Error";break;
			}
			//System.out.println("Estado : " + pedido.estado);
			switch(pedido.estado){
				case 1:pedido.me="Lo sentimos el pedido no existe"; break;
				case 2:pedido.me="Lo sentimos el pedido aún no puede ser recogido, acerquese el : " + pedido.fecha_entrega_limite + ", EN LUGAR DE ORIGEN"; break;
				case 3:pedido.me="Lo sentimos el pedido aún no puede ser recogido, acerquese el : " + pedido.fecha_entrega_limite + ", EN VIAJE"; break;
				case 4:pedido.me="";break;//Recibidobreak;
				case 5:pedido.me="Ya ha sido recogido";break;
				default: pedido.me="Error";break;
			}
		}else{
			pedido.me = "Dicho código no ha sido encontrado";
		}
	//	System.out.println("Mensaje : " + pedido.me);
		return pedido;
	}

	public int RegistrarPago(Pago pago) throws SQLException {

		int id = 0;
		String 	sql;
		sql = 	" INSERT INTO Pago(pago_total,pago_estado,pago_fecha_registro,pedido_id) " +
				" VALUES ("+pago.total +","+pago.estado+","+pago.fecha+","+pago.pedido_id+")";

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
	
	public void EliminarPago(Pago pago) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		String 	sql;
		sql = 	" UPDATE  Pago " +
				" SET pago_estado = 2" +  
				" WHERE pago_id = " + pago.id;
		Statement st = conexion.conn.createStatement();
		st.executeUpdate(sql);
		
	}

}
