package pacote.Model.Service;

import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_CargaBD;

public class CargaBDService {
	
	private static DAO_CargaBD dao_cargabd = new DAO_CargaBD();//
	private static Conexion conexion= new Conexion();
	
	public void buscaDataSimulacionDia(){
		
		try{
			conexion.abrirConexion();
			dao_cargabd.setConexion(conexion);
			dao_cargabd.cargarDatos();
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();			
		}
		
	}
	
}
