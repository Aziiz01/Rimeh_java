/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

/**
 *
 * @author kheir
 */

public class ProduitCrud {
    Connection TuniTrocDB = DBConnection.getConnection();
     public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tunitroc1", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
  public void ajouterproduit(Produit p, int id_user) {
    try {
        String requete = "INSERT INTO produit (type, categorie, nom, libelle, photo, ville, id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = TuniTrocDB.prepareStatement(requete);
        pst.setString(1, p.getType());
        pst.setString(2, p.getCategorie());
        pst.setString(3, p.getNom());
        pst.setString(4, p.getLibelle());
        pst.setString(5, p.getPhoto());
        pst.setString(6, p.getVille());
        pst.setInt(7, id_user);
        pst.executeUpdate();
        System.out.println("Produit ajouté");
    } catch(SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    public List<Produit> affich() {
    List<Produit> myList = new ArrayList<>();

    try {
        String requete = "SELECT * FROM produit";
        Statement st = TuniTrocDB.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Produit p = new Produit();
            p.setId(rs.getInt("id"));
            p.setType(rs.getString("type"));
            p.setCategorie(rs.getString("categorie"));
            p.setNom(rs.getString("nom"));
            p.setLibelle(rs.getString("libelle"));
            p.setPhoto(rs.getString("photo"));
            p.setVille(rs.getString("ville"));
            p.setId_user(rs.getInt("id_user"));
            myList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;
}

   
   
   
    public void supprimerProduit(Produit t){
        
        String query = "DELETE FROM produit WHERE produit.id="+ t.getId()+" ";
        try{
            Statement st =TuniTrocDB.createStatement();
            st.executeUpdate(query);
            System.out.println("Produit supprimé ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
   
  
      public void modifierProduit(Produit t ,String x,String y,String z,String a,String b  ){
            try{
        
        String requte ="update produit set type=?,categorie=?,nom=?,libelle=?,photo=?,ville=? where id="+ t.getId()+"";
        PreparedStatement pst= TuniTrocDB.prepareStatement(requte);
        pst.setString(1, t.getType());
        pst.setString(2, t.getCategorie());
        pst.setString(3, t.getNom());
        pst.setString(4, t.getLibelle());
        pst.setString(5, t.getPhoto());
        pst.setString(6, t.getVille());
        
        pst.executeUpdate();
        System.out.println("Produit Modifié");
    }catch (SQLException ex ){
            System.out.println(ex.getMessage());

    }
    
    }

}
