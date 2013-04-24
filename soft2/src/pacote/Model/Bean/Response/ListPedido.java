package pacote.Model.Bean.Response;

import java.util.ArrayList;

import pacote.Model.Bean.Mensaje;
import pacote.Model.Bean.Pedido;
import pacote.Model.Bean.PedidoY;

public class ListPedido extends Mensaje{
	public ArrayList<PedidoY> listaPedidos = new ArrayList<PedidoY>();
}