package paqman.Model.Bean;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class Mapa {
	public static int filas;
	public static int columnas;
	public static int numNodos;
	public static List<Nodo> grafo;
	
	//Mandar filas y columnas +1
	public  int crearMapa(int filas,int columnas){
		grafo=new ArrayList<Nodo>();
		if(filas<=0||columnas<=0) return 0;
		this.filas=filas;
		this.columnas=columnas;
		this.numNodos=filas*columnas;
		int i;
		for (i=0;i<this.numNodos;i++){
			int x=(i%this.columnas);
			int y=(i/this.columnas);
//			int up=(i+this.columnas>=this.columnas*this.filas)?-1:i+this.columnas;
//			int down=(i-this.columnas<0)?-1:i-this.columnas;;
//			int left=((i)%this.columnas==0)?-1:i-1;
//			int right=((i+1)%this.columnas==0)?-1:i+1;
			Nodo nodoInsertar=new Nodo(x,y);
			grafo.add(nodoInsertar);
		}
		return 1;
	}
	
	public static int imrimeMapa(){
		int i,j;
		for(i=0;i<filas;i++){
			for(j=0;j<columnas;j++){
				System.out.print(grafo.get(i*columnas+j).coorAbs+" up( "+grafo.get(i*columnas+j).up +")"+" down( "+grafo.get(i*columnas+j).down +")"+" left( "+grafo.get(i*columnas+j).left +")"+" right( "+grafo.get(i*columnas+j).right +")");	
			}
			System.out.println();
		}
		return 0;
	}
}
