package web;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entities.*;

@WebServlet(urlPatterns="/produits")
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
            request.setAttribute("username", username);
            request.setAttribute("catalogue", catalogue);
            request.getRequestDispatcher("produits.jsp").forward(request, response);
        }
    }
}