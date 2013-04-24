package pacote.Model.Bean.Response;


import java.util.ArrayList;
import java.util.List;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Usuario;

public class ListUsuario extends Mensaje{
	public List<Usuario> listaUser = new ArrayList<Usuario>();	
}
