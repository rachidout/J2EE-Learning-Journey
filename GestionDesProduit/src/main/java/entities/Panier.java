package entities;

import java.util.*;

public class Panier {
   private Map<Integer , Integer> articles;
    
   public Panier() {
	   this.articles = new HashMap<>();
   }
   public void ajouterProduit(int idProduit) {
	   articles.put(idProduit, articles.getOrDefault(idProduit, 0) + 1);
   }
   public void retirerProduit(int idProduit) {
	   articles.remove(idProduit);
   }
   public void modifierQuantite(int idProduit , int quantite) {
	   if(quantite <= 0) {
		   retirerProduit(idProduit);
	   }
	   else {
		   articles.put(idProduit,quantite);   
	   }
   }
   public void vider() {
	   articles.clear();
   }
   public Map<Integer , Integer> getArticles(){
	   return articles;
   }
   public int getNomberTotaleArticles() {
	   return articles.values().stream().mapToInt(Integer::intValue).sum();
   }
   public boolean estVide() {
	   return articles.isEmpty();
   }
}
