package es.curso.controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.curso.negocio.Ordenador;
import es.curso.repositories.OrdenadorRepository;
import es.curso.repositories.jdbc.OrdenadorRepositoryJDBC;

@WebServlet("/OrdenadorServlet")
	public class OrdenadorServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		OrdenadorRepository repo = new OrdenadorRepositoryJDBC();
		List<Ordenador> lista = null;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			if (request.getParameter("comando") == null) {

				lista=repo.buscarTodos();
				request.setAttribute("lista", lista);
				RequestDispatcher despachador = request.getRequestDispatcher("listaordenadoresjdbc.jsp");
				despachador.forward(request, response);
				
			}else{
					String comando = request.getParameter("comando");
					
					if (comando.equals("formularioordenador")) {
						RequestDispatcher despachador=request.getRequestDispatcher("formularioordenador.html");
						despachador.forward(request, response);
						
					}else if (comando.equals("salvarordenador")) {
						String marca=request.getParameter("marca");
						int precio=Integer.parseInt(request.getParameter("precio"));

						Ordenador o=new Ordenador(marca,precio);
						OrdenadorRepository repo=new OrdenadorRepositoryJDBC();
						repo.insertar(o);
						
						lista = repo.buscarTodos();
						
						request.setAttribute("lista", lista);
						RequestDispatcher despachador = request.getRequestDispatcher("listaordenadoresjdbc.jsp");
						despachador.forward(request, response);
						
					}
			}
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		
		}

	}

