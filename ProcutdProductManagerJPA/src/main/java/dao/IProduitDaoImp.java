package dao;

import java.util.List;
import jakarta.persistence.*;   // ✅ was: javax.persistence.*

public class IProduitDaoImp implements IProduitDao {

    private EntityManager entityManager;

    public IProduitDaoImp() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ProductManagerPU");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void save(Produit p) {
        entityManager.getTransaction().begin();   // ✅ transaction required
        entityManager.persist(p);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Produit> findAll() {
        try {
            return entityManager
                    .createQuery("SELECT p FROM Produit p", Produit.class)
                    .getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> findByDesignation(String mc) {
        return entityManager
                .createQuery("SELECT p FROM Produit p WHERE p.designation LIKE :mc", Produit.class)
                .setParameter("mc", "%" + mc + "%")
                .getResultList();
    }

    @Override
    public Produit findByID(Long id) {
        return entityManager.find(Produit.class, id);
    }

    @Override
    public void update(Produit p) {
        entityManager.getTransaction().begin();
        entityManager.merge(p);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Produit p = entityManager.find(Produit.class, id);
        if (p != null) entityManager.remove(p);
        entityManager.getTransaction().commit();
    }
}