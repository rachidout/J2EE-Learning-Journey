<%@page import="entities.Produit" %>
<%@page import="java.util.List" %>
<% 
   HttpSession sess = request.getSession(false);
   String name = (String)request.getAttribute("username");
   List<Produit> catalogue = (List<Produit>)request.getAttribute("catalogue");
   
  if(sess == null || name == null || session == null){
	  response.sendRedirect("login.html");
	  return;
  }
%>
     <!DOCTYPE html>
            <html>
            <head>
            <title>Catalogue de produits</title>
            <link rel='stylesheet' href='style.css'>
            </head>
            <body>
            <div class='header'>
            <span>Bienvenue, <strong><%= name %></strong></span>
            <a href='panier'>Voir mon panier</a>
            <a href='deconnexion'>Déconnexion</a>
            </div>
            <h2>Catalogue de produits</h2>
            <table>
            <thead>
            <tr>
            <th>ID</th>
            <th>Nom du produit</th>
            <th>Prix</th>
            <th>Action</th>
            </tr>
            </thead>
            <tbody>
              <% 
              for(Produit p : catalogue) {
              %>
              <tr>
              <td><%= p.getId() %> </td>
              <td><%= p.getNom() %> </td>
              <td><%= p.getPrix() %> </td>
              <td>
               <a href='panier?action=ajouter&id=<%=p.getId()%>'>Ajouter au panier</a>
              
              </td>
              
              <%
              }
              %>
              </tr>
            </tbody>
  
            </table>
            </body>
            </html>