import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
	
	//Imagen, Nombre, Precio, Unidades y Descripción
	
	private String id;
	private String nombre;
	private String precio;
	private String unidades;
	private String descripcion;
	private String imagen;
	
	public Producto (ResultSet rs) throws SQLException
	{
		this.id = rs.getString("ID");
		this.nombre = rs.getString("NOMBRE");
		this.precio = rs.getString("PRECIO");
		this.unidades = rs.getString("UNIDADES");
		this.descripcion = rs.getString("DESCRIPCION");
		this.imagen = rs.getString("IMAGEN");
	}
}
