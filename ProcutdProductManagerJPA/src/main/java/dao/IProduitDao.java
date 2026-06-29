package dao;
import java.util.*;

public interface IProduitDao {
    public  void save(Produit p) ;
    public List<Produit> findAll();
    public List<Produit> findByDesignation(String mc);
    public Produit findByID(Long id);
    public void update (Produit p);
    public void deleteById(Long id);
}