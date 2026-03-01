package web;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Produit;

import javax.servlet.http.HttpServletRequest;
import dao.*;
@WebServlet(name="cs" , urlPatterns={"*.do"})


public class ControllerServlet extends HttpServlet{
  private IProduitDao metier ;
  public void init() throws ServletException {
	  metier = new ProduitDaoImpl();
  }
	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String path = request.getServletPath();
  
  if(path.equals("/index.do")){
	  request.getRequestDispatcher("produits.jsp").forward(request,response);
  }else if(path.equals("/chercher.do")) {
	  String mc = request.getParameter("mc");
	  ProduitModel model = new ProduitModel();
	  model.setMc(mc);
	  List<Produit> produits = metier.produitParMC(mc);
	  model.setProduits(produits);
	  request.setAttribute("model",model);
	  request.getRequestDispatcher("produits.jsp").forward(request, response);
  }else {
	  response.sendError(Response.SC_NOT_FOUND);
  }
		
}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	

}
