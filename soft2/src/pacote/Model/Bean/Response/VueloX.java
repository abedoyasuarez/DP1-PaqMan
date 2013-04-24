package pacote.Model.Bean.Response;

import java.io.Serializable;
import java.util.Date;

public class VueloX implements Serializable{
   public int vuelo_id;
   public String padre_id;
   public String horaSalida;
   public Date horaSalidaDate;
   public String horaLlegada;
   public Date horaLlegadaDate;
   
   public int capacidad;
   public int cantidadLleno;
   public float porcentaje;
   public float porcentajeLlenado;
   
   public int almacenPartidaID;
   public String almacenPartida;
   public float almacenPartidaLatitud;
   public float almacenPartidaLongitud;
   
   public int almacenLlegadaID;
   public String almacenLlegada;
   public float almacenLlegadaLatitud;
   public float almacenLlegadaLongitud;
   
   public String codVuelo;
   public int existe;
}
