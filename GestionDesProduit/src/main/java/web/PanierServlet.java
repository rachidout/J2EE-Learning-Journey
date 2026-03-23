package web;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.*;
import entities.Panier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entities.*;
@WebServlet(urlPatterns="/panier")
public class PanierServlet extends HttpServlet {
	private List<Produit> catalogue;

	@Override
    public void init() throws ServletException {
        // Initialisation du catalogue local pour les requêtes de recherche de produits
        catalogue = new ArrayList<>();
        catalogue.add(new Produit(1, "Laptop HP Pavilion", 8909.99));
        catalogue.add(new Produit(2, "Smartphone Samsung Galaxy", 5099.99));
        catalogue.add(new Produit(3, "Tablette iPad Air", 4909.99));
        catalogue.add(new Produit(4, "Tv Sony 302'", 1290.99));
    }
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
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 Panier panier = (Panier) session.getAttribute("panier");
		 String username = (String) session.getAttribute("user");
		 out.println("<!DOCTYPE html>");
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Votre Panier</title>");
	        out.println("<link rel='stylesheet' href='style.css'>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class='header'>");
	        out.println("<span> Panier de <strong>"+username+"</strong></span>");
	        out.println("<a href='produits'> Continuer les achats </a>");
	        out.println("<a href='deconnexion'> Deconnexion </a>");
	        out.println("</div>");
	         out.println("<h2>Mon Panier</h2>");
	         String message =(String) session.getAttribute("message");
	         String error = (String) session.getAttribute("error");
	         
	          if (message != null) {
	        	  out.println("<p style='color = green;'>" + message +"</p>");
	        	  session.removeAttribute("message");
	          }
	          if (error != null) {
	        	  out.println("<p style='color: red;'>" + error + "</p>");
	        	  session.removeAttribute("error");
	        	  }
	         Map<Integer,Integer> articles = panier.getArticles();
	         
	         if(articles.isEmpty()) {
	        	 out.println("<p>Votre panier est vide.</p>");
	         }else {
	        	 out.println("<table>");
	             out.println("<tr>");
	             out.println("<th>ID Produit</th>");
	             out.println("<th>Nom du produit</th>");
	             out.println("<th>Prix unitaire</th>");
	             out.println("<th>Quantité</th>");
	             out.println("<th>Total</th>");
	             out.println("<th>Action</th>");
	             out.println("</tr>");
	             double totalGeneral = 0;
	             for( Map.Entry<Integer, Integer> entry : articles.entrySet()) {
	            	 int idProduit = entry.getKey();
	            	 int quantite = entry.getValue();
	            	 Produit produit = trouverProduit(idProduit);
	            	 if(produit != null) {
	            		 double total = produit.getPrix() * quantite;
	            		 totalGeneral += total;
	            		 out.println("<tr>");
	            		 out.println("<td>"+ idProduit+"</td>");
	            		 out.println("<td>"+ produit.getNom() +"</td>");
	            		 out.println("<td>"+ String.format("%.2f", produit.getPrix()) +"DH</td>");
	            		 out.println("<td>"+ quantite+"</td>");
	            		 out.println("<td>" + String.format("%.2f", total) + " DH</td>");
	            		 out.println("<td> <a class='btn' href='panier?action=supprimer&id="+idProduit+"'> Supprimer</a></td>");
	            		 out.println("</tr>");
	                 }
	             }
	             out.println("</table>");
	             out.println("<h3>Total général : " + String.format("%.2f", totalGeneral) + " DH</h3>");
	             out.println("<br>");
	             out.println("<a class='btn' href='panier?action=vider'>Vider le panier</a>");
	         }
	         
	         out.println("</body>");
	         out.println("</html>");
	     }
 	      private Produit trouverProduit(int id) {
 	    	  for(Produit p : catalogue ) {
 	    		  if(id == p.getId()) {
 	    			  return p;
 	    		  }
 	    	  }
 	    	  return null;
 	      }
}              
     























