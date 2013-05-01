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
      String username="admin";
      String password="admin";
      String database="mydb";
     Class.forName("com.mysql.jdbc.Driver").newInstance();
//     conn = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe/"+database, username, password);
     conn = DriverManager.getConnection("jdbc:mysql://localhost/"+database, username, password);
//     conn = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe:3306/inf282g10","inf282g10", "paralelo");
     conn.setAutoCommit(false);
     return conn;
	}

	public void cerrarConexion() throws SQLException{
			conn.close();
	}
}
