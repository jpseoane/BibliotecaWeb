package net.codejava.javaee.libreria;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * Pagina controladora para la aplicacion, en todos las peticiones y 
 * respuestas del usuario relacionadas con el libro.
 * @author GRUPO 5 UNLAM 
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LibroDAO libroDAO;
        private RevistaDAO revistaDAO;
        
        
        /**
          * Inicia la conexion y la instancia del DAO 
        */
	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		libroDAO = new LibroDAO(jdbcURL, jdbcUsername, jdbcPassword);
                revistaDAO = new RevistaDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/nuevo":
				mostrarFormNuevo(request, response);
				break;
			case "/insertar":
				insertarLibro(request, response);
				break;
			case "/borrar":
				borrarLibro(request, response);
				break;
			case "/editar":
				mostrarEditarForm(request, response);
				break;
			case "/actualizar":
				actualizarLibro(request, response);
				break;
                        case "/listarRevistas":
				listarRevistas(request, response);
				break;
			default:
				listarLibros(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listarLibros(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		
                List<Libro> listaLibros = libroDAO.listarLibros();
		request.setAttribute("listaLibros", listaLibros);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarLibros.jsp");
		dispatcher.forward(request, response);
	}
        private void listarRevistas(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
                List<Revista> listaRevistas= revistaDAO.listarRevista();
            
                request.setAttribute("listaRevistas", listaRevistas);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ListarRevistas.jsp");
		dispatcher.forward(request, response);
	}


	private void mostrarFormNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("LibroForm.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrarEditarForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Libro libroEditar = libroDAO.getLibro(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("LibroForm.jsp");
		request.setAttribute("libro", libroEditar);
		dispatcher.forward(request, response);

	}

        //Inserta el libro en la base de datos
	private void insertarLibro(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		float precio = Float.parseFloat(request.getParameter("precio"));

		Libro nuevoLibro = new Libro(titulo, autor, precio);
		libroDAO.ingresarLibro(nuevoLibro);
		response.sendRedirect("list");
	}

	private void actualizarLibro(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String autor = request.getParameter("autor");
		float precio = Float.parseFloat(request.getParameter("precio"));

		Libro libro = new Libro(id, titulo, autor, precio);
		libroDAO.actualizarLibro(libro);
		response.sendRedirect("list");
	}

	private void borrarLibro(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Libro libro = new Libro(id);
		libroDAO.borrarLibro(libro);
		response.sendRedirect("list");

	}

}
