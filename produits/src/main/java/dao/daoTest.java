package dao;

import java.util.List;
import metier.entities.Produit;
public class daoTest {
   public static void main(String[] args) {
	   ProduitDaoImpl dao = new ProduitDaoImpl();
	   Produit p1 = dao.save(new Produit("HP 22",1000,20));
	   Produit p2 = dao.save(new Produit("DELL 232",700,40));
	  System.out.println(p1.toString());
	  System.out.println(p2.toString());
	  System.out.println("chercher une produits");
	  List <Produit> prods = dao.produitParMC("%HP%");
	  for(Produit p:prods) {
		  System.out.println(p.toString());
	  }
   }
}
