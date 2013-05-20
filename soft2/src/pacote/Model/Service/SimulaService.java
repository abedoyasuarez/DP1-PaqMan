package pacote.Model.Service;

import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.AlmacenesSimula;
import pacote.Model.Bean.SimulaAlmacen;
import pacote.Model.Bean.SimulaDia;
import pacote.Model.Bean.SimulaVuelo;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Simula;

public class SimulaService {
	private static DAO_Simula dao_simula = new DAO_Simula();
	private static Conexion conexion= new Conexion();
	
	public List<SimulaAlmacen> buscaDataSimulacionAlmacen(){
		List<SimulaAlmacen> lista = new ArrayList<SimulaAlmacen>();
		try{
			conexion.abrirConexion();
			dao_simula.setConexion(conexion);
			lista =  dao_simula.dameListaSimulacionAlmacen();
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();			
		}
		return lista;
	}
	
	public List<SimulaDia> buscaDataSimulacionDia(){
		List<SimulaDia> lista = new ArrayList<SimulaDia>();
		try{
			conexion.abrirConexion();
			dao_simula.setConexion(conexion);
			lista =  dao_simula.dameListaSimulacionDia();
			conexion.cerrarConexion();
		}catch(Exception e){
			e.printStackTrace();			
		}
		return lista;
	}
	
	public List<SimulaVuelo> buscaDataSimulacionVuelo(){
		List<SimulaVuelo> lista = new ArrayList<SimulaVuelo>();
		
		try{
			conexion.abrirConexion();
			dao_simula.setConexion(conexion);
			lista = dao_simula.dameListaSimulacionVuelo();
			conexion.cerrarConexion();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}

	public List<SimulaVuelo> buscaDataSimulacionVuelo2(List<AlmacenesSimula> listaAlm){
		List<SimulaVuelo> lista = new ArrayList<SimulaVuelo>();
		List<SimulaVuelo> aux = new ArrayList<SimulaVuelo>();
		
		try{
			
			System.out.println("Inicio service Vuelo");
			
			conexion.abrirConexion();
			dao_simula.setConexion(conexion);
			for(int i = 0; i < listaAlm.size();i++){
				System.out.println("Pais de entrada: " + Integer.parseInt(listaAlm.get(i).idPaisEntrada));
				System.out.println("Pais de salida: " + Integer.parseInt(listaAlm.get(i).idPaisSalida));
				aux = dao_simula.dameListaSimulacionVuelo2(Integer.parseInt(listaAlm.get(i).idPaisEntrada), Integer.parseInt(listaAlm.get(i).idPaisSalida));
				
				for(int j = 0; j < aux.size();j++){
					lista.add(aux.get(j));
				}
				
			}
			conexion.cerrarConexion();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	
}
