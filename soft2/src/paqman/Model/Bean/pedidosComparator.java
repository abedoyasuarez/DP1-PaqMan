package paqman.Model.Bean;

import java.util.Comparator;

public class pedidosComparator implements Comparator<Pedido> {
	@Override
	public int compare(Pedido p1, Pedido p2) {
		int delayP1=p1.getMinutoLlegada()+p1.getTiempoEntrega()-Simulacion.minutoAcumulado;
		int delayP2=p2.getMinutoLlegada()+p2.getTiempoEntrega()-Simulacion.minutoAcumulado;
		
		return delayP1-delayP2;
	}
}

