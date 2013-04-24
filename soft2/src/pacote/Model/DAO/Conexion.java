package pacote.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion{
	protected Connection conn;
	
	public Conexion(){
		this.conn = null;
	}
	
	public void commit() throws SQLException{
		conn.commit();
	}
	
	public void rollback() throws SQLException{
		conn.rollback();
	}
	
	public Connection abrirConexion() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		conn = null;
      String username="root";
//      String password="josedaniel";
      String password="dp1-admin";
     
//     String username="inf2260882g2dba";
//     String password="industria";
		
//		String username="inf2260882g2dba";
//		String password="industria";

     String database="inf2260882g2";
     Class.forName("com.mysql.jdbc.Driver").newInstance();
//     conn = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/"+database, username, password);
//     conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+database, username, password);
     conn = DriverManager.getConnection("jdbc:mysql://200.16.7.78/"+database, username, password);
//     conn = DriverManager.getConnection("jdbc:mysql://localhost/"+database, username, password);
     conn.setAutoCommit(false);
     return conn;
	}

	public void cerrarConexion() throws SQLException{
			conn.close();
	}
}
