package web;
import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entities.Panier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entities.*;
@WebServlet(urlPatterns="panier")
public class PanierServlet extends HttpServlet {
  
    @Override
	 public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("user") == null) {
			response.sendRedirect("login.html");
			return ;
		}
		Panier panier =(Panier) session.getAttribute("panier");
		if(panier == null) {
			panier = new Panier();
			session.setAttribute("panier", panier);
		}
		String action = request.getParameter("action");
		String idParam = request.getParameter("id");
		if("ajouter".equals(action) && idParam != null && !idParam.isEmpty()) {
			try {
				int idProduit = Integer.parseInt(idParam);
				panier.ajouterProduit(idProduit);
				session.setAttribute("message", "Produit ajouter au panier avec succes!");
			}catch(NumberFormatException e) {
	                session.setAttribute("error", "ID de produit invalide");
	             
			}
            response.sendRedirect("panier");
          return ;
		}else if("supprimer".equals(action) && idParam != null){
			try {
			int idProduit = Integer.parseInt(idParam);
			panier.retirerProduit(idProduit);
			session.setAttribute("message", "produit supprimer avec succes!");
		}catch(NumberFormatException e) {
			session.setAttribute("error", "ID de produit invalide");
		}response.sendRedirect("panier");
		return ;
	 }else if("vider".equals(action)) {
		
		 panier.vider();
		 session.setAttribute("message", "Le panier a été vidé.");
		 response.sendRedirect("panier");
         return;
	 }
		 afficherPanier(request, response ,session);
		 
   }
	 
	 private void afficherPanier(HttpServletRequest request , HttpServletResponse response , HttpSession session) throws IOException{
		 
	 }
}              
     























