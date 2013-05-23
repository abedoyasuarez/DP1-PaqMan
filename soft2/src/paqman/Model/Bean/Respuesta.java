package paqman.Model.Bean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Respuesta {
	private List<Vehiculo> listaMotos;
	private List<Vehiculo> listaAutos;
	private List<Vehiculo> futuraListaMotos;
	private List<Vehiculo> futuraListaAutos;
	
	public int inicializarRespuesta(){
		
		listaMotos=new ArrayList<Vehiculo>();
		for (int i=0;i< Simulacion.cantidadMotos;i++){
				Vehiculo moto=new Vehiculo();
				moto.inicializarVehiculo(0,i);				
				listaMotos.add(moto);
				Vehiculo futuraMoto=new Vehiculo();
				futuraMoto.inicializarVehiculo(0,i);				
				futuraListaMotos.add(futuraMoto);
				
		}
		listaAutos=new ArrayList<Vehiculo>();
		for (int i=0;i< Simulacion.cantidadAutos;i++){
				Vehiculo auto=new Vehiculo();
				auto.inicializarVehiculo(1,i+Simulacion.cantidadMotos);
				listaAutos.add(auto);
				Vehiculo futuroAuto=new Vehiculo();
				futuroAuto.inicializarVehiculo(1,i+Simulacion.cantidadMotos);				
				futuraListaAutos.add(futuroAuto);
		}
		
		
		
		
		return 1;
	}
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
                vehiculo.setTiempoDescanzo(vehiculo.getTiempoDescanzo()+Simulacion.intervaloTiempo);
                if (vehiculo.getTiempoDescanzo()==60) vehiculo.setTiempoDescanzo(0);
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
	        	distanciaAcumulada+=(int)distanciaRecorrer;
	        	if(distanciaRecorrer>=1) flag=1;	
	        }
	        if (vehiculo.getPosicionRuta()+1==vehiculo.getRutaActual().getTrayectoria().size()){
	        	//Reset
	        	//Nodo nodoAlmacen=vehiculo.getRutaActual().getTrayectoria().get(0);
	        	
	        	//vehiculo.getRutaActual().getTrayectoria().clear();
	        	//vehiculo.getRutaActual().getTrayectoria().add(nodoAlmacen);
	        	
	        	
	        	if (vehiculo.getTipo()==0){//motos
	        		int id=vehiculo.getId();
	        		vehiculo.setRutaActual(this.futuraListaMotos.get(id).getRutaActual());
	        		this.futuraListaMotos.get(id).setRutaActual(new Ruta());
	        		this.futuraListaMotos.get(id).getRutaActual().inicializarRuta(vehiculo);
	        	}
	        	else {//autos
	        		int id=vehiculo.getId()-Simulacion.cantidadMotos;
	        		vehiculo.setRutaActual(this.futuraListaAutos.get(id).getRutaActual());
	        		this.futuraListaAutos.get(id).setRutaActual(new Ruta());
	        		this.futuraListaAutos.get(id).getRutaActual().inicializarRuta(vehiculo);
	        		
	        	}
	        	
	        	
	        	//vehiculo.getRutaActual().inicializarRuta(vehiculo);
	        	
	        	
	        	//vehiculo.getRutaActual().getListaPaquetes().clear();
	        	//Paquete paquete=vehiculo.getRutaActual().getListaPaquetes().get(0);
	        	//vehiculo.getRutaActual().getListaPaquetes().add(paquete);
	        	vehiculo.setCantidadPaquetes(0);
	        	vehiculo.setPosicionRelativa(0);
	        	vehiculo.setPosicionRuta(0);
	        	vehiculo.setEstado(0);
	        	vehiculo.setTiempoDescanzo(0);
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
		for(int i=Simulacion.indicePedidoFinal+1;i<Simulacion.listaPedidos.size();i++){
			
			Pedido pedido=Simulacion.listaPedidos.get(i);
			if(/*Simulacion.hastaIntervalo(pedido)*/ pedido.getMinutoLlegada()>Simulacion.minutoAcumulado+Simulacion.intervaloTiempo){
				limite++;
			} else break;
		}
		Simulacion.indicePedidoFinal+=limite;
		Collections.sort(Simulacion.listaPedidos.subList(Simulacion.indicePedidoInicial,Simulacion.indicePedidoFinal),new pedidosComparator());
		Collections.sort(this.listaMotos,new vehiculoComparator());
		Collections.sort(this.listaAutos,new vehiculoComparator());
		
		for(int i=Simulacion.indicePedidoInicial;i<=Simulacion.indicePedidoFinal;i++){
			Pedido pedido=Simulacion.listaPedidos.get(i);
		
			if (pedido.getMinutoLlegada()>Simulacion.minutoAcumulado+Simulacion.intervaloTiempo)break;
			for(Vehiculo moto:this.listaMotos){
				if (moto.getEstado()==0 || moto.getEstado()==2)continue;
				moto.EquiparPaquetes(pedido);
				if (pedido.getCantidadPaquetes()==0){
						swapPedidos(pedido,Simulacion.listaPedidos.get(Simulacion.indicePedidoInicial));
						Simulacion.indicePedidoInicial++;
				}
			}
			for(Vehiculo auto:this.listaMotos){
				if (auto.getEstado()==0 || auto.getEstado()==2)continue;
				auto.EquiparPaquetes(pedido);
				if (pedido.getCantidadPaquetes()==0){
					swapPedidos(pedido,Simulacion.listaPedidos.get(Simulacion.indicePedidoInicial));
					Simulacion.indicePedidoInicial++;
				}
			}
		
			
		}
		
		
		
		
		return 1;
	}
	
	public void swapPedidos(Pedido p1,Pedido p2){
		
		Pedido Paux=new Pedido(p1);
		p1=p2;
		p2=Paux;
		
	}
	public int planificarRespuesta(){
		//planificar la respuesta en base a pedidos leidos
		if(Simulacion.listaPedidos.size()==0||Simulacion.tiempoActual==4320 || Simulacion.indicePedidoInicial>Simulacion.indicePedidoFinal){
			return 0; //se acabó
		}
		solucion();
		return 1;
	}
}

