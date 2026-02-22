package dao;

import java.util.List;
import metier.entities.Produit;
public interface IProduitDao {

	
	public Produit save(Produit p);
	
	public List<Produit> produitParMC(String mc);
	
	public Produit getProduit(Long id);
	
	public void deleteProduit(Long id);
}
