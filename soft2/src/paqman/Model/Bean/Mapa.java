package paqman.Model.Bean;
import java.util.List;

public class Mapa {
	public static int filas;
	public static int columnas;
	public static int numNodos;
	public static List<Nodo> mapa;
	
	public int getNumNodos() {
		return numNodos;
	}
	
	public void setNumNodos(int numNodos) {
		this.numNodos = numNodos;
	}
}
