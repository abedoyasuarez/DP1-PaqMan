package paqman.Model.Bean;

public class Incidencia {

	private int minutoOcurrencia;
	private int idPedido;
	
	public Incidencia(int minutoOcurrencia,int idPedido){
		this.minutoOcurrencia=minutoOcurrencia;
		this.idPedido=idPedido;
	}
	
	public int getMinutoOcurrencia() {
		return minutoOcurrencia;
	}
	public void setMinutoOcurrencia(int minutoOcurrencia) {
		this.minutoOcurrencia = minutoOcurrencia;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
}
