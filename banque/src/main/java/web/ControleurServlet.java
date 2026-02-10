package web;
import java.metier
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="cs" ,urlPatterns= {"/Controller","*.lafak"})
public class ControleurServlet extends HttpServlet {
@Override
public void init() throws ServletException {
	// TODO Auto-generated method stub
	super.init();
}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("VueCredit.jsp").forward(req , resp);	}
@Override
   protected void doPost(HttpServletRequest req ,HttpServletResponse resp) throws ServletException , IOException {
	double montant = Double.parseDouble(req.getParameter("montant"));
	double  taux = Double.parseDouble(req.getParameter("taux"));
	int duree = Integer.parseInt(req.getParameter("duree"));
	 
}
}
