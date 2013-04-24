package pacote.Model.Bean.Response;

import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Ruta;

public class ListRutas extends Mensaje{
	public List<Ruta> listaRutas = new ArrayList<Ruta>();
}
