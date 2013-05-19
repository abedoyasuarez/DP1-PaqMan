package pacote.Controller;
//asdfssdfaMODIFICOasdfasdfaasdfa
//faasdfasadsfmiaadasdf
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pacote.Model.Bean.Response.ReporteMesResponse;
import pacote.Model.Bean.Response.ReportePaisResponse;
import pacote.Model.Facade.AdministracionFacade;

@Controller
public class Home{
 
	private AdministracionFacade adminFacade = new AdministracionFacade(); 
	
    @RequestMapping(value = "/")
    public String home() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/home.jsp";
    }
    
     @RequestMapping(value = "/movil")
    public String movil() {
        System.out.println("HomeController: Passing through...");
        //return "WEB-INF/Movil/GS/index.jsp";
        return "resourceMovil/GS/index.jsp";
     }
     
    @RequestMapping(value = "/reporteGeneral")
    public String reporte() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/Sistema Web/Reportes/reporteGeneral.jsp";
    }
     
    @RequestMapping(value = "/reportes/reporteGeneral", method = RequestMethod.POST)
    public @ResponseBody ReportePaisResponse reportePaises() {
        System.out.println("Reporte 1");
        return adminFacade.reportePaises();
    }
    
    @RequestMapping(value = "/reportes/reporteGeneralPais", method = RequestMethod.POST)
    public @ResponseBody ReportePaisResponse reportePais(@RequestBody int id) {
        System.out.println("Reporte 2");
        return adminFacade.reportePais(id);
    }
    
    @RequestMapping(value = "/reportes/reporteGeneralMes", method = RequestMethod.POST)
    public @ResponseBody ReporteMesResponse reporteMes(@RequestBody int mes) {
        System.out.println("Reporte Mes");
        return adminFacade.reporteMes(mes);
    }
    
     @RequestMapping(value = "/reporte1")
     public String reporte1() {
         System.out.println("HomeController: Passing through...");
         return "WEB-INF/Sistema Web/Reportes/reporte1.jsp";
     }
     
     @RequestMapping(value = "/reporte2")
     public String reporte2() {
         System.out.println("HomeController: Passing through...");
         return "WEB-INF/Sistema Web/Reportes/reporte2.jsp";
     }
     
     @RequestMapping(value = "/reporte3")
     public String reporte3() {
         System.out.println("HomeController: Passing through...");
         return "WEB-INF/Sistema Web/Reportes/reporte3.jsp";
     }
     
   
     @RequestMapping(value = "/reporteColumna")
    public String columna() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/Reportes/columna.jsp";
    }
   

   @RequestMapping(value = "/reportePie")
    public String pie() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/Reportes/pie.jsp";
    }
   
    @RequestMapping(value = "/reporteBarra")
    public String barra() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/Reportes/barra.jsp";
    }

    
    @RequestMapping(value = "/loginWeb")
    public String loginWeb() {
        System.out.println("HomeController: Passing through...");
        return "WEB-INF/Sistema Web/login.jsp";
    }
    
    @RequestMapping(value = "/contacto")
    public String contacto(){
    	return "WEB-INF/Extranet/Contacto.jsp";
    }

    @RequestMapping(value = "/extranet")
    public String extranet(){
    	return "WEB-INF/Extranet/Index.jsp";
    }
    
     @RequestMapping(value = "/extranet/mapa")
    public String mapa(){
       return "../WEB-INF/Extranet/Mapa.jsp"; 
    }
    
     @RequestMapping(value = "/extranet/maparastreo")
     public String mapaRastreo(){
          return "../WEB-INF/Extranet/MapaRastreo.jsp"; 
     }
    
    
    @RequestMapping(value = "/sistemaWeb")
    public String sistemaWeb(){
    	return "WEB-INF/Sistema Web/index.jsp";
    }
    
/*    @RequestMapping(value="/{name}", method = RequestMethod.GET)
	public @ResponseBody Shop getShopInJSON(@PathVariable String name) {
 
		Shop shop = new Shop();
		shop.name = name ;
		//shop.staffName = new String[]{"mkyong1", "mkyong2"}; 
		return shop;
 
	}
    
    @RequestMapping(value="/pst", method = RequestMethod.POST)
	public @ResponseBody Shop infoUsuario(@RequestBody Shop ss) {
 
		Shop shop = new Shop();
		shop.name = ss.name + "ss";
		//shop.staffName = new String[]{"mkyong1ss", "mkyong2"}; 
		return shop;
 
	}*/
}