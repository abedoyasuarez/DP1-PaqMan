package paqman.Model.Bean;

import java.util.Comparator;

public class vehiculoComparator implements Comparator<Vehiculo> {
	@Override
	public int compare(Vehiculo v1, Vehiculo v2) {
		
		if (v1.getEstado()==1 && v2.getEstado()==1)return v1.getRutaActual().getMinutoLlegadaAlmacen()-v2.getRutaActual().getMinutoLlegadaAlmacen();
		if (v1.getEstado()!=v2.getEstado())return v1.getEstado()-v2.getEstado();
		else return v1.getCantidadPaquetes()-v2.getCantidadPaquetes();
	}
}

