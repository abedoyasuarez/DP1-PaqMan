package pacote.Model.Bean;

public class Usuario extends Mensaje{
	public int id;
	public String nombre;
	public String apellidos;
	public int sexo;
	public String email;
	public String user;
	public String password;
	public String telefono;
	public String direccion;
	public int pais_id;
	public int documento_id;
	public String numero_documento;
	public int rol_id;
	public int estado;		
	public int almacen_id;	
	
	public Usuario(){}
	
	public Usuario(int id, String nombre, String apellidos, int sexo, String email, String user, String password, String telefono, String direccion, int pais_id, int documento_id, String numero_documento, int rol_id, int estado, int almacen_id){
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sexo = sexo;		
		this.email = email;
		this.user = user;
		this.password = password;
		this.telefono = telefono;
		this.direccion = direccion;
		this.pais_id = pais_id;
		this.documento_id = documento_id;
		this.numero_documento = numero_documento;
		this.rol_id = rol_id;		
		this.estado = estado;
		this.almacen_id = almacen_id;
	}
}
