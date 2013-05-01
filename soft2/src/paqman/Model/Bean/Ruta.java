package paqman.Model.Bean;
import java.util.Date;
import java.util.List;

public class Ruta {
	private int id;
	private int distancia;
	private List<Nodo> trayectoria;
	private Date salidaAlmacen;
	private Date llegadaAlmacen;
	private double costo;
	private int estado; //0 Normal 1 Incidencia
	private Vehiculo vehiculo;
	private List<Paquete> listaPaquetes;
}
