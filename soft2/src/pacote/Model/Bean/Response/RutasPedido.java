package pacote.Model.Bean.Response;

import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.Mensaje;


public class RutasPedido extends Mensaje{
	public List< List<AlmacenResponse> > lRuta = new ArrayList< List< AlmacenResponse> >();
}
