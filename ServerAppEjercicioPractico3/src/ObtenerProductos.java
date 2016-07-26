

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerProductos
 */
@WebServlet("/ObtenerProductos")
public class ObtenerProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerProductos() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String transformarProductosAJSON (List<Producto> lista_productos)
    {
    	String productos_json = null;
    	
    		Gson gson = new Gson();
    		productos_json = gson.toJson(lista_productos);
    	
    	return productos_json;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//DATA SOURCE
		
		Connection conexion = null;
		Statement statement = null;
		ResultSet rs = null;
		PrintWriter pw = null;
		List<Producto> lista_productos = null;
		
		try
		{
			lista_productos = new ArrayList<Producto>();
			conexion = Pool.getConnection();
			statement = conexion.createStatement();
			rs = statement.executeQuery("SELECT * FROM PRODUCTOS");
			
			Producto producto_aux = null;
			
			while (rs.next()) //Recorro el resulSet
			{
				producto_aux = new Producto(rs);
				lista_productos.add(producto_aux);
				
			}
			
			response.setContentType("application/json");//seteo la respuesta
			response.setStatus(HttpURLConnection.HTTP_OK);//seteo el codigo http de que ha ido bien la cosa! OK = 200
			
			String productos_json = transformarProductosAJSON (lista_productos);
		
			System.out.println("Productos como JSON = " + productos_json);
			
			pw = response.getWriter();//obtengo acceso al body
			pw.print(productos_json);//escribo los productos		
			
		}
		catch (Throwable t)
		{
			System.out.println(t);
		}
		finally 
		{
			Pool.liberarRecursos(conexion, statement);
			//t.printStackTrace();
		}		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
