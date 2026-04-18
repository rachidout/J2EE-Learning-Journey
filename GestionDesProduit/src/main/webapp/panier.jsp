<%@ page import="entities.Panier, entities.Produit, java.util.Map, java.util.List" %>
<%
    String username = (String) session.getAttribute("user");
    Panier panier = (Panier) session.getAttribute("panier");
    String message = (String) session.getAttribute("message");
    String error = (String) session.getAttribute("error");
    List<Produit> catalogue = (List<Produit>) request.getAttribute("catalogue");
    session.removeAttribute("message");
    session.removeAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Votre Panier</title>
    <link rel='stylesheet' href='style.css'>
</head>
<body>
    <div class='header'>
        <span>Panier de <strong><%= username %></strong></span>
        <a href='produits'>Continuer les achats</a>
        <a href='deconnexion'>Deconnexion</a>
    </div>
    
    <h2>Mon Panier</h2>
    
    <% if (message != null) { %>
        <p style='color: green;'><%= message %></p>
    <% } %>
    
    <% if (error != null) { %>
        <p style='color: red;'><%= error %></p>
    <% } %>
    
    <%
        Map<Integer, Integer> articles = panier.getArticles();
        if(articles.isEmpty()) {
    %>
        <p>Votre panier est vide.</p>
    <% } else { %>
        <table>
            <tr>
                <th>ID Produit</th>
                <th>Nom du produit</th>
                <th>Prix unitaire</th>
                <th>Quantite</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
            <%
                double totalGeneral = 0;
                for(Map.Entry<Integer, Integer> entry : articles.entrySet()) {
                    int idProduit = entry.getKey();
                    int quantite = entry.getValue();
                    
                    // Recherche du produit dans le catalogue
                    Produit produitTrouve = null;
                    if(catalogue != null) {
                        for(Produit p : catalogue) {
                            if(p.getId() == idProduit) {
                                produitTrouve = p;
                                break;
                            }
                        }
                    }
                    
                    if(produitTrouve != null) {
                        double total = produitTrouve.getPrix() * quantite;
                        totalGeneral += total;
            %>
            <tr>
                <td><%= idProduit %></td>
                <td><%= produitTrouve.getNom() %></td>
                <td><%= String.format("%.2f", produitTrouve.getPrix()) %> DH</td>
                <td><%= quantite %></td>
                <td><%= String.format("%.2f", total) %> DH</td>
                <td><a class='btn' href='panier?action=supprimer&id=<%= idProduit %>'>Supprimer</a></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        
        <h3>Total generale : <%= String.format("%.2f", totalGeneral) %> DH</h3>
        <br>
        <a class='btn' href='panier?action=vider'>Vider le panier</a>
    <% } %>
</body>
</html>