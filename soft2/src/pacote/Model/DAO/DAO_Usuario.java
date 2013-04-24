package pacote.Model.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.ListRol;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Rol;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListUsuario;
import pacote.Model.Bean.Response.LoginResponse;



public class DAO_Usuario extends ConnectBD{
	
	public ListRol listarRoles() throws SQLException{
		ListRol lista = new ListRol();
		PreparedStatement pst = conexion.conn.prepareStatement("SELECT rol_id, rol_nombre FROM Rol");
		pst.execute();
		ResultSet rs = pst.getResultSet();

		while(rs.next()){
			Rol rr = new Rol(); 
			rr.rol_id = rs.getInt(1);
			rr.rol_nombre = rs.getString(2);
			lista.lRol.add(rr);
		}
		return lista;
	}
	
	public String ultimoPedido(int id) throws SQLException{
		String res = "0";
		
		String sql = "SELECT pedido_code FROM Pedido WHERE pedido_estado>=2 AND cliente_envia_id=? ORDER BY 1 DESC ";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.setInt(1, id);
		
	    pst.execute();		
	    ResultSet rs = pst.getResultSet();
		
		if(rs.next()){
			res = rs.getString(1);
		}	    
		return res;
	}
	
	public LoginResponse loginUsuario(Usuario usuario) throws SQLException{
		LoginResponse login = new LoginResponse();

		String sql = 	" SELECT U.usuario_id, U.usuario_nombre " +
						" FROM Usuario U " + 
						" WHERE U.usuario_user = ? AND U.usuario_password = ? AND usuario_estado=1";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
	    pst.setString(1, usuario.user);
	    pst.setString(2, usuario.password);	    
	    pst.execute();
		
	    ResultSet rs = pst.getResultSet();
		
		if(rs.next()){
			 login.id = rs.getInt(1);
			 login.nombre = rs.getString(2);			 
		}else{
		    login.me = "Datos incorrectos, verifique su usuario y contrasenha";
                    
		}
		System.out.println(login.me);
		return login;
	}
	
	public Mensaje verficarCliente(String nombreUsuario) throws SQLException{
		Mensaje mensaje = new Mensaje();
		String sql;
		sql = " SELECT * FROM Usuario WHERE usuario_user = " + nombreUsuario+ " and usuario_estado=1"; 

		System.out.println("sql loginUsuario: " + sql);
		Statement st;
		st = conexion.conn.createStatement();
		st.executeQuery(sql);
		ResultSet rs = st.getResultSet();
		if(rs.next()){					
			mensaje.id = rs.getInt(1);
		}else{
			mensaje.me = "No existe dicho usuario";
		}
		return mensaje;
	}
	
	public Usuario insertarUsuario(Usuario usuario) throws SQLException{
	
		Usuario usu = new Usuario();		
		String 	sql;
		
		sql = 	" INSERT INTO Usuario(usuario_nombre, usuario_apellidos, usuario_sexo, usuario_email, usuario_user, usuario_estado,  usuario_password, usuario_telefono, usuario_direccion, pais_id, documento_id, numero_documento, rol_id, almacen_id) " +
				" VALUES (? , ?, ?, ?, ?, 1, ?, ?, ?, ?, 1, ?, ?,NULL)";		
		System.out.println("sql InsertarUsuario: " + sql);

		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setString(1, usuario.nombre );
	    pst.setString(2, usuario.apellidos );
	    pst.setInt(3, usuario.sexo);
	    pst.setString(4, usuario.email);
	    
	    if(usuario.email.equalsIgnoreCase("")){
	    	pst.setString(5, usuario.numero_documento);
	    	System.out.println("Registra Doc");
	    	usu.user = usuario.numero_documento;
	    }else{
	    	pst.setString(5, usuario.email);
	    	System.out.println("Registra Email");
	    	usu.user = usuario.email;
	    }
	    
	    pst.setString(6, usuario.password);
	    pst.setString(7, usuario.telefono );
	    pst.setString(8, usuario.direccion);
	    pst.setInt(9, usuario.pais_id);
	    pst.setString(10, usuario.numero_documento );
	    pst.setInt(11, usuario.rol_id);
		pst.execute();
		
		sql = 	" SELECT LAST_INSERT_ID() " ;
		System.out.println("sql InsertarUsuario: " + sql);				
		pst.executeQuery(sql);

		ResultSet rs = pst.getResultSet();
		
		if(rs.next()){
			usu.id = rs.getInt(1);
			System.out.println("ID : " + usu.id);
			usu = usuario;
		}
		return usu;
	}
	
