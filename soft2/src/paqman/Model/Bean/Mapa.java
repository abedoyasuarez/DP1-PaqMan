package paqman.Model.Bean;
import java.util.List;

public class Mapa {
	private static int filas;
	private static int columnas;
	private static int numNodos;
	public static List<List<Nodo>> mapa;
	
	public int getNumNodos() {
		return numNodos;
	}
	
	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}
}
