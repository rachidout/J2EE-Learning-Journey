package web;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entities.*;

@WebServlet(name="cs" , urlPatterns="/produits")
public class ProduitsServlet extends HttpServlet{
    private List<Produit> catalogue;
    
    public void init() throws ServletException{
        catalogue = new ArrayList<>();
        catalogue.add(new Produit(1, "Laptop HP Pavilion", 8909.99));
        catalogue.add(new Produit(2, "Smartphone Samsung Galaxy", 5099.99));
        catalogue.add(new Produit(3, "Tablette iPad Air", 4909.99));
        catalogue.add(new Produit(4, "Tv Sony 302'", 1290.99));
    }

    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
        } else {
            String username = (String)session.getAttribute("user");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Catalogue de produits</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; }");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println(".btn-ajouter { background-color: #4CAF50; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; }");
            out.println(".btn-ajouter:hover { background-color: #45a049; }");
            out.println(".header { background-color: #333; color: white; padding: 10px; margin-bottom: 20px; }");
            out.println(".header a { color: white; margin-left: 20px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='header'>");
            out.println("<span>Bienvenue, <strong>" + username + "</strong></span>");
            out.println("<a href='panier'>Voir mon panier</a>");
            out.println("<a href='deconnexion'>Déconnexion</a>");
            out.println("</div>");
            out.println("<h2>Catalogue de produits</h2>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Nom du produit</th>");
            out.println("<th>Prix</th>");
            out.println("<th>Action</th>");
            out.println("</tr>");

            for (Produit p : catalogue) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNom() + "</td>");
                out.println("<td>" + String.format("%.2f", p.getPrix()) + " DH</td>");
                out.println("<td><a class='btn-ajouter' href='panier?action=ajouter&id=" + p.getId() + "'>Ajouter au panier</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}