	public ListUsuario buscarUsuario(Usuario usuario) throws SQLException{
		ListUsuario lista = new ListUsuario();		
		lista.me = "";
		
		//System.out.println("");
		String 	sql;
		sql = " SELECT * FROM Usuario WHERE usuario_estado = 1 ";
		if(usuario.id != -1){
			sql+= " AND usuario_id = " + usuario.id; 
			
		}				
		System.out.println("sql : " + sql);
		Statement st = conexion.conn.createStatement();			
		
		st.executeQuery(sql);
		ResultSet rs = st.getResultSet();
		
		List <Usuario> listaUsuario = new ArrayList <Usuario>();
		while(rs.next()){					
			int id = rs.getInt(1);
			String numero_documento = rs.getString(2);
			String user = rs.getString(3);
			String nombre = rs.getString(4);
			String apellidos = rs.getString(5);
			int sexo= rs.getInt(6);
			String email = rs.getString(7);			
			String password = rs.getString(8);
			String telefono = rs.getString(9);
			String direccion = rs.getString(10);
			int pais_id = rs.getInt(11);
			int documento_id = rs.getInt(12);								
			int rol_id = rs.getInt(13);
			int estado = rs.getInt(14);
			int almacen_id =rs.getInt(15);
			
			Usuario usser = new Usuario(id, nombre, apellidos, sexo, email, user, password, telefono, direccion, pais_id, documento_id, numero_documento, rol_id, estado, almacen_id);
			listaUsuario.add(usser);
		}
		lista.listaUser = listaUsuario;	

		System.out.println(lista.listaUser.size());
		return lista;
	}
	
	public Usuario buscarUsuarioX(Usuario usuario) throws SQLException {		
		Usuario usser = new Usuario();
		usser.id=0;
		//System.out.println("");
		String 	sql;
		sql = " SELECT * FROM Usuario WHERE usuario_estado = 1 AND usuario_user = '" + usuario.user+ "'"; 

		System.out.println("sql : " + sql);
		Statement st = conexion.conn.createStatement();			
		
		st.executeQuery(sql);
		ResultSet rs = st.getResultSet();
		

		if(rs.next()){
			System.out.println("SIIIIIIIIIII");
			int id = rs.getInt(1);
			System.out.println("id : " + id);
			String numero_documento = rs.getString(2);
			String user = rs.getString(3);
			String nombre = rs.getString(4);
			String apellidos = rs.getString(5);
			int sexo= rs.getInt(6);
			String email = rs.getString(7);			
			String password = rs.getString(8);
			String telefono = rs.getString(9);
			String direccion = rs.getString(10);
			int pais_id = rs.getInt(11);
			int documento_id = rs.getInt(12);								
			int rol_id = rs.getInt(13);
			int estado = rs.getInt(14);
			int almacen_id =rs.getInt(15);
			System.out.println("id : " + id);
			usser = new Usuario(id, nombre, apellidos, sexo, email, user, password, telefono, direccion, pais_id, documento_id, numero_documento, rol_id, estado, almacen_id);
			
		}
		System.out.println("Salio " + usser.id);
		return usser;
	}
	
	
	public Mensaje actualizarUsuario(Usuario usuario) throws SQLException{
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";

		String 	sql;
//		try{contr, domicil, telf y movil
			sql = 	" UPDATE  Usuario " +
					" SET usuario_nombre = '"+usuario.nombre+"', usuario_apellidos = '"+usuario.apellidos+"', usuario_sexo = "+usuario.sexo+", usuario_email = '"+usuario.email+"', usuario_password = '"+ usuario.password+ "', usuario_telefono = '"+usuario.telefono + "', usuario_direccion = '"+usuario.direccion+"', rol_id = " + usuario.rol_id + ", pais_id = " + usuario.pais_id + ", documento_id = "+usuario.documento_id+", numero_documento='" + usuario.numero_documento+"' " +  
					" WHERE usuario_id = " + usuario.id + " AND usuario_estado = 1" ;
			System.out.println("sql : " + sql);
			Statement st = conexion.conn.createStatement();
			st.executeUpdate(sql);				
//		}
//		catch (SQLException s){
//			System.out.println("Error en Update Usuario : "+ s.toString());
//			mensaje.me = "Error en Update Usuario";
//		}

		return mensaje;
	}
	
	public Mensaje eliminarUsuario(Usuario usuario){
		Mensaje mensaje = new Mensaje();
		
		String 	sql;
		try{
				sql = 	" UPDATE  Usuario " +
						" SET usuario_estado = 2 "+  
						" WHERE usuario_id = " + usuario.id;
				System.out.println("sql : " + sql);
				Statement st = conexion.conn.createStatement();
				st.executeUpdate(sql);				
		}
		catch (SQLException s){
			System.out.println("Error en Update Usuario : "+ s.toString());
			mensaje.me = "Error en Update Usuario";
		}
		return mensaje;
	}
	
	public Mensaje cambiarContra(Usuario usuario){
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		Conexion conexion = new Conexion();
//		conexion.abrirConexion();
		
		String 	sql;
		try{
				sql = 	" UPDATE  Usuario " +
						" SET usuario_contrasena = '"+usuario.password+"' " +  
						" WHERE usuario_id = " + usuario.id + " AND usuario_estado = 1" ;
				System.out.println("sql : " + sql);
				Statement st = conexion.conn.createStatement();
				st.executeUpdate(sql);				
		}
		catch (SQLException s){
			System.out.println("Error en Update Usuario : "+ s.toString());
			mensaje.me = "Error en Update Usuario";
		}
//		conexion.cerrarConexion();
		return mensaje;
		
	}
	
	
}
