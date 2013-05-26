package paqman.Model.Bean;
import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
	private int id;
	private int tipo; //MOTO 0 AUTO 1 
	private int capacidad;
	private int cantidadPaquetes;
	//private List<Paquete> listaPaquetes; ya esta en ruta
	private int velocidad;
	private double costoHE;//costo por hora recorrida
	
	private int tiempoTrabajo;
	private int estado; // 0 EN ALMACEN - 1 EN CAMINO - 2 DESCANZO - 3 REPARACION
	private int estadoAnterior;
	private int turno; //TURNO 0 - 1 - 2		
	private int posicionRuta; //
	private double saltoDistancia;
	private double distanciaRecorrida; //distancia recorrida de la ruta
	private int posicionRelativa; //indice de un nodo de la ruta	
	private int	tiempoDescanzo;
	private int tiempoReparacion;
	private Ruta rutaActual;
	private double costoPorKm;
	
	//private List<Ruta> listaRutas; no se necesita historico
	
	public void inicializarVehiculo(int tipo,int codigo){
		
		this.id=codigo;
		this.tipo=tipo;		
		this.cantidadPaquetes=0;
		
		this.tiempoTrabajo=0;
		this.tiempoDescanzo=0;
		this.tiempoReparacion=0;
		this.estado=0;
		this.estadoAnterior=0;
		this.turno=0;
		this.posicionRuta=-1;
		this.distanciaRecorrida=0.0;
		this.posicionRelativa=-1;
		
		this.rutaActual=new Ruta();
		this.rutaActual.inicializarRuta(this);
		
		if (tipo==0){//motos
			this.capacidad=Simulacion.capacidadMotos;
			this.velocidad=Simulacion.velocidadMotos;
			this.costoPorKm=Simulacion.costoKmMotos;
			this.costoHE=Simulacion.costoHEMoto;
			
		}
		else{//autos
			this.capacidad=Simulacion.capacidadAutos;
			this.velocidad=Simulacion.velocidadAutos;
			this.costoPorKm=Simulacion.costoKmAutos;
			this.costoHE=Simulacion.costoHECarro;
			
		}
		this.saltoDistancia=60.0/(this.velocidad*1.0);
		
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getCantidadPaquetes() {
		return cantidadPaquetes;
	}
	public void setCantidadPaquetes(int cantidadPaquetes) {
		this.cantidadPaquetes = cantidadPaquetes;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getTiempoTrabajo() {
		return tiempoTrabajo;
	}
	public void setTiempoTrabajo(int tiempoTrabajo) {
		this.tiempoTrabajo = tiempoTrabajo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTurno() {
		return turno;
	}
	public void setTurno(int turno) {
		this.turno = turno;
	}
	public int getPosicionRuta() {
		return posicionRuta;
	}
	public void setPosicionRuta(int posicionRuta) {
		this.posicionRuta = posicionRuta;
	}
	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}
	public void setDistanciaRecorrida(double distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}
	public int getPosicionRelativa() {
		return posicionRelativa;
	}
	public void setPosicionRelativa(int posicionRelativa) {
		this.posicionRelativa = posicionRelativa;
	}
	public int getTiempoDescanzo() {
		return tiempoDescanzo;
	}
	public void setTiempoDescanzo(int tiempoDescanso) {
		this.tiempoDescanzo = tiempoDescanso;
	}
	public Ruta getRutaActual() {
		return rutaActual;
	}
	public void setRutaActual(Ruta rutaActual) {
		this.rutaActual = rutaActual;
	}
	public double getCostoPorKm() {
		return costoPorKm;
	}
	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}
	public double getSaltoDistancia() {
		return saltoDistancia;
	}
	public void setSaltoDistancia(double saltoDistancia) {
		this.saltoDistancia = saltoDistancia;
	}
	public double getCostoHE() {
		return costoHE;
	}
	public void setCostoHE(double costoHE) {
		this.costoHE = costoHE;
	}
	
	public int getTiempoReparacion() {
		return tiempoReparacion;
	}



	public void setTiempoReparacion(int tiempoReparacion) {
		this.tiempoReparacion = tiempoReparacion;
	}



	public int getEstadoAnterior() {
		return estadoAnterior;
	}



	public void setEstadoAnterior(int estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}



	public int EquiparPaquetes(Pedido pedido){
		
		if(estado==0 || estado==1){
			if (esPosibleEquipar(pedido)){
				equipar(pedido);
				if (tieneQueSalir()){
					posicionRuta=0;
					posicionRelativa=0;
					estado=1;
					estadoAnterior=1;
					this.rutaActual.setMinutoSalidaAlmacen(Simulacion.minutoAcumulado);
					for(Paquete paquete:this.rutaActual.getListaPaquetes()){
						paquete.setEstado(1);
					}
					regresarAlmacen();
					this.rutaActual.setMinutoLlegadaAlmacen(Simulacion.minutoAcumulado+(this.rutaActual.getDistancia()*60/this.velocidad));
				}
			}
			
			
		}
		
		
		return 1;
	}
	
	public boolean esPosibleEquipar(Pedido pedido){
		
		if (cantidadPaquetes==capacidad) return false;
		Nodo ultimoNodo=rutaActual.getUltimoNodo();
		int dist=pseudoDistancia(ultimoNodo,pedido.getNodoDestino());
		if (rutaActual.getTrayectoria().size()==1) return true;
		if (dist>pseudoDistancia(ultimoNodo,Simulacion.almacen))return false;
		return true;
		
	}
	public int pseudoDistancia(Nodo a,Nodo b){
		
		return Math.abs(a.coorX-b.coorX)+Math.abs(a.coorY-b.coorY);
		
	}
	
	public int equipar(Pedido pedido){
		
		int [] camino=Simulacion.BFS(this.getRutaActual().getUltimoNodo(),pedido.getNodoDestino());
		//List <Nodo> subRuta=construirCamino(this.getRutaActual().getUltimoNodo().getCoorAbs(),camino);
		this.rutaActual.anhadirCamino(this.getRutaActual().getUltimoNodo().getCoorAbs(),camino);
		int distancia=camino[(Mapa.filas+1)*(Mapa.columnas+1) +2];
		this.rutaActual.setDistancia(this.rutaActual.getDistancia()+distancia);
		if (pedido.getCantidadPaquetes()>this.getCapacidad()-this.getCantidadPaquetes()){
			this.rutaActual.getListaPaquetes().add(new Paquete(this.capacidad-this.cantidadPaquetes,pedido,distancia,this.rutaActual.getTrayectoria().size()-1));
			
			this.setCantidadPaquetes(this.capacidad);
			pedido.setCantidadPaquetes(this.capacidad-this.cantidadPaquetes);
			
		}
		else{
			this.rutaActual.getListaPaquetes().add(new Paquete(pedido.getCantidadPaquetes(),pedido,distancia,this.rutaActual.getTrayectoria().size()-1));			
			this.setCantidadPaquetes(pedido.getCantidadPaquetes());
			pedido.setCantidadPaquetes(0);
			
		}
		//en lista de paquetes se encuentra punto de entrega
		//this.getRutaActual().getTrayectoria().addAll(subRuta);
		
		
		return 1;
	}
	
	public boolean tieneQueSalir(){
		if (this.rutaActual.getTrayectoria().size()==1)return false;
		if (this.cantidadPaquetes==this.capacidad) return true;	
		double tiempoIda=((this.rutaActual.getTrayectoria().size()-1)*1.0/this.velocidad)*60.0;
		int numeroClientes=this.rutaActual.getListaPaquetes().size();
		int posX=this.rutaActual.getUltimoNodo().getCoorX();
		int posY=this.rutaActual.getUltimoNodo().getCoorY();
		int dist=pseudoDistancia(new Nodo(posX,posY), Simulacion.almacen);
		double tiempoRegreso=(dist*1.0/this.velocidad)*60.0;
		double tiempoRestante=8.0*60.0-(this.tiempoTrabajo+this.tiempoDescanzo);
		if (tiempoIda+tiempoRegreso>tiempoRestante-60.0) return true;
		for (Paquete paquete:this.rutaActual.getListaPaquetes()){
			Pedido pedido=paquete.getPedido();
			if (pedido.getNodoDestino().coorAbs==Simulacion.almacen.getCoorAbs())continue;
			if (paquete.getDistancia()/this.velocidad>=paquete.getPedido().getMinutoLlegada()+paquete.getPedido().getTiempoEntrega()-Simulacion.minutoAcumulado+Simulacion.margenSeguridad){
				return true;
			}
			
			
		}
		
		return false;
		
	}
	public void regresarAlmacen(){
		int dist=this.rutaActual.getTrayectoria().size();
		this.rutaActual.anhadirCaminoReversa(Simulacion.almacen.getCoorAbs(), Simulacion.patriarca);
		dist=this.rutaActual.getTrayectoria().size()-dist;
		this.rutaActual.getListaPaquetes().add(new Paquete(0,null,dist,this.rutaActual.getTrayectoria().size()-1));
		
		
	
	}
	
	public List<Nodo> construirCamino(int nodo,int [] padre){
		
		if (padre[nodo]==nodo) return new ArrayList();
		List<Nodo> aux=construirCamino(padre[nodo],padre);
		if (padre[nodo]!=nodo)aux.add(new Nodo(nodo%Mapa.columnas,nodo/Mapa.columnas));	
		
		return aux;
	}
	
	
} 
