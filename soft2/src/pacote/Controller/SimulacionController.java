package pacote.Controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import pacote.Model.Bean.AlmacenCaida;
import pacote.Model.Bean.AlmacenesSimula;
import pacote.Model.Bean.Simul;
import pacote.Model.Bean.SimulaAlmacen;
import pacote.Model.Bean.SimulaDataAlmacen;
import pacote.Model.Bean.SimulaDataVuelo;
import pacote.Model.Bean.SimulaVuelo;
import pacote.Model.Bean.VueloCaida;
import pacote.Model.Bean.Vuelo_Padre;
import pacote.Model.Bean.Response.AlmacenX;
import pacote.Model.Bean.Response.DatoSimulacion;
import pacote.Model.Bean.Response.RetornoSimulacion;
import pacote.Model.Bean.Response.VueloX;
import pacote.Model.DAO.Conexion;
import pacote.Model.DAO.DAO_Almacen;
import pacote.Model.DAO.DAO_Vuelo_Padre;
import pacote.Model.Facade.SimulacionFacade;
import pacote.Model.Service.SimulaService;

import weka.core.Instances;
import weka.classifiers.Classifier;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.functions.neural.LinearUnit;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.functions.SimpleLinearRegression;
import weka.classifiers.functions.SMO;

import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.timeseries.WekaForecaster;
import weka.classifiers.timeseries.core.TSLagMaker;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;

