package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import metier.entities.Produit;

public class ProduitDaoImpl implements IProduitDao{

	@Override
	public Produit save(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		List<Produit> produits = new ArrayList<Produit>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			ps.setString(1, "%" + mc +"%"); //% disent n'importe qoui 
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Produit p = new Produit();
				p.setId(rs.getLong("ID"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setQuantite(rs.getInt("QUANTITE"));
				produits.add(p);
			}
		}catch (SQLException e) { e.printStackTrace();}
			return produits;
	}

	@Override
	public Produit getProduit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}
	

}
