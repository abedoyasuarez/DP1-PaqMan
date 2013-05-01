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
      String password="";
      String database="mydb";
      
     Class.forName("com.mysql.jdbc.Driver").newInstance();
     conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+database, username, password);
     conn.setAutoCommit(false);
     return conn;
	}

	public void cerrarConexion() throws SQLException{
			conn.close();
	}
}
