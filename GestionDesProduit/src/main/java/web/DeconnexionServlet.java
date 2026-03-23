package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;

@WebServlet(name="deconnexion" ,urlPatterns="/deconnexion")

public class DeconnexionServlet extends HttpServlet{

	  @Override
	  protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession(false);
		  if(session != null) {
			  session.invalidate();
		  }
		  response.sendRedirect("login");
	  }
}
