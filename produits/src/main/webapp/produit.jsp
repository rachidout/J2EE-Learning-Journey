<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/> 
    <title>Gestion des Produits</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
</head>
<body>

    <jsp:useBean id="model" class="metier.ProduitModel" scope="request" />

    <div class="container mt-5">
        <h2 class="mb-4">Rechercher un produit</h2>

        <form action="ControleurServlet" method="get" class="form-inline mb-4">
            <div class="form-group mr-2">
                <label for="motCle" class="mr-2">Mot Clé :</label>
                <input type="text" id="motCle" name="motCle" class="form-control" value="${model.motCle}" />
            </div>
            <button type="submit" class="btn btn-primary">Chercher</button>
        </form>

        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Désignation</th>
                    <th>Prix</th>
                    <th>Quantité</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${model.produits}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.designation}</td>
                        <td>${p.prix}</td>
                        <td>${p.quantite}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

</body>
</html>