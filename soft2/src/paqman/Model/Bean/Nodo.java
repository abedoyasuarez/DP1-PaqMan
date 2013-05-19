package paqman.Model.Bean;

public class Nodo {

	public int coorX;
	public int coorY;
	public int coorAbs;
	public int up;
	public int down;
	public int left;
	public int right;
	
	public Nodo(int coorX, int coorY/*, int coorAbs, int up, int down, int left,
			int right*/) {
		this.coorX = coorX;
		this.coorY = coorY;
		this.coorAbs = coorY*Mapa.columnas+coorX;
		this.up=(coorAbs+Mapa.columnas>=Mapa.columnas*Mapa.filas)?-1:coorAbs+Mapa.columnas;
		this.down=(coorAbs-Mapa.columnas<0)?-1:coorAbs-Mapa.columnas;;
		this.left=((coorAbs-1)%Mapa.columnas==0)?-1:coorAbs-1;
		this.right=((coorAbs+1)%Mapa.columnas==0)?-1:coorAbs+1;
	}

	public int getCoorX() {
		return coorX;
	}

	public void setCoorX(int coorX) {
		this.coorX = coorX;
	}

	public int getCoorY() {
		return coorY;
	}

	public void setCoorY(int coorY) {
		this.coorY = coorY;
	}

	public int getCoorAbs() {
		return coorAbs;
	}

	public void setCoorAbs(int coorAbs) {
		this.coorAbs = coorAbs;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public int getDown() {
		return down;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
	
	
}
