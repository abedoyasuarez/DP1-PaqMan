package pacote.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.Incidencia;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Usuario;
import pacote.Model.Bean.Response.ListIncidencia;
import pacote.Model.Facade.AdministracionFacade;

@Controller
public class IncidenciaController {
	private static AdministracionFacade almacenFacade = new AdministracionFacade();
	
	@RequestMapping(value = "/Incidencia/registrarIncidencia")
    public String RegistrarIncidencia() {        
        return "../WEB-INF/Sistema Web/Incidencia/registrarIncidencia.jsp";
    }
	
	@RequestMapping(value = "/Incidencia/registrarIncidencia", method = RequestMethod.POST)
    public @ResponseBody Mensaje RegistrarIncidencia(@RequestBody Incidencia incidencia) {
		System.out.println("Registrar Incidencia");
        return almacenFacade.RegistrarIncidencia(incidencia);
    }
	
	@RequestMapping(value = "/Incidencia/buscarIncidencia", method = RequestMethod.POST)
    public @ResponseBody ListIncidencia BuscarIncidencia(@RequestBody Usuario usuario) {
		System.out.println("Registrar Incidencia");
        return almacenFacade.ListarIncidencias(usuario.id);
    }
	
	@RequestMapping(value = "/Incidencia/modificarIncidencia")
    public String ModificarIncidencia() {        
        return "../WEB-INF/Sistema Web/Incidencia/modificarIncidencia.jsp";
    }
	
	@RequestMapping(value = "/Incidencia/modificarIncidencia", method = RequestMethod.POST)
    public @ResponseBody Mensaje ModificarIncidencia(@RequestBody Incidencia incidencia) {
		System.out.println("Registrar Incidencia");
        return almacenFacade.ModificarIncidencia(incidencia);
    }
	
	@RequestMapping(value = "/Incidencia/eliminarIncidencia")
    public String EliminarIncidencia() {        
        return "../WEB-INF/Sistema Web/Incidencia/eliminarIncidencia.jsp";
    }
	
	@RequestMapping(value = "/Incidencia/eliminarIncidencia", method = RequestMethod.POST)
    public @ResponseBody Mensaje EliminarIncidencia(@RequestBody Incidencia incidencia) {
		System.out.println("Registrar Incidencia");
        return almacenFacade.EliminarIncidencia(incidencia);
    }
	
}
