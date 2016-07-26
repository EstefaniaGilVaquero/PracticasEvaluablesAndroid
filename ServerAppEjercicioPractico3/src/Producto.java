import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
	
	//Imagen, Nombre, Precio, Unidades y Descripción
	
	private int id;
	private String nombre;
	private int precio;
	private int unidades;
	private String descripcion;
	private String imagen;
	
	public Producto (ResultSet rs) throws SQLException
	{
		this.id = rs.getInt("ID");
		this.nombre = rs.getString("NOMBRE");
		this.precio = rs.getInt("PRECIO");
		this.unidades = rs.getInt("UNIDADES");
		this.descripcion = rs.getString("DESCRIPCION");
		this.imagen = rs.getString("IMAGEN");
	}
	
//	@Override
//	public String toString() {
//		
//		return "ID = " + this.id + " Nombre = " +  this.nombre;
//	}
	
	

}
