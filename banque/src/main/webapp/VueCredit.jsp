<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credit bancaire</title>
<meta charset='utf-8' >
</head>
<body>
  <div>
  <form method="post" action="calculerM.do">
   <label for="montant" > Montant </label>
   <input type="text"   id="montant" name="montant" placeholder="en DH">
   <label for="taux"> Taux</label>
   <input type="text" placeholder="taux en %" name="taux" id="taux" />
   <label for="duree"> Duree</label>
   <input type="text" placeholder="Mois" id="duree" name="taux" />
   <button type="submit"> Submit </button>
    </form>
  </div> 
</body>
</html>
