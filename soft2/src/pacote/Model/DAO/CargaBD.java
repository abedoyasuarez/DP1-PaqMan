package pacote.Model.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CargaBD {
	
	private Connection connect = null;
	private Statement statement = null;
	
	public CargaBD() throws Exception{
		
		File directorio = new File("C:/Users/Julio/Downloads/DatosHistoricos"/*path en donde estaran los files*/);
		File[] archivos = directorio.listFiles();
		String insert;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://quilla.lab.inf.pucp.edu.pe:3306"/*orderdata*/,"inf2260981g5dba","aeronave");
			statement = connect.createStatement();
			
			FileReader reader;
			BufferedReader br;
			
			for (File path:archivos){
				
				reader = new FileReader(path.toString());
				br = new BufferedReader(reader);
				String input = "";
				String filename = path.getName().toString();
				String year = filename.substring(3, 7);
				String month = filename.substring(7, 9);
				String day = filename.substring(9, 11);
				String fecha = year+"/"+month+"/"+day;
				
				while ((input = br.readLine()) != null){
					
					insert = "INSERT INTO Pedido_Historico_Test VALUES(" + fecha + "," + input +")";
					statement.executeUpdate(insert);
					
				}
				
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		
	}	
	
}