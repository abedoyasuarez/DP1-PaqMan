package pacote.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.Almacen;
import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Response.ListAlmacen;
import pacote.Model.Bean.Response.ListContinente;
import pacote.Model.Bean.Response.ListPais;
import pacote.Model.Facade.AdministracionFacade;

@Controller
public class AlmacenController {
	private static AdministracionFacade almacenFacade = new AdministracionFacade();

	@RequestMapping(value = "/Almacen/ListarContinentes", method = RequestMethod.POST)
    public @ResponseBody ListContinente ListarContinentes() {
        return almacenFacade.ListarContinentes();
    }
	
	@RequestMapping(value = "/Almacen/ListarPaises", method = RequestMethod.POST)
    public @ResponseBody ListPais ListarPaises(@RequestBody int id_continente) {
        return almacenFacade.ListarPaises(id_continente);
    }
	
	
	@RequestMapping(value = "/Almacen/modificarAlmacen")
    public String ModificarAlmacen() {        
        return "../WEB-INF/Sistema Web/Almacen/modificarAlmacen.jsp";
    }
	
	@RequestMapping(value = "/Almacen/cargarAlmacen")
    public String CargarAlmacen() {        
        return "../WEB-INF/Sistema Web/Almacen/cargarAlmacen.jsp";
    }
	
	@RequestMapping(value = "/Almacen/modificarAlmacen", method = RequestMethod.POST)
    public @ResponseBody Mensaje ModificarAlmacen(@RequestBody Almacen almacen) {
		System.out.println("Modifica Almacen");        
        return almacenFacade.ModificarAlmacen(almacen); 
    }
	
	@RequestMapping(value = "/Almacen/eliminarAlmacen", method = RequestMethod.POST)
    public @ResponseBody Mensaje EliminarAlmacen(@RequestBody Almacen almacen) {
		System.out.println("Modifica Almacen");        
        return almacenFacade.eliminarAlmacen(almacen); 
    }
	
	@RequestMapping(value = "/Almacen/eliminarAlmacen")
    public String EliminarAlmacen() {        
        return "../WEB-INF/Sistema Web/Almacen/eliminarAlmacen.jsp";
    }
	
	@RequestMapping(value = "/Almacen/buscarAlmacen", method = RequestMethod.POST)
    public @ResponseBody Mensaje BuscarAlmacen(@RequestBody Almacen almacen) {
		System.out.println("Buscar Almacen");
        Mensaje mensaje = new Mensaje();
        mensaje.me = "BUSCADO";
        return mensaje;
    }
	
	@RequestMapping(value = "/Almacen/listarAlmacenesActivos", method = RequestMethod.POST)
    public @ResponseBody ListAlmacen ListarAlmacenesActivos() {
		System.out.println("Buscar Almacen");
        return almacenFacade.ListarAlmacenesActivos();
    }
	
	@RequestMapping(value = "/Almacen/listarAlmacenes", method = RequestMethod.POST)
    public @ResponseBody ListAlmacen ListarAlmacenes(@RequestBody Almacen almacen) {
		System.out.println("Buscar Almacen");
        return almacenFacade.ListarAlmacenes(almacen.id);
    }
	
	@RequestMapping(value = "/Almacen/registrarAlmacen")
    public String RegistrarAlmacen() {        
        return "../WEB-INF/Sistema Web/Almacen/registrarAlmacen.jsp";
    }
	
	@RequestMapping(value = "/Almacen/registrarAlmacen", method = RequestMethod.POST)
    public @ResponseBody Mensaje RegistrarAlmacen(@RequestBody Almacen almacen) {
		System.out.println("Registra " +
				"almacen");
        Mensaje mensaje = new Mensaje();
        mensaje = almacenFacade.RegistrarAlmacen(almacen);        
        return mensaje;
    }
}
