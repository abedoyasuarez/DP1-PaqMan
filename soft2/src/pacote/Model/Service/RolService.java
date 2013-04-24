package pacote.Model.Service;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Rol;
import pacote.Model.DAO.DAO_Rol;

public class RolService {
private static DAO_Rol rolDAO = new DAO_Rol (); 
	
	public Mensaje RegistrarRol(Rol rol){
		return rolDAO.insertarRol(rol);
	}
	
	public Mensaje ModificarRol(Rol rol){
		return rolDAO.modificarRol(rol);		
	}
	
	public Mensaje EliminarRol(Rol rol){
		return 	rolDAO.eliminarRol(rol);
	}

}
