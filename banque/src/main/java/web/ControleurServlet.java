package web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.ICreditMetier;
import metier.CreditMetierIMP;
@WebServlet(name="cs" ,urlPatterns= {"/Controller","*.lafak"})
public class ControleurServlet extends HttpServlet {
	private ICreditMetier metier;
@Override
public void init() throws ServletException {
	metier = new CreditMetierIMP();
}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("VueCredit.jsp").forward(req , resp);	}
@Override
   protected void doPost(HttpServletRequest req ,HttpServletResponse resp) throws ServletException , IOException {
	double montant = Double.parseDouble(req.getParameter("montant"));
	double  taux = Double.parseDouble(req.getParameter("taux"));
	int duree = Integer.parseInt(req.getParameter("duree"));
	 CreditModel model = new CreditModel();
	 model.setMensualite(duree);
	 model.setMontant(montant);
	 model.setTaux(taux);
	 double resultat = metier.calculerM(montant, taux, duree);
	 model.setMensualite(resultat);
	 req.setAttribute("creditModel",model);
	 req.getRequestDispatcher("VueCredit.jsp").forward(req,resp);
}
}
