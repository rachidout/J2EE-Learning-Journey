package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entities.Panier;
import entities.Produit;

@WebServlet(urlPatterns="/panier")
public class PanierServlet extends HttpServlet {
    private List<Produit> catalogue;

    @Override
    public void init() throws ServletException {
        catalogue = new ArrayList<>();
        catalogue.add(new Produit(1, "Laptop HP Pavilion", 8909.99));
        catalogue.add(new Produit(2, "Smartphone Samsung Galaxy", 5099.99));
        catalogue.add(new Produit(3, "Tablette iPad Air", 4909.99));
        catalogue.add(new Produit(4, "Tv Sony 302'", 1290.99));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        Panier panier = (Panier) session.getAttribute("panier");
        if(panier == null) {
            panier = new Panier();
            session.setAttribute("panier", panier);
        }

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if(("ajouter".equals(action) || "supprimer".equals(action)) && idParam != null && !idParam.isEmpty()) {
            try {
                int idProduit = Integer.parseInt(idParam);
                if("ajouter".equals(action)) {
                    panier.ajouterProduit(idProduit);
                    session.setAttribute("message", "Produit ajouter au panier !");
                } else if("supprimer".equals(action)) {
                    panier.retirerProduit(idProduit);
                    session.setAttribute("message", "Produit supprimer du panier   !");
                }
            } catch(NumberFormatException e) {
                session.setAttribute("error", "ID de produit invalide");
            }
            response.sendRedirect("panier");
            return;
        } else if("vider".equals(action)) {
            panier.vider();
            session.setAttribute("message", "Le panier a ete vider.");
            response.sendRedirect("panier");
            return;
        }

        // Transmission des données nécessaires à la JSP
        request.setAttribute("catalogue", catalogue);
        
        // Redirection vers le fichier JSP
        request.getRequestDispatcher("/panier.jsp").forward(request, response);
    }
}