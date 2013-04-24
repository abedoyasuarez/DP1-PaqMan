package pacote.Model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pacote.Model.Bean.Envio;
import pacote.Model.Bean.Vuelo_Mov;

public class DAO_Vuelo_Mov extends ConnectBD{
	public void insertar_Vuelo_Mov(Vuelo_Mov vuelo_mov, int envio_cantidad, String padre_id, Date hora_ini) throws SQLException{
		String sql = 	" INSERT INTO Vuelo_Mov(vuelo_id, envio_id, vuelo_mov_estado, padre_id, envio_cantidad, vuelo_mov_fecha) " +
						" VALUES (?,?,1,?,?,?)";

		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, vuelo_mov.vuelo_id);
		pst.setInt(2, vuelo_mov.envio_id);
		pst.setString(3, padre_id);
		pst.setInt(4, envio_cantidad);
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha=sdf.format(hora_ini);
		
		pst.setString(5, fecha);
	    pst.execute();
	}
	
	public List<Envio> listarEnviosVuelo(int vuelo_id) throws SQLException{
		List<Envio> listEnvio =  new ArrayList<Envio>();
		
		String sql = "SELECT envio_id FROM Vuelo_Mov WHERE vuelo_id = ? AND vuelo_mov_estado=1";
		PreparedStatement pst = conexion.conn.prepareStatement(sql);
		pst.setInt(1, vuelo_id);
	    pst.execute();
	    ResultSet rs = pst.getResultSet();
	    while(rs.next()){
	    	Envio env = new Envio();
	    	env.id = rs.getInt(1);
	    	listEnvio.add(env);
	    }
	    return listEnvio;
	}
	
}
