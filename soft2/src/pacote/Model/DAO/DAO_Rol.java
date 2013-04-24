package pacote.Model.DAO;

import java.sql.SQLException;
import java.sql.Statement;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Rol;

public class DAO_Rol {
	
	public Mensaje insertarRol(Rol rol){
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		Conexion conexion = new Conexion();
//		conexion.abrirConexion();
		
		String 	sql;
		try{
				sql = 	" INSERT INTO Rol(rol_nombre, rol_descripcion, rol_permisos, rol_estado) " +
						" VALUES ('" + rol.rol_nombre + "','" + rol.rol_descripcion + "',"+rol.rol_permisos+ "', " + rol.rol_estado + " )";
				System.out.println("sql : " + sql);
				Statement st = conexion.conn.createStatement();
				st.executeUpdate(sql);				
		}
		catch (SQLException s){
			System.out.println("Error en Insertar Rol");
			mensaje.me = s.toString();
		}
//		conexion.cerrarConexion();
		return mensaje;
	}
	
	public Mensaje modificarRol(Rol rol){
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		Conexion conexion = new Conexion();
//		conexion.abrirConexion();
		
		String 	sql;
		try{
				sql = 	" UPDATE  Rol " +
						" SET rol_nombre = '"+rol.rol_nombre+"', rol_descripcion = '"+rol.rol_descripcion+"', rol_permisos = '"+rol.rol_permisos + "', rol_estado = "+rol.rol_estado+  
						" WHERE rol_id = " + rol.rol_id;
				System.out.println("sql : " + sql);
				Statement st = conexion.conn.createStatement();
				st.executeUpdate(sql);				
		}
		catch (SQLException s){
			System.out.println("Error en Update Rol : "+ s.toString());
			mensaje.me = "Error en Update Rol";
		}
//		conexion.cerrarConexion();
		return mensaje;
	}
	
	public Mensaje eliminarRol(Rol rol){
		Mensaje mensaje = new Mensaje();
		mensaje.me = "";
		
		Conexion conexion = new Conexion();
//		conexion.abrirConexion();
		
		String 	sql;
		try{
				sql = 	" UPDATE  Rol " +
						" SET rol_estado = 2 "+  
						" WHERE rol_id = " + rol.rol_id;
				System.out.println("sql : " + sql);
				Statement st = conexion.conn.createStatement();
				st.executeUpdate(sql);				
		}
		catch (SQLException s){
			System.out.println("Error en Eliminar Rol : "+ s.toString());
			mensaje.me = "Error en Eliminar Rol";
		}
//		conexion.cerrarConexion();
		return mensaje;
	}
	
	
}
