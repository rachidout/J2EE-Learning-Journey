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
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUITS(DESIGNATION,PRIX,QUANTITE) "
					+ "VALUES(?,?,?)");
 			ps.setString(2, p.getDesignation());
			ps.setDouble(3,p.getPrix());
			ps.setInt(4,p.getQuantite());
			ps.executeUpdate(); //insert delete update 
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs = ps2.executeQuery();
	   if(rs.next()) {			
				p.setId(rs.getLong("MAX_ID"));
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		List<Produit> produits = new ArrayList<Produit>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			ps.setString(1,mc); //% disent n'importe qoui 
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
	     Produit p = null;
		Connection connection = SingletonConnection.getConnection();
		try {
		 PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE ID = ?");
	     ps.setLong(1, id);
	     ResultSet rs = ps.executeQuery();
	     if(rs.next()) {
	    	 p = new Produit();
	     p.setId(rs.getLong("ID"));
	     p.setDesignation(rs.getString("DESIGNATION"));
	     p.setPrix(rs.getDouble("PRIX"));
	     p.setQuantite(rs.getInt("QUANTITE"));
	        }
	     ps.close();
	     }
		catch(SQLException e) {
			e.printStackTrace();
		} 
	     return p;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}
	

}
