package pacote.Model.DAO;

import java.io.*;
import java.net.URL;
import java.sql.*;

public class DAO_CargaBD extends ConnectBD {
	
	private static Statement statement = null;
	
	public void cargarDatos() throws Exception{
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL url= classLoader.getResource("/pacote/Model/DAO/ArchivosDatosHistoricos");
		//System.out.println(url2.toString());
		
		File directorio = new File(url.toURI());
		
		//File directorio = new File("src/pacote/Model/DAO/ArchivosDatosHistoricos"/*path en donde estaran los files*/);
		File[] archivos = directorio.listFiles();
		String insert;
		
		try{
			
			statement = conexion.conn.createStatement();
			//FileReader reader;
			//BufferedReader br;
			
			for (File path:archivos){
				
				//reader = new FileReader(path.toString());
				//br = new BufferedReader(reader);
				//String input = "";
				String filename = path.getName().toString();
				String year = filename.substring(5, 7);
				String month = filename.substring(7, 9);
				String day = filename.substring(9, 11);
				String fecha = year+"/"+month+"/"+day;
				
				//while ((input = br.readLine()) != null){
					
					String ruta = path.getAbsolutePath().toString();
					//System.out.println(ruta);
					//ruta.replace("\\", "/");
					//String[] arrayRuta = ruta.split(Character.toString((char)92));
				 
					//for (int i = 0; i < arrayRuta.length; i++) {
						//System.out.println(arrayRuta[i]);
					//}
				
					System.out.println(ruta);
					insert = "LOAD DATA LOCAL INFILE '"+ ruta +"' INTO TABLE `inf2260981g5`.`Pedido_Historico` FIELDS TERMINATED BY ',' LINES TERMINATED BY '\\n' (hora,coordx,coordy,paquetes,idcliente,prioridad) set fecha = '"+ fecha +"'";
					System.out.println(insert);
					//input = "'" + input.substring(0,5) + "'" + input.substring(5,input.length());
					//insert = "INSERT INTO Pedido_Historico_Test VALUES('" + fecha + "'," + input +")";
					statement.executeUpdate(insert);
					
				//}
				
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}