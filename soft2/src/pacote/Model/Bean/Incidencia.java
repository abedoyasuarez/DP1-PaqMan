package pacote.Model.Bean;

import java.sql.Date;



public class Incidencia {
	public int incidencia_id;
	public int usuario_id;
	public String incidencia_descripcion;
	public Date	incidencia_fecha_registro;
	public Date incidencia_fecha_actualizacion;
	public int incidencia_estado; //CAMBIAR EN BD A TIPO INT
	public int vuelo_id;
}