@Controller
public class SimulacionController {
	SimulacionFacade facadeSimulacion = new SimulacionFacade();
	@RequestMapping(value = "/Simulacion/simular", method = RequestMethod.POST)
    public @ResponseBody RetornoSimulacion ConsultarRuta(@RequestBody Simul s) {
		Date fecha= new Date();
		RetornoSimulacion retorno = null;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			System.out.println("fecha : " + s.ff);
			fecha =  dateFormat.parse(s.ff);
			System.out.println("fecha : " + fecha);
			//retorno = facadeSimulacion.simular(fecha,s.numDiasDataHistorica);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al parsear fecha");
			e.printStackTrace();
		};		        
        return retorno;
    }


	
	@RequestMapping(value = "/Simulacion/hardcode", method = RequestMethod.POST)
    public @ResponseBody RetornoSimulacion HardCode() {
		DAO_Almacen dao_almacen = new DAO_Almacen();
		DAO_Vuelo_Padre dao_vuelo = new DAO_Vuelo_Padre();
		
		Conexion conexion = new Conexion();
				
		RetornoSimulacion ret = new RetornoSimulacion();
		try{
			conexion.abrirConexion();
			dao_almacen.setConexion(conexion);
			dao_vuelo.setConexion(conexion);
			
			List<AlmacenX> lAlm = dao_almacen.lAlmacen();
			List<Vuelo_Padre> lVuelo =  dao_vuelo.listaVPadres();
			
			for(int i=0; i<100; i++){
				DatoSimulacion datSim = new DatoSimulacion();
				String fech = "";
				
				ArrayList<AlmacenX> listaAlmacen = new ArrayList<AlmacenX>();
				for(int j=0; j<lAlm.size(); j++){
					AlmacenX aall = new AlmacenX();
					AlmacenX aux = lAlm.get(j); 
					aall.id = aux.id;
					aall.nombre = ""+aux.id;
					aall.longitud = aux.longitud;
					aall.latitud = aux.latitud;
					aall.cantidadLleno = (int)(Math.random() * ((1000) + 1));
					aall.capacidad = 1000;
					aall.porcentajeLlenado = (float) ((aall.cantidadLleno*1.0)/aall.capacidad);
					listaAlmacen.add(aall);
				}
				
				ArrayList<VueloX> listaVuelo = new ArrayList<VueloX>();
				for(int j=0; j<lVuelo.size(); j++){
					VueloX vuelo = new VueloX();
					Vuelo_Padre vp = lVuelo.get(i); 
					vuelo.vuelo_id = j;
					
					vuelo.capacidad=300;
					vuelo.cantidadLleno= (int)(Math.random() * ((300) + 1));;
					vuelo.porcentaje=(float)(vuelo.cantidadLleno*1.0)/vuelo.capacidad;
					   
					vuelo.almacenPartidaID = vp.almacen_partida;					
					vuelo.almacenPartidaLatitud = lAlm.get(vp.almacen_partida-1).latitud;
					vuelo.almacenPartidaLongitud = lAlm.get(vp.almacen_partida-1).longitud;
					   
					vuelo.almacenLlegadaID = vp.almacen_llegada;
					vuelo.almacenLlegadaLatitud = lAlm.get(vp.almacen_llegada-1).latitud;
					vuelo.almacenLlegadaLongitud = lAlm.get(vp.almacen_llegada-1).longitud;
					
					listaVuelo.add(vuelo);
				}
				datSim.almacenes = listaAlmacen;
				datSim.vuelos = listaVuelo; 
				ret.listaSim.add(datSim);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		ret.me="MENSAJE ERROR";
		return ret;
    }

    @RequestMapping(value = "/gerente/simular")
    public String simulacion(){
    	return "../WEB-INF/Sistema Web/Simulacion/simulacion.jsp";
    }   
    
     @RequestMapping(value = "/gerente/mapa")
    public String mapa(){
    	return "../WEB-INF/Sistema Web/Simulacion/mapa.jsp";
    }   
     
     
     @RequestMapping(value = "/Simulacion/hardcode2", method = RequestMethod.POST)
     public @ResponseBody RetornoSimulacion SilumacionPacote(@RequestBody Simul s) throws Exception {
    	 
    	 RetornoSimulacion rpta = new RetornoSimulacion();    	 
    	 
    	 rpta.me = "";
    	 
    	 int numUT = Integer.parseInt(s.ff);		
    	 List<AlmacenesSimula> almacenesSim = s.listaAlmacenes; 
    	     	 
    	 System.out.println("Panchito");
    	 try{
    	 
	    	 System.out.println("WEKA working :) - Almacenes");
	    	 
	    	 //Inicializo todos los dias que simulo
	         for(Integer i = 0; i < numUT; i++){
	        	DatoSimulacion dato = new DatoSimulacion();
	        	dato.fechaCad = i.toString();
	        	rpta.listaSim.add(dato);
	        	
	        	DatoSimulacion dato1 = new DatoSimulacion();
	        	dato.fechaCad = i.toString();
	        	rpta.listaSimVuelo.add(dato1);
	         }
	         //Pido datos
	         
	         System.out.println("Antes del servicio de Almacenes");
	         
	         SimulaService simulaService = new SimulaService();
	         List<SimulaAlmacen> listaAlmacenes = simulaService.buscaDataSimulacionAlmacen();
	         
	         System.out.println("Paso el servicio de Almacenes");
	         	         	         
	         System.out.println("Size Almacenes: " + listaAlmacenes.size());
	         
	         for(int i = 0; i < listaAlmacenes.size();i++){
	        	 
	        	 List<SimulaDataAlmacen> listaSimulaData = listaAlmacenes.get(i).listaSimulaDataAlmacen;		
	        	 
	        	 System.out.println("****Impresion Lista*****");
	        	 	        	 
	        	 System.out.println("Almacen: " + listaAlmacenes.get(i).almacen_id);
	        	 System.out.println("Tama�o: " + listaSimulaData.size());
	        	 	        	 
	        	 int caido = 0;
	        	 
	        	 WekaForecaster forecaster = new WekaForecaster();
		    	 
		    	 Attribute atcantEnvios = new Attribute("cantEnvios");
		    	 Attribute atFecha = new Attribute("Fecha", "dd-MM-yyyy");
		         
		         FastVector atributos = new FastVector();
		         
		         atributos.addElement(atcantEnvios);
		         atributos.addElement(atFecha);
		         
		         Instances dataset = new Instances("dataset_simulacion", atributos, 0);	        	 
	        	 
		         if(listaSimulaData.size() > 1){
                                 int dataSize = listaSimulaData.size();
			         for(int j = 0; j< dataSize; j++){
			        	 
			        	 double[] attValues = new double[dataset.numAttributes()];
			        	 String fecha;
			        	 java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
			        	 Integer n;
			        	 
			        	 n = listaSimulaData.get(j).cantidadTotal;
			        	 fecha = sdf.format(listaSimulaData.get(j).created_day);
			        	 attValues[0] = n.intValue();
			        	 attValues[1]= dataset.attribute(1).parseDate(fecha);
				         dataset.add(new DenseInstance(1.0, attValues));
				         
				         
			         }
			         
			         forecaster.setFieldsToForecast("cantEnvios");
			         forecaster.setBaseForecaster(new GaussianProcesses());
			         
			         forecaster.getTSLagMaker().setTimeStampField("Fecha");
			         forecaster.getTSLagMaker().setPeriodicity(TSLagMaker.Periodicity.DAILY);
			         
			         forecaster.getTSLagMaker().setMinLag(1); 
			         forecaster.getTSLagMaker().setMaxLag(4);
			         
			         forecaster.buildForecaster(dataset, System.out);
			         
			         forecaster.primeForecaster(dataset);
			         
			         List<List<NumericPrediction>> forecast = forecaster.forecast(numUT, System.out);
			         
			         for(int w = 0;w < numUT; w++){
			        	 List<NumericPrediction> predsAtStep = forecast.get(w);
			        	 
			        	 NumericPrediction predForTarget = predsAtStep.get(0);
			        	 System.out.println(w + ": " + predForTarget.predicted() + " ;");
			        	 
			        	 AlmacenX insertAl = new AlmacenX();
			        	 insertAl.id = listaAlmacenes.get(i).almacen_id;
			        	 insertAl.nombre = listaAlmacenes.get(i).almacen_ciudad;
			        	 insertAl.longitud = listaAlmacenes.get(i).almacen_longitud;
			        	 insertAl.latitud = listaAlmacenes.get(i).almacen_latitud;
			        	 insertAl.cantidadLleno = (int)((predForTarget.predicted() + 0.5));	
			        	 
			        	 if((insertAl.cantidadLleno < 0)){
			        		 insertAl.cantidadLleno = 0;
			        	 }
			        	 else{ 
			        		 if(insertAl.cantidadLleno > 999){
			        			 insertAl.cantidadLleno = 1000; //PEdidos
			        			 
			        			 if(caido != 1){
			        				 AlmacenCaida almCaida = new AlmacenCaida();
			        				 almCaida.almacen_id = insertAl.id;
			        				 almCaida.dia_caida = w;
			        				 almCaida.nombre_almacen = insertAl.nombre;
			        				 rpta.listaCaidos.add(almCaida);
			        				 caido = 1;
			        			 }
			        		 }
			        	 }
			        	 insertAl.capacidad = listaAlmacenes.get(i).almacen_capacidad;
			        	 insertAl.porcentajeLlenado = insertAl.cantidadLleno/10;
			        	 
			        	 rpta.listaSim.get(w).almacenes.add(insertAl);
			         }
			         System.out.println("****Fin Lista*****");
		         }
	         }
	         //Fin de los almacenes
/*	         //Inicio vuelos
	         System.out.println("WEKA working for Vuelos :)");
	         
	         System.out.println("Antes del servicio de Vuelos :(");

	         //Obtener los vuelos del servicio
	         List<SimulaVuelo> listaVuelos = simulaService.buscaDataSimulacionVuelo();
	         
	         System.out.println("Despues del servicio de Vuelos");
	         
	         System.out.println("Cantidad de vuelos: " + listaVuelos.size());
	         
                 int tam = listaVuelos.size();
	         for(int i = 0; i < tam; i++){
	        	 
	        	 List<SimulaDataVuelo> listaSimulaData = listaVuelos.get(i).listaSimulaDataVuelo;
	        	 
	        	 System.out.println("****Impresion Lista*****");
 	        	 
	        	 System.out.println("Vuelo: " + listaVuelos.get(i).vuelo_id);
	        	 System.out.println("Tama�o: " + listaSimulaData.size());
	        	 	        	 
	        	 int caido = 0;
	        	 
	        	 WekaForecaster forecaster = new WekaForecaster();
		    	 
		    	 Attribute atcantEnvios = new Attribute("cantEnvios");
		    	 Attribute atFecha = new Attribute("Fecha", "dd-MM-yyyy");
		         
		         FastVector atributos = new FastVector();
		         
		         atributos.addElement(atcantEnvios);
		         atributos.addElement(atFecha);
		         
		         Instances dataset = new Instances("dataset_simulacion", atributos, 0);	        
	        	
		         if(listaSimulaData.size() > 1){
		        	 int simulaData = listaSimulaData.size();
		        	 for(int j = 0; j< simulaData; j++){
		        		 double[] attValues = new double[dataset.numAttributes()];
			        	 String fecha;
			        	 java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd-MM-yyyy");
			        	 Integer n;
			        	 
			        	 n = listaSimulaData.get(j).cantidadTotal;
			        	 fecha = sdf.format(listaSimulaData.get(j).created_day);
			        	 attValues[0] = n.intValue();
			        	 attValues[1]= dataset.attribute(1).parseDate(fecha);
				         dataset.add(new DenseInstance(1.0, attValues));
		        	 }    	 
		        	 
		        	 forecaster.setFieldsToForecast("cantEnvios");
			         forecaster.setBaseForecaster(new GaussianProcesses());
			         
			         forecaster.getTSLagMaker().setTimeStampField("Fecha");
			         forecaster.getTSLagMaker().setPeriodicity(TSLagMaker.Periodicity.DAILY);
			         
			         forecaster.getTSLagMaker().setMinLag(1); 
			         forecaster.getTSLagMaker().setMaxLag(4);
			         
			         forecaster.buildForecaster(dataset, System.out);
			         
			         forecaster.primeForecaster(dataset);
			         
			         List<List<NumericPrediction>> forecast = forecaster.forecast(numUT, System.out);
			        
			         for(int w = 0;w < numUT; w++){
			        	 List<NumericPrediction> predsAtStep = forecast.get(w);
			        	 
			        	 NumericPrediction predForTarget = predsAtStep.get(0);
			        	 System.out.println(w + ": " + predForTarget.predicted() + " ;");
			        	 
			        	 VueloX insertVl = new VueloX();
			        	 insertVl.padre_id = listaVuelos.get(i).padre_id;
			        	 insertVl.almacenLlegada = listaVuelos.get(i).almacen_llegada;
			        	 insertVl.almacenPartida = listaVuelos.get(i).almacen_partida;
			        	 insertVl.cantidadLleno = (int)(predForTarget.predicted()/3 + 0.5);
			        	 
			        	 if((insertVl.cantidadLleno < 0)){
			        		 insertVl.cantidadLleno = 0;
			        	 }
			        	 else{
			        		 if(insertVl.cantidadLleno > 299){
			        			 insertVl.cantidadLleno = 300;
			        			 
			        			 if(caido != 1){
			        				 VueloCaida vueloCaida = new VueloCaida();
			        				 vueloCaida.padre_id = insertVl.padre_id;
			        				 vueloCaida.dia_caida = w;
			        				 vueloCaida.almacen_partida = insertVl.almacenPartida;
			        				 vueloCaida.almacen_llegada = insertVl.almacenLlegada;
			        				 rpta.listaCaidosVuelos.add(vueloCaida);
			        				 caido = 1;
			        			 }
			        		 }
			        	 }
			        	 
			        	 insertVl.capacidad = listaVuelos.get(i).vuelo_capacidad;
			        	 insertVl.porcentajeLlenado = insertVl.cantidadLleno / 10;
			        	 
			        	 
			        	 rpta.listaSimVuelo.get(w).vuelos.add(insertVl);
			        	 
			         }
			         
			         
			         
		         }
		         
		         
	         }
	         //Fin vuelos
	*/                  
	         
    	 }
	     catch(Exception e){
	    	 rpta.me = "Error :(";
	    	 e.printStackTrace();
	    	 System.out.println("CATCH");
	     }
         
         
    	 return rpta;
    	 
     }
        
}

