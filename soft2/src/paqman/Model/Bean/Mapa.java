package paqman.Model.Bean;
import java.util.List;

public class Mapa {
	public static int filas;
	public static int columnas;
	public static int numNodos;
	public static List<Nodo> grafo;
	
	//Mandar filas y columnas +1
	public int crearMapa(int filas,int columnas){
		if(filas<=0||columnas<=0) return 0;
		this.filas=filas;
		this.columnas=columnas;
		this.numNodos=filas*columnas;
		int i;
		for (i=0;i<this.numNodos;i++){
			int y=(i%this.columnas);
			int x=(i/this.columnas);
			int up=(i+this.columnas>=this.columnas*this.filas)?-1:i+this.columnas;
			int down=(i-this.columnas<0)?-1:i-this.columnas;;
			int left=((i-1)%this.columnas==0)?-1:i-1;
			int right=((i+1)%this.columnas==0)?-1:i+1;
			Nodo nodoInsertar=new Nodo(x,y);
			grafo.add(nodoInsertar);
		}
		return 1;
	}
}
