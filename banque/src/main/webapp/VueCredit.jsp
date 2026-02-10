<%@page import="web.CreditModel" %>
<%
  CreditModel model =(CreditModel)request.getAttribute("creditModel");
if(model == null){
	model = new CreditModel();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credit bancaire</title>
<meta charset='utf-8' >
</head>
<body>
  <div>
  <form method="post" action="calculerM.lafak">
   <label for="montant" > Montant </label>
   <input type="text"   id="montant" name="montant" value="<%=model.getMontant()%>" placeholder="en DH">
   <label for="taux"> Taux</label>
   <input type="text" placeholder="taux en %" value="<%=model.getTaux()%>" name="taux" id="taux" />
   <label for="duree"> Duree</label>
   <input type="text" placeholder="Mois" value="<%=model.getDuree()%>" id="duree" name="duree" />
   <button type="submit"> Submit </button>
    </form>
  </div> 
  <div>
    <strong>Resultat est = </strong> <%=model.getMensualite()%>
  </div>
</body>
</html> 
