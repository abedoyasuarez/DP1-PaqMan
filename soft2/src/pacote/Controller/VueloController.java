package pacote.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Facade.NegocioFacade;

@Controller
public class VueloController {
	private static NegocioFacade negocioFacade = new NegocioFacade(); 
	@RequestMapping(value = "/Vuelos/cargarVuelos")
    public String RegistrarVuelos() {        
        return "../WEB-INF/Sistema Web/Vuelos/cargarVuelos.jsp";
    }
	
	@RequestMapping(value = "/Vuelos/cargarVuelos", method = RequestMethod.POST)
    public Mensaje RegistrarVuelos(@RequestBody String file) {        
        System.out.println("file : " + file );
        return negocioFacade.LeerFileVuelo(file);
    }
}
