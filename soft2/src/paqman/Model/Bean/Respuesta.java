package paqman.Model.Bean;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Respuesta {
	private List<Vehiculo> listaMotos;
	private List<Vehiculo> listaAutos;
	
	public int modificarRespuesta(){
		int flag=0;
		flag+=modificarListaVehiculo(this.listaMotos);
		flag+=modificarListaVehiculo(this.listaAutos);			
		return (flag>0)?1:0;
	}
	
	public int modificarListaVehiculo(List<Vehiculo> listaVehiculos) {
		int flag=0;
		int i;
		for(i=0;i<listaVehiculos.size();i++){
			Vehiculo vehiculo=listaVehiculos.get(i);
	        if (vehiculo.getRutaActual().getTrayectoria().size()==1 || vehiculo.getEstado()==0)continue;
	        if (vehiculo.getEstado()==2){
                vehiculo.setTiempoDescanso(vehiculo.getTiempoDescanso()+2);
                if (vehiculo.getTiempoDescanso()==60) vehiculo.setTiempoDescanso(0);
                continue;
	        }
	        int posicionRelativa=vehiculo.getPosicionRelativa();
	        int distanciaAcumulada=0;
	        double distanciaRecorrer=vehiculo.getSaltoDistancia();
	        distanciaRecorrer+=vehiculo.getDistanciaRecorrida()-(int)vehiculo.getDistanciaRecorrida();
	        while(posicionRelativa<vehiculo.getRutaActual().getListaPaquetes().size()&&(
	        		vehiculo.getRutaActual().getListaPaquetes().get(posicionRelativa+1).getPuntoEntrega() -
	        		vehiculo.getRutaActual().getListaPaquetes().get(posicionRelativa).getPuntoEntrega())<=distanciaRecorrer){
	        	vehiculo.setCantidadPaquetes(vehiculo.getCantidadPaquetes()-vehiculo.getRutaActual().getListaPaquetes().get(posicionRelativa).getCantidad());
	        	distanciaAcumulada+=vehiculo.getRutaActual().getListaPaquetes().get(posicionRelativa+1).getPuntoEntrega()-
	        			vehiculo.getRutaActual().getListaPaquetes().get(posicionRelativa).getPuntoEntrega();
	        	distanciaRecorrer-=distanciaAcumulada;
	        	posicionRelativa++;
	        	flag=1;
	        }
	        vehiculo.setPosicionRelativa(posicionRelativa);
	        vehiculo.setDistanciaRecorrida(vehiculo.getDistanciaRecorrida()+distanciaAcumulada);
	        if(distanciaRecorrer>0){
	        	vehiculo.setPosicionRuta(vehiculo.getPosicionRuta()+(int)distanciaRecorrer);
	        	vehiculo.setDistanciaRecorrida(vehiculo.getDistanciaRecorrida()+distanciaRecorrer);
	        	if(distanciaRecorrer>=1) flag=1;	
	        }
	        if (vehiculo.getPosicionRuta()+1==vehiculo.getRutaActual().getTrayectoria().size()){
	        	//Reset
	        	Nodo nodoAlmacen=vehiculo.getRutaActual().getTrayectoria().get(0);
	        	vehiculo.getRutaActual().getTrayectoria().clear();
	        	vehiculo.getRutaActual().getTrayectoria().set(0, nodoAlmacen);
	        	vehiculo.getRutaActual().getListaPaquetes().clear();
	        	Paquete paquete=vehiculo.getRutaActual().getListaPaquetes().get(0);
	        	vehiculo.getRutaActual().getListaPaquetes().set(0, paquete);
	        	vehiculo.setCantidadPaquetes(0);
	        	vehiculo.setPosicionRelativa(0);
	        	vehiculo.setPosicionRuta(0);
	        	vehiculo.setEstado(0);
	        	vehiculo.setTiempoDescanso(0);
	        	vehiculo.setTiempoTrabajo(0);
	        	vehiculo.getRutaActual().setDistancia(0);
	        	vehiculo.getRutaActual().setLlegadaAlmacen(Simulacion.getFechaActual(false));   	
	        }
	        vehiculo.setTiempoTrabajo(vehiculo.getTiempoTrabajo()+Simulacion.intervaloTiempo);
	        double costoHE=(vehiculo.getTiempoTrabajo()>480)?(Simulacion.intervaloTiempo*vehiculo.getCostoHE()/60.0):0;
	        Simulacion.costo+=distanciaAcumulada*vehiculo.getCostoPorKm()+costoHE;
		}
		return flag;
	}

	private int solucion(){
//		aguanta tu coche
		int limite=0;
		for(Pedido pedido:Simulacion.listaPedidos){
			if(Simulacion.hastaIntervalo(pedido)){
				limite++;
			} else break;
		}
		Collections.sort(Simulacion.listaPedidos.subList(0,limite),new pedidosComparator());
		Collections.sort(this.listaMotos,new vehiculoComparator());
		
		return 1;
	}
	
	public int planificarRespuesta(){
		//planificar la respuesta en base a pedidos leidos
		if(Simulacion.listaPedidos.size()==0||Simulacion.tiempoActual==4320){
			return 0; //se acabó
		}
		solucion();
		return 1;
	}
}

