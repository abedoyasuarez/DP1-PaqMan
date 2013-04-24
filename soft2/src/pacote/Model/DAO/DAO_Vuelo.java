package pacote.Model.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.PedidoX;
import pacote.Model.Bean.Vuelo;

public class DAO_Vuelo extends ConnectBD{
	
	public Vuelo pedirVuelo(int vuelo_id) throws SQLException{
		Vuelo vuelo = new Vuelo();
		String sql = "SELECT vuelo_id, vuelo_hora_partida, almacen_partida FROM Vuelo WHERE vuelo_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, vuelo_id);
	    pst.execute();
	    
	    ResultSet rs = pst.getResultSet();
	    if(rs.next()){
	    	vuelo.id = rs.getInt(1);
	    	vuelo.hora_inicio = rs.getTimestamp(2);
	    	vuelo.ciudad_ini = rs.getInt(3);
	    }
		return vuelo;
	}
	
	public void darBajaVuelo(int vuelo_id) throws SQLException{
		String sql = "UPDATE Vuelo SET vuelo_estado = 3 WHERE vuelo_id = ?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, vuelo_id);
	    pst.execute();
	}
	
	public void cambiarCapacidad(int cant, int vuelo_id) throws SQLException{
		String sql = "UPDATE Vuelo SET vuelo_capacidad_actual = (vuelo_capacidad_actual - ?) WHERE vuelo_id = ? ";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, cant);
		pst.setInt(2, vuelo_id);
	    pst.execute();
	}
	
	public List<Vuelo> ListarVuelosCambio() throws SQLException{
		List<Vuelo> lista= new ArrayList<Vuelo>();
		String sql = "SELECT vuelo_id, vuelo_hora_partida, vuelo_hora_llegada, vuelo_estado FROM Vuelo WHERE vuelo_estado<2";
		
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.execute();
		ResultSet rs = pst.getResultSet();
		while(rs.next()){
			Vuelo vuel = new Vuelo();
			vuel.id = rs.getInt(1);
			vuel.hora_inicio = rs.getTimestamp(2);
			vuel.hora_fin = rs.getTimestamp(3);
			vuel.estado = rs.getInt(4);
			lista.add(vuel);
		}
		
		return lista;
	}
	
	public void actualizarVueloCambio(Vuelo vuelo) throws SQLException{
		String sql = "UPDATE Vuelo SET vuelo_estado = ? WHERE vuelo_id=?";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, vuelo.estado);
	    pst.setInt(2, vuelo.id);
	    pst.execute();
	}
	
	public Mensaje RegistrarVuelos(File archivo){
		Mensaje mensaje = new Mensaje();
		try{
			if(archivo!=null){
	            FileReader Fichero=new FileReader(archivo);
	            BufferedReader leer=new BufferedReader(Fichero);
	          /*  while((linea=leer.readLine())!=null){
	                  texto+= linea+"\n";
	              }
	            leer.close();
	            doc= new DefaultStyledDocument(estilos);
	            doc.insertString(0,texto,null);
	            this.setStyledDocument(doc);
	            archivo=Abrir.getPath();
	          */
			}
        }catch(Exception e){
			mensaje.me ="Error al Registrar vuelos";
		}
		return mensaje;		
	}
	
	public List<Vuelo> ListarVuelos(PedidoX pedido) throws SQLException {
		List <Vuelo> listaVuelos = new ArrayList<Vuelo>();	
		Statement s;
			//(movimiento_hora_entrada BETWEEN ? AND ?)
		
		
			String sql = 	" SELECT * FROM Vuelo WHERE vuelo_estado=1 AND " + 
							" ( vuelo_hora_partida BETWEEN ? AND ?) AND "+ 
							" ( vuelo_hora_llegada BETWEEN ? AND ?) ";
			PreparedStatement pst = conexion.conn.prepareStatement(sql);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String f1 = sdf.format(pedido.fecha_registro);
			String f2 = sdf.format(pedido.fecha_entrega_limite);
			
			pst.setString(1, f1);
			pst.setString(2, f2);
			pst.setString(3, f1);
			pst.setString(4, f2);
			
			pst.execute();
			ResultSet rs = pst.getResultSet();
			while(rs.next()){
	
				int vuelo_id = rs.getInt(1);
				int capacidad = rs.getInt(2);
				int capacidad_actual = rs.getInt(3);
				int ciudad_ini = rs.getInt(4);
				int ciudad_fin = rs.getInt(5);
				Date hora_inicio = rs.getTimestamp(6);
				Date hora_fin = rs.getTimestamp(7);
				int estado = rs.getInt(8);
				Vuelo vuelo = new Vuelo(vuelo_id, ciudad_ini, ciudad_fin, hora_inicio, hora_fin, capacidad, capacidad_actual, estado);
				listaVuelos.add(vuelo);
		}
		return listaVuelos;
	}

	public void insertarVuelo(Vuelo vuelo){
		Conexion conexion = new Conexion();
		String sql = 	"INSERT INTO Vuelo (vuelo_capacidad, vuelo_capacidad_actual, almacen_partida, almacen_llegada, vuelo_hora_partida, vuelo_hora_llegada, vuelo_estado) " +
						"VALUES (" + vuelo.capacidad+ "," + vuelo.capacidad_usada+ ","+vuelo.ciudad_ini+ ","+vuelo.ciudad_fin+","+vuelo.hora_inicio+","+vuelo.hora_fin+","+vuelo.estado+")";
		
		try{
			  Statement st = conexion.conn.createStatement();
			  st.executeUpdate(sql);
		}
		catch (SQLException s){
			System.out.println("Insertar Vuelo BD");
		}
	}
	
	public void actualizar_capacidad(Envio envio, Vuelo vuelo) throws SQLException{
		
		String sql = 	" UPDATE Vuelo " + 
						" SET vuelo_capacidad_actual = vuelo_capacidad_actual + " + envio.cantidad +
						" WHERE vuelo_id="+vuelo.id;
		
		Statement st = conexion.conn.createStatement();
		st.executeUpdate(sql);		
	}
}
